package com.simonfl.hockeymoms.server;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.api.client.extensions.appengine.http.urlfetch.UrlFetchTransport;
import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.googleapis.GoogleUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.xml.atom.AtomParser;
import com.google.api.client.xml.XmlNamespaceDictionary;
import com.google.common.collect.Lists;
import com.simonfl.hockeymoms.shared.Calendar;
import com.simonfl.hockeymoms.shared.Game;

/**
 * Helper class that deals with communicating with the calendar service
 * 
 * @author simonfl@gmail.com
 */
public class CalendarUtil {

	private HttpRequestFactory requestFactory;
	private String url;

	public CalendarUtil(String url) {
		this.url = url;
		requestFactory = getTransport().createRequestFactory();
	}

	static private HttpTransport getTransport() {
		return new UrlFetchTransport();
	}

	public Calendar getCalendar() {
		try {
			CalendarFeed calendarFeed = getCalendarResponse().parseAs(
					CalendarFeed.class);
			List<Game> games = Lists.newArrayList();
			for (GameFeed gameFeed : calendarFeed.gameFeeds) {
				try {
					games.add(new Game(gameFeed.title,
							getGameDate(gameFeed.summary)));
				} catch (ParseException e) {
					// Print the exception and skip this game
					e.printStackTrace();
					continue;
				}
			}
			Calendar calendar = new Calendar(calendarFeed.title, games);
			return calendar;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * Parse the start date from the feed summary
	 * 
	 * @param summary
	 *            Example might look like: When: Sun Aug 7, 2011 8:45pm to
	 *            9:45pm&nbsp; PDT<br>
	 * <br>
	 *            Where: Yerba Buena Ice Skating Cntr. <br>
	 *            Event Status: confirmed
	 * @return The game start Date
	 * @throws ParseException
	 *             If we can't parse the date correctly
	 */
	private Date getGameDate(String summary) throws ParseException {
		int startIndex = summary.indexOf("When: ") + 6;
		int endIndex = summary.indexOf(" to");
		// When: Sun Aug 7, 2011 8:45pm to 9:45pm&nbsp; PDT<br> <br>Where: Yerba
		// Buena Ice Skating Cntr. <br>Event Status: confirmed
		String dateStr = summary.substring(startIndex, endIndex);
		// Sun Apr 17, 2011 8:45pm
		DateFormat formatter = new SimpleDateFormat("EEE MMM d, yyyy HH:mma");
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			// Try another format without the minutes
			// Sun Apr 17, 2011 10pm
			formatter = new SimpleDateFormat("EEE MMM d, yyyy HHa");
			return formatter.parse(dateStr);
		}
	}

	private HttpRequest getRequest() throws IOException {
		HttpRequest request = requestFactory
				.buildGetRequest(new GoogleUrl(url));

		GoogleHeaders headers = new GoogleHeaders();
		headers.setApplicationName("HockeyMoms/1.0");
		request.headers = headers;

		AtomParser parser = new AtomParser();
		parser.namespaceDictionary = new XmlNamespaceDictionary().set("",
				"http://www.w3.org/2005/Atom");
		request.addParser(parser);

		return request;
	}

	private HttpResponse getCalendarResponse() throws IOException {
		HttpRequest request = getRequest();
		return request.execute();
	}
}

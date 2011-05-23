package com.simonfl.hockeymoms.server;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

import com.google.api.client.extensions.appengine.http.urlfetch.UrlFetchTransport;
import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.googleapis.GoogleUrl;
import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.xml.XmlNamespaceDictionary;
import com.google.api.client.http.xml.atom.AtomParser;
import com.google.common.io.CharStreams;

/**
 * Helper class that deals with communicating with the calendar service
 * 
 * @author m0x
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

	/** Sets up the HTTP transport. */
	static private HttpTransport setUpTransport() throws IOException {
		HttpTransport result = new UrlFetchTransport();
		GoogleHeaders headers = new GoogleHeaders();
		headers.setApplicationName("HockeyMoms/1.0");
		headers.gdataVersion = "2";
		result.defaultHeaders = headers;
		GoogleUtils.useMethodOverride(result);
		AtomParser parser = new AtomParser();
		parser.namespaceDictionary = new XmlNamespaceDictionary().set("",
				"http://www.w3.org/2005/Atom");
		result.addParser(parser);
		return result;
	}

	public String getCalendarName() {
		try {
			HttpResponse response = getCalendar();
			return CharStreams.toString(new InputStreamReader(response.getContent()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
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

	private HttpResponse getCalendar() throws IOException {
		HttpRequest request = getRequest();
		return request.execute();
	}
}

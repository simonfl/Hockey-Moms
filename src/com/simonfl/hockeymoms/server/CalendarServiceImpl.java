package com.simonfl.hockeymoms.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.simonfl.hockeymoms.client.CalendarService;
import com.simonfl.hockeymoms.shared.Calendar;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CalendarServiceImpl extends RemoteServiceServlet implements
		CalendarService {

	private static final String HOCKEY_MOMS_CALENDAR_URL = "https://www.google.com/calendar/feeds/1vdmm618kmvc51u9bppq71eok8%40group.calendar.google.com/public/basic";
	private static final CalendarUtil calendarUtil = new CalendarUtil(
			HOCKEY_MOMS_CALENDAR_URL);

	public String getCalendarName(String input) throws IllegalArgumentException {
		return calendarUtil.getCalendar().getCalendarTitle();
	}

	public Calendar getCalendar() {
		return calendarUtil.getCalendar();
	}
}

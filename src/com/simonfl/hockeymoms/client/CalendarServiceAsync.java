package com.simonfl.hockeymoms.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.simonfl.hockeymoms.shared.Calendar;

/**
 * The async counterpart of <code>CalendarService</code>.
 */
public interface CalendarServiceAsync {
	void getCalendarName(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void getCalendar(AsyncCallback<Calendar> callback);
}

package com.simonfl.hockeymoms.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>CalendarService</code>.
 */
public interface CalendarServiceAsync {
	void getCalendarName(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}

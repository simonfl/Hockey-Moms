package com.simonfl.hockeymoms.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.simonfl.hockeymoms.shared.Calendar;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("calendar")
public interface CalendarService extends RemoteService {
	String getCalendarName(String name) throws IllegalArgumentException;

	Calendar getCalendar();
}

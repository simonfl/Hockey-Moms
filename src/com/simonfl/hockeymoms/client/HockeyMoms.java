package com.simonfl.hockeymoms.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HockeyMoms implements EntryPoint {
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final CalendarServiceAsync greetingService = GWT
			.create(CalendarService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		CalendarPresenter calendarPresenter = new CalendarPresenter(greetingService);
		CalendarView calendarView = new CalendarView(calendarPresenter);
		RootPanel.get("calendar").add(calendarView);
	}
}

package com.simonfl.hockeymoms.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.simonfl.hockeymoms.shared.Calendar;

public class CalendarPresenter implements CalendarView.Presenter {

	CalendarServiceAsync service;
	
	CalendarPresenter(CalendarServiceAsync service) {
		this.service = service;
	}
	
	public void getCalendar(AsyncCallback<Calendar> callback) {
		service.getCalendar(callback);
	}
}

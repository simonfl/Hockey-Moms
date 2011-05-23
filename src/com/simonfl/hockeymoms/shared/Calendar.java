package com.simonfl.hockeymoms.shared;

import java.io.Serializable;

/**
 * Calendar class used for GWT RPC.
 * 
 * @author simonfl@gmail.com
 */
@SuppressWarnings("serial")
public class Calendar implements Serializable {

	private String calendarTitle;

	Calendar() {};

	public Calendar(String calendarTitle) {
		this.calendarTitle = calendarTitle;
	}
	
	public String getCalendarTitle() {
		return calendarTitle;
	}
	
}

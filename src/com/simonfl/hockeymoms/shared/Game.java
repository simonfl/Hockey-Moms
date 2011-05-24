package com.simonfl.hockeymoms.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Game class used for GWT RPC.
 * 
 * @author simonfl@gmail.com
 */
@SuppressWarnings("serial")
public class Game implements Serializable{

	String title;
	Date startTime;
	
	// For GWT
	Game() {
	}
	
	public Game(String title, Date startTime) {
		this.title = title;
		this.startTime = startTime;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Date getStartTime() {
		return startTime;
	}
}

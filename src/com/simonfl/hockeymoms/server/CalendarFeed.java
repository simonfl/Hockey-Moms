package com.simonfl.hockeymoms.server;

import java.util.List;

import com.google.api.client.util.Key;

/**
 * 
 * @author simonfl@gmail.com
 */
public class CalendarFeed {

	@Key
	public String title;
	
	@Key("entry")
	public List<GameFeed> gameFeeds;
}

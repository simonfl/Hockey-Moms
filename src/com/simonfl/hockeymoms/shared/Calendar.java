package com.simonfl.hockeymoms.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Calendar class used for GWT RPC.
 * 
 * @author simonfl@gmail.com
 */
@SuppressWarnings("serial")
public class Calendar implements Serializable {

	private String calendarTitle;
	
	/**
	 * List of games, guaranteed to be in reverse chronological order
	 */
	private Game[] games;

	// For GWT
	Calendar() {};

	public Calendar(String calendarTitle, List<Game> games) {
		this.calendarTitle = calendarTitle;
		List<Game> orderedGames = new ArrayList<Game>(games);
		Collections.sort(orderedGames, new Comparator<Game>() {
			public int compare(Game arg0, Game arg1) {
				return arg1.getStartTime().compareTo(arg0.getStartTime());
			}
		});
		this.games = orderedGames.toArray(new Game[]{});
	}
	
	public String getCalendarTitle() {
		return calendarTitle;
	}
	
	public List<Game> getGames() {
		List<Game> gameList = new ArrayList<Game>();
		Collections.addAll(gameList, games);
		return gameList;
	}
}

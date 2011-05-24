package com.simonfl.hockeymoms.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.simonfl.hockeymoms.shared.Calendar;
import com.simonfl.hockeymoms.shared.Game;

public class CalendarView extends Composite {

	private static CalendarViewUiBinder uiBinder = GWT
			.create(CalendarViewUiBinder.class);

	interface CalendarViewUiBinder extends UiBinder<Widget, CalendarView> {
	}
	
	interface Presenter {
		void getCalendar(AsyncCallback<Calendar> callback);
	}

	@UiField
	Label title;
	@UiField
	VerticalPanel games;
	
	Presenter presenter;

	public CalendarView(Presenter presenter) {
		initWidget(uiBinder.createAndBindUi(this));
		this.presenter = presenter;
		populateCalendar();
	}
	
	private void populateCalendar() {
		AsyncCallback<Calendar> callback = new AsyncCallback<Calendar>() {

			public void onFailure(Throwable caught) {
				// do nothing
			}

			public void onSuccess(Calendar result) {
				setCalendar(result);
			}
		
		};
		presenter.getCalendar(callback);
	}
	
	private void setCalendar(Calendar calendar) {
		title.setText(calendar.getCalendarTitle());
		games.clear();
		for (Game game : calendar.getGames()) {
			Label gameLbl = new Label(game.getTitle() + " : " + game.getStartTime().toString());
			games.add(gameLbl);
		}
		
	}
}

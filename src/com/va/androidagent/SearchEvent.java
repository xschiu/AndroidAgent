package com.va.androidagent;

import java.util.GregorianCalendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SearchEvent extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_event);
		Long time = new GregorianCalendar().getTimeInMillis()+5000;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_event, menu);
		return true;
	}

}

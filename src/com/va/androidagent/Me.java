package com.va.androidagent;

import java.text.Format;

import android.os.Bundle;
import android.provider.CalendarContract;
import android.app.Activity;
import android.database.Cursor;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Me extends Activity implements OnClickListener{
	
	private Cursor mCursor = null;
	private static final String[] COLS = new String[]
	{ CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART};

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me);
		
		mCursor = getContentResolver().query(


				CalendarContract.Events.CONTENT_URI, COLS, null, null, null);
				mCursor.moveToFirst();


				Button b = (Button)findViewById(R.id.next);


				b.setOnClickListener(this);
				b = (Button)findViewById(R.id.previous);


				b.setOnClickListener(this);
				onClick(findViewById(R.id.previous));
	}


	@Override
	public void onClick(View v) {
	TextView tv = (TextView)findViewById(R.id.data);


	String title = "N/A";


	Long start = 0L;


	switch(v.getId()) {
	case R.id.next:
	if(!mCursor.isLast()) mCursor.moveToNext();
	break;
	case R.id.previous:
	if(!mCursor.isFirst()) mCursor.moveToPrevious();
	break;
	}


	Format df = DateFormat.getDateFormat(this);
	Format tf = DateFormat.getTimeFormat(this);
	try {
	title = mCursor.getString(0);


	start = mCursor.getLong(1);


	} catch (Exception e) {
	//ignore


	}


	tv.setText(title+" on "+df.format(start)+" at "+tf.format(start));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification, menu);
		return true;
	}

}


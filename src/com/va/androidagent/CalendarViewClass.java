package com.va.androidagent;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;


import java.util.GregorianCalendar;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.widget.Toast;

public class CalendarViewClass extends Activity {

	// Parameters for quering the calendar
	// Projection array. Creating indices for this array instead of doing
	// dynamic lookups improves performance.
	public static final String[] EVENT_PROJECTION = new String[] {
			Calendars._ID, // 0
			Calendars.ACCOUNT_NAME, // 1
			Calendars.CALENDAR_DISPLAY_NAME // 2
	};
		
	// The indices for the projection array above.
	private static final int PROJECTION_ID_INDEX = 0;
	private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
	private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        
        
        Intent intent = new Intent(Intent.ACTION_INSERT);
		intent.setType("vnd.android.cursor.item/event");
		intent.putExtra(Events.TITLE, "Learn Android");
		intent.putExtra(Events.EVENT_LOCATION, "Home suit home");
		intent.putExtra(Events.DESCRIPTION, "Download Examples");

		// Setting dates
		int month;
		int day;
		int year;
		GregorianCalendar gregorianCalendar=new GregorianCalendar(); 
		month=gregorianCalendar.get(GregorianCalendar.MONTH);            
		day= gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
		year=gregorianCalendar.get(GregorianCalendar.YEAR);
		
		GregorianCalendar calDate = new GregorianCalendar(year, month, day);
		intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
				calDate.getTimeInMillis());
		intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
				calDate.getTimeInMillis());

		// Make it a full day event
		intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

		// Make it a recurring Event
		intent.putExtra(Events.RRULE,
				"FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");

		// Making it private and shown as busy
		intent.putExtra(Events.ACCESS_LEVEL, Events.ACCESS_PRIVATE);
		intent.putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY);

		startActivity(intent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.calendar_view, menu);
//        return true;
//    }
    
    public void onClick(View view) {
		// Intent calIntent = new Intent(Intent.ACTION_INSERT);
		// calIntent.setData(CalendarContract.Events.CONTENT_URI);
		// startActivity(calIntent);

		

	}
    
    public void scheduleAlarm(View V)
    {
            // time at which alarm will be scheduled here alarm is scheduled at 1 day from current time, 
            // we fetch  the current time in milliseconds and added 1 day time
            // i.e. 24*60*60*1000= 86,400,000   milliseconds in a day        
            Long time = new GregorianCalendar().getTimeInMillis()+5000;

            // create an Intent and set the class which will execute when Alarm triggers, here we have
            // given AlarmReciever in the Intent, the onRecieve() method of this class will execute when
            // alarm triggers and 
            //we will write the code to send SMS inside onRecieve() method pf Alarmreciever class
           
            Intent intentAlarm = new Intent(this, AlarmReceiver.class);
            
       
            // create the object
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            //set the alarm for particular time
            alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
            Toast.makeText(this, "Alarm Scheduled for Tommrrow", Toast.LENGTH_LONG).show();
         
    }
    
    public void queryCalendar(View view) {
		// Run query
		Cursor cur = null;
		ContentResolver cr = getContentResolver();
		Uri uri = Calendars.CONTENT_URI;
		String selection = "((" + Calendars.ACCOUNT_NAME + " = ?) AND ("
				+ Calendars.ACCOUNT_TYPE + " = ?))";

		// Replace this with your own user and account type
		String[] selectionArgs = new String[] { "Lars.Vogel@gmail.com",
				"com.google" };
		// Submit the query and get a Cursor object back.
		cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);
		Toast.makeText(this, String.valueOf(cur.getCount()), Toast.LENGTH_LONG)
				.show();
		// Use the cursor to step through the returned records
		while (cur.moveToNext()) {
			long calID = 0;
			String displayName = null;
			String accountName = null;

			// Get the field values
			calID = cur.getLong(PROJECTION_ID_INDEX);
			displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
			accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);

			// Do something with the values...
			Toast.makeText(this, "Calendar " + displayName, Toast.LENGTH_SHORT)
					.show();
		}
    }
    
}

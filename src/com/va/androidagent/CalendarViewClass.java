package com.va.androidagent;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;


import java.text.Format;
import java.util.GregorianCalendar;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.widget.Button;
import android.widget.Toast;

public class CalendarViewClass extends Activity {
	private Cursor mCursor = null; private static final String[] COLS = new String[]
			{ CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        long startMillis = 0; 
        
        CalendarService.readCalendar(CalendarViewClass.this);
        
//        mCursor = getContentResolver().query(
//
//		CalendarContract.Events.CONTENT_URI, COLS, null, null, null);
//		mCursor.moveToFirst();
//
//		String title = "N/A";
//
//		Long start = 0L;
//		
//		Format df = DateFormat.getDateFormat(this); 
//		Format tf = DateFormat.getTimeFormat(this); 
//		
//		try {
//			title = mCursor.getString(0);
//
//			start = mCursor.getLong(1);
//
//			} catch (Exception e) {
//			//ignore
//
//			}

//		System.out.println("title: " + title + " date: " + df.format(start) + " time: " + tf.format(start) );
        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
        builder.appendPath("time");
        ContentUris.appendId(builder, startMillis);
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());

        startActivity(intent);
//
//		// Setting dates
//		int month;
//		int day;
//		int year;
//		GregorianCalendar gregorianCalendar=new GregorianCalendar(); 
//		month=gregorianCalendar.get(GregorianCalendar.MONTH);            
//		day= gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
//		year=gregorianCalendar.get(GregorianCalendar.YEAR);
//		
//		GregorianCalendar calDate = new GregorianCalendar(year, month, day);
//		intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
//				calDate.getTimeInMillis());
//		intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
//				calDate.getTimeInMillis());
//
//		// Make it a full day event
//		intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
//
//		// Make it a recurring Event
//		intent.putExtra(Events.RRULE,
//				"FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");
//
//		// Making it private and shown as busy
//		intent.putExtra(Events.ACCESS_LEVEL, Events.ACCESS_PRIVATE);
//		intent.putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY);
//
//		startActivity(intent);
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
            Long time = new GregorianCalendar().getTimeInMillis()-30000;
           

            // create an Intent and set the class which will execute when Alarm triggers, here we have
            // given AlarmReciever in the Intent, the onRecieve() method of this class will execute when
            // alarm triggers and 
            //we will write the code to send SMS inside onRecieve() method pf Alarmreciever class
           
            Intent intentAlarm = new Intent(this, AlarmReceiver.class);
//            Intent priority2 = new Intent(this,Priority2Alarm.class);
//            Intent priority3 = new Intent(this,Priority3Alarm.class);
//            Intent normal = new Intent(this,Normal.class);
//            
//            Intent med1 = new Intent(this, Med1.class);
//            Intent med2 = new Intent(this,Med2.class);
//            Intent med3 = new Intent(this,Med3.class);
       
            // create the object
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
          
        	alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
//            alarmManager.set(AlarmManager.RTC_WAKEUP,time+5000, PendingIntent.getBroadcast(this,2, priority2, PendingIntent.FLAG_UPDATE_CURRENT));
//            alarmManager.set(AlarmManager.RTC_WAKEUP,time+12000, PendingIntent.getBroadcast(this,3, priority3, PendingIntent.FLAG_UPDATE_CURRENT));
//            alarmManager.set(AlarmManager.RTC_WAKEUP,time+18000, PendingIntent.getBroadcast(this,3, normal, PendingIntent.FLAG_UPDATE_CURRENT));
//            
//            alarmManager.set(AlarmManager.RTC_WAKEUP,time+24000, PendingIntent.getBroadcast(this,1,  med1, PendingIntent.FLAG_UPDATE_CURRENT));
//            alarmManager.set(AlarmManager.RTC_WAKEUP,time+30000, PendingIntent.getBroadcast(this,2, med2, PendingIntent.FLAG_UPDATE_CURRENT));
//            alarmManager.set(AlarmManager.RTC_WAKEUP,time+36000, PendingIntent.getBroadcast(this,3, med3, PendingIntent.FLAG_UPDATE_CURRENT));
            
            
            
            //set the alarm for particular time
          
            Toast.makeText(this, "Alarm Scheduled", Toast.LENGTH_LONG).show();    
    }
}

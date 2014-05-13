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
import android.widget.ImageButton;
import android.widget.Toast;

public class CalendarViewClass extends Activity {
	private Cursor mCursor = null; private static final String[] COLS = new String[]
			{ CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        long startMillis = 0; 
        
        
       
        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
        builder.appendPath("time");
        ContentUris.appendId(builder, startMillis);
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());

        startActivity(intent);
    }

    
    public void onClick(View view) {

	}
    
    public void gotoHome(View v)
	{
    	
//    	CalendarService.readCalendar(CalendarViewClass.this);//read calendar event from here
//		Intent intent = new Intent(getApplicationContext(), Home.class);	
//		startActivity(intent); 

	}
    
    public void scheduleAlarm(View V)
    {
                
            Long time = new GregorianCalendar().getTimeInMillis()-30000;
            Intent intentAlarm = new Intent(this, AlarmReceiver.class);
       
            // create the object
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);       
        	alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
 
            //set the alarm for particular time
            Toast.makeText(this, "Alarm Scheduled", Toast.LENGTH_LONG).show();    
    }
}

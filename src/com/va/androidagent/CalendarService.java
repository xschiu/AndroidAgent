package com.va.androidagent;

import java.text.Format;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.widget.Toast;


public class CalendarService {
	

	// Default constructor
	public static void readCalendar(Context context) {
		readCalendar(context, 1, 0);
	}

	// Use to specify specific the time span
	public static void readCalendar(Context context, int days, int hours) {

		ContentResolver contentResolver = context.getContentResolver();
//		 Use the cursor below for Android API 4.0+ (Thanks to SLEEPLisNight)
		
		 Cursor cursor = contentResolver.query(Uri.parse("content://com.android.calendar/events"), 
		 new String[]{ "calendar_id", "title", "description", "dtstart", "dtend", "eventLocation" }, 
		 null, null, null);


		// Create a set containing all of the calendar IDs available on the phone
		HashSet<String> calendarIds = getCalenderIds(cursor);

		// Create a hash map of calendar ids and the events of each id
		HashMap<String, List<CalendarEvent>> eventMap = new HashMap<String, List<CalendarEvent>>();

		// Loop over all of the calendars
		for (String id : calendarIds) {

			// Create a builder to define the time span
			Uri.Builder builder = Uri.parse("content://com.android.calendar/instances/when").buildUpon();
	        long now = new Date().getTime();

	        // create the time span based on the inputs
	        ContentUris.appendId(builder, now );
	        ContentUris.appendId(builder, now + (DateUtils.DAY_IN_MILLIS * days) + (DateUtils.HOUR_IN_MILLIS * hours));

	        // Create an event cursor to find all events in the calendar
	        Cursor eventCursor = contentResolver.query(builder.build(),
	                new String[]  { "title", "begin", "end", "allDay"}, 
	                " calendar_id= " + id,
	                null, "startDay ASC, startMinute ASC");

	        System.out.println("eventCursor count="+eventCursor.getCount());
	        long start = 0L;
	        String title = "N/A";
	        
	        // If there are actual events in the current calendar, the count will exceed zero
	        if(eventCursor.getCount()>0)
	        {

	        	// Create a list of calendar events for the specific calendar
	        	List<CalendarEvent> eventList = new ArrayList<CalendarEvent>();

	        	// Move to the first object
	            eventCursor.moveToFirst();

	            Format tf = DateFormat.getTimeFormat(context);
	            try {
	    			title = eventCursor.getString(0);
	    
	    			start = eventCursor.getLong(1);
	    
	    			} catch (Exception e) {
	    			//ignore
	    
	    		}
	           

	            // Create an object of CalendarEvent which contains the title, when the event begins and ends, 
	            // and if it is a full day event or not 
	            CalendarEvent ce = loadEvent(eventCursor);

	            // Adds the first object to the list of events
	            eventList.add(ce);

	            System.out.println("ce" + ce.toString());
	            
	            // While there are more events in the current calendar, move to the next instance
	            while (eventCursor.moveToNext())
	            {

	            	// Adds the object to the list of events
	            	ce = loadEvent(eventCursor);
	            	eventList.add(ce);  

	            	System.out.println(ce.toString());
	            	

	            }
	            Calendar cal = GregorianCalendar.getInstance();
	            cal.setTimeInMillis(start);
	            cal.add(Calendar.SECOND, -30);
	            
	            
	            Long time = cal.getTimeInMillis();      
	            
	            Intent intentAlarm = new Intent(context, Med1.class);
	            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
	          
	        	alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(context,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
    
	            //To check the event exported from the calendar is correct
	            System.out.println("First item is " + eventList.subList(0, 1).toString());
	            System.out.println("title=" + title + "   start= "+ start + "  time " + time);
	    	    Collections.sort(eventList);
	    	    eventMap.put(id, eventList);
	    	    System.out.println(eventMap.keySet().size() + " " + eventMap.values());

	        }
		}
	}

	// Returns a new instance of the calendar object
	private static CalendarEvent loadEvent(Cursor csr) {
		return new CalendarEvent(csr.getString(0), 
						new Date(csr.getLong(1)),
						new Date(csr.getLong(2)), 
						!csr.getString(3).equals("0"));
		
		
	}

	
	// Creates the list of calendar ids and returns it in a set
	private static HashSet<String> getCalenderIds(Cursor cursor) {
		
		HashSet<String> calendarIds = new HashSet<String>();

		try
	    {

			// If there are more than 0 calendars, continue
	        if(cursor.getCount() > 0)
	        {

	        	// Loop to set the id for all of the calendars
		        while (cursor.moveToNext()) {

		             String _id = cursor.getString(0);
		             String displayName = cursor.getString(1);
//		             start = cursor.getLong(2);
		             Boolean selected = !cursor.getString(3).equals("0");
		             

		            System.out.println("Id: " + _id + " Display Name: " + displayName + " Selected: " + selected);
		            
		            calendarIds.add(_id);
		           
		            

		        	}
	        } 
	    }

	    catch(AssertionError ex)
	    {
	        ex.printStackTrace();
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }

		return calendarIds;

	}
}
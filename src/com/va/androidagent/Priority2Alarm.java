package com.va.androidagent;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//events level 2
public class Priority2Alarm extends BroadcastReceiver{

	public final static String MESSAGE = "com.va.androidagent.MESSAGE";

	
	@Override
    public void onReceive(Context context, Intent intent)
    {
            // TODO Auto-generated method stub
        
            
              // here you can start an activity or service depending on your need
             // for ex you can start an activity to vibrate phone or to ring the phone  
		
		try {
		    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		    Ringtone r = RingtoneManager.getRingtone(context, notification);
		    r.play();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		 
		String reminderMessage = "The event at Toa Payoh Community Centre is Happening now";	
		Intent i = new Intent(context,Home.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("message", reminderMessage);
		i.putExtra("level","2");
		context.startActivity(i);

    }
	
	
	

}


	
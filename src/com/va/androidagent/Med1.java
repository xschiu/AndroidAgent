package com.va.androidagent;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//medication event level 1
public class Med1 extends BroadcastReceiver{

	public final static String MESSAGE = "com.va.androidagent.MESSAGE";

	
	@Override
    public void onReceive(Context context, Intent intent)
    {
            // TODO Auto-generated method stub
        
            
              // here you can start an activity or service depending on your need
             // for ex you can start an activity to vibrate phone or to ring the phone  
		 
	
		 
		String reminderMessage = "Mister, time to take your medicine";	
		Intent i = new Intent(context,Home.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("message", reminderMessage);
		i.putExtra("level", "5");
		context.startActivity(i);

        Toast.makeText(context, "Low Priority", Toast.LENGTH_LONG).show();
    
        
        

    
    }
	
	
	

}


	
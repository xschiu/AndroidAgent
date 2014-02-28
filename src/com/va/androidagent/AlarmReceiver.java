package com.va.androidagent;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver{

	public final static String MESSAGE = "com.va.androidagent.MESSAGE";
	

	
	@Override
    public void onReceive(Context context, Intent intent)
    {
            // TODO Auto-generated method stub
        
            
              // here you can start an activity or service depending on your need
             // for ex you can start an activity to vibrate phone or to ring the phone  
		String reminderMessage = "There is an event held at Serangoon CC, do you want to attend?";
		
		Intent i = new Intent(context,Home.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("message", reminderMessage);
		context.startActivity(i);
	
//        String phoneNumberReciver="+6582015534";// phone number to which SMS to be send
//        String message="Your mother is very excited at 7pm earlier on, you may want to check her out";// message to send
//		
//        SmsManager sms = SmsManager.getDefault(); 
//        sms.sendTextMessage(phoneNumberReciver, null, message, null, null);
        // Show the toast  like in above screen shot
        Toast.makeText(context, "Alarm Triggered and SMS Sent", Toast.LENGTH_LONG).show();
        
        
        
            
     }
	
	
	

}


	
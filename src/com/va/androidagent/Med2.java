package com.va.androidagent;

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

public class Med2 extends BroadcastReceiver{

	public final static String MESSAGE = "com.va.androidagent.MESSAGE";

	
	@Override
    public void onReceive(Context context, Intent intent)
    {
            // TODO Auto-generated method stub
        
            
              // here you can start an activity or service depending on your need
             // for ex you can start an activity to vibrate phone or to ring the phone  
		
		Long time = new GregorianCalendar().getTimeInMillis()+5000;
		 
		String reminderMessage = "Please take your medicine now.";	
		Intent i = new Intent(context,Home.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("message", reminderMessage);
		i.putExtra("level", "6");
		context.startActivity(i);

//	
//        String phoneNumberReciver="+6582015534";// phone number to which SMS to be send
//        String message="Your mother is very excited at 7pm earlier on, you may want to check her out";// message to send
//		
//        SmsManager sms = SmsManager.getDefault(); 
//        sms.sendTextMessage(phoneNumberReciver, null, message, null, null);
        // Show the toast  like in above screen shot
        Toast.makeText(context, "Medium Priority", Toast.LENGTH_LONG).show();
    
        
        

    
    }
	
	
	

}


	
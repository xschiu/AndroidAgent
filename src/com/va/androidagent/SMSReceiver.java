package com.va.androidagent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


//sms to trigger the event and send information
public class SMSReceiver extends BroadcastReceiver {
	   private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
       private static final String TAG = "SMSBroadcastReceiver";
       private static String receivedTxt = "";

       @Override
       public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "Intent recieved: " + intent.getAction());

               if (intent.getAction().equals(SMS_RECEIVED)) {
                   Bundle bundle = intent.getExtras();
                   if (bundle != null) {
                       Object[] pdus = (Object[])bundle.get("pdus");
                       final SmsMessage[] messages = new SmsMessage[pdus.length];
                       for (int i = 0; i < pdus.length; i++) {
                           messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                       }
                       if (messages.length > -1) {
                    	   receivedTxt = messages[0].getMessageBody();
                    	   if (receivedTxt.substring(0, 12).equals("Notification")){ //keyword is "notification", any text message with notification as starting will be recorded
	
                    			Intent i = new Intent(context,Home.class);
                    			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    			i.putExtra("message", receivedTxt);
                    			i.putExtra("level", "1");
                    			context.startActivity(i);
                    		   Log.i(TAG, "Message recieved: " + receivedTxt);
                    	   }
                       }
                    	   
                   }
               }
          }
	}

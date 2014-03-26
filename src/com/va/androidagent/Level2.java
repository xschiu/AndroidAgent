package com.va.androidagent;

import java.util.GregorianCalendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;

public class Level2 extends BroadcastReceiver {

	 public void onReceive(Context context, Intent intent)
	    {
			String reminderMessage = "Level2!!";	
			Intent i = new Intent(context,Home.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.putExtra("message", reminderMessage);
			context.startActivity(i);
		 
	    }
	
}

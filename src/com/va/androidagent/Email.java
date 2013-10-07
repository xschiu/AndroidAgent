package com.va.androidagent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class Email extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email);
		
		Intent emailIntent=new Intent(Intent.ACTION_SEND);
		String subject = "Hi!";
		String body = "hello from android....";
		String[] recipients = new String[]{"xschiu@gmail.com"}; emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject); emailIntent.putExtra(Intent.EXTRA_TEXT, body); emailIntent.setType("message/rfc822");
		startActivity(emailIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email, menu);
		return true;
	}

}

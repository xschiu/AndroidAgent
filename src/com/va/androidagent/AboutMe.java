package com.va.androidagent;

import android.net.Uri;  
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class AboutMe extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_me);
		this.contactsButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about_me, menu);
		return true;
	}
  
	private void contactsButton()
	{
		ImageButton contacts = (ImageButton)this.findViewById(R.id.contactsButton); 
		contacts.setOnClickListener(new ImageButton.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
			    Intent intent= new Intent(Intent.ACTION_PICK,  Contacts.CONTENT_URI);
		        startActivityForResult(intent, 1);

			}
		}); 
	}
}

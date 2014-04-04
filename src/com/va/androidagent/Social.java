package com.va.androidagent;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class Social extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.social, menu);
		return true;
	}
	
	  public void facebook(View view){
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"));
		startActivity(browserIntent);
	  }
	  
	  public void twitter(View view){
		  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.twitter.com"));
		  startActivity(browserIntent);
	  }
	  
	  public void insta(View view){
		  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.instagram.com"));
		  startActivity(browserIntent);
	  }
	  
	  public void linkedin(View view){
		  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com"));
		  startActivity(browserIntent);
	  }
	   
	    	

}

package com.va.androidagent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity{
	public final static String NAME = "com.va.androidagent.MESSAGE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		this.finishButton();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu); 
//		
//		Intent intent = new Intent(this, FrameAnimationActivity.class);
//		
//		startActivity(intent);
		return super.onCreateOptionsMenu(menu);
	}
	
	//Action Bar working code
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//	    // Handle presses on the action bar items
//	    switch (item.getItemId()) {
//	        case R.id.action_search:
//	            openSearch();
//	            return true;
//	        case R.id.action_settings:
//	            openSettings();
//	            return true;
//	        default:
//	            return super.onOptionsItemSelected(item);
//	    }
//	}
	
	private void openSettings() {
		// TODO Auto-generated method stub
		
	}


	private void openSearch() {
		// TODO Auto-generated method stub
		
	}


	private void finishButton()
	{
		Button b = (Button)this.findViewById(R.id.finishBtn); 
		b.setOnClickListener(new Button.OnClickListener()
		{ 
			public void onClick(View v) 
			{
				Intent intent = new Intent(getApplicationContext(), FrameAnimationActivity.class);	
				EditText editText = (EditText) findViewById(R.id.edit_name);
			    String name = editText.getText().toString();
			    intent.putExtra(NAME, name);
				startActivity(intent); 
			}
		}); 
	}

	
	
	
}

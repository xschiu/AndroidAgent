package com.va.androidagent;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;



public class Play1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play1);
		this.goPlay2();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play1, menu);
		return true;
	}
	
	private void goPlay2()
	{
		Button goPlay2 = (Button)this.findViewById(R.id.physicalActivitiesBtn); 
		goPlay2.setOnClickListener(new Button.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(getApplicationContext(), Play2.class);	
				startActivity(intent); 
			}
		}); 
	}
	public void minigame(View view){
		  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mindgames.com"));
		  startActivity(browserIntent);
	  }

}

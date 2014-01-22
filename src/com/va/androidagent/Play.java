package com.va.androidagent;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;
import android.net.Uri;



public class Play extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		
		VideoView videoView = (VideoView)this.findViewById(R.id.videoView1);
		MediaController mc = new MediaController(this); videoView.setMediaController(mc); 
		//videoView.setVideoURI(Uri.parse(
		//"http://www.androidbook.com/akc/filestorage/android/" + "documentfiles/3389/movie.mp4"));
		
		videoView.setVideoPath( Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_MOVIES) +
				"/Taiji.avi");
		videoView.requestFocus();
		videoView.start();
		
		
//		this.recordButton();
//		this.playButton();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tutorial, menu);
		
		return true;
	}
	
//	private void recordButton()
//	{
//		ImageButton record = (ImageButton)this.findViewById(R.id.recordBtn); 
//		record.setOnClickListener(new ImageButton.OnClickListener()
//		{ 
//			@Override
//			public void onClick(View v) 
//			{
//				Intent intent = new Intent(getApplicationContext(), Butler.class);	
//				startActivity(intent); 
//			}
//		}); 
//	}
//	
//	private void playButton()
//	{
//		ImageButton play = (ImageButton)this.findViewById(R.id.playRecordBtn); 
//		play.setOnClickListener(new ImageButton.OnClickListener()
//		{ 
//			@Override
//			public void onClick(View v) 
//			{
//				
//				VideoView videoView = (VideoView)this.findViewById(R.id.videoView1);
//				MediaController mc = new MediaController(this); videoView.setMediaController(mc); 
//				//videoView.setVideoURI(Uri.parse(
//				//"http://www.androidbook.com/akc/filestorage/android/" + "documentfiles/3389/movie.mp4"));
//				
//				videoView.setVideoPath( Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_MOVIES) +
//						"/movie.avi");
//				videoView.requestFocus();
//				videoView.start();
//				
//			}
//		}); 
//	}

}

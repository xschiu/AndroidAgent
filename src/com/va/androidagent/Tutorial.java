package com.va.androidagent;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;
import android.net.Uri;

public class Tutorial extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tutorial);
		
		VideoView videoView = (VideoView)this.findViewById(R.id.videoView1);
		MediaController mc = new MediaController(this); videoView.setMediaController(mc); 
		//videoView.setVideoURI(Uri.parse(
		//"http://www.androidbook.com/akc/filestorage/android/" + "documentfiles/3389/movie.mp4"));
		
		videoView.setVideoPath( Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_MOVIES) +
				"/movie.avi");
		videoView.requestFocus();
		videoView.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tutorial, menu);
		return true;
	}

}

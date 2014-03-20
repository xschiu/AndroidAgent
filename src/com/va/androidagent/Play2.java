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
import android.app.Activity;
import android.view.Menu;

import android.util.SparseArray;
import android.widget.ExpandableListView;

public class Play2 extends Activity {

	 SparseArray<Group> groups = new SparseArray<Group>();
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play2);
		
		createData();
	    ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
	    MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
	        groups);
	    listView.setAdapter(adapter);
		
//		VideoView videoView = (VideoView)this.findViewById(R.id.videoView1);
//		MediaController mc = new MediaController(this); videoView.setMediaController(mc); 
//		videoView.setVideoURI(Uri.parse(
//		"http://www.androidbook.com/akc/filestorage/android/" + "documentfiles/3389/movie.mp4"));

//		videoView.setVideoPath( Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_MOVIES) +
//				"/spin");
//		videoView.requestFocus();
//		videoView.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play2, menu);
		return true;
	}
	
	public void createData() {
		Group group;
	    for (int j = 0; j < 5; j++) {
	    	switch(j){
	    	case 0:
	    	{
	    		group = new Group("Dance");
	    		for (int i = 0; i < 5; i++) {
			        group.children.add("Sub Item" + i);
			      }
			    groups.append(j, group);
	    		break;
	    	}
	    	
	    	case 1:
	    	{
	    		group = new Group("Sport");
	    		for (int i = 0; i < 5; i++) {
			        group.children.add("Sub Item" + i);
			      }
			    groups.append(j, group);
	    		break;
	    	}
	    	
	    	case 2:
	    	{
	    		group = new Group("Yoga");
	    		for (int i = 0; i < 5; i++) {
			        group.children.add("Sub Item" + i);
			      }
			    groups.append(j, group);
	    		break;
	    	}
	    	
	    	case 3:
	    	{
	    		group = new Group("Stretch");
	    		for (int i = 0; i < 5; i++) {
			        group.children.add("Sub Item" + i);
			      }
			    groups.append(j, group);
	    		break;
	    	}
	    	default:
	    	{
		    	group = new Group("Fun");
			      for (int i = 0; i < 5; i++) {
			        group.children.add("Sub Item" + i);
			      }
			      groups.append(j, group);
			      break;
		    	}
	    	}	
	      
	    }
	  }

}

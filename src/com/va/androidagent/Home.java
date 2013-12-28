package com.va.androidagent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		this.chatButton();
		this.sendEmail();
		this.tutorialButton();
		this.calendarButton();
		//this.listView();
		
		 initList();
	     // We get the ListView component from the layout
	    // ListView lv = (ListView) findViewById(R.id.listView1);
	     
	     // This is a simple adapter that accepts as parameter
	     // Context
	     // Data list
	     // The row layout that is used during the row creation
	     // The keys used to retrieve the data
	     // The View id used to show the data. The key number and the view id must match
	    // simpleAdpt = new SimpleAdapter(this, planetsList, android.R.layout.simple_list_item_1, new String[] {"planet"}, new int[] {android.R.id.text1});
	  //   SimpleAdapter simpleAdpt = new SimpleAdapter(this, planetsList, android.R.layout.simple_list_item_1, new String[] {"planet"}, new int[] {android.R.id.text1});
	   //  lv.setAdapter(simpleAdpt);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
		
	}

/*	private void listView()
	{
		 public void onItemClick(ListView l, View v, int position, long id) {
		        // Do something when a list item is clicked
		    }
		
		
	}*/
	
	private void chatButton()
	{
		ImageButton chat = (ImageButton)this.findViewById(R.id.chatBtn); 
		chat.setOnClickListener(new ImageButton.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(getApplicationContext(), Butler.class);	
				startActivity(intent); 
			}
		}); 
	}
	
	private void calendarButton()
	{
		ImageButton calendar = (ImageButton)this.findViewById(R.id.calendarBtn); 
		calendar.setOnClickListener(new ImageButton.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(getApplicationContext(), CalendarView.class);	
				startActivity(intent); 
			}
		}); 
	}
	private void sendEmail()
	{
		ImageButton email = (ImageButton)this.findViewById(R.id.emailBtn); 
		email.setOnClickListener(new ImageButton.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(getApplicationContext(), Email.class);	
				startActivity(intent); 
			}
		}); 
	}
	
	private void tutorialButton()
	{
		ImageButton tutorial = (ImageButton)this.findViewById(R.id.tutorialBtn); 
		tutorial.setOnClickListener(new ImageButton.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(getApplicationContext(), Tutorial.class);	
				startActivity(intent); 
			}
		}); 
	}
	
	
	
	// The data to show
	List<Map<String, String>> planetsList = new ArrayList<Map<String,String>>();
	   private void initList() {
	    // We populate the planets

	    planetsList.add(createPlanet("planet", "Chat with Me"));
	    planetsList.add(createPlanet("planet", "Going Out"));
	    planetsList.add(createPlanet("planet", "Move It"));
	    planetsList.add(createPlanet("planet", "Medicine"));
	    planetsList.add(createPlanet("planet", "Function 5"));
	    planetsList.add(createPlanet("planet", "Function 6"));
	    planetsList.add(createPlanet("planet", "Function 7"));
	}
	private HashMap<String, String> createPlanet(String key, String name) {
	    HashMap<String, String> planet = new HashMap<String, String>();
	    planet.put(key, name);
	    return planet;
	}
	
	
	
	

}

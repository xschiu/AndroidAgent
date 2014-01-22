package com.va.androidagent;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {
	String[] strings = {"Arousal","Excitement","Pleasure","Contentment","Sleepiness","Depression","Misery","Distress","Neutral"};
	int arr_images[] = { R.drawable.arousal, R.drawable.excitement,R.drawable.pleasure, R.drawable.contentment, R.drawable.sleepy, R.drawable.depression, R.drawable.misery, R.drawable.distress, R.drawable.neutral};
	String[] weather = {"Rainning","Sunny","Thunder","Cloudy"};
	int arr_weather[] = {R.drawable.raining, R.drawable.sunny, R.drawable.thunder, R.drawable.cloudy};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		//mood spinner
		Spinner mySpinner = (Spinner)findViewById(R.id.emotionSpinner);
        mySpinner.setAdapter(new MyAdapter(Home.this, R.layout.row, strings));
		
        //weather spinner
//        Spinner weatherSpinner = (Spinner)findViewById(R.id.weatherSpinner);
//        weatherSpinner.setAdapter(new WeatherAdapter(Home.this, R.layout.weather, strings));
        
        MyFragment frag = new MyFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction= manager.beginTransaction();
        transaction.add(R.id.my_layout,frag,"vivzFragment");
        transaction.commit();
        
		this.chatButton();
		this.sendEmail();
		this.tutorialButton();
		this.calendarButton();
		this.notificationButton();
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
		String currentDateString = DateFormat.getDateInstance().format(new Date());
		TextView todayDate = (TextView)findViewById(R.id.todayDate);
		//Toast.makeText(this, currentDateTimeString, Toast.LENGTH_SHORT).show();
		//TextView todayDate = new TextView(this);
		// textView is the TextView view that; should display it
		todayDate.setText(currentDateString);
		
		//WeekDay

		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		Date d = new Date();
		String dayOfTheWeek = sdf.format(d);
		TextView weekDay = (TextView)findViewById(R.id.weekDay);
		weekDay.setText(dayOfTheWeek);
		return true;
		
	}
	   public class MyAdapter extends ArrayAdapter<String>{
	    	 
	        public MyAdapter(Context context, int textViewResourceId,   String[] objects) {
	            super(context, textViewResourceId, objects);
	        }
	 
	        @Override
	        public View getDropDownView(int position, View convertView,ViewGroup parent) {
	            return getCustomView(position, convertView, parent);
	        }
	 
	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	            return getCustomView(position, convertView, parent);
	        }
	 
	        public View getCustomView(int position, View convertView, ViewGroup parent) {
	 
	            LayoutInflater inflater=getLayoutInflater();
	            View row=inflater.inflate(R.layout.row, parent, false);
	            TextView label=(TextView)row.findViewById(R.id.mood);
		   		label.setText(strings[position]);
	
	            ImageView icon=(ImageView)row.findViewById(R.id.image1);
	            icon.setImageResource(arr_images[position]);
	 
	            return row;
	        
	        }   
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
	
	private void notificationButton()
	{
		ImageButton notification = (ImageButton)this.findViewById(R.id.notificationBtn); 
		notification.setOnClickListener(new ImageButton.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(getApplicationContext(), Notification.class);	
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

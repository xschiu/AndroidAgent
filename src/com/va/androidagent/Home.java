package com.va.androidagent;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {

	//to receive message from AlarmReceiver class 

	private String memberFieldString;

	//Variabels
	String[] strings = {"Excitement","Arousal","Pleasure","Contentment","Sleepiness","Depression","Misery","Distress","Neutral"};
	int arr_images[] = { R.drawable.moodicon1, R.drawable.moodicon2,R.drawable.moodicon3, R.drawable.moodicon4, R.drawable.moodicon5, R.drawable.moodicon6, R.drawable.moodicon7, R.drawable.moodicon8, R.drawable.moodicon9};
	String[] weather = {"Rainning","Sunny","Thunder","Cloudy"};
	int arr_weather[] = {R.drawable.raining, R.drawable.sunny, R.drawable.thunder, R.drawable.cloudy};
	int arr_location[] = {R.drawable.livingroom, R.drawable.bathroom, R.drawable.bedroom, R.drawable.kitchen};
	String testStr = "testtest";
	String[] grids = {"Excitement"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		 //weather spinner
//      Spinner weatherSpinner = (Spinner)findViewById(R.id.weatherSpinner);
//      weatherSpinner.setAdapter(new WeatherAdapter(Home.this, R.layout.weather, strings));
      
      
      
      //fragment working code
//      MyFragment frag = new MyFragment();
//      FragmentManager manager = getFragmentManager();
//      FragmentTransaction transaction= manager.beginTransaction();
//      transaction.add(R.id.my_layout,frag,"vivzFragment");
//      transaction.commit();
      
    
      
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

//		 TextView tvHeading = (TextView) findViewById(R.id.titleHeading);
//		 tvHeading.setText("Add New Transaction");


		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		//change background of layout periodically
		final RelativeLayout locationImage = (RelativeLayout) findViewById(R.id.my_layout);
		final Handler handler = new Handler();

		Runnable runnable = new Runnable()
		{
			int i = 0;
			public void run()
			{
				locationImage.setBackgroundResource(arr_location[i]);
					i++;
				if(i>arr_location.length-1) { i=0; }
				handler.postDelayed(this, 5000); //for interval

			}
		};
			handler.postDelayed(runnable, 1000); //for initial delay

		//mood spinner
		Spinner mySpinner = (Spinner)findViewById(R.id.emotionSpinner);
        mySpinner.setAdapter(new MyAdapter(Home.this, R.layout.row, grids));
    
        
		this.chatButton();
		this.sendEmail();
		this.playButton();
		this.calendarButton();
		this.meButton();
		//this.listView();

		initList();



	}

	@Override
    protected void onResume() {
		String initialMessage = "The weather looks good today";
		super.onResume();
		TextView initialText = new TextView(this);
		initialText = (TextView)findViewById(R.id.butlerMessageTxt);
		initialText.setText(initialMessage);
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        if (message != null && !message.isEmpty()) {
        	TextView textView = new TextView(this);
    		textView = (TextView)findViewById(R.id.butlerMessageTxt);
    		textView.setText(message);
    		
        }
        


	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);

		//display date
//		String currentDateString = DateFormat.getDateInstance().format(new Date());
//		TextView todayDate = (TextView)findViewById(R.id.todayDate);
//		todayDate.setText(currentDateString);

		//Toast.makeText(this, currentDateTimeString, Toast.LENGTH_SHORT).show();
		//TextView todayDate = new TextView(this);
		// textView is the TextView view that; should display it




		//Display WeekDay

//		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
//		Date d = new Date();
//		String dayOfTheWeek = sdf.format(d);
//		TextView weekDay = (TextView)findViewById(R.id.weekDay);
//		weekDay.setText(dayOfTheWeek);
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


	        //change TextView label to label1 & delete the gridView from on Create, add spinner back
	        	//

	            LayoutInflater inflater=getLayoutInflater();
	            View row=inflater.inflate(R.layout.row, parent, false);

	            GridView label=(GridView)row.findViewById(R.id.grid);
	            
	            
	            ArrayAdapter ad=new ArrayAdapter(Home.this,R.layout.grid_view_item,R.id.mood,strings);
	            label.setAdapter(ad);

//	            View gridView=inflater.inflate(R.layout.grid_view_item, parent, false);	
//	            TextView label1=(TextView)gridView.findViewById(R.id.mood);
//	            
//		   		label1.setText(strings[position]);
//		   		
//		   		
//	            ImageView icon=(ImageView)gridView.findViewById(R.id.image1);
//	            icon.setImageResource(arr_images[position]);

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
				Intent intent = new Intent(getApplicationContext(), CalendarViewClass.class);	
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

	private void playButton()
	{
		ImageButton play = (ImageButton)this.findViewById(R.id.playBtn); 
		play.setOnClickListener(new ImageButton.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(getApplicationContext(), Play.class);	
				startActivity(intent); 
			}
		}); 
	}

	private void meButton()
	{
		ImageButton me = (ImageButton)this.findViewById(R.id.meBtn); 
		me.setOnClickListener(new ImageButton.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(getApplicationContext(), Me.class);	
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
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


import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
	String[] strings = {"Distress","Arousal","Excitement","Misery","Neutral","Pleasure","Depression","Sleepy","Contentment"};
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
//		this.playButton();
		this.calendarButton();
		this.meButton();
		//this.listView();


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

		String currentDateString = DateFormat.getDateInstance().format(new Date());
		String currentTimeString = new SimpleDateFormat("HH:mm:ss").format(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		Date d = new Date();
		String dayOfTheWeek = sdf.format(d);
		// textView is the TextView view that should display it
		TextView todayDate = (TextView)findViewById(R.id.todayDate);
		
		todayDate.setText(currentDateString + "  " + currentTimeString + "  "+ dayOfTheWeek);

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

		   private Context context;
	        public MyAdapter(Context context, int textViewResourceId,   String[] objects) {
	            super(context, textViewResourceId, objects);
	            this.context=context;
	        }

	        @Override
	        public View getDropDownView(int position, View convertView,ViewGroup parent) {
	            return getCustomView(position, convertView, parent);
	        }

	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {

	        	// TODO Auto-generated method stub
//		        TextView label=(TextView)convertView;
//
//		          if (convertView==null) {
//		            convertView=new TextView(context);
//		            label=(TextView)convertView;
//		          }
//
//		          label.setText(strings[position]);
//
//		          return(convertView);
	        	

		          return getCustomView(position, convertView, parent);

	        }

	        public View getCustomView(int position, View convertView, ViewGroup parent) {


	        	 //return super.getView(position, convertView, parent);

		        LayoutInflater inflater=getLayoutInflater();
		        View row=inflater.inflate(R.layout.row, parent, false);
		        
		       
		        CustomGrid adapter = new CustomGrid(Home.this, strings, arr_images);
		        GridView grid=(GridView)row.findViewById(R.id.grid);
		        grid.setAdapter(adapter);
		        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		                    @Override
		                    public void onItemClick(AdapterView<?> parent, View view,
		                                            int position, long id) {
		                        Toast.makeText(Home.this, "You are feeling" +strings[+ position], Toast.LENGTH_SHORT).show();
		                      
		                        
		                    }
		                });

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

//	private void playButton()
//	{
//		ImageButton play = (ImageButton)this.findViewById(R.id.playBtn); 
//		play.setOnClickListener(new ImageButton.OnClickListener()
//		{ 
//			@Override
//			public void onClick(View v) 
//			{
//				Intent intent = new Intent(getApplicationContext(), Play.class);	
//				startActivity(intent); 
//			}
//		}); 
//	}

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






}
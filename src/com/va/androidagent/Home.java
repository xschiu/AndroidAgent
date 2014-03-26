package com.va.androidagent;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
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
	String[] weather = {"Rainning","Sunny","Thunder","Cloudy"};
	int arr_weather[] = {R.drawable.raining, R.drawable.sunny, R.drawable.thunder, R.drawable.cloudy};
	int arr_location[] = {R.drawable.livingroom, R.drawable.bathroom, R.drawable.bedroom, R.drawable.kitchen};
	String testStr = "testtest";
	String[] grids = {"Excitement"};
	int x = 0;
	int i = 0;

	
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
//		Spinner mySpinner = (Spinner)findViewById(R.id.emotionSpinner);
//        mySpinner.setAdapter(new MyAdapter(Home.this, R.layout.row, grids));
//    
        
		
		this.chatButton();
		this.sendEmail();
		this.playFunctionButton();
		this.memoryButton();
		this.calendarButton();
		this.aboutMeButton();
		this.gridSpinner();
//		this.expandButton();
		
		//this.listView();


	}

	@Override
    protected void onResume() {
		String initialMessage = "It's sunny outside. Let's have a jog together?";
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
		
		todayDate.setText(currentDateString + " " + currentTimeString + " "+ dayOfTheWeek);

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
	
	   private void gridSpinner()
		{
		   
			ImageView gridSpinner = (ImageView)this.findViewById(R.id.gridSpinner); 
			gridSpinner.setOnClickListener(new ImageView.OnClickListener()
			{ 
				@Override
				public void onClick(View v) 
				{
					 
					 
					MyFragment frag = new MyFragment();
					FragmentManager manager = getFragmentManager();
					FragmentTransaction transaction= manager.beginTransaction();
					transaction.add(R.id.my_layout,frag,"vivzFragment");
					transaction.commit();
				
				     
				}
			}); 
		}
   

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

	private void playFunctionButton()
	{
		ImageButton play = (ImageButton)this.findViewById(R.id.playFunctionBtn); 
		play.setOnClickListener(new ImageButton.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(getApplicationContext(), Play1.class);	
				startActivity(intent); 
			}
		}); 
	}

	private void aboutMeButton()
	{
		ImageButton me = (ImageButton)this.findViewById(R.id.meBtn); 
		me.setOnClickListener(new ImageButton.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(getApplicationContext(), AboutMe.class);	
				startActivity(intent); 
			}
		}); 
	}
	
	private void memoryButton()
	{
		ImageButton memory = (ImageButton)this.findViewById(R.id.diaryBtn); 
		memory.setOnClickListener(new ImageButton.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
				startActivity(browserIntent);
			}
		}); 
	}
	
//	private void expandButton()
//	{
//		ImageButton expand = (ImageButton)this.findViewById(R.id.expandBtn); 
//		expand.setOnClickListener(new ImageButton.OnClickListener()
//		{ 
//			@Override
//			public void onClick(View v) 
//			{
//				if (i==0){
//					ImageButton expand = (ImageButton)findViewById(R.id.expandBtn); 
//					expand.setBackgroundResource(R.drawable.buttonshrink);
//					i=1;
//					
//					ButlerMessageExpandedView expandedView = new ButlerMessageExpandedView();
//					FragmentManager manager = getFragmentManager();
//					FragmentTransaction transaction= manager.beginTransaction();
//					transaction.add(R.id.my_layout,expandedView,"expandedView");
//					transaction.commit();
//				}
//				else{
//					ImageButton expand = (ImageButton)findViewById(R.id.expandBtn); 
//					expand.setBackgroundResource(R.drawable.buttonexpand);
//					i=0;
//					 
//	                FragmentManager manager = getFragmentManager();
//				    FragmentTransaction transaction= manager.beginTransaction();
//				    transaction.remove(manager.findFragmentByTag("expandedView")).commit();
//				}
//				
//				
//			}
//		}); 
//	}





}
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
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
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

	public String initialMessage;

	

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
		this.socialButton();
//		this.expandButton();

		//this.listView();


	}

	@Override
    protected void onResume() {
		initialMessage = "It's sunny outside. Let's have a jog together?";
		super.onResume();

		TextView initialText = new TextView(this);
		initialText = (TextView)findViewById(R.id.butlerMessageTxt);
		initialText.setText(initialMessage);
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        
//        CalendarService.readCalendar(Home.this);
        
        if (message != null && !message.isEmpty()) {
        	
    		if(intent.getStringExtra("level").equals("1")){
    			TextView textView = new TextView(this);
        		textView = (TextView)findViewById(R.id.butlerMessageTxt);
        		textView.setText(message);
        		
        		RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        		layoutParams.setMargins(0, 0, 100, 0);
        		textView.setPadding(0, 0, 0, 150);
        		textView.setLayoutParams(layoutParams);
        		
    		
//				ButlerMessageExpandedView expandedView = new ButlerMessageExpandedView();
//				FragmentManager manager = getFragmentManager();
//				FragmentTransaction transaction= manager.beginTransaction();
//				transaction.add(R.id.my_layout,expandedView,"expandedView");
//				transaction.commit();
        		
        		Button yesButton = new Button(this);
                yesButton.setText("OK");
                yesButton.setId(1);
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.my_layout);
                RelativeLayout.LayoutParams yesButtonParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                yesButtonParam.setMargins(100, 1450, 0, 0);
                yesButton.setLayoutParams(yesButtonParam); 
                yesButton.setOnClickListener(new OnClickListener(){
                	public void onClick(View v) {

                		closeButtonGroup();
                		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        				Long time = new GregorianCalendar().getTimeInMillis()+5000;
        				Intent priority2 = new Intent(Home.this,Priority2Alarm.class);
        				alarmManager.set(AlarmManager.RTC_WAKEUP,time+5000, PendingIntent.getBroadcast(Home.this,2, priority2, PendingIntent.FLAG_UPDATE_CURRENT));
                		
                	}
                });
                layout.addView(yesButton);
                
                
				addNoButton();
                addMoreButton();
               
    		}
    		
    		
    		
    		
			if(intent.getStringExtra("level").equals("2")){
			
				TextView textView = new TextView(this);
        		textView = (TextView)findViewById(R.id.butlerMessageTxt);
        		textView.setText(message);
        		RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        		layoutParams.setMargins(0, 0, 100, 0);
        		textView.setPadding(0, 0, 0, 150);
        		textView.setLayoutParams(layoutParams);
        		
        		
				Button okButton = new Button(this);
                okButton.setText("OK");
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.my_layout);
                RelativeLayout.LayoutParams layoutParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParam.setMargins(100, 1450, 0, 0);
                okButton.setLayoutParams(layoutParam); 
                
                okButton.setOnClickListener(new OnClickListener(){
                	public void onClick(View v) {
                		
                		
                	}
                });
                layout.addView(okButton);
                
                addNoButton();
                addMoreButton();
                
				Toast.makeText(this, "Level 2", Toast.LENGTH_LONG).show();
			}
        
        
	    		if(intent.getStringExtra("level").equals("5")){
	    			TextView textView = new TextView(this);
	        		textView = (TextView)findViewById(R.id.butlerMessageTxt);
	        		textView.setText(message);
	        		
	        		RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	        		layoutParams.setMargins(0, 0, 100, 0);
	        		textView.setPadding(0, 0, 0, 150);
	        		textView.setLayoutParams(layoutParams);
	        		
	    		
	//				ButlerMessageExpandedView expandedView = new ButlerMessageExpandedView();
	//				FragmentManager manager = getFragmentManager();
	//				FragmentTransaction transaction= manager.beginTransaction();
	//				transaction.add(R.id.my_layout,expandedView,"expandedView");
	//				transaction.commit();
	        		
	        		Button med1Button = new Button(this);
	                med1Button.setText("OK");
	                med1Button.setId(5);
	                RelativeLayout layout = (RelativeLayout) findViewById(R.id.my_layout);
	                RelativeLayout.LayoutParams okButtonParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	                okButtonParam.setMargins(100, 1450, 0, 0);
	                med1Button.setLayoutParams(okButtonParam); 
	                med1Button.setOnClickListener(new OnClickListener(){
	                	public void onClick(View v) {
	
	                	   ViewGroup layout = (ViewGroup) findViewById(R.id.my_layout);
	             		   View med1Btn = layout.findViewById(5);
	             		   layout.removeView(med1Btn);
	             		   
	             		   TextView textView = new TextView(Home.this);
		           	   	   textView = (TextView)findViewById(R.id.butlerMessageTxt);
		           	   	   textView.setText(initialMessage);
		           		   RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		           	   	   layoutParams.setMargins(0, 0, 0, 0);
		           	   	   textView.setPadding(0, 0, 0, 0);
		           	   	   textView.setLayoutParams(layoutParams);	

	                		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
	        				Long time = new GregorianCalendar().getTimeInMillis()+5000;
	        				Intent med2 = new Intent(Home.this,Med2.class);
	        				alarmManager.set(AlarmManager.RTC_WAKEUP,time+5000, PendingIntent.getBroadcast(Home.this,5, med2, PendingIntent.FLAG_UPDATE_CURRENT));
	                		
	                	}
	                });
	              
	                layout.addView(med1Button);
	                Toast.makeText(this, "Med Level 1", Toast.LENGTH_LONG).show();

	    		}
	    		
	    		
	    		
	    		if(intent.getStringExtra("level").equals("6")){
	    			
					TextView textView = new TextView(this);
	        		textView = (TextView)findViewById(R.id.butlerMessageTxt);
	        		textView.setText(message);
	        		RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	        		layoutParams.setMargins(0, 0, 100, 0);
	        		textView.setPadding(0, 0, 0, 150);
	        		textView.setLayoutParams(layoutParams);
	        		
	        		
					Button med3Button = new Button(this);
	                med3Button.setText("OK");
	                med3Button.setId(6);
	                RelativeLayout layout = (RelativeLayout) findViewById(R.id.my_layout);
	                RelativeLayout.LayoutParams layoutParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	                layoutParam.setMargins(100, 1450, 0, 0);
	                med3Button.setLayoutParams(layoutParam); 
	                
	                med3Button.setOnClickListener(new OnClickListener(){
	                	public void onClick(View v) {
	                		ViewGroup layout = (ViewGroup) findViewById(R.id.my_layout);
		             		   View med3Btn = layout.findViewById(6);
		             		   layout.removeView(med3Btn);
		             		   
		             		   TextView textView = new TextView(Home.this);
			           	   	   textView = (TextView)findViewById(R.id.butlerMessageTxt);
			           	   	   textView.setText(initialMessage);
			           		   RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			           	   	   layoutParams.setMargins(0, 0, 0, 0);
			           	   	   textView.setPadding(0, 0, 0, 0);
			           	   	   textView.setLayoutParams(layoutParams);	

			           	   	   AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			           	   	   Long time = new GregorianCalendar().getTimeInMillis()+5000;
			           	   	   Intent med3 = new Intent(Home.this,Med3.class);
			           	   	   alarmManager.set(AlarmManager.RTC_WAKEUP,time+5000, PendingIntent.getBroadcast(Home.this,5, med3, PendingIntent.FLAG_UPDATE_CURRENT));
	                		
	                	}
	                });
	                layout.addView(med3Button);
	                
	                
					Toast.makeText(this, "Level 6", Toast.LENGTH_LONG).show();
				}

	    		
	    		if(intent.getStringExtra("level").equals("7")){
	    			
					TextView textView = new TextView(this);
	        		textView = (TextView)findViewById(R.id.butlerMessageTxt);
	        		textView.setText(initialMessage);
	        		RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	        		layoutParams.setMargins(0, 0, 100, 0);
	        		textView.setPadding(0, 0, 0, 150);
	        		textView.setLayoutParams(layoutParams);
	                
	                
					Toast.makeText(this, "Level 7", Toast.LENGTH_LONG).show();
				}

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

		return true;

	}

	
		
	   private void addNoButton(){
		   RelativeLayout layout = (RelativeLayout) findViewById(R.id.my_layout);
		   Button noButton = new Button(this);
           noButton.setText("NO");
           noButton.setId(2);
           RelativeLayout.LayoutParams noButtonParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
           noButtonParam.setMargins(360, 1450, 0, 0);
           noButton.setLayoutParams(noButtonParam); 
           
           noButton.setOnClickListener(new OnClickListener(){
           	public void onClick(View v) {
           		closeButtonGroup();
           		
           	}
           });
           layout.addView(noButton);
	   }
		
	   private void addMoreButton(){
		   RelativeLayout layout = (RelativeLayout) findViewById(R.id.my_layout);
		   final Button moreButton = new Button(this);
           moreButton.setId(3);
           moreButton.setText("MORE");
           RelativeLayout.LayoutParams moreButtonParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
           moreButtonParam.setMargins(620, 1450, 0, 0);
           moreButton.setLayoutParams(moreButtonParam); 
           
           moreButton.setOnClickListener(new OnClickListener(){
           	public void onClick(View v) {
           		if (i==0){
           			moreButton.setText("Close");
   					i=1;
   					
   					ButlerMessageExpandedView expandedView = new ButlerMessageExpandedView();
   					FragmentManager manager = getFragmentManager();
   					FragmentTransaction transaction= manager.beginTransaction();
   					transaction.add(R.id.my_layout,expandedView,"expandedView");
   					transaction.commit();
   				}
   				else{
   					moreButton.setText("More");
   					i=0;
   					 
   	                FragmentManager manager = getFragmentManager();
   				    FragmentTransaction transaction= manager.beginTransaction();
   				    transaction.remove(manager.findFragmentByTag("expandedView")).commit();
   				}
           		
           	}
           });
           layout.addView(moreButton);
           
			
			
	   }
		
	   private void closeButtonGroup(){ 
		   ViewGroup layout = (ViewGroup) findViewById(R.id.my_layout);
		   View yesBtn = layout.findViewById(1);
		   View noBtn = layout.findViewById(2);
		   View moreBtn = layout.findViewById(3);
			
		   layout.removeView(yesBtn);
		   layout.removeView(noBtn);
		   layout.removeView(moreBtn);
		   
		   TextView textView = new TextView(this);
	   	   textView = (TextView)findViewById(R.id.butlerMessageTxt);
	   	   textView.setText(initialMessage);
		   RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	   	   layoutParams.setMargins(0, 0, 0, 0);
	   	   textView.setPadding(0, 0, 0, 0);
	   	   textView.setLayoutParams(layoutParams);	
	   }
	   
	   //methods to call emoticons
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
	
	private void socialButton()
	{
		ImageButton social = (ImageButton)this.findViewById(R.id.socialize); 
		social.setOnClickListener(new ImageButton.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(getApplicationContext(), Social.class);	
				startActivity(intent); 
			}
		}); 
	}
}
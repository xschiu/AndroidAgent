package com.va.androidagent;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;


import android.text.format.Time;
import android.util.Log;
import android.view.GestureDetector;
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
import android.view.MotionEvent;

public class Home extends Activity {

	//to receive message from AlarmReceiver class 
	protected static final long DOUBLE_CLICK_MAX_DELAY = 1000L;
	private long thisTime = 0;
    private long prevTime = 0;
    private boolean firstTap = true;
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
      
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);


		//change background of layout periodically
		final RelativeLayout locationImage = (RelativeLayout) findViewById(R.id.my_layout);
		final RelativeLayout doubleClickLayout = (RelativeLayout) findViewById(R.id.my_layout);
		final Handler handler = new Handler();

		//change background every 5000 miliseconds
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
		
			
			//*******double tap*********
			doubleClickLayout.setOnTouchListener( new View.OnTouchListener() {

	            @Override
	            public boolean onTouch(View v, MotionEvent event) {
	                // TODO Auto-generated method stub
	                if(firstTap){
	                    thisTime = SystemClock.uptimeMillis();
	                    firstTap = false;
	                }
	                else
	                {
	                    prevTime = thisTime;
	                    thisTime = SystemClock.uptimeMillis();

	                    //Check that thisTime is greater than prevTime
	                    //just incase system clock reset to zero
	                    if(thisTime > prevTime){

	                        //Check if times are within our max delay
	                        if((thisTime - prevTime) <= DOUBLE_CLICK_MAX_DELAY){
	                        
	                        	
	                           InputStream inputStream = getResources().openRawResource(R.raw.events);
	                           InputStream time = getResources().openRawResource(R.raw.timeline);
	                           //convert inputstream to string then to date
	                           
	                           SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
	                           Date convertedDate = new Date();
	                           
	                           
	                           System.out.println(inputStream);
	                           System.out.println(time);
	                           ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	                           ByteArrayOutputStream timeStream = new ByteArrayOutputStream();
	                           
	                           

	                           int eventList;
	                           int timeList;
	                        try {
	                         eventList = inputStream.read();
	                         timeList = time.read();
	                         while (eventList != -1)
	                            {
	                             byteArrayOutputStream.write(eventList);
	                             timeStream.write(timeList);
	                             eventList = inputStream.read();
	                             timeList = time.read();
	                             
	                             
	                             
	                            }
	                            inputStream.close();
	                            time.close();
	                        } catch (IOException e) {
	                         // TODO Auto-generated catch block
	                         e.printStackTrace();
	                        }
	                        String timeString = time.toString();
	                        try {
								convertedDate = dateFormat.parse(timeString);
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
	                        	
	                            //We have detected a double tap!
	                            Toast.makeText(Home.this, "Searching for events......", Toast.LENGTH_LONG).show();
	                            //PUT YOUR LOGIC HERE!!!!
	                            
	                            Calendar datetime = DateToCalendar(convertedDate);
	                            Intent i = new Intent(getApplicationContext(),Home.class);
                    			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    			i.putExtra("message", "There will be this events:" + byteArrayOutputStream.toString() + "on "+ datetime);
                    			i.putExtra("level", "1");
                    			startActivity(i);

	                        }
	                        else 
	                        {
	                            //Otherwise Reset firstTap
	                            firstTap = true;
	                        }
	                    }
	                    else 
	                    {
	                        firstTap = true;
	                    }
	                }
	                return false;
	            }
	        });
			
		//doubletap ***************	

		//declare the method used in this activity	
		this.chatButton();
		this.sendEmail();
		this.playFunctionButton();
		this.memoryButton();
		this.calendarButton();
		this.aboutMeButton();
		this.gridSpinner();
		this.socialButton();
		this.learnBtn();
	}

	public static Calendar DateToCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
		}
	//when the activity is nolonger visible, and become visible again, onResume method is called
	@Override
    protected void onResume() {
		initialMessage = "It's sunny outside. It's a good weather to do exercise!!";
		super.onResume();

		//set initial message to be display on the interaction panel
		TextView initialText = new TextView(this);
		initialText = (TextView)findViewById(R.id.butlerMessageTxt);
		initialText.setText(initialMessage);
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        
        if (message != null && !message.isEmpty()) {
        	
        	//different level perform different task, the level is decided dynamically but is hard coded
    		if(intent.getStringExtra("level").equals("1")){
    			TextView textView = new TextView(this);
        		textView = (TextView)findViewById(R.id.butlerMessageTxt);
        		textView.setText(message);
        		
        		RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        		layoutParams.setMargins(0, 0, 100, 0);
        		textView.setPadding(0, 0, 0, 150);
        		textView.setLayoutParams(layoutParams);

        		Button yesButton = new Button(this);
                yesButton.setText("Yes"); //text on the button
                yesButton.setId(1); //give the dynamically created button an Id
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.my_layout);
                RelativeLayout.LayoutParams yesButtonParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                yesButtonParam.setMargins(100, 1450, 0, 0);
                yesButton.setLayoutParams(yesButtonParam); 
                yesButton.setOnClickListener(new OnClickListener(){               		
                	public void onClick(View v) {
                		closeButtonGroup();
                		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        				Long time = new GregorianCalendar().getTimeInMillis()+10000;
        				Intent priority2 = new Intent(Home.this,Priority2Alarm.class);
        				alarmManager.set(AlarmManager.RTC_WAKEUP,time+5000, PendingIntent.getBroadcast(Home.this,2, priority2, PendingIntent.FLAG_UPDATE_CURRENT));                		
                	}
                });
                layout.addView(yesButton);

				addNoButton(); //add no button
                addMoreButton(); // add more button
               
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
                okButton.setId(4);
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.my_layout);
                RelativeLayout.LayoutParams layoutParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParam.setMargins(100, 1450, 0, 0);
                okButton.setLayoutParams(layoutParam); 
                
                okButton.setOnClickListener(new OnClickListener(){
                	public void onClick(View v) {
                		ViewGroup layout = (ViewGroup) findViewById(R.id.my_layout);
	             		  
	             		   closeButtonGroup();
	             		   
	             		   TextView textView = new TextView(Home.this);
		           	   	   textView = (TextView)findViewById(R.id.butlerMessageTxt);
		           	   	   textView.setText(initialMessage);
		           		   RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		           	   	   layoutParams.setMargins(0, 0, 0, 0);
		           	   	   textView.setPadding(0, 0, 0, 0);
		           	   	   textView.setLayoutParams(layoutParams);	

		           	   	   AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		           	   	   Long time = new GregorianCalendar().getTimeInMillis()+15000;
		           	   	   Intent priority3 = new Intent(Home.this,Priority3Alarm.class);
		           	   	   alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(Home.this,4, priority3, PendingIntent.FLAG_UPDATE_CURRENT));
                		
                	}
                });
                layout.addView(okButton);
                
                addNoButton();
                addMoreButton();
			}
        
			if(intent.getStringExtra("level").equals("3")){
				
				TextView textView = new TextView(this);
        		textView = (TextView)findViewById(R.id.butlerMessageTxt);
        		textView.setText(message);
        		RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        		layoutParams.setMargins(0, 0, 100, 0);
        		textView.setPadding(0, 0, 0, 150);
        		textView.setLayoutParams(layoutParams);
        		
        		
				Button okButton = new Button(this);
                okButton.setText("OK");
                okButton.setId(7);
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.my_layout);
                RelativeLayout.LayoutParams layoutParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParam.setMargins(100, 1450, 0, 0);
                okButton.setLayoutParams(layoutParam); 
                
                okButton.setOnClickListener(new OnClickListener(){
                	public void onClick(View v) {
                		ViewGroup layout = (ViewGroup) findViewById(R.id.my_layout);
	             		   View okBtn = layout.findViewById(7);
	             		   layout.removeView(okBtn);
	             		   
	             		  TextView textView = new TextView(Home.this);
	  	        		textView = (TextView)findViewById(R.id.butlerMessageTxt);
	  	        		textView.setText(initialMessage);
	  	        		RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	  	        		layoutParams.setMargins(0, 0, 0, 0);
	  	        		textView.setPadding(0, 0, 0, 0);
	  	        		textView.setLayoutParams(layoutParams);
                		
                	}
                });
                layout.addView(okButton);
			}
        
	    		if(intent.getStringExtra("level").equals("5")){
	    			TextView textView = new TextView(this);
	        		textView = (TextView)findViewById(R.id.butlerMessageTxt);
	        		textView.setText(message);
	        		
	        		RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	        		layoutParams.setMargins(0, 0, 100, 0);
	        		textView.setPadding(0, 0, 0, 150);
	        		textView.setLayoutParams(layoutParams);

	        		Button med1Button = new Button(this);
	                med1Button.setText("OK");
	                med1Button.setId(5);
	                RelativeLayout layout = (RelativeLayout) findViewById(R.id.my_layout);
	                RelativeLayout.LayoutParams okButtonParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	                okButtonParam.setMargins(400, 1450, 0, 0);
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
	        				Long time = new GregorianCalendar().getTimeInMillis()+10000;
	        				Intent med2 = new Intent(Home.this,Med2.class);
	        				alarmManager.set(AlarmManager.RTC_WAKEUP,time+5000, PendingIntent.getBroadcast(Home.this,5, med2, PendingIntent.FLAG_UPDATE_CURRENT));
	                		
	                	}
	                });
	              
	                layout.addView(med1Button);

	    		}
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		if(intent.getStringExtra("level").equals("6")){
	    			
					TextView textView = new TextView(this);
	        		textView = (TextView)findViewById(R.id.butlerMessageTxt);
	        		textView.setText(message);
	        		RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	        		layoutParams.setMargins(0, 0, 100, 0);
	        		textView.setPadding(0, 0, 0, 150);
	        		textView.setLayoutParams(layoutParams);
	        		
	        		
					Button med2Button = new Button(this);
					Button med2NoButton = new Button(this);
	                med2Button.setText("Yes");
	                med2NoButton.setText("No");
	                med2Button.setId(6);
	                med2NoButton.setId(16);
	                RelativeLayout.LayoutParams medNoButtonParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	                medNoButtonParam.setMargins(430, 1450, 0, 0);
	                med2NoButton.setLayoutParams(medNoButtonParam); 
	                
	                RelativeLayout layout = (RelativeLayout) findViewById(R.id.my_layout);
	                RelativeLayout.LayoutParams layoutParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	                layoutParam.setMargins(240, 1450, 0, 0);
	                
	                med2Button.setLayoutParams(layoutParam); 
	                
	                med2Button.setOnClickListener(new OnClickListener(){
	                	public void onClick(View v) {
	                		closeButtonGroup();
	                   		RelativeLayout layout = (RelativeLayout) findViewById(R.id.my_layout);
	                   		View med2NoBtn = layout.findViewById(16);
	                   		layout.removeView(med2NoBtn);
	                		
	                	}
	                });
	                
	                med2NoButton.setOnClickListener(new OnClickListener(){
	                   	public void onClick(View v) {
	                   		ViewGroup layout = (ViewGroup) findViewById(R.id.my_layout);
		             		   View med2Btn = layout.findViewById(6);
		             		   View med2NoBtn = layout.findViewById(16);
		             		   layout.removeView(med2Btn);
		             		   layout.removeView(med2NoBtn);
		             		   
		             		   TextView textView = new TextView(Home.this);
			           	   	   textView = (TextView)findViewById(R.id.butlerMessageTxt);
			           	   	   textView.setText(initialMessage);
			           		   RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			           	   	   layoutParams.setMargins(0, 0, 0, 0);
			           	   	   textView.setPadding(0, 0, 0, 0);
			           	   	   textView.setLayoutParams(layoutParams);	

			           	   	   AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			           	   	   Long time = new GregorianCalendar().getTimeInMillis()+15000;
			           	   	   Intent med3 = new Intent(Home.this,Med3.class);
			           	   	   alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(Home.this,5, med3, PendingIntent.FLAG_UPDATE_CURRENT));
	                   		
	                   		
	                   		
	                   	}
	                   });
	                layout.addView(med2Button);
	                layout.addView(med2NoButton);
	                
	                
					
				}

	    		
	    		if(intent.getStringExtra("level").equals("7")){
	    			
	    			TextView textView = new TextView(this);
	        		textView = (TextView)findViewById(R.id.butlerMessageTxt);
	        		textView.setText(message);
	        		RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	        		layoutParams.setMargins(0, 0, 100, 0);
	        		textView.setPadding(0, 0, 0, 150);
	        		textView.setLayoutParams(layoutParams);
	        		
	        		
					Button okButton = new Button(this);
	                okButton.setText("OK");
	                okButton.setId(8);
	                RelativeLayout layout = (RelativeLayout) findViewById(R.id.my_layout);
	                RelativeLayout.LayoutParams layoutParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	                layoutParam.setMargins(400, 1450, 0, 0);
	                okButton.setLayoutParams(layoutParam); 
	                
	                okButton.setOnClickListener(new OnClickListener(){
	                	public void onClick(View v) {
	                		closeButtonGroup();
	                		TextView textView = new TextView(Home.this);
	    	        		textView = (TextView)findViewById(R.id.butlerMessageTxt);
	    	        		textView.setText(initialMessage);
	    	        		RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	    	        		layoutParams.setMargins(0, 0, 0, 0);
	    	        		textView.setPadding(0, 0, 0, 0);
	    	        		textView.setLayoutParams(layoutParams);
	                		
	                	}
	                });
	                layout.addView(okButton);
	                
	                
					
				}
	    			
	             

        }
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		String currentDateString = DateFormat.getDateInstance().format(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		Date d = new Date();
		String dayOfTheWeek = sdf.format(d);
//		textView is the TextView view that should display it
		TextView todayDate = (TextView)findViewById(R.id.todayDate);

		todayDate.setText(currentDateString + "                     "+ dayOfTheWeek);

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
           moreButton.setText("More");
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
		
	   //remove all dynamically created button
	   private void closeButtonGroup(){ 
		   ViewGroup layout = (ViewGroup) findViewById(R.id.my_layout);
		   View yesBtn = layout.findViewById(1);
		   View noBtn = layout.findViewById(2);
		   View moreBtn = layout.findViewById(3);
		   View okBtn = layout.findViewById(4);
		   View med1Btn = layout.findViewById(5);
		   View med2Btn = layout.findViewById(6);
		   View ok2Btn = layout.findViewById(7);
		   View ok3Btn = layout.findViewById(8);
		   
		  
		   layout.removeView(yesBtn);
		   layout.removeView(noBtn);
		   layout.removeView(moreBtn);
		   layout.removeView(okBtn);
		   layout.removeView(med1Btn);
		   layout.removeView(med2Btn);
		   layout.removeView(ok2Btn);
		   layout.removeView(ok3Btn);
		   
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
	
	private void learnBtn(){
		ImageButton learn = (ImageButton)this.findViewById(R.id.learnBtn); 
		learn.setOnClickListener(new ImageButton.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(getApplicationContext(), Learn.class);	
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
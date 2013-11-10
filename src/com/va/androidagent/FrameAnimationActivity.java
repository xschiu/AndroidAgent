package com.va.androidagent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FrameAnimationActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_animations_layout);
//		this.setupButton();
		
		animate();
		greeting();
		this.goToHome();
		
	}
	
	public void goToHome()
	{
		LinearLayout llayout = (LinearLayout)findViewById(R.id.functionsLayout);
		llayout.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	Intent gotomain = new Intent(FrameAnimationActivity.this, Home.class);	
				startActivity(gotomain); 
	        }
		});
		
	}
	
	
	
//	private void setupButton()
//	{
//		Button b = (Button)this.findViewById(R.id.startFAButtonId); 
//		b.setOnClickListener(new Button.OnClickListener()
//		{ 
//			public void onClick(View v) 
//			{
//				parentButtonClicked(v); 
//			}
//		}); 
//	}
	
	
	private void greeting()
	{
		Intent intent = getIntent();
		String name = intent.getStringExtra(MainActivity.NAME);
		TextView textView = new TextView(this);
		textView = (TextView)findViewById(R.id.textView1);
		
		String temp = "Welcome Mr."+name+", You can ask me any question!!";
		textView.setText(temp);
		
		
		
	}

	private void animate()
	{
		ImageView imgView = (ImageView)findViewById(R.id.animationImage); 
		imgView.setVisibility(ImageView.VISIBLE); 
		imgView.setBackgroundResource(R.drawable.frame_animation);
		AnimationDrawable frameAnimation = (AnimationDrawable) imgView.getBackground();
		
		if (frameAnimation.isRunning()) 
		{
			frameAnimation.stop(); 
		}
		else 
		{
			frameAnimation.stop();
			frameAnimation.start(); 
		}
		
		
	} 

}

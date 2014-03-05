package com.va.androidagent;

//import com.va.androidagent.Butler.MyAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Butler extends Activity {

	
	String[] strings = {"Happy","Sad"};
	int arr_images[] = { R.drawable.smileyface, R.drawable.sadface};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_butler);
		Spinner mySpinner = (Spinner)findViewById(R.id.spinner1);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, 
				R.array.emotion_icon, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		//mySpinner.setAdapter(adapter);
      
//	   mySpinner.setAdapter(new MyAdapter(Butler.this, R.layout.row, strings));
    
	    
        this.sendButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.butler, menu);
		return true;
	}
	
//	public class MyAdapter extends ArrayAdapter<String>{
//
//        public MyAdapter(Context context, int textViewResourceId,   String[] objects) {
//            super(context, textViewResourceId, objects);
//        }
// 
//        @Override
//        public View getDropDownView(int position, View convertView,ViewGroup parent) {
//            return getCustomView(position, convertView, parent);
//        }
// 
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            return getCustomView(position, convertView, parent);
//        }
// 
//        public View getCustomView(int position, View convertView, ViewGroup parent) {
// 
////            LayoutInflater inflater=getLayoutInflater();
////            View row=inflater.inflate(R.layout.row, parent, false);
////            TextView label=(TextView)row.findViewById(R.id.mood);
////            label.setText(strings[position]);
//// 
////            ImageView icon=(ImageView)row.findViewById(R.id.image1);
////            icon.setImageResource(arr_images[position]);
// 
////            return row;
//        
//        }   
//   }
    
   private void sendButton()
  {
		Button b = (Button)this.findViewById(R.id.sendBtn); 
		b.setOnClickListener(new Button.OnClickListener()
		{ 
			public void onClick(View v) 
			{
				EditText editText = (EditText) findViewById(R.id.taskTxt);
				String task = editText.getText().toString();
			    
			    if (task.contentEquals("email")){
			    	Intent intent = new Intent(Butler.this, Email.class);
					startActivity(intent);
			    }	
				
			}
		 
		}); 
		
	}

}

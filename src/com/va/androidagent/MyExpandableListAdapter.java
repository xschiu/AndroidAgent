package com.va.androidagent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

  private final SparseArray<Group> groups;
  public LayoutInflater inflater;
  public Activity activity;

  public MyExpandableListAdapter(Activity act, SparseArray<Group> groups) {
    activity = act;
    this.groups = groups;
    inflater = act.getLayoutInflater();
  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
    return groups.get(groupPosition).children.get(childPosition);
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return 0;
  }

  @Override
  public View getChildView(int groupPosition, final int childPosition,
      boolean isLastChild, View convertView, ViewGroup parent) {
    final String children = (String) getChild(groupPosition, childPosition);
    TextView text = null;
    if (convertView == null) {  
      convertView = inflater.inflate(R.layout.listrow_details, null);
    }
    text = (TextView) convertView.findViewById(R.id.textView1);
    text.setText(children);
    
    if (children.equals("Ballroom")){
    	Drawable img = activity.getResources().getDrawable( R.drawable.dancesport );
        text.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
    }
    if (children.equals("Salsa")){
    	Drawable img = activity.getResources().getDrawable( R.drawable.salsa );
        text.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
    }
    if (children.equals("Ballet")){
    	Drawable img = activity.getResources().getDrawable( R.drawable.ballet );
        text.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
    }
    
    if (children.equals("Swimming")){
    	Drawable img = activity.getResources().getDrawable( R.drawable.swimming );
        text.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
    }
    
    if (children.equals("Running")){
    	Drawable img = activity.getResources().getDrawable( R.drawable.running );
        text.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
    }
    

    convertView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(activity, children,
            Toast.LENGTH_SHORT).show();
        if (children.equals("Ballroom")){
        	
        	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=MXmvyPzkkQo"));
			activity.startActivity(browserIntent);
        }
        if (children.equals("Salsa")){
        	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=aV8dS2m9Adc"));
			activity.startActivity(browserIntent);
        }
        if (children.equals("Ballet")){
        	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=ZIm7QNsU-x4"));
			activity.startActivity(browserIntent);
        }
        
        if (children.equals("Swimming")){
        	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=ioFydzeYgt8"));
			activity.startActivity(browserIntent);
        }
        
        if (children.equals("Running")){
        	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=Gty7rFrsnBg"));
			activity.startActivity(browserIntent);
        }
        
        
      }
        		
    });
    return convertView;
  }
  
  
  
  @Override
  public int getChildrenCount(int groupPosition) {
    return groups.get(groupPosition).children.size();
  }

  @Override
  public Object getGroup(int groupPosition) {
    return groups.get(groupPosition);
  }

  @Override
  public int getGroupCount() {
    return groups.size();
  }

  @Override
  public void onGroupCollapsed(int groupPosition) {
    super.onGroupCollapsed(groupPosition);
  }

  @Override
  public void onGroupExpanded(int groupPosition) {
    super.onGroupExpanded(groupPosition);
  }

  @Override
  public long getGroupId(int groupPosition) {
    return 0;
  }
  
  @Override
  public View getGroupView(int groupPosition, boolean isExpanded,
      View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.listrow_group, null);
    }
    Group group = (Group) getGroup(groupPosition);
    ((CheckedTextView) convertView).setText(group.string);
    ((CheckedTextView) convertView).setChecked(isExpanded);
    if (group.string == "Dance")
    	((CheckedTextView) convertView).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.dance, 0);
    if (group.string == "Sport")
    	((CheckedTextView) convertView).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.sport, 0);
    if (group.string == "Stretch")
    	((CheckedTextView) convertView).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.stretch, 0);
    if (group.string == "Yoga")
    	((CheckedTextView) convertView).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.yoga, 0);
    if (group.string == "Fun")
    	((CheckedTextView) convertView).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.fun, 0);
   
    
    return convertView;
  }

  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return false;
  }
} 
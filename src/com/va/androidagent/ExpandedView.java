package com.va.androidagent;

import android.view.View;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class ExpandedView extends Fragment {
	int i=0;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
		
		String fullMsg = "full message";
	
		View V = inflater.inflate(R.layout.expanded, container, false);
//		View expandedTextView = V.findViewById(R.id.expandedTxt);
//	    ((ExpandableTextView)expandedTextView).setText(fullMsg);
		return V;
	}
	
	
	private void expandButton()
	{
		ImageButton expand = (ImageButton)getActivity().findViewById(R.id.expandBtn); 
		expand.setOnClickListener(new ImageButton.OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				if (i==0){
					ImageButton expand = (ImageButton)getActivity().findViewById(R.id.expandBtn); 
					expand.setBackgroundResource(R.drawable.buttonshrink);
					i=1;
					
					ButlerMessageExpandedView expandedView = new ButlerMessageExpandedView();
					FragmentManager manager = getFragmentManager();
					FragmentTransaction transaction= manager.beginTransaction();
					transaction.add(R.id.my_layout,expandedView,"expandedView");
					transaction.commit();
				}
				else{
					ImageButton expand = (ImageButton)getActivity().findViewById(R.id.expandBtn); 
					expand.setBackgroundResource(R.drawable.buttonexpand);
					i=0;
					 
	                FragmentManager manager = getFragmentManager();
				    FragmentTransaction transaction= manager.beginTransaction();
				    transaction.remove(manager.findFragmentByTag("expandedView")).commit();
				}
				
				
			}
		}); 
	}

}

package com.va.androidagent;

import android.view.View;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ExpandedView extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
		
		String fullMsg = "full message";
	
		View V = inflater.inflate(R.layout.expanded, container, false);
		View expandedTextView = V.findViewById(R.id.expandedTxt);
	    ((ExpandableTextView)expandedTextView).setText(fullMsg);
//		View expandableTextView = expandedTextView.findViewById(R.id.fragmentTxt);
//		((ExpandableTextView)expandableTextView).setText();
		//return inflater.inflate(R.layout.smart_butler,container,false);
		return expandedTextView;
	}
}

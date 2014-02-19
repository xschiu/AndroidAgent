package com.va.androidagent;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment {
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
		String yourText = "Hey, your favourite TV show <Talentime for Elderly> is about to begin in five minutes! You excited or nottttttttttttttttttttttttttttttttttttttttttttttttttttt";
		String depressText = "Don't be sad, let me tell you a joke";
       
        
        View V = inflater.inflate(R.layout.smart_butler, container, false);
//        ExpandableTextView expandableTextView = (ExpandableTextView) getView().findViewById(R.id.fragmentTxt);
//        expandableTextView.setText(yourText);
      
//        ExpandableTextView expandableTextView = (ExpandableTextView)V.findViewById(R.id.fragmentTxt);
//        expandableTextView.setText(yourText);
       
        View expandableTextView = V.findViewById(R.id.fragmentTxt);
        ((ExpandableTextView)expandableTextView).setText(yourText);
		//return inflater.inflate(R.layout.smart_butler,container,false);
        return V;
	}
	
	
}

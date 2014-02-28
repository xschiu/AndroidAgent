package com.va.androidagent;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




public class MyFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
		String yourText = "Hey, your favourite TV show <Talentime for Elderly> is about to begin in five minutes! You excited or nottttttttttttttttttttttttttttttttttttttttttttttttttttt";
		String depressText = "Don't be sad, let me tell you a joke";

		Intent intent = getActivity().getIntent();
		String priority1 = intent.getStringExtra(MainActivity.NAME);
		
		
        View V = inflater.inflate(R.layout.smart_butler, container, false);
        View expandableTextView = V.findViewById(R.id.fragmentTxt);
        ((ExpandableTextView)expandableTextView).setText(yourText);
        return V;
	}
	
	
	
	
}

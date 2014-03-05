package com.va.androidagent;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;




public class MyFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
		
		View V = inflater.inflate(R.layout.grid_spinner, container, false);
		ImageView icon = (ImageView) V.findViewById(R.id.image1);
     
 
		return V;
	}




}
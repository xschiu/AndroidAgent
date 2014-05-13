package com.va.androidagent;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ButlerMessageExpandedView extends Fragment  {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
	
		View V = inflater.inflate(R.layout.expanded, container, false);

		return V;

	}
	
}

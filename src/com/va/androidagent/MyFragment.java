package com.va.androidagent;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


//Emoticons 

public class MyFragment extends Fragment {

	String[] strings = {"Distressed","Arousal","Excited","Misery","Neutral","Pleasure","Depressed","Sleepy","Contented"};
	int arr_images[] = { R.drawable.moodicon1, R.drawable.moodicon2,R.drawable.moodicon3, R.drawable.moodicon4, R.drawable.moodicon5, R.drawable.moodicon6, R.drawable.moodicon7, R.drawable.moodicon8, R.drawable.moodicon9};
	int images=3;
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
        View row=inflater.inflate(R.layout.row, container, false); 
        CustomGrid adapter = new CustomGrid(this.getActivity(), strings, arr_images);
        GridView grid=(GridView)row.findViewById(R.id.grid);
        grid.setAdapter(adapter); 
		return row;
	}
	
	 @Override
   	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        

        GridView grid=(GridView)getActivity().findViewById(R.id.grid);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "You are feeling " +strings[+ position], Toast.LENGTH_SHORT).show();
        
                ImageView gridSpinner = (ImageView) getActivity().findViewById(R.id.gridSpinner);
                
                
                switch(position) {
                case 0:
                	images = R.drawable.moodicon1;
                    break;
                case 1:
                	images = R.drawable.moodicon2;
                    break;
                case 2:
                	images = R.drawable.moodicon3;
                    break;
                case 3:
                	images = R.drawable.moodicon4;
                    break;
                case 4:
                	images = R.drawable.moodicon5;
                    break;
                case 5:
                	images = R.drawable.moodicon6;
                    break;
                case 6:
                	images = R.drawable.moodicon7;
                    break;
                case 7:
                	images = R.drawable.moodicon8;
                case 8:
                	images = R.drawable.moodicon9;
                    break;
                
                default:
                	images = R.drawable.moodicon4;
            }
                
                gridSpinner.setImageResource(images);
                
                FragmentManager manager = getFragmentManager();
			    FragmentTransaction transaction= manager.beginTransaction();
			    transaction.remove(manager.findFragmentByTag("vivzFragment")).commit();
                
         

            } 
        });
        
       
    }



}
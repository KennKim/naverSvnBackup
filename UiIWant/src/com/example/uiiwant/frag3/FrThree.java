package com.example.uiiwant.frag3;

import com.example.uiiwant.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class FrThree extends Fragment{
	
	private ActionBar actionBar;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
//		actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//		actionBar.setTitle("채널");
//		actionBar.setDisplayHomeAsUpEnabled(false);
//		actionBar.setHomeButtonEnabled(false);
		
	}
	
//	@Override
//	public void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//		actionBar.setTitle("채널");
//	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fr_3, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fr_three, menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
}

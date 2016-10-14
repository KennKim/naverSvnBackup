package com.example.uiiwant.frag2;

import com.example.uiiwant.MainActivity;
import com.example.uiiwant.R;
import com.example.uiiwant.frag1.HeaderViewEditText;
import com.example.uiiwant.frag2.chat.ChatMain;
import com.example.uiiwant.frag2.data.FrTwoItemData;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FrTwo extends Fragment{
	
	private ActionBar actionBar;
	
	private ListView list;
	private FrTwoAdapter mAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
//		actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//		actionBar.setTitle("채팅");
//		actionBar.setDisplayHomeAsUpEnabled(false);
//		actionBar.setHomeButtonEnabled(false);
	}
	
//	@Override
//	public void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//		actionBar.setTitle("채팅");
//	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		return inflater.inflate(R.layout.fr_2, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		list=(ListView)view.findViewById(R.id.listView1);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> patent, View view, int position, long id) {
				// TODO Auto-generated method stub
			
				Log.i("abc", "item click~!!");
				Intent intent = new Intent(getActivity(),ChatMain.class);
				startActivityForResult(intent, 1);
				
			}
		});
		
		HeaderViewEditText headerView = new HeaderViewEditText(getContext());
		headerView.editText.setHint(R.string.chat_edit_hint);
		list.addHeaderView(headerView);
		
		mAdapter=new FrTwoAdapter(getContext());
		
		list.setAdapter(mAdapter);
		
		initData();
		
	}
	
	private void initData(){
		
		for (int i = 0; i < 20; i++) {
			FrTwoItemData d = new FrTwoItemData();
			d.chatPhoto = R.drawable.fuckyou;
			d.chatTitle = "chatTitle " + i;
			d.lastTime = "2015.11." + (i+1);
			d.lastTalk = "lastTalk " + i;
			mAdapter.add(d);
		}
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fr_two, menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
	

	
}

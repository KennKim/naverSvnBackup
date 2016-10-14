package com.travel.radio.record;

import com.travel.radio.main.MainActivity;
import com.travel.radio.main.PropertyManager;
import com.travel.radio.main.R;
import com.travel.radio.main.R.drawable;
import com.travel.radio.main.R.id;
import com.travel.radio.main.R.layout;
import com.travel.radio.main.R.menu;
import com.travel.radio.record.tab.location.TabLocation;
import com.travel.radio.record.tab.main.TabMainOne;
import com.travel.radio.record.tab.powertj.TabPowerTJ;
import com.travel.radio.record.tab.sos.TabSOS;
import com.travel.radio.voice.googlemap.GoogleMapActivity;
import com.travel.radio.voice.memberlist.VoiceMemberList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabWidget;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

public class TabMain extends Fragment {
	
	private static final String TAB1_ID = "tab1";
	private static final String TAB2_ID = "tab2";
	private static final String TAB3_ID = "tab3";
	private static final String TAB4_ID = "tab4";

	FragmentTabHost tabHost;
	ActionBar actionBar;
	TabWidget tabs;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		setHasOptionsMenu(true);

		actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		actionBar.show();
		actionBar.setTitle("Travel Radio");
		actionBar.setIcon(R.drawable.actionbar_radio);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#333333")));

		// Fragment의 tabHost는 반드시 onCreateView안에 삽입.
		// Fragment의 tabHost는 반드시 onCreateView안에 삽입.
		// Fragment의 tabHost는 반드시 onCreateView안에 삽입.
		
		TabView1 view1 = new TabView1(getActivity());
		TabView2 view2 = new TabView2(getActivity());
		TabView3 view3 = new TabView3(getActivity());
		TabView4 view4 = new TabView4(getActivity());
		
		
		View view = inflater.inflate(R.layout.tab_main, container, false);
		tabs=(TabWidget)view.findViewById(android.R.id.tabs);
		tabs.setBackgroundResource(R.drawable.bar_one);

		tabHost = (FragmentTabHost) view.findViewById(R.id.tabhost);
		tabHost.setup(getActivity(), getChildFragmentManager(),R.id.realtabcontent);
		tabHost.addTab(tabHost.newTabSpec(TAB1_ID).setIndicator(view1),TabMainOne.class, null);
		tabHost.addTab(tabHost.newTabSpec(TAB2_ID).setIndicator(view2),TabLocation.class, null);
		tabHost.addTab(tabHost.newTabSpec(TAB3_ID).setIndicator(view3),TabPowerTJ.class, null);
		tabHost.addTab(tabHost.newTabSpec(TAB4_ID).setIndicator(view4),TabSOS.class, null);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				if (tabId.equals(TAB1_ID)) {
					tabs.setBackgroundResource(R.drawable.bar_one);

				} else if (tabId.equals(TAB2_ID)) {
					tabs.setBackgroundResource(R.drawable.bar_two);

				} else if (tabId.equals(TAB3_ID)) {
					tabs.setBackgroundResource(R.drawable.bar_three);

				} else if (tabId.equals(TAB4_ID)) {
					tabs.setBackgroundResource(R.drawable.bar_four);

				}
			}
		});
		
		Button btn = (Button)view.findViewById(R.id.button_voice);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Toast.makeText(getActivity(), "준비중입니다.", Toast.LENGTH_SHORT).show();
				
//				String latitude = PropertyManager.getInstance().getLatitude();
//				String longitude = PropertyManager.getInstance().getLongitude();
//				String radius = PropertyManager.getInstance().getRadius();
//				
//				if(latitude.equals("") || latitude==null){
//					Toast.makeText(getActivity(), "먼저 위치설정을 해주세요.", Toast.LENGTH_SHORT).show();
//				}else{
//					Intent intent = new Intent(getActivity(),VoiceMemberList.class);
//					intent.putExtra(VoiceMemberList.PARAM_LATITUDE,latitude);   
//					intent.putExtra(VoiceMemberList.PARAM_LONGITUDE,longitude); 
//					intent.putExtra(VoiceMemberList.PARAM_RADIUS,radius); 
//					startActivity(intent);
//				}
				
			}
		});
		
//		tabHost = (TabHost)view.findViewById(R.id.fragment_tabhost);
//		tabHost.setup();
//		tabHost.addTab(tabHost.newTabSpec(TAB1_ID).setIndicator("HOME").setContent(R.id.tab1));
//		tabHost.addTab(tabHost.newTabSpec(TAB2_ID).setIndicator("내지역").setContent(R.id.tab2));
//		tabHost.addTab(tabHost.newTabSpec(TAB3_ID).setIndicator("파워TJ").setContent(R.id.tab3));
//		tabHost.addTab(tabHost.newTabSpec(TAB4_ID).setIndicator("SOS").setContent(R.id.tab4));
//		
//		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
//			
//			@Override
//			public void onTabChanged(String tabId) {
//				if(tabId.equals(TAB1_ID)){
//					
//					getChildFragmentManager().beginTransaction().add(R.id.tab_container, new TabMainContent()).commit();
//					
//				}else if (tabId.equals(TAB2_ID)){
//					
//					getChildFragmentManager().beginTransaction().add(R.id.tab_container, new TabLocation()).commit();
//					
//				} else if (tabId.equals(TAB3_ID)) {
//					
//				} else if (tabId.equals(TAB4_ID)) {
//					Intent i = new Intent(getActivity(), VoiceMsgMap.class);
//					startActivity(i);
//					tabHost.setCurrentTabByTag(TAB1_ID);
//				}
//			}
//		});


	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.f2_menu, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.f2_menu) {
			Toast.makeText(getActivity(), "f2 menu", Toast.LENGTH_SHORT).show();
			return true;
		} else if (item.getItemId() == R.id.f3_menu) {
			Intent intent = new Intent(getActivity(),GoogleMapActivity.class);
			intent.putExtra("latitude",PropertyManager.getInstance().getLatitude());   
			intent.putExtra("longitude",PropertyManager.getInstance().getLongitude()); 
			startActivity(intent);
			Toast.makeText(getActivity(), "f3 menu", Toast.LENGTH_SHORT).show();
			return true;
		}
 		return super.onOptionsItemSelected(item);
	}
	
	
	
}

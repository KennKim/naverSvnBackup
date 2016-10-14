package com.travelradio.main;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

import com.travelradio.record.RecordUpload;
import com.travelradio.record.RequestSOS;
import com.travelradio.record.PowerTJ;
import com.travelradio.record.MyLocationList;
import com.travelradio.record.RecordList;
import com.travelradio.voice.VoiceMap;


public class MainActivity extends ActionBarActivity {
	
	private static final String TAB1_ID = "tab1";
	private static final String TAB2_ID = "tab2";
	private static final String TAB3_ID = "tab3";
	private static final String TAB4_ID = "tab4";
	private static final String TAB5_ID = "tab5";
	FragmentTabHost tabHost;
	android.support.v7.app.ActionBar actionBar;
	
	public static final int MESSAGE_BACK_PRESSED_TIME_OUT = 0;
	public static final int TIMEOUT_BACK_PRESSED = 2000;
	boolean isBackPressed = false;

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_BACK_PRESSED_TIME_OUT:
				isBackPressed = false;
				break;
			}
		}
	};

	@Override
	public void onBackPressed() {
		if (!isBackPressed) {
			isBackPressed = true;
			Toast.makeText(this, "one more back press", Toast.LENGTH_SHORT)
					.show();
			mHandler.sendMessageDelayed(
					mHandler.obtainMessage(MESSAGE_BACK_PRESSED_TIME_OUT),
					TIMEOUT_BACK_PRESSED);
		} else {
			mHandler.removeMessages(MESSAGE_BACK_PRESSED_TIME_OUT);
			super.onBackPressed();
		}
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		actionBar = ((ActionBarActivity)this).getSupportActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#cc333333")));

		
		
		
		
		tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		tabHost.addTab(tabHost.newTabSpec(TAB1_ID).setIndicator("权",getResources().getDrawable(R.drawable.delete_btn_search)), RecordList.class, null);
		tabHost.addTab(tabHost.newTabSpec(TAB2_ID).setIndicator("郴 瘤开"), MyLocationList.class, null);
		tabHost.addTab(tabHost.newTabSpec(TAB3_ID).setIndicator("颇况TJ"), PowerTJ.class, null);
		tabHost.addTab(tabHost.newTabSpec(TAB4_ID).setIndicator("公傈"), VoiceMap.class, null);
		tabHost.addTab(tabHost.newTabSpec(TAB5_ID).setIndicator("S.O.S"), RequestSOS.class, null);
		
		
		tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#55111111"));
		
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				if(tabId.equals(TAB1_ID)) {
					tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#111111"));
					
					
				} else if (tabId.equals(TAB2_ID)) {
					tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#55111111"));

					
				} else if (tabId.equals(TAB3_ID)) {
					tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#55111111"));

					
				} else if (tabId.equals(TAB4_ID)) {
					Intent intent = new Intent(MainActivity.this,VoiceMap.class);
					startActivity(intent);
					
				} else if (tabId.equals(TAB5_ID)) {
					
					
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

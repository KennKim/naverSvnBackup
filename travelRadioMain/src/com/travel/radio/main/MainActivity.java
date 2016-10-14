package com.travel.radio.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.travel.radio.alarm.AlarmSetting;
import com.travel.radio.blacklist.BlackList;
import com.travel.radio.following.FollowingList;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.member.data.MyUserResultList;
import com.travel.radio.member.info.MemberInfo;
import com.travel.radio.member.join.MemberJoin;
import com.travel.radio.member.login.MemberLogin;
import com.travel.radio.member.login.MemberModify;
import com.travel.radio.member.pwsearch.MemberPWsearch;
import com.travel.radio.menu.notice.Notice;
import com.travel.radio.messagebox.MessageBox;
import com.travel.radio.mybooth.MyBoothMain;
import com.travel.radio.record.TabMain;
import com.travel.radio.record.upload.RecordUpload;
import com.travel.radio.version.VersionInfo;
import com.travel.radio.voice.MessageSendMachine;
import com.travel.radio.voice.VoiceRecordMachine;
import com.travel.radio.voice.googlemap.GoogleMapActivity;
import com.travel.radio.voice.memberlist.VoiceMemberList;
import com.travel.radio.voicebox.VoiceMsgBox;



public class MainActivity extends SlidingFragmentActivity implements
		MenuFragment.MenuClickListener {
	
	////////////////////////// SPLASH ////////////////////////
	////////////////////////// SPLASH ////////////////////////
	////////////////////////// SPLASH ////////////////////////
	
	public static final int MESSAGE_BACK_PRESSED_TIME_OUT = 0;
	public static final int TIMEOUT_BACK_PRESSED = 2000;
	boolean isBackPressed = true;
	
	
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
			Toast.makeText(this, "one more press", Toast.LENGTH_SHORT).show();
			mHandler.sendMessageDelayed(
					mHandler.obtainMessage(MESSAGE_BACK_PRESSED_TIME_OUT),
					TIMEOUT_BACK_PRESSED);
		} else {
			mHandler.removeMessages(MESSAGE_BACK_PRESSED_TIME_OUT);
			super.onBackPressed();
		}
	}
	
	//////////////////// Slide Menu /////////////////////
	//////////////////// Slide Menu /////////////////////
	//////////////////// Slide Menu /////////////////////

	private static final String TAG_MENU_MAIN = "menu";
	private static final String TAG_MENU_ONE = "one";
	private static final String TAG_MENU_TWO = "two";
	private static final String TAG_MENU_THREE = "three";
	private static final String TAG_MENU_FOUR = "four";
	private static final String TAG_MENU_FIVE = "five";
	private static final String TAG_MENU_SIX = "six";
	private static final String TAG_MENU_SEVEN = "seven";
	private static final String TAG_MENU_EIGHT = "eight";
	private static final String TAG_MENU_NINE = "nine";
	private static final String TAG_MENU_TEN = "ten";
	private static final String TAG_OVER1 = "1";
	private static final String TAG_OVER2 = "2";
	private static final String TAG_OVER3 = "3";
	private static final String TAG_OVER4 = "4";

	SlidingMenu sm;

	ImageView dimImage;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.menu_frame);
		dimImage = (ImageView)findViewById(R.id.image_dim);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new TabMain(), TAG_MENU_MAIN)
					.commit();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.menu_container, new MenuFragment()).commit();
		}

		sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT);
//		sm.setMode(SlidingMenu.LEFT_RIGHT);
//		sm.setSecondaryMenu(R.layout.menu_second);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
//		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		sm.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
			
			@Override
			public void onOpened() {
				dimImage.setVisibility(View.VISIBLE);
			}
		});
		sm.setOnCloseListener(new SlidingMenu.OnCloseListener() {
			
			@Override
			public void onClose() {
				dimImage.setVisibility(View.GONE);
			}
		});
		
		///////////////// ActionBar Setting //////////////////
		///////////////// ActionBar Setting //////////////////
		///////////////// ActionBar Setting //////////////////
		
		getSupportActionBar().setHomeButtonEnabled(true);
		
		

		
	
	}


	@Override
	public void selectMenu(int menu) {
		switch (menu) {
		case MenuFragment.MENU_MAIN:

			emptyBackStack();
//			Fragment f = getSupportFragmentManager().findFragmentByTag(
//					TAG_MENU_MAIN);
//			if (f == null) {
//				getSupportFragmentManager()
//						.beginTransaction()
//						.replace(R.id.container, new MainFragment(),
//								TAG_MENU_MAIN).commit();
//			}
			break;
		case MenuFragment.MENU_ONE:
			Fragment f1 = getSupportFragmentManager().findFragmentByTag(TAG_MENU_ONE);
			if (f1 == null) {
				emptyBackStack();
				if(PropertyManager.getInstance().getUserId()==null || PropertyManager.getInstance().getUserId().equals("")){
					Toast.makeText(getApplicationContext(), "로그인을 해주세요", Toast.LENGTH_SHORT);
					getSupportFragmentManager().beginTransaction().replace(R.id.container, new MemberLogin(),TAG_MENU_EIGHT).addToBackStack(null).commit();
				}else{
					getSupportFragmentManager().beginTransaction().replace(R.id.container, new MyBoothMain(),TAG_MENU_ONE).addToBackStack(null).commit();
				}
			}
			break;
		case MenuFragment.MENU_TWO:
			Fragment f2 = getSupportFragmentManager().findFragmentByTag(TAG_MENU_TWO);
			if (f2 == null) {
				emptyBackStack();
				if(PropertyManager.getInstance().getUserId()==null || PropertyManager.getInstance().getUserId().equals("")){
					Toast.makeText(getApplicationContext(), "로그인을 해주세요", Toast.LENGTH_SHORT);
					getSupportFragmentManager().beginTransaction().replace(R.id.container, new MemberLogin(),TAG_MENU_EIGHT).addToBackStack(null).commit();
				}else{
					getSupportFragmentManager().beginTransaction().replace(R.id.container, new FollowingList(),TAG_MENU_TWO).addToBackStack(null).commit();
				}
			}
			break;
		case MenuFragment.MENU_THREE:
			Fragment f3 = getSupportFragmentManager().findFragmentByTag(TAG_MENU_THREE);
			if (f3 == null) {
				emptyBackStack();
				if(PropertyManager.getInstance().getUserId()==null || PropertyManager.getInstance().getUserId().equals("")){
					Toast.makeText(getApplicationContext(), "로그인을 해주세요", Toast.LENGTH_SHORT);
					getSupportFragmentManager().beginTransaction().replace(R.id.container, new MemberLogin(),TAG_MENU_EIGHT).addToBackStack(null).commit();
				}else{
					getSupportFragmentManager().beginTransaction().replace(R.id.container, new VoiceMsgBox(),TAG_MENU_THREE).addToBackStack(null).commit();
				}
			}
			break;
		case MenuFragment.MENU_FOUR:
			Fragment f4 = getSupportFragmentManager().findFragmentByTag(TAG_MENU_FOUR);
			if (f4 == null) {
				emptyBackStack();
				if(PropertyManager.getInstance().getUserId()==null || PropertyManager.getInstance().getUserId().equals("")){
					Toast.makeText(getApplicationContext(), "로그인을 해주세요", Toast.LENGTH_SHORT);
					getSupportFragmentManager().beginTransaction().replace(R.id.container, new MemberLogin(),TAG_MENU_EIGHT).addToBackStack(null).commit();
				}else{
					getSupportFragmentManager().beginTransaction().replace(R.id.container, new MessageBox(),TAG_MENU_FOUR).addToBackStack(null).commit();
				}
			}
			break;
		case MenuFragment.MENU_FIVE:
			
			Intent intent = new Intent(MainActivity.this,GoogleMapActivity.class);
			intent.putExtra("latitude",PropertyManager.getInstance().getLatitude());   
			intent.putExtra("longitude",PropertyManager.getInstance().getLongitude());   
			startActivity(intent);
			
//			Fragment f5 = getSupportFragmentManager().findFragmentByTag(TAG_MENU_FIVE);
//			if (f5 == null) {
//				
//				emptyBackStack();
//				getSupportFragmentManager().beginTransaction().replace(R.id.container, new LocationSetting(),TAG_MENU_FIVE).addToBackStack(null).commit();
//			}
			break;
//		case MenuFragment.MENU_SIX:
//			Fragment f6 = getSupportFragmentManager().findFragmentByTag(TAG_MENU_SIX);
//			if (f6 == null) {
//				emptyBackStack();
//				getSupportFragmentManager().beginTransaction().replace(R.id.container, new AlarmSetting(),TAG_MENU_SIX).addToBackStack(null).commit();
//			}
//			break;
		case MenuFragment.MENU_SIX:
			Fragment f7 = getSupportFragmentManager().findFragmentByTag(TAG_MENU_SEVEN);
			if (f7 == null) {
				emptyBackStack();
				if(PropertyManager.getInstance().getUserId()==null || PropertyManager.getInstance().getUserId().equals("")){
					Toast.makeText(getApplicationContext(), "로그인을 해주세요", Toast.LENGTH_SHORT);
					getSupportFragmentManager().beginTransaction().replace(R.id.container, new MemberLogin(),TAG_MENU_EIGHT).addToBackStack(null).commit();
				}else{
					getSupportFragmentManager().beginTransaction().replace(R.id.container, new BlackList(),TAG_MENU_SEVEN).addToBackStack(null).commit();
				}
			}
			break;
		case MenuFragment.MENU_SEVEN:
			Fragment f8 = getSupportFragmentManager().findFragmentByTag(TAG_MENU_EIGHT);
			if (f8 == null) {
				emptyBackStack();
				if(PropertyManager.getInstance().getUserId()==null || PropertyManager.getInstance().getUserId().equals("")){
					Toast.makeText(getApplicationContext(), "로그인을 해주세요", Toast.LENGTH_SHORT);
					getSupportFragmentManager().beginTransaction().replace(R.id.container, new MemberLogin(),TAG_MENU_EIGHT).addToBackStack(null).commit();
				}else{
					getSupportFragmentManager().beginTransaction().replace(R.id.container, new MemberModify(),TAG_MENU_EIGHT).addToBackStack(null).commit();
				}
			}
			break;
//		case MenuFragment.MENU_NINE:
//			Fragment f9 = getSupportFragmentManager().findFragmentByTag(TAG_MENU_NINE);
//			if (f9 == null) {
//				emptyBackStack();
//				getSupportFragmentManager().beginTransaction().replace(R.id.container, new Notice(),TAG_MENU_NINE).addToBackStack(null).commit();
//			}
//			break;
		case MenuFragment.MENU_EIGHT:
			Fragment f10 = getSupportFragmentManager().findFragmentByTag(TAG_MENU_TEN);
			if (f10 == null) {
				emptyBackStack();
				getSupportFragmentManager().beginTransaction().replace(R.id.container, new VersionInfo(),TAG_MENU_TEN).addToBackStack(null).commit();
			}
			break;
		case MenuFragment.MEMBER_JOIN:
			Fragment f20 = getSupportFragmentManager().findFragmentByTag(TAG_OVER1);
			if (f20 == null) {
				emptyBackStack();
				getSupportFragmentManager().beginTransaction().replace(R.id.container, new MemberJoin(),TAG_OVER1).addToBackStack(null).commit();
			}
			break;
		case MenuFragment.MEMBER_MODIFY:
			Fragment f21 = getSupportFragmentManager().findFragmentByTag(TAG_OVER2);
			if (f21 == null) {
				emptyBackStack();
				getSupportFragmentManager().beginTransaction().replace(R.id.container, new MemberModify(),TAG_OVER2).addToBackStack(null).commit();
			}
			break;
		case MenuFragment.MEMBER_SEARCH:
			Fragment f22 = getSupportFragmentManager().findFragmentByTag(TAG_OVER3);
			if (f22 == null) {
				emptyBackStack();
				getSupportFragmentManager().beginTransaction().replace(R.id.container, new MemberPWsearch(),TAG_OVER3).addToBackStack(null).commit();
			}
			break;
		case MenuFragment.MEMBER_LOGIN:
			Fragment f23 = getSupportFragmentManager().findFragmentByTag(TAG_OVER4);
			if (f23 == null) {
				emptyBackStack();
				getSupportFragmentManager().beginTransaction().replace(R.id.container, new MemberLogin(),TAG_OVER4).addToBackStack(null).commit();
			}
			break;
		}
		hideMenu();
	}
	
	private void emptyBackStack() {
		while(getSupportFragmentManager().getBackStackEntryCount() > 0) {
			getSupportFragmentManager().popBackStackImmediate();
		}
	}

	Handler sHandler = new Handler();
	public void hideMenu() {
		sHandler.postDelayed(hideMenuRunnable, 100);
	}

	Runnable hideMenuRunnable = new Runnable() {
		@Override
		public void run() {
			showContent();
		}
	};
	
	//////////////////////// Fragment Transfer ////////////////////////////
	public void change(Fragment fragment) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.container, fragment);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	
	/////////////////////// Option MENU //////////////////////////
	/////////////////////// Option MENU //////////////////////////
	/////////////////////// Option MENU //////////////////////////
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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
		} else if (id == android.R.id.home) {
			toggle();
			return false;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}

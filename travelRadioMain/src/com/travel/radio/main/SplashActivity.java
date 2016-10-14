package com.travel.radio.main;

import java.io.IOException;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.member.data.MyUserResultList;

public class SplashActivity extends Activity {

	public static final int MESSAGE_MOVE_ACTIVITY = 0;
	public static final int TIMEOUT_ACTIVITY = 2000;
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	private static final String SENDER_ID = "512512369050";

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_MOVE_ACTIVITY:

				String email = PropertyManager.getInstance().getEmail();
				String password = PropertyManager.getInstance().getPassword();

				if (!email.equals("")) {
					NetworkManager.getInstance().userLogin(getApplicationContext(), email, password,new OnResultListener<MyUserResultList>() {

								@Override
								public void onSuccess(MyUserResultList result) {
									Toast.makeText(SplashActivity.this, " 자동로그인 완료  ", Toast.LENGTH_SHORT).show();
									PropertyManager.getInstance().setUserId(result.result.myUser.id);
									PropertyManager.getInstance().setName(result.result.myUser.name);
									PropertyManager.getInstance().setImg(result.result.myUser.img);
									PropertyManager.getInstance().setBestUser(result.result.myUser.bestUser);
									
									String latitude=result.result.myUser.latitude;
									String longitude=result.result.myUser.longitude;
									String userPlace=result.result.myUser.userPlace;

									if(!latitude.equals("")||latitude!=null){
										PropertyManager.getInstance().setLatitude(result.result.myUser.latitude);
									}
									if(!latitude.equals("")||latitude!=null){
										PropertyManager.getInstance().setLongitude(result.result.myUser.longitude);
									}
									if(!latitude.equals("")||latitude!=null){
										PropertyManager.getInstance().setUserPlace(result.result.myUser.userPlace);
									}
//									Toast.makeText(SplashActivity.this, " lat : " + PropertyManager.getInstance().getLatitude() + " lon : " + PropertyManager.getInstance().getLongitude() + " place : " +  PropertyManager.getInstance().getUserPlace(), Toast.LENGTH_LONG).show();
//									PropertyManager.getInstance().setUserPlace(result.result.myUser.userPlace);
								}

								@Override
								public void onFail(int code) {
									Toast.makeText(SplashActivity.this, "autoLogin_fail", Toast.LENGTH_SHORT).show();
								}
							});
				}

				Intent i = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(i);
				finish();
				break;
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_splash);
		
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (checkPlayServices()) {
			String regId = PropertyManager.getInstance().getRegistrationId();
			if (!regId.equals("")) {
				runOnUiThread(nextAction);
			} else {
				registerInBackground();
			}
		} 
	}

	@Override
	protected void onPause() {
		super.onPause();
		mHandler.removeMessages(MESSAGE_MOVE_ACTIVITY);
	}

	Runnable nextAction = new Runnable() {

		@Override
		public void run() {
			mHandler.sendMessageDelayed(
					mHandler.obtainMessage(MESSAGE_MOVE_ACTIVITY), TIMEOUT_ACTIVITY);
		}
	};

	GoogleCloudMessaging gcm;

	private void registerInBackground() {
		new AsyncTask<Void, Integer, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String msg = "";
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging
								.getInstance(SplashActivity.this);
					}
					String regid = gcm.register(SENDER_ID);

					PropertyManager.getInstance().setRegistrationId(regid);

				} catch (IOException ex) {
				}
				return msg;
			}

			@Override
			protected void onPostExecute(String msg) {
				runOnUiThread(nextAction);
			}
		}.execute(null, null, null);
	}

	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
						resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST);
				dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

					@Override
					public void onCancel(DialogInterface dialog) {

					}
				});
				dialog.show();
			} else {
				// To Do...
				finish();
			}
			return false;
		}
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

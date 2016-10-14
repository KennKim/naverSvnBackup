package com.travel.radio.voice.memberlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.R;
import com.travel.radio.member.data.MyUser;
import com.travel.radio.member.data.MyUserResultList;
import com.travel.radio.record.content.MyPagerAdapter;
import com.travel.radio.voice.MessageSendMachine;
import com.travel.radio.voice.VoiceRecordMachine;
import com.travel.radio.voice.memberlist.AdapterVoiceMemList.OnAdapterItemClickListener;

public class VoiceMemberList extends ActionBarActivity {
	
	private final static int REQUEST_CODE_MY_ACTIVITY = 0;
	
	public static final String PARAM_LATITUDE = "latitude";
	public static final String PARAM_LONGITUDE = "longitude";
	public static final String PARAM_RADIUS = "radius";


	ListView listView;
	AdapterVoiceMemList mAdapter;
	
	
	String latitude;
	String longitude;
	String radius;
	String page="1";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voice_member_list);
		
		Intent intent = getIntent();
		latitude = intent.getStringExtra(PARAM_LATITUDE);
		longitude = intent.getStringExtra(PARAM_LONGITUDE);
		radius = intent.getStringExtra(PARAM_RADIUS);
		
		
		listView = (ListView) findViewById(R.id.listView1);
		listView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,	int position, long id) {
				
			}
		});
		mAdapter = new AdapterVoiceMemList(VoiceMemberList.this);
		mAdapter.setOnAdapterItemClickListener(new OnAdapterItemClickListener() {

			@Override
			public void onAdapterFaceItemClick(View v, MyUser data) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAdapterVoiceItemClick(View v, MyUser data) {
				Intent intent = new Intent(VoiceMemberList.this,VoiceRecordMachine.class);
				startActivity(intent);					
			}

			@Override
			public void onAdapterMsgItemClick(View v, MyUser data) {
				Intent intent = new Intent(VoiceMemberList.this,MessageSendMachine.class);
//				startActivityForResult(intent, REQUEST_CODE_MY_ACTIVITY);
				intent.putExtra("userId", data.id);
				startActivity(intent);						
			}
			
		});
		
		listView.setAdapter(mAdapter);
		
		initData();
		
		
	}
	
	private void initData() {


		NetworkManager.getInstance().getMapMembers(getApplicationContext(), latitude, longitude, radius, page, new OnResultListener<MyUserResultList>() {
			
			@Override
			public void onSuccess(MyUserResultList result) {
				if(result.success == 1){
					Toast.makeText(VoiceMemberList.this, result.result.msg, Toast.LENGTH_SHORT).show();
					mAdapter.add(result.result.myUsers);
				}else{
					Toast.makeText(VoiceMemberList.this, result.result.msg, Toast.LENGTH_SHORT).show();
				}				
			}
			
			@Override
			public void onFail(int code) {
				Toast.makeText(VoiceMemberList.this, "mapMemList_onfail", Toast.LENGTH_SHORT).show();
			}
		});
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_MY_ACTIVITY && resultCode == Activity.RESULT_OK) {
//			String result = data.getStringExtra(MyActivity.PARAM_RESULT);
//			messageView.setText(result);
		}
		
	}
}

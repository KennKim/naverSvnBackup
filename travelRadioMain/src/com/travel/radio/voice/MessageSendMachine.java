package com.travel.radio.voice;

import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.travel.radio.main.MainActivity;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.R;
import com.travel.radio.voice.data.VoiceResultList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MessageSendMachine extends ActionBarActivity {
	
	
	EditText etMessage;
	
	String receiveId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_machine);
		
		getSupportActionBar().show();
		getSupportActionBar().setTitle("메세지보내기");
		getSupportActionBar().setIcon(R.drawable.actionbar_radio);
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		getSupportActionBar().setHomeButtonEnabled(false);
		
		
		Intent intent = getIntent();  

		receiveId=intent.getExtras().getString("receiveId");

		
		etMessage=(EditText)findViewById(R.id.editText1message);
		
		Button btn=(Button)findViewById(R.id.button1send);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String message = etMessage.getText().toString();
				String voice=null;
				
				NetworkManager.getInstance().setVoiceMsgSend(getApplicationContext(), message, voice, receiveId, new OnResultListener<VoiceResultList>() {
					
					@Override
					public void onSuccess(VoiceResultList result) {
						if(result.success==1){
							Toast.makeText(MessageSendMachine.this, result.result.msg, Toast.LENGTH_LONG).show();
							finish();
						}else{
							Toast.makeText(MessageSendMachine.this, result.result.msg, Toast.LENGTH_SHORT).show();
							finish();
						}						
					}
					
					@Override
					public void onFail(int code) {
						Toast.makeText(MessageSendMachine.this, "messageSend_onfail", Toast.LENGTH_SHORT).show();
						
					}
				});
				
			}
		});
		
	}
}

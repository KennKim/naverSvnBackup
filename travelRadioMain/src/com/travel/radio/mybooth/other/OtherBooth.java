package com.travel.radio.mybooth.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.radio.main.MainActivity;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.R;
import com.travel.radio.member.data.MyUserResultList;
import com.travel.radio.mybooth.TabMyboothView1;
import com.travel.radio.mybooth.TabMyboothView2;
import com.travel.radio.mybooth.TabMyboothView3;
import com.travel.radio.mybooth.myfollowing.MyboothMyfollowing;
import com.travel.radio.mybooth.myrecord.MyboothMyrecord;
import com.travel.radio.mybooth.myscrap.MyboothMyScrap;
import com.travel.radio.record.content.RecordContent;
import com.travel.radio.voice.MessageSendMachine;
import com.travel.radio.voice.VoiceRecordMachine;
import com.travel.radio.voice.VoiceRecordMachine2;

public class OtherBooth extends Fragment {

	private static final String TAB1_ID = "tab1";
	private static final String TAB2_ID = "tab2";
	private static final String TAB3_ID = "tab3";

	FragmentTabHost tabHost;
	ActionBar actionBar;
	TabWidget tabs;

	
	String userId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		if (b != null) {
			userId = b.getString("userId");
		}
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		TabMyboothView1 view1 = new TabMyboothView1(getActivity());
		TabMyboothView2 view2 = new TabMyboothView2(getActivity());
		TabMyboothView3 view3 = new TabMyboothView3(getActivity());
		

		View view = inflater.inflate(R.layout.mybooth_main_anonymous,container, false);
		
		tabs=(TabWidget)view.findViewById(android.R.id.tabs);
		tabs.setBackgroundResource(R.drawable.tab_sub_one);
		
		Bundle b = new Bundle();
		b.putString("userId", userId);
		
		tabHost = (FragmentTabHost) view.findViewById(R.id.tabhost_mybooth);
		tabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent_mybooth);
		tabHost.addTab(tabHost.newTabSpec(TAB1_ID).setIndicator(view1),MyboothMyrecord.class, b);
		tabHost.addTab(tabHost.newTabSpec(TAB2_ID).setIndicator(view2),MyboothMyScrap.class, b);
		tabHost.addTab(tabHost.newTabSpec(TAB3_ID).setIndicator(view3),OtherMyfollowing.class, null);
		return view;
	}

	ImageView ivFace;
	TextView tvUserName;

	TextView tvBlock;

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				if (tabId.equals(TAB1_ID)) {
					tabs.setBackgroundResource(R.drawable.tab_sub_one);

				} else if (tabId.equals(TAB2_ID)) {
					tabs.setBackgroundResource(R.drawable.tab_sub_two);

				} else if (tabId.equals(TAB3_ID)) {
					tabs.setBackgroundResource(R.drawable.tab_sub_three);

				}
			}
		});

		tvBlock=(TextView)view.findViewById(R.id.textView1block);
		tvBlock.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				BlockDialogFragment dialog = new BlockDialogFragment();
				
				Bundle b = new Bundle();
				b.putString("userId", userId);
				dialog.setArguments(b);
				
				dialog.show(getChildFragmentManager(), "dialog");
				
			}
		});
		
		ivFace = (ImageView) view.findViewById(R.id.imageView2face);
		
		tvUserName=(TextView)view.findViewById(R.id.textView1username);

		Button btn = (Button) view.findViewById(R.id.button1voice_message);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),VoiceRecordMachine2.class);
				intent.putExtra("receiveId", userId);
				startActivity(intent);
			}
		});

		btn = (Button) view.findViewById(R.id.button2message);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), MessageSendMachine.class);
				intent.putExtra("receiveId", userId);
				startActivity(intent);
			}
		});

		btn = (Button) view.findViewById(R.id.button3following);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				FollowingDialogFragment dialog = new FollowingDialogFragment();
				
				Bundle b = new Bundle();
				b.putString("userId", userId);
				dialog.setArguments(b);
				
				dialog.show(getChildFragmentManager(), "dialog");
				
				
			}
		});
		
		mLoader=ImageLoader.getInstance();
		
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initData();
	}

	ImageLoader mLoader;
	
	private void initData() {
		
		NetworkManager.getInstance().getUserList(getActivity(), userId, new OnResultListener<MyUserResultList>() {
			
			@Override
			public void onSuccess(MyUserResultList result) {

				if(result.success==1){
//					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
					mLoader.displayImage(result.result.myUser.img, ivFace);
					tvUserName.setText(result.result.myUser.name);
				}else{
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
				}
			}
			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "userList_onFail", Toast.LENGTH_SHORT).show();
				
			}
		});
	}
}

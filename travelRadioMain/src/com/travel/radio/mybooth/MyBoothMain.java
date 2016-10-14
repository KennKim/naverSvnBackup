package com.travel.radio.mybooth;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
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
import com.travel.radio.main.MenuFragment;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.PropertyManager;
import com.travel.radio.main.R;
import com.travel.radio.member.data.MyUserResultList;
import com.travel.radio.member.login.MemberModify;
import com.travel.radio.mybooth.myfollowing.MyboothMyfollowing;
import com.travel.radio.mybooth.myrecord.MyboothMyrecord;
import com.travel.radio.mybooth.myscrap.MyboothMyScrap;
import com.travel.radio.record.content.RecordContent;
import com.travel.radio.record.upload.CameraDialogFragment;
import com.travel.radio.voicebox.VoiceMsgBox;

public class MyBoothMain extends Fragment {
	
	private static final String TAB1_ID = "tab1";
	private static final String TAB2_ID = "tab2";
	private static final String TAB3_ID = "tab3";

	FragmentTabHost tabHost;
	ActionBar actionBar;
	TabWidget tabs;
	
	String userId=PropertyManager.getInstance().getUserId();
	
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

		actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		actionBar.setTitle("MyBooth");
		
		TabMyboothView1 view1 = new TabMyboothView1(getActivity());
		TabMyboothView2 view2 = new TabMyboothView2(getActivity());
		TabMyboothView3 view3 = new TabMyboothView3(getActivity());
		
		View view = inflater.inflate(R.layout.side_mybooth_main, container, false);
		tabs=(TabWidget)view.findViewById(android.R.id.tabs);
		tabs.setBackgroundResource(R.drawable.tab_sub_one);
		
		Bundle b = new Bundle();
		b.putString("userId", userId);
		tabHost = (FragmentTabHost) view.findViewById(R.id.tabhost_mybooth);
		tabHost.setup(getActivity(), getChildFragmentManager(),R.id.realtabcontent_mybooth);
		tabHost.addTab(tabHost.newTabSpec(TAB1_ID).setIndicator(view1),MyboothMyrecord.class, b);
		tabHost.addTab(tabHost.newTabSpec(TAB2_ID).setIndicator(view2),MyboothMyScrap.class, b);
		tabHost.addTab(tabHost.newTabSpec(TAB3_ID).setIndicator(view3),MyboothMyfollowing.class, b);
		

		
		return view;
	}
	
	
	ImageView ivFace;
	TextView tvUserName;
	ImageLoader mLoader;
	
	
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
		
		ivFace=(ImageView)view.findViewById(R.id.imageView2face);
		
		Button btn=(Button)view.findViewById(R.id.button2option);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MemberModify memberModify = new MemberModify();
				((MainActivity)getActivity()).change(memberModify);
			}
		});
		
		tvUserName=(TextView)view.findViewById(R.id.textView1username);
		
		
		btn = (Button)view.findViewById(R.id.button1voice_message_box);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				VoiceMsgBox voiceMsgBox = new VoiceMsgBox();
				Bundle b = new Bundle();
				b.putString("userId", userId);
				voiceMsgBox.setArguments(b);
				((MainActivity)getActivity()).selectMenu(MenuFragment.MENU_THREE);
			}
		});
		
		btn = (Button)view.findViewById(R.id.button2message_box);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity)getActivity()).selectMenu(MenuFragment.MENU_FOUR);
				
			}
		});

		
		
		mLoader=ImageLoader.getInstance();
	}
	@Override
	public void onResume() {
		super.onResume();
		initData();
	}
	
	

	private void initData() {
		
		userId=PropertyManager.getInstance().getUserId();
		
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
				Toast.makeText(getActivity(), "userInfo_onFail", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
}

package com.travel.radio.voicebox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabWidget;
import android.widget.TabHost.OnTabChangeListener;

import com.travel.radio.main.MainActivity;
import com.travel.radio.main.PropertyManager;
import com.travel.radio.main.R;
import com.travel.radio.mybooth.TabMyboothView1;
import com.travel.radio.voicebox.receive.ReceivedVoice;
import com.travel.radio.voicebox.saved.SavedVoice;
import com.travel.radio.voicebox.sent.SentVoice;

public class VoiceMsgBox extends Fragment {

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
		actionBar.setTitle("¹«ÀüÇÔ");

		TabVoiceBoxView1 view1 = new TabVoiceBoxView1(getActivity());
		TabVoiceBoxView2 view2 = new TabVoiceBoxView2(getActivity());
		TabVoiceBoxView3 view3 = new TabVoiceBoxView3(getActivity());
		
		View view = inflater.inflate(R.layout.side_voicebox, container, false);

		tabs=(TabWidget)view.findViewById(android.R.id.tabs);
		tabs.setBackgroundResource(R.drawable.tab_sub_one);
		
		Bundle b = new Bundle();
		b.putString("userId", userId);
		
		tabHost = (FragmentTabHost) view.findViewById(R.id.tabhost);
		tabHost.setup(getActivity(), getChildFragmentManager(),
				R.id.voicebox_realtabcontent);
		tabHost.addTab(tabHost.newTabSpec(TAB1_ID).setIndicator(view1),
				ReceivedVoice.class, b);
		tabHost.addTab(tabHost.newTabSpec(TAB2_ID).setIndicator(view2),
				SentVoice.class, b);
		tabHost.addTab(tabHost.newTabSpec(TAB3_ID).setIndicator(view3),
				SavedVoice.class, b);
		return view;
	}

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

	}
}

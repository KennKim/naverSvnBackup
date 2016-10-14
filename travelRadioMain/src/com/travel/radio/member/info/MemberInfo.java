package com.travel.radio.member.info;


import com.travel.radio.main.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MemberInfo extends Fragment {

	ActionBar actionBar;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		actionBar.setTitle("회원정보");
		
		return inflater.inflate(R.layout.side_member_info, container, false);
	}
}

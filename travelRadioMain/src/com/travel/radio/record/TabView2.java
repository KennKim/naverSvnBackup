package com.travel.radio.record;

import com.travel.radio.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TabView2 extends FrameLayout {

	public TabView2(Context context) {
		super(context);
		init();
	}

	TextView tvTitle;

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.tab_tabview1, this);

		tvTitle = (TextView) findViewById(R.id.textView1);
		tvTitle.setText("나의\n채널");
	}
}

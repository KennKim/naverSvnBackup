package com.travel.radio.record;

import com.travel.radio.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TabView3 extends FrameLayout {

	public TabView3(Context context) {
		super(context);
		init();
	}

	TextView tvTitle;

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.tab_tabview1, this);

		tvTitle = (TextView) findViewById(R.id.textView1);
		tvTitle.setText("ÆÄ¿ö\n T J");
	}
}

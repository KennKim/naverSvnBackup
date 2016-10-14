package com.example.uiiwant.frag1;

import com.example.uiiwant.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class HeaderViewListTitle extends FrameLayout{
	
	TextView textView;

	public HeaderViewListTitle(Context context) {
		super(context);
		LayoutInflater.from(getContext()).inflate(R.layout.fr_1_header_list_title, this);
		
		textView=(TextView)findViewById(R.id.textView1);
		
	}

}

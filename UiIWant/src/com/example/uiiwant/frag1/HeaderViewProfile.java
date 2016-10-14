package com.example.uiiwant.frag1;

import com.example.uiiwant.R;
import com.example.uiiwant.frag1.data.FrOneItemData;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class HeaderViewProfile extends FrameLayout{
	
	public ImageView userImg;
	public TextView userName;
	public TextView userComment;
	public FrOneItemData data;
	

	public HeaderViewProfile(Context context) {
		super(context);
		LayoutInflater.from(getContext()).inflate(R.layout.fr_1_header_profile, this);
		
		userImg=(ImageView)findViewById(R.id.imageView1);
		userName=(TextView)findViewById(R.id.textView1);
		userComment=(TextView)findViewById(R.id.textView2);
		
		setData();
		
	}
	
	public void setData(){
		
		userImg.setImageResource(R.drawable.fuckyou);
		userName.setText("My name");
		userComment.setText("My comment");
		
	}

}

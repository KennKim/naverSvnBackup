package com.example.uiiwant.frag1.view;

import com.example.uiiwant.R;
import com.example.uiiwant.frag1.data.FrOneItemData;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class FrOneItemView extends FrameLayout{

	public FrOneItemView(Context context) {
		super(context);
		init();
	}
	
	ImageView iv;
	TextView userName;
	TextView userComment;
	FrOneItemData data;

	private void init(){
		
		LayoutInflater.from(getContext()).inflate(R.layout.fr_1_header_profile, this);
		
		iv=(ImageView)findViewById(R.id.imageView1);
		userName=(TextView)findViewById(R.id.textView1);
		userComment=(TextView)findViewById(R.id.textView2);
		
		
	}
	
	public void setData(FrOneItemData data){
		
		this.data=data;
		
		iv.setImageResource(data.userImg);
		userName.setText(data.userName);
		userComment.setText(data.userComment);
		
	}
}

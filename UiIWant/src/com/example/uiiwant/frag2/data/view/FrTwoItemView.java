package com.example.uiiwant.frag2.data.view;

import com.example.uiiwant.R;
import com.example.uiiwant.frag2.data.FrTwoItemData;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class FrTwoItemView extends FrameLayout{

	public FrTwoItemView(Context context) {
		super(context);
		init();
	}
	
	ImageView iv;
	TextView chatTitle;
	TextView lastTime;
	TextView lastTalk;
	FrTwoItemData data;
	
	public void init(){
		
		LayoutInflater.from(getContext()).inflate(R.layout.fr_2_item_view, this);
		iv=(ImageView)findViewById(R.id.imageView1);
		chatTitle=(TextView)findViewById(R.id.textView1);
		lastTalk=(TextView)findViewById(R.id.textView2);
		lastTime=(TextView)findViewById(R.id.textView3);
		
	}
	
	public void setData(FrTwoItemData data){
		
		this.data=data;
		iv.setImageResource(data.chatPhoto);
		chatTitle.setText(data.chatTitle);
		lastTime.setText(data.lastTime);
		lastTalk.setText(data.lastTalk);
		
	}

}

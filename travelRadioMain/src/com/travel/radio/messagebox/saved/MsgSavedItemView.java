package com.travel.radio.messagebox.saved;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.radio.main.R;
import com.travel.radio.record.tab.main.RecordItemView;
import com.travel.radio.voice.data.Voice;


public class MsgSavedItemView extends FrameLayout {

	public interface OnPlayClickListener {
		public void onFaceClick(MsgSavedItemView view, Voice data);
		public void onDelClick(MsgSavedItemView view, Voice data);
		public void onReplyClick(MsgSavedItemView view, Voice data);
	}
	
	OnPlayClickListener mListener;
	public void setOnPlayClickListener(OnPlayClickListener listener) {
		mListener = listener;
	}
	
	public MsgSavedItemView(Context context) {
		super(context);
		init();
	}

	ImageView ivFace;
	TextView tvName;
	TextView tvDate;
	CheckBox cbVoice;
	TextView tvContent;
	
	Voice data;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.voice_list_item_msg_saved, this);
		
		ivFace = (ImageView)findViewById(R.id.imageView1face);
		ivFace.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFaceClick(MsgSavedItemView.this, data);
				}
			}
		});
		
		tvName = (TextView)findViewById(R.id.textView1user_name);
		tvName.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFaceClick(MsgSavedItemView.this, data);
				}
			}
		});
		
		tvDate = (TextView)findViewById(R.id.textView2date);
		
		Button btn=(Button)findViewById(R.id.button1del);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onDelClick(MsgSavedItemView.this, data);
				}	
			}
		});
		
		btn=(Button)findViewById(R.id.button3reply);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onReplyClick(MsgSavedItemView.this, data);
				}	
			}
		});
		
		
		tvContent = (TextView)findViewById(R.id.textView_content);
		
		mLoader = ImageLoader.getInstance();
		
	}
	
	ImageLoader mLoader;
	
	public void setData(Voice data) {
		this.data = data;
		
		tvName.setText(data.name);
		tvDate.setText(data.cdate);
		tvContent.setText(data.content);
		mLoader.displayImage(data.img, ivFace);
		
		
	}

}

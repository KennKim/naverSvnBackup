package com.travel.radio.voicebox.receive;

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
import com.travel.radio.voice.data.Voice;
import com.travel.radio.voicebox.saved.SavedItemView;


public class ReceiveItemView extends FrameLayout {

	public interface OnPlayClickListener {
		public void onPlayClick(ReceiveItemView view, Voice data);
		public void onFaceClick(ReceiveItemView view, Voice data);
		public void onDelClick(ReceiveItemView view, Voice data);
		public void onSavedClick(ReceiveItemView view, Voice data);
		public void onReplyClick(ReceiveItemView view, Voice data);
	}
	
	OnPlayClickListener mListener;
	public void setOnPlayClickListener(OnPlayClickListener listener) {
		mListener = listener;
	}
	
	public ReceiveItemView(Context context) {
		super(context);
		init();
	}

	ImageView ivFace;
	TextView tvName;
	TextView tvDate;
	CheckBox cbVoice;
	
	Voice data;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.voice_list_item, this);
		
		ivFace = (ImageView)findViewById(R.id.imageView1face);
		ivFace.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFaceClick(ReceiveItemView.this, data);
				}
			}
		});
		
		tvName = (TextView)findViewById(R.id.textView1user_name);
		tvName.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFaceClick(ReceiveItemView.this, data);
				}
			}
		});
		tvDate = (TextView)findViewById(R.id.textView2date);
		

		Button btn=(Button)findViewById(R.id.button1del);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onDelClick(ReceiveItemView.this, data);
				}	
			}
		});
		
		
		btn=(Button)findViewById(R.id.button2saved);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onSavedClick(ReceiveItemView.this, data);
				}					
			}
		});
		
		btn=(Button)findViewById(R.id.button3reply);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onReplyClick(ReceiveItemView.this, data);
				}					
			}
		});
		
		btn=(Button)findViewById(R.id.button1play);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onPlayClick(ReceiveItemView.this, data);
				}				
			}
		});
		
		
		mLoader = ImageLoader.getInstance();
		
	}
	
	ImageLoader mLoader;
	
	public void setData(Voice data) {
		this.data = data;
		
		tvName.setText(data.name);
		tvDate.setText(data.cdate);
		mLoader.displayImage(data.img, ivFace);
		
		
	}

}

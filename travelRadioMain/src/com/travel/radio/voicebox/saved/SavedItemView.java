package com.travel.radio.voicebox.saved;

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
import com.travel.radio.messagebox.saved.MsgSavedItemView;
import com.travel.radio.voice.data.Voice;


public class SavedItemView extends FrameLayout {

	public interface OnPlayClickListener {
		public void onPlayClick(SavedItemView view, Voice data);
		public void onFaceClick(SavedItemView view, Voice data);
		public void onDelClick(SavedItemView view, Voice data);
		public void onReplyClick(SavedItemView view, Voice data);
	}
	
	OnPlayClickListener mListener;
	public void setOnPlayClickListener(OnPlayClickListener listener) {
		mListener = listener;
	}
	
	public SavedItemView(Context context) {
		super(context);
		init();
	}

	ImageView ivFace;
	TextView tvName;
	TextView tvDate;
	CheckBox cbVoice;
	
	Voice data;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.voice_list_item_saved, this);
		
		ivFace = (ImageView)findViewById(R.id.imageView1face);
		ivFace.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFaceClick(SavedItemView.this, data);
				}
			}
		});
		
		tvName = (TextView)findViewById(R.id.textView1user_name);
		tvName.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFaceClick(SavedItemView.this, data);
				}
			}
		});
		tvDate = (TextView)findViewById(R.id.textView2date);
		
		Button btn=(Button)findViewById(R.id.button1del);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onDelClick(SavedItemView.this, data);
				}	
			}
		});
		
		
		btn=(Button)findViewById(R.id.button3reply);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onReplyClick(SavedItemView.this, data);
				}					
			}
		});

		
		btn=(Button)findViewById(R.id.button1play);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onPlayClick(SavedItemView.this, data);
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

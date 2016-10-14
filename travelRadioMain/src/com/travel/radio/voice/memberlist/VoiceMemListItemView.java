package com.travel.radio.voice.memberlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.radio.main.R;
import com.travel.radio.member.data.MyUser;

public class VoiceMemListItemView extends FrameLayout {

	public interface OnFaceClickListener {
		public void onFaceClick(VoiceMemListItemView view, MyUser data);
		public void onVoiceClick(VoiceMemListItemView view, MyUser data);
		public void onMsgClick(VoiceMemListItemView view, MyUser data);
	}
	
	OnFaceClickListener mListener;
	public void setOnFaceClickListener(OnFaceClickListener listener) {
		mListener = listener;
	}
	
	public VoiceMemListItemView(Context context) {
		super(context);
		init();
	}

	ImageView ivFace;
	TextView tvName;
	
	MyUser data;
	
	ImageLoader mLoader;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.voice_member_list_item, this);
		
		ivFace = (ImageView)findViewById(R.id.imageView1followlist_face);
		ivFace.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFaceClick(VoiceMemListItemView.this, data);
				}
			}
		});
		
		tvName = (TextView)findViewById(R.id.textView1followlist_name);
		tvName.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFaceClick(VoiceMemListItemView.this, data);
				}
			}
		});
		
		Button btn=(Button)findViewById(R.id.button_voice_machine);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onVoiceClick(VoiceMemListItemView.this, data);
				}				
			}
		});
		
		btn=(Button)findViewById(R.id.button_message_machine);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onMsgClick(VoiceMemListItemView.this, data);
				}				
			}
		});
		
		mLoader = ImageLoader.getInstance();
	}
	
	
	
	public void setData(MyUser data) {
		this.data = data;

		mLoader.displayImage(data.img, ivFace);
		tvName.setText(data.name);
	}

}

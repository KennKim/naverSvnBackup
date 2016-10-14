package com.travel.radio.voice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.radio.main.R;
import com.travel.radio.voice.data.Voice;


public class VoicelistItemView extends FrameLayout {

	public interface OnSelectClickListener {
		public void onSelectClick(VoicelistItemView view, Voice voice);
	}
	
	OnSelectClickListener mListener;
	public void setOnSelectClickListener(OnSelectClickListener listener) {
		mListener = listener;
	}
	
	public VoicelistItemView(Context context) {
		super(context);
		init();
	}

	ImageView ivFace;
	TextView tvName;
	CheckBox cbVoice;
	Voice voice;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.voice_member_list_item, this);
		
		ivFace = (ImageView)findViewById(R.id.imageView1followlist_face);
		tvName = (TextView)findViewById(R.id.textView1name);
//		cbVoice = (CheckBox)findViewById(R.id.checkBox1voicelist);
//		cbVoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//			
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				if (mListener != null) {
//					mListener.onSelectClick(VoicelistItemView.this, voice);
//				}
//			}
//		});
		mLoader = ImageLoader.getInstance();
	}
	
	ImageLoader mLoader;
	public void setData(Voice voice) {
		this.voice = voice;
//		mloader.displayImage(uri, ivFace);
//		tvName.setText(voice.name);

		mLoader.displayImage("http://cafefiles.naver.net/20090917_194/busut04_1253142693432vX28x_jpg/500_%2812%29_busut04.jpg", ivFace);
		tvName.setText("Byienkanhn");
	}

}

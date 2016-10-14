package com.travel.radio.following;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.radio.following.data.Follow;
import com.travel.radio.main.R;

public class FollowingItemView extends FrameLayout {

	public interface OnFaceClickListener {
		public void onFaceClick(FollowingItemView view, Follow data);
	}
	
	OnFaceClickListener mListener;
	public void setOnFaceClickListener(OnFaceClickListener listener) {
		mListener = listener;
	}
	
	public FollowingItemView(Context context) {
		super(context);
		init();
	}

	ImageView ivFace;
	TextView tvName;
	
	Follow data;
	
	ImageLoader mLoader;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.following_list_item, this);
		
		ivFace = (ImageView)findViewById(R.id.imageView1followlist_face);
		ivFace.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFaceClick(FollowingItemView.this, data);
				}
			}
		});
		
		tvName = (TextView)findViewById(R.id.textView1followlist_name);
		tvName.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFaceClick(FollowingItemView.this, data);
				}
			}
		});
		mLoader = ImageLoader.getInstance();
	}
	
	
	
	public void setData(Follow data) {
		this.data = data;

		mLoader.displayImage(data.img, ivFace);
		tvName.setText(data.name);
	}

}

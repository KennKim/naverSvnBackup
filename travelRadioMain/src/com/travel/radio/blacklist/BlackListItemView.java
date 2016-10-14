package com.travel.radio.blacklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.radio.blacklist.data.Block;
import com.travel.radio.main.R;

public class BlackListItemView extends FrameLayout {

	public interface OnFaceClickListener {
		public void onFaceClick(BlackListItemView view, Block data);
	}
	
	OnFaceClickListener mListener;
	public void setOnFaceClickListener(OnFaceClickListener listener) {
		mListener = listener;
	}
	
	public BlackListItemView(Context context) {
		super(context);
		init();
	}

	ImageView ivFace;
	TextView tvName;
	
	Block data;
	
	ImageLoader mLoader;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.following_list_item, this);
		
		ivFace = (ImageView)findViewById(R.id.imageView1followlist_face);
		ivFace.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFaceClick(BlackListItemView.this, data);
				}
			}
		});
		
		tvName = (TextView)findViewById(R.id.textView1followlist_name);
		tvName.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFaceClick(BlackListItemView.this, data);
				}
			}
		});
		mLoader = ImageLoader.getInstance();
	}
	
	
	
	public void setData(Block data) {
		this.data = data;

		mLoader.displayImage(data.img, ivFace);
		tvName.setText(data.name);
	}

}

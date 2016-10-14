//package com.travel.radio.following;
//
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.travel.radio.following.data.Follow;
//import com.travel.radio.main.R;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.CheckBox;
//import android.widget.Checkable;
//import android.widget.CompoundButton;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
//public class xFollowingItemView extends FrameLayout {
//
//	public interface OnItemClickListener {
//		public void onFaceClick(xFollowingItemView view, Follow data);
//		public void onCheckClick(xFollowingItemView view, Follow data);
////		public void onCheckClick(FollowingItemView view, Follow data, int position, boolean checked);
//	}
//	
//	OnItemClickListener mListener;
//	public void setOnItemClickListener(OnItemClickListener listener) {
//		mListener = listener;
//	}
//	
//	public xFollowingItemView(Context context) {
//		super(context);
//		init();
//	}
//
//	ImageView ivFace;
//	TextView tvName;
//	CheckBox cbSelect;
//	Follow data;
//	
//	private void init() {
//		LayoutInflater.from(getContext()).inflate(R.layout.following_list_item, this);
//		
//		ivFace = (ImageView)findViewById(R.id.imageView1followlist_face);
//		ivFace.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if(mListener != null){
//					mListener.onFaceClick(xFollowingItemView.this, data);
//				}
//			}
//		});
//		
//		tvName = (TextView)findViewById(R.id.textView1followlist_name);
//		tvName.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				if(mListener != null){
//					mListener.onFaceClick(xFollowingItemView.this, data);
//				}
//			}
//		});
//		
////		cbSelect = (CheckBox)findViewById(R.id.checkBox1followlist);
////		cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
////			
////			@Override
////			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
////				if (mListener != null) {
////					mListener.onCheckClick(FollowingItemView.this, data,position, isChecked);
////				}
////			}
////		});
//		cbSelect.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				if(mListener != null){
//					mListener.onCheckClick(xFollowingItemView.this, data);
//				}
//				
//			}
//		});
//		
//		mLoader = ImageLoader.getInstance();
//		
//	}
//	
//	ImageLoader mLoader;
//	
//	public void setData(Follow data) {
//		this.data = data;
//		
//		mLoader.displayImage(data.img, ivFace);
//		tvName.setText(data.name);
//		
//	}
//	
//	int position;
//	public void setPosition(int position) {
//		this.position = position;
//	}
//	
//	public void setChecked(boolean isChecked) {
//		cbSelect.setChecked(isChecked);
//	}
//
//}

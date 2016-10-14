package com.travelradio.record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.travelradio.data.ItemData;
import com.travelradio.main.R;

public class ItemView extends FrameLayout {
	
	ItemData data;

	public interface OnLikeClickListener {
		public void onLikeClick(ItemView view, ItemData data);
	}
	
	OnLikeClickListener mListener;
	public void setOnLikeClickListener(OnLikeClickListener listener) {
		mListener = listener;
	}
	
	public ItemView(Context context) {
		super(context);
		init();
	}

	
	ImageView ivFace;	
	TextView tvPlace;
	TextView tvDate;
	
	ImageView ivPhoto;
	ImageView ivHeart;
	TextView tvHeartNumber;
	ImageView ivReply;
	TextView tvReplyNumber;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.record_list_item, this);
		
		ivFace = (ImageView)findViewById(R.id.imageView1face);
		tvPlace = (TextView)findViewById(R.id.textView1place);
		tvDate = (TextView)findViewById(R.id.textView2date);
		
		ivPhoto = (ImageView)findViewById(R.id.imageView2photo);
		ivHeart = (ImageView)findViewById(R.id.imageView3heart);
		tvHeartNumber = (TextView)findViewById(R.id.textView3heart);
		ivReply = (ImageView)findViewById(R.id.imageView4reply);
		tvReplyNumber = (TextView)findViewById(R.id.textView4reply);

	}
	
	public void setData(ItemData data) {
		this.data = data;
		ivFace.setImageResource(data.userFace);
		tvPlace.setText(data.place);
		tvDate.setText(data.uploadDate);
		
		ivPhoto.setImageResource(data.photoImage);
		tvPlace.setText(data.heartNumber);
		tvPlace.setText(data.replyNumber);

	}

}

package com.travel.radio.mybooth.myscrap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.radio.main.R;
import com.travel.radio.record.data.Tags;
import com.travel.radio.record.scrap.data.Scrap;

public class MyScrapItemView extends FrameLayout {

	public interface OnPhotoClickListener {
		public void onPhotoClick(MyScrapItemView view, Scrap data);

		public void onFaceClick(MyScrapItemView view, Scrap data);
	}

	OnPhotoClickListener mListener;

	public void setOnPhotoClickListener(OnPhotoClickListener listener) {
		mListener = listener;
	}

	public MyScrapItemView(Context context) {
		super(context);
		init();
	}

	ImageView ivFace;
	ImageView ivPhoto;
	TextView tvName;
	TextView tvPlace;
	TextView tvDate;
	TextView tvContent;

	TextView tvHeartCnt;
	TextView tvReplyCnt;

	CheckBox checkBPlace;
	CheckBox checkBFood;
	CheckBox checkBEnjoy;
	CheckBox checkBStay;
	CheckBox checkBEtc;
	CheckBox checkBsos;

	Scrap data;
	ImageLoader mLoader;

	private void init() {
		LayoutInflater.from(getContext()).inflate(
				R.layout.tab_record_list_item, this);
		mLoader = ImageLoader.getInstance();

		ivFace = (ImageView) findViewById(R.id.imageView1face);
		ivFace.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onFaceClick(MyScrapItemView.this, data);
				}
			}
		});
		tvName = (TextView) findViewById(R.id.textView1user_name);
		tvName.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onFaceClick(MyScrapItemView.this, data);
				}
			}
		});
		tvPlace = (TextView) findViewById(R.id.textView2place);
		tvDate = (TextView) findViewById(R.id.textView2date);
		tvContent=(TextView)findViewById(R.id.textView1content);

		ivPhoto = (ImageView) findViewById(R.id.imageView2photo);
		tvHeartCnt = (TextView) findViewById(R.id.textView3heart_cnt);
		tvReplyCnt = (TextView) findViewById(R.id.textView4reply_cnt);

		ivPhoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onPhotoClick(MyScrapItemView.this, data);
				}
			}
		});

		checkBPlace = (CheckBox) findViewById(R.id.checkBox1place);
		checkBFood = (CheckBox) findViewById(R.id.checkBox2Food);
		checkBEnjoy = (CheckBox) findViewById(R.id.checkBox3enjoy);
		checkBStay = (CheckBox) findViewById(R.id.checkBox4stay);
		checkBEtc = (CheckBox) findViewById(R.id.checkBox5etc);
		checkBsos = (CheckBox) findViewById(R.id.checkBox5sos);

	}

	public void setData(Scrap data) {
		this.data = data;
		
		tvName.setText(data.name);
		tvDate.setText(data.udate);
		tvPlace.setText(data.recordPlace);
		tvHeartCnt.setText(data.hCount);
		tvReplyCnt.setText(data.replyCount);
		tvContent.setText(data.content);
		
		
		String faceUrl = data.img;
		mLoader.displayImage(faceUrl, ivFace);
		

		String photoUrl = data.images.get(0).imgName;
		mLoader.displayImage(photoUrl, ivPhoto);

		
//		String photoUrl = data.images.get(0).imgName;
//		mLoader.displayImage(photoUrl, ivPhoto);

		for(Tags t : data.tags){
			switch (t.tagId) {
			case 1:
				checkBPlace.setChecked(true);
				break;
			case 2:
				checkBFood.setChecked(true);
				break;
			case 3:
				checkBEnjoy.setChecked(false);
				break;
			case 4:
				checkBStay.setChecked(false);
				break;
			case 5:
				checkBEtc.setChecked(true);
				break;
			}
		}
	}

}

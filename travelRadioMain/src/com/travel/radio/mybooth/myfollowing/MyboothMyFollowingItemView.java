package com.travel.radio.mybooth.myfollowing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.radio.main.R;
import com.travel.radio.record.data.Records;
import com.travel.radio.record.data.Tags;

public class MyboothMyFollowingItemView extends FrameLayout {

	public interface OnPhotoClickListener {
		public void onPhotoClick(MyboothMyFollowingItemView view, Records data);

		public void onFaceClick(MyboothMyFollowingItemView view, Records data);
	}

	OnPhotoClickListener mListener;

	public void setOnPhotoClickListener(OnPhotoClickListener listener) {
		mListener = listener;
	}

	public MyboothMyFollowingItemView(Context context) {
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

	Records data;
	ImageLoader mLoader;

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.tab_record_list_item, this);
		mLoader = ImageLoader.getInstance();

		ivFace = (ImageView) findViewById(R.id.imageView1face);
		ivFace.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onFaceClick(MyboothMyFollowingItemView.this, data);
				}
			}
		});
		tvName = (TextView) findViewById(R.id.textView1user_name);
		tvName.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onFaceClick(MyboothMyFollowingItemView.this, data);
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
					mListener.onPhotoClick(MyboothMyFollowingItemView.this, data);
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

	public void setData(Records data) {
		this.data = data;
//		tvName.setText("Amyeihyanui");
//		tvPlace.setText("Cairo, Egypt");
//		tvDate.setText("10월 21일 오후 3:40");
//		tvHeartCnt.setText("77");
//		tvReplyCnt.setText("188");
		
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

//		checkBPlace.setChecked(true);
//		checkBFood.setChecked(true);
//		checkBEnjoy.setChecked(false);
//		checkBStay.setChecked(false);
//		checkBEtc.setChecked(true);
//		checkBsos.setChecked(false);
//		mLoader.displayImage(
//				"http://postfiles3.naver.net/20140315_258/estclassic_1394870139706jV88R_JPEG/%C0%CC%BB%DB%BF%A9%C0%DA%BB%E7%C1%F8%B8%F0%C0%BD.jpg?type=w2",
//				ivFace);
//		mLoader.displayImage(
//				"http://postfiles9.naver.net/20120615_40/manttang0_1339755781635GbI7I_JPEG/7.jpg?type=w2",
//				ivPhoto);
	}

}

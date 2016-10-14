package com.travel.radio.record.replys;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.radio.main.R;
import com.travel.radio.record.reply.data.Reply;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ReplyItemView extends FrameLayout {

	public interface OnFaceClickListener {
		public void onFaceClick(ReplyItemView view, Reply data);
		public void onModifyClick(ReplyItemView view, Reply data);
		public void onDeleteClick(ReplyItemView view, Reply data);
	}
	
	OnFaceClickListener mListener;
	public void setOnFaceClickListener(OnFaceClickListener listener) {
		mListener = listener;
	}
	
	public ReplyItemView(Context context) {
		super(context);
		init();
	}

	ImageView ivFace;
	TextView tvName;
	TextView tvDate;
	TextView tvReply;
	
	Reply data;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.record_reply_item, this);
		
		ivFace = (ImageView)findViewById(R.id.imageView1face);
		ivFace.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFaceClick(ReplyItemView.this, data);
				}
			}
		});
		
		tvName = (TextView)findViewById(R.id.textView1name);
		tvName.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFaceClick(ReplyItemView.this, data);
				}
			}
		});
		
		tvDate = (TextView)findViewById(R.id.textView2date);
		tvReply = (TextView)findViewById(R.id.textView2reply);
		
		Button btn=(Button)findViewById(R.id.button_modify);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onModifyClick(ReplyItemView.this, data);
				}
			}
		});
		
		btn=(Button)findViewById(R.id.button_delete);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onDeleteClick(ReplyItemView.this, data);
				}
			}
		});
		

		mLoader = ImageLoader.getInstance();
		
	}
	
	ImageLoader mLoader;
	
	public void setData(Reply data) {
		this.data = data;
		
		tvName.setText(data.name);
		tvDate.setText(data.udate);
		tvReply.setText(data.content);
		
		mLoader.displayImage(data.img, ivFace);
		
//		ivFace.setImageResource(data.userFace);
//		tvName.setText(data.name);
		
//		mLoader.displayImage("http://blogfiles16.naver.net/20140811_63/manttang0_1407758826138nbTns_JPEG/COR3492020.JPG", ivFace);
//		tvName.setText("chyuinwe");
		
	}

}

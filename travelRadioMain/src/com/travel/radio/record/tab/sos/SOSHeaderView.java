package com.travel.radio.record.tab.sos;

import com.travel.radio.main.R;
import com.travel.radio.main.R.id;
import com.travel.radio.main.R.layout;
import com.travel.radio.record.data.Records;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class SOSHeaderView extends FrameLayout {
	
	int radius = 0;
	final static int RADIUS1 = 10;
	final static int RADIUS2 = 20;
	final static int RADIUS3 = 30;
	final static int RADIUS4 = 40;
	final static int RADIUS5 = 50;
	String latitude;
	String longitude;
	
	public interface OnHeaderClickListener {
		public void onCheckBoxClick(SOSHeaderView view, Records data);
	}
	OnHeaderClickListener mListener;
	public void setOnHeaderViewItemClickListener(OnHeaderClickListener listener){
		mListener = listener;
	}

	public SOSHeaderView(Context context) {
		super(context);
		init();
	}
	
	EditText editTSearch;
//	CheckBox checkBPlace;
//	CheckBox checkBFood;
//	CheckBox checkBEnjoy;
//	CheckBox checkBStay;
//	CheckBox checkBEtc;
	RadioButton radioBheart;
	RadioButton radioBreply;
	

	RadioButton rbR1;
	RadioButton rbR2;
	RadioButton rbR3;
	RadioButton rbR4;
	RadioButton rbR5;
	
	
	Records data;

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.tab_sos_header, this);
		
		

		rbR1=(RadioButton)findViewById(R.id.radioButtonR1);
		rbR1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					radius = RADIUS1;
				}
			}
		});
		rbR2=(RadioButton)findViewById(R.id.radioButtonR2);
		rbR2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					radius = RADIUS2;
				}
			}
		});
		rbR3=(RadioButton)findViewById(R.id.radioButtonR3);
		rbR3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					radius = RADIUS3;
				}
			}
		});
		rbR4=(RadioButton)findViewById(R.id.radioButtonR4);
		rbR4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					radius = RADIUS4;
				}
			}
		});
		rbR5=(RadioButton)findViewById(R.id.radioButtonR5);
		rbR5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					radius = RADIUS5;
				}
			}
		});


		editTSearch = (EditText) findViewById(R.id.editText_search);

		Button btn = (Button) findViewById(R.id.button_search);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				Toast.makeText(getContext(), "abc", Toast.LENGTH_SHORT).show();
			}
		});

//		checkBPlace = (CheckBox) findViewById(R.id.checkBox1place);
//		checkBPlace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//					public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
////						if (isChecked) { // 선택
//							if(mListener != null){
//								mListener.onCheckBoxClick(SOSHeaderView.this, data);
//							}
////						}
//					}
//				});
//
//		checkBFood = (CheckBox) findViewById(R.id.checkBox2Food);
//		checkBEnjoy = (CheckBox) findViewById(R.id.checkBox3enjoy);
//		checkBStay = (CheckBox) findViewById(R.id.checkBox4stay);
//		checkBEtc = (CheckBox) findViewById(R.id.checkBox5etc);

		radioBheart = (RadioButton) findViewById(R.id.radio_heart_sort);
		radioBheart	.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
						if (isChecked) { // 선택
						}
					}
				});
		radioBreply = (RadioButton) findViewById(R.id.radio_review_sort);

	}

}

package com.travel.radio.record.tab.main;

import java.util.ArrayList;

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

import com.travel.radio.main.R;
import com.travel.radio.record.data.Records;

public class RecordHeaderView extends FrameLayout {
	
	public static final int TYPE_PLACE = 1;
	public static final int TYPE_FOOD = 2;
	public static final int TYPE_ENJOY = 3;
	public static final int TYPE_STAY = 4;
	public static final int TYPE_ETC = 5;
	
	public interface OnHeaderClickListener {
		public void onCheckBoxClick(RecordHeaderView view, Records data, ArrayList<Integer> list);
	}
	OnHeaderClickListener mListener;
	public void setOnHeaderViewItemClickListener(OnHeaderClickListener listener){
		mListener = listener;
	}

	public RecordHeaderView(Context context) {
		super(context);
		init();
	}
	
	EditText editTSearch;
	CheckBox checkBPlace;
	CheckBox checkBFood;
	CheckBox checkBEnjoy;
	CheckBox checkBStay;
	CheckBox checkBEtc;
	RadioButton radioBheart;
	RadioButton radioBreply;
	
	private ArrayList<Integer> getSelectList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (checkBPlace.isChecked()) {
			list.add((Integer)TYPE_PLACE);
		}
		if (checkBFood.isChecked()) {
			list.add((Integer)TYPE_FOOD);
		}
		if (checkBEnjoy.isChecked()) {
			list.add((Integer)TYPE_ENJOY);
		}
		if (checkBStay.isChecked()) {
			list.add((Integer)TYPE_STAY);
		}
		if (checkBEtc.isChecked()) {
			list.add((Integer)TYPE_ETC);
		}
		return list;
	}
	
	Records data;

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.tab_record_header,
				this);

		editTSearch = (EditText) findViewById(R.id.editText_search);

		Button btn = (Button) findViewById(R.id.button_search);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				Toast.makeText(getContext(), "abc", Toast.LENGTH_SHORT).show();
			}
		});

		checkBPlace = (CheckBox) findViewById(R.id.checkBox1place);
		checkBPlace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
						if (isChecked) { // 선택
							if(mListener != null){
								mListener.onCheckBoxClick(RecordHeaderView.this, data, getSelectList());
							}
						}
					}
				});

		checkBFood = (CheckBox) findViewById(R.id.checkBox2Food);
		checkBFood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				if (isChecked) { // 선택
					if(mListener != null){
						mListener.onCheckBoxClick(RecordHeaderView.this, data, getSelectList());
					}
				}
			}
		});
		
		checkBEnjoy = (CheckBox) findViewById(R.id.checkBox3enjoy);
		checkBEnjoy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				if (isChecked) { // 선택
					if(mListener != null){
						mListener.onCheckBoxClick(RecordHeaderView.this, data, getSelectList());
					}
				}
			}
		});
		
		checkBStay = (CheckBox) findViewById(R.id.checkBox4stay);
		checkBStay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				if (isChecked) { // 선택
					if(mListener != null){
						mListener.onCheckBoxClick(RecordHeaderView.this, data, getSelectList());
					}
				}
			}
		});
		
		checkBEtc = (CheckBox) findViewById(R.id.checkBox5etc);
		checkBEtc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				if (isChecked) { // 선택
					if(mListener != null){
						mListener.onCheckBoxClick(RecordHeaderView.this, data, getSelectList());
					}
				}
			}
		});
		

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

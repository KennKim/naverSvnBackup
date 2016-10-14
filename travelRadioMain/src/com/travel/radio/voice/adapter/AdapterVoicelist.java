package com.travel.radio.voice.adapter;


import java.util.ArrayList;

import com.travel.radio.voice.data.Voice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class AdapterVoicelist extends BaseAdapter implements
	VoicelistItemView.OnSelectClickListener {

	public interface OnAdapterItemClickListener {
		public void onAdapterItemClick(View v, Voice voice);
	}
	
	OnAdapterItemClickListener mListener;
	public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
		mListener = listener;
	}
	
	ArrayList<Voice> items = new ArrayList<Voice>();
	Context mContext;
	
	public AdapterVoicelist(Context context) {
		mContext = context;
	}
	
	
	public void add(Voice voice) {
		items.add(voice);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		VoicelistItemView view;
		if (convertView == null) {
			view = new VoicelistItemView(mContext);
			view.setOnSelectClickListener(this);
		} else {
			view = (VoicelistItemView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}


	@Override
	public void onSelectClick(VoicelistItemView view, Voice voice) {
		// TODO Auto-generated method stub
		if (mListener != null) {
			mListener.onAdapterItemClick(view, voice);
		}	
	}

}

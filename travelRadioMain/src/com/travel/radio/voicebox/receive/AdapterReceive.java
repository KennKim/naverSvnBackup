package com.travel.radio.voicebox.receive;


import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.travel.radio.voice.data.Voice;



public class AdapterReceive extends BaseAdapter implements
	ReceiveItemView.OnPlayClickListener {

	public interface OnAdapterItemClickListener {
		public void onAdapterFaceItemClick(View v, Voice data);
		public void onAdapterPlayItemClick(View v, Voice data);
		public void onAdapterDelItemClick(View v, Voice data);
		public void onAdapterSavedItemClick(View v, Voice data);
		public void onAdapterReplyItemClick(View v, Voice data);
	}
	
	OnAdapterItemClickListener mListener;
	public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
		mListener = listener;
	}
	
	ArrayList<Voice> items = new ArrayList<Voice>();
	Context mContext;
	
	public AdapterReceive(Context context) {
		mContext = context;
	}
	
	public void add(Voice data) {
		items.add(data);
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
		ReceiveItemView view;
		if (convertView == null) {
			view = new ReceiveItemView(mContext);
			view.setOnPlayClickListener(this);
		} else {
			view = (ReceiveItemView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}

	@Override
	public void onPlayClick(ReceiveItemView view, Voice data) {
		if (mListener != null) {
			mListener.onAdapterPlayItemClick(view, data);
		}		
	}

	@Override
	public void onFaceClick(ReceiveItemView view, Voice data) {
		if (mListener != null) {
			mListener.onAdapterFaceItemClick(view, data);
		}				
	}

	@Override
	public void onDelClick(ReceiveItemView view, Voice data) {
		if (mListener != null) {
			mListener.onAdapterDelItemClick(view, data);
		}				
	}

	@Override
	public void onSavedClick(ReceiveItemView view, Voice data) {
		if (mListener != null) {
			mListener.onAdapterSavedItemClick(view, data);
		}				
	}

	@Override
	public void onReplyClick(ReceiveItemView view, Voice data) {
		if (mListener != null) {
			mListener.onAdapterReplyItemClick(view, data);
		}				
	}

}

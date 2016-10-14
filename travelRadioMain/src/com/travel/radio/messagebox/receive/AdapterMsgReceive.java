package com.travel.radio.messagebox.receive;


import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.travel.radio.voice.data.Voice;



public class AdapterMsgReceive extends BaseAdapter implements
	MsgReceiveItemView.OnPlayClickListener {

	public interface OnAdapterItemClickListener {
		public void onAdapterFaceItemClick(View v, Voice data);
		public void onAdapterDelItemClick(View v, Voice data);
		public void onAdapterSaveItemClick(View v, Voice data);
		public void onAdapterReplyItemClick(View v, Voice data);
	}
	
	OnAdapterItemClickListener mListener;
	public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
		mListener = listener;
	}
	
	ArrayList<Voice> items = new ArrayList<Voice>();
	Context mContext;
	
	public AdapterMsgReceive(Context context) {
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
		MsgReceiveItemView view;
		if (convertView == null) {
			view = new MsgReceiveItemView(mContext);
			view.setOnPlayClickListener(this);
		} else {
			view = (MsgReceiveItemView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}

	@Override
	public void onFaceClick(MsgReceiveItemView view, Voice data) {
		if (mListener != null) {
			mListener.onAdapterFaceItemClick(view, data);
		}				
	}

	@Override
	public void onDelClick(MsgReceiveItemView view, Voice data) {
		if (mListener != null) {
			mListener.onAdapterDelItemClick(view, data);
		}			
	}

	@Override
	public void onSaveClick(MsgReceiveItemView view, Voice data) {
		if (mListener != null) {
			mListener.onAdapterSaveItemClick(view, data);
		}			
	}

	@Override
	public void onReplyClick(MsgReceiveItemView view, Voice data) {
		if (mListener != null) {
			mListener.onAdapterReplyItemClick(view, data);
		}			
	}

}

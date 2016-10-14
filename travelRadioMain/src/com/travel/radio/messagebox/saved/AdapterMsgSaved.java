package com.travel.radio.messagebox.saved;


import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.travel.radio.voice.data.Voice;



public class AdapterMsgSaved extends BaseAdapter implements
	MsgSavedItemView.OnPlayClickListener {

	public interface OnAdapterItemClickListener {
		public void onAdapterFaceItemClick(View v, Voice data);
		public void onAdapterDelItemClick(View v, Voice data);
		public void onAdapterReplyItemClick(View v, Voice data);
	}
	
	OnAdapterItemClickListener mListener;
	public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
		mListener = listener;
	}
	
	ArrayList<Voice> items = new ArrayList<Voice>();
	Context mContext;
	
	public AdapterMsgSaved(Context context) {
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
		MsgSavedItemView view;
		if (convertView == null) {
			view = new MsgSavedItemView(mContext);
			view.setOnPlayClickListener(this);
		} else {
			view = (MsgSavedItemView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}

	@Override
	public void onFaceClick(MsgSavedItemView view, Voice data) {
		if (mListener != null) {
			mListener.onAdapterFaceItemClick(view, data);
		}				
	}

	@Override
	public void onDelClick(MsgSavedItemView view, Voice data) {
		if (mListener != null) {
			mListener.onAdapterDelItemClick(view, data);
		}			
	}

	@Override
	public void onReplyClick(MsgSavedItemView view, Voice data) {
		if (mListener != null) {
			mListener.onAdapterReplyItemClick(view, data);
		}			
	}

}

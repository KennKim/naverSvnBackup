package com.travel.radio.record.tab.sos;

import java.util.ArrayList;

import com.travel.radio.record.data.Records;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class AdapterSOS extends BaseAdapter implements
	SOSItemView.OnPhotoClickListener,
	SOSHeaderView.OnHeaderClickListener{
	
	public interface OnAdapterItemClickListener {
		public void onAdapterPhotoItemClick(View v, Records records);
		public void onAdapterFaceItemClick(View v, Records records);
	}
	OnAdapterItemClickListener mListener;
	public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
		mListener = listener;
	}
	
	public interface OnHeaderItemClickListener {
		public void onAdapterPhotoItemClick(View v, Records records);
		public void onAdapterFaceItemClick(View v, Records records);
	}
	OnHeaderItemClickListener hListener;
	public void setOnHeaderItemClickListener(OnHeaderItemClickListener listener) {
		hListener = listener;
	}
	
	ArrayList<Records> items = new ArrayList<Records>();
	Context mContext;
	
	public AdapterSOS(Context context) {
		mContext = context;
	}
	
	public void add(Records records) {
		items.add(records);
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
		SOSItemView view;
		if (convertView == null) {
			view = new SOSItemView(mContext);
			view.setOnPhotoClickListener(this);
		} else {
			view = (SOSItemView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}

	@Override
	public void onPhotoClick(SOSItemView view, Records data) {
		if (mListener != null) {
			mListener.onAdapterPhotoItemClick(view, data);
		}
	}

	@Override
	public void onFaceClick(SOSItemView view, Records data) {
		if (mListener != null) {
			mListener.onAdapterFaceItemClick(view, data);
		}		
	}

	@Override
	public void onCheckBoxClick(SOSHeaderView view, Records data) {
		if (hListener != null) {
			hListener.onAdapterFaceItemClick(view, data);
		}		
	}


}

package com.travel.radio.record.tab.main;

import java.util.ArrayList;

import com.travel.radio.record.data.Records;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class AdapterRecord extends BaseAdapter implements
	RecordItemView.OnPhotoClickListener,
	RecordHeaderView.OnHeaderClickListener{
	
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
	
	public AdapterRecord(Context context) {
		mContext = context;
	}
	
	public void add(Records records) {
		items.add(records);
		notifyDataSetChanged();
	}
	public void clear() {
		items.clear();
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
		RecordItemView view;
		if (convertView == null) {
			view = new RecordItemView(mContext);
			view.setOnPhotoClickListener(this);
		} else {
			view = (RecordItemView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}

	@Override
	public void onPhotoClick(RecordItemView view, Records data) {
		if (mListener != null) {
			mListener.onAdapterPhotoItemClick(view, data);
		}
	}

	@Override
	public void onFaceClick(RecordItemView view, Records data) {
		if (mListener != null) {
			mListener.onAdapterFaceItemClick(view, data);
		}		
	}

	@Override
	public void onCheckBoxClick(RecordHeaderView view, Records data, ArrayList<Integer> list) {
		if (hListener != null) {
			hListener.onAdapterFaceItemClick(view, data);
		}			
	}


}

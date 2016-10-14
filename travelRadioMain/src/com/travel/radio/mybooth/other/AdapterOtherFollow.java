package com.travel.radio.mybooth.other;


import java.util.ArrayList;

import com.travel.radio.record.data.Records;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class AdapterOtherFollow extends BaseAdapter implements
	OtherFollowingItemView.OnPhotoClickListener{
	
	//////////////// ItemView Listener /////////////////
	//////////////// ItemView Listener /////////////////
	//////////////// ItemView Listener /////////////////
	public interface OnAdapterItemClickListener {
		public void onAdapterItemClick(View v, Records data);
		public void onAdapterFaceItemClick(View v, Records data);
	}
	OnAdapterItemClickListener mListener;
	public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
		mListener = listener;
	}
	
	ArrayList<Records> items = new ArrayList<Records>();
	Context mContext;
	
	public AdapterOtherFollow(Context context) {
		mContext = context;
	}
	
	public void add(Records data) {
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
		OtherFollowingItemView view;
		if (convertView == null) {
			view = new OtherFollowingItemView(mContext);
			view.setOnPhotoClickListener(this);
		} else {
			view = (OtherFollowingItemView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}

	@Override
	public void onPhotoClick(OtherFollowingItemView view, Records data) {
		if (mListener != null) {
			mListener.onAdapterItemClick(view, data);
		}
	}

	@Override
	public void onFaceClick(OtherFollowingItemView view, Records data) {
		if (mListener != null) {
			mListener.onAdapterItemClick(view, data);
		}		
	}


}

package com.travel.radio.following;

import java.util.ArrayList;

import com.travel.radio.following.data.Follow;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class AdapterFollowing extends BaseAdapter implements
	FollowingItemView.OnFaceClickListener {

	public interface OnAdapterItemClickListener {
		public void onAdapterItemClick(View v, Follow data);
	}
	
	OnAdapterItemClickListener mListener;
	public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
		mListener = listener;
	}
	
	ArrayList<Follow> items = new ArrayList<Follow>();
	Context mContext;
	
	public AdapterFollowing(Context context) {
		mContext = context;
	}
	
	
	public void add(Follow data) {
		items.add(data);
		notifyDataSetChanged();
	}
	
	public void deleteItem(Follow data) {
		items.remove(data);
		notifyDataSetChanged();
	}
	
	public void deleteAll() {
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
		FollowingItemView view;
		if (convertView == null) {
			view = new FollowingItemView(mContext);
			view.setOnFaceClickListener(this);
		} else {
			view = (FollowingItemView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}

	@Override
	public void onFaceClick(FollowingItemView view, Follow data) {
		// ... 
		if (mListener != null) {
			mListener.onAdapterItemClick(view, data);
		}
	}

}

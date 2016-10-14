package com.travel.radio.blacklist;

import java.util.ArrayList;

import com.travel.radio.blacklist.data.Block;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class AdapterBlackList extends BaseAdapter implements
	BlackListItemView.OnFaceClickListener {

	public interface OnAdapterItemClickListener {
		public void onAdapterItemClick(View v, Block data);
	}
	
	OnAdapterItemClickListener mListener;
	public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
		mListener = listener;
	}
	
	ArrayList<Block> items = new ArrayList<Block>();
	Context mContext;
	
	public AdapterBlackList(Context context) {
		mContext = context;
	}
	
	public void add(Block data) {
		items.add(data);
		notifyDataSetChanged();
	}
	
	public void deleteItem(Block data) {
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
		BlackListItemView view;
		if (convertView == null) {
			view = new BlackListItemView(mContext);
			view.setOnFaceClickListener(this);
		} else {
			view = (BlackListItemView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}

	@Override
	public void onFaceClick(BlackListItemView view, Block data) {
		// ... 
		if (mListener != null) {
			mListener.onAdapterItemClick(view, data);
		}
	}

}

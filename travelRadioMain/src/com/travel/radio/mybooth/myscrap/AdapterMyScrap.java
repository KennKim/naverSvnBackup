package com.travel.radio.mybooth.myscrap;


import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.travel.radio.record.scrap.data.Scrap;


public class AdapterMyScrap extends BaseAdapter implements
	MyScrapItemView.OnPhotoClickListener{
	
	//////////////// ItemView Listener /////////////////
	//////////////// ItemView Listener /////////////////
	//////////////// ItemView Listener /////////////////
	public interface OnAdapterItemClickListener {
		public void onAdapterItemClick(View v, Scrap data);
		public void onAdapterFaceItemClick(View v, Scrap data);
	}
	OnAdapterItemClickListener mListener;
	public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
		mListener = listener;
	}
	
	ArrayList<Scrap> items = new ArrayList<Scrap>();
	Context mContext;
	
	public AdapterMyScrap(Context context) {
		mContext = context;
	}
	
	public void add(Scrap data) {
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
		MyScrapItemView view;
		if (convertView == null) {
			view = new MyScrapItemView(mContext);
			view.setOnPhotoClickListener(this);
		} else {
			view = (MyScrapItemView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}

	@Override
	public void onPhotoClick(MyScrapItemView view, Scrap data) {
		if (mListener != null) {
			mListener.onAdapterItemClick(view, data);
		}
	}

	@Override
	public void onFaceClick(MyScrapItemView view, Scrap data) {
		if (mListener != null) {
			mListener.onAdapterItemClick(view, data);
		}		
	}


}

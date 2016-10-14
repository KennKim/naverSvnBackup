package com.travel.radio.record.replys;


import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.travel.radio.record.reply.data.Reply;


public class AdapterReply extends BaseAdapter implements
	ReplyItemView.OnFaceClickListener {

	public interface OnAdapterItemClickListener {
		public void onAdapterFaceItemClick(View v, Reply data);
		public void onAdapterModifyItemClick(View v, Reply data);
		public void onAdapterDeleteItemClick(View v, Reply data);
	}
	
	OnAdapterItemClickListener mListener;
	public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
		mListener = listener;
	}
	
	ArrayList<Reply> items = new ArrayList<Reply>();
	Context mContext;
	
	public AdapterReply(Context context) {
		mContext = context;
	}
	
	public void add(Reply data) {
		items.add(data);
		notifyDataSetChanged();
	}
	
	public void delete(Reply data){
		items.remove(data);
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
		ReplyItemView view;
		if (convertView == null) {
			view = new ReplyItemView(mContext);
			view.setOnFaceClickListener(this);
		} else {
			view = (ReplyItemView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}

	@Override
	public void onFaceClick(ReplyItemView view, Reply data) {
		if (mListener != null) {
			mListener.onAdapterFaceItemClick(view, data);
		}		
	}

	@Override
	public void onModifyClick(ReplyItemView view, Reply data) {
		if (mListener != null) {
			mListener.onAdapterModifyItemClick(view, data);
		}				
	}

	@Override
	public void onDeleteClick(ReplyItemView view, Reply data) {
		if (mListener != null) {
			mListener.onAdapterDeleteItemClick(view, data);
		}				
	}


}

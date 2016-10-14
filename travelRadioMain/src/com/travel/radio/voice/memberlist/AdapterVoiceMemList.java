package com.travel.radio.voice.memberlist;

import java.util.ArrayList;

import com.travel.radio.member.data.MyUser;
import com.travel.radio.voice.data.Voice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class AdapterVoiceMemList extends BaseAdapter implements
	VoiceMemListItemView.OnFaceClickListener {

	public interface OnAdapterItemClickListener {
		public void onAdapterFaceItemClick(View v, MyUser data);
		public void onAdapterVoiceItemClick(View v, MyUser data);
		public void onAdapterMsgItemClick(View v, MyUser data);
	}
	
	OnAdapterItemClickListener mListener;
	public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
		mListener = listener;
	}
	
	ArrayList<MyUser> items = new ArrayList<MyUser>();
	Context mContext;
	
	public AdapterVoiceMemList(Context context) {
		mContext = context;
	}
	
	
	public void add(MyUser data) {
		items.add(data);
		notifyDataSetChanged();
	}
	
	public void deleteItem(MyUser data) {
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
		VoiceMemListItemView view;
		if (convertView == null) {
			view = new VoiceMemListItemView(mContext);
			view.setOnFaceClickListener(this);
		} else {
			view = (VoiceMemListItemView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}

	@Override
	public void onFaceClick(VoiceMemListItemView view, MyUser data) {
		// ... 
		if (mListener != null) {
			mListener.onAdapterFaceItemClick(view, data);
		}
	}


	@Override
	public void onVoiceClick(VoiceMemListItemView view, MyUser data) {
		if (mListener != null) {
			mListener.onAdapterVoiceItemClick(view, data);
		}		
	}


	@Override
	public void onMsgClick(VoiceMemListItemView view, MyUser data) {
		if (mListener != null) {
			mListener.onAdapterMsgItemClick(view, data);
		}		
	}

}

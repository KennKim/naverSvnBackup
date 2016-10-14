//package com.travel.radio.following;
//
//
//import java.util.ArrayList;
//
//import android.content.Context;
//import android.util.SparseBooleanArray;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//
//import com.travel.radio.following.data.Follow;
//
//
//public class xAdapterFollowinglist extends BaseAdapter implements
//	xFollowingItemView.OnItemClickListener {
//	
//	SparseBooleanArray mSelected = new SparseBooleanArray();
//
//	public interface OnAdapterItemClickListener {
//		public void onAdapterItemClick(View v, Follow data);
//		public void onAdapterCheckItemClick(View v, Follow data);
//	}
//	
//	OnAdapterItemClickListener mListener;
//	public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
//		mListener = listener;
//	}
//	
//	ArrayList<Follow> items = new ArrayList<Follow>();
//	Context mContext;
//	
//	public xAdapterFollowinglist(Context context) {
//		mContext = context;
//	}
//	
//	public void add(Follow data) {
//		items.add(data);
//		notifyDataSetChanged();
//	}
//	
//	@Override
//	public int getCount() {
//		
//		return items.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		return items.get(position);
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return position;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		xFollowingItemView view;
//		if (convertView == null) {
//			view = new xFollowingItemView(mContext);
//			view.setOnItemClickListener(this);
//		} else {
//			view = (xFollowingItemView)convertView;
//		}
//		view.setData(items.get(position));
////		view.setPosition(position);
////		view.setChecked(mSelected.get(position, false));
//		return view;
//	}
//
//	@Override
//	public void onFaceClick(xFollowingItemView view, Follow data) {
//		if (mListener != null) {
//			mListener.onAdapterItemClick(view, data);
//		}		
//	}
//
//
//	@Override
//	public void onCheckClick(xFollowingItemView view, Follow data) {
//		if(mListener != null){
//			mListener.onAdapterCheckItemClick(view, data);
//		}
//	}
//
//
////	@Override
////	public void onCheckClick(FollowingItemView view, Follow data, int position, boolean checked) {
////		mSelected.put(position, checked);
////		if (mListener != null){
////			mListener.onAdapterCheckItemClick(view, data);
////		}
////	}
//	
////	public SparseBooleanArray getSelected() {
////		return mSelected;
////	}
//
//}

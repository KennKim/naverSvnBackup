package com.example.uiiwant.frag1;

import java.util.ArrayList;

import com.example.uiiwant.frag1.data.FrOneItemData;
import com.example.uiiwant.frag1.view.FrOneItemView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class FrOneAdapter extends BaseAdapter{
	
	Context mContext;
	ArrayList<FrOneItemData> items = new ArrayList<FrOneItemData>();
	
	public  FrOneAdapter(Context context) {
		mContext=context;
	}
	
	public void add(FrOneItemData data){
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
		
		FrOneItemView view;
		
		if(convertView == null){
			view = new FrOneItemView(mContext);
		}else{
			view=(FrOneItemView)convertView;
		}
		view.setData(items.get(position));
		
		return view;
	}

}

package com.example.uiiwant.frag2;

import java.util.ArrayList;

import com.example.uiiwant.frag2.data.FrTwoItemData;
import com.example.uiiwant.frag2.data.view.FrTwoItemView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class FrTwoAdapter extends BaseAdapter{
	
	Context mContext;
	ArrayList<FrTwoItemData> items = new ArrayList<FrTwoItemData>();
	
	public FrTwoAdapter(Context context) {
		mContext=context;
	}
	
	public void add(FrTwoItemData data){
		items.add(data);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FrTwoItemView view;
		if(convertView == null){
			view=new FrTwoItemView(mContext);
		}else{
			view=(FrTwoItemView)convertView;
		}
		view.setData(items.get(position));
		
		return view;
	}

}

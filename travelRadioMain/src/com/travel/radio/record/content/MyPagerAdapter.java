package com.travel.radio.record.content;

import java.util.ArrayList;

import com.travel.radio.record.content.data.CImages;
import com.travel.radio.record.data.Images;
import com.travel.radio.record.data.Records;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
	

	ArrayList<CImages> items = new ArrayList<CImages>();
	Context mContext;
	
//	String imgUrl="http://cfile218.uf.daum.net/image/025DD14B5108ADE63495CA";

	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		FragmentOne f = new FragmentOne();
		Bundle b = new Bundle();
		b.putString(FragmentOne.PARAM_MESSAGE, items.get(position).imgName);
//		b.putString("abc", "abc");
		
		f.setArguments(b);
		
		return f;
	}
	
	public void add(ArrayList<CImages> images) {
		items.addAll(images);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
//		return items.size();
		return items.size();
	}

}

package com.travel.radio.menu.notice;

import com.travel.radio.main.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class Notice extends Fragment {

	ExpandableListView listView;
	MyAdapterNotice mAdapter;
	ActionBar actionBar;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		actionBar.setTitle("공지사항");
		
		return inflater.inflate(R.layout.side_notice, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		listView = (ExpandableListView) view
				.findViewById(R.id.expandableListView1);

		mAdapter = new MyAdapterNotice(getActivity());

		listView.setAdapter(mAdapter);

		listView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				ChildItemData data = (ChildItemData) mAdapter.getChild(
						groupPosition, childPosition);
				Toast.makeText(getActivity(), "child : " + data.title,
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		listView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				GroupItemData group = (GroupItemData) mAdapter
						.getGroup(groupPosition);
				return false;
			}
		});

		listView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {

			}
		});

//		listView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
//
//			@Override
//			public void onGroupCollapse(int groupPosition) {
//				listView.expandGroup(groupPosition);
//			}
//		});

		initData();

//		for (int i = 0; i < mAdapter.getGroupCount(); i++) {
//			listView.expandGroup(i);
//		}

	}

	private void initData() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				ChildItemData data = new ChildItemData();
				data.title = "child " + j;
				mAdapter.add("group" + i, data);
			}
		}
	}
}

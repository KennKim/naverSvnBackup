package com.travelradio.record;

import java.util.zip.Inflater;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.travelradio.adapter.MyAdapter;
import com.travelradio.data.ItemData;
import com.travelradio.main.MainActivity;
import com.travelradio.main.R;

public class MyLocationList extends Fragment {

	ListView listView;
	MyAdapter mAdapter;
	android.support.v7.app.ActionBar actionBar;
	

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		setHasOptionsMenu(true);
		actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		actionBar.setTitle("³» Áö¿ª");
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		
		return inflater.inflate(R.layout.record_list_layout, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		listView = (ListView) view.findViewById(R.id.listView1);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Object item = listView.getItemAtPosition(position);
				if (item != null) {
					if (item instanceof ItemData) {
						ItemData data = (ItemData) item;
						Toast.makeText(getActivity(), "item : " + data.place,
								Toast.LENGTH_SHORT).show();
					} else {
						String str = (String) item;
						Toast.makeText(getActivity(), "message : " + str,
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		mAdapter = new MyAdapter(getActivity());
		listView.setAdapter(mAdapter);

	}

	private void initData() {
		for (int i = 0; i < 5; i++) {
			ItemData d = new ItemData();
			d.userFace = R.drawable.ic_launcher;
			d.place = "place " + i;
			d.contentText = "contentText " + i;
			mAdapter.add(d);
		}
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_home, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_home) {
			Toast.makeText(getActivity(), "menu_home", Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}

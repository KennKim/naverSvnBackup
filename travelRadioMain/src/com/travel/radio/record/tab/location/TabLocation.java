package com.travel.radio.record.tab.location;

import com.travel.radio.blacklist.data.Block;
import com.travel.radio.main.MainActivity;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.PropertyManager;
import com.travel.radio.main.R;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.mybooth.other.OtherBooth;
import com.travel.radio.record.content.RecordContent;
import com.travel.radio.record.data.RecordResultList;
import com.travel.radio.record.data.Records;
import com.travel.radio.record.tab.location.LocationHeaderView.OnHeaderClickListener;
import com.travel.radio.record.tab.main.AdapterRecord;
import com.travel.radio.record.tab.main.AdapterRecord.OnAdapterItemClickListener;
import com.travel.radio.record.upload.RecordUpload;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TabLocation extends Fragment {
	

	String latitude="47.6254";
	String longitude="-122.33395";
	String radius="40";
	String page="1";
	
	ListView listView;
	TextView tvNone;
	AdapterRecord mAdapter;
 
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.tab_record_main, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		tvNone=(TextView)view.findViewById(R.id.textView1none);
		tvNone.setText("해당 지역에 레코드가 없습니다.");
		tvNone.setVisibility(view.GONE);

		listView = (ListView) view.findViewById(R.id.listView1);
		
		LocationHeaderView locHeaderView = new LocationHeaderView(getActivity());
		listView.addHeaderView(locHeaderView, "tabHeaderView", true);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(), "listclick", Toast.LENGTH_SHORT).show();
				Object item = listView.getItemAtPosition(position);
				if (item != null) {
					if (item instanceof Block) {
						Block data = (Block)item;
						Toast.makeText(getActivity(), "listclick", Toast.LENGTH_SHORT).show();
						
//						UnBlockDialogFragment dialog = new UnBlockDialogFragment(mAdapter, position);
//						Bundle b = new Bundle();
//						b.putString("userId", data.id);
//						dialog.setArguments(b);
//						dialog.show(getChildFragmentManager(), "dialog");
						
					} else {
						String str = (String) item;
						Toast.makeText(getActivity(), "message : " + str, Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		mAdapter = new AdapterRecord(getActivity());
		mAdapter.setOnAdapterItemClickListener(new OnAdapterItemClickListener() {

			@Override
			public void onAdapterPhotoItemClick(View v, Records records) {
				
//				Toast.makeText(getActivity(), "상세페이지로 이동",Toast.LENGTH_SHORT).show();
				String recordId = records.id;
				RecordContent recordContent = new RecordContent();
				Bundle b = new Bundle();
				b.putString("recordId", recordId);
				recordContent.setArguments(b);
				((MainActivity)getActivity()).change(recordContent);
			}

			@Override
			public void onAdapterFaceItemClick(View v, Records records) {
				
				Toast.makeText(getActivity(), "부스로 이동",Toast.LENGTH_SHORT).show();
				
				OtherBooth otherBooth = new OtherBooth();
				String userId = records.userId;
				Bundle b = new Bundle();
				b.putString("userId", userId);
				otherBooth.setArguments(b);
				
				((MainActivity)getActivity()).change(otherBooth);

			}
		});

		locHeaderView.setOnHeaderViewItemClickListener(new OnHeaderClickListener() {

			@Override
			public void onCheckBoxClick(LocationHeaderView view, Records data) {
//				Toast.makeText(getActivity(), "헤더클릭",Toast.LENGTH_SHORT).show();
				
			}
		});

		listView.setAdapter(mAdapter);

		
		Button btn = (Button)view.findViewById(R.id.button_upload);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RecordUpload recordUpload = new RecordUpload();
				((MainActivity)getActivity()).change(recordUpload);
			}
		});
		
		initData();

	}
	
	@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			initData();

		}
	
	private void initData() {
		
		latitude=PropertyManager.getInstance().getLatitude();
		longitude=PropertyManager.getInstance().getLongitude();
		radius=PropertyManager.getInstance().getRadius();
		
		NetworkManager.getInstance().getLocationRecord(getActivity(), latitude, longitude, radius, page, new OnResultListener<RecordResultList>() {

			@Override
			public void onSuccess(RecordResultList result) {
				if(result.success == 1){
//					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
					if(result.result.records.isEmpty()){
						tvNone.setVisibility(View.VISIBLE);
					}else{
						for (Records r : result.result.records) {
							mAdapter.add(r);
						}
					}
					
				}else{
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "LocationRecord_onFail", Toast.LENGTH_SHORT).show();
			}
		});
	}
}

package com.travel.radio.record.tab.sos;

import java.util.ArrayList;

import com.travel.radio.blacklist.data.Block;
import com.travel.radio.main.MainActivity;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.R;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.R.layout;
import com.travel.radio.mybooth.other.OtherBooth;
import com.travel.radio.record.content.RecordContent;
import com.travel.radio.record.data.RecordResultList;
import com.travel.radio.record.data.Records;
import com.travel.radio.record.data.Tags;
import com.travel.radio.record.tab.sos.AdapterSOS.OnAdapterItemClickListener;
import com.travel.radio.record.tab.sos.SOSHeaderView.OnHeaderClickListener;
import com.travel.radio.record.upload.RecordUpload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TabSOS extends Fragment {
	
	String latitude="47.6254";
	String longitude="-122.33395";
	String radius="40";
	String page="1";
	
	ListView listView;
	AdapterSOS mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.tab_sos, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		listView = (ListView) view.findViewById(R.id.listView1);

		SOSHeaderView tabHeaderView = new SOSHeaderView(getActivity());
		listView.addHeaderView(tabHeaderView, "tabHeaderView", true);
		
//		listView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				Toast.makeText(getActivity(), "listclick", Toast.LENGTH_SHORT).show();
//				Object item = listView.getItemAtPosition(position);
//				if (item != null) {
//					if (item instanceof Block) {
//						Block data = (Block)item;
//						Toast.makeText(getActivity(), "listclick", Toast.LENGTH_SHORT).show();
//						
////						UnBlockDialogFragment dialog = new UnBlockDialogFragment(mAdapter, position);
////						Bundle b = new Bundle();
////						b.putString("userId", data.id);
////						dialog.setArguments(b);
////						dialog.show(getChildFragmentManager(), "dialog");
//						
//					} else {
//						String str = (String) item;
//						Toast.makeText(getActivity(), "message : " + str, Toast.LENGTH_SHORT).show();
//					}
//				}
//			}
//		});
		
		mAdapter = new AdapterSOS(getActivity());
//		mAdapter.setOnAdapterItemClickListener(new OnAdapterItemClickListener() {
//
//			@Override
//			public void onAdapterPhotoItemClick(View v, Records records) {
//				
//				Toast.makeText(getActivity(), "상세페이지로 이동",Toast.LENGTH_SHORT).show();
////				String recordId = records.id;
////				RecordContent recordContent = new RecordContent();
////				Bundle b = new Bundle();
////				b.putString("recordId", recordId);
////				recordContent.setArguments(b);
////				((MainActivity)getActivity()).change(recordContent);
//			}
//
//			@Override
//			public void onAdapterFaceItemClick(View v, Records records) {
//				
////				Toast.makeText(getActivity(), "부스로 이동",Toast.LENGTH_SHORT).show();
//				
//				OtherBooth otherBooth = new OtherBooth();
//				String userId = records.userId;
//				Bundle b = new Bundle();
//				b.putString("userId", userId);
//				otherBooth.setArguments(b);
//				
//				((MainActivity)getActivity()).change(otherBooth);
//
//			}
//		});

		tabHeaderView.setOnHeaderViewItemClickListener(new OnHeaderClickListener() {

			@Override
			public void onCheckBoxClick(SOSHeaderView view, Records data) {
//				Toast.makeText(getActivity(), "헤더클릭",Toast.LENGTH_SHORT).show();
				
			}
		});

		listView.setAdapter(mAdapter);
		
		
		Button btn = (Button)view.findViewById(R.id.button_sos);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RecordUpload recordUpload = new RecordUpload();
				((MainActivity)getActivity()).change(recordUpload);
			}
		});
	}
	
	@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			initData();

		}
	
	private void initData() {
		

		ArrayList<Integer> tags = new ArrayList<Integer>();
		tags.add(6);
		
		NetworkManager.getInstance().getTagRecord(getActivity(), tags, new OnResultListener<RecordResultList>() {

			@Override
			public void onSuccess(RecordResultList result) {
				if(result.success == 1){
//					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
					
					for (Records r : result.result.records) {
						mAdapter.add(r);
					}
				}else{
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
				}				
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "SOS list_onFail", Toast.LENGTH_SHORT).show();
			}
		});
	}
}

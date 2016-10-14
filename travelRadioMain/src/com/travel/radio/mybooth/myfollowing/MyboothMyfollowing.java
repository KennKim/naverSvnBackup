package com.travel.radio.mybooth.myfollowing;

import com.travel.radio.main.MainActivity;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.PropertyManager;
import com.travel.radio.main.R;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.mybooth.MyBoothMain;
import com.travel.radio.mybooth.myfollowing.AdapterMyFollowing.OnAdapterItemClickListener;
import com.travel.radio.mybooth.other.OtherBooth;
import com.travel.radio.record.content.RecordContent;
import com.travel.radio.record.data.RecordResultList;
import com.travel.radio.record.data.Records;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MyboothMyfollowing extends Fragment {

	ListView listView;
	AdapterMyFollowing mAdapter;
	TextView tvNone;

	String userId;
	String page="1";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		if (b != null) {
			userId = b.getString("userId");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.mybooth_myfollowing, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		tvNone=(TextView)view.findViewById(R.id.textView1none);
		tvNone.setText("팔로잉이 없습니다.");
		tvNone.setVisibility(view.GONE);

		listView = (ListView) view.findViewById(R.id.listView1);
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,	int position, long id) {
			}
		});
		
		mAdapter = new AdapterMyFollowing(getActivity());
		mAdapter.setOnAdapterItemClickListener(new OnAdapterItemClickListener() {

			@Override
			public void onAdapterItemClick(View v, Records data) {
//				Toast.makeText(getActivity(), "상세페이지로 이동",Toast.LENGTH_SHORT).show();
				RecordContent recordContent = new RecordContent();
				Bundle b = new Bundle();
				b.putString("recordId", data.id);
				recordContent.setArguments(b);
				((MainActivity)getActivity()).change(recordContent);
			}

			@Override
			public void onAdapterFaceItemClick(View v, Records data) {
//				if(PropertyManager.getInstance().getUserId().equals(userId)){
//					
//					MyBoothMain myBoothMain = new MyBoothMain();
//					Bundle b = new Bundle();
//					b.putString("userId", userId);
//					myBoothMain.setArguments(b);
//					((MainActivity)getActivity()).change(myBoothMain);
//					
//				}else{
//					
//					OtherBooth otherBooth = new OtherBooth();
//					Bundle b = new Bundle();
//					b.putString("userId", userId);
//					otherBooth.setArguments(b);
//					((MainActivity)getActivity()).change(otherBooth);
//
//				}
			}
		});
		
		
		listView.setAdapter(mAdapter);

		initData();
		
	}
	
	private void initData() {
		
		NetworkManager.getInstance().getFollowRecordList(getActivity(), userId, page, new OnResultListener<RecordResultList>() {

			@Override
			public void onSuccess(RecordResultList result) {
				if(result.success == 1){
//					Toast.makeText(getActivity(), "팔로우", Toast.LENGTH_SHORT).show();
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
				Toast.makeText(getActivity(), "myfollowRecordList_onFail", Toast.LENGTH_SHORT).show();
				
			}
		});

		
	}
}

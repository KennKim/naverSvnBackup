package com.travel.radio.following;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.radio.following.AdapterFollowing.OnAdapterItemClickListener;
import com.travel.radio.following.data.Follow;
import com.travel.radio.following.data.FollowResultList;
import com.travel.radio.main.MainActivity;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.PropertyManager;
import com.travel.radio.main.R;
import com.travel.radio.mybooth.other.OtherBooth;

public class FollowingList extends Fragment {
	
	
	AdapterFollowing mAdapter;
	ActionBar actionBar;
	
	String userId;
	
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		
//		Bundle b = getArguments();
//		if(b!=null){
//			userId=b.getString("userId");
//		}
//	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		actionBar.setTitle("팔로잉 리스트");
		
		return inflater.inflate(R.layout.side_following_booth, container, false);
	}
	
	ListView listView;
	TextView tvNone;
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
			
		tvNone=(TextView)view.findViewById(R.id.textView1none);
		tvNone.setText("팔로잉 리스트가 없습니다.");
		tvNone.setVisibility(view.GONE);
		
		listView = (ListView) view.findViewById(R.id.listView1);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Object item = listView.getItemAtPosition(position);
				if (item != null) {
					if (item instanceof Follow) {
						Follow data = (Follow)item;
//						Toast.makeText(getActivity(), "item : "+data.name, Toast.LENGTH_SHORT).show();
						
						UnfollowDialogFragment dialog = new UnfollowDialogFragment(mAdapter, position);
						Bundle b = new Bundle();
						b.putString("userId", data.id);
						dialog.setArguments(b);
						dialog.show(getChildFragmentManager(), "dialog");
						
					} else {
						String str = (String) item;
//						Toast.makeText(getActivity(), "message : " + str, Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		mAdapter = new AdapterFollowing(getActivity());
		mAdapter.setOnAdapterItemClickListener(new OnAdapterItemClickListener() {

			@Override
			public void onAdapterItemClick(View v, Follow data) {
//				Toast.makeText(getActivity(), "like click : " + data.id,Toast.LENGTH_SHORT).show();
				
				OtherBooth otherBooth = new OtherBooth();
				Bundle b = new Bundle();
				b.putString("userId", data.id);
				otherBooth.setArguments(b);
				((MainActivity)getActivity()).change(otherBooth);
			}
		});
		listView.setAdapter(mAdapter);
		
		Toast toast = Toast.makeText(getActivity(), "리스트를 클릭하시면 언팔로우 할 수 있습니다.", Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		initData();
		
	}
	
	private void initData() {
		
		String userId = PropertyManager.getInstance().getUserId();
		
		NetworkManager.getInstance().getFollowList(getActivity(), userId, new OnResultListener<FollowResultList>() {
			
			@Override
			public void onSuccess(FollowResultList result) {
				if(result.success==1){
//					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
					if(result.result.users.isEmpty()){
						tvNone.setVisibility(View.VISIBLE);
					}else{
						for(Follow f : result.result.users){
							mAdapter.add(f);
						}
					}
				}else{
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
				}		
			}
			
			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "followlist_onfail", Toast.LENGTH_SHORT).show();
//				Log.v("test", "Fail code" + code);
				
			}
		});
	}
}

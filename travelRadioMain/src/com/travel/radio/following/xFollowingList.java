//package com.travel.radio.following;
//
//import java.util.ArrayList;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.ActionBarActivity;
//import android.util.SparseBooleanArray;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.travel.radio.following.xAdapterFollowinglist.OnAdapterItemClickListener;
//import com.travel.radio.following.data.Follow;
//import com.travel.radio.following.data.FollowResultList;
//import com.travel.radio.main.MainActivity;
//import com.travel.radio.main.NetworkManager;
//import com.travel.radio.main.NetworkManager.OnResultListener;
//import com.travel.radio.main.R;
//import com.travel.radio.mybooth.other.OtherBooth;
//
//public class xFollowingList extends Fragment {
//	
//	
//	xAdapterFollowinglist mAdapter;
//	ActionBar actionBar;
//	
//	String userId;
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		
//		Bundle b = getArguments();
//		if (b != null) {
//			userId = b.getString("userId");
//		}
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater,
//			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//		
//		actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
//		actionBar.setTitle("팔로잉 리스트");
//		
//		return inflater.inflate(R.layout.side_following_booth, container, false);
//	}
//	
//	ListView listView;
//	TextView tvNone;
//	
//	@Override
//	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//		super.onViewCreated(view, savedInstanceState);
//		
//		tvNone=(TextView)view.findViewById(R.id.textView1none);
//		tvNone.setText("팔로잉 리스트가 없습니다.");
//		tvNone.setVisibility(view.GONE);
//		
//		listView = (ListView) view.findViewById(R.id.listView1);
//		listView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				
//				Object item = listView.getItemAtPosition(position);
//				
//				if (item != null) {
//					if (item instanceof Follow) {
//						
//						Follow data = (Follow) item;
//						Toast.makeText(getActivity(), "name : " + data.name,
//								Toast.LENGTH_SHORT).show();
//						OtherBooth anonymous = new OtherBooth();
//						((MainActivity)getActivity()).change(anonymous);
//
//						
//					} else {
//						String str = (String) item;
//						Toast.makeText(getActivity(), "message : " + str,
//								Toast.LENGTH_SHORT).show();
//					}
//				}
//			}
//		});
//		mAdapter = new xAdapterFollowinglist(getActivity());
////		mAdapter.setOnAdapterItemClickListener(new OnAdapterItemClickListener() {
////			
////			@Override
////			public void onAdapterItemClick(View v, Follow data) {
////				Toast.makeText(getActivity(), "해당유저페이지로 이동",Toast.LENGTH_SHORT).show();
////				OtherBooth otherBooth = new OtherBooth();
////				((MainActivity)getActivity()).change(otherBooth);
////			}
////
////			@Override
////			public void onAdapterCheckItemClick(View v, Follow data) {
////				Toast.makeText(getActivity(), "체크박스 클릭",Toast.LENGTH_SHORT).show();
////				
////				UnfollowDialogFragment dialog = new UnfollowDialogFragment();
////				
////				
////				Bundle b = new Bundle();
////				b.putString("userId", data.userId);
////				dialog.setArguments(b);
////				
////				dialog.show(getChildFragmentManager(), "dialog");
////			}
////		});
//		
////		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//		listView.setAdapter(mAdapter);
//		
////		btn=(Button)view.findViewById(R.id.button1submit);
////		btn.setOnClickListener(new View.OnClickListener() {
////			
////			@Override
////			public void onClick(View v) {
////				SparseBooleanArray array = mAdapter.getSelected();
////				ArrayList<Follow> list = new ArrayList<Follow>();
////				for (int i = 0; i < array.size(); i++) {
////					int position = array.keyAt(i);
////					if (array.get(position)) {
////						Follow f = (Follow)listView.getItemAtPosition(position);
////						list.add(f);
////					}
////				}
////				
////				UnfollowDialogFragment dialog = new UnfollowDialogFragment();
////				
////				dialog.setFollowList(list);
////				dialog.show(getChildFragmentManager(), "dialog");
////				
////			}
////		});
//
//		
//	}
//	
//	@Override
//	public void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		
//		initData();
//
//	}
//	
//	private void initData() {
//		
//		NetworkManager.getInstance().getFollowList(getActivity(), new OnResultListener<FollowResultList>() {
//			
//			@Override
//			public void onSuccess(FollowResultList result) {
//				if(result.success==1){
////					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
//					if(result.result.users.isEmpty()){
//						tvNone.setVisibility(View.VISIBLE);
////						btn.setVisibility(View.GONE);
//					}else{
//						for(Follow f : result.result.users){
//							mAdapter.add(f);
//						}
//					}
//				}else{
//					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
//				}		
//			}
//			
//			@Override
//			public void onFail(int code) {
//				Toast.makeText(getActivity(), "followlist_onfail", Toast.LENGTH_SHORT).show();
//				
//			}
//		});
//	}
//}

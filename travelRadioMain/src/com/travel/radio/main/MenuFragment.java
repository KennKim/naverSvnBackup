package com.travel.radio.main;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends Fragment {

	public static final int MENU_MAIN = 0;
	public static final int MENU_ONE = 1;
	public static final int MENU_TWO = 2;
	public static final int MENU_THREE = 3;
	public static final int MENU_FOUR = 4;
	public static final int MENU_FIVE = 5;
	public static final int MENU_SIX = 6;
	public static final int MENU_SEVEN = 7;
	public static final int MENU_EIGHT = 8;
	public static final int MENU_NINE = 9;
	public static final int MENU_TEN = 10;
	public static final int MEMBER_JOIN = 20;
	public static final int MEMBER_MODIFY = 21;
	public static final int MEMBER_SEARCH = 22;
	public static final int MEMBER_LOGIN = 23;
	
	
	public interface MenuClickListener {
		public void selectMenu(int menu);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, new ArrayList<String>());
		initData();
	}
	
	private void initData() {
		mAdapter.add("HOME");
		mAdapter.add("나의 부스");
		mAdapter.add("팔로잉 부스");
		mAdapter.add("무전함");
		
		mAdapter.add("메세지함");
		mAdapter.add("위치 설정");
//		mAdapter.add("알림 설정");
		mAdapter.add("차단 리스트 관리");
		
		mAdapter.add("로그인/로그아웃");
//		mAdapter.add("공지사항");
		mAdapter.add("Travel Radio Ver0.1");
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_menu, container, false);
	}
	
	ListView listView;
	ArrayAdapter<String> mAdapter;
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		listView = (ListView)view.findViewById(R.id.listView1);
		listView.setAdapter(mAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Activity activity = getActivity();
				if (activity instanceof MenuClickListener) {
					((MenuClickListener)activity).selectMenu(position);
				}				
			}
		});
	}
}

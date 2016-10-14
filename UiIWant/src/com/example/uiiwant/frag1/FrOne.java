package com.example.uiiwant.frag1;

import com.example.uiiwant.R;
import com.example.uiiwant.frag1.data.FrOneItemData;
import com.example.uiiwant.frag1.user.UserMain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class FrOne extends Fragment{
	
	private ActionBar actionBar;
	private EditText et;
	private ListView list;
	private FrOneAdapter oneAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
//		actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//		actionBar.setTitle("칭구");
//		actionBar.setDisplayHomeAsUpEnabled(false);
//		actionBar.setHomeButtonEnabled(false);
	}
	
//	@Override
//	public void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//		actionBar.setTitle("칭구");
//	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fr_1, container, false);
		
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		et=(EditText)view.findViewById(R.id.editText1);
		list=(ListView)view.findViewById(R.id.listView1);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
//				Log.i("abc", "FrOne List Click~!!");
				Intent intent = new Intent(getActivity(),UserMain.class);
				startActivityForResult(intent, 1);

			}
		});
		
		HeaderViewEditText header_edit = new HeaderViewEditText(getContext());
		header_edit.editText.setHint(R.string.user_list_edit_hint);
		list.addHeaderView(header_edit);
		
		HeaderViewListTitle header_title1 = new HeaderViewListTitle(getContext());
		header_title1.textView.setText(R.string.user_list_title1);
		list.addHeaderView(header_title1);
		
		HeaderViewProfile header_profile = new HeaderViewProfile(getContext());
		list.addHeaderView(header_profile);
		
		HeaderViewListTitle header_title2 = new HeaderViewListTitle(getContext());
		header_title2.textView.setText(R.string.user_list_title3);
		list.addHeaderView(header_title2);
		
		
		oneAdapter = new FrOneAdapter(getContext());
		
		list.setAdapter(oneAdapter);
		
		initData();
		
		
	}
	
	private void initData(){
		
		for (int i = 0; i < 20; i++){
			
			FrOneItemData data = new FrOneItemData();
			
			data.userImg = R.drawable.fuckyou;
			data.userName = "name" + i;
			data.userComment = "comment" + i;
			
			oneAdapter.add(data);
			
		}
		
	}
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fr_one, menu);
	}
	
	
}

package com.travel.radio.member.pwsearch;

import com.travel.radio.main.MainActivity;
import com.travel.radio.main.MenuFragment;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.PropertyManager;
import com.travel.radio.main.R;
import com.travel.radio.member.data.MyUserResultList;
import com.travel.radio.member.join.MemberJoin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class MemberPWsearch extends Fragment {

	ActionBar actionBar;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
		actionBar.setTitle("비밀번호 찾기");

		return inflater.inflate(R.layout.menu_member_pw_search, container,
				false);
	}

	EditText etEmail;
	EditText etName;
	TextView tvRejoin;

	String email;
	String name;

	boolean emailOk;
	boolean nameOk;

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		etEmail = (EditText)view.findViewById(R.id.editText2join_email);
		etEmail.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				email=etEmail.getText().toString();
				if(!email.matches(".*@.*")){
					Toast toast=Toast.makeText(getActivity(), "이메일이 형식이 맞지 않습니다.", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					emailOk=false;
				}else{
					emailOk=true;
				}
				return false;
			}
		});
		
		etName = (EditText)view.findViewById(R.id.editText3join_name);	
		etName.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				name=etName.getText().toString();
				if(name==null || name.equals("")){
					Toast toast=Toast.makeText(getActivity(), "한글자이상 적어주세요", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					nameOk=false;
				}else{
					nameOk=true;
				}
				return false;
			}
		});
		
		Button btn = (Button)view.findViewById(R.id.button1password_search);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				email = etEmail.getText().toString();
				name = etName.getText().toString();
				
				if (!email.matches(".*@.*")) {
					Toast toast = Toast.makeText(getActivity(),
							"이메일이 형식이 맞지 않습니다.", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					emailOk = false;
				}else if (name == null || name.equals("")) {
					Toast toast = Toast.makeText(getActivity(), "한글자이상 적어주세요",
							Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					nameOk = false;
				}
				
				if(emailOk==true && nameOk==true){
					
					NetworkManager.getInstance().userFindPW(getActivity(), email, new OnResultListener<MyUserResultList>() {
						
						@Override
						public void onSuccess(MyUserResultList result) {
							if(result.success==1){
								Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
							}else{
								Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
							}								
						}
						@Override
						public void onFail(int code) {
							Toast.makeText(getActivity(), "PWsearch_onfail", Toast.LENGTH_SHORT).show();
						}
					});
				}
				}
		});

		
		tvRejoin=(TextView)view.findViewById(R.id.textView2rejoin);
		tvRejoin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity)getActivity()).selectMenu(MenuFragment.MEMBER_JOIN);
			}
		});

	
	}
}

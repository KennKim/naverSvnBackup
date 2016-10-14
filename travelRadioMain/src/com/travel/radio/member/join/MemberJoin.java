package com.travel.radio.member.join;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.travel.radio.main.MainActivity;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.R;
import com.travel.radio.member.data.MyUserResultList;
import com.travel.radio.record.TabMain;

public class MemberJoin extends Fragment {

	ActionBar actionBar;
	
	EditText etEmail;
	EditText etPw;
	EditText etPwcheck;
	EditText etName;
	CheckBox cbAgree;
	
	String email;
	String password;
	String pwCheck;
	String name;
	
	boolean emailOk = true;
	boolean passwordOk = true;
	boolean pwCheckOk = true;
	boolean cbCheckOk = true;
	boolean nameOk = true;
	
	boolean overEmailOk;
	boolean overNameOk;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		actionBar.setTitle("회원가입");
		
		return inflater.inflate(R.layout.menu_member_join, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		etEmail = (EditText)view.findViewById(R.id.editText2join_email);
		etEmail.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				checkEmail();
				return false;
			}
		});
		
		etPw = (EditText)view.findViewById(R.id.editText2join_password);
		etPw.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				checkPw();
				return false;
			}
		});
		
		etPwcheck = (EditText)view.findViewById(R.id.editText4join_pwcheck);
		etPwcheck.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				checkPwCheck();
				return false;
			}
		});
		
		etName = (EditText)view.findViewById(R.id.editText3join_name);
		etName.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				checkName();
				return false;
			}
		});
		
		cbAgree = (CheckBox)view.findViewById(R.id.checkBox1join_agree);
		cbAgree.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked==true){
					Toast.makeText(getActivity(), "동의하셨습니다.", Toast.LENGTH_SHORT).show();
					cbCheckOk=true;
				}else{
					Toast.makeText(getActivity(), "동의가 해제되었습니다.", Toast.LENGTH_SHORT).show();
					cbCheckOk=false;
				}
			}
		});
		
		
		
		Button btn = (Button)view.findViewById(R.id.button1join_login);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				email = etEmail.getText().toString();
				password = etPw.getText().toString();
				pwCheck = etPwcheck.getText().toString();
				name = etName.getText().toString();

				if (!email.matches(".*@.*")) {
					Toast toast = Toast.makeText(getActivity(),
							"이메일이 형식이 맞지 않습니다.", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					emailOk = false;
				}else if (password.length() < 6) {
					Toast toast = Toast.makeText(getActivity(), "6자리이상 적어주세요.",
							Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					passwordOk = false;
				}else if (!pwCheck.equals(password)) {
					Toast toast = Toast.makeText(getActivity(), "Password가 일치하지 않습니다.",
							Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					pwCheckOk = true;
				}else if (name == null || name.equals("")) {
					Toast toast = Toast.makeText(getActivity(), "한글자이상 적어주세요",
							Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					nameOk = false;
				}
				
				overCheckEmail();
				overCheckName();

				if(emailOk == true && passwordOk == true && pwCheckOk == true && cbCheckOk == true &&overEmailOk==true&&overNameOk==true){
					setUserJoinAction();
					userLoginAction();
				}
			}
		});
	}
	
	private void setUserJoinAction() {
		
		NetworkManager.getInstance().setUserJoin(getActivity(), email, password, name, new OnResultListener<MyUserResultList>() {
			
			@Override
			public void onSuccess(MyUserResultList result) {
				
				if(result.success == 1){
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "join_onFail", Toast.LENGTH_SHORT).show();

			}
		});

	}

	private void userLoginAction() {
//		NetworkManager.getInstance().userLogin(getActivity(), email, password,
//				new OnResultListener<MyUserResultList>() {
//
//					@Override
//					public void onSuccess(MyUserResultList result) {
//						if (result.success == 1) {
//							Toast.makeText(getActivity(), result.result.msg,Toast.LENGTH_SHORT).show();
							TabMain tabMain = new TabMain();
							((MainActivity)getActivity()).change(tabMain);
//						}
//					}
//
//					@Override
//					public void onFail(int code) {
//						Toast.makeText(getActivity(), "login_onFail",Toast.LENGTH_SHORT).show();
//					}
//				});

	}
	
	public void checkEmail() {
		// TODO Auto-generated method stub
		email=etEmail.getText().toString();
		if(!email.matches(".*@.*")){
			Toast toast=Toast.makeText(getActivity(), "이메일이 형식이 맞지 않습니다.", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			emailOk=false;
		}else{
			emailOk=true;
		}
	}
	
	public void checkPw() {
		// TODO Auto-generated method stub
		password=etPw.getText().toString();
		if(password.length()<6){
			Toast toast=Toast.makeText(getActivity(), "6자리이상 적어주세요.", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			passwordOk=false;
		}else{
			passwordOk=true;
		}
	}
	
	public void checkPwCheck() {
		// TODO Auto-generated method stub
		pwCheck=etPwcheck.getText().toString();
		if(!pwCheck.equals(password)){
			Toast toast=Toast.makeText(getActivity(), "Password가 일치하지 않습니다.", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			pwCheckOk=false;
		}else{
			pwCheckOk=true;
		}
	}
	
	public void checkName() {
		// TODO Auto-generated method stub
		name=etName.getText().toString();
		if(name==null || name.equals("")){
			Toast toast=Toast.makeText(getActivity(), "한글자이상 적어주세요", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			nameOk=false;
		}else{
			nameOk=true;
		}
	}
	
	public void overCheckEmail(){
		NetworkManager.getInstance().getcheckEmail(getActivity(), email, new OnResultListener<MyUserResultList>() {

			@Override
			public void onSuccess(MyUserResultList result) {
				if(result.success==1){
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
					overEmailOk=true;
				}else{
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
					overEmailOk=false;
				}
			}
			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "Email_onFail", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public void overCheckName(){
		
		NetworkManager.getInstance().getCheckUserName(getActivity(), name, new OnResultListener<MyUserResultList>() {

			@Override
			public void onSuccess(MyUserResultList result) {
				if(result.success==1){
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
					overNameOk=true;
				}else{
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
					overNameOk=false;
				}
			}
			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "onFail", Toast.LENGTH_SHORT).show();
			}
		});
	}
}

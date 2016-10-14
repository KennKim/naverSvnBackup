package com.travel.radio.member.login;

import android.content.Intent;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.travel.radio.main.MainActivity;
import com.travel.radio.main.MenuFragment;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.PropertyManager;
import com.travel.radio.main.R;
import com.travel.radio.member.data.MyUserResultList;
import com.travel.radio.member.join.MemberJoin;
import com.travel.radio.member.pwsearch.MemberPWsearch;

public class MemberLogin extends Fragment {
	
	ActionBar actionBar;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		actionBar.setTitle("로그인");
		
		return inflater.inflate(R.layout.menu_member_login, container, false);
	}

	EditText etEmail;
	EditText etPw;
	TextView tvJoin;
	TextView tvPWSearch;
	CheckBox cbAutoLogin;
	
	String email;
	String password;
	
	boolean emailOk;
	boolean passwordOk;
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		etEmail = (EditText)view.findViewById(R.id.editText1login_email);
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
		
		etPw = (EditText)view.findViewById(R.id.editText1login_password);
		etPw.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
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
				return false;
			}
		});
		
		
		Button btn = (Button)view.findViewById(R.id.button1login_login);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				email=etEmail.getText().toString();
				if(!email.matches(".*@.*")){
					Toast toast=Toast.makeText(getActivity(), "이메일이 형식이 맞지 않습니다.", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					emailOk=false;
				}else{
					emailOk=true;
				}
				
				password=etPw.getText().toString();
				if(password.length()<6){
					Toast toast=Toast.makeText(getActivity(), "6자리이상 적어주세요.", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					passwordOk=false;
				}else{
					passwordOk=true;
				}				
				

				if(emailOk==true && passwordOk==true){
					
					NetworkManager.getInstance().userLogin(getActivity(), email, password, new OnResultListener<MyUserResultList>() {

						@Override
						public void onSuccess(MyUserResultList result) {
							if(result.success == 1){
								Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
								PropertyManager.getInstance().setUserId(result.result.myUser.id);
								PropertyManager.getInstance().setEmail(email);
								PropertyManager.getInstance().setPassword(password);
								PropertyManager.getInstance().setName(result.result.myUser.name);
								PropertyManager.getInstance().setImg(result.result.myUser.img);
								PropertyManager.getInstance().setBestUser(result.result.myUser.bestUser);
//								TabMain tabMain = new TabMain();
//								MemberModify memberModify = new MemberModify();
								((MainActivity)getActivity()).selectMenu(MenuFragment.MEMBER_MODIFY);
							}else{
								Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
							}
						}

						@Override
						public void onFail(int code) {
							// TODO Auto-generated method stub
							Toast.makeText(getActivity(), "onFail", Toast.LENGTH_SHORT).show();

						}
					});
				}
			}
		});
		
		btn=(Button)view.findViewById(R.id.button_login_facebook);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Session.openActiveSession(getActivity(), MemberLogin.this, true, new StatusCallback() {
					
					@Override
					public void call(Session session, SessionState state, Exception exception) {
						if (session.isOpened()) {
							String token = session.getAccessToken();
							// NetworkManager... token을 넘겨주면 서버에서 처리.
						}
					}
				});
			}
		});
		
		
		tvJoin =(TextView)view.findViewById(R.id.textView2join);
		tvJoin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MemberJoin memberJoin = new MemberJoin();
				((MainActivity)getActivity()).change(memberJoin);
			}
		});
		
		tvPWSearch =(TextView)view.findViewById(R.id.textView3pwsearch);
		tvPWSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MemberPWsearch memberPWsearch = new MemberPWsearch();
				((MainActivity)getActivity()).change(memberPWsearch);				
			}
		});
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (Session.getActiveSession() != null) {
			Session.getActiveSession().onActivityResult(getActivity(), requestCode, resultCode, data);
		}
	}
	
}

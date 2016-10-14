package com.travel.radio.member.login;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.MainActivity;
import com.travel.radio.main.MenuFragment;
import com.travel.radio.main.PropertyManager;
import com.travel.radio.main.R;
import com.travel.radio.member.data.MyUserResultList;
import com.travel.radio.record.TabMain;
import com.travel.radio.voice.VoiceRecordMachine;
import com.travel.radio.voice.googlemap.GoogleMapActivity;

public class MemberModify extends Fragment {
	
	public static final int REQUEST_CODE_CROP = 0;

	ActionBar actionBar;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		actionBar.setTitle("회원정보수정");
		
		return inflater.inflate(R.layout.menu_member_modify, container, false);
	}
	
	
	ImageView ivFace;
	ImageView ivLocation;
	TextView tvLocation;
	EditText etEmail;
	EditText etPw;
	EditText etPwcheck;
	EditText etName;
	EditText etLocation;
	
	
	TextView tvGetOut;
	TextView tvInitial;

	String password;
	String pwCheck;
	String name;
	
	boolean passwordOk=true;
	boolean pwCheckOk=true;
	boolean nameOk=true;
	
	

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		ivFace=(ImageView)view.findViewById(R.id.imageView1photo);
		ivFace.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				imageUpload();
			}
		});
		
		
		ivLocation=(ImageView)view.findViewById(R.id.imageView1location_icon);
		ivLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myLocationSetting();
				
			}
		});
		tvLocation=(TextView)view.findViewById(R.id.textView2location_text);
		tvLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myLocationSetting();
				
			}
		});
		
//		etEmail = (EditText)view.findViewById(R.id.editText2join_email);
		etPw = (EditText)view.findViewById(R.id.editText2join_password);
		etPw.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
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
		
		etPwcheck = (EditText)view.findViewById(R.id.editText4join_pwcheck);
		etPwcheck.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				pwCheck=etPwcheck.getText().toString();
				if(!pwCheck.equals(password)){
					Toast toast=Toast.makeText(getActivity(), "Password가 일치하지 않습니다.", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					pwCheckOk=false;
				}else{
					pwCheckOk=true;
				}
				return false;
			}
		});
		
		etName = (EditText)view.findViewById(R.id.editText3join_name);
		
		Button btn=(Button)view.findViewById(R.id.button1modify);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				userModify();
				
				if(passwordOk==true && pwCheckOk==true && nameOk==true){
					
				}

			}
		});
		
		etLocation=(EditText)view.findViewById(R.id.editText3join_mylocation);
		etLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myLocationSetting();
			}
		});
		
		btn=(Button)view.findViewById(R.id.button1logout);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				NetworkManager.getInstance().userLogOut(getActivity(), new OnResultListener<MyUserResultList>() {
					
					@Override
					public void onSuccess(MyUserResultList result) {
						if(result.success==1){
							Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
							
							PropertyManager.getInstance().initializing();
							((MainActivity)getActivity()).selectMenu(MenuFragment.MENU_MAIN);
						}else{
							Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
						}
					}
					
					@Override
					public void onFail(int code) {
						Toast.makeText(getActivity(), "logout_onFail", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
		
		tvGetOut=(TextView)view.findViewById(R.id.textView2getout);
		tvGetOut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				NetworkManager.getInstance().setUserDelete(getActivity(), new OnResultListener<MyUserResultList>() {

					@Override
					public void onSuccess(MyUserResultList result) {
						if(result.success==1){
							Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
							PropertyManager.getInstance().initializing();
							((MainActivity)getActivity()).selectMenu(MenuFragment.MENU_EIGHT);
						}else{
							Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
						}
					}

					@Override
					public void onFail(int code) {
						Toast.makeText(getActivity(), "userDelete_onFail", Toast.LENGTH_SHORT).show();

					}
				});
			}
		});
		
		
		mLoader=ImageLoader.getInstance();
		
		initData();
	}

	ImageLoader mLoader;
	
	private void initData() {
		
		etLocation.setText(PropertyManager.getInstance().getUserPlace());

		String userId=PropertyManager.getInstance().getUserId();
		
		NetworkManager.getInstance().getUserList(getActivity(), userId, new OnResultListener<MyUserResultList>() {

			@Override
			public void onSuccess(MyUserResultList result) {
				if(result.success==1){
//					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
					PropertyManager.getInstance().setName(result.result.myUser.name);
					PropertyManager.getInstance().setImg(result.result.myUser.img);
					PropertyManager.getInstance().setBestUser(result.result.myUser.bestUser);
					
					etName.setText(result.result.myUser.name);
					mLoader.displayImage(result.result.myUser.img, ivFace);
				}else{
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
				}				
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "userInfo_onFail", Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	
	private void myLocationSetting() {

		Intent intent = new Intent(getActivity(),GoogleMapActivity.class);
		intent.putExtra("latitude",PropertyManager.getInstance().getLatitude());   
		intent.putExtra("longitude",PropertyManager.getInstance().getLongitude()); 
		startActivity(intent);
	}

	private void userModify() {
		
		String password = etPw.getText().toString();
		String name = etName.getText().toString();
		String userPlace = PropertyManager.getInstance().getUserPlace();
		String latitude = PropertyManager.getInstance().getLatitude();
		String longitude = PropertyManager.getInstance().getLongitude();
		
		
		NetworkManager.getInstance().setUserModify(getActivity(), mSavedFile, password, name, userPlace, latitude, longitude, new OnResultListener<MyUserResultList>() {

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
				// TODO Auto-generated method stub
				
			}
			
		});

	}
	/**
	 * 이미지 올리기
	 */
	private void imageUpload() {
		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		photoPickerIntent.setType("image/*");
		photoPickerIntent.putExtra("crop", "true");
		photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
		photoPickerIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		startActivityForResult(photoPickerIntent, REQUEST_CODE_CROP);
	}

	private Uri getTempUri() {
		mSavedFile = new File(Environment.getExternalStorageDirectory(),"temp_" + System.currentTimeMillis() / 1000);
		return Uri.fromFile(mSavedFile);
	}
	
	
	File mSavedFile;
	String filepath;

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
			Bitmap bm = BitmapFactory.decodeFile(mSavedFile.getAbsolutePath());
			filepath=mSavedFile.getAbsolutePath();
			ivFace.setImageBitmap(bm);
			
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			String file = savedInstanceState.getString("filename");
			if (file != null) {
				mSavedFile = new File(file);
			}
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mSavedFile != null) {
			outState.putString("filename", mSavedFile.getAbsolutePath());
		}
		outState.putString("filearray", filepath);
	}
	
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//		if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
//			Uri uri = data.getData();
//			imageView.setImageURI(uri);
//		}
//		
//	}


}

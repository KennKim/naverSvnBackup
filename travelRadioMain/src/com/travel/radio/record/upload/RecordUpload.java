package com.travel.radio.record.upload;

import java.io.File;
import java.util.ArrayList;

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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.travel.radio.main.MenuFragment;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.MainActivity;
import com.travel.radio.main.PropertyManager;
import com.travel.radio.main.R;
import com.travel.radio.record.data.Images;
import com.travel.radio.record.data.RecordResultList;
import com.travel.radio.voice.VoiceRecordMachine;
import com.travel.radio.voice.data.VoiceResultList;
import com.travel.radio.voice.googlemap.GoogleMapActivity;

public class RecordUpload extends Fragment {

	ActionBar actionBar;
	File mSavedFile;
	public static final int REQUEST_CODE_CROP = 0;
	private final static int REQUEST_CODE_RECORDING = 1;

//	boolean imageOn1, imageOn2, imageOn3 = true;
 	private final static int REQUEST_CODE_MY_ACTIVITY = 0;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
		actionBar.setTitle("레코드 올리기");

		return inflater.inflate(R.layout.record_upload, container, false);
	}

	EditText etContent;
	ImageView ivLink;
	EditText etLink;
	ImageView[] ivPhoto = new ImageView[3];
	String[] filepath = new String[3];
	int imageIndex = 0;
	ImageView ivVoiceOn;
	TextView tvVoiceName;
	TextView tvLocation;
	ImageView ivLocationSetting;

	LinearLayout linearLink;
	LinearLayout linearVoice;

	CheckBox checkBPlace;
	CheckBox checkBFood;
	CheckBox checkBEnjoy;
	CheckBox checkBStay;
	CheckBox checkBEtc;
	CheckBox checkBsos;

	@Override
	public void onViewCreated(final View view,
			@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		Button btn = (Button) view.findViewById(R.id.button3camera);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				CameraDialogFragment dialog = new CameraDialogFragment();
//				dialog.show(getFragmentManager(),"dialog");
				
				startCamera();
			}
		});

		btn = (Button) view.findViewById(R.id.button3voice);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				linearVoice.setVisibility(view.VISIBLE);
				Intent intent = new Intent(getActivity(),VoiceRecordMachine.class);
				startActivityForResult(intent, REQUEST_CODE_RECORDING);
				
			}
		});

		btn = (Button) view.findViewById(R.id.button3link);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				linearLink.setVisibility(view.VISIBLE);
			}
		});

		etContent = (EditText) view.findViewById(R.id.editText1content);
		etContent.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

				return false;
			}
		});

		ivPhoto[0] = (ImageView) view.findViewById(R.id.imageView_up1);
		ivPhoto[0].setTag((Integer) 0);
		ivPhoto[0].setOnClickListener(mImageListener);
		if (filepath[0] != null) {
			Bitmap bm = BitmapFactory.decodeFile(filepath[0]);
			ivPhoto[0].setImageBitmap(bm);
		}

		ivPhoto[1] = (ImageView) view.findViewById(R.id.imageView_up2);
		ivPhoto[1].setTag((Integer) 1);
		ivPhoto[1].setOnClickListener(mImageListener);
		if (filepath[0] != null) {
			Bitmap bm = BitmapFactory.decodeFile(filepath[0]);
			ivPhoto[0].setImageBitmap(bm);
		}
		
		ivPhoto[2] = (ImageView) view.findViewById(R.id.imageView_up3);
		ivPhoto[2].setTag((Integer) 2);
		ivPhoto[2].setOnClickListener(mImageListener);
		if (filepath[0] != null) {
			Bitmap bm = BitmapFactory.decodeFile(filepath[0]);
			ivPhoto[0].setImageBitmap(bm);
		}
		

		linearLink = (LinearLayout) view.findViewById(R.id.link_linear);
		linearLink.setVisibility(view.GONE);

		etLink = (EditText) view.findViewById(R.id.editText2link);

		linearVoice = (LinearLayout) view.findViewById(R.id.voice_linear);
		linearVoice.setVisibility(view.GONE);

		tvLocation = (TextView) view.findViewById(R.id.textView2location);
		tvLocation.setText(PropertyManager.getInstance().getUserPlace());
		
		ivLocationSetting=(ImageView)view.findViewById(R.id.imageView_locationsetting);
		ivLocationSetting.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),GoogleMapActivity.class);
				startActivity(intent);
				
			}
		});

		btn = (Button) view.findViewById(R.id.button6record_send);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				checkboxIsChecked();
				
//				if(count!=0){
//					images = new String[count];
//					
//					switch (count) {
//					case 0:
//						break;
//					case 1:
//						images[0]=a;
//						break;
//					case 2:
//						images[0]=a;
//						images[1]=b;
//						break;
//					case 3:
//						images[0]=a;
//						images[1]=b;
//						images[2]=c;
//						break;
//					}
//				}
				if(filepath[0]==null){
					Toast.makeText(getActivity(), "이미지를 올려주세요.", Toast.LENGTH_SHORT).show();
				}else if(etContent.getText().toString().equals("") || etContent.getText().toString()==null){
					Toast.makeText(getActivity(), "텍스트를 입력해주세요.", Toast.LENGTH_SHORT).show();
				}else{
					recordSubmit();				
				}
				
			}
		});

		checkBPlace = (CheckBox) view.findViewById(R.id.checkBox1place);
		checkBPlace.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					
				}
			}
		});
		checkBFood = (CheckBox) view.findViewById(R.id.checkBox3cook);
		checkBEnjoy = (CheckBox) view.findViewById(R.id.checkBox4enjoy);
		checkBStay = (CheckBox) view.findViewById(R.id.checkBox2stay);
		checkBEtc = (CheckBox) view.findViewById(R.id.checkBox5etc);
		checkBsos = (CheckBox) view.findViewById(R.id.checkBox6sos);

		if (savedInstanceState != null) {
			String file = savedInstanceState.getString("filename");
			if (file != null) {
				mSavedFile = new File(file);
			}
		}
		
//		voice = new File(outputFile);
		
	}
	
	File voice;
	String outputFile;
	
	String content;
	String link;
	String latitude;
	String longitude;
	String recordPlace;
	
	int [] tags;

	private void recordSubmit() {
		
		content = etContent.getText().toString();
		link = etLink.getText().toString();
		latitude = PropertyManager.getInstance().getLatitude();
		longitude=PropertyManager.getInstance().getLongitude();
//		Integer[] tags = {1,2,3};
		recordPlace=PropertyManager.getInstance().getUserPlace();
		
		
		NetworkManager.getInstance().setRecordWrite(getActivity(), content, outputFile, filepath, link, latitude, longitude, tags, recordPlace, new OnResultListener<RecordResultList>() {
			
			@Override
			public void onSuccess(RecordResultList result) {
				if(result.success==1){
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
					((MainActivity)getActivity()).selectMenu(MenuFragment.MENU_MAIN);
				}else{
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
				}				
			}
			
			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "write_onfail", Toast.LENGTH_SHORT).show();
				
			}
		});
	}

	OnClickListener mImageListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			imageIndex = (Integer) v.getTag();
			imageUpload();
		}
	};

	/**
	 * 체크박스 체크여부
	 */
	private void checkboxIsChecked() {
		

		tags = new int[6];
		
		if(checkBPlace.isChecked()==true){
			tags[0]=1;
		}else{
			tags[0]=0;
		}
		if(checkBFood.isChecked()==true){
			tags[1]=2;
		}else{
			tags[1]=0;
		}
		if(checkBEnjoy.isChecked()==true){
			tags[2]=3;
		}else{
			tags[2]=0;
		}
		if(checkBStay.isChecked()==true){
			tags[3]=4;
		}else{
			tags[3]=0;
		}
		if(checkBEtc.isChecked()==true){
			tags[4]=5;
		}else{
			tags[4]=0;
		}
		if(checkBsos.isChecked()==true){
			tags[5]=6;
		}else{
			tags[5]=0;
		}
		
	}

	/**
	 * 이미지 올리기
	 */
	private void imageUpload() {
		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK,
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		photoPickerIntent.setType("image/*");
		photoPickerIntent.putExtra("crop", "true");
		photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
		photoPickerIntent.putExtra("outputFormat",
				Bitmap.CompressFormat.JPEG.toString());
		startActivityForResult(photoPickerIntent, REQUEST_CODE_CROP);
	}

	/**
	 * 카메라 실행
	 */
	private void startCamera() {
		imageIndex = 0;
		Intent photoPickerIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//		photoPickerIntent.putExtra("crop", "true");
//		photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
//		photoPickerIntent.putExtra("outputFormat",	Bitmap.CompressFormat.JPEG.toString());
//		startActivityForResult(photoPickerIntent, REQUEST_CODE_CROP);
		startActivity(photoPickerIntent);
	}

	private Uri getTempUri() {
		mSavedFile = new File(Environment.getExternalStorageDirectory(),
				"temp_" + System.currentTimeMillis() / 1000);
		return Uri.fromFile(mSavedFile);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_CROP 	&& resultCode == Activity.RESULT_OK) {
			Bitmap bm = BitmapFactory.decodeFile(mSavedFile.getAbsolutePath());
			filepath[imageIndex] = mSavedFile.getAbsolutePath();
			ivPhoto[imageIndex].setImageBitmap(bm);
//			images.add(mSavedFile.getAbsolutePath());
		} else if (requestCode == REQUEST_CODE_RECORDING && resultCode == Activity.RESULT_OK) {	
			outputFile = data.getStringExtra(VoiceRecordMachine.PARAM_RESULT);
			Toast.makeText(getActivity(), "음성녹음이 저장되었습니다.", Toast.LENGTH_LONG).show();
		}
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			String file = savedInstanceState.getString("filename");
			if (file != null && !file.equals("")) {
				mSavedFile = new File(file);
			}
			filepath = savedInstanceState.getStringArray("filearray");
		}
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mSavedFile != null) {
			outState.putString("filename", mSavedFile.getAbsolutePath());
		}
		outState.putStringArray("filearray", filepath);
	}

}

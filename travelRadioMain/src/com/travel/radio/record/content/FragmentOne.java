package com.travel.radio.record.content;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.radio.main.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentOne extends Fragment {
	
//	String message;
	String imgUrl;
	
	public static final String PARAM_MESSAGE = "img";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		if (b != null) {
			imgUrl = b.getString(PARAM_MESSAGE);
//			message = b.getString("abc");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.f_layout, container, false);
	}
	
	ImageView ivPhoto;
	ImageLoader mLoader;
//	TextView tv;
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		mLoader = ImageLoader.getInstance();

		ivPhoto=(ImageView)view.findViewById(R.id.imageView1content_photo);
		mLoader.displayImage(imgUrl, ivPhoto);
		
//		tv=(TextView)view.findViewById(R.id.textView1);
//		tv.setText(message);
		
	}
}

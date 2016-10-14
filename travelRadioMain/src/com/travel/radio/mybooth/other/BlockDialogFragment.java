package com.travel.radio.mybooth.other;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.R;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.member.data.MyUserResultList;


public class BlockDialogFragment extends DialogFragment {
	
String userId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.DialogDim);

		Bundle b = getArguments();
		if (b != null) {
			userId = b.getString("userId");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.dialog_fragment_otherbooth, container, false);
	}

	
	TextView tvAlert;
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		tvAlert=(TextView)view.findViewById(R.id.textView1alert);
		tvAlert.setText("차단하시겠습니까?");
		
		
		Button btn=(Button)view.findViewById(R.id.button_cancel);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		btn=(Button)view.findViewById(R.id.button_ok);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				NetworkManager.getInstance().setUserBlock(getActivity(), userId, new OnResultListener<MyUserResultList>() {

					@Override
					public void onSuccess(MyUserResultList result) {
						if(result.success==1){
							Toast.makeText(getActivity(), "차단되었습니다.", Toast.LENGTH_SHORT).show();
							dismiss();

						}else{
							Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
						}						
					}

					@Override
					public void onFail(int code) {
						Toast.makeText(getActivity(), "block_onFail", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
		
		
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
//		getDialog().setTitle("Custom Dialog");
//		getDialog().getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_launcher);
		setCancelable(true);
	}
	
}

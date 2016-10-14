package com.travel.radio.record.replys;


import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.radio.following.data.Follow;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.R;
import com.travel.radio.record.reply.data.RecordReplyList;


public class ReplyDeleteDialogFragment extends DialogFragment {
	
	AdapterReply mAdapter;
	int position;
	
	public ArrayList<Follow> followList;
	
	public void setFollowList(ArrayList<Follow> list) {
		followList = list;
	}
	
	String replyId;
	String recordId;
	
	public interface OnDeleteListener {
		public void onSuccess();
		public void onCancel();
	}
	
	OnDeleteListener mListener;
	public void setOnDeleteListener(OnDeleteListener listener) {
		mListener = listener;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.DialogDim);

		Bundle b = getArguments();
		if (b != null) {
			replyId = b.getString("replyId");
			recordId = b.getString("recordId");
		}
	}
	
	public ReplyDeleteDialogFragment(){}
	
	public ReplyDeleteDialogFragment(AdapterReply adapter, int position){
		this.mAdapter = adapter;
		this.position = position;
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
		tvAlert.setText("����� �����Ͻðڽ��ϱ�?");
		
		
		
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
				NetworkManager.getInstance().delRecordReplyDelete(getActivity(), recordId, replyId, new OnResultListener<RecordReplyList>() {

					@Override
					public void onSuccess(RecordReplyList result) {
						if(result.success == 1){
							Toast.makeText(getActivity(), "����� �����Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
							if (mListener != null) {
								mListener.onSuccess();
							}
//							mAdapter.clear();
							dismiss();
						}else{
							Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
						}							
					}

					@Override
					public void onFail(int code) {
						Toast.makeText(getActivity(), "replyDel_onfail", Toast.LENGTH_SHORT).show();
						
					}
				});
			}
		});
		
		btn=(Button)view.findViewById(R.id.button_ex);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onCancel();
				}
				dismiss();
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
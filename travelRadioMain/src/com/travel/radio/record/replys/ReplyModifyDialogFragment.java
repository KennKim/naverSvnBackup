package com.travel.radio.record.replys;


import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.radio.following.data.Follow;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.R;
import com.travel.radio.record.reply.data.RecordReplyList;


public class ReplyModifyDialogFragment extends DialogFragment {
	
	AdapterReply mAdapter;
	int position;
	
	public ArrayList<Follow> followList;
	
	public void setFollowList(ArrayList<Follow> list) {
		followList = list;
	}
	
	String replyId;
	String message;
	String recordId;


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.DialogDim);

		Bundle b = getArguments();
		if (b != null) {
			replyId = b.getString("replyId");
			message = b.getString("message");
			recordId = b.getString("recordId");
		}
	}
	
	public ReplyModifyDialogFragment(){}
	
	public ReplyModifyDialogFragment(AdapterReply adapter, int position){
		this.mAdapter = adapter;
		this.position = position;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.dialog_fragment_reply, container, false);
	}

	
	TextView tvAlert;
	EditText etContent;

	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		tvAlert=(TextView)view.findViewById(R.id.textView1alert);
		tvAlert.setText("댓글을 수정 하시겠습니까?");
		
		etContent=(EditText)view.findViewById(R.id.editText_content);
		etContent.setText(message);
		
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
				
				message = etContent.getText().toString();
				
				NetworkManager.getInstance().setRecordReplyModify(getActivity(), recordId, replyId, message, new OnResultListener<RecordReplyList>() {

					@Override
					public void onSuccess(RecordReplyList result) {
						if(result.success==1){
//							Toast.makeText(getActivity(), "댓글을 수정하였습니다.", Toast.LENGTH_SHORT).show();
							dismiss();
//							mAdapter.deleteItem(((Follow)(mAdapter.getItem(position))));
//							mAdapter.deleteAll();
						}else{
							Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
						}							
					}

					@Override
					public void onFail(int code) {
						Toast.makeText(getActivity(), "replyModify_onfail", Toast.LENGTH_SHORT).show();
						
					}
				});				
			}
		});
		
		btn=(Button)view.findViewById(R.id.button_ex);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
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

package com.travel.radio.record.replys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.travel.radio.main.MainActivity;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.PropertyManager;
import com.travel.radio.main.R;
import com.travel.radio.mybooth.MyBoothMain;
import com.travel.radio.mybooth.other.OtherBooth;
import com.travel.radio.record.reply.data.RecordReplyList;
import com.travel.radio.record.reply.data.Reply;
import com.travel.radio.record.replys.AdapterReply.OnAdapterItemClickListener;
import com.travel.radio.record.replys.ReplyDeleteDialogFragment.OnDeleteListener;

public class RecordReply extends Fragment {

	ActionBar actionBar;

	PullToRefreshListView plistView;
	
	ListView listView;
	AdapterReply mAdapter;
	EditText etReply;
	TextView tvNone;

	String replyId;
	String recordId;
	String content;
	String img;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		if(b!=null){
			recordId = b.getString("recordId");
		}
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		actionBar.setTitle("댓글");
		
		return inflater.inflate(R.layout.record_reply, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		tvNone=(TextView)view.findViewById(R.id.textView1none);
		tvNone.setText("댓글이 없습니다.");
		tvNone.setVisibility(view.GONE);
		
		plistView= (PullToRefreshListView) view.findViewById(R.id.listView1);
		plistView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Reply item = (Reply) mAdapter.getItem(position);
				Toast.makeText(getActivity(), "item.content",Toast.LENGTH_SHORT).show();
			}
		});
		
		listView=plistView.getRefreshableView();
		
		

//		listView = (ListView) view.findViewById(R.id.listView1);
//		listView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Reply item = (Reply) mAdapter.getItem(position);
//				Toast.makeText(getActivity(), "item.content",Toast.LENGTH_SHORT).show();
//			}
//		});
		mAdapter = new AdapterReply(getActivity());
		mAdapter.setOnAdapterItemClickListener(new OnAdapterItemClickListener() {
			

			@Override
			public void onAdapterFaceItemClick(View v, Reply data) {
//				Toast.makeText(getActivity(), "해당유저페이지로 이동",Toast.LENGTH_SHORT).show();
				if(PropertyManager.getInstance().getUserId()==data.userId){
					
					MyBoothMain myBoothMain = new MyBoothMain();
					Bundle b = new Bundle();
					b.putString("userId", data.userId);
					myBoothMain.setArguments(b);
					((MainActivity)getActivity()).change(myBoothMain);
					
				}else{ 
					
					OtherBooth otherBooth = new OtherBooth();
					Bundle b = new Bundle();
					b.putString("userId", data.userId);
					otherBooth.setArguments(b);
					((MainActivity)getActivity()).change(otherBooth);

				}					
			}

			@Override
			public void onAdapterModifyItemClick(View v, Reply data) {
				
				ReplyModifyDialogFragment dialog = new ReplyModifyDialogFragment();
				Bundle b = new Bundle();
				b.putString("replyId", data.id);
				b.putString("message", data.content);
				b.putString("recordId", recordId);
				dialog.setArguments(b);
				dialog.show(getChildFragmentManager(), "dialog");
			}

			@Override
			public void onAdapterDeleteItemClick(View v, Reply data) {
				
				ReplyDeleteDialogFragment dialog = new ReplyDeleteDialogFragment();
				Bundle b = new Bundle();
				b.putString("replyId", data.id);
				b.putString("recordId", recordId);
				dialog.setArguments(b);
				dialog.setOnDeleteListener(new OnDeleteListener() {
					
					@Override
					public void onSuccess() {
						initData();
					}
					
					@Override
					public void onCancel() {
						
					}
				});
				dialog.show(getChildFragmentManager(), "dialog");
				

			}
		});
		
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listView.setAdapter(mAdapter);
		
		etReply=(EditText)view.findViewById(R.id.editText1reply);
		etReply.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					replySend();					
				}				
				return false;
			}
		});
		
		Button btn=(Button)view.findViewById(R.id.button1send);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				replySend();
			}
		});

		initData();
		
	}
	
	private void replySend() {

		if(PropertyManager.getInstance().getUserId()==null || PropertyManager.getInstance().getUserId().equals("")){
			Toast.makeText(getActivity(), "먼저 로그인을 해주세요!", Toast.LENGTH_LONG).show();
		}else{
			
			content=etReply.getText().toString();
			
//			Reply data = new Reply();
//			data.id=recordId;
//			data.content=content;
//			data.udate="방금 전";
//			data.name=PropertyManager.getInstance().getName();
//			data.img=PropertyManager.getInstance().getImg();
//			
//			mAdapter.add(data);
			
			NetworkManager.getInstance().setRecordReplyWrite(getActivity(), recordId, content, new OnResultListener<RecordReplyList>() {

				@Override
				public void onSuccess(RecordReplyList result) {
					if(result.success == 1){
//						Toast.makeText(getActivity(), "setReply Success", Toast.LENGTH_SHORT).show();
						etReply.setText("");
						mAdapter.clear();
						initData();
						
					}else{
						Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
					}							
				}

				@Override
				public void onFail(int code) {
					Toast.makeText(getActivity(), "getRecordReply_onFail", Toast.LENGTH_SHORT).show();
					
				}
			});
		}
	}
	
	public void initData() {
		
		NetworkManager.getInstance().getRecordReply(getActivity(), recordId, new OnResultListener<RecordReplyList>() {
			
			@Override
			public void onSuccess(RecordReplyList result) {
				
				if(result.success == 1){
//					Toast.makeText(getActivity(), "getRecordReply", Toast.LENGTH_SHORT).show();
					if(result.result.reply.isEmpty()){
						tvNone.setVisibility(View.VISIBLE);
					}else{
						for (Reply r : result.result.reply) {
							mAdapter.add(r);
						}
					}
					
				}else{
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "getRecordReply_onFail", Toast.LENGTH_SHORT).show();
				
			}
		});
	}
}

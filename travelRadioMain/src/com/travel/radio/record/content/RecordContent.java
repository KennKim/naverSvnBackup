package com.travel.radio.record.content;


import java.io.IOException;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.google.android.gms.internal.bt;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.radio.main.MainActivity;
import com.travel.radio.main.MenuFragment;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.PropertyManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.R;
import com.travel.radio.member.data.MyUserResultList;
import com.travel.radio.mybooth.MyBoothMain;
import com.travel.radio.mybooth.other.OtherBooth;
import com.travel.radio.record.content.data.CImages;
import com.travel.radio.record.content.data.CRecordResultList;
import com.travel.radio.record.data.Images;
import com.travel.radio.record.data.RecordResultList;
import com.travel.radio.record.replys.RecordReply;
import com.travel.radio.record.replys.ReplyModifyDialogFragment;
import com.travel.radio.voice.googlemap.GoogleMapActivity;

public class RecordContent extends Fragment {
	
	ActionBar actionBar;
	
	ViewPager pager;
	MyPagerAdapter mAdapter;

	
	String userId;
	String recordUserId;
	String recordId;
	String userPlace;
	String voiceUrl;
	String latitude;
	String longitude;
	String replyCount;
	
	MediaPlayer mPlayer;
	public static final int PLAYER_STATE_IDLE = 0;
	public static final int PLAYER_STATE_INITIALIZED = 1;
	public static final int PLAYER_STATE_PREPARED = 2;
	public static final int PLAYER_STATE_STARTED = 3;
	public static final int PLAYER_STATE_PAUSED = 4;
	public static final int PLAYER_STATE_STOPPED = 5;
	

	int mState;
	
	SeekBar progressView;
	Handler mHandler = new Handler();
	private static final int INTERVAL = 100;
	
	boolean isStartTracking = false;
	
	Runnable updateRunnable = new Runnable() {
		
		@Override
		public void run() {
			if(mState == PLAYER_STATE_STARTED){
				if(!isStartTracking){
					int progress = mPlayer.getCurrentPosition();
					progressView.setProgress(progress);
				}
				mHandler.postDelayed(this, INTERVAL);
			}
		}
	};
	
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		if (b != null) {
			recordId = b.getString("recordId");
			userId = b.getString("userId");
			userPlace = b.getString("userPlace");
		}
		mPlayer = new MediaPlayer();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		actionBar.setTitle("레코드");
		
		
		return inflater.inflate(R.layout.record_content, container, false);
	}
	
	ImageView ivFace;
	TextView tvName;
	TextView tvDate;
	TextView tvPlace;
	
	Button btn;
	Button btnVoice;
	
	TextView tvContent;
	TextView tvLink;
	TextView tvVoiceNone;
	TextView tvDelete;
	
	LinearLayout liPlayer;
	
	@Override
	public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		pager = (ViewPager)view.findViewById(R.id.pager);
//		pager.setPageMargin(-50);
		
		
		ivFace=(ImageView)view.findViewById(R.id.imageView1face);
		ivFace.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(PropertyManager.getInstance().getUserId().equals(userId)){
					
					MyBoothMain myBoothMain = new MyBoothMain();
					Bundle b = new Bundle();
					b.putString("userId", userId);
					myBoothMain.setArguments(b);
					((MainActivity)getActivity()).change(myBoothMain);
					
				}else{
					
					OtherBooth otherBooth = new OtherBooth();
					Bundle b = new Bundle();
					b.putString("userId", userId);
					otherBooth.setArguments(b);
					((MainActivity)getActivity()).change(otherBooth);

				}
			}
		});
		
		tvName=(TextView)view.findViewById(R.id.textView1user_name);
		tvName.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((MainActivity)getActivity()).selectMenu(MenuFragment.MENU_ONE);
			}
		});
		
		tvDate=(TextView)view.findViewById(R.id.textView2date);
		tvPlace=(TextView)view.findViewById(R.id.textView2place);
		
		tvContent = (TextView)view.findViewById(R.id.textView1content);
		tvLink = (TextView)view.findViewById(R.id.textView1link);
		
		
		tvVoiceNone=(TextView)view.findViewById(R.id.textView1voice_none);
		
		
		btnVoice=(Button)view.findViewById(R.id.button_voicefile);
		btnVoice.setVisibility(view.INVISIBLE);
		btnVoice.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				mPlayer.reset();
				mState = PLAYER_STATE_IDLE;
				try {
					mPlayer.setDataSource(getActivity(), Uri.parse(voiceUrl));
					mState = PLAYER_STATE_INITIALIZED;
					mPlayer.prepare();
					mState = PLAYER_STATE_PREPARED;
					progressView.setMax(mPlayer.getDuration());
					progressView.setProgress(0);
				} catch (IllegalArgumentException | SecurityException
						| IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				
				liPlayer.setVisibility(view.VISIBLE);
				voicePlay();
				
				
//				if(voiceUrl == null || voiceUrl.equals("")){
//					tvVoiceNone.setVisibility(view.VISIBLE);
//				}else{
//					liPlayer.setVisibility(view.VISIBLE);
//					voicePlay();
//				}
				
			}
		});
		
		tvDelete=(TextView)view.findViewById(R.id.textView_delete);
		tvDelete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContentDeleteDialogFragment dialog = new ContentDeleteDialogFragment();
				Bundle b = new Bundle();
				b.putString("recordId", recordId);
				dialog.setArguments(b);
				dialog.show(getChildFragmentManager(), "dialog");
			}
		});
		
		progressView = (SeekBar)view.findViewById(R.id.seekBar1voice);
		progressView.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			int progress;
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				if(mState == PLAYER_STATE_STARTED){
					mPlayer.seekTo(progress);
				}
				isStartTracking = false;
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				isStartTracking = true;
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,	boolean fromUser) {
				if(fromUser){
					this.progress = progress;
				}
			}
		});
		
		int duration = mPlayer.getDuration();
		progressView.setMax(duration);
		
		
		////////////////////////////// Voice Player /////////////////////////////
		////////////////////////////// Voice Player /////////////////////////////
		////////////////////////////// Voice Player /////////////////////////////
		
		liPlayer=(LinearLayout)view.findViewById(R.id.linear_player);
		liPlayer.setVisibility(view.GONE);
		
		btn=(Button)view.findViewById(R.id.button1stop);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				voiceStop();
				
			}
		});
		
		
		btn=(Button)view.findViewById(R.id.button2exit);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				liPlayer.setVisibility(view.GONE);
				voiceStop();
			}
		});
		
		
		///////// Menu  //////////
		///////// Menu  //////////
		///////// Menu  //////////

		btn=(Button)view.findViewById(R.id.button1heart);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				NetworkManager.getInstance().setRecordHeart(getActivity(), recordId, new OnResultListener<RecordResultList>() {

					@Override
					public void onSuccess(RecordResultList result) {
						if(result.success==1){
							Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
						}
					}
					@Override
					public void onFail(int code) {
						Toast.makeText(getActivity(), "heart_onFail", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
		
		btn=(Button)view.findViewById(R.id.button2reply);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
//				if(replyCount.equals("0")){
//					Toast.makeText(getActivity(), "댓글이 없습니다.", Toast.LENGTH_SHORT).show();
//				}else{
					RecordReply recordReply = new RecordReply();
					Bundle b = new Bundle();
					b.putString("recordId", recordId);
					recordReply.setArguments(b);
					((MainActivity)getActivity()).change(recordReply);
//				}
				
			}
		});
		
		btn=(Button)view.findViewById(R.id.button3scrap);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {


				NetworkManager.getInstance().setRecordScrap(getActivity(), recordId, new OnResultListener<MyUserResultList>() {

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
						Toast.makeText(getActivity(), "setScrap_onfail", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
		
		btn=(Button)view.findViewById(R.id.button4save);
		
		btn=(Button)view.findViewById(R.id.button5location);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getActivity(),GoogleMapActivity.class);
				intent.putExtra("latitude",latitude);   
				intent.putExtra("longitude",longitude);   
				startActivity(intent);
				
			}
		});
		
		initData();

		mLoader = ImageLoader.getInstance();
	}

	ImageLoader mLoader;
	
	
	
	private void voiceStop() {
		
		if (mState == PLAYER_STATE_STARTED
				|| mState == PLAYER_STATE_PAUSED) {
			mPlayer.stop();
			progressView.setProgress(0);
			mState = PLAYER_STATE_STOPPED;
		}

	}
	private void voicePlay() {
		
		if (mState == PLAYER_STATE_INITIALIZED || mState == PLAYER_STATE_STOPPED) {
			try {
				mPlayer.prepare();
				mState = PLAYER_STATE_PREPARED;
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}

		if (mState == PLAYER_STATE_PREPARED || mState == PLAYER_STATE_PAUSED) {
			mPlayer.seekTo(progressView.getProgress());
			mPlayer.start();
			mState = PLAYER_STATE_STARTED;
			mHandler.post(updateRunnable);
		}
	}
	
	
	private void initData() {
		
		NetworkManager.getInstance().getRecordContent(getActivity(), recordId, new OnResultListener<CRecordResultList>() {
			
			@Override
			public void onSuccess(CRecordResultList result) {
				if(result.success == 1){
//					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
					
					recordUserId=result.result.records.userId;
					userId = PropertyManager.getInstance().getUserId();
					if(!recordUserId.equals(userId)){
						tvDelete.setVisibility(View.GONE);
					}
					
					mLoader.displayImage(result.result.records.img, ivFace);
					tvName.setText(result.result.records.name);
					
					tvDate.setText(result.result.records.udate);
					tvPlace.setText(result.result.records.recordPlace);
					
					if(result.result.records.voice==null || result.result.records.voice.equals("")){
						btnVoice.setVisibility(View.GONE);
					}else{
						voiceUrl=result.result.records.voice;
						btnVoice.setVisibility(View.VISIBLE);
					}
					
					tvContent.setText(result.result.records.content);
					if(result.result.records.link!=null){
						tvLink.setText(result.result.records.link);
					}
					
					mAdapter = new MyPagerAdapter(getChildFragmentManager());
					mAdapter.add(result.result.records.images);
					pager.setAdapter(mAdapter);
					
					latitude = result.result.records.latitude;
					longitude = result.result.records.longitude;
					replyCount = result.result.records.replyCount;

				}else{
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "recordContent_onFail", Toast.LENGTH_SHORT).show();
			}
		});
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		voiceStop();
	}
	
}

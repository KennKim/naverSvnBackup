package com.travel.radio.voicebox.receive;


import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.travel.radio.main.MainActivity;
import com.travel.radio.main.R;
import com.travel.radio.member.data.MyUserResultList;
import com.travel.radio.voice.data.Voice;


public class PlayerDialogFragment extends DialogFragment {


	String voice;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.DialogDim);

		Bundle b = getArguments();
		if (b != null) {
			voice = b.getString("voice");
		}
	}
	
	AudioManager mAudiomanager;

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
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		mPlayer = new MediaPlayer();
		
		return inflater.inflate(R.layout.dialog_fragment_player, container, false);

	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		

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

		Button btn = (Button)view.findViewById(R.id.button1stop);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				voiceStop();

			}
		});

		int duration = mPlayer.getDuration();
		progressView.setMax(duration);

		btn = (Button)view.findViewById(R.id.button2exit);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				voiceStop();

				dismiss();

			}
		});



		initData();


	}

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


		mPlayer.reset();
		mState = PLAYER_STATE_IDLE;
		try {
			mPlayer.setDataSource(getActivity(), Uri.parse(voice));
			mState = PLAYER_STATE_INITIALIZED;
			mPlayer.prepare();
			mState = PLAYER_STATE_PREPARED;
			progressView.setMax(mPlayer.getDuration());
			progressView.setProgress(0);
		} catch (IllegalArgumentException | SecurityException
				| IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		voicePlay();
		
	}

	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		//		getDialog().setTitle("Custom Dialog");
		//		getDialog().getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_launcher);
		setCancelable(true);
	}

}

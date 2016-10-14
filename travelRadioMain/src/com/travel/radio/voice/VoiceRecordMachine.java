package com.travel.radio.voice;


import java.io.File;
import java.io.IOException;

import com.travel.radio.main.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaRecorder.AudioEncoder;
import android.media.MediaRecorder.AudioSource;
import android.media.MediaRecorder.OutputFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore.Audio;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class VoiceRecordMachine extends ActionBarActivity {

	private final static int REQUEST_CODE_MY_ACTIVITY = 0;
	private final static int REQUEST_CODE_RECORDING = 1;
	public static final String PARAM_RESULT = "result";

	
	MediaRecorder mRecorder;
	File outputFile;
	
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voice_machine);
		
		mRecorder = new MediaRecorder();
		
//		progressView = (SeekBar)findViewById(R.id.seekBar1voice_machine);
//		progressView.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
//			
//			int progress;
//			
//			@Override
//			public void onStopTrackingTouch(SeekBar seekBar) {
//				if(mState == PLAYER_STATE_STARTED){
//					mPlayer.seekTo(progress);
//				}
//				isStartTracking = false;
//			}
//			
//			@Override
//			public void onStartTrackingTouch(SeekBar seekBar) {
//				isStartTracking = true;
//			}
//			
//			@Override
//			public void onProgressChanged(SeekBar seekBar, int progress,	boolean fromUser) {
//				if(fromUser){
//					this.progress = progress;
//				}
//			}
//		});
		
		mPlayer = MediaPlayer.create(this, R.raw.aya);
		mState = PLAYER_STATE_PREPARED;
		
		Button btn = (Button)findViewById(R.id.button1stop);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				stopRecording();
				
//				Intent data = new Intent();
//				data.putExtra(PARAM_RESULT, outputFile.getAbsolutePath());
//				setResult(Activity.RESULT_OK, data);
//				finish();
				
//				Bundle b = new Bundle();
//				b.putString("outputFile", outputFile);
				
				
				
				
//				
//				if (mState == PLAYER_STATE_STARTED
//						|| mState == PLAYER_STATE_PAUSED) {
//					mPlayer.stop();
//					progressView.setProgress(0);
//					mState = PLAYER_STATE_STOPPED;
//				}
			}
		});
		
		int duration = mPlayer.getDuration();
//		progressView.setMax(duration);
		
//		btn = (Button)findViewById(R.id.button1play);
//		btn.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				
//				
//				if (mState == PLAYER_STATE_INITIALIZED
//						|| mState == PLAYER_STATE_STOPPED) {
//					try {
//						mPlayer.prepare();
//						mState = PLAYER_STATE_PREPARED;
//					} catch (IllegalStateException | IOException e) {
//						e.printStackTrace();
//					}
//				}
//
//				if (mState == PLAYER_STATE_PREPARED
//						|| mState == PLAYER_STATE_PAUSED) {
//					mPlayer.seekTo(progressView.getProgress());
//					mPlayer.start();
//					mState = PLAYER_STATE_STARTED;
//					mHandler.post(updateRunnable);
//				}
//				
//				
//				
//			}
//		});
		
		btn = (Button)findViewById(R.id.button2recording);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startRecording();
			}
		});
		
//		btn = (Button)findViewById(R.id.button3voice_send);
//		btn.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
	}
	
	
	private void startRecording() {
		mRecorder.reset();
		mRecorder.setAudioSource(AudioSource.MIC);
		mRecorder.setOutputFormat(OutputFormat.MPEG_4);
		mRecorder.setAudioEncoder(AudioEncoder.AMR_NB);
		
		File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		outputFile = new File( dir , "my_record_" + (System.currentTimeMillis() / 1000)+".mp3");
		mRecorder.setOutputFile(outputFile.getAbsolutePath());
		//mRecorder.start();
		
		try {
			mRecorder.prepare();
			mRecorder.start();
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void stopRecording() {
		if (outputFile != null) {
			mRecorder.stop();
			
			ContentValues values = new ContentValues();
			values.put(Audio.Media.TITLE, "my record");
			values.put(Audio.Media.MIME_TYPE, "audio/mpeg");
			values.put(Audio.Media.DATA, outputFile.getAbsolutePath());
			values.put(Audio.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
			values.put(Audio.Media.DISPLAY_NAME, "my record");
			Uri uri = getContentResolver().insert(Audio.Media.EXTERNAL_CONTENT_URI, values);
			
			if (uri != null) {
//				Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
//				sendBroadcast(intent);
				
				Intent data = new Intent();
				data.putExtra(PARAM_RESULT, outputFile.getAbsolutePath());
				setResult(Activity.RESULT_OK, data);
				finish();
			}
//			Toast.makeText(VoiceRecordMachine.this, outputFile.getAbsolutePath().toString() + "¿˙¿Â", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mRecorder.release();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if(id == R.id.action_settings){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}

package com.example.sample5mediaplayer;

import java.io.File;
import java.io.IOException;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

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

	SeekBar volumeView;

	Runnable updateRunnable = new Runnable() {

		@Override
		public void run() {
			if (mState == PLAYER_STATE_STARTED) {
				if (!isStartTracking) {
					int progress = mPlayer.getCurrentPosition();
					progressView.setProgress(progress);
				}
				mHandler.postDelayed(this, INTERVAL);
			}
		}
	};

	boolean isStartTracking = false;

	AudioManager mAudioManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressView = (SeekBar) findViewById(R.id.seek_progress);
		progressView.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			int progress;

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				if (mState == PLAYER_STATE_STARTED) {
					mPlayer.seekTo(progress);
				}
				isStartTracking = false;
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				isStartTracking = true;
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					this.progress = progress;
				}

			}
		});

		volumeView = (SeekBar) findViewById(R.id.volume_bar);
		mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		volumeView.setMax(max);
		int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		
		volumeView.setProgress(current);
		volumeView.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
							progress, 0);
				}
			}
		});
		CheckBox check = (CheckBox) findViewById(R.id.check_mute);
		check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					volume = 1;
					mHandler.post(downRunnable);
				} else {
					volume = 0;
					mHandler.post(upRunnable);
				}
			}
		});
		mPlayer = MediaPlayer.create(this, R.r);
		mState = PLAYER_STATE_PREPARED;
		
		mPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				
			}
		});
		
		mPlayer.setOnErrorListener(new OnErrorListener() {
			
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				return false;
			}
		});
		
		int duration = mPlayer.getDuration();
		progressView.setMax(duration);

		Button btn = (Button) findViewById(R.id.btn_play);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mState == PLAYER_STATE_INITIALIZED
						|| mState == PLAYER_STATE_STOPPED) {
					try {
						mPlayer.prepare();
						mState = PLAYER_STATE_PREPARED;
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}

				if (mState == PLAYER_STATE_PREPARED
						|| mState == PLAYER_STATE_PAUSED) {
					mPlayer.seekTo(progressView.getProgress());
					mPlayer.start();
					mState = PLAYER_STATE_STARTED;
					mHandler.post(updateRunnable);
				}
			}
		});

		btn = (Button) findViewById(R.id.btn_pause);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mState == PLAYER_STATE_STARTED) {
					mPlayer.pause();
					mState = PLAYER_STATE_PAUSED;
				}
			}
		});

		btn = (Button) findViewById(R.id.btn_stop);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mState == PLAYER_STATE_STARTED
						|| mState == PLAYER_STATE_PAUSED) {
					mPlayer.stop();
					progressView.setProgress(0);
					mState = PLAYER_STATE_STOPPED;
				}
			}
		});
		
		btn = (Button)findViewById(R.id.btn_list);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, MusicListActivity.class);
				startActivityForResult(i, 0);
			}
		});
		btn = (Button)findViewById(R.id.btn_rec);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startRecord();

			}
		});
		btn = (Button)findViewById(R.id.btn_recStop);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stopRecord();
			}
		});
	}
	
	MediaRecorder recorder;
	File outputFile;


	private void startRecord() {
		
		try{
	           File file = Environment.getExternalStorageDirectory();
	           String path = file.getAbsolutePath() + "test.3gp";
	           recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	           recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	           recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
	           recorder.setOutputFile(path);
	           recorder.prepare();
	           recorder.start();
	    }catch(IOException e){
	     e.printStackTrace();
	    }

//		try {
//			
//			Toast.makeText(this, "start Record", Toast.LENGTH_SHORT).show();
//			File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
//			outputFile = new File( file , "my_video_" + (System.currentTimeMillis() / 1000));
//			
//			
//			//갤럭시 S4기준으로 /storage/emulated/0/의 경로를 갖고 시작한다. 
//			String path=file.getAbsolutePath()+"파일 경로 ";
//
//			recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//			//첫번째로 어떤 것으로 녹음할것인가를 설정한다. 마이크로 녹음을 할것이기에 MIC로 설정한다.
//			recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//			//이것은 파일타입을 설정한다. 녹음파일의경우 3gp로해야 용량도 작고 효율적인 녹음기를 개발할 수있다. 
//			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//			//이것은 코덱을 설정하는 것이라고 생각하면된다. 
//			recorder.setOutputFile(outputFile.getAbsolutePath());
//			//저장될 파일을 저장한뒤 
//			recorder.prepare();
//			recorder.start();
//			//시작하면된다. 
//			
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	private void stopRecord() {

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(arg0, arg1, arg2);
		if (requestCode == 0 && resultCode == RESULT_OK) {
			Uri uri = data.getData();
			String title = data.getStringExtra("title");
			setTitle(title);
			mPlayer.reset();
			mState = PLAYER_STATE_IDLE;
			try {
				mPlayer.setDataSource(this, uri);
				mState = PLAYER_STATE_INITIALIZED;
				mPlayer.prepare();
				mState = PLAYER_STATE_PREPARED;
				progressView.setMax(mPlayer.getDuration());
				progressView.setProgress(0);
			} catch (IllegalArgumentException | SecurityException
					| IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (mState == PLAYER_STATE_STARTED) {
			mPlayer.pause();
			mState = PLAYER_STATE_PAUSED;
		}
	}

	float volume = 1.0f;
	Runnable downRunnable = new Runnable() {

		@Override
		public void run() {
			if (volume >= 0) {
				mPlayer.setVolume(volume, volume);
				volume -= 0.2f;
				mHandler.postDelayed(this, 200);
			} else {
				volume = 0;
			}
		}
	};

	Runnable upRunnable = new Runnable() {

		@Override
		public void run() {
			if (volume <= 1) {
				mPlayer.setVolume(volume, volume);
				volume += 0.2f;
				mHandler.postDelayed(this, 200);
			} else {
				volume = 1;
			}
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

package com.travel.radio.voicebox.saved;

import com.travel.radio.main.MainActivity;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.PropertyManager;
import com.travel.radio.main.R;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.mybooth.MyBoothMain;
import com.travel.radio.mybooth.other.OtherBooth;
import com.travel.radio.voice.MessageSendMachine;
import com.travel.radio.voice.VoiceRecordMachine2;
import com.travel.radio.voice.data.Voice;
import com.travel.radio.voice.data.VoiceResultList;
import com.travel.radio.voicebox.receive.PlayerDialogFragment;
import com.travel.radio.voicebox.saved.AdapterSaved.OnAdapterItemClickListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SavedVoice extends Fragment {
	
	
	String option = "voice";

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.voicebox_saved, container, false);
	}
	
	ListView listView;
	
	EditText etSearch;
	TextView tvNone;

	AdapterSaved mAdapter;
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		tvNone=(TextView)view.findViewById(R.id.textView1none);
		tvNone.setText("������ ������ �����ϴ�.");
		tvNone.setVisibility(view.GONE);
		
		
		etSearch=(EditText)view.findViewById(R.id.editText_search);
		Button btn=(Button)view.findViewById(R.id.button_search);
		btn=(Button)view.findViewById(R.id.button2reply);
		btn=(Button)view.findViewById(R.id.button3delete);
//		btn=(Button)view.findViewById(R.id.button4saved);
		
		
		listView = (ListView) view.findViewById(R.id.listView1);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(), "list click",Toast.LENGTH_SHORT).show();

			}
		});
		mAdapter = new AdapterSaved(getActivity());
		mAdapter.setOnAdapterItemClickListener(new OnAdapterItemClickListener() {
			
			@Override
			public void onAdapterFaceItemClick(View v, Voice data) {
//				Toast.makeText(getActivity(), "�ش������������� �̵�",Toast.LENGTH_SHORT).show();

				if(PropertyManager.getInstance().getUserId().equals(data.userId)){
					
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
			public void onAdapterPlayItemClick(View v, Voice data) {
				Toast.makeText(getActivity(), "�������� ���", Toast.LENGTH_SHORT).show();
				
				PlayerDialogFragment dialog = new PlayerDialogFragment();
				Bundle b = new Bundle();
				b.putString("voice", data.voice);
				dialog.setArguments(b);
				dialog.show(getChildFragmentManager(), "dialog");
			}

			@Override
			public void onAdapterDelItemClick(View v, Voice data) {
				String radiogramId = data.id;
				String deleteOption = "1";

						NetworkManager.getInstance().delMsgVoiceDelete(getActivity(), radiogramId, deleteOption, new OnResultListener<VoiceResultList>() {

							@Override
							public void onSuccess(VoiceResultList result) {
								if(result.success == 1){
									Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
								}else{
									Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
								}								
							}

							@Override
							public void onFail(int code) {
								Toast.makeText(getActivity(), "del_onfail", Toast.LENGTH_SHORT).show();
								
							}
						});								
			}

			@Override
			public void onAdapterReplyItemClick(View v, Voice data) {
				Intent intent = new Intent(getActivity(), VoiceRecordMachine2.class);
				intent.putExtra("receiveId", data.userId);
				startActivity(intent);						
			}
		});
		
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listView.setAdapter(mAdapter);
		

		initData();
		
	}
	
	private void initData() {
		
		NetworkManager.getInstance().getMsgVoiceSaveList(getActivity(), new OnResultListener<VoiceResultList>() {

			@Override
			public void onSuccess(VoiceResultList result) {
				if(result.success == 1){
//					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();
					if(result.result.radiogram.isEmpty()){
						tvNone.setVisibility(View.VISIBLE);
					}else{
						for (Voice r : result.result.radiogram) {
							mAdapter.add(r);
						}
					}
				}else{
					Toast.makeText(getActivity(), result.result.msg, Toast.LENGTH_SHORT).show();

				}
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "voiceSaveList_onfail", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		
	}
}
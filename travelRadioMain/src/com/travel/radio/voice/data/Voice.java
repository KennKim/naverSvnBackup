package com.travel.radio.voice.data;

import com.google.gson.annotations.SerializedName;

public class Voice {

	public String id;
	
	@SerializedName("user_id")
	public String userId;
	
	@SerializedName("msg_flag ")
	public String msgFlag;
	
	@SerializedName("send_user_id")
	public String sendUserId;
	
	@SerializedName("receive_user_id")
	public String receiveUserId;
	public String content;
	public String name;
	public String latitude;
	public String longitude;
	public String img;
	public String cdate;
	public String voice ;
	
}

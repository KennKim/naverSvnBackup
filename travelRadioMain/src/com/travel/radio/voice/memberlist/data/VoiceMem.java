package com.travel.radio.voice.memberlist.data;

import com.google.gson.annotations.SerializedName;


public class VoiceMem {
	
	public String id;
	public String img;
	public String name;
	public String latitude;
	public String longitude;
	
	@SerializedName("best_user")
	public String bestUser;
	
	@SerializedName("user_place")
	public String userPlace;
	
}

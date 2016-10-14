package com.travel.radio.member.data;

import com.google.gson.annotations.SerializedName;


public class MyUser {
	
	public String id;
	public String name;
	public String email;
	public String img;
	public String latitude;
	public String longitude;
	
	@SerializedName("best_user")
	public String bestUser;
	
	@SerializedName("user_place")
	public String userPlace;
	
}

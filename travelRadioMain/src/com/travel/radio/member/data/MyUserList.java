package com.travel.radio.member.data;

import com.google.gson.annotations.SerializedName;


public class MyUserList {
	
	public String msg;
	
	@SerializedName("user")
	public MyUser myUser;
	
	@SerializedName("users")
	public MyUser myUsers;
	
	

}

package com.travel.radio.record.reply.data;

import com.google.gson.annotations.SerializedName;

public class Reply {
	
	public String id;
	
	@SerializedName("user_id")
	public String userId;
	public String content;
	public String udate;
	public String name;
	public String img;

}

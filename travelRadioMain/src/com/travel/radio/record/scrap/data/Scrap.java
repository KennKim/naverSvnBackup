package com.travel.radio.record.scrap.data;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;
import com.travel.radio.record.data.Images;
import com.travel.radio.record.data.Tags;

public class Scrap {

	public String id;
	
	@SerializedName("user_id")
	public String userId;
	public String name;
	public String img;
	
	@SerializedName("best_user")
	public String bestUser;
	public String title;
	
	@SerializedName("record_place ")
	public String recordPlace;
	
	@SerializedName("user_place  ")
	public String userPlace;
	
	public String content;
	public String voice;
	public String link;
	public String udate;
	public String latitude;
	public String longitude;
	
	@SerializedName("h_count")
	public String hCount;
	
	@SerializedName("reply_count")
	public String replyCount;
	
	public ArrayList<Tags> tags;
	
	public ArrayList<Images> images;
	
}

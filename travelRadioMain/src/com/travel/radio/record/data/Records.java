package com.travel.radio.record.data;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Records {
	
	public String id;
	
	@SerializedName("user_id")
	public String userId;
	public String name;
	public String img;
	
	@SerializedName("best_user")
	public String bestUser;
	public String title;
	public String content;
	
	@SerializedName("record_place")
	public String recordPlace;
	
	@SerializedName("user_place")
	public String userPlace;
	
	public String voice;
	public String link;
	public String udate;
	public String longitude;
	public String latitude;
	
	@SerializedName("h_count")
	public String hCount;
	
	@SerializedName("reply_count")
	public String replyCount;
	public ArrayList<Tags> tags;
	
	public ArrayList<Images> images;

	@Override
	public String toString() {
		return "Records [id=" + id + ", userId=" + userId + ", name=" + name
				+ ", img=" + img + ", bestUser=" + bestUser + ", title="
				+ title + ", content=" + content + ", voice=" + voice
				+ ", link=" + link + ", udate=" + udate + ", longitude="
				+ longitude + ", latitude=" + latitude + ", hCount=" + hCount
				+ ", replyCount=" + replyCount + ", tags=" + tags + ", images="
				+ images + "]";
	}
	

	
}

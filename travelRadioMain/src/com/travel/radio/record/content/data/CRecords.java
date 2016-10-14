package com.travel.radio.record.content.data;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CRecords implements Parcelable{
	
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
	public String longitude;
	public String latitude;
	
	@SerializedName("h_count")
	public String hCount;
	
	@SerializedName("reply_count")
	public String replyCount;
	public ArrayList<CTags> tags;
	
	public ArrayList<CImages> images;

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

	
	
	public CRecords(Parcel source) {
		
		
		id = source.readString();
		userId = source.readString();
		name = source.readString();
		img = source.readString();
		bestUser = source.readString();
		title = source.readString();
		recordPlace = source.readString();
		userPlace = source.readString();
		
		content = source.readString();
		voice = source.readString();
		link = source.readString();
		udate = source.readString();
		longitude = source.readString();
		latitude = source.readString();
		
		hCount = source.readString();
		replyCount = source.readString();
		
//		tags = source.readString();
//		images = source.readString();
		
		
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeString(id);
		dest.writeString(userId);
		dest.writeString(name);
		dest.writeString(img);
		dest.writeString(bestUser);
		dest.writeString(title);
		dest.writeString(recordPlace);
		dest.writeString(userPlace);
		dest.writeString(content);
		
		dest.writeString(voice);
		dest.writeString(link);
		dest.writeString(udate);
		dest.writeString(latitude);
		dest.writeString(longitude);
		
		dest.writeString(hCount);
		dest.writeString(replyCount);
		
//		dest.writeString(tags);
//		dest.writeString(images);
		

	}
	
	public static Parcelable.Creator<CRecords> CREATOR = new Parcelable.Creator<CRecords>() {

		@Override
		public CRecords createFromParcel(Parcel source) {
			return new CRecords(source);
		}

		@Override
		public CRecords[] newArray(int size) {
			return new CRecords[size];
		}
		
	};

	
}

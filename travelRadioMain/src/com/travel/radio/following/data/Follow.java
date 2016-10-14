package com.travel.radio.following.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Follow implements Parcelable{
	
	public String id;
	public String name;
	public String img;
	
	public Follow(Parcel source) {
		id = source.readString();
		name = source.readString();
		img = source.readString();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(name);
		dest.writeString(img);
	}
	
	public static Parcelable.Creator<Follow> CREATOR = new Parcelable.Creator<Follow>() {

		@Override
		public Follow createFromParcel(Parcel source) {
			return new Follow(source);
		}

		@Override
		public Follow[] newArray(int size) {
			return new Follow[size];
		}
	};

}

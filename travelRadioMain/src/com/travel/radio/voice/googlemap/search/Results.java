package com.travel.radio.voice.googlemap.search;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Results {
	
	@SerializedName("formatted_address")
	public String formatted_address;
	
	public Geometry geometry;
	
	
	public String icon;
	public String id;
	public String name;
	
	@SerializedName("place_id")
	public String placeId;
	
	public String reference;
	
	public ArrayList<String> types;
	
	
	@Override
	public String toString() {
		return name;
	}
	
}

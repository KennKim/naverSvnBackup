package com.travel.radio.voice.googlemap.geocoder;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class GAddressComponents {

	@SerializedName("long_name")
	public String longName;
	
	@SerializedName("short_name")
	public String shortName;
	
	public ArrayList<String> types;
}

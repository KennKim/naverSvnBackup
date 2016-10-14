package com.travel.radio.voice.googlemap.search;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class MapResultList {
	
	@SerializedName("html_attributions")
	public ArrayList<String> htmlAttributions;
	
	public ArrayList<Results> results;
	
	public String status;

	@SerializedName("error_message")
	public String errorMessage;
	
}

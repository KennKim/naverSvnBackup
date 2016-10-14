package com.travel.radio.voice.googlemap.geocoder;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class GeocoderResultList {

	public ArrayList<GResults> results;
	
	@SerializedName("error_message")
	public String errorMessage;

	public String status;
	
	
}

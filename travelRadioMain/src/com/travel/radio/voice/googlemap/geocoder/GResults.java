package com.travel.radio.voice.googlemap.geocoder;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class GResults {
	
	@SerializedName("address_components")
	public ArrayList<GAddressComponents> addressComponents;

	@SerializedName("formatted_address")
	public String formattedAddress;
	
	public GGeometry geometry;
	
	
	
	
}

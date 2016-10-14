package com.travel.radio.main;

import android.content.Context;
import android.content.SharedPreferences;

public class PropertyManager {
	   private static PropertyManager instance;
	   
	   public static PropertyManager getInstance(){
	      if(instance == null){
	         instance = new PropertyManager();
	      }
	      return instance;
	   }
	   
	   SharedPreferences mPrefs;
	   SharedPreferences.Editor mEditor;
	   
	   private static final String PREF_NAME = "my_prefs";
	   private PropertyManager(){
	      mPrefs = MyApplication.getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
	      mEditor = mPrefs.edit();
	   }
	   
	   
	   private String userId;
	   private static final String FIELD_USER_ID = "userId";
	   private static final String FIELD_EMAIL = "email";
	   private static final String FIELD_PASSWORD = "password";
	   private static final String FIELD_NAME = "name";
	   private static final String FIELD_IMG = "img";
	   private static final String FIELD_LATITUDE = "latitude";
	   private static final String FIELD_LONGITUDE = "longitude";
	   private static final String FIELD_RADIUS = "radius";
	   private static final String FIELD_BESTUSER = "radius";
	   private static final String FIELD_USERPLACE = "radius";
	   
	   public void setUserId(String userId) {
	      this.userId = userId;
	      mEditor.putString(FIELD_USER_ID, userId);
	      mEditor.commit();
	   }
	   
	   public String getUserId() {
	      if (userId == null) {
	         userId = mPrefs.getString(FIELD_USER_ID, "");
	      }
	      return userId;
	   }
	   
	   private String email;
	   private String password;
	   private String name;
	   private String img;
	   private String latitude="37.566535";
	   private String longitude="126.9779692";
	   private String radius="50";
	   private String bestUser;
	   private String userPlace;
	   
	   public void initializing(){
		   userId="";
		   email="";
		   password="";
		   name="";
		   img="";
		   latitude="37.566535";
		   longitude="126.9779692";
		   radius="50";
		   bestUser="";
		   userPlace="Seoul, KOREA";
	   }
	   

	public String getEmail() {
		if (email == null) {
			email = mPrefs.getString(FIELD_EMAIL, "");
	      }
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
		mEditor.putString(FIELD_EMAIL, email);
		mEditor.commit();
	}
	
	
	public String getPassword() {
		if (password == null) {
			password = mPrefs.getString(FIELD_PASSWORD, "");
		}
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		mEditor.putString(FIELD_PASSWORD, password);
		mEditor.commit();
	}
	
	
	
	public String getName() {
		if (name == null) {
			name = mPrefs.getString(FIELD_NAME, "");
		}
		return name;
	}
	public void setName(String name) {
		this.name = name;
		mEditor.putString(FIELD_NAME, name);
		mEditor.commit();
	}
	
	

	public String getImg() {
		if (img == null) {
			img = mPrefs.getString(FIELD_IMG, "");
	      }
		return img;
	}
	public void setImg(String img) {
		this.img = img;
		mEditor.putString(FIELD_IMG, img);
		mEditor.commit();
	}
	
	

	public String getLatitude() {
		if (latitude == null) {
			latitude = mPrefs.getString(FIELD_LATITUDE, "37.566535");
	      }
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
		mEditor.putString(FIELD_LATITUDE, latitude);
		mEditor.commit();
	}
	
	

	public String getLongitude() {
		if (longitude == null) {
			longitude = mPrefs.getString(FIELD_LONGITUDE, "126.9779692");
	      }
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
		mEditor.putString(FIELD_LONGITUDE, longitude);
		mEditor.commit();
	}
	
	
	
	public String getRadius() {
		if (radius == null) {
			radius = mPrefs.getString(FIELD_RADIUS, "50");
		}
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
		mEditor.putString(FIELD_RADIUS, radius);
		mEditor.commit();
	}

	
	
	public String getBestUser() {
		if (bestUser == null) {
			bestUser = mPrefs.getString(FIELD_BESTUSER, "");
	      }
		return bestUser;
	}
	public void setBestUser(String bestUser) {
		this.bestUser = bestUser;
		mEditor.putString(FIELD_BESTUSER, bestUser);
		mEditor.commit();
	}

	
	
	public String getUserPlace() {
		if (userPlace == null) {
			userPlace = mPrefs.getString(FIELD_USERPLACE, "Seoul, KOREA");
	      }
		return userPlace;
	}
	public void setUserPlace(String userPlace) {
		this.userPlace = userPlace;
		mEditor.putString(FIELD_USERPLACE, userPlace);
		mEditor.commit();
	}

	private String regId;
	private static final String FIELD_REGID = "regid";
	public String getRegistrationId() {
		if (regId == null) {
			regId = mPrefs.getString(FIELD_REGID, "");
		}
		return regId;
	}

	public void setRegistrationId(String regid) {
		this.regId = regid;
		mEditor.putString(FIELD_REGID, regid);
		mEditor.commit();
	}
	   
}
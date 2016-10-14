package com.travel.radio.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicHeader;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import com.travel.radio.blacklist.data.BlockResultList;
import com.travel.radio.following.data.FollowResultList;
import com.travel.radio.member.data.MyUserList;
import com.travel.radio.member.data.MyUserResultList;
import com.travel.radio.record.content.data.CRecordResultList;
import com.travel.radio.record.data.RecordResultList;
import com.travel.radio.record.data.Tags;
import com.travel.radio.record.reply.data.RecordReplyList;
import com.travel.radio.record.scrap.data.ScrapResultList;
import com.travel.radio.voice.data.VoiceResultList;
import com.travel.radio.voice.googlemap.CarRouteInfo;
import com.travel.radio.voice.googlemap.Geometry;
import com.travel.radio.voice.googlemap.GeometryDeserializer;
import com.travel.radio.voice.googlemap.POIResult;
import com.travel.radio.voice.googlemap.geocoder.GeocoderResultList;
import com.travel.radio.voice.googlemap.search.MapResultList;

public class NetworkManager {
	
	private static NetworkManager instance;
	public static NetworkManager getInstance() {
		if (instance == null) {
			instance = new NetworkManager();
		}
		return instance;
	}
	
	
	
	AsyncHttpClient client;
	
	private NetworkManager() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);
			MySSLSocketFactory socketFactory = new MySSLSocketFactory(trustStore);
			socketFactory.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			client = new AsyncHttpClient();
			client.setSSLSocketFactory(socketFactory);			
			client.setCookieStore(new PersistentCookieStore(MyApplication.getContext()));
			client.setTimeout(30000);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		}
	}

	public interface OnResultListener<T> {
		public void onSuccess(T result);
		public void onFail(int code);
	}
	
	Gson gson = new Gson();
	
	public HttpClient getHttpClient(){
		return client.getHttpClient();
	}
	
	
	///////////// Tmap /////////////// 
	///////////// Tmap /////////////// 
	///////////// Tmap ///////////////
	
//	public static final String URL_TEXT = "https://apis.skplanetx.com/tmap/pois";
//	public static final String APP_KEY = "458a10f5-c07e-34b5-b2bd-4a891e024c2a";
//	
//	Header[] headers = new Header[] {
//		new BasicHeader("Accept", "application/json"),
//		new BasicHeader("appKey", APP_KEY)
//	};
//
//	public void getTMapPOI(Context context, String keyword, final OnResultListener<POIResult> listener) {
//		RequestParams params = new RequestParams();
//		params.put("version", "1");
//		params.put("resCoordType" , "WGS84GEO");
//		params.put("reqCoordType", "WGS84GEO");
//		params.put("searchKeyword", keyword);
//		
//		
//		client.get(context, URL_TEXT, headers, params, new TextHttpResponseHandler() {
//			
//			@Override
//			public void onSuccess(int statusCode, Header[] headers,
//					String responseString) {
//				Gson gson = new Gson();
//				POIResult result = gson.fromJson(responseString, POIResult.class);
//				listener.onSuccess(result);
//			}
//			
//			@Override
//			public void onFailure(int statusCode, Header[] headers,
//					String responseString, Throwable throwable) {
//				listener.onFail(statusCode);
//			}
//		});
//		
//	}
//	
//	private static final String URL_ROUTE = "https://apis.skplanetx.com/tmap/routes";
//	
//	public void searchRoute(Context context, double startLat, double startLng, double endLat, double endLng, final OnResultListener<CarRouteInfo> listener) {
//		RequestParams params = new RequestParams();
//		params.put("version", "1");
//		params.put("resCoordType", "WGS84GEO");
//		params.put("reqCoordType", "WGS84GEO");
//		params.put("startX", ""+startLng);
//		params.put("startY", ""+startLat);
//		params.put("endX", ""+endLng);
//		params.put("endY", ""+endLat);
//		client.post(context, URL_ROUTE, headers, params, null ,new TextHttpResponseHandler() {
//			
//			@Override
//			public void onSuccess(int statusCode, Header[] headers,
//					String responseString) {
//				Gson gson = new GsonBuilder().registerTypeAdapter(Geometry.class, new GeometryDeserializer()).create();
//				CarRouteInfo result = gson.fromJson(responseString, CarRouteInfo.class);
//				listener.onSuccess(result);
//			}
//			
//			@Override
//			public void onFailure(int statusCode, Header[] headers,
//					String responseString, Throwable throwable) {
//				listener.onFail(statusCode);
//			}
//		});
//	}
//	
//	public void uploadMapFile(Context context, String title, File file, File file2, final OnResultListener<String> listener) {
//		RequestParams params = new RequestParams();
//		params.put("title", title);
//		try {
//			params.put("myfile", file);
//			params.put("myfile", file2);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		client.post(context,  "http://...." , params, new TextHttpResponseHandler() {
//			
//			@Override
//			public void onSuccess(int statusCode, Header[] headers,
//					String responseString) {
//				listener.onSuccess(responseString);
//			}
//			
//			@Override
//			public void onProgress(int bytesWritten, int totalSize) {
//				super.onProgress(bytesWritten, totalSize);
//				
//			}
//			
//			@Override
//			public void onFailure(int statusCode, Header[] headers,
//					String responseString, Throwable throwable) {
//				listener.onFail(statusCode);
//			}
//		});
//	}
	
	
	
	
	////////////////// GoogleMap Text Search ///////////////////
	////////////////// GoogleMap Text Search ///////////////////
	////////////////// GoogleMap Text Search ///////////////////
	
	public static final String GOOGLE_MAP_SERVER = "https://maps.googleapis.com/maps/api/place/textsearch";
	public static final String URL_TEXT_SEARCH = GOOGLE_MAP_SERVER + "/json";
	public static final String MAP_APP_KEY = "AIzaSyBLdBU3esrYBOmgLFFcyJhS2w2ELpwAyaY";
	
	
	/**
	 * 구글 플레이스 텍스트 검색
	 * @param context
	 * @param query
	 * @param listener
	 */
	public void getTextSearch(Context context, String query, final OnResultListener<MapResultList> listener) {
		RequestParams params = new RequestParams();
		params.put("query", ""+query);
		params.put("sensor", "true");
		params.put("key", MAP_APP_KEY);

		client.get(context, URL_TEXT_SEARCH, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MapResultList result = gson.fromJson(responseString, MapResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	////////////////// GoogleMap Geocoder  ///////////////////
	////////////////// GoogleMap Geocoder  ///////////////////
	////////////////// GoogleMap Geocoder  ///////////////////
	
	public static final String GEOCODER_SERVER = "http://maps.googleapis.com/maps/api/geocode";
	public static final String URL_GEOCODER = GEOCODER_SERVER + "/json";
	
	/**
	 * 역지오코더
	 * @param context
	 * @param latlng
	 * @param listener
	 */
	public void getGeocoderAdrs(Context context, LatLng latlng, final OnResultListener<GeocoderResultList> listener) {
		RequestParams params = new RequestParams();
		params.put("latlng", ""+latlng.latitude +"," + latlng.longitude);
		params.put("sensor", "true");
		
		client.get(context, URL_GEOCODER, params, new TextHttpResponseHandler() {
			
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				GeocoderResultList result = gson.fromJson(responseString, GeocoderResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}

	
	
	///////////////////// DB connecting ////////////////////
	///////////////////// DB connecting ////////////////////
	///////////////////// DB connecting ////////////////////
	
	
	public static final String SERVER = "http://ec2-54-65-7-14.ap-northeast-1.compute.amazonaws.com";
	public static final String URL_USER_JOIN = SERVER + "/user";
	
	/**
	 * 1. 회원가입
	 * @param context
	 * @param email
	 * @param password
	 * @param name
	 * @param listener
	 */
	public void setUserJoin(Context context, String email, String password, String name, final OnResultListener<MyUserResultList> listener) {
//		String url = String.format(URL_USER_LIST, user);
		RequestParams params = new RequestParams();
		params.put("email", ""+email);
		params.put("password", ""+password);
		params.put("name", ""+name);
		
		client.post(context, URL_USER_JOIN, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String URL_USERNAME_CHECK = SERVER + "/user/checkname";
	
	/**
	 * 2. 이름 중복 확인
	 * @param context
	 * @param name
	 * @param listener
	 */
	public void getCheckUserName(Context context, String name, final OnResultListener<MyUserResultList> listener) {
//		String url = String.format(URL_USERNAME_CHECK, name);
		RequestParams params = new RequestParams();
		params.put("name", ""+name);
		
		client.post(context, URL_USERNAME_CHECK, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String URL_USER_MODIFY = SERVER + "/user";
	/**
	 * 3. 회원정보 수정
	 * @param context
	 * @param email
	 * @param password
	 * @param name
	 * @param img
	 * @param latitude
	 * @param longitude
	 * @param listener
	 */
	public void setUserModify(Context context, File img, String password, String name, String userPlace, String latitude, String longitude, final OnResultListener<MyUserResultList> listener) {
//		String url = String.format(URL_USER_LIST, user);
		RequestParams params = new RequestParams();
		try {
			params.put("img", img);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		params.put("name", ""+name);
		params.put("password", ""+password);
		params.put("latitude", ""+latitude);
		params.put("longitude", ""+longitude);
		params.put("userPlace", ""+userPlace);
		
		client.put(context, URL_USER_MODIFY, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	public static final String URL_USER_MODIFY_LOCATION = SERVER + "/user";
	/**
	 * 3-1. 회원정보 수정
	 * @param context
	 * @param name
	 * @param userPlace
	 * @param latitude
	 * @param longitude
	 * @param listener
	 */
	public void setLocationModify(Context context, String name, String userPlace, String latitude, String longitude, final OnResultListener<MyUserResultList> listener) {
//		String url = String.format(URL_USER_LIST, user);
		RequestParams params = new RequestParams();
		params.put("name", ""+name);
		params.put("latitude", ""+latitude);
		params.put("longitude", ""+longitude);
		params.put("user_Place", ""+userPlace);
		
		client.put(context, URL_USER_MODIFY_LOCATION, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_USER_LIST = SERVER + "/user/%s";
	/**
	 * 4-1 사용자정보 조회(로그인 한 유저정보)
	 * @param context
	 * @param user
	 * @param listener
	 */
	public void getUserList(Context context, String user, final OnResultListener<MyUserResultList> listener) {
		String url = String.format(URL_USER_LIST, user);
		RequestParams params = new RequestParams();
//		params.put("user", ""+user);
		client.get(context, url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	/**
	 * 4-2 사용자정보 조회(다른 유저 정보)
	 * @param context
	 * @param user
	 * @param listener
	 */
	public void getOtherUserList(Context context, String user, final OnResultListener<MyUserResultList> listener) {
		String url = String.format(URL_USER_LIST, user);
		RequestParams params = new RequestParams();
//		params.put("user", ""+user);
		client.get(context, url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
public static final String URL_USER_DELETE = SERVER + "/user";
	
	/**
	 * 5. 회원 탈퇴
	 * @param context
	 * @param user
	 * @param listener
	 */
	public void setUserDelete(Context context, final OnResultListener<MyUserResultList> listener) {
//		String url = String.format(URL_USER_LIST, user);
//		RequestParams params = new RequestParams();
//		params.put("user", ""+user);
		
		client.delete(context, URL_USER_DELETE, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
public static final String URL_VOICE_MEMBER_LIST = SERVER + "/user";
	
	/**
	 * 6. 사용자 주위의 사용자 보기
	 * @param context
	 * @param user
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @param page
	 * @param listener
	 */
	public void getMapMembers(Context context, String latitude, String longitude, String radius, String page, final OnResultListener<MyUserResultList> listener) {
//		String url = String.format(URL_USER_LIST, user);
		RequestParams params = new RequestParams();
		params.put("latitude", ""+latitude);
		params.put("longitude", ""+longitude);
		params.put("radius", ""+radius);
		params.put("page", ""+page);
		
		client.get(context, URL_VOICE_MEMBER_LIST, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_USER_RECORD = SERVER + "/user/%s/record";
	/**
	 * 7. 사용자가 쓴 모든 기록
	 * @param context
	 * @param userId
	 * @param listener
	 */
	public void getUserRecord(Context context, String userId, final OnResultListener<RecordResultList> listener) {
		String url = String.format(URL_USER_RECORD, userId);
		RequestParams params = new RequestParams();
//		params.put("user", ""+user);
//		params.put("_method", "DELETE");
		
		client.get(context, url, params, new TextHttpResponseHandler() {
			
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				RecordResultList result = gson.fromJson(responseString, RecordResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String URL_USER_WRITE = SERVER + "/record";
	
	/**
	 * 8. 레코드 올리기
	 * @param context
	 * @param content
	 * @param voice
	 * @param images
	 * @param link
	 * @param latitude
	 * @param longitude
	 * @param tags
	 * @param recordPlace
	 * @param listener
	 */
	public void setRecordWrite(Context context,  String content, String voice, String[] images, String link, String latitude, String longitude, int [] tags, String recordPlace, final OnResultListener<RecordResultList> listener) {
//		String url = String.format(URL_USER_LIST, user);
		RequestParams params = new RequestParams();
		params.put("content", ""+content);
		try {
			if (voice != null) {
				params.put("voice", new File(voice));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String s : images) {
			try {
				if (s != null && !s.equals("")) {
					params.put("images", new File(s));
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (Integer s : tags) {
			if(s != null && s != 0){
				params.add("tags", ""+s);
			}
		}		
		params.put("link", ""+link);
		params.put("latitude", ""+latitude);
		params.put("longitude", ""+longitude);
		params.put("record_place", ""+recordPlace);
		
		client.post(context, URL_USER_WRITE, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				RecordResultList result = gson.fromJson(responseString, RecordResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String URL_LOCATION_RECORD = SERVER + "/record/location";
	
	/**
	 * 9. 위치 기반 기록조회
	 * @param context
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @param page
	 * @param listener
	 */
	public void getLocationRecord(Context context, String latitude, String longitude, String radius, String page, final OnResultListener<RecordResultList> listener) {
//		String url = String.format(URL_USER_LIST, user);
		RequestParams params = new RequestParams();
		params.put("latitude", ""+latitude);
		params.put("longitude", ""+longitude);
		params.put("radius", ""+radius);
		params.put("page", ""+page);
		
		client.get(context, URL_LOCATION_RECORD, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				RecordResultList result = gson.fromJson(responseString, RecordResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_WRITE_MODIFY = SERVER + "/record/%s";
	
	/**
	 * 10. 기록수정
	 * @param context
	 * @param id
	 * @param title
	 * @param content
	 * @param voice
	 * @param images
	 * @param link
	 * @param latitude
	 * @param longitude
	 * @param tags
	 * @param listener
	 */
	public void setRecordModify(Context context, String id, String title, String content, String voice, String images, String link, String latitude, String longitude, String tags, final OnResultListener<RecordResultList> listener) {
		String url = String.format(URL_WRITE_MODIFY, id);
		RequestParams params = new RequestParams();
		params.put("_method", "PUT");
		params.put("title", ""+title);
		params.put("content", ""+content);
		params.put("voice", ""+voice);
		params.put("images", ""+images);
		params.put("link", ""+link);
		params.put("latitude", ""+latitude);
		params.put("longitude", ""+longitude);
		params.put("tags", ""+tags);
		
		client.get(context, url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				RecordResultList result = gson.fromJson(responseString, RecordResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_RECORD_DELETE = SERVER + "/record/%s";
	
	/**
	 * 11. 기록삭제
	 * @param context
	 * @param id
	 * @param listener
	 */
	public void setRecordDelete(Context context, String recordId, final OnResultListener<RecordResultList> listener) {
		String url = String.format(URL_RECORD_DELETE, recordId);
//		RequestParams params = new RequestParams();
		
		client.delete(context, url, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				RecordResultList result = gson.fromJson(responseString, RecordResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String URL_RECORD_HEART = SERVER + "/heart/%s";
	
	/**
	 * 12. 하트주기
	 * @param context
	 * @param id
	 * @param listener
	 */
	public void setRecordHeart(Context context, String id, final OnResultListener<RecordResultList> listener) {
		String url = String.format(URL_RECORD_HEART, id);
		RequestParams params = new RequestParams();
//		params.put("_method", "DELETE");
		
		client.post(context, url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				RecordResultList result = gson.fromJson(responseString, RecordResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String URL_RECORD_REPLY_WRITE = SERVER + "/record/%s/reply";
	/**
	 * 13. 댓글 쓰기
	 * @param context
	 * @param recordId
	 * @param content
	 * @param listener
	 */
	public void setRecordReplyWrite(Context context, String recordId, String content, final OnResultListener<RecordReplyList> listener) {
		String url = String.format(URL_RECORD_REPLY_WRITE, recordId);
		RequestParams params = new RequestParams();
		params.put("message", content);
		
		client.post(context, url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				RecordReplyList result = gson.fromJson(responseString, RecordReplyList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_RECORD_REPLY_MODIFY = SERVER + "/record/%s/reply/%s";
	
	/**
	 * 14. 댓글 수정
	 * @param context
	 * @param id
	 * @param replyId
	 * @param message
	 * @param listener
	 */
	public void setRecordReplyModify(Context context, String recordId, String replyId, String message, final OnResultListener<RecordReplyList> listener) {
		String url = String.format(URL_RECORD_REPLY_MODIFY, recordId, replyId);
		RequestParams params = new RequestParams();
//		params.put("replyId", "replyId");
		params.put("message", message);
		
		client.put(context, url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				RecordReplyList result = gson.fromJson(responseString, RecordReplyList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_RECORD_REPLY_DELETE = SERVER + "/record/%s/reply/%s";
	/**
	 * 15. 댓글 삭제
	 * @param context
	 * @param id
	 * @param replyId
	 * @param message
	 * @param listener
	 */
	public void delRecordReplyDelete(Context context, String recordId, String replyId, final OnResultListener<RecordReplyList> listener) {
		String url = String.format(URL_RECORD_REPLY_DELETE, recordId, replyId);
//		RequestParams params = new RequestParams();
//		params.put("replyId", "replyId");
//		params.put("message", "message");
		
		client.delete(context, url, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				RecordReplyList result = gson.fromJson(responseString, RecordReplyList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_RECORD_REPLY = SERVER + "/record/%s/reply";
	/**
	 * 16. 댓글 조회
	 * @param context
	 * @param replyId
	 * @param listener
	 */
	public void getRecordReply(Context context, String recordId, final OnResultListener<RecordReplyList> listener) {
		String url = String.format(URL_RECORD_REPLY, recordId);
		RequestParams params = new RequestParams();
//		params.put("reply", "/reply");
		
		client.get(context, url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				RecordReplyList result = gson.fromJson(responseString, RecordReplyList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_USER_BLOCK = SERVER + "/ban";
	/**
	 * 17. 차단하기
	 * @param context
	 * @param userId
	 * @param listener
	 */
	public void setUserBlock(Context context, String userId, final OnResultListener<MyUserResultList> listener) {
//		String url = String.format(URL_USER_BLOCK, userId);
		RequestParams params = new RequestParams();
		params.put("u_id", ""+userId);
		
		client.post(context, URL_USER_BLOCK, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_USER_BLOCK_CANCEL = SERVER + "/ban/%s";
	/**
	 * 18. 차단 해제
	 * @param context
	 * @param userId
	 * @param listener
	 */
	public void setUserBlockCancel(Context context, String userId, final OnResultListener<MyUserResultList> listener) {
		String url = String.format(URL_USER_BLOCK_CANCEL, userId);
//		RequestParams params = new RequestParams();
//		params.put("_method", "DELETE");
		
		client.delete(context, url, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_USER_BLOCK_LIST = SERVER + "/ban";
	/**
	 * 19. 차단 정보 조회
	 * @param context
	 * @param userId
	 * @param listener
	 */
	public void setUserBlockList(Context context, final OnResultListener<BlockResultList> listener) {
//		String url = String.format(URL_USER_BLOCK_LIST, userId);
		RequestParams params = new RequestParams();
//		params.put("userId", "userId");
		
		client.get(context, URL_USER_BLOCK_LIST, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				BlockResultList result = gson.fromJson(responseString, BlockResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	

	public static final String URL_FOLLOW = SERVER + "/following";
	/**
	 * 20. 팔로우 하기
	 * @param context
	 * @param userId
	 * @param listener
	 */
	public void setUserFollow(Context context, String userId, final OnResultListener<MyUserResultList> listener) {
//		String url = String.format(URL_FOLLOW, userId);
		RequestParams params = new RequestParams();
		params.put("u_id", ""+userId);
		

		client.post(context, URL_FOLLOW, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}

	public static final String URL_FOLLOW_CANCEL = SERVER + "/following/%s";
	/**
	 * 21. 팔로우 삭제
	 * @param context
	 * @param userId
	 * @param listener
	 */
	public void setFollowCancel(Context context, String userId, final OnResultListener<MyUserResultList> listener) {
		String url = String.format(URL_FOLLOW_CANCEL, userId);
//		RequestParams params = new RequestParams();
//		params.put("_method", "DELETE");
		
		client.delete(context, url, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	

	public static final String URL_FOLLOW_LIST = SERVER + "/following/%s";
	/**
	 * 22. 팔로우 리스트 조회
	 * @param context
	 * @param userId
	 * @param listener
	 */
	public void getFollowList(Context context, String userId, final OnResultListener<FollowResultList> listener) {
		String url = String.format(URL_FOLLOW_LIST, userId);
		RequestParams params = new RequestParams();
//		params.put("_method", "DELETE");
		
		client.get(context, url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				FollowResultList result = gson.fromJson(responseString, FollowResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	

	public static final String URL_VOICE_SEND = SERVER + "/radiogram";
	/**
	 * 23. 무전 보내기
	 * @param context
	 * @param content
	 * @param voice
	 * @param receiveId
	 * @param listener
	 */
	public void setVoiceMsgSend(Context context, String content, String voice, String receiveId, final OnResultListener<VoiceResultList> listener) {
//		String url = String.format(URL_VOICE_SEND, userId);
		RequestParams params = new RequestParams();
		try {
			if (voice != null) {
				params.put("voice", new File(voice));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		params.put("content", ""+content);
		params.put("receive_id", ""+receiveId);
		
		client.post(context, URL_VOICE_SEND, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				VoiceResultList result = gson.fromJson(responseString, VoiceResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String URL_TAG_RECORD = SERVER + "/record/tag";
	
	/**
	 * 	 25. 태그(Tag)로 기록 조회하기
	 * @param context
	 * @param tags
	 * @param listener
	 */
	public void getTagRecord(Context context, ArrayList<Integer> tags, final OnResultListener<RecordResultList> listener) {
//		String url = String.format(URL_USER_LIST, user);
		RequestParams params = new RequestParams();
		for(Integer t : tags){
			params.add("viewtag","" + t);
		}
		
//		params.put("viewtag"+"["+key5+"]", ""+val5);
		
		client.get(context, URL_TAG_RECORD, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				RecordResultList result = gson.fromJson(responseString, RecordResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_RECORD_CONTENT = SERVER + "/record/%s";
	
	/**
	 * 24.기록 ID로 기록조회
	 * @param context
	 * @param recordId
	 * @param listener
	 */
	public void getRecordContent(Context context, String recordId, final OnResultListener<CRecordResultList> listener) {
		String url = String.format(URL_RECORD_CONTENT, recordId);
		RequestParams params = new RequestParams();
//		params.put("viewtag", ""+recordId);
		
		client.get(context, url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				CRecordResultList result = gson.fromJson(responseString, CRecordResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_USER_LOGIN = SERVER + "/user/login";
	/**
	 * 26. 로그인
	 * @param context
	 * @param email
	 * @param password
	 * @param listener
	 */
	public void userLogin(Context context, String email, String password, final OnResultListener<MyUserResultList> listener) {
//		String url = String.format(URL_USER_LIST, user);
		RequestParams params = new RequestParams();
		params.put("email", ""+email);
		params.put("password", ""+password);
		
		client.post(context, URL_USER_LOGIN, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_CHECK_EMAIL = SERVER + "/user/checkemail";
	/**
	 * 27. 이메일 체크 중복확인
	 * @param context
	 * @param email
	 * @param listener
	 */
	public void getcheckEmail(Context context, String email, final OnResultListener<MyUserResultList> listener) {
//		String url = String.format(URL_CHECK_EMAIL, email);
		RequestParams params = new RequestParams();
		params.put("email", ""+email);
		
		client.post(context, URL_CHECK_EMAIL, params, new TextHttpResponseHandler() {
			
//			RequestParams params = new RequestParams();
//			params.put("name", ""+name);
//			
//			client.post(context, URL_USERNAME_CHECK, params, new TextHttpResponseHandler() {
			
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String URL_RECORD_SCRAP = SERVER + "/scrap";
	/**
	 * 28. 스크랩하기
	 * @param context
	 * @param rId
	 * @param listener
	 */
	public void setRecordScrap(Context context, String recordId, final OnResultListener<MyUserResultList> listener) {
//		String url = String.format(URL_RECORD_SCRAP, recordId);
		RequestParams params = new RequestParams();
		params.put("r_id", ""+recordId);
		
		client.post(context, URL_RECORD_SCRAP, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_RECORD_SCRAP_LIST= SERVER + "/scrap/%s";
	/**
	 * 29. 스크랩 조회
	 * @param context
	 * @param listener
	 */
	public void getScrapList(Context context, String userId, final OnResultListener<ScrapResultList> listener) {
		String url = String.format(URL_RECORD_SCRAP_LIST, userId);
		RequestParams params = new RequestParams();
//		params.put("email", ""+email);
		
		client.get(context, url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				ScrapResultList result = gson.fromJson(responseString, ScrapResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_MSG_VOICE_LIST= SERVER + "/radiogram/all/%s";
	/**
	 * 30. 무전 받고 보낸거 모두 보기
	 * @param context
	 * @param option
	 * @param listener
	 */
	public void getMsgVoiceAllList(Context context, String option, final OnResultListener<VoiceResultList> listener) {
		String url = String.format(URL_MSG_VOICE_LIST, option);
		RequestParams params = new RequestParams();
//		params.put("email", ""+email);
		
		client.get(context, url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				VoiceResultList result = gson.fromJson(responseString, VoiceResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_MSG_VOICE_SEND_LIST= SERVER + "/radiogram/send/%s";
	/**
	 * 31. 보낸 무전 모두 보기
	 * @param context
	 * @param option
	 * @param listener
	 */
	public void getMsgVoiceSendList(Context context, String option, final OnResultListener<VoiceResultList> listener) {
		String url = String.format(URL_MSG_VOICE_SEND_LIST, option);
		RequestParams params = new RequestParams();
//		params.put("email", ""+email);
		
		client.get(context, url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				VoiceResultList result = gson.fromJson(responseString, VoiceResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_MSG_VOICE_RECEIVE_LIST= SERVER + "/radiogram/receive/%s";
	/**
	 * 32. 받은 무전 모두 보기
	 * @param context
	 * @param option
	 * @param listener
	 */
	public void getMsgVoiceReceiveList(Context context, String option, final OnResultListener<VoiceResultList> listener) {
		String url = String.format(URL_MSG_VOICE_RECEIVE_LIST, option);
		RequestParams params = new RequestParams();
//		params.put("email", ""+email);
		
		client.get(context, url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				VoiceResultList result = gson.fromJson(responseString, VoiceResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_MSG_VOICE_DELETE= SERVER + "/radiogram/%s/%s";
	/**
	 * 33. 무전 삭제하기
	 * @param context
	 * @param option
	 * @param listener
	 */
	public void delMsgVoiceDelete(Context context, String radiogramId, String deleteOption, final OnResultListener<VoiceResultList> listener) {
		String url = String.format(URL_MSG_VOICE_DELETE, radiogramId, deleteOption);
//		RequestParams params = new RequestParams();
//		params.put("deleteoption", ""+deleteOption);
		
		client.delete(context, url, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				VoiceResultList result = gson.fromJson(responseString, VoiceResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	

	public static final String URL_MSG_VOICE_SAVE= SERVER + "/radiogram/save";
	/**
	 * 34. 무전 보관하기
	 * @param context
	 * @param option
	 * @param listener
	 */
	public void setMsgVoiceSave(Context context, String radiogramId, String deleteOption, final OnResultListener<VoiceResultList> listener) {
//		String url = String.format(URL_MSG_VOICE_RECEIVE_LIST, radiogramId, deleteOption);
		RequestParams params = new RequestParams();
		params.put("radiogram_id", ""+radiogramId);
		params.put("saveoption", ""+deleteOption);
		
		client.post(context, URL_MSG_VOICE_SAVE, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				VoiceResultList result = gson.fromJson(responseString, VoiceResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	

	public static final String URL_MSG_VOICE_SAVE_LIST= SERVER + "/radiogram/save";
	/**
	 * 35. 무전 보관함 보기
	 * @param context
	 * @param option
	 * @param listener
	 */
	public void getMsgVoiceSaveList(Context context, final OnResultListener<VoiceResultList> listener) {
//		String url = String.format(URL_MSG_VOICE_RECEIVE_LIST, option);
		RequestParams params = new RequestParams();
//		params.put("email", ""+email);
		
		client.get(context, URL_MSG_VOICE_SAVE_LIST, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				VoiceResultList result = gson.fromJson(responseString, VoiceResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String URL_MSG_VOICE_SAVE_DELETE= SERVER + "/radiogram/save/%s/%s";
	/**
	 * 36. 무전 보관함 삭제
	 * @param context
	 * @param radiogramId
	 * @param deleteOption
	 * @param listener
	 */
	public void delMsgVoiceSaveDel(Context context, String radiogramId, String deleteOption, final OnResultListener<VoiceResultList> listener) {
		String url = String.format(URL_MSG_VOICE_SAVE_DELETE, radiogramId, deleteOption);
		RequestParams params = new RequestParams();
//		params.put("email", ""+email);
		
		client.delete(context, url, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				VoiceResultList result = gson.fromJson(responseString, VoiceResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	

	public static final String URL_RECORD_SCRAP_DELETE= SERVER + "/scrap/%s";
	/**
	 * 37.스크랩 삭제
	 * @param context
	 * @param rId
	 * @param listener
	 */
	public void delScrap(Context context, String rId, final OnResultListener<ScrapResultList> listener) {
		String url = String.format(URL_RECORD_SCRAP_DELETE, rId);
//		RequestParams params = new RequestParams();
//		params.put("email", ""+email);
		
		client.delete(context, url, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				ScrapResultList result = gson.fromJson(responseString, ScrapResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}

	public static final String URL_USER_LOGOUT = SERVER + "/user/logout";
	/**
	 * 38. 로그아웃
	 * @param context
	 * @param email
	 * @param password
	 * @param listener
	 */
	public void userLogOut(Context context, final OnResultListener<MyUserResultList> listener) {
//		String url = String.format(URL_USER_LOGOUT, user);
		RequestParams params = new RequestParams();
//		params.put("email", ""+email);
//		params.put("password", ""+password);
		
		client.get(context, URL_USER_LOGOUT, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	public static final String URL_FIND_PASSWORD = SERVER + "/user/findpassword";
	/**
	 * 39.비밀번호 찾기
	 * @param context
	 * @param email
	 * @param listener
	 */
	public void userFindPW(Context context, String email,  final OnResultListener<MyUserResultList> listener) {
//		String url = String.format(URL_USER_LOGOUT, user);
		RequestParams params = new RequestParams();
		params.put("email", ""+email);
//		params.put("password", ""+password);
		
		client.post(context, URL_FIND_PASSWORD, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String URL_POWER_TJ_RECORD = SERVER + "/user/best";
	/**
	 * 40.베스트 사용자 기록조회
	 * @param context
	 * @param page
	 * @param listener
	 */
	public void getPowerTJRecord(Context context, int page, final OnResultListener<RecordResultList> listener) {
//		String url = String.format(URL_POWER_TJ_RECORD, user);
		RequestParams params = new RequestParams();
		params.put("page", ""+page);
		
		client.get(context, URL_POWER_TJ_RECORD, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				RecordResultList result = gson.fromJson(responseString, RecordResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	

	public static final String URL_FOLLOW_RECORD_LIST= SERVER + "/following/%s/record";
	/**
	 * 41.following 기록 조회 
	 * @param context
	 * @param userId
	 * @param page
	 * @param listener
	 */
	public void getFollowRecordList(Context context, String userId, String page, final OnResultListener<RecordResultList> listener) {
		String url = String.format(URL_FOLLOW_RECORD_LIST, userId);
		RequestParams params = new RequestParams();
		params.put("page", ""+page);
		
		client.get(context, url, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				RecordResultList result = gson.fromJson(responseString, RecordResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	public static final String URL_FACEBOOK_LOGIN = SERVER + "/user/login";
	/**
	 * FACEBOOK 로그인
	 * @param context
	 * @param token
	 * @param email
	 * @param password
	 * @param listener
	 */
	public void facebookLogin(Context context, String token, String email, String password, final OnResultListener<MyUserResultList> listener) {
//		String url = String.format(URL_USER_LIST, user);
		RequestParams params = new RequestParams();
		params.put("token", ""+token);
		params.put("email", ""+email);
		params.put("password", ""+password);
		
		client.post(context, URL_FACEBOOK_LOGIN, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				MyUserResultList result = gson.fromJson(responseString, MyUserResultList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	
}

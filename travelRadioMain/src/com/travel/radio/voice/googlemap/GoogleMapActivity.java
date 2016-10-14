package com.travel.radio.voice.googlemap;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.travel.radio.main.MainActivity;
import com.travel.radio.main.NetworkManager;
import com.travel.radio.main.NetworkManager.OnResultListener;
import com.travel.radio.main.PropertyManager;
import com.travel.radio.main.R;
import com.travel.radio.member.data.MyUserResultList;
import com.travel.radio.member.login.MemberLogin;
import com.travel.radio.voice.googlemap.geocoder.GAddressComponents;
import com.travel.radio.voice.googlemap.geocoder.GResults;
import com.travel.radio.voice.googlemap.geocoder.GeocoderResultList;
import com.travel.radio.voice.googlemap.search.MapResultList;
import com.travel.radio.voice.googlemap.search.Results;
import com.travel.radio.voice.memberlist.VoiceMemberList;

public class GoogleMapActivity extends ActionBarActivity implements
		GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener,
		GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerDragListener,
		GoogleMap.OnCameraChangeListener {

	GoogleMap mMap;
//	ListView listView;
	ListView listViewGPOI;
	ArrayAdapter<Results> sAdapter;
	EditText editTkeyword;
	
	Marker marker;
	Circle mCircle;
	int radius = 0;
	final static int RADIUS1 = 10;
	final static int RADIUS2 = 20;
	final static int RADIUS3 = 30;
	final static int RADIUS4 = 40;
	final static int RADIUS5 = 50;

	HashMap<POI, Marker> mMarkerResolver = new HashMap<POI, Marker>();
	HashMap<Marker, POI> mPOIResolver = new HashMap<Marker, POI>();
	
	
	RadioButton rbR1;
	RadioButton rbR2;
	RadioButton rbR3;
	RadioButton rbR4;
	RadioButton rbR5;
	
	
	String country;
	String address1;
	String address2;
	String userPlace;
	LatLng latLng;
	Double latitude;
	Double longitude;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.i_googlemap);
		
		getSupportActionBar().hide();
		getSupportActionBar().setTitle("위치설정");
		getSupportActionBar().setIcon(R.drawable.actionbar_radio);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		
		Intent intent = getIntent();  
		
//		if(intent.getExtras().getString("latitude")!=null || !intent.getExtras().getString("latitude").equals("")){
//			latitude = Double.parseDouble(intent.getExtras().getString("latitude"));
//		    longitude = Double.parseDouble(intent.getExtras().getString("longitude"));
		    latitude = Double.parseDouble(PropertyManager.getInstance().getLatitude());
		    longitude = Double.parseDouble(PropertyManager.getInstance().getLongitude());
//		}
	     
//	    latitude = Double.parseDouble(intent.getExtras().getString("latitude"));
//	    longitude = Double.parseDouble(intent.getExtras().getString("longitude"));
	     
	    
	    
	    listViewGPOI = (ListView) findViewById(R.id.listView_google_places);
		sAdapter = new ArrayAdapter<Results>(this, android.R.layout.simple_list_item_1, new ArrayList<Results>());
		listViewGPOI.setAdapter(sAdapter);
		listViewGPOI.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,	int position, long id) {
				Toast.makeText(getApplicationContext(), "list Click", Toast.LENGTH_LONG).show();
				Results results = (Results) listViewGPOI.getItemAtPosition(position);
				moveMap(results.geometry.location.lat, results.geometry.location.lng);
				
				listViewGPOI.setVisibility(view.INVISIBLE);

//				Marker marker = mMarkerResolver.get(results);
//				moveMap(marker.getPosition().latitude,	marker.getPosition().longitude);
//				marker.showInfoWindow();
			}
		});

		
		
		setupMapIfNeeded();
		

		rbR1=(RadioButton)findViewById(R.id.radioButtonR1);
		rbR1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					mCircleMake(1000);			
					radius = RADIUS1;
				}
			}
		});
		rbR2=(RadioButton)findViewById(R.id.radioButtonR2);
		rbR2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					mCircleMake(2000);			
					radius = RADIUS2;
				}
			}
		});
		rbR3=(RadioButton)findViewById(R.id.radioButtonR3);
		rbR3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					mCircleMake(3000);			
					radius = RADIUS3;
				}
			}
		});
		rbR4=(RadioButton)findViewById(R.id.radioButtonR4);
		rbR4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					mCircleMake(4000);			
					radius = RADIUS4;
				}
			}
		});
		rbR5=(RadioButton)findViewById(R.id.radioButtonR5);
		rbR5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					mCircleMake(5000);			
					radius = RADIUS5;
				}
			}
		});
		

		Button btn = (Button)findViewById(R.id.button_map_back);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		editTkeyword = (EditText) findViewById(R.id.keyword);
		editTkeyword.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					textSearch();					
				}
				return false;
			}
		});
		
		btn = (Button)findViewById(R.id.btn_search);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				textSearch();
				InputMethodManager imm = (InputMethodManager)getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(editTkeyword.getWindowToken(), 0);
			}
		});
		
		Toast toast = Toast.makeText(GoogleMapActivity.this, "위치를 설정하려면 지도를 클릭하세요.", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show(); 
		
	}
	
	private void mCircleMake(int intRadius) {
		if(marker==null || marker.equals("")){
			Toast toast = Toast.makeText(GoogleMapActivity.this, "지도를 클릭하세요.", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show(); 
		}else{
			circleRemove();
			CircleOptions options = new CircleOptions();
			options.center(mMap.getCameraPosition().target);
			options.radius(intRadius);
			options.fillColor(0x5000fff6);
			options.strokeColor(0x9000fff6);
			options.strokeWidth(5);
			mCircle = mMap.addCircle(options);
			mCircle.setCenter(latLng);				
		}

	}
	
	private void circleRemove() {
		if(mCircle!=null){
			mCircle.remove();
			mCircle=null;
		}
		radius=0;
	}
	
	private void markerRemove() {
		if (marker != null) {
			marker.remove();
			marker = null;
		}
	}
	
	/**
	 *  Google places Search
	 */
	public void textSearch(){
		if(editTkeyword.getText().toString() == null || editTkeyword.getText().toString().equals("")){
			Toast.makeText(getApplicationContext(), "검색어를 입력해주세요", Toast.LENGTH_SHORT).show();
		}else{
			String keyword = editTkeyword.getText().toString();
			NetworkManager.getInstance().getTextSearch(getApplicationContext(), keyword, new OnResultListener<MapResultList>() {
				
				@Override
				public void onSuccess(MapResultList result) {
					
					if(result.status.equals("OK")){
						
						if(result.results.isEmpty() == false){
							Toast.makeText(getApplicationContext(), "검색완료", Toast.LENGTH_SHORT).show();
							listViewGPOI.setVisibility(View.VISIBLE);
//							listViewGPOI.smoothScrollToPosition(sAdapter.getCount()-1);
							listViewGPOI.smoothScrollToPosition(0);

							sAdapter.clear();
							for (Results results : result.results) {
								sAdapter.add(results);
							}
						}else{
							
							listViewGPOI.setVisibility(View.INVISIBLE);
							Toast.makeText(getApplicationContext(), "검색결과 없음", Toast.LENGTH_SHORT).show();
							editTkeyword.setText("");
						}

					}else{
						
						Toast.makeText(getApplicationContext(), "검색결과가 없습니다", Toast.LENGTH_SHORT).show();
//						Toast.makeText(getApplicationContext(), result.status, Toast.LENGTH_LONG).show();
						
					}
					
					
//					for (int i = 0; i < mAdapter.getCount(); i++) {
//						
//						POI p = mAdapter.getItem(i);
//						Marker m = mMarkerResolver.get(p);
//						m.remove();
//						mMarkerResolver.remove(p);
//						mPOIResolver.remove(m);
//					}
					
				}
				
				@Override
				public void onFail(int code) {
					listViewGPOI.setVisibility(View.INVISIBLE);
					Toast.makeText(getApplicationContext(), "value fail", Toast.LENGTH_LONG).show();
					
				}
			});
		}
	}
	
	
//	private void addMarker(POI poi) {
//		
//		markerRemove();
//		
//		MarkerOptions options = new MarkerOptions();
//		LatLng latLng = new LatLng(poi.getLatitude(), poi.getLongitude());
//		options.position(latLng);
//		options.anchor(0.5f, 1);
//		options.title(poi.name);
//		options.snippet(poi.detailAddrName);
////		options.icon(BitmapDescriptorFactory
////				.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
//		options.icon(BitmapDescriptorFactory.fromBitmap(infoBitmap.getMarkerBitmap(poi)));
//		options.draggable(false);
//		// options.visible(true);
//		marker = mMap.addMarker(options);
//		
//		
//		
////		mMarkerResolver.put(poi, marker);
////		mPOIResolver.put(marker, poi);
//	}


	@Override
	protected void onResume() {
		super.onResume();
		setupMapIfNeeded();
	}

	private void setupMapIfNeeded() {
		if (mMap == null) {
			SupportMapFragment f = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.fragment1);
			mMap = f.getMap();
			if (mMap != null) {
				setupMap();
			}
		}
	}

	InfoBitmap infoBitmap;
	private void setupMap() {
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mMap.setMyLocationEnabled(true);
		mMap.setOnMapClickListener(this);
		mMap.setOnMarkerClickListener(this);
		mMap.setOnInfoWindowClickListener(this);
		mMap.setOnMarkerDragListener(this);
		mMap.setOnCameraChangeListener(this);
		
		mMap.setInfoWindowAdapter(new MyInfoWindowAdapter(this, mPOIResolver));
		infoBitmap = new InfoBitmap(this);
		// mMap.setTrafficEnabled(true);
		// mMap.getUiSettings().setZoomControlsEnabled(false);
		// mMap.getUiSettings().setMyLocationButtonEnabled(false);
		// mMap.getUiSettings().setCompassEnabled(true);
		// mMap.getUiSettings().setScrollGesturesEnabled(false);
		moveMap(latitude, longitude);
//		LatLng latlng = (37.566535, 126.9779692);
//		onMapClick(latlng);
	}

	private void moveMap(double lat, double lng) {
		if (mMap != null) {
			LatLng pos = new LatLng(lat, lng);
			CameraPosition.Builder builder = new CameraPosition.Builder();
			builder.target(pos);
			builder.zoom(11.5f);
			// builder.bearing(30);
			// builder.tilt(30);
			CameraPosition cp = builder.build();

			CameraUpdate update = CameraUpdateFactory.newCameraPosition(cp);

//			mMap.moveCamera(update);
			mMap.animateCamera(update);
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * types 매칭되는 지 검사.
	 * @param list
	 * @param key
	 * @return
	 */
	private boolean match(List<String> list, String key) {
		for (String d : list) {
			if (d.equals(key)) {
				return true;
			}
		}
		return false;
	}
	


	@Override
	public void onMapClick(LatLng latlng) {
		latLng = latlng;
		latitude = latlng.latitude;
		longitude = latlng.longitude;
		
		circleRemove();
		moveMap(latitude, longitude);
		
		listViewGPOI.setVisibility(View.INVISIBLE);
		
		NetworkManager.getInstance().getGeocoderAdrs(getApplicationContext(), latlng, new OnResultListener<GeocoderResultList>() {

			@Override
			public void onSuccess(GeocoderResultList result) {
//				if(address2!=null)address2="";
				address1 = "";
				address2 = "";
				
//				Toast.makeText(getApplicationContext(), result.errorMessage, Toast.LENGTH_SHORT).show();
				
				
				for (GResults r : result.results) {
					for (GAddressComponents addr : r.addressComponents) {
						if (match(addr.types, "country")) {
							country = addr.longName;
						}
						if (match(addr.types, "locality")) {
							address1 = addr.longName;
						}
						// america address
						if (match(addr.types, "neighborhood")) {
							address2 = addr.longName;
						}
						// korea address
						if (match(addr.types, "sublocality")) {
							address2 = addr.longName;
						}
					}
				}
				userPlace=address2 + " " + address1 + ", " + country;
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(getApplicationContext(), "Geocoder_onFail", Toast.LENGTH_LONG).show();
			}
		});
		
		MarkerOptions options = new MarkerOptions();		
		options.position(latlng);
		options.anchor(0.5f, 1);
		options.title("나의 위치");
		options.snippet("클릭하세요");
		options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
		options.draggable(false);
		// options.visible(true);
		
		markerRemove();
		marker = mMap.addMarker(options);
		marker.showInfoWindow();
		
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
//		Toast.makeText(this, "marker : " + marker.getTitle(),
//				Toast.LENGTH_SHORT).show();
		marker.showInfoWindow();
		return true;
	}

	
	@Override
	public void onInfoWindowClick(final Marker marker) {
//		Toast.makeText(this, "info window click", Toast.LENGTH_SHORT).show();
		marker.hideInfoWindow();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("위치설정");
		builder.setMessage(address2 + " " + address1 + ", " + country + "\n" +"이 위치로 설정할까요?");
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(radius == 0){
					Toast toast = Toast.makeText(getApplicationContext(), "상단의 검색반경을 선택한 후\n 위치를 설정해주세요.", Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}else{
//					Intent intent = new Intent(GoogleMapActivity.this,VoiceMemberList.class);
//					startActivity(intent);
					PropertyManager.getInstance().setLatitude(""+latitude);
					PropertyManager.getInstance().setLongitude(""+longitude);
					PropertyManager.getInstance().setRadius(""+radius);
					PropertyManager.getInstance().setUserPlace(""+userPlace);
					
					if(!PropertyManager.getInstance().getUserId().equals("") || PropertyManager.getInstance().getUserId()!=null){
						modifyLocation(userPlace);
					}
					
					Toast.makeText(GoogleMapActivity.this, "위치가 설정되었습니다.", Toast.LENGTH_SHORT).show();

				}
			}
		});
		
		builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		
		builder.create().show();
	}
	
	
	private void modifyLocation(String userPlace) {
		
		
		String name = PropertyManager.getInstance().getName();
		String latitude = ""+this.latitude;
		String longitude = ""+this.longitude;
		
		NetworkManager.getInstance().setLocationModify(getApplicationContext(), name, userPlace, latitude, longitude, new OnResultListener<MyUserResultList>() {

			@Override
			public void onSuccess(MyUserResultList result) {
				if(result.success==1){
//					Toast.makeText(GoogleMapActivity.this, "위치가 설정되었습니다.", Toast.LENGTH_SHORT).show();
					finish();				
				}else{
					Toast.makeText(GoogleMapActivity.this, "OK", Toast.LENGTH_SHORT).show();
					finish();
				}
				
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(GoogleMapActivity.this, "modify_onfail.", Toast.LENGTH_SHORT).show();
				
			}
		});

	}

	@Override
	public void onMarkerDrag(Marker arg0) {

	}

	@Override
	public void onMarkerDragEnd(Marker marker) {

	}

	@Override
	public void onMarkerDragStart(Marker arg0) {

	}

	@Override
	public void onCameraChange(CameraPosition arg0) {
//		if (mCircle != null) {
//			mCircle.remove();
//			mCircle = null;
//		}
	}
}

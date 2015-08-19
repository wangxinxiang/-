package com.example.complain1.utils;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;
import com.baidu.location.*;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MyLocationData;


public class LocationApplication extends Application {
	public LocationClient mLocationClient;
	public GeofenceClient mGeofenceClient;
	public MyLocationListener mMyLocationListener;
	public Vibrator mVibrator;
	private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
	//
	private String tempcoor="gcj02";
	public TextView address;
	public MyLocationData locData;
	
	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(getApplicationContext());
		mLocationClient = new LocationClient(this.getApplicationContext());
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		mGeofenceClient = new GeofenceClient(getApplicationContext());
		mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
		initLocation();
	}


	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// 构造定位数据
			if(location != null){
				locData = new MyLocationData.Builder()
						.accuracy(location.getRadius())
								// 此处设置开发者获取到的方向信息，顺时针0-360
						.direction(100).latitude(location.getLatitude())
						.longitude(location.getLongitude()).build();
			}
			//Receive Location
			if(address != null){
				String add = "";
				add = addString(add, location.getCity());
				Log.d("onReceiveLocation","add");
				add = addString(add,location.getDistrict());
				Log.d("onReceiveLocation","add");
				add = addString(add,location.getStreet());
				Log.d("onReceiveLocation","add");
				add = addString(add,location.getStreetNumber());
				Log.d("onReceiveLocation","add");
				address.setText(add);
			}
		}
	}

	//配置百度定位属性
	private void initLocation(){
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(tempMode);//设置定位模式
		option.setCoorType(tempcoor);//返回的定位结果是百度经纬度，默认值gcj02
		int span=1000;//设置发起定位请求的间隔时间为5000ms
		option.setScanSpan(span);
		option.setIsNeedAddress(true);
		mLocationClient.setLocOption(option);
	}

	private String addString(String str,String add){
		if(str != null){
			str += add;
		}
		return str;
	}

}

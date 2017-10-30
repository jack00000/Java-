---
title: lbstest<CODE 2 chaper11>
date: 2017-8-7 20:13:10
tags: CODE 2
---

<h3>学习目的：1.LBS(基于位置的服务)确定用户位置的两种方式（GPS 网络定位(手机与各基站的距离算出位置)）

-  http://developer.baidu.com/user/reg（申请成为百度开发者）  申请APIkey  http://lbsyun.baidu.com/apiconsole/key

使用百度定位 准备LBS SDK http://lbsyun.baidu.com/sdk/download
![Markdown](http://i4.bvimg.com/595109/0edfb96df7081646.png)
![Markdown](http://i4.bvimg.com/595109/721084064698167e.png)
![Markdown](http://i4.bvimg.com/595109/9a1039c4f77274c0.png)
![Markdown](http://i4.bvimg.com/595109/3b1cb2d34fd7bb39.png)
![Markdown](http://i4.bvimg.com/595109/78bb2eca8702e927.png)

androidMainifest.xml
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.lbstest">

    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
    <uses-permission android:name="android.permission.WAKE_LOCK"/>

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <meta-data
            android:name="com.baidu.lbsapi.API_KEY"
            android:value="i6VD2fHKM3msMfZtIOXAhFSzDiYGFIwL" />
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <service android:name="com.baidu.location.f" android:enabled="true" android:process=":remote">
        </service>
    </application>

</manifest>
```


MainActivity
```java
public class MainActivity extends AppCompatActivity {

    public LocationClient mLocationClient;

    private TextView positionText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());//LocationClient实例创建Context参数
        mLocationClient.registerLocationListener(new MyLocationListener());//注册一个监听器  当获取到位置信息就会回调这个监听器

        setContentView(R.layout.activity_main);

        positionText = (TextView) findViewById(R.id.position_text_view);
        List<String> permissionList = new ArrayList<>();//创建List集合  没有授权的权限加入到List中
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        } else {
            requestLocation();
        }
    }



    private void requestLocation() {

        mLocationClient.start();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
           StringBuilder currentPosition = new StringBuilder();

            currentPosition.append("定位方式：");
            if (location.getLocType() == BDLocation.TypeGpsLocation) {
                currentPosition.append("GPS");
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                currentPosition.append("网络");
            }
            positionText.setText(currentPosition);

        }

    }

}
```
问题:LocationClient的start()只会定位一次   快速移动定位需要LBS SDK提供的方法
MainActivity中添加
```java
private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();//更新的间隔
        option.setScanSpan(5000);

        mLocationClient.setLocOption(option);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();//停止定位

    }
```

强制指定GPS进行定位
```java
private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);

        mLocationClient.setLocOption(option);
    }

```

到此，位置信息以文本显示

具体化位置信息
```java
private void initLocation(){
       LocationClientOption option = new LocationClientOption();
       option.setScanSpan(5000);
       option.setIsNeedAddress(true);
       mLocationClient.setLocOption(option);
   }


   public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
            currentPosition.append("经线：").append(location.getLongitude()).append("\n");
            currentPosition.append("国家：").append(location.getCountry()).append("\n");
            currentPosition.append("省：").append(location.getProvince()).append("\n");
            currentPosition.append("市：").append(location.getCity()).append("\n");
            currentPosition.append("区：").append(location.getDistrict()).append("\n");
            currentPosition.append("街道：").append(location.getStreet()).append("\n");
            currentPosition.append("定位方式：");
            if (location.getLocType() == BDLocation.TypeGpsLocation) {
                currentPosition.append("GPS");
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                currentPosition.append("网络");
            }
                        positionText.setText(currentPosition);
            if (location.getLocType() == BDLocation.TypeGpsLocation
                    || location.getLocType() == BDLocation.TypeNetWorkLocation) {
                navigateTo(location);
            }
        }

    }
```
到此，位置信息以文本显示

使用百度地图(显示地图)
```java
<TextView
        android:id="@+id/position_text_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:visibility="gone" />

    <com.baidu.mapapi.map.MapView
        android:id="@+id/bmapView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:clickable="true" />
```

修改MainActivity
```java
public class MainActivity extends AppCompatActivity {


    private MapView mapView;

  //...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.bmapView);

        //...
    }



  //...
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

  //...
}
```        

    移动到我的位置(BaiduMap类  地图的总控制器)
```java
public class MainActivity extends AppCompatActivity {

    public LocationClient mLocationClient;

    private TextView positionText;

    private MapView mapView;

    private BaiduMap baiduMap;

    private boolean isFirstLocate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        positionText = (TextView) findViewById(R.id.position_text_view);
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        } else {
            requestLocation();
        }
    }

    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            Toast.makeText(this, "nav to " + location.getAddrStr(), Toast.LENGTH_SHORT).show();
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.
                Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            if (location.getLocType() == BDLocation.TypeGpsLocation
                    || location.getLocType() == BDLocation.TypeNetWorkLocation) {
                navigateTo(location);
            }
        }

    }

}
```

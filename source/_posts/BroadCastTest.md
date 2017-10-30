---
title: BroadCastTest<CODE 2 chaper5>
date: 2017-7-25 20:13:10
tags: CODE 2
---

<h3>学习目的：1.了解标准广播 有序广播区别(简单，不赘述) 2.接收系统广播 3,发送自定义广播 4.使用本地广播</h3>

<h3>2.接收系统广播<h3>
1.动态注册监听网络变化
- 需要声明权限

```
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.broadcasttest">

    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
>

```
- 新建NetworkChangeReceiver类      
```java
public class MainActivity extends AppCompatActivity {


   private IntentFilter intentFilter;
   private NetworkChangeReceiver networkChangeReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");//当网络发生变化  系统发出值/为android.net.conn.CONNECTIVITY_CHANGE的广播  要监听什么广播就加什么action
        networkChangeReceiver = new NetworkChangeReceiver();//新建NetworkChangeReceiver实例
        registerReceiver(networkChangeReceiver, intentFilter);//对其进行注册

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);//动态注册的的广播接收器一定要取消注册
          }


class NetworkChangeReceiver extends BroadcastReceiver {

       @Override
      public void onReceive(Context context, Intent intent) {
           Toast.makeText(content,"network changes",Toast.LENGTH_SHORT).show();
        }

```

<h3>优化（告诉用户有网还是没网）</h3>
```java
class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);//ConnectivityManager 系统服务类 专门管理网络getSystemService获得NetworkInfo实例
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();//
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

```

2.静态注册监听网路变化
直接NEW-other-BoardCastReceriver
```java

public class BoardCompelteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Board Complete", Toast.LENGTH_SHORT).show();

    }

}

```
添加权限(部分自动生成)
```
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />

    <receiver
            android:name=".BootCompleteReceiver"
            android:enabled="true"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED" />//系统启动时会发android.intent.action.BOOT_COMPLETED值得广播
            </intent-filter>
        </receiver>
```

<h3>3,发送自定义广播</h3>
1.发送标准广播
```java
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }

}

添加权限
```text
<receiver
            android:name=".MyBroadcastReceiver"
            android:enabled="true"
            android:exported="true">
            <intent-filter android:priority="100">
                <action android:name="com.example.broadcasttest.MY_BROADCAST"/>
            </intent-filter>
        </receiver>

```     
按钮启动
```java
Button button = (Button) findViewById(R.id.button);
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
              sendBroadcast(intent);
          }
      });   

<h3>2.发送有序广播</h3>
改一行代码
```java
sendOrderedBroadcast(intent,null);//这时广播接收器有先后顺序
```
如何定义先后顺序
```java
<receiver
           android:name=".MyBroadcastReceiver"
           android:enabled="true"
           android:exported="true">
           <intent-filter android:priority="100">//100优先级最高
               <action android:name="com.example.broadcasttest.MY_BROADCAST"/>
           </intent-filter>
```
是否允许继续传递
```java
ublic void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
        abortBroadcast();//表示截断
    }  
```

<h3>4.使用本地广播</h3>
```java
ublic class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;

    private LocalReceiver localReceiver;

    private LocalBroadcastManager localBroadcastManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localBroadcastManager = LocalBroadcastManager.getInstance(this); // 获取实例
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent); // 发送本地广播
            }
        });
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");//当网络发生变化  系统发出值/为android.net.conn.CONNECTIVITY_CHANGE的广播  要监听什么广播就加什么action
        localReceiver = new LocalReceiver();//新建NetworkChangeReceiver实例
        localBroadcastManager.registerReceiver(localReceiver, intentFilter); // 注册本地广播监听器
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }

    }
```

<h3>本地广播的优势</h3>
不用担心机密数据泄露
其他程序将广播发送到我们程序内部
比全局广播更高效

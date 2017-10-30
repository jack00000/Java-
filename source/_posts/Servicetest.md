---
title: servicetest<CODE 2 chaper10>
date: 2017-8-4 20:13:10
tags: CODE 2
---

<h3>学习目的：1.定义一个服务(服务都需要注册) 2.启动和停止活动 3.活动与服务进行通信 4.服务的生命周期 5.服务的更多技巧（前台服务，intentService）

1.定义一个服务New->Service->Service 以下自动生成的代码
```java
public class MyService extends Service {

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
```

重写Service中的方法
```java

@Override
   public void onCreate() {
       super.onCreate();

   }

   @Override
   public int onStartCommand(Intent intent, int flags, int startId) {

       return super.onStartCommand(intent, flags, startId);
   }

   @Override
   public void onDestroy() {
       super.onDestroy();

   }
```

注册自动完成
```java
<application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <service
            android:name=".MyService"
            android:enabled="true"
            android:exported="true" />
        <service android:name=".MyIntentService" />
    </application>
```    

2.启动和停止活动
```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startService = (Button) findViewById(R.id.start_service);
        Button stopService = (Button) findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent); // 启动服务
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent); // 停止服务
                break;

        }
    }

}
```

3.活动与服务进行通信（让活动具有下载功能，活动可以随时调用）
思路：创建Binder对象对下载功能进行管理
MyService
```java
ublic class MyService extends Service {

    public MyService() {
    }

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {

        public void startDownload() {
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }




}
```

活动与服务绑定
``java
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bindService = (Button) findViewById(R.id.bind_service);
        Button unbindService = (Button) findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE); // 绑定服务
                break;
            case R.id.unbind_service:
                unbindService(connection); // 解绑服务
                break;

            default:
                break;
        }
    }

}
```
4.服务的生命周期
```text
A started service

　　被开启的service通过其他组件调用 startService()被创建。

　　这种service可以无限地运行下去，必须调用stopSelf()方法或者其他组件调用stopService()方法来停止它。

　　当service被停止时，系统会销毁它。



A bound service

　　被绑定的service是当其他组件（一个客户）调用bindService()来创建的。

　　客户可以通过一个IBinder接口和service进行通信。

　　客户可以通过 unbindService()方法来关闭这种连接。

　　一个service可以同时和多个客户绑定，当多个客户都解除绑定之后，系统会销毁service。



　　这两条路径并不是完全分开的。

　　即是说，你可以和一个已经调用了 startService()而被开启的service进行绑定。

　　比如，一个后台音乐service可能因调用 startService()方法而被开启了，稍后，可能用户想要控制播放器或者得到一些当前歌曲的信息，可以通过bindService()将一个activity和service绑定。这种情况下，stopService()或 stopSelf()实际上并不能停止这个service，除非所有的客户都解除绑定。


```

5.服务的更多技巧（前台服务，intentService)
创建前台服务
```java
public class MyService extends Service {

    public MyService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate executed");
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);
    }

}
```
显示效果是一条通知，下拉有详细信息



使用intentService(需要用到android多线程)
```java
public class MyService extends Service {

    public MyService() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable(){
          @verride
          public void run(){
            //处理逻辑
          }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }
}
```
一旦启动就一直运行

必须调用stopService的stopSelf()方法才能让服务停止
```java
new Thread(new Runnable(){
  @verride
  public void run(){
    //处理逻辑
    stopSelf();
  }
}).start();
```

intentService解决程序员忘开线程  或忘stopSelf()
```java
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService"); // 调用父类的有参构造函数
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // 打印当前线程的id
        Log.d("MyIntentService", "Thread id is " + Thread.currentThread(). getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestroy executed");
    }

}
```

MainActivity
```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startIntentService = (Button) findViewById(R.id.start_intent_service);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.start_intent_service:
                // 打印主线程的id
                Log.d("MainActivity", "Thread id is " + Thread.currentThread(). getId());
                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);
                break;
            default:
                break;
        }
    }

}
```
注册
```java
 <service android:name=".MyIntentService" />
```

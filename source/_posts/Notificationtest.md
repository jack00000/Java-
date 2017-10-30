---
title: notificationtset<CODE 2 chaper8>
date: 2017-7-31 13:10
tags: CODE 2
---




<h3>学习目的:1.发通知（程序不在前台运行，但希望向用户发一些提示信息）</h3>
NotificationActivity类
```java
public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);

    }
}
```
点击事件
```java
public void onClick(View v) {
       switch (v.getId()) {
           case R.id.send_notice:

               NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
               Notification notification = new NotificationCompat.Builder(this)
                       .setContentTitle("This is content title")//指定标题内容
                       .setContentText("This is content text")//指定正文内容
                       .setWhen(System.currentTimeMillis())//指定通知被创建时间
                       .setSmallIcon(R.mipmap.ic_launcher)//设置通知的小图标
                       .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//设置通知的大图标

                       .setAutoCancel(true);//点击后 通知自动取消
                       .build();
               manager.notify(1, notification);//让通知显示
               break;
           default:
               break;
       }
   }
```

设置点击通知点击事件PendingIntent（点击通知调到NotificationAcitity.xml并点击后自动消失）
```java
public void onClick(View v) {
       switch (v.getId()) {
           case R.id.send_notice:
               Intent intent=new Intent(this,NotificationAcitity.class);
               PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
               NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
               Notification notification = new NotificationCompat.Builder(this)
                       .setContentTitle("This is content title")//指定标题内容
                       .setContentText("This is content text")//指定正文内容
                       .setWhen(System.currentTimeMillis())//指定通知被创建时间
                       .setSmallIcon(R.mipmap.ic_launcher)//设置通知的小图标
                       .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//设置通知的大图标
                       .setContentIntent(pi);
                       .setAutoCancel(true);//点击后 通知自动取消
                       .build();
               manager.notify(1, notification);//让通知显示
               break;
           default:
               break;
       }
   }
````

通知的进阶：通知提示音 灯光 /振动需要申请权限


```java
Notification notification = new NotificationCompat.Builder(this)
setSound(Uri.fromFile(new File("/System/media/audio/ringtone/Luna.ogg")))
.bulid();
```
振动
```java
Notification notification = new NotificationCompat.Builder(this)
setVibrate(new log[]{0,1000,1000,1000})震动1秒 静止1秒
.bulid();

<uses-permission android:name="android.permission.VIBRATE" />
```

灯光
```java
Notification notification = new NotificationCompat.Builder(this)
setLight(Color.GREEN,1000,1000);
.bulid();
```

直接使用默认设置
```java
Notification notification = new NotificationCompat.Builder(this)
setDefaults(NotificationCompat.DEFAULT_ALL)
.bulid();

```


通知的高级功能 解决超长文字显示不僚的问题  显示超大图片 setStyle()方法
```java
Notification notification = new NotificationCompat.Builder(this)
.setStyle(new NotificationCompat.BigTextStyle().bigText("fbdvdsjfekfjlsfjskfbjsefbsejfjsjdkfbsdbsdubseufhfusefhuiegeiefjselgbsuohsufbsudfbusfseffusfus"))
.bulid();
```
显示超大图片
```java
Notification notification = new NotificationCompat.Builder(this)
.setStyle(new NotificationCompat.BigPictureStyle.BigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.big_image)));
.bulid();
```

设置通知的重要程度setPriority()
```java
Notification notification = new NotificationCompat.Builder(this)
setPriority(NotificationCompat.PRIORITY_MAX)//最高权限
.bulid();
```

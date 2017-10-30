---
title: coolweather6<CODE 2 chaper14>
date: 2017-8-17 20:13:10
tags: CODE 2
---


<h3>学习目的：1.获取必应每日一图

1.获取必应每日一图

每日一图接口：http://guolin.tech/api/bing_pic

服务器返回http://cn.bing.com/az/hprichbg/rb/AlaskaLynx_ROW9337883641_1920x1080.jpg每日一图背景

使用Glide加载即可

修改activity_weather
```xml
<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/colorPrimary">

    <ImageView
        android:id="@+id/bing_pic_img"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scaleType="centerCrop" />


      //...3qe
</FrameLayout>

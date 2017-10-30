---
title: coolweather4<CODE 2 chaper14>
date: 2017-8-15 20:13:10
tags: CODE 2
---

<h3>学习目的:1.显示天气信息 2.编写天气界面

1.显示天气信息（由于和风天气返回的json数据很复杂，用JSONObject解析很麻烦，这里借助Gson解析）
回顾返回数据的大致格式
```json

{
  "HeWeather":[
    {
      "status":"ok",
      "basic":{},
      "api":{},
      "suggestion":{},
      "daily_forecast":[]
    }
  ]
}
```

可以将status api等5个部分内容定义为5个实体类
例：“basic”;{
  "city":"苏州"，
  "id":"CN101190401",
  "update":{
    "loc":"2016-08-08 21:58"
  }
}



```
按照此结构早GSON下建一个Basic类
```java
import com.google.gson.annotations.SerializedName;

public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {

        @SerializedName("loc")
        public String updateTime;

    }

}
```
类推
```java
public class AQI {

    public AQICity city;

    public class AQICity {

        public String aqi;

        public String pm25;

    }

}
```

```java
import com.google.gson.annotations.SerializedName;

public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {

        @SerializedName("txt")
        public String info;

    }

}
```

```java
import com.google.gson.annotations.SerializedName;

public class Suggestion {

    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public CarWash carWash;

    public Sport sport;

    public class Comfort {

        @SerializedName("txt")
        public String info;

    }

    public class CarWash {

        @SerializedName("txt")
        public String info;

    }

    public class Sport {

        @SerializedName("txt")
        public String info;

    }

}
```

```java
import com.google.gson.annotations.SerializedName;

public class Forecast {

    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public class Temperature {

        public String max;

        public String min;

    }

    public class More {

        @SerializedName("txt_d")
        public String info;

    }

}
```

新建Weather类对五个类进行引用
```java
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    public String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;

}
```


2.编写天气界面

新建WeatherActivity 并指定布局activity_weather.xml

新建title.xml作为头布局
```xml
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize">

    <Button
        android:id="@+id/nav_button"
        android:layout_width="30dp"
        android:layout_height="30dp"
        android:layout_marginLeft="10dp"
        android:layout_alignParentLeft="true"
        android:layout_centerVertical="true"
        android:background="@drawable/ic_home" />

    <TextView
        android:id="@+id/title_city"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:textColor="#fff"
        android:textSize="20sp" />

    <TextView
        android:id="@+id/title_update_time"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginRight="10dp"
        android:layout_alignParentRight="true"
        android:layout_centerVertical="true"
        android:textColor="#fff"
        android:textSize="16sp"/>

</RelativeLayout>
```
![Markdown](http://i1.bvimg.com/595109/f5d167e46bab9112.png)
居中更新城市名 居右更新时间

新建now.xml作为当前天气信息布局
```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp">

    <TextView
        android:id="@+id/degree_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="end"
        android:textColor="#fff"
        android:textSize="60sp" />

    <TextView
        android:id="@+id/weather_info_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="end"
        android:textColor="#fff"
        android:textSize="20sp" />

</LinearLayout>
```
![Markdown](http://i1.bvimg.com/595109/32ab6d67156e8e5a.png)
两个TextView 一个显示气温 一个显示天气概况

新建forecast.xml作为未来几天天气布局
```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp"
    android:background="#8000">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="15dp"
        android:layout_marginTop="15dp"
        android:text="预报"
        android:textColor="#fff"
        android:textSize="20sp"/>

    <LinearLayout
        android:id="@+id/forecast_layout"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
    </LinearLayout>

</LinearLayout>
```
![Markdown](http://i1.bvimg.com/595109/d04a58ff05532099.png)
LinearLayout做一个半透明背景 TextView标题   LinearLayout显示未来几天天气布局


定义未来几天天气子布局forecast_item.xml
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp">

    <TextView
        android:id="@+id/date_text"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_gravity="center_vertical"
        android:layout_weight="2"
        android:textColor="#fff"/>

    <TextView
        android:id="@+id/info_text"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_gravity="center_vertical"
        android:layout_weight="1"
        android:gravity="center"
        android:textColor="#fff"/>

    <TextView
        android:id="@+id/max_text"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_weight="1"
        android:gravity="right"
        android:textColor="#fff"/>

    <TextView
        android:id="@+id/min_text"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_weight="1"
        android:gravity="right"
        android:textColor="#fff"/>

</LinearLayout>
```
![Markdown](http://i4.bvimg.com/595109/389102db579be106.png)

四个TextView  天气预报日期 天气概况 最高温度 最低温度

新建aqi.xml作为空气质量信息布局
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp"
    android:background="#8000">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="15dp"
        android:layout_marginTop="15dp"
        android:text="空气质量"
        android:textColor="#fff"
        android:textSize="20sp"/>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="15dp">

        <RelativeLayout
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1">

            <LinearLayout
                android:orientation="vertical"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_centerInParent="true">

                <TextView
                    android:id="@+id/aqi_text"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center"
                    android:textColor="#fff"
                    android:textSize="40sp"
                    />

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center"
                    android:text="AQI指数"
                    android:textColor="#fff"/>

            </LinearLayout>

        </RelativeLayout>

        <RelativeLayout
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1">

            <LinearLayout
                android:orientation="vertical"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_centerInParent="true">

                <TextView
                    android:id="@+id/pm25_text"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center"
                    android:textColor="#fff"
                    android:textSize="40sp"
                    />

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center"
                    android:text="PM2.5指数"
                    android:textColor="#fff"
                    />

            </LinearLayout>

        </RelativeLayout>

    </LinearLayout>

</LinearLayout>
```
![Markdown](http://i4.bvimg.com/595109/e29d8117bcde0369.png)
LinearLayout半透明背景 TextView定义标题  RelativeLayout左右平分居中对齐 用于显示AQI和PM2.5


新建suggestion.xml作为生活建议信息的布局
```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="15dp"
    android:background="#8000">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="15dp"
        android:layout_marginTop="15dp"
        android:text="生活建议"
        android:textColor="#fff"
        android:textSize="20sp"/>

    <TextView
        android:id="@+id/comfort_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="15dp"
        android:textColor="#fff" />

    <TextView
        android:id="@+id/car_wash_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="15dp"
        android:textColor="#fff" />

    <TextView
        android:id="@+id/sport_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="15dp"
        android:textColor="#fff" />

</LinearLayout>
```
![Markdown](http://i4.bvimg.com/595109/6087767c7f3bb787.png)
LinearLayout半透明背景 TextView定义标题 其他三个TextView显示舒适度  洗车指数 运动建议相关数据

将每个部分布局引入到activity_weather.xml
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

    <android.support.v4.widget.DrawerLayout
        android:id="@+id/drawer_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <android.support.v4.widget.SwipeRefreshLayout
            android:id="@+id/swipe_refresh"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <ScrollView
                android:id="@+id/weather_layout"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:scrollbars="none"
                android:overScrollMode="never">

                <LinearLayout
                    android:orientation="vertical"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:fitsSystemWindows="true">

                    <include layout="@layout/title" />

                    <include layout="@layout/now" />

                    <include layout="@layout/forecast" />

                    <include layout="@layout/aqi" />

                    <include layout="@layout/suggestion" />

                </LinearLayout>

            </ScrollView>

        </android.support.v4.widget.SwipeRefreshLayout>

        <fragment
            android:id="@+id/choose_area_fragment"
            android:name="com.coolweather.android.ChooseAreaFragment"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            />

    </android.support.v4.widget.DrawerLayout>

</FrameLayout>
```
![Markdown](http://i4.bvimg.com/595109/afd70ca5719fb493.png)
ScrollViewn允许滚动

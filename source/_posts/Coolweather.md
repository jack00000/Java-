---
title: coolweather<CODE 2 chaper14>
date: 2017-8-13 20:13:10
tags: CODE 2
---

<h3>学习目的：1.功能需求和可行性分析  2.加入创建数据库和表的各项配置

1.功能需求和可行性分析
- 可以罗列出全国的省 市 县
- 可以查看全国任意城市的天气信息
- 可以自由切换城市，去看其他城市天气
- 提供手动更新和后台自动更新天气功能

![Markdown](http://i1.bvimg.com/595109/732fdb2742f73161.png)
![Markdown](http://i1.bvimg.com/595109/b88744037984e0b2.png)
有API key（key=bf79cd24a8494d0eb2c7c949fce6c383）和weather id（cityid=CN101190401）就能获取任意天气信息

如：查看苏州天气：
http://guolin.tech/api/weather?cityid=CN101190401&key=bf79cd24a8494d0eb2c7c949fce6c383

信息以json格式返回
![Markdown](http://i1.bvimg.com/595109/13075fc3de9f820d.png)

提取下重要信息
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


2.创建数据库和表
![Markdown](http://i4.bvimg.com/595109/21d805f460c6c2d2.png)

添加依赖
```java
compile 'org.litepal.android:core:1.3.2'
  compile 'com.squareup.okhttp3:okhttp:3.4.1'
  compile 'com.google.code.gson:gson:2.7'
```

在DB包下新建Province类 City类 Country类
```java
import org.litepal.crud.DataSupport;

public class Province extends DataSupport {

    private int id;

    private String provinceName;

    private int provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
```

```java
mport org.litepal.crud.DataSupport;

public class County extends DataSupport {

    private int id;

    private String countyName;

    private String weatherId;

    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

}
```

```java
import org.litepal.crud.DataSupport;

public class City extends DataSupport {

    private int id;

    private String cityName;

    private int cityCode;

    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

}
```

配置litepal.xml文件 将实体类添加到映射表中
app/src/main->New->Directory->assets->litepal.xml
```xml
litepal>

    <dbname value="cool_weather" />

    <version value="1" />

    <list>
        <mapping class="com.coolweather.android.db.Province" />
        <mapping class="com.coolweather.android.db.City" />
        <mapping class="com.coolweather.android.db.County" />
    </list>

</litepal>
```

还需配置LitePalApplication 修改AndroidManifest.xml
```xml
<application
        android:name="org.litepal.LitePalApplication"
```


数据库配置完成！        

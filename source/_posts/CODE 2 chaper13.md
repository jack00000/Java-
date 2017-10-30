---
title: 高级技巧<CODE 2 chaper13>
date: 2017-8-15 20:13:10
tags: CODE 2
---

<h3>学习目的：1.全局获取Context技巧（活动本身就是一个context对象）2. 使用INtent传递对象（serializable与Parcclablc方式）
创建MyApplication类继承Application
```java
public clss MyApplication extends Application{
  private static Context context;
  public void onCreate(){
    context=getApplicationContext();
  }
  public static Context getContext(){
    return context;
  }
}
```

修改AndroidMaifest.xml
```xml
<application
        android:name="com.example.networktest.MyApplication"//添加一行就行   路径一定要正确
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
```

使用INtent传递对象（serializable与Parcclablc方式）

serializable 序列化（方法：让类实现serializable接口）
如：定义person类
```java
public class person implements Serializable{
  private String name;
  private int age;
  public String getName(){
    return name;
  }
  public void setName(){
    this.name=name;
  }
  public int getAge(){
    return age;
  }
  public void setAge(){
    this.age=age;
  }
}
```

FristActivity
```java
Person person=new person();
person.setName();
person.setAge();
Intent intent=new Intent(FristActivity.this,SecondActivity.class);
intent.putExtra(Person_data,person);
startActivity(intent);
```

在SecondActivity获取这个对象
```java
Person person=(Person)getIntent().getSerializableExtra("Person_data");

```


Parcclablc方式 (将一个完整的对象进行分解)

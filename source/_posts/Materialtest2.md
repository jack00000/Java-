---
title: materialtest2(最佳UI体验)<CODE 2 chaper12>
date: 2017-8-9 20:13:10
tags: CODE 2
---

<h3>学习目的：1.NavigationView  2.nav_head.xml(放置头像 邮箱 用户名)  3.使用NavigationView

1.NavigationView

添加依赖
```java
compile 'com.android.support:design:24.2.1'//Design supprot
    compile 'de.hdodenhof:circleimageview:2.1.0'//开源项目  轻松实现图片圆形化
```    

在menu下创建nav_menu.xml
```java
?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <group android:checkableBehavior="single">
        <item
            android:id="@+id/nav_call"
            android:icon="@drawable/nav_call"
            android:title="Call" />
        <item
            android:id="@+id/nav_friends"
            android:icon="@drawable/nav_friends"
            android:title="Friends" />
        <item
            android:id="@+id/nav_location"
            android:icon="@drawable/nav_location"
            android:title="Location" />
        <item
            android:id="@+id/nav_mail"
            android:icon="@drawable/nav_mail"
            android:title="Mail" />
        <item
            android:id="@+id/nav_task"
            android:icon="@drawable/nav_task"
            android:title="Tasks" />
    </group>
</menu>
```
![Markdown](http://i1.bvimg.com/595109/29a53cd5eab026d1.png)

3.nav_head.xml(放置头像 邮箱 用户名)
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="180dp"
    android:background="?attr/colorPrimary"
    android:padding="10dp">

    <de.hdodenhof.circleimageview.CircleImageView//图片圆形化控件
        android:id="@+id/icon_image"
        android:layout_width="70dp"
        android:layout_height="70dp"
        android:layout_centerInParent="true"
        android:src="@drawable/nav_icon" />

    <TextView
        android:id="@+id/username"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:text="tonygreendev@gmail.com"
        android:textColor="#FFF"
        android:textSize="14sp" />

    <TextView
        android:id="@+id/mail"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_above="@id/username"
        android:text="Tony Green"
        android:textColor="#FFF"
        android:textSize="14sp" />

</RelativeLayout>
```
![Markdown](http://i1.bvimg.com/595109/aaf19dc895ab43bc.png)


使用NavigationView
activity_maind的TextView替换成
```java
<android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/nav_header"
        app:menu="@menu/nav_menu" />
```
设置点击事件
```java
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_call);
         navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(MenuItem item) {
                 mDrawerLayout.closeDrawers();
                 return true;
             }
         });
    }
```

---
title: materialtest(最佳UI体验)<CODE 2 chaper12>
date: 2017-8-8 20:13:10
tags: CODE 2
---

<h3>学习目的：1.什么是Materrial Design  2.Toolbar与Actionbar（已淘汰）  (Materrial控件)  3.app命名空间</h3>

1.什么是Materrial Design:Google推出的全新界面语言

2.Toolbar与Actionbar（已淘汰）  (Materrial控件)
```java
android:theme="@style/AppTheme">
```

res/values/styles.xml
```java
<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">//默认使用Actionbar
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
```    
禁用Actionbar
```java
name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar
```

用Toolbar替代Actionbar
```java
<android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:background="?attr/colorPrimary"
                android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
                app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
                app:layout_scrollFlags="scroll|enterAlways|snap" />

```
![Markdown](http://i4.bvimg.com/595109/97171dbb977c9a04.png)

3.创建mene文件夹 新建toolbar.xml
```java
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/backup"
        android:icon="@drawable/ic_backup"
        android:title="Backup"
        app:showAsAction="always"/>
    <item
        android:id="@+id/delete"
        android:icon="@drawable/ic_delete"
        android:title="Delete"
        app:showAsAction="ifRoom"/>
    <item
        android:id="@+id/settings"
        android:icon="@drawable/ic_settings"
        android:title="Settings"
        app:showAsAction="never"/>
</menu>
```
![Markdown](http://i4.bvimg.com/595109/831131cba95e2fba.png)
app:showAsAction指定显示位置  never永远在菜单栏   ifRoom屏幕空间足够则显示在Toolbar中 always永远显示在Toolbar中

4.滑动菜单
DrawerLayout(一种布局：能放两个直接子控件  一个子控件显示主屏幕内容  另一个子控件是滑动菜单显示内容)
activity_main
```java
<android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">


            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:background="?attr/colorPrimary"
                android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
                app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
                app:layout_scrollFlags="scroll|enterAlways|snap" />
      </FrameLayout>

      <TextView
             android:layout_width="match_parent"
             android:layout_height="match_parent"
             android:layout_gravity="start"
             android:text="This is name"
             android:testSize="30sp"
             android:background="#FFF"/>
</android.support.v4.widget.DrawerLayout>
```
![Markdown](http://i1.bvimg.com/595109/9f81ff22678cbc9e.png)

防止用户不知道左边可以滑动（设置导航栏）
MainActivity
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

    }
//...
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            //...
            default:
        }
        return true;
    }

}
```

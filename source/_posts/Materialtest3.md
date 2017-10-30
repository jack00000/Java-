---
title: materialtest3(最佳UI体验)<CODE 2 chaper11>
date: 2017-8-10 20:13:10
tags: CODE 2
---


<h3>学习目的：1.悬浮按钮（FloatingAcgtionButton有投影）和可交互提示(Snackbar更先进的提示工具)

FloatingAcgtionButton(Design support的一个控件)

```xml
<android.support.design.widget.FloatingActionButton
            android:id="@+id/fab"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="bottom|end"
            android:layout_margin="16dp"
            android:src="@drawable/ic_done"
            app：elevation="8dp" />//指定悬浮高度 高度越大投影效果小
```

![Markdown](http://i4.bvimg.com/595109/64a2bf8fb9d555c8.png)


FloatingActionButton的点击事件
```java
FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                                Toast.makeText(MainActivity.this, "Data restored", Toast.LENGTH_SHORT).show();


            }
        });
```        




Snackbar更先进的提示工具（Design support）(用户接到提示可以进行（按钮形式）逻辑操作  toast不行)
```java
FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Data restored", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
```        

如此提示会遮挡FloatingActionButton

解决方法Coordinatorlayout(将FrameLayout替换成Coordinatorlayout
activity_main
```xml
<android.support.design.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <android.support.design.widget.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:background="?attr/colorPrimary"
                android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
                app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
                app:layout_scrollFlags="scroll|enterAlways|snap" />
        </android.support.design.widget.AppBarLayout>

        <android.support.v4.widget.SwipeRefreshLayout
            android:id="@+id/swipe_refresh"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior">

            <android.support.v7.widget.RecyclerView
                android:id="@+id/recycler_view"
                android:layout_width="match_parent"
                android:layout_height="match_parent" />
        </android.support.v4.widget.SwipeRefreshLayout>

        <android.support.design.widget.FloatingActionButton
            android:id="@+id/fab"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="bottom|end"
            android:layout_margin="16dp"
            android:src="@drawable/ic_done" />
    </android.support.design.widget.CoordinatorLayout>
```

---
title: materialtest5(最佳UI体验)<CODE 2 chaper12>
date: 2017-8-11 20:13:10
tags: CODE 2
---

<h3>学习目的：3.可折叠的标题栏(界面编写)

3.可折叠的标题栏（借助CollapsingToolbarLayout基于Toolbar的布局）

CollapsingToolbarLayout被限定作为AppBarLayout的子布局来使用

首先实现标题栏
```xml
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"//定义命名空间
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true">


</android.support.design.widget.CoordinatorLayout>
```

套入  AppBarLayout
```java
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/appBar"
        android:layout_width="match_parent"
        android:layout_height="250dp"
        android:fitsSystemWindows="true">
         </android.support.design.widget.AppBarLayout>

        </android.support.design.widget.CoordinatorLayout>
```
再套入CollapsingToolbarLayout
```xml
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/appBar"
        android:layout_width="match_parent"
        android:layout_height="250dp"
        android:fitsSystemWindows="true">

        <android.support.design.widget.CollapsingToolbarLayout
            android:id="@+id/collapsing_toolbar"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
            android:fitsSystemWindows="true"
            app:contentScrim="?attr/colorPrimary"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">

             </android.support.design.widget.CollapsingToolbarLayout>
          </android.support.design.widget.AppBarLayout>

         </android.support.design.widget.CoordinatorLayout>
```

在CollapsingToolbarLayout定义标题栏的内容
```xml
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/appBar"
        android:layout_width="match_parent"
        android:layout_height="250dp"
        android:fitsSystemWindows="true">

        <android.support.design.widget.CollapsingToolbarLayout
            android:id="@+id/collapsing_toolbar"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
            android:fitsSystemWindows="true"
            app:contentScrim="?attr/colorPrimary"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">

            <ImageView
               android:id="@+id/fruit_image_view"
               android:layout_width="match_parent"
               android:layout_height="match_parent"
               android:scaleType="centerCrop"//指定折叠模式
               android:fitsSystemWindows="true"
               app:layout_collapseMode="parallax" />//在折叠过程中产生错位偏移

           <android.support.v7.widget.Toolbar
               android:id="@+id/toolbar"
               android:layout_width="match_parent"
               android:layout_height="?attr/actionBarSize"
               app:layout_collapseMode="pin" />

             </android.support.design.widget.CollapsingToolbarLayout>
          </android.support.design.widget.AppBarLayout>

         </android.support.design.widget.CoordinatorLayout>     
```

水果的标题栏界面完成 编写水果内容详情(NestedScrollView滚动)只能放一个字布局
activity_fruit.xml
```xml

<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/appBar"
        android:layout_width="match_parent"
        android:layout_height="250dp"
        android:fitsSystemWindows="true">
        </android.support.design.widget.AppBarLayout>



        <android.support.v4.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">
        </android.support.v4.widget.NestedScrollView>

        </android.support.design.widget.CoordinatorLayout>
```

套入布局
```xml
<android.support.v4.widget.NestedScrollView
android:layout_width="match_parent"
android:layout_height="match_parent"
app:layout_behavior="@string/appbar_scrolling_view_behavior">

<LinearLayout
           android:orientation="vertical"
           android:layout_width="match_parent"
           android:layout_height="wrap_content">

           <android.support.v7.widget.CardView
               android:layout_width="match_parent"
               android:layout_height="wrap_content"
               android:layout_marginBottom="15dp"
               android:layout_marginLeft="15dp"
               android:layout_marginRight="15dp"
               android:layout_marginTop="35dp"
               app:cardCornerRadius="4dp">

               <TextView
                   android:id="@+id/fruit_content_text"
                   android:layout_width="match_parent"
                   android:layout_height="wrap_content"
                   android:layout_margin="10dp" />
           </android.support.v7.widget.CardView>
       </LinearLayout>
</android.support.v4.widget.NestedScrollView>
```

添加悬浮按钮
```xml
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true">

    <android.support.design.widget.FloatingActionButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="16dp"
            android:src="@drawable/ic_comment"
            app:layout_anchor="@id/appBar"
            app:layout_anchorGravity="bottom|end" />  
            </android.support.v4.widget.NestedScrollView>
```                

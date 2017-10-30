---
title: eclipse开发计算器
date: 2017-3-20 20:13:10
tags:
---
<h3>eclipse目录结构三大部分</h3>
- AndroidManifest.xml
- layout(res资源的一部分)
- src

<h3>AndroidManifest.xml</h3>
```java
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.jisuanqil"
    android:versionCode="1"
    android:versionName="1.0" >

    <uses-sdk
        android:minSdkVersion="8"
        android:targetSdkVersion="19" />

    <application
        android:allowBackup="true"
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/AppTheme" >
        <activity
            android:name="com.example.jisuanqil.MainActivit"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```
<h3>layout(res资源的一部分)</h3>
```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="#000000" >

  <EditText
        android:layout_width="fill_parent"
        android:layout_height="60dip"
        android:background="#FFFFFF"
        android:editable="false"
        android:id="@+id/et_showview"
        android:gravity="bottom|right"
        android:textSize="20sp" />

   <LinearLayout
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:layout_marginTop="20dip"
        android:gravity="center_horizontal"
        android:orientation="vertical" >

        <LinearLayout
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:gravity="center_horizontal"
            android:orientation="horizontal" >

            <Button
                android:layout_width="60dp"
                android:layout_height="60dp"
                android:gravity="bottom|right"
                android:paddingBottom="10dp"
                android:paddingRight="10dp"
                android:textSize="20sp"
                android:id="@+id/btn_clear"
                android:background="#FFFFFF"
                android:text="C" />

            <Button
                android:layout_width="60dp"
                android:layout_height="60dp"
                android:layout_marginLeft="10dip"
                android:gravity="bottom|right"
                android:paddingBottom="10dp"
                android:paddingRight="10dp"
                android:textSize="20sp"
                android:id="@+id/btn_del"
                android:background="#FFFFFF"
                android:text="DEL" />

            <Button
                android:layout_width="60dp"
                android:layout_height="60dp"
                android:layout_marginLeft="10dip"
                android:gravity="bottom|right"
                android:paddingBottom="10dp"
                android:paddingRight="10dp"
                android:textSize="20sp"
                android:id="@+id/btn_divide"
                android:background="#FFFFFF"
                android:text="÷" />

            <Button
                android:layout_width="60dp"
                android:layout_height="60dp"
                android:layout_marginLeft="10dip"
                android:gravity="bottom|right"
                android:paddingBottom="10dp"
                android:paddingRight="10dp"
                android:textSize="20sp"
                 android:id="@+id/btn_multiply"
                android:background="#FFFFFF"
                android:text="×" />
        </LinearLayout>


        <LinearLayout
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="10dip"
            android:gravity="center_horizontal"
            android:orientation="horizontal" >

            <Button
                android:layout_width="60dp"
                android:layout_height="60dp"
                android:gravity="bottom|right"
                android:paddingBottom="10dp"
                android:paddingRight="10dp"
                android:textSize="20sp"
                 android:id="@+id/btn_7"
                android:background="#FFFFFF"
                android:text="7" />

            <Button
                android:layout_width="60dp"
                android:layout_height="60dp"
                android:layout_marginLeft="10dip"
                android:gravity="bottom|right"
                android:paddingBottom="10dp"
                android:paddingRight="10dp"
                android:textSize="20sp"
                 android:id="@+id/btn_8"
                android:background="#FFFFFF"
                android:text="8" />

            <Button
                android:layout_width="60dp"
                android:layout_height="60dp"
                android:layout_marginLeft="10dip"
                android:gravity="bottom|right"
                android:paddingBottom="10dp"
                android:paddingRight="10dp"
                android:textSize="20sp"
                android:id="@+id/btn_9"
                android:background="#FFFFFF"
                android:text="9" />

            <Button
                android:layout_width="60dp"
                android:layout_height="60dp"
                android:layout_marginLeft="10dip"
                android:gravity="bottom|right"
                android:paddingBottom="10dp"
                android:paddingRight="10dp"
                android:textSize="20sp"
                android:id="@+id/btn_minus"
                android:background="#FFFFFF"
                android:text="－" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="10dip"
            android:gravity="center_horizontal"
            android:orientation="horizontal" >

            <Button
                android:layout_width="60dp"
                android:layout_height="60dp"
                android:gravity="bottom|right"
                android:paddingBottom="10dp"
                android:paddingRight="10dp"
                android:textSize="20sp"
                android:id="@+id/btn_4"
                android:background="#FFFFFF"
                android:text="4" />

            <Button
                android:layout_width="60dp"
                android:layout_height="60dp"
                android:layout_marginLeft="10dip"
                android:gravity="bottom|right"
                android:paddingBottom="10dp"
                android:paddingRight="10dp"
                android:textSize="20sp"
                android:id="@+id/btn_5"
                android:background="#FFFFFF"
                android:text="5" />

            <Button
                android:layout_width="60dp"
                android:layout_height="60dp"
                android:layout_marginLeft="10dip"
                android:gravity="bottom|right"
                android:paddingBottom="10dp"
                android:paddingRight="10dp"
                android:textSize="20sp"
                 android:id="@+id/btn_6"
                android:background="#FFFFFF"
                android:text="6" />

            <Button
                android:layout_width="60dp"
                android:layout_height="60dp"
                android:layout_marginLeft="10dip"
                android:gravity="bottom|right"
                android:paddingBottom="10dp"
                android:paddingRight="10dp"
                android:textSize="20sp"
                android:id="@+id/btn_pluse"
                android:background="#FFFFFF"
                android:text="＋" />
        </LinearLayout>

         <LinearLayout
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="10dip"
            android:gravity="center_horizontal"
            android:orientation="horizontal" >

            <LinearLayout
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:orientation="vertical" >

                <LinearLayout
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal" >

                    <Button
                        android:layout_width="60dp"
                        android:layout_height="60dp"
                        android:gravity="bottom|right"
                        android:paddingBottom="10dp"
                        android:paddingRight="10dp"
                        android:textSize="20sp"
                        android:id="@+id/btn_1"
                        android:background="#FFFFFF"
                        android:text="1" />

                    <Button
                        android:layout_width="60dp"
                        android:layout_height="60dp"
                        android:layout_marginLeft="10dip"
                        android:gravity="bottom|right"
                        android:paddingBottom="10dp"
                        android:paddingRight="10dp"
                        android:textSize="20sp"
                        android:id="@+id/btn_2"
                        android:background="#FFFFFF"
                        android:text="2" />

                    <Button
                        android:layout_width="60dp"
                        android:layout_height="60dp"
                        android:layout_marginLeft="10dip"
                        android:gravity="bottom|right"
                        android:paddingBottom="10dp"
                        android:paddingRight="10dp"
                        android:textSize="20sp"
                        android:id="@+id/btn_3"
                        android:background="#FFFFFF"
                        android:text="3" />
                </LinearLayout>

                <LinearLayout
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="10dip"
                    android:orientation="horizontal" >

                    <Button
                        android:layout_width="130dp"
                        android:layout_height="60dp"
                        android:gravity="bottom|right"
                        android:paddingBottom="10dp"
                        android:paddingRight="10dp"
                        android:textSize="20sp"
                        android:id="@+id/btn_0"
                        android:background="#FFFFFF"
                        android:text="0" />

                    <Button
                        android:layout_width="60dp"
                        android:layout_height="60dp"
                        android:layout_marginLeft="10dip"
                        android:gravity="bottom|right"
                        android:paddingBottom="10dp"
                        android:paddingRight="10dp"
                        android:text="."
                        android:id="@+id/btn_point"
                        android:background="#FFFFFF"
                        android:textSize="20sp"/>
                </LinearLayout>
            </LinearLayout>

            <Button
                android:layout_width="60dip"
                android:layout_height="130dip"
                android:layout_marginLeft="10dip"
                android:gravity="bottom|right"
                android:paddingBottom="10dp"
                android:paddingRight="10dp"
                android:textSize="20sp"
                android:id="@+id/btn_equal"
                android:background="#FFFFFF"
                android:text="=" />
        </LinearLayout>


    </LinearLayout>


</LinearLayout>
```

<h3>src</h3>
```java
package com.example.jisuanqil;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	Button btn_0;
	Button btn_1;
	Button btn_2;
	Button btn_3;
	Button btn_4;
	Button btn_5;
	Button btn_6;
	Button btn_7;
	Button btn_8;
	Button btn_9;

	Button btn_point;// 小数点
	Button btn_divide;// 除以
	Button btn_multiply;// 乘以
	Button btn_minus;// 减去
	Button btn_pluse;// 加
	Button btn_equal;// 等于

	Button btn_clear;
	Button btn_del;

	EditText et_showview;
	boolean needclear;

	protected void onCreat(Bundle saveInstenceState){
		super.onCreate(saveInstenceState);
		setContentView(R.layout.activity);
		btn_0 = (Button) findViewById(R.id.btn_0);
		btn_1 = (Button) findViewById(R.id.btn_1);
		btn_2 = (Button) findViewById(R.id.btn_2);
		btn_3 = (Button) findViewById(R.id.btn_3);
		btn_4 = (Button) findViewById(R.id.btn_4);
		btn_5 = (Button) findViewById(R.id.btn_5);
		btn_6 = (Button) findViewById(R.id.btn_6);
		btn_7 = (Button) findViewById(R.id.btn_7);
		btn_8 = (Button) findViewById(R.id.btn_8);
		btn_9 = (Button) findViewById(R.id.btn_9);
		btn_point = (Button) findViewById(R.id.btn_point);// 小数点
		btn_divide = (Button) findViewById(R.id.btn_divide);// 除以
		btn_multiply = (Button) findViewById(R.id.btn_multiply);// 乘以
		btn_minus = (Button) findViewById(R.id.btn_minus);// 减去
		btn_pluse = (Button) findViewById(R.id.btn_pluse);// 加
		btn_equal = (Button) findViewById(R.id.btn_equal);// 等于

		btn_clear = (Button) findViewById(R.id.btn_clear);
		btn_del = (Button) findViewById(R.id.btn_del);
		et_showview = (EditText) findViewById(R.id.et_showview);

		btn_0.setOnClickListener(this);
		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_3.setOnClickListener(this);
		btn_4.setOnClickListener(this);
		btn_5.setOnClickListener(this);
		btn_6.setOnClickListener(this);
		btn_7.setOnClickListener(this);
		btn_8.setOnClickListener(this);
		btn_9.setOnClickListener(this);

		btn_point.setOnClickListener(this);
		btn_divide.setOnClickListener(this);
		btn_multiply.setOnClickListener(this);
		btn_minus.setOnClickListener(this);
		btn_pluse.setOnClickListener(this);
		btn_equal.setOnClickListener(this);

		btn_clear.setOnClickListener(this);
		btn_del.setOnClickListener(this);


	}

	public void onClick(View v){

		String str = et_showview.getText().toString();
		switch (v.getId()) {
		case R.id.btn_0:
		case R.id.btn_1:
		case R.id.btn_2:
		case R.id.btn_3:
		case R.id.btn_4:
		case R.id.btn_5:
		case R.id.btn_6:
		case R.id.btn_7:
		case R.id.btn_8:
		case R.id.btn_9:
		case R.id.btn_point:
			if(needclear){
				str = "";
				et_showview.setText("");
			}
			et_showview.setText(str + ((Button) v).getText());
			break;
		case R.id.btn_pluse:
		case R.id.btn_minus:
		case R.id.btn_multiply:
		case R.id.btn_divide:
			if(needclear){
				et_showview.setText("");
			}
			et_showview.setText(str +" "+((Button) v).getText()+" ");
			break;
		case R.id.btn_equal:
			getResult();
			break;
		case R.id.btn_del:
			if (str != null && !str.equals("")) {
				et_showview.setText(str.substring(0, str.length() - 1));
			}
			break;
		case R.id.btn_clear:
			et_showview.setText("");
			break;
		}



	}

	public void getResult(){



		needclear = true;
		String exp = et_showview.getText().toString();
		double r = 0;
	    int space = exp.indexOf(' ');//用于搜索空格位置
        String s1 = exp.substring(0, space);//s1用于保存第一个运算数
        String op = exp.substring(space + 1, space + 2);//op用于保存运算符
        String s2 = exp.substring(space + 3);//s2用于保存第二个运算数
        double arg1 = Double.parseDouble(s1);//将运算数从string转换为Single
        double arg2 = Double.parseDouble(s2);
        if(op.equals("＋")){
        	 r = arg1 + arg2;
        }else if(op.equals("－")){
        	r = arg1 - arg2;
        }else if(op.equals("×")){
        	 r = arg1 * arg2;
        }else if(op.equals("÷")){
        	 if (arg2 == 0)
             {
                r=0;
             }
             else
             {
                 r = arg1 / arg2;
             }
        }       
        if(!s1.contains(".")&&!s2.contains(".")){
        	int result = (int)r;
        	et_showview.setText(result+"");
        }else{
        	et_showview.setText(r+"");
        }
	}

	}

```

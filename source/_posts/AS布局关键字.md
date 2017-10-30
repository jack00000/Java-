---
title: AS关键字
date: 2017-9-16 20:13:10
tags:
---

计算器思路：1.布局2.声明变量 找到控件地址 设置监听器 处理逻辑


- match_parent 是指“填充满”父容器

- wrap_content 根据容器内的东西决定组件的大小
- android:gravity="center"
- bottom|right

- orientation
- vertical
- Bottom
- android:gravity="center_vertical"  这个的意思是指 限定它里面的内容要垂直居中显示。
- android:layout_gravity="center_vertical"，这个是指它的位置是相对于它父亲的垂直居中。
- substring(a,b)  从一串字符中挑出第a个到第b个
-
-
-
-
-
-


布局··
```java
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:gravity="center"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.a15581.myapplication.MainActivity">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="80sp"
        android:gravity="center">
    <EditText
        android:layout_width="350dip"
        android:layout_height="100dip"
        android:gravity="bottom|right"
        android:text="0"
        android:textSize="20sp"
        android:maxLength="25"
        android:editable="false"
        android:id="@+id/showview"
        />
    </RelativeLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="500sp"


        android:gravity="center_vertical"
        android:orientation="vertical"
        android:layout_alignParentTop="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true">
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <Button
            android:layout_width="80dp"
            android:layout_height="80dp"
            android:id="@+id/btn_clean"
            android:text="C"
            android:textSize="30sp"/>
            <Button
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:id="@+id/btn_delet"
                android:text="DEL"
                android:textSize="30sp"/>
            <Button
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:id="@+id/btn_div"
                android:text="/"
                android:textSize="30sp"/>
            <Button
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:id="@+id/btn_mulitiply"
                android:text="*"
                android:textSize="30sp"/>

        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="0dp"
            android:orientation="horizontal">

            <Button
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:id="@+id/btn_7"
                android:text="7"
                android:textSize="30sp"/>

            <Button
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:id="@+id/btn_8"
                android:text="8"
                android:textSize="30sp"/>

            <Button
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:id="@+id/btn_9"
                android:text="9"
                android:textSize="30sp"/>
            <Button
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:id="@+id/btn_minus"
                android:text="-"
                android:textSize="30sp"/>

        </LinearLayout>
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="0dp"
            android:orientation="horizontal">

            <Button
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:id="@+id/btn_4"
                android:text="4"
                android:textSize="30sp"/>

            <Button
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:id="@+id/btn_5"
                android:text="5"
                android:textSize="30sp"/>

            <Button
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:id="@+id/btn_6"
                android:text="6"
                android:textSize="30sp"/>
            <Button
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:id="@+id/btn_add"
                android:text="+"
                android:textSize="30sp"/>

        </LinearLayout>

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="0dp"
            android:orientation="horizontal">
            <LinearLayout
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:orientation="vertical">
                <LinearLayout
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                    <Button
                        android:layout_width="80dp"
                        android:layout_height="80dp"
                        android:id="@+id/btn_1"
                        android:text="1"
                        android:textSize="30sp"/>

                    <Button
                    android:layout_width="80dp"
                    android:layout_height="80dp"
                    android:id="@+id/btn_2"
                    android:text="2"
                    android:textSize="30sp"/>

                <Button
                    android:layout_width="80dp"
                    android:layout_height="80dp"
                    android:id="@+id/btn_3"
                    android:text="3"
                    android:textSize="30sp"/>
            </LinearLayout>
                <LinearLayout
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal">
                    <Button
                        android:layout_width="120dp"
                        android:layout_height="80dp"
                        android:text="0"
                        android:textSize="30dp"
                        android:id="@+id/btn_0"/>

                    <Button
                        android:layout_width="120dp"
                        android:layout_height="80dp"
                        android:textSize="30dp"
                        android:text="."
                        android:id="@+id/btn_point"/>



                </LinearLayout>


            </LinearLayout>

            <LinearLayout
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:orientation="vertical">

                <Button
                    android:layout_width="80dp"
                    android:layout_height="160dp"
                    android:id="@+id/btn_equal"
                    android:text="="
                    android:textSize="30sp"/>



            </LinearLayout>

        </LinearLayout>


    </LinearLayout>


</RelativeLayout>
```

![Markdown](http://i1.bvimg.com/595109/fd9da505f25e6bff.png)

MainActivity
```jAVA
package com.example.a15581.myapplication;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //声明变量
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;

    private Button btn_point;// 小数点
    private Button btn_divide;// 除以
    private Button btn_multiply;// 乘以
    private Button btn_minus;// 减去
    private Button btn_pluse;// 加
    private Button btn_equal;// 等于

    private Button btn_clear;  //清空
    private Button btn_del;  //取消

    private EditText et_showview;  //输入框
    private double dou = 0;  //接收结果



    private boolean flag;//标志服，判断是否清空编辑框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    public void initView(){
        flag=true;
        //找到按钮地址
        btn_0=(Button) findViewById(R.id.btn_0);
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
        btn_divide = (Button) findViewById(R.id.btn_div);// 除以
        btn_multiply = (Button) findViewById(R.id.btn_mulitiply);// 乘以
        btn_minus = (Button) findViewById(R.id.btn_minus);// 减去
        btn_pluse = (Button) findViewById(R.id.btn_add);// 加
        btn_equal = (Button) findViewById(R.id.btn_equal);// 等于

        btn_clear = (Button) findViewById(R.id.btn_clean);
        btn_del = (Button) findViewById(R.id.btn_delet);
        et_showview = (EditText) findViewById(R.id.showview);

        //设置监听器
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

        btn_pluse.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_equal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String str=et_showview.getText().toString();
        switch (v.getId()){
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
                if(str.charAt(0)==' '){
                    et_showview.setText(((Button) v).getText());}
                else if(str.charAt(0)=='0'&&!str.contains(" ")&&!str.contains(".")){
                    et_showview.setText(((Button) v).getText());

                }

                else
                    et_showview.setText(str+((Button) v).getText());

                break;
            case R.id.btn_point:
                if(str=="0"){
                    et_showview.setText("0"+".");
                }else if(str.contains(".")||str.charAt(str.length()-1)==' '){
                    et_showview.setText(str);
                }else {
                    et_showview.setText(str+".");
                }

                break;
            case R.id.btn_add:
            case R.id.btn_mulitiply:
            case R.id.btn_div:
            case R.id.btn_minus:
                if(str.charAt(str.length()-1)==' '){
                    et_showview.setText(str.substring(0,str.length()-3)+" "+((Button) v).getText());//返回一个新的字符串，它是此字符串的一个子字符串。该子字符串从指定索引处的字符开始，直到此字符串末尾。
                }else {
                    et_showview.setText(str+" "+((Button) v).getText());
                }
                break;
            case R.id.btn_delet:
                if(str.length()==1){
                    et_showview.setText("0");
                }else{
                    et_showview.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.btn_clean:
                    et_showview.setText("0");
                break;
            case R.id.btn_equal:

                     getResult();
                break;
            default:
                break;


        }
    }

    private void getResult(){
        flag=false;
        String result=et_showview.getText().toString();
        if(!result.contains(" ")){
            et_showview.setText("呵呵");
        }
        else {
            String str1 = result.substring(0, result.indexOf(" "));//第一个数字
            String op = result.substring(result.indexOf(" ") + 1, result.indexOf(" ") + 2);//操作符
            String str2 = result.substring(result.indexOf(" ") + 3);//第二个数字
            if (!str2.equals("")) {
                Double d1 = Double.parseDouble(str1);
                Double d2 = Double.parseDouble(str2);
                if (op.equals("+")) {
                    dou = d1 + d2;

                } else if (op.equals("-")) {
                    dou = d1 - d2;
                } else if (op.equals("*")) {
                    dou = d1 * d2;
                } else if (op.equals("/")) {
                    if (d1 == 0 && d2 != 0) {
                        dou = 0;
                    } else if (d2 == 0) {
                        dou = 0;
                        Toast.makeText(MainActivity.this, "There is a error", Toast.LENGTH_SHORT).show();
                    } else {
                        dou = d1 / d2;
                    }
                }

                double media = dou - (int) dou;
                if (media > 0) {
                    String last = String.valueOf(dou);
                    et_showview.setText(last);
                } else {
                    if (dou > 9999999999999d) {
                        et_showview.setText("over failed");
                    } else {
                        int inter = (int) dou;
                        String lasr = String.valueOf(inter);
                        et_showview.setText(inter);
                    }
                }

            } else {
          et_showview.setText(result);
                Toast.makeText(MainActivity.this,"finished",Toast.LENGTH_SHORT    );


            }

        }
    }
}
```

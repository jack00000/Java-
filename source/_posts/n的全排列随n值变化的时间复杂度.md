---
title: 全排列的时间复杂度
date: 2017-9-17 12:39:04
tag:
---


```java
package com.example.a15581.myapplication;
import java.util.ArrayList;
import java.util.Arrays;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button start;
    private EditText T1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start=(Button) findViewById(R.id.btn_start);
        start.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int num=4;
        Date   startDate   =   new Date(System.currentTimeMillis());//开始计时
        String[] str = {"1","2","3","4"};

        arrange(str, 0, str.length);

        Date   endDate   =   new   Date(System.currentTimeMillis());//结束计时
        long diff = endDate.getTime() - startDate.getTime();
         String sdiff=String.valueOf(diff);
        ((EditText) findViewById(R.id.btn_1)).setText("n="+num+"耗时"+sdiff+"ms");


        int num2=5;
        Date   startDate2   =   new Date(System.currentTimeMillis());//开始计时
        String[] str2 = {"1","2","3","4","5"};

        arrange(str2, 0, str2.length);

        Date   endDate2   =   new   Date(System.currentTimeMillis());//结束计时
        long diff2 = endDate2.getTime() - startDate2.getTime();
        String sdiff2=String.valueOf(diff2);
        ((EditText) findViewById(R.id.btn_2)).setText("n="+num2+"耗时"+sdiff2+"ms");

        int num3=6;
        Date   startDate3   =   new Date(System.currentTimeMillis());//开始计时
        String[] str3 = {"1","2","3","4","5","6"};

        arrange(str3, 0, str3.length);

        Date   endDate3   =   new   Date(System.currentTimeMillis());//结束计时
        long diff3 = endDate3.getTime() - startDate3.getTime();
        String sdiff3=String.valueOf(diff3);
        ((EditText) findViewById(R.id.btn_3)).setText("n="+num3+"耗时"+sdiff3+"ms");


        int num4=7;
        Date   startDate4   =   new Date(System.currentTimeMillis());//开始计时
        String[] str4 = {"1","2","3","4","5","6","7"};

        arrange(str4, 0, str4.length);

        Date   endDate4   =   new   Date(System.currentTimeMillis());//结束计时
        long diff4 = endDate4.getTime() - startDate4.getTime();
        String sdiff4=String.valueOf(diff4);
        ((EditText) findViewById(R.id.btn_4)).setText("n="+num4+"耗时"+sdiff4+"ms");

        int num5=8;
        Date   startDate5   =   new Date(System.currentTimeMillis());//开始计时
        String[] str5 = {"1","2","3","4","5","6","7","8"};

        arrange(str5, 0, str5.length);

        Date   endDate5   =   new   Date(System.currentTimeMillis());//结束计时
        long diff5 = endDate5.getTime() - startDate5.getTime();
        String sdiff5=String.valueOf(diff5);
        ((EditText) findViewById(R.id.btn_5)).setText("n="+num5+"耗时"+sdiff5+"ms");


        int num6=9;
        Date   startDate6   =   new Date(System.currentTimeMillis());//开始计时
        String[] str6 = {"1","2","3","4","5","6","7","9"};

        arrange(str6, 0, str6.length);

        Date   endDate6   =   new   Date(System.currentTimeMillis());//结束计时
        long diff6 = endDate6.getTime() - startDate.getTime();
        String sdiff6=String.valueOf(diff6);
        ((EditText) findViewById(R.id.btn_6)).setText("n="+num6+"耗时"+sdiff6+"ms");

        int num7=10;
        Date   startDate7   =   new Date(System.currentTimeMillis());//开始计时
        String[] str7 = {"1","2","3","4","5","6","7","8","9","10"};

        arrange(str7, 0, str7.length);

        Date   endDate7  =   new   Date(System.currentTimeMillis());//结束计时
        long diff7 = endDate7.getTime() - startDate7.getTime();
        String sdiff7=String.valueOf(diff7);
        ((EditText) findViewById(R.id.btn_7)).setText("n="+num7+"耗时"+sdiff7+"ms");




    }



        /**
         * 交换str数组的 i和 j。
         *
         * @param str
         * @param i
         * @param j
         */
        public static void swap(String[] str, int i, int j) {
            String temp = new String();
            temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }

        /**
         * 对str数组，，从st到len进行全排列
         *
         * @param str
         * @param st
         * @param len
         */
        public static void arrange(String[] str, int st, int len) {
            int total=0;
            if (st == len - 1) {
                for (int i = 0; i < len; i++) {
                    System.out.print(str[i] + "  ");
                }
                System.out.println();
                total++;
            } else {
                for (int i = st; i < len; i++) {
                    swap(str, st, i);
                    arrange(str, st + 1, len);
                    swap(str, st, i);
                }
            }

        }
    }



```

![Markdown](http://i1.bvimg.com/595109/e68940064a763c2c.png)

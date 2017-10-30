---
title: JDK SDK下载配置
date: 2017-6-14 20:13:10
tags:
---

```java
1、JDK(Java Development Kit) 是 Java 语言的软件开发工具包(SDK)。开发Java程序必须要的工具包。
2、软件开发工具包（外语首字母缩写：SDK、外语全称：Software Development Kit）一般都是一些软件工程师为特定的软件包、软件框架、硬件平台、操作系统等建立应用软件时的开发工具的集合。这个封装了程序具体实现的细节；例如点击按钮这个事件的具体细节就封装在sdk中，开发者只需要调用相应的方法或接口就可以实现点击按钮的事件处理，具体调用操作系统的细节就有sdk去处理
3、ADT是Android开发的eclipse插件，用于打包和封装Android应用
```

官网下载：http://www.oracle.com/technetwork/java/javase/downloads/index.html

默认安装路径：c:/program files/java


安装成功检验方法：cmd中输入Java -version

<img src='http://d1.freep.cn/3tb_170614220506e13f585966.png'/>

配置jdk：https://jingyan.baidu.com/album/f96699bb8b38e0894e3c1bef.html

检验配置成功：JAVAC

<img src='http://d1.freep.cn/3tb_1706142205066v1q585966.png'/>

别再在这上面浪费时间！！！

<h3>Android SDK的安装与环境变量配置</h3>

下载SDK tool  再下载sdk

默认安装路径：c:/program files（X86）/Andriod



1、下载Android SDK，点击安装，直接默认路径即可！
下载地址：http://developer.android.com/sdk/index.html
Android <wbr>SDK的安装与环境变量配置

2、默认路径安装后，安装完成，开始配置环境变量。
3、打开计算机属性——高级系统设置——环境变量（如上文）
4、新建一个环境变量，变量名：ANDROID_HOME，变量值：C:\Program Files (x86)\Android\android-sdk（以你安装目录为准,确认里面有tools和add-ons等多个文件夹），点击确认。
Android <wbr>SDK的安装与环境变量配置

5、在变量PATH后面加上变量值%ANDROID_HOME%\tools;点击确认即可。 如果没有这个变量，新建一个即可！新建方法见上文！
6、Android SDK配置完成，接下来验证配置是否成功。
7、点击运行——输入cmd——回车——输入android -h——回车，如果出现一堆英文，如下图所示，即表示配置成功！！
<img src='http://d1.freep.cn/3tb_170617123536lmt6585966.png'/>

---
title: build.gradle文件详解<CODE 2 chaper2>
date: 2017-7-20 20:13:10
tags: CODE 2
---
<h3>build.gradle(项目目录下)</h3>
```android
buildscript {
    repositories {
        jcenter()//代码托管仓库，声明后可引用jcenter上的开源项目
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.2.0'//“依赖”文件使用classpath声明一个gradle插件  声明的原因：让gradle知道构建的是Android java 还是
c++                                                     //com.android.tools.build:gradle:2.2.0说明构建的是android项目
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()//代码托管仓库，声明后可引用jcenter上的开源项目
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```

<h3>build.gradle(项目目录下)</h3>
```Android
apply plugin: 'com.android.application'//应用一个插件 两个值可选  应用程序模块 （可直接运行）   com.android.library库模块（作为代码库依附别的库运行）

android {
    compileSdkVersion 24  //编译所用sdk版本
    buildToolsVersion "24.0.2"//构建工具版本
    defaultConfig {//细节配置
        applicationId "com.example.helloworld"//项目包名
        minSdkVersion 15//最低sdk版本
        targetSdkVersion 24//充分测试的sdk版本
        versionCode 1//项目版本号
        versionName "1.0"//项目版本名
    }
    buildTypes {//生成安装文件的相关配置
        release {//release指定生产正式版安装文件的配置    debug闭包指定生成测试版安装文件的配置
            minifyEnabled false//指定是否对项目的代码进行混淆
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'//proguardFiles指定混淆的规则文件  //proguard-android.txt通用混淆规则  //proguard-rules.pro自定义混淆规则
        }
    }
}

dependencies {//指定项目的所有依赖文件
    compile fileTree(include: ['*.jar'], dir: 'libs')//本地依赖声明
    compile 'com.android.support:appcompat-v7:24.2.1'//远程依赖声明
    testCompile 'junit:junit:4.12'//声明测试用例库
}
```

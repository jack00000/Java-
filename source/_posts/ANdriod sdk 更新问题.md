---
title: Andriod sdk 更新问题
date: 2017-6-18 20:13:10
tags:
---

<h3>问题描述</h3>

```java

使用SDK Manager更新时出现问题
Failed to fetch URL https://dl-ssl.google.com/android/repository/repository-6.xml, reason: Connection to https://dl-ssl.google.com refused
Failed to fetch URL http://dl-ssl.google.com/android/repository/addons_list-1.xml, reason: Connection to http://dl-ssl.google.com refused
Failed to fetch URL https://dl-ssl.google.com/android/repository/addons_list-1.xml, reason: hostname in certificate didn't match: <dl-ssl.google.com> != <www.google.com>
更新ADT时无法解析https://dl-ssl.google.com/android/eclipse

```

<h3>解决办法</h3>

由于某些众所周知又无法理解的原因，我们大陆使用Google的服务会出现种种问题，譬如Android开发也会出现阻碍。不过首先要说明的是一般情况下使用SDK Manager更新或者更新Eclipse的ADT插件是没有问题的，我以前也能正常更新，但是昨天不知道节点抽什么风，压根无法连接服务器，出现了上边的种种问题，下面说一下如果网络抽风的话应该如何解决问题。

第一种方法一劳永逸，直接配置VPN，但是现在想找个速度快又稳定还免费的VPN实在不易，尤其是更新SDK，以几kb/s的速度一个文件需要400多分钟，所以也就放弃了VPN。

另一种方法是使用http协议而不是https协议，因为https协议进行了加密处理，大陆因为无法审查，直接封死，而http协议则进行过滤处理，如果不访问乱七八糟的东西，更新个SDK还是没问题的。
在SDK Manager下Tools->Options打开了SDK Manager的Settings，选中“Force https://… sources to be fetched using http://…”，强制使用http协议。
而在更新ADT插件的时候则使用网址http://dl-ssl.google.com/android/eclipse，而不是https://dl-ssl.google.com/android/eclipse，这个在官方开发文档里也有介绍。
但是昨天的情况就是使用http协议也无法访问。

<img src='http://d1.freep.cn/3tb_170617121117nuh3585966.png'/>

再说一个比较麻烦的方法，就是直接打开
https://dl-ssl.google.com/android/repository/addons_list.xml
https://dl-ssl.google.com/android/repository/repository.xml
https://dl-ssl.google.com/android/repository/addon.xml
这几个文件，找到你要下载的文件名，直接用迅雷下载，ADT可以直接在官网下载ADT包进行安装。具体方法自己搜索。

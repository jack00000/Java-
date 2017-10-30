---
title: wechar robot
data: 2017-3-26 12：00
tags:
---
- python的安装   pip的安装
- [下载weBot源码](https://github.com/jack00000/wxBot)
- [注册图灵账号](http://www.tuling123.com/)并创建机器人获得apikey
- 然后开始搞事情

<h3>1.python的安装   pip的安装</h3>
- 别忘啦配置路径
- [python安装](http://jingyan.baidu.com/article/7908e85c78c743af491ad261.html)

- 重点说pip（为安装一些依赖库而生）这个坑
[python官网](https://pypi.python.org/packages/11/b6/abcb525026a4be042b486df43905d6893fb04f05aac21c32c638e939e447/pip-9.0.1.tar.gz#md5=35f01da33009719497f01a4ba69d63c9)
下载文件如图
<img src="http://i1.piimg.com/4851/6a72a24d2f40147e.png">
- 打开cmd 直接输入python 结果如下，则python已搞定
<img src="http://i1.piimg.com/4851/5172b9207690a331.png">
<h3>注意</h3>
- 需要将pip的路径配给系统变量Path
- 需要将python安装目录下的Scripts文件路径陪给系统变量Path
<img src="http://i1.piimg.com/4851/58561b78d3c51c7a.png">
- 配置好的pip，运行如下：
<img src='http://i1.piimg.com/1949/0429ebde5aa05efe.png'>

<h3>注册图灵账号并获得apikey</h3>
<img src='http://i1.piimg.com/1949/4b90c4f0985c26ab.png'>

<h3>搞事情</h3>
详情见wxbot的[readme.md](https://github.com/jack00000/wxBot/blob/master/README.md)
- 在bot.py所在目录下新建conf.ini配置文件
<img src='http://i1.piimg.com/1949/09ce78b2a6116127.png'>
- 里面的内容
<img src='http://i1.piimg.com/1949/7f62ddce1c252787.png'>
- 运行bot.py(用cmd打开bot.py文件所在目录)
<img src='http://i1.piimg.com/1949/1da7e1f71ed132f2.png'>
- 用手机微信扫描二维码登录 扫描后如下：
<img src='http://i1.piimg.com/1949/5362a69d1dde4dd8.png'>
- 到这步就成功啦  要注意的是 群聊@你的备注 机器人不回复，将你的备注改成你的用户名就行拉
- 效果如图
<img src='http://i1.piimg.com/1949/96437616c466c3f2.jpg'>

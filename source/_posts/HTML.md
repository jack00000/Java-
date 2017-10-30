---
title: HTML
date: 2017-2-14 18:00
tags:
---
<h3>1.HTML框架</h3>
HTML决定网页内容，CSS决定网页样式，javascripe决定网页行为。

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="ucf-8">//编码方式ucf-8
<title>fuck</title>//标题
<body>//body里面是网页的内容

</body>
</head>
</html>//就TM这样简单
```
CSS以styl标签搞事情
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>菜鸟教程(runoob.com)</title>
<style>
body     //body的样式
{
	background-color:#d0e4fe;
}
h1      //h1的样式
{
	color:orange;
	text-align:center;
}
p       //page的样式
{
	font-family:"Times New Roman";
	font-size:20px;
}
</style>
</head>

<body>

<h1>CSS 实例!</h1>
<p>这是一个段落。</p>

</body>
</html>
```
scripe标签
```html
<!DOCTYPE html>
<html>
<head> 
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
</head>
<body>

<div style="text-align:center">
  <button onclick="playPause()">播放/暂停</button>
  <button onclick="makeBig()">放大</button>
  <button onclick="makeSmall()">缩小</button>
  <button onclick="makeNormal()">普通</button>
  <br>
  <video id="video1" width="420">
    <source src="mov_bbb.mp4" type="video/mp4">
    <source src="mov_bbb.ogg" type="video/ogg">
    您的浏览器不支持 HTML5 video 标签。
  </video>
</div>

<script>
var myVideo=document.getElementById("video1");

function playPause()
{
	if (myVideo.paused)
	  myVideo.play();
	else
	  myVideo.pause();
}

	function makeBig()
{
	myVideo.width=560;
}

	function makeSmall()
{
	myVideo.width=320;
}

	function makeNormal()
{
	myVideo.width=420;
}
</script>

</body>
</html>
```
<h3>简单的网页以及两个常用搜索</h3>
<img src="http://i1.piimg.com/567571/e36e34c045d6c315.png">
代码如下
```html
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<title>fuck</title>
<style media="screen">
input[type=text] {         //静态栏搜索
    width: 130px;
    box-sizing: border-box;
    border: 2px solid #ccc;
    border-radius: 4px;
    font-size: 16px;
    background-color: white;
    background-image: url('searchicon.png');
    background-position: 10px 10px;
    background-repeat: no-repeat;
    padding: 12px 20px 12px 40px;
    -webkit-transition: width 0.4s ease-in-out;
    transition: width 0.4s ease-in-out;
}

input[type=text]:focus {
    width: 100%;
}
body{
  background-image: url('http://p1.bqimg.com/567571/1487e5929ef40176.jpg');
}
#myInput {                       //动态栏搜索
  background-image: url('https://static.runoob.com/images/mix/searchicon.png'); /* 搜索按钮 */
  background-position: 10px 12px; /* 定位搜索按钮 */
  background-repeat: no-repeat; /* 不重复图片 */
  width: 100%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}


</style>
<body>
  <input type="text" id="myInput" onkeyup="myFunction()" placeholder="搜索...">

  <form>
    <input type="text" name="search" placeholder="Search..">
  </form>
</body>

</html>
```

---
title: semmantical UI的使用
date: 2017-8-29 20:13:10
tags:

---

<h2>学习目的：1.熟练使用semantical官网所提供的样式 2.了解常用关键词如inverted vertical等 div的嵌套 3.semantical UI 非常语义话 如div class="ui big star  segment"> 就画啦一个大星星</h2>

<h2>1.简而言之：别人css javascript theme写好啦等你用。</h2>
![Markdown](http://i1.bvimg.com/595109/4e32188b0be717a0.png)
![Markdown](http://i1.bvimg.com/595109/18aa5ef45d17c856.png)
![Markdown](http://i1.bvimg.com/595109/cfff7ed03564ef4d.png)

<h2>2.别人的样式semantic.css    区域块样式引用<div class="ui inverted vertical  segment"></h2>
![Markdown](http://i2.bvimg.com/595109/7aa0699c19bec9b7.png)
```html
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>first web</title>
        <link rel="stylesheet" href="css/semantic.css" media="screen" title="no title" charset="utf-8">
    </head>
    <body>
        <div class="ui inverted vertical  segment">//样式的引用 inverted 反转（颜色）  vertical 垂直（不同块间没有间隙）
            <div class="ui image">//通常用div标签表示区域块
                <img src="images/banner.jpg" alt="" />
            </div>
        </div>

        <div class="ui  vertical  segment">
            <div class="ui container segment">
                <div class="ui blue right ribbon label">
                    life
                </div>
                <h1 class="ui header">First web</h1>
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                </p>
                <button type="button" name="button" class="ui inverted blue button">Read more</button>
            </div>

            <div class="ui container segment">
                <div class="ui red right ribbon label">
                    tech
                </div>
                <h1 class="ui header">First web</h1>
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                </p>
                <button type="button" name="button" class="ui inverted blue button">Read more</button>
            </div>

            <div class="ui container segment">
                <div class="ui teal right ribbon label">
                    news
                </div>
                <h1 class="ui header">First web</h1>
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                </p>
                <button type="button" name="button" class="ui inverted blue button">Read more</button>
            </div>
        </div>

        <div class="ui inverted  vertical very padded  segment">
            Mugglecoding®
        </div>
    </body>
</html>
```
![Markdown](http://i2.bvimg.com/595109/0c54ac720765f57d.png)
<h2>画神盾</h2>
```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="css/semantic.css" media="screen" title="no title" charset="utf-8">
  </head>
  <body>
     <div class="ui  inverted red circular segment">//非常语义化的semantical UI
         <div class="ui circular segment">
              <div class="ui inverted red circular segment">
                    <i class="circular inverted blue big star icon"></i>
              </div>
         </div>
     </div>
  </body>
</html>
```
![Markdown](http://i2.bvimg.com/595109/bbe1fa067583d419.png)

<h2>3.了解网格grid分布 及柱形分布column</h2>
```html
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>First landing page</title>
        <link rel="stylesheet" href="css/semantic.css" media="screen" title="no title" charset="utf-8">
    </head>
    <body>
        <div class="ui fixed inverted menu">//semantical  的UI menu
            <a href="#" class="item">Home</a>//带有链接的按钮通常用标签a表示
            <a href="#" class="item">About</a>
            <a href="#" class="item">Other</a>
        </div>
        <div class="ui vertical basic segment">
            <div class="ui image">
                <img src="images/banner.jpg" alt="" />
            </div>
        </div>

        <div class="ui vertical basic segment">
            <div class="ui grid">//grid网格分布 区域块中存在某种比例
              <div class="ten wide column">//10个柱形区域

                    <div class="ui image">
                        <img src="images/devices2.png" alt="" />
                    </div>
                </div>

                <div class="six wide column">  6个柱形区域
                    <h2 class="ui header">
                        <i class="icon star"></i>//<i> 标签显示斜体文本效果
                        This ia a title
                    </h2>

                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    </p>
                </div>

                </div>
            </div>
        </div>




        <div class="ui vertical very padded inverted segment">//padded内聚 文字距离区域块边缘的多少
            <div class="ui grid">a
                <div class="four wide column">
                    <div class="ui vertical inverted text menu">
                        <div class="item">
                            <h3 class="ui inverted header">Company</h3>
                        </div>

                        <div class="item">
                            Address : CN
                        </div>

                        <div class="item">
                            Tel : 010-66666
                        </div>

                        <div class="item">
                            Fax : 010-66666
                        </div>
                    </div>
                </div>

                <div class="four wide column">
                    <div class="ui vertical inverted text menu">
                        <div class="item">
                            <h3 class="ui inverted header">Company</h3>
                        </div>

                        <div class="item">
                            Address : CN
                        </div>

                        <div class="item">
                            Tel : 010-66666
                        </div>

                        <div class="item">
                            Fax : 010-66666
                        </div>
                    </div>
                </div>

                <div class="four wide column">
                    <div class="ui vertical inverted text menu">
                        <div class="item">
                            <h3 class="ui inverted header">Company</h3>
                        </div>

                        <div class="item">
                            Address : CN
                        </div>

                        <div class="item">
                            Tel : 010-66666
                        </div>

                        <div class="item">
                            Fax : 010-66666
                        </div>
                    </div>
                </div>

                <div class="four wide column">
                    <div class="ui vertical inverted text menu">
                        <div class="item">
                            <h3 class="ui inverted header">Company</h3>
                        </div>

                        <div class="item">
                            Address : CN
                        </div>

                        <div class="item">
                            Tel : 010-66666
                        </div>

                        <div class="item">
                            Fax : 010-66666
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
```
![Markdown](http://i4.bvimg.com/595109/ce2522edca8266d0.png)


<h2>对semantic较复杂的使用</h2>
```html
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/semantic.css" media="screen" title="no title" charset="utf-8">
        <title>Apple(中国)-非官方网站</title>
    </head>
    <body>
        <!-- 菜单部分开始 -->
        <div class="ui inverted fixed fitted borderless fluid nine item menu">
            <a href="#" class="item">
                <div class="ui image">
                    <img class="" src="images/appleicon.png" alt="" />
                </div>
            </a>
            <a href="#" class="item">Mac</a>
            <a href="#" class="item">iPad</a>
            <a href="#" class="item">iPhone</a>
            <a href="#" class="item">Watch</a>
            <a href="#" class="item">Music</a>
            <a href="#" class="item">技术支持</a>

            <a href="#" class="item">
                <div class="ui image">
                    <img class="" src="images/searchicon.png" alt="" />
                </div>
            </a>
            <a href="#" class="item">
                <div class="ui image">
                    <img class="" src="images/buyicon.png" alt="" />
                </div>
            </a>
        </div>
        <!-- 菜单部分结束 -->
        <!-- 导航图部分开始 -->
        <div class="ui secondary vertical basic segment ">
            <div class="ui banner image">
                <img src="images/banner.png" alt="" />
            </div>
        </div>
        <!-- 导航图部分结束 -->
        <!-- 4张小图部分开始 -->
        <div class="ui secondary vertical basic segment ">
            <div class="ui fitted text menu">
                <div class="item">
                    <img class="ui image" src="images/img1.png" alt="" />
                </div>

                <div class="item">
                    <img class="ui image" src="images/img2.png" alt="" />
                </div>

                <div class="item">
                    <img class="ui image" src="images/img3.png" alt="" />
                </div>

                <div class="item">
                    <img class="ui image" src="images/img4.png" alt="" />
                </div>
            </div>
        </div>
        <!-- 4张小图部分结束 -->
        <!-- 页尾部分开始 -->
        <div class="ui vertical secondary very padded segment">
            <div class="ui container">
                <div class="sub header">
                    双镜头摄像头仅于 iPhone 7 Plus 提供。亮黑色外观仅于 128GB 及以上存储容量的机型提供。
                </div>

                <div class="ui divider"></div>

                <div class="ui five column grid">
                    <div class="column">
                        <div class="ui vertical text menu">
                            <div class="item">
                                <h4>Apple Store 商店</h4>
                            </div>
                            <a class="item">
                                查找零售店
                            </a>
                            <a class="item">
                                iPad
                            </a>
                            <a class="item">
                                iPhone
                            </a>
                            <a class="item">
                                Watch
                            </a>
                            <a class="item">
                                iPod
                            </a>
                        </div>
                    </div>

                    <div class="column">
                        <div class="ui vertical text menu">
                            <div class="item">
                                <h4>Apple Store 商店</h4>
                            </div>
                            <a class="item">
                                查找零售店
                            </a>
                            <a class="item">
                                iPad
                            </a>
                            <a class="item">
                                iPhone
                            </a>
                            <a class="item">
                                Watch
                            </a>
                            <a class="item">
                                iPod
                            </a>
                        </div>
                    </div>
                    <div class="column">
                        <div class="ui vertical text menu">
                            <div class="item">
                                <h4>Apple Store 商店</h4>
                            </div>
                            <a class="item">
                                查找零售店
                            </a>
                            <div class="item">
                                <!-- nothing here just want a litte space XD-->
                            </div>
                            <div class="item">
                                <h4>商务应用</h4>
                            </div>
                            <a class="item">
                                Apple 与商务
                            </a>
                            <a class="item">
                                商务选购
                            </a>
                        </div>
                    </div>
                    <div class="column">
                        <div class="ui vertical text menu">
                            <div class="item">
                                <h4>Apple Store 商店</h4>
                            </div>
                            <a class="item">
                                查找零售店
                            </a>
                            <div class="item">
                                <!-- nothing here just want a litte space XD-->
                            </div>
                            <div class="item">
                                <h4>商务应用</h4>
                            </div>
                            <a class="item">
                                Apple 与商务
                            </a>
                            <a class="item">
                                商务选购
                            </a>
                        </div>
                    </div>

                    <div class="column">
                        <div class="ui vertical text menu">
                            <div class="item">
                                <h4>Apple Store 商店</h4>
                            </div>
                            <a class="item">
                                查找零售店
                            </a>
                            <a class="item">
                                iPad
                            </a>
                            <a class="item">
                                iPhone
                            </a>
                            <a class="item">
                                Watch
                            </a>
                            <a class="item">
                                iPod
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- 页尾部分结束 -->
    </body>
</html>
```
![Markdown](http://i4.bvimg.com/595109/6a54ea2037878935.png)

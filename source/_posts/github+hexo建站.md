---
title: github+hexo建站
date: 2016-12-18 20:13:10
tags:
---




------

<h3>  1.  安装nodo.js ,  git，学习markdown语言</h3>
[如何安装nodo.js](http://jingyan.baidu.com/article/b0b63dbfca599a4a483070a5.html)       [如何安装git](http://jingyan.baidu.com/article/90895e0fb3495f64ed6b0b50.html)
[什么是markdown](https://zhidao.baidu.com/question/1766196940543047940.html)
[如何在github上建立仓库](http://jingyan.baidu.com/article/ce43664927411a3773afd30f.html)(注意：仓库名一定要是     你的github用户名.github.io    )
[如何使本地仓库与github的仓库建立关联](http://blog.csdn.net/binyao02123202/article/details/20130891)
<img src='http://img.027cgb.cn/20170711/20177119681775731906.png' />

你要知道git的基本使用如：
```
//先cd你的仓库，然后
git add .//添加你的修改
git commit -m"注释"// -m可以不用写注释
git push//提交
```
<h3>2.安装HEXO</h3>
```c++
npm install hexo-cli -g    //cmd中输入
```

<img src='http://img.027cgb.cn/20170711/20177112501775731906.png' />

<h3>3.hexo 命令</h3>
```
//打开一个文件夹
hexo init blog//创建一个博客文件
cd blog//打开博客文件
npm install//生成node_modules依赖文件
hexo server//部署到本地服务器
hexo g//生成静态文件（上传到gihub的你建的仓库就可以用啦）
hexo d//一键部署到服务器（前提在_config.yml中配置delopy）
```
<h3>4.theme的转换</h3>
[在github上搜索hexo theme](https://github.com/search?utf8=%E2%9C%93&q=hexo+theme)
将下载好的theme文件放在theme目录之下
在配置文件config.yml找到theme变量
将变量的值landspace改为刚下载的theme的文件名，如果这步出错，首先怀疑是不是theme出啦问题。
（一定要记住保存，再打开git bash 搞事情）
hexo g生成静态文件到public
把pulic的文件传到github.io仓库就行

<h3>5.使用markdown写博文</h3>
[介绍一款不错的markdown在线编辑器cmd markdown](https://www.zybuluo.com/mdeditor)
[几种流行的客户端](http://sspai.com/27792)
（注意：写博文，文件名一定要是xxxx.md,不然hexo g不能识别你写的文件）
markdown语言很简单，简单来说，就是不用鼠标就能完成简单排版的语言

<h3>6.一键部署到github</h3>
在config.yml末尾处配置deploy
```
deploy:
  type: git
  repo: https://github.com/jack00000/jack00000.github.io.git
  branch: master
  message: blog
```
  在git bash中输入命令
```
  npm install hexo-deployer-git --save
  hexo g
  hexo d
```
打开xxxx.github.io看下成功没。（注意：由于版本原因，type：github报错的话改成github）

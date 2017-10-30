---
title: deploy报错及其解决方法
date: 2017-2-1 20:13:10
tags:
---


#报错提示1：

------

```
INFO  Deploying: git
FATAL Something's wrong. Maybe you can find the solution here: http://hexo.iocs/troubleshooting.html
TypeError: source.replace is not a function
```
解决方法:
_config.yml中的depoloy设置（这是正确的前提，特别注意冒号后要有空格，repo配置时要写成https形式，type类型要是git）
```
deploy:
  type: git
  repo: https://github.com/jack00000/jack00000.github.io.git
  branch: master
  message: blog
```
git bash中应该输入的命令
```
 npm install hexo-deployer-git --save//安装deploy的依赖文件
 hexo g//重新生成public文件
 hexo d//部署到github上
```
 ##正确后的提示代码：
 ![cmd-markdown-logo](http://i1.piimg.com/4851/f42f8de6bed1f3c0.png)

 ##如果实在不会hexo deploy，教你一种直接的方法：
 ```
 打开blog目录
 找到public文件
 将public里面的文件上传到你新建的仓库xxxx.githu.io
 然后直接打开游览器，输入xxxx.github.io
 ```
#blog文件，public文件，以及部署成功示例图
 ![](http://p1.bqimg.com/4851/f2acfc2a037f022c.png)
 ![](http://p1.bqimg.com/4851/241367ffd76771ff.png)
 ![](http://p1.bqimg.com/4851/59600f5a407ccfe3.png)
 其实你的静态文件就是public里面的文件，只要把public的东西上传到github就行

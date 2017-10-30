---
title: Valine -- 一款极简的评论系统
date: 2016-8-17 20:13:10
tags:
---

<h3>Hexo 中的解决方案


获取 APP ID 和 APP KEY

1.点击这里登录或注册Leancloud
2.点这里创建应用，应用名看个人喜好。
3.选择刚刚创建的应用>设置>选择应用 Key，然后你就能看到你的APP ID和APP KEY了，参考下图：
![Markdown](http://i2.bvimg.com/595109/7af91d978cb98978.png)

4.为了您的数据安全，请填写应用>设置>安全设置中的Web 安全域名(自己的域名就行)，如下图：
![Markdown](http://i2.bvimg.com/595109/fee84924fb3fc62e.png)






注：该解决方案基于Hexo主题–NexT.Pisces

配置_config.yml

主题目录下的/themes/next/_config.yml中添加：
```yml
valine:
  enable: true
  appId: Your_AppId
  appKey: Your_AppKey
```  
修改comments.swig

注：因为我没有其他的评论系统需求，所以将其他的删掉了。点这里，查看原主题的comments.swig备份文件

打开/themes/next/layout/_partials/comments.swig，整个文件修改为：
```swig

{% if page.comments %}
  <div class="comments" id="comments">
      {% if (theme.valine and theme.valine.enable)%}
        <script src="//cdn1.lncld.net/static/js/3.0.4/av-min.js"></script>
        <script src='/lib/Valine.min.js'></script>
        <script>
            new Valine({
                av: AV,
                el: '.comments' ,
                app_id: '{{ theme.valine.appId }}',
                app_key: '{{ theme.valine.appKey }}',
                placeholder: 'ヾﾉ≧∀≦)o来啊，快活啊!'
            });
        </script>
      {% endif %}
  </div>
{% endif %}
```
注：我引入的Valine路径为：/lib/Valine.min.js，是已将文件[Valine.min.js](https://raw.githubusercontent.com/xCss/Valine/master/dist/Valine.min.js)放到了目录/themes/next/source/lib/下。

好了，差不多了，如果你看到你的文章页面出现了如下图所示的评论框，那么恭喜你，你已装逼成功，赶紧去炫耀吧。ヽ(￣▽￣)ﾉ



评论数据管理

插播一下，关于评论数据管理，请自行登录Leancloud应用管理。
具体步骤：登录>选择你创建的应用>存储>选择ClassComment，然后就可以尽情的发挥你的权利啦(～￣▽￣)～

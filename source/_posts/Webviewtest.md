---
title: webviewtest<CODE 2 chaper9>
date: 2017-7-31 13:10
tags: CODE 2
---

<h3>学习目的：1.显示各种网页</h3>
权限声明
```java
<uses-permission android:name="android.permission.INTERNET" />
```

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);//支持javas脚本
        webView.setWebViewClient(new WebViewClient());//当网页跳转到另一个网页时  任然在webview显示
        webView.loadUrl("http://www.baidu.com");
    }

```

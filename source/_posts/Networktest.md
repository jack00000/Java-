---
title: networktest<CODE 2 chaper9>
date: 2017-8-1 20:13:10
tags: CODE 2
---

<h3>学习目的：1.使用HTTP访问网络 2.使用HttpURIConnection

1.使用HTTP访问网络
---
工作原理：客户端向服务器发出HTTP请求 服务器接收到请求返回数据 客户端解析处理

2.使用HttpURIConnection（本例获取的是html代码）

布局
```java
<Button
        android:id="@+id/send_request"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Send Request" />

    <ScrollView//滚动查看界面显示不了的内容
        android:layout_width="match_parent"
        android:layout_height="match_parent" >

        <TextView
            android:id="@+id/response_text"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    </ScrollView>
```

MainActivity类
```java
@Override
   public void onClick(View v) {
       if (v.getId() == R.id.send_request) {
         sendRequestWithHttpURLConnection();

       }
   }
private void sendRequestWithHttpURLConnection() {
       // 开启线程来发起网络请求
       new Thread(new Runnable() {
           @Override
           public void run() {
               HttpURLConnection connection = null;
               BufferedReader reader = null;
               try {
                   URL url = new URL("http://www.baidu.com");
                   connection = (HttpURLConnection) url.openConnection();
                   connection.setRequestMethod("GET");
                   connection.setConnectTimeout(8000);
                   connection.setReadTimeout(8000);
                   InputStream in = connection.getInputStream();
                   // 下面对获取到的输入流进行读取
                   reader = new BufferedReader(new InputStreamReader(in));
                   StringBuilder response = new StringBuilder();
                   String line;
                   while ((line = reader.readLine()) != null) {
                       response.append(line);
                   }
                   showResponse(response.toString());
               } catch (Exception e) {
                   e.printStackTrace();
               } finally {
                   if (reader != null) {
                       try {
                           reader.close();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
                   if (connection != null) {
                       connection.disconnect();
                   }
               }
           }
       }).start();
   }
```

添加权限
```java

 <uses-permission android:name="android.permission.INTERNET" />
 ```

 3.提交数据给服务器（如：提交用户名和密码）数据用&隔开
 ```java
 connection.setRequestMethod("POST");
 DataOutPutStream out=new DataOutPutStream(connection.getOutPutStream());
 out.writeBytes("username=admin&password=123456789");
```

4.使用Okhttp
添加依赖
```java
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:24.2.1'
    compile 'com.squareup.okhttp3:okhttp:3.4.1'//自动下载两个库Okhttp库 Okio库

}
```


用Okhttp重写http的例子
```java
public void onClick(View v) {
       if (v.getId() == R.id.send_request) {
//            sendRequestWithHttpURLConnection();
           sendRequestWithOkHttp();
       }
   }

   private void sendRequestWithOkHttp() {
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   OkHttpClient client = new OkHttpClient();
                   Request request = new Request.Builder()

                           .url("http://www.baidu.com")
                           .build();
                   Response response = client.newCall(request).execute();
                   String responseData = response.body().string();

                  showResponse(responseData);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }).start();
   }

   private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                responseText.setText(response);
            }
        });
    }
```

---
title: networktest3<CODE 2 chaper9>
date: 2017-8-2 20:13:10
tags: CODE 2
---

优化：（在tese1，2中http请求代码基本相同 但我们每一次发送请求都编写一次代码）将通用的网络操作放到一个类HttpUil

HttpUil
```java
public class HttpUtil {

    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {

                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    return response.toString();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }

}
```
若需发起请求(sendHttpRequest()内部没有开启线程 调用时可能阻塞主线程)
```
String address="http://wwww.baid.com";
String response=HttpUtil.sendHttpRequest(address);
```

解决方法(Java的回调机制)
首先定义接口 HttpCallbackListener
```java
public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);

}
```

修改HttoUtil
```java
public class HttpUtil {

    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
                        // 回调onFinish()方法
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        // 回调onError()方法
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


}
```    
传入HttoCallbackListener实例
```java
HttpUtil.sendHttpRequest(address,new HttpCallbackListener()){
  @version
  public void onFinish(String response){
    //在这里根据返回内容执行具体逻辑
  }
  @version
  public void onError(Exception e){
    //在这里对异常进行处理
  }

}

```

使用HttpURIConnection太复杂  改用Okhttp
在HttpUtil内田间sendOkhttpRequest()方法
```java
public static void sendOkHttpRequest(final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
```    

```java
HttpUtil.sendOkHttpRequest(address,new HttpCallbackListener()){
  @version
  public void onFinish(String response){
    //在这里根据返回内容执行具体逻辑
    String responseData=response.body().strng();
  }
  @version
  public void onError(Exception e){
    //在这里对异常进行处理
  }

}
```

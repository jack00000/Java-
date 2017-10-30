---
title: networktest2<CODE 2 chaper9>
date: 2017-8-2 20:13:10
tags: CODE 2
---


1.搭建Apache服务器
- 搜索Apache http srever
![Markdown](http://i4.eiimg.com/595109/e2f9f11792e4c50e.png)
- 一路next 成功标志
![Markdown](http://i4.eiimg.com/595109/0965150ee66f1436.png)
- 在Apache/htdocs下新建html文件


xml数据格式
```xml
<apps>
    <app>
       <id>1<id>
       <name>Google Maps</name>
       <version>1.0</version>
    </app>
    <app>
        <id>2<id>
       <name>Chorme</name>
       <version>2.1</version>
     </app>
     <app>
        <id>3<id>
       <name>Google Play</name>
       <version>2.3</version>
     </app>
</apps>
```

2.Pull解析方式（解析XML格式数据）（例子结果在log呈现）
MainActivity
```java
private void sendRequestWithOkHttp() {
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   OkHttpClient client = new OkHttpClient();
                   Request request = new Request.Builder()
                           // 指定访问的服务器地址是电脑本机
                           .url("http://10.0.2.2/get_data.xml")//get_data.xml我们在Apache/htdocs下新建的文件 10.0.2.2对模拟器来说是本机地址
                           .build();
                   Response response = client.newCall(request).execute();
                   String responseData = response.body().string();


                  parseXMLWithPull(responseData);

               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }).start();
   }

   private void parseXMLWithPull(String xmlData) {
       try {
           XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
           XmlPullParser xmlPullParser = factory.newPullParser();
           xmlPullParser.setInput(new StringReader(xmlData));
           int eventType = xmlPullParser.getEventType();
           String id = "";
           String name = "";
           String version = "";
           while (eventType != XmlPullParser.END_DOCUMENT) {
               String nodeName = xmlPullParser.getName();
               switch (eventType) {
                   // 开始解析某个结点
                   case XmlPullParser.START_TAG: {
                       if ("id".equals(nodeName)) {
                           id = xmlPullParser.nextText();
                       } else if ("name".equals(nodeName)) {
                           name = xmlPullParser.nextText();
                       } else if ("version".equals(nodeName)) {
                           version = xmlPullParser.nextText();
                       }
                       break;
                   }
                   // 完成解析某个结点
                   case XmlPullParser.END_TAG: {
                       if ("app".equals(nodeName)) {
                           Log.d("MainActivity", "id is " + id);
                           Log.d("MainActivity", "name is " + name);
                           Log.d("MainActivity", "version is " + version);
                       }
                       break;
                   }
                   default:
                       break;
               }
               eventType = xmlPullParser.next();
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
```
3.SAX解析方式(解析XML格式数据)（实现解析功能同上）
新建类继承DefaultHandler 重写5个方法
```java
public class ContentHandler extends DefaultHandler {

    private String nodeName;

    private StringBuilder id;

    private StringBuilder name;

    private StringBuilder version;

    @Override
    public void startDocument() throws SAXException {
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 记录当前结点名
        nodeName = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // 根据当前的结点名判断将内容添加到哪一个StringBuilder对象中
        if ("id".equals(nodeName)) {
            id.append(ch, start, length);
        } else if ("name".equals(nodeName)) {
            name.append(ch, start, length);
        } else if ("version".equals(nodeName)) {
            version.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("app".equals(localName)) {
            Log.d("ContentHandler", "id is " + id.toString().trim());
            Log.d("ContentHandler", "name is " + name.toString().trim());
            Log.d("ContentHandler", "version is " + version.toString().trim());
            // 最后要将StringBuilder清空掉
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

}
```

MainActivity
```java
private void sendRequestWithOkHttp() {
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   OkHttpClient client = new OkHttpClient();
                   Request request = new Request.Builder()
                           // 指定访问的服务器地址是电脑本机
                           .url("http://10.0.2.2/get_data.xml")//get_data.xml我们在Apache/htdocs下新建的文件 10.0.2.2对模拟器来说是本机地址
                           .build();
                   Response response = client.newCall(request).execute();
                   String responseData = response.body().string();


                  parseXMLWithSAX(responseData);

               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }).start();
   }



   private void parseXMLWithSAX(String xmlData) {
          try {
              SAXParserFactory factory = SAXParserFactory.newInstance();
              XMLReader xmlReader = factory.newSAXParser().getXMLReader();
              ContentHandler handler = new ContentHandler();
              // 将ContentHandler的实例设置到XMLReader中
              xmlReader.setContentHandler(handler);
              // 开始执行解析
              xmlReader.parse(new InputSource(new StringReader(xmlData)));
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
```


4.解析JSON数据格式
json数据格式
```json
{“name”:"Tome","age":20}
```

用JSONObject解析
```java
private void sendRequestWithOkHttp() {
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   OkHttpClient client = new OkHttpClient();
                   Request request = new Request.Builder()
                           // 指定访问的服务器地址是电脑本机
                           .url("http://10.0.2.2/get_data.json")//get_data.xml我们在Apache/htdocs下新建的文件 10.0.2.2对模拟器来说是本机地址
                           .build();
                   Response response = client.newCall(request).execute();
                   String responseData = response.body().string();


                  parseJSONWithJSONObject(responseData);

               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }).start();
   }



   private void parseJSONWithJSONObject(String jsonData) {
          try {
              JSONArray jsonArray = new JSONArray(jsonData);
              for (int i = 0; i < jsonArray.length(); i++) {
                  JSONObject jsonObject = jsonArray.getJSONObject(i);
                  String id = jsonObject.getString("id");
                  String name = jsonObject.getString("name");
                  String version = jsonObject.getString("version");
                  Log.d("MainActivity", "id is " + id);
                  Log.d("MainActivity", "name is " + name);
                  Log.d("MainActivity", "version is " + version);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
```

5.使用GSON解析JSON
添加依赖
```java
 compile 'com.google.code.gson:gson:2.7'
```
新建App类
```java
public class App {

    private String id;

    private String name;

    private String version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
```

MainActivity
```java
private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            .url("http://10.0.2.2/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void parseJSONWithGSON(String jsonData) {
           Gson gson = new Gson();
           List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>() {}.getType());
           for (App app : appList) {
               Log.d("MainActivity", "id is " + app.getId());
               Log.d("MainActivity", "name is " + app.getName());
               Log.d("MainActivity", "version is " + app.getVersion());
           }
       }
```       

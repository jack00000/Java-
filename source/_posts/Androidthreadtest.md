---
title: androidthreadtest<CODE 2 chaper9>
date: 2017-8-2 20:13:10
tags: CODE 2
---


<h3>学习目的：1.了解android线程的使用 2.了解主线程与子线程区别 3.解析异步处理机制
主线程与子线程：所谓主线程，在Windows窗体应用程序中一般指UI线程，这个是程序启动的时候首先创建的线程。而子线程，一般指为了完成某个特殊任务，并行于主线程的其他线程。例如让一个窗体单独开一个线程，去远程数据库中取得数据资料，下载并且保存到本地文件中。
所以，主线程和子线程是构建于线程这个通用概念上的人为的习惯称呼，并不是什么官方的固有专用词语。
MainActivity（简而言之，为实现某个功能而创建爱的线程是子线程）

1.了解android线程的使用(例：在子线程更新UI)

```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          text = (TextView) findViewById(R.id.text);
          Button changeText = (Button) findViewById(R.id.change_text);
          changeText.setOnClickListener(this);
      }

      @Override
   public void onClick(View v) {
       switch (v.getId()) {
           case R.id.change_text:
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       test.setText("Nice to meet you");
                   }
               }).start();
               break;
           default:
               break;
       }
   }

}
```

logcat报错（说明不能在子线程更新UI）修改如下
```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int UPDATE_TEXT = 1;

    private TextView text;

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    // 在这里可以进行UI操作
                    text.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        Button changeText = (Button) findViewById(R.id.change_text);
        changeText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message); // 将Message对象发送出去
                    }
                }).start();
                break;
            default:
                break;
        }
    }

}
```

3.解析异步处理机制（Message MessageQueue Looper Handler四个部分）
异步处理机制的核心思想：一条Message经过如下转展调用，从子线程进入主线程，从而使不能更新UI变成可以更新UI
![Markdown](http://i4.eiimg.com/595109/30b4ad0573b88091.png)

4.使用AsyncTask类（更方便的进行UI操作）
```java
class DownloadTask extends AsyncTask<Void Integer Boolean>{...}//
```

三个参数
Params:执行AsyncTask需要传入的参数
Progress:后台任务执行时，需要在当前界面显示进度
Result:返回值类型

还需重写四个方法
```java
class DownloadTask extends AsyncTask<Void Integer Boolean>{
  @verride
  protected void onPreExecute(){
    progressDialog.show();//显示进度对话框
  }
  @verride
  protected Boolean doInBackground(void... Params){
    try{
      while(true){
        int downloadPercent=doDownload();//这是一个虚构的方法
        publishProgress(downloadPercent);
        if(downloadPercent>=100){
          break;
        }
      }catch(Exception e){
        return false;
      }
      return true;
    }

  }
  @verride
  protected void onProgressUpdata(Integer... values){
    //在这里更新下载进度
    progressDialog.setMessage("Download"+values[0]+"%");
  }
  @verride
  protected void onPostExecute(Boolean result){
    progressDialog.dismiss();//关闭进度对话框
    if(result){
      Toast.makeText(context,"Download Succeeded",Toast.LENGTH_SHORT).show();

    }else{
        Toast.makeText(context,"Download Failed",Toast.LENGTH_SHORT).show();
    }
  }
}
```

想要启动这个任务
```java
new DownloadTask().execute();
```

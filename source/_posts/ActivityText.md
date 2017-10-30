---
title: ActivityText<CODE 2 chaper2>
date: 2017-7-20 20:13:10
tags: CODE 2
---

<h3>学习目的：1.了解standard启动模式 2.singleTop启动模式 3.singleTask启动模式 4.singleInstance启动模式
5.实用技巧：知晓当前活动 随时退出程序 启动活动最佳写法</h3>


<h3>standard启动模式</h3>
```
Android是利用返回栈来管理活动的！
在standard模式下，每启用一个新活动就会在返回栈入栈并置于栈顶。如：
button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
startActivity(intent);
}
按三次返回键才能退出！
```

<h3>singleTop启动模式</h3>
```
当栈顶是该活动不创建新实例（与standard的不同之处）
android:launchMode="singleTop"//需要在活动先声明

代码同上
仅一次back可退出程序
```

<h3>singleTask启动模式</h3>
```
解决重复创建栈顶活动的问题！使某个活动在上下文之存在一个实例。
android:launchMode="singleTask"//需要在活动先声明
```

<h3>singleInstance启动模式</h3>
```
启用一个新的返回栈管理这个活动，解决共享活动实例的问题！
android:launchMode="singleInstance"//需要在活动先声明
```
![Markdown](http://i2.tiimg.com/595109/f9bcece3e67deff6.png)
![Markdown](http://i2.tiimg.com/595109/637e7ddfe3d6e7c7.png)
![Markdown](http://i2.tiimg.com/595109/6eaebe571f0f124b.png)
![Markdown](http://i2.tiimg.com/595109/eef8749d34f27304.png)
![Markdown](http://i2.tiimg.com/595109/9ccd294fcd557c76.png)

<h3>知晓当前活动</h3>
```Android
public class BaseActivity extends AppCompatActivity {//无需注册，TM就是个java类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity", getClass().getSimpleName());
        //需要让BaseActivity成为所有类的父类，因其继承AppCompatActivity,所以其他类功能不变
    }
}
```

<h3>随时退出程序(思路：创建一个集合类对所有活动进行管理)</h3>
```Android
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();//用List存储活动

    public static void addActivity(Activity activity) {//添加活动
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {//不多说，看来add remove finish方法都写好啦
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {//销毁全部活动
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}

随时退出举例：
Button button3 = (Button) findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();}}
```                

<h3>启动活动最佳写法</h3>
```Android
为了解决 如secondActivity不是你开发的，但你需要知道启动这个活动需要传递那些数据 等问题
原写法：
Intent intent = new Intent(FristActivity.this, SecondActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        startActivity(intent);
改进写法：
public class SecondActivity extends BaseActivity {

    public static void actionStart(Context context, String data1, String data2) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1", data1);//intent传递的数据
        intent.putExtra("param2", data2);
        context.startActivity(intent);//context 上下文
    }
  }
只需一行代码启动SecondActivity
 SecondActivity.actionStart(FirstActivity.this, "data1", "data2");
 ```  

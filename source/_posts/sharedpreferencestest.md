---
title: sharedpreferencestest<CODE 2 chaper6>
date: 2017-7-28 20:13:10
tags: CODE 2
---

<h3>学习目的：1.获取sharedpreferences对象的三种方法

1.获取sharedpreferencestest对象的三种方法
- Context类中的getsharedpreferences（）
- Activity类中的getDefaulsharedpreferences()
- PreferenceManager类中的getDefaulsharedpreferences()

- 范例
```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveData = (Button) findViewById(R.id.save_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();//data文件名
                editor.putString("name", "Tom");//存储String数据
                editor.putInt("age", 28);//存储Int数据
                editor.putBoolean("married", false);//存储Boolean数据
                editor.apply();
            }
        });
}
```

- 读取数据

```java
Button restoreData = (Button) findViewById(R.id.restore_data);
       restoreData.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
               String name = pref.getString("name", "");
               int age = pref.getInt("age", 0);
               boolean married = pref.getBoolean("married", false);
               Log.d("MainActivity", "name is " + name);
               Log.d("MainActivity", "age is " + age);
               Log.d("MainActivity", "married is " + married);
           }
       });
   }
```   

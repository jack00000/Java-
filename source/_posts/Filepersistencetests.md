---
title: Filepersistencetest<CODE 2 chaper6>
date: 2017-7-28 20:13:10
tags: CODE 2
---

<h3>学习目的：文件存储</h3>

- 将数据保存到文件
```java
public void save(String inputText) {
       FileOutputStream out = null;
       BufferedWriter writer = null;
       try {
           out = openFileOutput("data", Context.MODE_PRIVATE);//MODE_PRIVATE指定同一文件名将被覆盖 openFileOutput（）获得FileOutputSteame对象 再借助它构建OutputStreamWriter对象 再使用OutputStreamWriter构建BufferedWriter对象
           writer = new BufferedWriter(new OutputStreamWriter(out));//
           writer.write(inputText);
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           try {
               if (writer != null) {
                   writer.close();
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
```   

- 范例
```java
public class MainActivity extends AppCompatActivity {

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.edit);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = edit.getText().toString();
        save(inputText);//掉用自定义方法 存储输入字符
    }

```    

- 从文件中读取数据
```java
public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");//获取FileInputStream对象data
            reader = new BufferedReader(new InputStreamReader(in));构建InputStreamReader对象reader
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
```

- 范例
```java
public class MainActivity extends AppCompatActivity {

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.edit);
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show();
        }
    }
```    

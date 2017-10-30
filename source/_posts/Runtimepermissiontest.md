---
title: runtimepermissiontest<CODE 2 chaper7>
date: 2017-7-30 13:10
tags: CODE 2
---


<h3>学习目的：1.了解权限及如何申请权限
![Markdown](http://i1.ciimg.com/595109/3d1e50447a82fe8a.png)
1.了解权限及如何申请权限
```java
Button makeCall = (Button) findViewById(R.id.make_call);
        makeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_CALL);//打电话
                    intent.setData(Uri.parse("tel:10086"));
                    startActivity(intent);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        });
```        

```java
<uses-permission android:name="android.permission.CALL_PHONE" />
```

但在高版本的Android系统会出错 权限问题 permission Denial  修改如下
```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button makeCall = (Button) findViewById(R.id.make_call);
        makeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //判断用户是否已授权 将返回值与PackageManager.PERMISSION_GRANTED比较
                if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                  //申请授权 Activity实例 String数组 请求码
                  ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }else{
                  call();
                }
            }
        });
    }

    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
//判断最后的授权结果
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
```

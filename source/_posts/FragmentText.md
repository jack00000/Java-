---
title: FragmentText<CODE 2 chaper4>
date: 2017-7-23 20:13:10
tags: CODE 2
---

<h3>学习目的：1.碎片的简单用法 2.动态添加碎片(创建布局需要加载) 3.碎片 活动之间进行通信 4.加载布局技巧<h3>
---
<h3>1.碎片的简单用法 </h3>
```java
类LeftFragment（创建布局需要加载  ，活动也是一样）
public class LeftFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,//重写onCreateView()方法
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_fragment, container, false);//将布局动态加载进来
        return view;
    }

}

类RightFragment（创建布局需要加载 ，活动也是一样）
public class RightFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,//重写onCreateView()方法
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.right_fragment, container, false);//将布局动态加载进来
        return view;
    }
布局
    <fragment
            android:id="@+id/left_fragment"
            android:name="com.example.fragmenttest.LeftFragment"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1" />

        <fragment
            android:id="@+id/right_fragment"
            android:name="com.example.fragmenttest.RightFragment"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="3" />

```

<h3>2.动态添加碎片</h3>
- 创建待添加的碎片实例
- 获取FragmentManager
- 开启一个事物
- 向容器内添加或替换碎片
- 提交事务

```java
布局
<fragment
        android:id="@+id/left_fragment"
        android:name="com.example.fragmenttest.LeftFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

<FrameLayout//这种布局把所有控件放在左上角
        android:id="@+id/right_layout"
        android:layout_width="0dp"
        android:layout_height="match_parent"        
        android:layout_weight="1">
</FrameLayout>

向FrameLayout里添加内容
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        replaceFragment(new RightFragment());//动态添加RightFragment
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                replaceFragment(new AnotherRightFragment());//动态添加AnotherRightFragment
                break;
            default:
                break;
        }
    }

  private void replaceFragment(Fragment fragment) {
       FragmentManager fragmentManager = getSupportFragmentManager();//获取FragmentManager
       FragmentTransaction transaction = fragmentManager.beginTransaction();//开启一个事物
       transaction.replace(R.id.right_layout, fragment);//向容器内替换碎片
       transaction.addToBackStack(null);//模拟返回栈（碎片中 按back会直接退出 不会返回上一步）
       transaction.commit();//提交事务
  }

}
```

<h3>3.碎片 活动之间进行通信</h3>
- （从布局文件获取碎片实例）调用FragmentManager的findFragmentById() 可以在活动得到相应碎片实例 然后就可调用
```Android
RightFragment rightFragment=(RightFragment)getFragmentManager().findFragmentById(R.id.right_fragment);
```

- （碎片调用实例） 用getActivity（）
```Android
MainActivity mainActivity=(MainActivity)getActivity();
```


<h3>4.碎片的生命周期</h3>
- onAtach()
- onCreateView()
- omActivityCreated()
- onDestroyView()
- onDeath()


```java
public class RightFragment extends Fragment {

    public static final String TAG = "RightFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.right_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

}
```

<h3>4.加载布局技巧</h3>
- 1.限定符small nomal large xlarge(单页手机模式 双页平板模式)  新建layout-large文件夹 在下面创建布局 运行时根据设备自动识别

- 2.分辨率ldpi hdpi xhdpi xxhdpi  新建layout-sw600dp文件夹 然后新建布局  运行时根据设备自动识别

- 3.方向 land port 新建layout-land文件夹  然后新建布局  运行时根据设备自动识别

---
title: ListView<CODE 2 chaper3>
date: 2017-7-20 20:13:10
tags: CODE 2
---

<h3>学习目的：1.了解ListView（列表）的用法 2.定制listview的界面(自定义适配器) 3.提升ListView的运行效率
4.ListView的点击事件

<h3>ListView（列表）的用法</h3>
```Android
ListView需要大量数据  来源数据库 或自定义
public class MainActivity extends AppCompatActivity {

    private String[] data={"a","b","c","b","c","b","c","b","c","b","c","b","c"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArraryAdapter<String>adapter = new ArraryAdapter(MainActivity.this, R.layout.fruit_item, data);//第一个参数 上下文  布局ID 数据名//ArraryAdapter数组适配器
        ListView listView = (ListView) findViewById(R.id.list_view);//导入布局id
        listView.setAdapter(adapter);//启动适配器
      }
    }
```    

<h3>定制listview的界面</h3>

<img src='http://img.027cgb.cn/20170722/20177222181775731906.png' />

```Android
如：列表每一项包含图片及文字
详解每一步：
1.两个布局activity_main 及fruit_item(简单，不赘述)

2.fruit类（简单，不赘述）
public class Fruit {

    private String name;

    private int imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

}

3.FruitAdaper类
public class FruitAdapter extends ArrayAdapter<Fruit> {//继承ArrayAdaper 从而使之能调用适配器需要的函数 //泛型为fruit类

    private int resourceId;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {//上下文 布局id 列表对象
        super(context, textViewResourceId, objects);//调用父类构造函数   及FruitAdapter()等同于ArrayAdaper()
        resourceId = textViewResourceId;//之所以另定义一个变量来获得textViewResourceId,是怕使用过程中将之改变
    }
//  重写getView方法(获取到视图类对象的指针，并转换成CAdaptoneViewView   的指针)
  public View getView(int position, View convertView, ViewGroup parent) {/ /每个子项滚动到屏幕内都会被调用
        Fruit fruit = getItem(position); // 获取当前项的Fruit实例
        View view=layoutInflater.from(getContext()).inflate(resourceId,parent,false);//为子项加载我们传入的布局
        ImageView fruitImage=(ImageView)view.findViewById(R.id.fruit_image);//ImageView内部类
        TextView fruitName=(TextView)view.findViewById(R.id.fruit_name);//TextView内部类
        fruitImage.setImageResouceId(fruit.getImageId());
        fruitName.setText(fruit.getName());
        return view;
    }

```
![Markdown](http://i4.piimg.com/595109/3e87d1af99d7521b.png)

4.MianActivity类
```Android
public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<Fruit>();//用ArrayList（）方法声明一个fruitList列表 指定泛型为Fruit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits(); // 初始化水果数据
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);//第一个参数 传入水果对象  布局ID 水果列表
        ListView listView = (ListView) findViewById(R.id.list_view);//导入布局id
        listView.setAdapter(adapter);//启动适配器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//给listview设置监听器
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();//设置逻辑
            }
        });
    }

    private void initFruits() {
            for (int i = 0; i < 2; i++) {//循环两次是出于谨慎
                Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
                fruitList.add(apple);
                Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
                fruitList.add(banana);
                Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
                fruitList.add(orange);
                Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
                fruitList.add(watermelon);
                Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
                fruitList.add(pear);
                Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
                fruitList.add(grape);
                Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
                fruitList.add(pineapple);
                Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
                fruitList.add(strawberry);
                Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
                fruitList.add(cherry);
                Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
                fruitList.add(mango);
            }
        }
```

<h3>3.ListView的优化</h3>
```Android
需要优化的点：1.getView每一次滑动都要将布局重载！（效率低）
2.每次getView（）都会通过findViewById获取一次控件实例
优化思路 使用convertView参数（布局缓存重用） 新建viewHolder集合类
优化后代码：
public View getView(int position, View convertView, ViewGroup parent) {//每个子项滚动到屏幕内都会被调用
       Fruit fruit = getItem(position); // 获取当前项的Fruit实例
       View view;
       ViewHolder viewHolder;
       if (convertView == null) {
           view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
           viewHolder = new ViewHolder();
           viewHolder.fruitImage = (ImageView) view.findViewById (R.id.fruit_image);
           viewHolder.fruitName = (TextView) view.findViewById (R.id.fruit_name);
           view.setTag(viewHolder); // 将ViewHolder存储在View中
       } else {
           view = convertView;
           viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
       }
       viewHolder.fruitImage.setImageResource(fruit.getImageId());
       viewHolder.fruitName.setText(fruit.getName());
       return view;
   }

   class ViewHolder {

       ImageView fruitImage;

       TextView fruitName;

   }
```

   <h3>4.ListView的点击事件</h3>
```Android
简单，不赘述。
   protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);
           initFruits(); // 初始化水果数据
           FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);//第一个参数 传入水果对象  布局ID 水果列表
           ListView listView = (ListView) findViewById(R.id.list_view);//导入布局id
           listView.setAdapter(adapter);//启动适配器
           listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//给listview设置监听器
               @Override
               public void onItemClick(AdapterView<?> parent, View view,
                                       int position, long id) {
                   Fruit fruit = fruitList.get(position);
                   Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();//设置逻辑
               }
           });
       }
```

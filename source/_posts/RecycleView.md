---
title: RecycleView<CODE 2 chaper3>
date: 2017-7-21 20:13:10
tags: CODE 2
---

<h3>学习目的：1.RecycleView的基本用法 2.实现横向列表  3.实现瀑布流 4.RecycleView的点击事件（需添加依赖库）

---
![Markdown](http://i1.ciimg.com/595109/7853ee0ff4b9f52e.png)
<h3>1.RecycleView的基本用法（不止可实现列表 还可实现瀑布流等） </h3>
```Android
添加一行代码（依赖）
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:24.2.1'
    compile 'com.android.support:recyclerview-v7:24.2.1'//recycleview定义在suprot库中  需添加依赖库
}

创建RecycleView布局
<android.support.v7.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

重写FruitAdaper类（思路：ViewHolder获取子项布局ID再设置监听器）
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{// 中括号里一般是类名 泛型

    private List<Fruit> mFruitList;//定义全局变量

    static class ViewHolder extends RecyclerView.ViewHolder {//RecycleView有ViewHolder内部类
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {//子项的布局
            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "you clicked image " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {//对子项数据进行赋值
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }


    MainActivity类
    public class MainActivity extends AppCompatActivity {

        private List<Fruit> fruitList = new ArrayList<Fruit>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            initFruits();//初始化数据 自定义方法
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);//指定布局方式 可以是横向 竖向 此为竖向
            recyclerView.setLayoutManager(layoutManager);//启动自定义布局
            FruitAdapter adapter = new FruitAdapter(fruitList);
            recyclerView.setAdapter(adapter);
        }
```

<h3>2.实现横向列表（只用加一行代码）</h3>
```Android
RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
LinearLayoutManager layoutManager=new LinearLayoutManager(this);//指定布局方式 可以是横向 竖向 此为竖向
layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//横向线性布局  默认为竖向
recyclerView.setLayoutManager(layoutManager);//启动自定义布局
FruitAdapter adapter = new FruitAdapter(fruitList);
recyclerView.setAdapter(adapter);
```

<h3> 3.实现瀑布流 (只用加一行代码)</h3>
```Android
RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
LinearLayoutManager layoutManager=new LinearLayoutManager(this);//指定布局方式 可以是横向 竖向 此为竖向
StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);//瀑布流  默认为竖向
recyclerView.setLayoutManager(layoutManager);//启动自定义布局
FruitAdapter adapter = new FruitAdapter(fruitList);
recyclerView.setAdapter(adapter);

```

<h3>4.RecycleView的点击事件</h3>
```Android
不同于ListView（setOnItemClickListener） RecycleView没有点击事件

static class ViewHolder extends RecyclerView.ViewHolder {//RecycleView有ViewHolder内部类
    View fruitView;//保存最外层子布局实例
    ImageView fruitImage;
    TextView fruitName;

    public ViewHolder(View view) {//子项的布局
        super(view);
        fruitView = view;//保存最外层子布局实例
        fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
        fruitName = (TextView) view.findViewById(R.id.fruit_name);
    }
}

public FruitAdapter(List<Fruit> fruitList) {
    mFruitList = fruitList;
}

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
    final ViewHolder holder = new ViewHolder(view);
    holder.fruitView.setOnClickListener(new View.OnClickListener() {//给RecycleViewS设置点击事件
        @Override
        public void onClick(View v) {
            int position = holder.getAdapterPosition();
            Fruit fruit = mFruitList.get(position);
            Toast.makeText(v.getContext(), "you clicked view " + fruit.getName(), Toast.LENGTH_SHORT).show();
        }
    });
    holder.fruitImage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = holder.getAdapterPosition();
            Fruit fruit = mFruitList.get(position);
            Toast.makeText(v.getContext(), "you clicked image " + fruit.getName(), Toast.LENGTH_SHORT).show();
        }
    });
    return holder;
}
```

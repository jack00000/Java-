---
title: FragmentBestPractise<CODE 2 chaper4>
date: 2017-7-24 20:13:10
tags: CODE 2
---


<h3>学习目的：制作简易版新闻应用</h3>
![Markdown](http://i4.piimg.com/595109/afe937df13c4e6f6.png)
1.为RecycleView添加依赖
```java
 compile 'com.android.support:recyclerview-v7:24.2.1'
```

2.新建News类
```java
public class News {

    private String title;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
```

3.新建布局news_content_frag
```java
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:id="@+id/visibility_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:visibility="invisible" >

        <TextView
            android:id="@+id/news_title"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:padding="10dp"
            android:textSize="20sp" />

        <View//分割线
            android:layout_width="match_parent"
            android:layout_height="1dp"
            android:background="#000" />

        <TextView
            android:id="@+id/news_content"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"
            android:padding="15dp"
            android:textSize="18sp" />

    </LinearLayout>

    <View
        android:layout_width="1dp"
        android:layout_height="match_parent"
        android:layout_alignParentLeft="true"
        android:background="#000" />

</RelativeLayout>
```
4.新建NewsContentFragment //加载布局 套路
```java
public class NewsContentFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_frag, container, false);//R.layout  布局文件
        return view;
    }

    public void refresh(String newsTitle, String newsContent) {
        View visibilityLayout = view.findViewById(R.id.visibility_layout);//R.id   布局文件中的子布局 如LinerLayout
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView newsTitleText = (TextView) view.findViewById (R.id.news_title);
        TextView newsContentText = (TextView) view.findViewById(R.id.news_content);
        newsTitleText.setText(newsTitle); // 刷新新闻的标题
        newsContentText.setText(newsContent); // 刷新新闻的内容
    }

}
```

5.新建newsContentAcyivity及其布局文件news_content

- news_content
```java
<fragment
        android:id="@+id/news_content_fragment"
        android:name="com.example.fragmentbestpractice.NewsContentFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        />
```

- NewsContentActivity类
```java

public class NewsContentActivity extends AppCompatActivity {

    public static void actionStart(Context context, String newsTitle, String newsContent) {//启动活动的最佳写法
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        String newsTitle = getIntent().getStringExtra("news_title"); // 获取传入的新闻标题
        String newsContent = getIntent().getStringExtra("news_content"); // 获取传入的新闻内容
        NewsContentFragment newsContentFragment = (NewsContentFragment)getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle, newsContent); // 刷新NewsContentFragment界面
    }

}
```        


<h3>6.新建layout-sw600dp文件夹 新建activity_main文件</h3>
- 双页
```java
<fragment
       android:id="@+id/news_title_fragment"
       android:name="com.example.fragmentbestpractice.NewsTitleFragment"
       android:layout_width="0dp"
       android:layout_height="match_parent"
       android:layout_weight="1" />

   <FrameLayout
       android:id="@+id/news_content_layout"
       android:layout_width="0dp"
       android:layout_height="match_parent"
       android:layout_weight="3" >

       <fragment
           android:id="@+id/news_content_fragment"
           android:name="com.example.fragmentbestpractice.NewsContentFragment"
           android:layout_width="match_parent"
           android:layout_height="match_parent" />
   </FrameLayout>
```   

<h3>7.在NewsTitleFragment类中新建RecyclerView的适配器内部类NewsAdaper</h3>
- 适配器代码千篇一律，只是参数有变化
```java
class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView newsTitleText;

            public ViewHolder(View view) {
                super(view);
                newsTitleText = (TextView) view.findViewById(R.id.news_title);
            }

        }

        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPane) {
                        NewsContentFragment newsContentFragment = (NewsContentFragment)
                                getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(), news.getContent());
                    } else {
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

    }

}
```

<h3>8.NewsTitleFragment类</h3>
```java
public class NewsTitleFragment extends Fragment {

    private boolean isTwoPane;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        RecyclerView newsTitleRecyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true; // 可以找到news_content_layout布局时，为双页模式
        } else {
            isTwoPane = false; // 找不到news_content_layout布局时，为单页模式
        }
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            News news = new News();
            news.setTitle("This is news title " + i);
            news.setContent(getRandomLengthContent("This is news content " + i + ". "));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomLengthContent(String content) {//设置随机信息
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }
}
```

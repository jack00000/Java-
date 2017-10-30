---
title: materialtest6(最佳UI体验)<CODE 2 chaper12>
date: 2017-8-11 20:13:10
tags: CODE 2
---

<h3>学习目的：3.可折叠的标题栏(逻辑编写) 4.充分利用系统状态栏空间（上拉时背景图与状态栏融合）
FruitActivity
```java
public class FruitActivity extends AppCompatActivity {

    public static final String FRUIT_NAME = "fruit_name";

    public static final String FRUIT_IMAGE_ID = "fruit_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        TextView fruitContentText = (TextView) findViewById(R.id.fruit_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        String fruitContent = generateFruitContent(fruitName);
        fruitContentText.setText(fruitContent);
    }

    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
```

FruitAdapter
```java
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{

  //...
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Intent intent = new Intent(mContext, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

  //...
}
```
![Markdown](http://i2.bvimg.com/595109/9969140c788f23de.png)
![Markdown](http://i2.bvimg.com/595109/979fb8917ee7e4f0.png)
![Markdown](http://i2.bvimg.com/595109/694b5a2a6b82b311.png)
![Markdown](http://i2.bvimg.com/595109/429607e5ed515c13.png)


充分利用系统状态栏空间（上拉时背景图与状态栏融合）
activity_fruit
```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true">//上拉时背景图与状态栏融合 还需设置主题透明

    <android.support.design.widget.AppBarLayout
        android:id="@+id/appBar"
        android:layout_width="match_parent"
        android:layout_height="250dp"
        android:fitsSystemWindows="true">//上拉时背景图与状态栏融合 还需设置主题透明

        <android.support.design.widget.CollapsingToolbarLayout
            android:id="@+id/collapsing_toolbar"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
            android:fitsSystemWindows="true"//上拉时背景图与状态栏融合 还需设置主题透明
            app:contentScrim="?attr/colorPrimary"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">

            <ImageView
                android:id="@+id/fruit_image_view"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:scaleType="centerCrop"
                android:fitsSystemWindows="true"
                app:layout_collapseMode="parallax" />

          //...
</android.support.design.widget.CoordinatorLayout>

```
还需设置主题透明 创建New->Directory->values-v21->New->Values resource file->style.xml
```xml
<resources>

    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>

    <style name="FruitActivityTheme" parent="AppTheme">
    </style>

</resources>
```

AndroidMaifest.xml
```xml
<activity
            android:name=".FruitActivity"
            android:theme="@style/FruitActivityTheme">
        </activity>

```

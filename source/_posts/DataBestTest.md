---
title: DataBestTest<CODE 2 chaper5>
date: 2017-7-29 20:13:10
tags: CODE 2
---
<h3>学习目的：1.创建数据库 2.升级数据库 3.对数据库的四种操作 4.使用SQL操作数据库 5.使用LitePal操作数据库

1.创建名为BookStore.db数据库

```java
//MyDatabaseHelper
public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);

        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //MainActivity
    public class MainActivity extends AppCompatActivity {

        private MyDatabaseHelper dbHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
            Button createDatabase = (Button) findViewById(R.id.create_database);
            createDatabase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper.getWritableDatabase();
                }
            });    
}
```

2.升级数据库(给它加东西) onUpgrade()
建表Category
```java
public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";

            public void onCreate(SQLiteDatabase db) {
                db.execSQL(CREATE_BOOK);
                db.execSQL(CREATE_CATEGORY);
                Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
            }
```

onUpgrade()方法
```java
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("drop table if exists Book");
       db.execSQL("drop table if exists Category");
       onCreate(db);
   }
```

MianActivity
```java

protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);//将数据库版本号指定为2
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

```

3.对数据库的四种操作
- Create添加 insert（）方法
- Retrieve查询 Updata（）方法
- Updata更新
- Delete删除

1.Create添加 insert（）方法
```java
Button addData = (Button) findViewById(R.id.add_data);
       addData.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SQLiteDatabase db = dbHelper.getWritableDatabase();
               ContentValues values = new ContentValues();//提供put（）方法
               // 开始组装第一条数据
               values.put("name", "The Da Vinci Code");
               values.put("author", "Dan Brown");
               values.put("pages", 454);
               values.put("price", 16.96);
               db.insert("Book", null, values); // 插入第一条数据  表名 给空的列赋值null ContentValues对象（提供put（）方法）
               values.clear();
               // 开始组装第二条数据
               values.put("name", "The Lost Symbol");
               values.put("author", "Dan Brown");
               values.put("pages", 510);
               values.put("price", 19.95);
               db.insert("Book", null, values); // 插入第二条数据
           }
       });
```

2.Updata更新 Updata（）方法
```java
Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
                db.update("Book", values, "name = ?", new String[] { "The Da Vinci Code" });//更新name =The Da Vinci Code的这一行
                //将名字为he Da Vinci Code的price改为10.99
            }
        });
```

3.Delete删除
```java
Button deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book", "pages > ?", new String[] { "500" });//删除页数超过500页的的boo表中的数据
            }
        });

```

4.Retrieve查询 query（）方法
```java
Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // 查询Book表中所有的数据
                Cursor cursor = db.query("Book", null, null, null, null, null, null);//调用getColumnIndex方法
                if (cursor.moveToFirst()) {
                    do {
                        // 遍历Cursor对象，取出数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
```

使用SQL操作数据库
- 添加数据
```java
db.execSQL("insret into Book (name,author,pages,price)values(?,?,?,?)",new String[]{"The Da Vinci Code","Dan Brown ","545","16.96"});
db.execSQL("insret into Book (name,author,pages,price)values(?,?,?,?)",new String[]{"The Lost Symbol","Dan Brown ","545","16.96"});
```

- 更新数据
```java
db.execSQL("updata Book set price=? where name=?",new String[]{"10.99","The Da Vinci Code"});
```

- 删除数据
```java
db.execSQL("delete from Book where pages>?,new String[]{"500"}");
```

- 查询数据
```java
db.rawQuery("select * from Book",null);
```




5.使用LitePal操作数据库

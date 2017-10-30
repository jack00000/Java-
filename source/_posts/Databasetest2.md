---
title: databasetest2<CODE 2 chaper7>
date: 2017-7-30 13:10
tags: CODE 2
---

<h3>学习目的:1.实现跨程序数据共享 1.新建内容提供器的两种方法</h3>
1.新建内容提供器（通过新建一个类继承ContentProvider类的方式创建）重写如下方法
```java
public class DatabaseProvider extends ContentProvider {


    @Override
    //初始化内容提供器时调用  这时候数据库完成创建或升级操作
    public boolean onCreate() {

        return false;
    }

    @Override
    //Uri确定那张表 projection确定查询那些列    selection与selectionArgs约束查询哪些行    sortOrder对结果进行排序最后存到Cursor对象中
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        return null;
    }

    @Override
    //Uri指定要添加的表  values待添加的数据
    public Uri insert(Uri uri, ContentValues values) {

        return null;
    }

    @Override
    //uri确定哪张表    values新数据保存在此  selection 与 selectionArgs约束更新哪些行
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        return 0;
    }

    @Override
    //uri确定哪张表  selection 与 selectionArgs约束删除哪些行
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        return 0;
    }

    @Override
    //根据传入的uri返回相应的MIME
    public String getType(Uri uri) {

        return null;
    }
```
2.重点解析uri参数
标准的URI写法   
```java
//表示调用方期望访问的是com.eaxmple.app这个应用的tabel表的数据
content://com.eaxmple.app.provider/tablel
//可以加id
//表示调用方期望访问的是com.eaxmple.app这个应用的tabel表中id为1的数据
content://com.eaxmple.app.provider/tablel/1
```

URI格式   *  #
```java
//表示匹配任意表内容
content://com.eaxmple.app.provider/*
//表示匹配tablel表任意一行内容
content://com.eaxmple.app.provider/tablel/#
```

3.UriMatcher类的math（）方法
```java
public class DatabaseProvider extends ContentProvider {

    public static final int BOOK_DIR = 0;

    public static final int BOOK_ITEM = 1;

    public static final int CATEGORY_DIR = 2;

    public static final int CATEGORY_ITEM = 3;

    public static final String AUTHORITY = "com.example.databasetest.provider";

    private static UriMatcher uriMatcher;

    private MyDatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.eaxmple.app.provider", "book", BOOK_DIR);//内容提供器 表名 使数据与之关联
        uriMatcher.addURI("com.eaxmple.app.provider", "book/#", BOOK_ITEM);
        uriMatcher.addURI("com.eaxmple.app.provider", "category", CATEGORY_DIR);
        uriMatcher.addURI("com.eaxmple.app.provider", "category/#", CATEGORY_ITEM);
    }
    @Override
        public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
            // 查询数据

            switch (uriMatcher.match(uri)) {
                case BOOK_DIR:
                    cursor = db.query("Book", projection, selection, selectionArgs, null, null, sortOrder);
                    break;
                case BOOK_ITEM:
                    String bookId = uri.getPathSegments().get(1);
                    cursor = db.query("Book", projection, "id = ?", new String[] { bookId }, null, null, sortOrder);
                    break;
                case CATEGORY_DIR:
                    cursor = db.query("Category", projection, selection, selectionArgs, null, null, sortOrder);
                    break;
                case CATEGORY_ITEM:
                    String categoryId = uri.getPathSegments().get(1);
                    cursor = db.query("Category", projection, "id = ?", new String[] { categoryId }, null, null, sortOrder);
                    break;
                default:
                    break;
            }
            return cursor;
        }

1.新建内容提供器（AS快捷方式创建）
```
com.example.broadcasttest->new->other->Content Provider
//重写几个方法

```

修改databaseProvider

```java
//继承内容提供器
public class DatabaseProvider extends ContentProvider {

    public static final int BOOK_DIR = 0;

    public static final int BOOK_ITEM = 1;

    public static final int CATEGORY_DIR = 2;

    public static final int CATEGORY_ITEM = 3;

    public static final String AUTHORITY = "com.example.databasetest.provider";

    private static UriMatcher uriMatcher;

    private MyDatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
    }

    @Override
    //创建一个yDatabaseHelper实例  这时候数据库完成创建或升级操作
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext(), "BookStore.db", null, 2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // 查询数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                cursor = db.query("Book", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book", projection, "id = ?", new String[] { bookId }, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Category", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category", projection, "id = ?", new String[] { categoryId }, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // 添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("Book", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/book/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert("Category", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/category/" + newCategoryId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // 更新数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                updatedRows = db.update("Book", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updatedRows = db.update("Book", values, "id = ?", new String[] { bookId });
                break;
            case CATEGORY_DIR:
                updatedRows = db.update("Category", values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updatedRows = db.update("Category", values, "id = ?", new String[] { categoryId });
                break;
            default:
                break;
        }
        return updatedRows;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // 删除数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                deletedRows = db.delete("Book", selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Book", "id = ?", new String[] { bookId });
                break;
            case CATEGORY_DIR:
                deletedRows = db.delete("Category", selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Category", "id = ?", new String[] { categoryId });
                break;
            default:
                break;
        }
        return deletedRows;
    }

    @Override
    //所有内容提供器都必须提供的方法 用于获取URI对象所对应的MIME类型    MIME由三个部分组成
    //1.必须以vnd开头 2.uri以路径结尾则android.cursor.dir/  以id结尾则android.cursor.item/ 3.最后接上vnd.<authority>.<path>
    //例如：URI：content://com.eaxmple.app.provider/tabel
    //MIME:vnd.android.sursor.dir/vnd.com.eaxmple.app.provider.tabel
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest. provider.book";//返回MIME
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest. provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest. provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest. provider.category";
        }
        return null;
    }

```



mydatabaseHalper类
```java
public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";

    public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
       // Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }

}
```


MianActivity类
```java
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
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                // 开始组装第一条数据
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values); // 插入第一条数据
                values.clear();
                // 开始组装第二条数据
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values); // 插入第二条数据
            }
        });
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
                db.update("Book", values, "name = ?", new String[] { "The Da Vinci Code" });
            }
        });
        Button deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book", "pages > ?", new String[] { "500" });
            }
        });
        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // 查询Book表中所有的数据
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
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
    }

}
```

---
title: LitePal<CODE 2 chaper5>
date: 2017-7-29 20:13:10
tags: CODE 2
---

<h3>学习目的：1.使用来源库LitePal(使用ORM 对象关系映射)创建数据库 2. 升级数据库(litepal会自动保存更新前的数据) 3.使用LitePal添加数据

1.使用来源库LitePal
- 配置LitePal
```java
 compile 'org.litepal.android:core:1.3.2'
```
- 配置litepal.xml文件
```java
<litepal>
    <dbname value="BookStore" ></dbname>

    <version value="2" ></version>

    <list>
        <mapping class="com.example.litepaltest.Book"></mapping>
        <mapping class="com.example.litepaltest.Category"></mapping>
    </list>
</litepal>
```
- 用面向对象思维实现建表功能（不用和SQL打交道）
```java
import org.litepal.crud.DataSupport;

public class Book  {//Book对应Book表

    private int id;

    private String author;

    private double price;

    private int pages;

    private String name;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }



    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setPress(String press) {
        this.press = press;
    }

}
```

- 将Book类映射到模型列表中
```java
<litepal>
    <dbname value="BookStore" ></dbname>

    <version value="2" ></version>

    <list>
        <mapping class="com.example.litepaltest.Book"></mapping>

    </list>
</litepal>
```

只需要任意一次数据库操作 BookSrore.db数据库可自动创建
```java
Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });
```


2. 升级数据库(litepal会自动保存更新前的数据)
```java
//Book类
  private String press;


  public String getPress() {
      return press;
  }

  public void setPrice(double price) {
      this.price = price;
  }

```
- 新建表Category
```java
//Category类
public class Category {

    private int id;

    private String categoryName;

    private int categoryCode;

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

}
```
- 将Category类映射到模型列表中

```java

<mapping class="com.example.litepaltest.Category"></mapping>

```

3.使用LitePal添加数据
- 使Book类继承DataSupport
```java
public class Book extends DataSupport{
  ...
}
```
- 添加数据
```java
Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();//save()由DataSupport继承而来
            }
        });
```        

4.使用LitePal更新数据(1.对已存储（model.isSaved()方法）对象重新设值 2.)
```java
Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Lost Symol");
                book.setPrice(10.99);
                book.save();
                book.setPrice(14.95);//对象重新设值
                book.save();
            }
        })；

```            

更巧妙地方法
```java
Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?", "The Lost Symbol", "Dan Brown");
            }
        });
```        

将数据更新成默认值
```java
Book book=new Book();
book.setTofault("page");//setTofault()方法将数据更新成默认值
book.updateAll();
```



5.使用LitePal删除数据 delete()方法
```java
Button deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class, "price < ?", "15");
            }
        });

```

6.查询数据 查询API：findALL findFrist  findLast
```java
Button queryButton = (Button) findViewById(R.id.query_data);
       queryButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               List<Book> books = DataSupport.findAll(Book.class);
               for (Book book: books) {
                   Log.d("MainActivity", "book name is " + book.getName());
                   Log.d("MainActivity", "book author is " + book.getAuthor());
                   Log.d("MainActivity", "book pages is " + book.getPages());
                   Log.d("MainActivity", "book price is " + book.getPrice());
                   Log.d("MainActivity", "book press is " + book.getPress());
               }
           }
       });
```

查询API
```java
//select()指定查询哪几列
List<Book>books=DataSupport.select("name","author").find(Book.class);
//where()指定查询约束条件
List<Book>books=DataSupport.where("pages>?","400").find(Book.class);
//order()指定结果排序方式
List<Book>books=DataSupport.order("price desc").find(Book.class);
//limit()查询结果数量
List<Book>books=DataSupport.limit(3).find(Book.class);
//offset()查询结果偏移量
List<Book>books=DataSupport.limit(3).offset().find(Book.class);


```

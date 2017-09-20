package com.example;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Created by 15581 on 2017/9/20.
 */

public class Application {
    public static void main(String arg[]){
        BookNameList oldBookList=new BookNameList();
        oldBookList.setBookName();
        Enumeration bookenum=oldBookList.getEnumeration();
        IteratorAdapter adapter=new IteratorAdapter(bookenum);
        NewBookNameList newBookList=new NewBookNameList(adapter);
        newBookList.setBookName();
        System.out.println("导入到新系统中的图书立标");
        newBookList.getBookName();
    }
}

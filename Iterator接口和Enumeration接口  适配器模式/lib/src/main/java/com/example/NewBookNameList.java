package com.example;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by 15581 on 2017/9/20.
 */

public class NewBookNameList {
    LinkedList<String>booklist;
    Iterator iterator;
    NewBookNameList(Iterator iterator){
        booklist=new LinkedList<String>();
        this.iterator=iterator;
    }
    public void setBookName(){
        while (iterator.hasNext()){
            String name=(String)iterator.next();
            booklist.add(name);
        }
    }
    public void getBookName(){
        Iterator<String>iter=booklist.iterator();
        while (iter.hasNext()){
            String name=iter.next();
            System.out.println(name);
        }
    }
}

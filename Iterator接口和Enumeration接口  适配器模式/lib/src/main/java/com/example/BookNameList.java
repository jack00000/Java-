package com.example;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by 15581 on 2017/9/20.
 */

public class BookNameList {
    private Vector<String>vector;
    private Enumeration bookenum;
    BookNameList(){
        vector=new Vector<String>();
    }
    public void  setBookName(){
        vector.add("java程序设计");
        vector.add("j2me程序设计");
        vector.add("xml程序设计");
        vector.add("jsp程序设计");
    }
    public Enumeration getEnumeration(){
        return vector.elements();
    }
}

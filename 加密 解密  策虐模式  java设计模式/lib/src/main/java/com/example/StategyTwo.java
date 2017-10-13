package com.example;

/**
 * Created by 15581 on 2017/9/22.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;




/**
 * Created by 15581 on 2017/9/21.
 */
import  java.io.*;
public class StategyTwo implements EncryptStrategy {
    String password;
    StategyTwo(){
        this.password="i love this game";
    }
    StategyTwo(String password){
        if(password.length()==0)
            this.password="i love game";
        this.password=password;
    }
    public  void  encryptFile(File file) {
        try {
            byte[] a = password.getBytes();
            FileInputStream in = new FileInputStream(file);
            long length = file.length();
            byte[] c = new byte[(int) length];
            int m = in.read(c);
            for (int k = 0; k < m; k++) {
                int n = c[k]^ a[k % a.length];//解密
                c[k] = (byte) n;
            }
            in.close();
            FileOutputStream out = new FileOutputStream(file);
            out.write(c, 0, m);
            out.close();
        } catch (IOException exp) {
        }
    }
    public String decryptFile(File file) {
        try {
            byte[] a = password.getBytes();
            long length = file.length();
            FileInputStream in = new FileInputStream(file);
            byte[] c = new byte[(int) length];
            int m = in.read(c);
            for (int k = 0; k < m; k++) {
                int n = c[k]^a[k % a.length];
                c[k] = (byte) n;
            }
            in.close();
            return new String(c, 0, m);
        } catch (IOException exp) {
            return exp.toString();
        }

    }
}
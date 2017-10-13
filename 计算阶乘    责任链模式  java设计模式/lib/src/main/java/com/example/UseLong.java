package com.example;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by 15581 on 2017/9/22.
 */

    public class UseLong implements Handler{
    private Handler handler;
    private long result=1;
    public void computerMultiply(String number){
        try{
            long n= Long.parseLong(number);
            long i=1;
            while (i<=n){
                result=result*i;
                if(result<=0){
                    System.out.println("超出能力");
                    handler.computerMultiply(number);
                    return;
                }
                i++;
            }
            System.out.println(number+"的阶乘"+result);
        }
        catch (NumberFormatException exp){
            System.out.println(exp.toString());
        }


    }
    public void setNextHandler(Handler handler){
        this.handler=handler;
    }
}

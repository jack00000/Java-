package com.example;

import java.io.IOException;

/**
 * Created by 15581 on 2017/9/22.
 */

public class UseInt implements Handler {
    private Handler handler;
    private int result=1;
    public void computerMultiply(String number){
        try{
            int n=Integer.parseInt(number);
            int i=1;
            while (i<=n){
                result=result*i;
                if(result<=0){
                    System.out.println("超出能力范围");
                    handler.computerMultiply(number);
                    return;
                }
                i++;
            }
            System.out.println(number+"阶乘"+result);
        }
        catch (Exception exp){
            System.out.println(exp.toString());
        }
    }

    public void setNextHandler(Handler handler){
        this.handler=handler;
    }
}

package com.example;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.math.BigInteger.ONE;

/**
 * Created by 15581 on 2017/9/22.
 */

public class UseBigInteger implements Handler{
    private Handler handler;
    private BigInteger result=new BigInteger("1");
    public  void computerMultiply(String number){
        try{
            BigInteger n=new BigInteger(number);
            BigInteger ONE=new BigInteger("1");
            BigInteger i=ONE;
            while (i.compareTo(n)<0){
                result=result.multiply(i);
                i=i.add(ONE);

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

package com.example;

/**
 * Created by 15581 on 2017/9/23.
 */

public class Application {
    private Handler useInt,useLong,useBig;
    public void  createChain(){
        useInt=new UseInt();
        useLong=new UseLong();
        useBig=new UseBigInteger();
        useInt.setNextHandler(useLong);
        useLong.setNextHandler(useBig);

    }
    public void reponseClient(String number){
        useInt.computerMultiply(number);
    }
    public static void main(String args[]){
        Application application=new Application();
        application.createChain();
        application.reponseClient("32");
    }
}

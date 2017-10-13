package com.example;



public class DepositSlip1 implements DepositSlip{
    String clientNumber;
    String clientName;
    int money;
    DepositSlip1(String clientNumber,String clientName,int money ){
        this.clientName=clientName;
        this.clientNumber=clientNumber;
        this.money=money;
    }

    @Override
    public String getBankName() {
        return "中国银行";
    }

    public String getClientNumber() {
        return clientNumber;
    }


    public String getClientName() {
        return  clientName;
    }

    public int getAmountOfMoney() {
        return money;
    }
}

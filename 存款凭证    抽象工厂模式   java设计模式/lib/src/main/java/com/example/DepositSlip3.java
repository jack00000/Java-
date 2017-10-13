package com.example;

public class DepositSlip3 implements DepositSlip {
    String clientNumber;
    String clientName;
    int money;
    DepositSlip3(String clientNumber,String clientName,int money ){
        this.clientName=clientName;
        this.clientNumber=clientNumber;
        this.money=money;
    }

    @Override
    public String getBankName() {
        return "交通银行";
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

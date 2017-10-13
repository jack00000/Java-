package com.example;

public class ChinaConstructionBank extends Bank{
    @Override
    public DepositSlip createDepositSlip(String number, String name, int money) {
        return new DepositSlip1(number, name, money);


    }
    public Seal createSeal(){
        return new SealOne();
    }
}

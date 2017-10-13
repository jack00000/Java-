package com.example;

public class BankOfCommunication extends Bank {
    @Override
    public DepositSlip createDepositSlip(String number,String name,int money){
        return new DepositSlip3(number,name,money);
    }


    public Seal createSeal() {
        return new SealThree();
    }
}

package com.example;

public class Application {
    public static void main(String args[]){
        AShowDespositSlip showDespositSlip=new  AShowDespositSlip();
        Bank bank=new ChinaBank();
        showDespositSlip.showDepositSlip(bank,"82738484","张三",3000);
        showDespositSlip.setLocation(20,20);
        showDespositSlip=new AShowDespositSlip();
        bank=new ChinaConstructionBank();
        showDespositSlip.showDepositSlip(bank,"1541545","李四",5000);
        showDespositSlip.setLocation(240,20);
        showDespositSlip=new AShowDespositSlip();
        bank=new BankOfCommunication();
        showDespositSlip.showDepositSlip(bank,"25534525","王五",5512);
        showDespositSlip.setLocation(460,20);

    }
}

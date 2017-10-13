package com.example;

import javax.swing.*;
import java.awt.*;

public class AShowDespositSlip extends JPanel {
    DepositSlip depositSlip;
    Seal seal;
    boolean boo;
    JLabel label;
    Image image;
    JFrame frame;
    AShowDespositSlip(){
        setLayout(null);
        setSize(200,200);
        label=new JLabel();
        add(label);
        frame=new JFrame();
        frame.add(this);

    }
    public void showDepositSlip(Bank bank,String number,String name,int money){
        depositSlip=bank.createDepositSlip(number,name,money);
        seal=bank.createSeal();
        image=seal.getImage();
        boo=true;
        frame.setSize(200,200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setLocation(int a,int b){
        frame.setLocation(a,b);
    }

    public  void paintComponent(Graphics g){
        if(boo){
            g.drawString("存款凭证"+depositSlip.getBankName(),10,20);
            g.drawString("账号"+depositSlip.getClientNumber(),20,50);
            g.drawString("姓名"+depositSlip.getBankName(),10,80);
            g.drawString("金额"+depositSlip.getAmountOfMoney(),20,110);
            label.setBounds(60,120,image.getWidth(this),image.getHeight(this));
        }
    }
}

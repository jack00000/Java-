package com.example;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Created by 15581 on 2017/9/19.
 */

public class Application extends JFrame{
    Application(){
        InputTextSubject textSubject =new InputTextSubject();//具体主题
        ShowWord observerOne=new ShowWord(textSubject);//具体观察者
        ShowDigt observerTwo=new ShowDigt(textSubject);//....
        setLayout(new FlowLayout());
        add(new JScrollPane(textSubject.getJTextArea()));
        add(observerOne);
        add(observerTwo);
        setBounds(20,20,400,300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public  static void main(String arg[]){
        new Application();
    }

}

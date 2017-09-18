package com.example;

/**
 * Created by 15581 on 2017/9/18.
 */
import java.awt.Frame;

import javax.swing.*;
import javax.swing.plaf.multi.MultiButtonUI;

public class ClientUseButton extends JFrame{
    JButton button;
    Command command;
    Invoke person;
    public ClientUseButton(){
        person=new Invoke();
        command=new MultiCommand(new ShowMultForm());
        person.setCommand(command);
        JFrame frame=new JFrame();
        frame.setLayout(new java.awt.FlowLayout());
        button=person.getButton();
        frame.add(button);
        frame.setSize(200,200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setCommand(Command command){
        person.setCommand(command);
    }
    public static void main(String arg []){
        ClientUseButton win=new ClientUseButton();
    }
}

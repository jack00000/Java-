package com.example;

/**
 * Created by 15581 on 2017/9/18.
 */
import javax.swing.*;
import java.awt.event.*;

public class Invoke {
    JButton button;
    Command command;
    Invoke(){
        button =new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                executeCommand();
            }
        });
    }
    public void setCommand(Command command){
        this.command=command;
        button.setText(command.getName());
    }
    public JButton getButton(){
        return button;
    }
    private void executeCommand(){
        command.execute();
    }
}

package com.example;

import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.*;
//具体主题
public class InputTextSubject extends Observable{
    String content;
    JTextArea text;
    InputTextSubject(){
        text=new JTextArea(10,15);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        (text.getDocument()).addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                content=text.getText();
                setChanged();
                notifyObservers(content);

            }
        });
    }
    public String getContent(){
        return content;
    }
    public JTextArea getJTextArea(){
        return text;
    }
}

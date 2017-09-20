package com.example;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Created by 15581 on 2017/9/19.
 */

public class ShowDigt extends JPanel implements Observer {
    Observable subject;
    JTextArea text;
    Vector<String> vector;

    ShowDigt(Observable subject) {
        this.subject = subject;
        subject.addObserver(this);
        text = new JTextArea(10, 15);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        add(new JScrollPane(text));
        vector = new Vector<String>();

    }


    public void update(Observable subject, Object object) {
        text.setText(null);
        text.append("出现的数字有：");
        vector.removeAllElements();
        String content = object.toString();
        //正则表达式
        String regex = "\\D+";
        String digitword[] = content.split(regex);
        for (int i = 0; i < digitword.length; i++) {
            if (!vector.contains(digitword[i]))
                vector.add(digitword[i]);

        }
        for (int i = 0; i < vector.size(); i++) {
            text.append(vector.elementAt(i) + " ");
        }
    }
}
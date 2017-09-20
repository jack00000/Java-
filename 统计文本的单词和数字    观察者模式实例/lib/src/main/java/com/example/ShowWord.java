package com.example;

/**
 * Created by 15581 on 2017/9/18.
 */

import java.util.Observable;
import java.util.Observer;
import java.util.TreeSet;
import java.util.Iterator;
import javax.swing.*;
//具体观察者
public class ShowWord extends JPanel implements Observer{
    Observable subject;
    JTextArea text;
    TreeSet<String> wordList;
    ShowWord(Observable subject) {
        this.subject = subject;
        subject.addObserver(this);
        text = new JTextArea(10, 15);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        add(new JScrollPane(text));
        wordList = new TreeSet<String>();
    }

    public void update(Observable subject, Object object) {
        text.setText(null);
        text.append("出现的单词有（按字典排序）：");
        wordList.clear();
        String content = object.toString();
        //正则表达式
        String regex = "[\\s\\d\\p{Punct}]+";
        String word[] = content.split(regex);
        for (int i = 0; i < word.length; i++) {
            wordList.add(word[i]);

        }
        Iterator<String> te = wordList.iterator();
        while (te.hasNext()) {
            String str = te.next();
            text.append(str + " ");
        }
    }

}

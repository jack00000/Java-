---
title: StringMatching
date: 2017-10-29 20:13:10
tags:
---

<h3>学习目的：有条理的血java界面程序<h3>
![](http://oyhm15net.bkt.clouddn.com/2017-10-27_231142.png)

```java
package com.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StringMatching extends JFrame{
    private static final long serialVersionUID = 1L;//序列化时为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性

    private JFrame jf=new JFrame("标题：charMatching");
    //提醒字符
    private JLabel l1=new JLabel("Pattern Strng:");//JLabel对象可显示文本，图片
    private JLabel l2=new JLabel("Text resource");
    private JLabel l4=new JLabel("resource");

    private JLabel l3=new JLabel("choose pattren");
    //定义菜单
    private JComboBox<String> pattern=new JComboBox<String>();//下拉框

    //资源与结果框
    private JTextArea result=new JTextArea();
    private JTextArea resource=new JTextArea();

    //某种绑定
    private  JScrollPane jp2=new JScrollPane(result);
    private JScrollPane jp=new JScrollPane(resource);//JScrollPane 管理视口、可选的垂直和水平滚动条以及可选的行和列标题视口，

    //输入查询字符框
    private JTextField  patternInput=new JTextField();//JTextField 是一个轻量级组件，它允许编辑单行文本

    //按钮
    private JButton find=new JButton();
    private JButton clear=new JButton();

    private JFileChooser chooser=new JFileChooser();

    //构造函数
    Demo(){
        setFrame();
        setTextFields();
        setMenu();
        setButton();

    }
    //设置主界面
    void setFrame(){
        jf.setSize(600,600);
        jf.setResizable(false);//窗体是否自由变换大小
        jf.setVisible(true);//窗口显示
        jf.setLayout(null);//未设置layout 系统默认flowLayout布局 null清除布局管理器
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);//设置用户在此窗体上发起 "close" 时默认执行的操做

    }
    //添加文本域
    void setTextFields(){
        l1.setBounds(20,90,100,30);//setBounds(x,y,width,height); x:组件在容器X轴上的起点
        // y:组件在容器Y轴上的起点 width:组件的长度 height:组件的高度
        jf.add(l1);

        patternInput.setBounds(120,30,200,25);
        jf.add(patternInput);
        patternInput.setCaretColor(Color.CYAN);//设置符号颜色
        patternInput.setSelectedTextColor(Color.YELLOW);//设置搜索字符颜色

        l2.setBounds(20,90,100,30);
        jf.add(l2);

        resource.setLineWrap(true);//设置文本区的换行策略(句子换行)
        resource.setWrapStyleWord(true);//单词换行true
        resource.setEditable(false);
        resource.setCaretColor(Color.YELLOW);
        resource.setSelectedTextColor(Color.BLUE);////设置文本框被选择颜色
        //resource的异常处理
        try{
            @SuppressWarnings("resource")//有了suppresswarning这个批注，可以取消一些特定代码段中的警告，比如你看到警告，你查了一下，发现他不是问题，
            // 可是你为了好看又不想让他报警，就可以加这个批注。
                    BufferedReader br=new BufferedReader(new FileReader(new File("txtFile/cache.txt")));
                    resource.setText("\n\n\n\n\n");//正则表达式
                    while (br.readLine()!=null){
                        resource.setText(resource.getText()+"\n"+br.readLine()+"\n");
                    }

        }catch(Exception e){
                   e.printStackTrace();//在命令行打印异常信息在程序中出错的位置及原因
        }

        //jp对象传入的参数为resource
        jp.setBounds(20,120,300,400);
        jf.add(jp);

        l4.setBounds(370,95,100,25);
        jf.add(l4);
        //result
        result.setLineWrap(true);//设置文本区的换行策略(句子换行)
        result.setWrapStyleWord(true);//单词换行true
        result.setEditable(false);
        result.setSelectedTextColor(Color.getHSBColor(1,400,100));////设置文本框被选择颜色
        //jp2对象传入的参数为result
        jp2.setBounds(20,90,100,30);
        jf.add(jp2);

     //完工

    }
    //设置下拉菜单
    void setMenu(){
        l3.setBounds(370,30,100,25);
        jf.add(l3);
        pattern.addItem("Choose File");
        pattern.addItem("Chinese Demo");
        pattern.addItem("English Demo");
        pattern.addItem("Custom");
        //给下拉框设置监听事件
        pattern.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(pattern.getSelectedItem().toString()=="Chinese Demo"){
                    resource.setText("InPut your text");
                    resource.setText(null);
                    result.setText(null);

                    try{
                        @SuppressWarnings("resource")//有了suppresswarning这个批注，可以取消一些特定代码段中的警告，比如你看到警告，你查了一下，发现他不是问题，
                                // 可是你为了好看又不想让他报警，就可以加这个批注。
                                BufferedReader br=new BufferedReader(new FileReader(new File("txtFile/cache.txt")));
                        int i=1;
                        while (br.readLine()!=null){
                            resource.setText(resource.getText()+"\n"+br.readLine()+"\n");
                            i++;
                        }

                    }catch(Exception e2){
                        e2.printStackTrace();//在命令行打印异常信息在程序中出错的位置及原因
                    }

                }
                else if(pattern.getSelectedItem().toString()=="English Demo"){
                    resource.setText("InPut your text");
                    resource.setText(null);
                    result.setText(null);

                    try{
                        @SuppressWarnings("resource")//有了suppresswarning这个批注，可以取消一些特定代码段中的警告，比如你看到警告，你查了一下，发现他不是问题，
                                // 可是你为了好看又不想让他报警，就可以加这个批注。
                                BufferedReader br=new BufferedReader(new FileReader(new File("txtFile/cache.txt")));
                        int i=1;
                        while (br.readLine()!=null){
                            resource.setText(resource.getText()+"\n"+br.readLine()+"\n");
                            i++;
                        }

                    }catch(Exception e2){
                        e2.printStackTrace();//在命令行打印异常信息在程序中出错的位置及原因
                    }

                }
                else if(pattern.getSelectedItem().toString()=="Choose File"){
//					resource.setText("Choose File");
                    resource.setText(null);
                    result.setText(null);
                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    chooser.showOpenDialog(null);
                    if(chooser.getSelectedFile().exists()&&chooser.getSelectedFile().canRead()){
                        try {
                            @SuppressWarnings("resource")
                            BufferedReader br=new BufferedReader(new FileReader(chooser.getSelectedFile()));
                            int i=1;
                            while(br.readLine()!=null){
                                resource.setText(resource.getText().trim()+"\n"+i+"—"+br.readLine());
                                i++;
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();// TODO: handle exception
                        }
                    }
                }
                else {
                    resource.setEditable(true);
                }
            }
        });
        //下拉框监听器在此结束

    }
    //设置按钮点击事件
    void setButton(){
        find.setBounds(370,65,90,25);
        jf.add(find);
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String sourceString=resource.getText();
                String patternString=patternInput.getText();
                String s[]=sourceString.split("\n");//分割字符串sourceString到s[]
                result.setText("teh text contains"+s.length+"lines");
                result.setText(result.getText()+"\nThe following line(s) contain(s) "+"\""+patternString+"\":");//没理解
                int no=0;
                for(int i=0;i<s.length;i++){
                    if(s[i].contains(patternString)){
                        result.setText(result.getText()+"\n"+(no+1)+":"+s[i]+"\n");
                        no++;
                    }
                    result.setText(result.getText()+"\n\n"+"Total"+no+"item(s)contains"+"\""+patternString+"\".");//"\""这样才行  好奇怪

                }

            }
        });
        clear.setBounds(480,65,90,25);
        jf.add(clear);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                patternInput.setText(null);
                result.setText(null);
                resource.setText(null);
            }
        });
    }


    public static void main(String[] args){
        new Demo();
    }
}
```

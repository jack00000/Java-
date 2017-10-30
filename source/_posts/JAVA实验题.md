---
title: JAVA实验题
date: 2017-6-2 12：00
tag:
---
01
```java
public class HelloWorld {
    public static void main(String []args) {
    System.out.println("    J    A    V     V   A	");
		System.out.println("    J   A A    V   V   A A");
		System.out.println("J   J  AAAAA    V V   AAAAA");
		System.out.println(" JJ   A     A    V   A     A");

    }
}
```
02
```java
public class HelloWorld {
    public static void main(String []args) {
       float pai;
		pai=4*(1-1/3+1/5-1/7+1/9-1/11+1/13);
		System.out.println("pai="+pai);

    }
}
```
03
```java
import java.util.Scanner;
public class A*B{
  public static void  main(String[] args) {
    double a[3][3],b[3][3];
    double [][] A={{a[0][0],a[0][1],a[0][2]}
                   {a[1][0],a[1][1],a[1][2]}
                   {a[2][0],a[2][1],a[2][2]}}
    double [][] B={{b[0][0],b[0][1],b[0][2]}
                   {b[1][0],b[1][1],b[1][2]}
                   {b[2][0],b[2][1],b[2][2]}}
    double [][] C={{c[0][0],c[0][1],c[0][2]}
                   {c[1][0],c[1][1],c[1][2]}
                   {c[2][0],c[2][1],c[2][2]}}
                   //输入               
       Scanner in=new Scanner(System in);
       a[3][3]=scanner.nextLine();
       b[3][3]=scanner.nextLine();
       //计算
     for(int i=0;i<3;i++){
       for(int j=0;j<3;j++){
        double m+=a[i][j]*b[j][i]
       }

     }

  }
}
```
04 矩阵相乘算法
```java
public class ArrayMult {  

    public static void main(String[] args) {  
        int[][] a = {{1,2},{3,4},{5,6}};  
        int[][] b = {{1,2,3},{4,5,6}};  
        //m表示数组a的行数，n表示数组b的列数，s表示数组a的列数和数组b的行数  
        int m = a.length, n = b[0].length, s1 = a[0].length, s2 = b.length;  
        //判断两个数组是否能够相乘求积  
        if(s1 != s2) {  
            System.out.println("数组不能相乘，数组a的列数不等于数组b的行数");  
            return;  
        }  
        //动态创建数组c用来保存结果  
        int[][] c = new int[m][n];  
        for(int i=0; i<m; i++)   //i表示数组c的每一行  
            for(int j=0; j<n; j++) {  //j表示数组c的每一列  
                int temp = 0;  
                for(int k=0; k<s1; k++) //k表示数组a的列号和数组b的行号  
                    temp+=a[i][k]*b[k][j];  
                c[i][j] = temp;  
            }  
        print(a);  
        print(b);  
        print(c);  
    }  

    private static void print(int[][] c) {  
        int m = c.length;  
        int n = c[0].length;  
        for(int i=0; i<m; i++) {  
            for(int j=0; j<n; j++) {  
                if(c[i][j]<10)  
                    System.out.print(" ");  
                System.out.print(c[i][j] + " ");  
            }  
        System.out.println();  
        }  
        System.out.println("--------------------------------------");  
    }  
}  
```
06
```java
import java.util.Scanner;
public class FingNearstPoints{
  public static void main(String[] args) {
    //输入
    Scanner input=new Scanner(System in);
    System.out.println("Enter the number of points:");
    int numberOfPoints=intput.nextLine();
    //
    double[][] points=new double[numberOfPoints][2];
    System.out.println("Enter"+numberOfPoints+"points");
    for(int i=0;i<points.length;i++){
      points[i][0]=input.nextDouble();
      points[i][1]=input.nextDouble();
    }
    int p1=0,p2=1;
    double shortDistance=distance(points[p1][0],points[p1][1],points[p2][0],points[p2][1]);

    for(int i=0;i<points.length;i++){
      for(int j=i+1;j<points.length;j++){
        double distance=distance(points[i][0],points[i][10],points[j][0],points[j][10]);

        if(shortDistance>distance){
          p1=i;p2=j;
          shortDistance=distance;
        }
      }
    }

    System.out.println("The two points are"+"("+points[p1][0]+","+points[p1][1]+")and("+points[p2][0]+","+points[p2][1]+")");

  }
  public static double distance (double x1,double y1,double x2,double y2)
  {
    return Math.sqrt(x2-x1)*(x2-x1)+(y2-y1)*(y2-y1);
  }

  }
}
```

03  日历 时钟

```java
package textchess;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
  import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
 import java.util.*;
import javax.swing.JPanel;  
import javax.swing.JTextField;  
import java.awt.GridLayout;
import javax.swing.JFrame;  
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class Test extends JFrame
{
 private static final long serialVersionUID = 1L;
//测试
 public Test()
 {
  Clock clock =new Clock();
  Calender cal = new Calender();
  @SuppressWarnings("unused")
  JPanel jp2 = new JPanel();

  setLocationRelativeTo(null);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setSize(560,300);
  setVisible(true);
  this.setContentPane(clock);


  this.getContentPane().add(cal,BorderLayout.WEST);
  setResizable(false);
 }
////////////////////////////////////
 //画时钟
public class DrawClock extends JPanel implements Runnable
 {  


  private static final long serialVersionUID = 1L;
  Thread newThread;  //线程

     public int RADIUS = 80;    //时钟的半径
    //设置时钟位置
     public int centerX = 150;         //设置时钟x轴
     public int centerY = 120;         //设置时钟y轴

     public int hr, min, sec;   //小时，分钟，秒
     public int[] xPoint = new int[4]; //指针的4个坐标   
     public int[] yPoint = new int[4];  
     public double hrAlpha, minAlpha, secAlpha, theta;  

     private JTextField timeZone;  
        //启动时钟
     public void start()
     {  
         newThread = new Thread(this);  
         newThread.start();  //启动线程
     }  
        //终止线程
     public void stop()
     {  
         newThread = null;  
     }  


     @SuppressWarnings("deprecation")
  public void paint(Graphics g)
     {  
         super.paint(g);  


         //画出时钟刻度   
         double minuteAlpha  = Math.PI/30.0;  
         int count = 0;        
         for(double alpha=0; alpha<2.0*Math.PI; alpha+=minuteAlpha)
         {  
             int tX = (int)(centerX+RADIUS*0.9*Math.sin(alpha));  
             int tY = (int)(centerY-RADIUS*0.9*Math.cos(alpha));  
             if(count%5 == 0)
             {  
                 g.setColor(Color.CYAN);  
                 g.fill3DRect(tX, tY, 3, 3, false);  
                 if(count%3==0)
                 {
                  int m = count /15;
                  switch(m){
                  case  1: g.drawString("3", centerX+RADIUS-18, centerY+5);break;
                  case  2: g.drawString("6", centerX-3, centerY+RADIUS-10);break;
                  case  3: g.drawString("9", centerX-RADIUS+11,centerY+6);break;
                  default: g.drawString("12", centerX-5, centerY-RADIUS+22);                 
                  }

                 }
             }
             else
             {  
                 g.setColor(Color.DARK_GRAY);  
                 g.fill3DRect(tX, tY, 2, 2, false);  
             }  
             count++;  
         }  

         //画出时钟时针   
         g.setColor(Color.gray);   // 定义颜色
         drawPointer(g, centerX+2, centerY+2, (int)(RADIUS*0.75), hrAlpha);  
         g.setColor(Color.CYAN);   //   定义颜色
         drawPointer(g, centerX, centerY, (int)(RADIUS*0.75), hrAlpha);  


         //画出分针   
         g.setColor(Color.gray);  // 定义颜色
         drawPointer(g, centerX+2, centerY+2, (int)(RADIUS*0.83), minAlpha);  
         g.setColor(Color.CYAN);  // 定义颜色
         drawPointer(g, centerX, centerY, (int)(RADIUS*0.83), minAlpha);  


         //画出秒针   
         g.setColor(Color.DARK_GRAY);   //定义颜色
         g.drawLine( centerX,centerY,  
                 (int)(centerX+(int)(RADIUS*0.79)*Math.sin(secAlpha)),  
                 (int)(centerY-(int)(RADIUS*0.79)*Math.cos(secAlpha))  
         );  
            setBorder(new TitledBorder("时间"));
            setBackground(Color.white); // 定义颜色

            g.drawRect(85, 210, 130, 20);  
            g.setColor(Color.WHITE);
            g.setColor(Color.DARK_GRAY);  

            Date timeNow = new Date();
            g.drawString(timeNow.toLocaleString(), 100,225);  
     }  


     public Date getDate()
     {  
         Date timeNow = new Date();  
         return timeNow;  
     }  

     // 刷新图层

     public void update(Graphics g)
     {  
         paint(g);  
     }  


      // 画出一个帧的图像

     public void run() {  
         while(newThread != null)
         {  
             repaint();  
             try
             {  
                 Thread.sleep(800);  
             } catch(InterruptedException E) {}  

             Date timeNow = new Date();

             @SuppressWarnings("deprecation")
    int hours  = timeNow.getHours();      //这里不知道为什么会画横线的

             @SuppressWarnings("deprecation")
    int minutes = timeNow.getMinutes();   //这里不知道为什么会画横线的

             @SuppressWarnings("deprecation")
    int seconds = timeNow.getSeconds();   //这里不知道为什么会画横线的
             hr  = hours;  
             min = minutes;  
             sec = seconds;  

             theta = Math.PI/6.0/20.0;  

             hrAlpha  = (double)(hr*3600 + min*60 + sec)  
                 /(12.0*3600.0)*2.0*Math.PI;  
             minAlpha = (double)(min*60 + sec)/3600.0*2.0*Math.PI;  
             secAlpha = (double)sec/60.0 * 2.0*Math.PI;  

         }         
     }  


     private void drawPointer(Graphics g, int x, int y,   
             int len, double theta)
     {  

         xPoint[0] = (int)(x+len*0.3*Math.sin(theta-Math.PI));  
         yPoint[0] = (int)(y-len*0.3*Math.cos(theta-Math.PI));  
         xPoint[1] = (int)(xPoint[0]+len*0.3*Math.sin  
                 (theta-(double)(10.0/180)*Math.PI));  
         yPoint[1] = (int)(yPoint[0]-len*0.3*Math.cos  
                 (theta-(double)(10.0/180)*Math.PI));  
         xPoint[2] = (int)(xPoint[0]+len * Math.sin(theta));  
         yPoint[2] = (int)(yPoint[0]-len * Math.cos(theta));  
         xPoint[3] = (int)(xPoint[0]+len*0.3*Math.sin  
                 (theta+(double)(10.0/180)*Math.PI));  
         yPoint[3] = (int)(yPoint[0]-len*0.3*Math.cos  
                 (theta+(double)(10.0/180)*Math.PI));  

         g.fillPolygon(xPoint, yPoint, 4);         
     }
  public JTextField getTimeZone() {
   return timeZone;
  }
  public void setTimeZone(JTextField timeZone) {
   this.timeZone = timeZone;
  }  


 }  
//////////////////////////////////
 //时钟
public class Clock extends JPanel
 {    

  private static final long serialVersionUID = 1L;
  private UIManager.LookAndFeelInfo looks[];
  private DrawClock clock ;

     @SuppressWarnings("unused")
  private JPanel pane_clock ;
  JPanel pane_cal;
  public Clock()
  {
     super();

     looks = UIManager.getInstalledLookAndFeels();
     changeTheLookAndFeel(2);


       clock = new DrawClock();
          clock.start();

         this.setBackground(Color.GRAY);  
         this.setLayout(new BorderLayout());       
         this.setOpaque(false);  

       this.add(clock);
       this.setBorder(new TitledBorder("时间日期"));

       setSize( 300, 300 );  
       setVisible( true );  
    }

     private void changeTheLookAndFeel(int i)
     {
      try
      {
       UIManager.setLookAndFeel(looks[i].getClassName());
       SwingUtilities.updateComponentTreeUI(this);
      }
      catch(Exception exception)
      {
       exception.printStackTrace();
      }



  }
 }   
///////////////////////////////////
               //设计日历
public class Calender extends JPanel implements ActionListener
 {

  private static final long serialVersionUID = 1L;
  public  final String HOUR_OF_DAY = null;

  //定义
  @SuppressWarnings("rawtypes")
  JComboBox Month = new JComboBox();
  @SuppressWarnings("rawtypes")
  JComboBox Year = new JComboBox();
  JLabel Year_l = new JLabel("年");
  JLabel Month_l = new JLabel("月");  
  Date now_date = new Date();
  JLabel[] Label_day = new JLabel[49];  
  @SuppressWarnings("deprecation")
  int now_year = now_date.getYear() + 1900;
  @SuppressWarnings("deprecation")
  int now_month = now_date.getMonth();
  boolean bool = false;
  String year_int = null;
  int month_int;
  JPanel pane_ym = new JPanel();
  JPanel pane_day = new JPanel();

  @SuppressWarnings("unchecked")
  public Calender()
  {
   super();
   //设定年月
   for (int i = now_year - 10; i <= now_year + 20; i++)
   {
    Year.addItem(i + "");
   }
   for (int i = 1; i < 13; i++)
   {
    Month.addItem(i + "");
   }
   Year.setSelectedIndex(10);
   pane_ym.add(new  JLabel("        "));
   pane_ym.add(Year);
   pane_ym.add(Year_l);
   Month.setSelectedIndex(now_month);  
   pane_ym.add(Month);
   pane_ym.add(Month_l);
   pane_ym.add(new  JLabel("        "));

   Month.addActionListener(this);
   Year.addActionListener(this);


   //初始化日期并绘制
   pane_day.setLayout(new GridLayout(7, 7, 10, 10));
   for (int i = 0; i < 49; i++) {
    Label_day[i] = new JLabel(" ");
    pane_day.add(Label_day[i]);
   }
   this.setDay();
   this.setLayout(new BorderLayout());
      this.add(pane_day, BorderLayout.CENTER);
   this.add(pane_ym, BorderLayout.NORTH);
   this.setSize(100,200);
   this.setBorder(new TitledBorder("日历"));
   setSize(300,300);

  }
  @SuppressWarnings("deprecation")
  void setDay()
  {
   if (bool)
   {
    year_int = now_year + "";
    month_int = now_month;
   }
   else
   {
    year_int = Year.getSelectedItem().toString();
    month_int = Month.getSelectedIndex();
   }

   int year_sel = Integer.parseInt(year_int) - 1900; //获得年份值
   //@SuppressWarnings("deprecation")
   Date dt = new Date(year_sel, month_int, 1); //构造一个日期
   GregorianCalendar cal = new GregorianCalendar(); //创建一个Calendar实例
   cal.setTime(dt);
   String week[] = { "日", "一","二", "三", "四", "五", "六" };

   int day = 0;
   int day_week = 0;
   for (int i = 0; i < 7; i++) {
    Label_day[i].setText(week[i]);
   }

   //月份
   if (month_int == 0||month_int == 2 ||month_int == 4 ||
     month_int == 6 ||
     month_int == 9 ||month_int == 11)
   {
    day = 31;
   }
   else if (month_int == 3 ||month_int == 5 || month_int == 7||
     month_int == 8 ||month_int == 10|| month_int == 1)
   {
    day = 30;
   }
   else
   {
    if (cal.isLeapYear(year_sel))
    {
     day = 29;
    }
    else
    {
     day = 28;
    }
   }
   day_week = 7 + dt.getDay();
   int count = 1;
   for (int i = day_week; i < day_week + day; count++, i++)
   {
    if (i % 7 == 0 ||i == 13||i == 20||i == 27||
      i == 48 ||i == 34 ||i == 41)
    {
     if (i == day_week + now_date.getDate() - 1)
     {
      Label_day[i].setForeground(Color.blue);
      Label_day[i].setText(count + "");
     }
     else
     {
      Label_day[i].setForeground(Color.red);
      Label_day[i].setText(count + "");
     }
    }
    else
    {
     if (i == day_week + now_date.getDate() - 1)
     {
      Label_day[i].setForeground(Color.blue);

      Label_day[i].setText(count + "");
     }
     else
     {
      Label_day[i].setForeground(Color.black);
      Label_day[i].setText(count + "");
     }
    }
   }
   if (day_week == 0)
   {
    for (int i = day; i < 49; i++)
    {
     Label_day[i].setText(" ");
    }
   }
   else
   {

    for (int i = 7; i < day_week; i++)
    {
     Label_day[i].setText(" ");
    }
    for (int i = day_week + day; i < 49; i++)
    {
     Label_day[i].setText("  ");
    }
   }
  }
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == Year || e.getSource() == Month) {
    bool = false;
    this.setDay();
   }
  }

 }
//////////////////////////////////
public static void main(String[] args)
{

try
{
   Test frame = new Test();
   frame.setTitle("日历");
}
catch (Exception e){
 System.out.print("run error!");    
}
}
}
```

<img src='http://img.027cgb.cn/20170608/201768531287945569.png' />

4 棋盘覆盖
```java
package textchess;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class chessBoard extends JFrame {
 private int tr, tc, dr, dc, size;//定义各成员变量
 int tile = 1;
 float red,green,blue;
 JPanel centerPanel;
 JPanel southPanel;
 JButton[][] button;
 JTextField TrText, TcText, DrText, DcText, SizeText;
 JLabel TrLabel, TcLabel, DrLabel, DcLabel, SizeLabel;
 JButton OKButton;
 JButton CancelButton;
 JPanel panel = new JPanel();
 public chessBoard() {
  super();
  setTitle("棋盘覆盖");

  this.setResizable(false);
  centerPanel = new JPanel();
  southPanel = new JPanel();
  OKButton = new JButton("确定或开始");
  OKButton.addActionListener(new OKButtonAction());
  CancelButton = new JButton("取消或清除");
  CancelButton.addActionListener(new OKButtonAction());
  setBounds(300, -10, 900, 900);//设置窗口大小与位置
  TrText = new JTextField("0",2);//定义各组件
  TcText = new JTextField("0",2);
  DrText = new JTextField("0",2);
  DcText = new JTextField("0",2);
  SizeText = new JTextField("4",2);
  TrLabel = new JLabel("起始方格坐标x:   ");
  TcLabel = new JLabel("起始方格坐标y:    ");
  DrLabel = new JLabel("特殊方格坐标x:   ");
  DcLabel = new JLabel("特殊方格坐标y:   ");
  SizeLabel = new JLabel("棋盘规模size:   ");
  TrText.setEnabled(false);
  TcText.setEnabled(false);
  int tR = Integer.parseInt(TrText.getText());
  int tC = Integer.parseInt(TcText.getText());
  int dR = Integer.parseInt(DrText.getText());
  int dC = Integer.parseInt(DcText.getText());
  int Size = 1;
  for (int i=0;i<Integer.parseInt(SizeText.getText());i++)
   Size*=2;
  tr = tR;
  tc = tC;
  dr = dR;
  dc = dC;
  size = Size;
  southPanel.add(CancelButton);//添加各组件到窗体
  southPanel.add(TrLabel);
  southPanel.add(TrText);
  southPanel.add(TcLabel);
  southPanel.add(TcText);
  southPanel.add(DrLabel);
  southPanel.add(DrText);
  southPanel.add(DcLabel);
  southPanel.add(DcText);
  southPanel.add(SizeLabel);
  southPanel.add(SizeText);
  southPanel.add(OKButton);

  getContentPane().add(southPanel, BorderLayout.NORTH);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
 class gridLayout {
  public gridLayout() {
   centerPanel.setLayout(new GridLayout(0, size));
   button = new JButton[size][size];
   for (int i = 0; i < size; i++) {
    for (int j = 0; j < size; j++) {
     button[i][j] = new JButton();
     if (i == dr && j == dc) {
      button[i][j].setBackground(Color.BLUE);
      button[i][j].setText("<html><font size='3' color='white'>棋盘覆盖<br>Done By Java!</font></html>");
      button[i][j].setEnabled(false);
     }
     centerPanel.add(button[i][j]);
    }
   }
  }
  private void sleep()
  {
   for (int i=0;i<100;i++)
    for(int j=0;j<1000;j++);
  }
  public void ChessBoard(int tr, int tc, int dr, int dc, int size) {//算法实现
   if (size == 1) // 棋盘方格大小为1,说明递归到最里层
    return;
   int t = tile++;// 每次递增1
   Random rd =  new Random();
   red=rd.nextFloat();
   green=rd.nextFloat();
   blue=rd.nextFloat();
   Color col = new Color(red,green,blue);
   sleep();
   int s = size / 2; // 棋盘中间的行、列号(相等的)
   // 检查特殊方块是否在左上角子棋盘中
   if (dr < tr + s && dc < tc + s) // 在
    ChessBoard(tr, tc, dr, dc, s);
   else // 不在，将该子棋盘右下角的方块视为特殊方块
   {
    button[tr + s - 1][tc + s - 1].setBackground(col);
    button[tr + s - 1][tc + s - 1].setEnabled(false);
    button[tr + s - 1][tc + s - 1].setText("<html><Font size='4',color='white'>"+t+"</Font></html>");
    ChessBoard(tr, tc, tr + s - 1, tc + s - 1, s);
    sleep();
   }
   // 检查特殊方块是否在右上角子棋盘中
   if (dr < tr + s && dc >= tc + s) // 在
    ChessBoard(tr, tc + s, dr, dc, s);
   else // 不在，将该子棋盘左下角的方块视为特殊方块
   {
    button[tr + s - 1][tc + s].setBackground(col);
    button[tr + s - 1][tc + s].setEnabled(false);
    button[tr + s - 1][tc + s ].setText("<html><Font size='4',color='white'>"+t+"</Font></html>");
    ChessBoard(tr, tc + s, tr + s - 1, tc + s, s);
    sleep();
   }
   // 检查特殊方块是否在左下角子棋盘中
   if (dr >= tr + s && dc < tc + s) // 在
    ChessBoard(tr + s, tc, dr, dc, s);
   else // 不在，将该子棋盘右上角的方块视为特殊方块
   {
    button[tr + s][tc + s - 1].setBackground(col);
    button[tr + s][tc + s - 1].setEnabled(false);
    button[tr + s ][tc + s - 1].setText("<html><Font size='4',color='white'>"+t+"</Font></html>");
    ChessBoard(tr + s, tc, tr + s, tc + s - 1, s);
    sleep();
   }
   // 检查特殊方块是否在右下角子棋盘中
   if (dr >= tr + s && dc >= tc + s) // 在
    ChessBoard(tr + s, tc + s, dr, dc, s);
   else // 不在，将该子棋盘左上角的方块视为特殊方块
   {
    button[tr + s][tc + s].setBackground(col);
    button[tr + s][tc + s].setEnabled(false);
    button[tr + s ][tc + s ].setText("<html><Font size='4',color='white'>"+t+"</Font></html>");
    ChessBoard(tr + s, tc + s, tr + s, tc + s, s);
    sleep();
   }
  }
 }
 public class OKButtonAction implements ActionListener {//点下一个按钮时的事件响应
  @Override
  public void actionPerformed(ActionEvent e) {
   // TODO Auto-generated method stub
   JButton whichButton = (JButton) e.getSource();//获取点击的事件，即是点下了哪个按钮
   String whichName = whichButton.getActionCommand();
   if(whichName.equals("确定或开始")) {
    getContentPane().add(centerPanel, BorderLayout.CENTER);
    int tR = Integer.parseInt(TrText.getText());
    int tC = Integer.parseInt(TcText.getText());
    int dR = Integer.parseInt(DrText.getText());
    int dC = Integer.parseInt(DcText.getText());
    int Size = 1;
    for (int i=0;i<Integer.parseInt(SizeText.getText());i++)
     Size*=2;
    tr = tR;
    tc = tC;
    dr = dR;
    dc = dC;
    size = Size;
    try {
     gridLayout grid = new gridLayout();
     grid.ChessBoard(tr, tc, dr, dc, size);
     centerPanel.updateUI();
    } catch (Exception EX) {
     EX.printStackTrace();
    }
    panel.removeAll();
    OKButton.setEnabled(false);
   }
   if (whichName.equals("取消或清除")) {//当你点下一个提示按钮时的事件响应
    JLabel label = new JLabel();
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setText("<html><Font size='+8',color='red'><center><b><br>    您取消了操作或是   <br><Font size='+8',color='blue'><center>您清除了前一个棋盘……" +
      "<br><Font size='+8',color='green'><center>下面是关于题目的介绍<br><br><br><br><br><br>  </b></Font></html>");
//    JLabel l = new JLabel("题目要求");
    JTextArea area = new JTextArea("在一个2k x 2k ( 即：2^k x 2^k )个方格组成的棋盘中，恰有一个方格与其他方格不同，" +
      "称该方格为一特殊方格，且称该棋盘为一特殊棋盘。在棋盘覆盖问题中，要用4种不同形态的L型骨牌覆盖给定的特殊棋盘上除特殊方格以外的所有方格，" +
      "且任何2个L型骨牌不得重叠覆盖。",7,60);
    area.setLineWrap(true);
    area.setBackground(Color.blue);
    area.setForeground(Color.white);
    area.setFont (new Font("SansSerif", Font.PLAIN, 14));
    area.setEditable(false);
//    FlowLayout layout =  new FlowLayout();
//    layout.addLayoutComponent(arg0, arg1)
    panel.add(label,centerPanel );
//    panel.add(l,southPanel);

    panel.add(area,southPanel);
    getContentPane().add(panel, BorderLayout.CENTER);
    panel.updateUI();
    tile=1;
    centerPanel.removeAll();
    OKButton.setEnabled(true);
   }
  }
 }
 public static void main(String[] args) {//主函数方法实现
  chessBoard chess = new chessBoard();
  chess.setVisible(true);
  Runtime run = Runtime.getRuntime();
  run.gc();//手动清除数据垃圾
 }
}
```
<img src='http://img.027cgb.cn/20170608/201768422407433177.png' />

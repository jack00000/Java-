package com.example;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class SealOne implements Seal {
    BufferedImage image;
    Graphics2D g;
    SealOne(){
        image=new BufferedImage(100,100,BufferedImage.TYPE_3BYTE_BGR);
        g=image.createGraphics();
        g.setColor(Color.WHITE);
        Rectangle2D rect=new Rectangle2D.Double(0,0,100,100);
        g.fill(rect);
        g.setColor(Color.RED);
        BasicStroke bs=new BasicStroke(3f,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_ROUND);
        Ellipse2D ellipse=new Ellipse2D.Double(5,6,80,80);
        g.setStroke(bs);
        g.draw(ellipse);
        g.setFont(new Font("宋体",Font.BOLD,14));
        g.drawString("中国银行",16,15);
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }
}

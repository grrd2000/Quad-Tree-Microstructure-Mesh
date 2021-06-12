package com.gerskom;

import java.awt.*;

public class Partition {

    int xMin, yMin, xMax, yMax;

    public Partition(int xMin, int yMin, int xMax, int yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    public boolean inRange(int x, int y) {
        return (x >= this.xMin && x <= this.xMax && y >= this.yMin && y <= this.yMax);
    }

    public void paintComponent (Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        //g2D.setPaint(new Color(153,51,153));
        g2D.setPaint(new Color(0,0,0));
        g2D.setStroke(new BasicStroke(1f));
        g2D.drawLine(xMin, yMax, xMax, yMax);
        g2D.drawLine(xMax, yMax, xMax, yMin);
        g2D.drawLine(xMax, yMin, xMin, yMin);
        g2D.drawLine(xMin, yMin, xMin, yMax);
    }
}

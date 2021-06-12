package com.gerskom;

import java.awt.*;

public class Node2D {
    int x;
    int y;

    public Node2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        return this.x == ((Node2D) obj).x && this.y == ((Node2D) obj).y;
    }

    public void paintComponent (Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(new Color(250,110,0));
        g2D.setStroke(new BasicStroke(0.15f));
        g2D.drawLine(x, y, x, y);
    }
}

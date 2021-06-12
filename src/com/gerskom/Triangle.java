package com.gerskom;

import java.awt.*;

public class Triangle {

    Node2D A;
    Node2D B;
    Node2D C;

    public Triangle(Node2D a, Node2D b, Node2D c) {
        A = a;
        B = b;
        C = c;
    }

    public void paintComponent (Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(new Color(0,0,0));
        g2D.setStroke(new BasicStroke(1f));
        g2D.drawLine(A.x, A.y, B.x, B.y);
        g2D.drawLine(B.x, B.y, C.x, C.y);
        g2D.drawLine(C.x, C.y, A.x, A.y);
    }
}

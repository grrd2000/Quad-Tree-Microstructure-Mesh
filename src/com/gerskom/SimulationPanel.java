package com.gerskom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimulationPanel extends JPanel {

    ImageData imageData;

    static List<Node2D> points;
    static List<Partition> divisions = new ArrayList<>();
    static List<Triangle> triangles = new ArrayList<>();

    public SimulationPanel(ImageData imageData) throws IOException {
        super();
        this.imageData = imageData;
        this.setPreferredSize(new Dimension(imageData.width, imageData.height));
        points = imageData.keyPoints;
        quadTreeAlgorithm();
        triangulation();
        meshImageExport();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create();

        g2D.drawImage(imageData.image, 0, 0, null);

        //for(Partition partition : divisions) {
        //    partition.paintComponent(g2D);
        //}

        for(Triangle triangle : triangles)
            triangle.paintComponent(g2D);


        for (Node2D node2D : points)
            node2D.paintComponent(g2D);

        g2D.dispose();
    }

    public static void addBoundary(Partition partition) {
        divisions.add(partition);
        //boundary.print();
    }

    public void quadTreeAlgorithm() {
        QuadTree quadTree = new QuadTree(1, new Partition(0,0,imageData.width, imageData.height));

        for(Node2D node : imageData.keyPoints) {
            quadTree.insert(node.x, node.y);
        }
        QuadTree.treeRecur(quadTree);
        System.out.println("end");
    }

    public void triangulation() {
        triangles.addAll(new Triangulation().triangulation(divisions));
    }

    public void meshImageExport() throws IOException {
        BufferedImage bi = new BufferedImage(imageData.width, imageData.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = bi.createGraphics();

        //g2D.drawImage(imageData.image, 0, 0, null);   //obraz mikrostruktury w tle

        //for(Partition partition : divisions)                         //siatka kwadratów przed triangulacją
        //    partition.paintComponent(g2D);

        for(Triangle triangle : triangles)                           //siatka trójkątów
            triangle.paintComponent(g2D);

        for (Node2D node2D : points)                                 //węzły siatki
            node2D.paintComponent(g2D);

        ImageIO.write(bi, "PNG", new File("triangles+nodes.png"));
    }
}
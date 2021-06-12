package com.gerskom;

import java.util.ArrayList;
import java.util.List;

public class Triangulation {

    public List<Triangle> triangulation (List<Partition> squares) {
        List<Triangle> triangles = new ArrayList<>();
        List<Node2D> middlePoints = new ArrayList<>();

        for(Partition square : squares) {
            Node2D middlePoint = new Node2D(square.xMax - ((square.xMax - square.xMin) / 2), square.yMax - ((square.yMax - square.yMin) / 2));
            middlePoints.add(middlePoint);

            triangles.add(new Triangle(new Node2D(square.xMin, square.yMin), middlePoint, new Node2D(square.xMin, square.yMax)));
            triangles.add(new Triangle(new Node2D(square.xMin, square.yMax), middlePoint, new Node2D(square.xMax, square.yMax)));
            triangles.add(new Triangle(new Node2D(square.xMax, square.yMax), middlePoint, new Node2D(square.xMax, square.yMin)));
            triangles.add(new Triangle(new Node2D(square.xMax, square.yMin), middlePoint, new Node2D(square.xMin, square.yMin)));
        }
        return triangles;
    }
}

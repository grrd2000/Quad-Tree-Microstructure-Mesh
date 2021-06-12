package com.gerskom;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {

    final int MAX_CAPACITY = 4;
    int level;
    List<Node2D> nodes;
    QuadTree northWest = null;
    QuadTree northEast = null;
    QuadTree southWest = null;
    QuadTree southEast = null;
    Partition partition;

    QuadTree(int level, Partition partition) {
        this.level = level;
        nodes = new ArrayList<>();
        this.partition = partition;
    }

    static public void treeRecur(QuadTree tree) {
        if (tree == null)
            return;

        treeRecur(tree.northWest);
        treeRecur(tree.northEast);
        treeRecur(tree.southWest);
        treeRecur(tree.southEast);
    }

    private void divide() {
        int xOffset = this.partition.xMin + (this.partition.xMax - this.partition.xMin) / 2;
        int yOffset = this.partition.yMin + (this.partition.yMax - this.partition.yMin) / 2;

        northWest = new QuadTree(this.level + 1, new Partition(this.partition.xMin, this.partition.yMin, xOffset, yOffset));
        northEast = new QuadTree(this.level + 1, new Partition(xOffset, this.partition.yMin, this.partition.xMax, yOffset));
        southWest = new QuadTree(this.level + 1, new Partition(this.partition.xMin, yOffset, xOffset, this.partition.yMax));
        southEast = new QuadTree(this.level + 1, new Partition(xOffset, yOffset, this.partition.xMax, this.partition.yMax));
    }

    public void insert(int x, int y) {
        if (!this.partition.inRange(x, y))
            return;

        Node2D node = new Node2D(x, y);
        if (nodes.size() < MAX_CAPACITY) {
            nodes.add(node);
            return;
        }
        if (northWest == null) {
            divide();
            SimulationPanel.addBoundary(partition);
        }

        if (this.northWest.partition.inRange(x, y))
            this.northWest.insert(x, y);
        else if (this.northEast.partition.inRange(x, y))
            this.northEast.insert(x, y);
        else if (this.southWest.partition.inRange(x, y))
            this.southWest.insert(x, y);
        else if (this.southEast.partition.inRange(x, y))
            this.southEast.insert(x, y);
        else
            System.err.println("Unsupported partition!");
    }
}

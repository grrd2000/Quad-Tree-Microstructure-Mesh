package com.gerskom;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageData {

    BufferedImage image;
    public final int width;
    public final int height;
    List<Node2D> keyPoints = new ArrayList<>();

    ImageData(String path) throws IOException {
        this.image = ImageIO.read(new File(path));
        this.width = image.getWidth();
        this.height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for(int y = 0; y < height; y ++) {
                Color tmpColor = new Color(image.getRGB(x, y), false);

                if(x - 1 > 0 && x + 1 < width && y + 1 < height && y - 1 > 0) {
                    if ((!tmpColor.equals(new Color(image.getRGB(x - 1, y), false))) ||
                            (!tmpColor.equals(new Color(image.getRGB(x, y + 1), false))) ||
                            (!tmpColor.equals(new Color(image.getRGB(x, y - 1), false))) ||
                            (!tmpColor.equals(new Color(image.getRGB(x + 1, y), false)))) {

                        this.keyPoints.add(new Node2D(x, y));
                    }
                }
            }
        }
        System.out.println(keyPoints.size());
    }
}

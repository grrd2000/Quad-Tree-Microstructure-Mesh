package com.gerskom;

public class Main {

    public static void main(String[] args) {

        try {
            //new MyFrame(new ImageData("src/testImage.png"));
            new MyFrame(new ImageData("src/singleSlice.png"));
        }
        catch (Exception exc) {
            System.out.println("Interrupted: " + exc.getMessage());
        }
    }
}

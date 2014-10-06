package com.intelligence.games.triangle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class ImageGame extends JFrame {

    private static final long serialVersionUID = 1L;

    public ImageGame() throws IOException {
        super("Image Game");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BufferedImage image = ImageIO.read(new File("C:\\Documents and Settings\\srikanth.kumar\\Desktop\\Game\\Orange_rectangle.jpg"));
        setSize(image.getWidth() + 20, image.getHeight() + 40);

        /*ImagePanel imagePanel = new ImagePanel(image);
        setContentPane(imagePanel);*/
    }

    public static void main(String[] args) throws IOException {
        ImageGame game = new ImageGame();
        game.setVisible(true);
    }

}

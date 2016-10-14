package com.viv3rra;

/**
 * Created by krogh on 10/14/16.
 */
import java.awt.*;     // Using AWT's Graphics and Color
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;  // Using Swing's components and container
import java.util.*;
import java.util.List;

/** Test drawImage() thru ImageIcon */
@SuppressWarnings("serial")
public class DrawAnAnt extends JFrame {
    public static final int WORLD_SIZE = 700;
    private static double ANT_SCALE_FACTOR = 0.058997050147492625;
    public static final double ANT_SCALED_SIZE = 339 * ANT_SCALE_FACTOR;
    // Define constants for the various dimensions
    private int IMAGE_SIZE = 20;
    private List<Ant> ants;
    /*private int x = (CANVAS_SIZE / 2) - (IMAGE_SIZE / 2);
    private int y = (CANVAS_SIZE / 2) - (IMAGE_SIZE / 2);
    private int rotation = 0;*/



    private DrawCanvas canvas;    // The drawing canvas (an inner class extends JPanel)

    // Images
    private String antFilename = "/res/img/ant_square.png";
    private BufferedImage ant;   // drawImage() uses an Image object

    // Constructor to set up the GUI components and event handlers
    public DrawAnAnt(List<Ant> ants) {
        this.ants = ants;
        // Prepare the ImageIcon and Image objects for drawImage()
        ant = null;
        try {
            ant = ImageIO.read(getClass().getResource(antFilename));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot find image!!");
        }

        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(WORLD_SIZE, WORLD_SIZE));
        setContentPane(canvas);  // use JPanel as content-pane
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();  // pack the components of "super" JFrame
        setTitle("DarwinsCosmos");
        setVisible(true);
        //update();
    }

    public void update() {
        if (/*y*/ - 1 < -IMAGE_SIZE) {
            //y = CANVAS_SIZE;
        } else {
            //y -= 1;
        }
        for (Ant a : ants) {
            a.updatePos();
            //a.printTest();
        }
        repaint();
    }

    // Define inner class DrawCanvas, which is a JPanel used for custom drawing
    private class DrawCanvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            setBackground(Color.WHITE);  // Set background color for this JPanel

            for (Ant a : ants) {
                double rotationRequired = Math.toRadians(a.getDirection());
                double locationX = ant.getWidth() / 2;
                double locationY = ant.getHeight() / 2;
                AffineTransform objTrans = new AffineTransform();
                objTrans.scale(ANT_SCALE_FACTOR, ANT_SCALE_FACTOR);
                objTrans.translate(ant.getWidth() / 2, ant.getHeight() / 2);
                objTrans.rotate(rotationRequired, locationX, locationY);
                AffineTransformOp op = new AffineTransformOp(objTrans, AffineTransformOp.TYPE_BILINEAR);

                g2d.drawImage(op.filter(ant, null), a.getX(), a.getY(), null);
            }
        }
    }
}

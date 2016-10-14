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
public class Canvas extends JFrame {
    public static final int WORLD_SIZE = 700;
    private static double ANT_SCALE_FACTOR = 0.058997050147492625;
    public static final double ANT_SCALED_SIZE = 339 * ANT_SCALE_FACTOR;
    private List<Ant> ants;
    private List<AntFood> food;
    /*private int x = (CANVAS_SIZE / 2) - (IMAGE_SIZE / 2);
    private int y = (CANVAS_SIZE / 2) - (IMAGE_SIZE / 2);
    private int rotation = 0;*/



    private DrawCanvas canvas;    // The drawing canvas (an inner class extends JPanel)

    // Images
    private String antFilename = "/res/img/ant_square.png";
    private BufferedImage antImg;
    private String superAntFilename = "/res/img/super_ant_square.png";
    private BufferedImage superAntImg;

    // Constructor to set up the GUI components and event handlers
    public Canvas(List<Ant> ants, List<AntFood> food) {
        this.ants = ants;
        this.food = food;
        // Prepare the ImageIcon and Image objects for drawImage()
        antImg = null;
        superAntImg = null;
        try {
            antImg = ImageIO.read(getClass().getResource(antFilename));
            superAntImg = ImageIO.read(getClass().getResource(superAntFilename));
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
    }

    public void updateCanvas(List<Ant> ants, List<AntFood> food) {
        this.ants = ants;
        this.food = food;
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
                BufferedImage img = a.getFitness() > 0 ? superAntImg : antImg;
                double rotationRequired = Math.toRadians(a.getDirection());
                double locationX = img.getWidth() / 2;
                double locationY = img.getHeight() / 2;

                AffineTransform objTrans = new AffineTransform();
                objTrans.scale(ANT_SCALE_FACTOR, ANT_SCALE_FACTOR);
                objTrans.translate(img.getWidth() / 2, img.getHeight() / 2);
                objTrans.rotate(rotationRequired, locationX, locationY);
                AffineTransformOp op = new AffineTransformOp(objTrans, AffineTransformOp.TYPE_BILINEAR);

                g2d.drawImage(op.filter(img, null), a.getX(), a.getY(), null);
            }

            for (AntFood f : food) {
                g2d.drawRect(f.getX(), f.getY(), 5, 5);
            }
        }
    }
}

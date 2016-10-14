package com.viv3rra;

/**
 * Created by krogh on 10/14/16.
 */
import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT's event classes and listener interface
import javax.swing.*;    // Using Swing's components and containers
/**
 * Custom Graphics Example: Using key/button to move a line left or right.
 */
@SuppressWarnings("serial")
public class CGMoveALine extends JFrame {
    // Define constants for the various dimensions
    public static final int CANVAS_WIDTH = 400;
    public static final int CANVAS_HEIGHT = 400;
    public static final Color LINE_COLOR = Color.BLACK;
    public static final Color CANVAS_BACKGROUND = Color.CYAN;

    // The moving line from (x1, y1) to (x2, y2), initially position at the center
    private int x1 = CANVAS_WIDTH / 2;
    private int y1 = (CANVAS_HEIGHT / 2) - 20;
    private int x2 = x1;
    private int y2 = (CANVAS_HEIGHT / 2) + 20;
    private int y1_tmp = 1;
    private int y2_tmp = CANVAS_HEIGHT - 1;
    private boolean transition = false;

    //private DrawCanvas canvas; // The custom drawing canvas (an innder class extends JPanel)
    private DrawAnt canvas;

    // Constructor to set up the GUI components and event handlers
    public CGMoveALine() {

        // Set up a custom drawing JPanel
        //canvas = new DrawCanvas();
        canvas = new DrawAnt();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        // Add both panels to this JFrame's content-pane
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(canvas, BorderLayout.CENTER);

        // "super" JFrame fires KeyEvent
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                switch(evt.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (x1 - 10 >= 0) {
                            x1 -= 10;
                            x2 -= 10;
                        } else {
                            x1 = x1 - 10 + CANVAS_WIDTH;
                            x2 = x2 - 10 + CANVAS_WIDTH;
                        }
                        repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        x1 = (x1 + 10) % CANVAS_WIDTH;
                        x2 = (x2 + 10) % CANVAS_WIDTH;
                        repaint();
                        break;
                    case KeyEvent.VK_UP:
                        if (y1 - 10 >= 0) {
                            y1 -= 10;
                        } else {
                            y1 = y1 - 10 + CANVAS_HEIGHT;
                        }
                        if (y2 - 10 >= 0) {
                            y2 -= 10;
                        } else {
                            y2 = y2 - 10 + CANVAS_HEIGHT;
                        }
                        transition = (y1 > y2) ? true : false;
                        repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        y1 = (y1 + 10) % CANVAS_HEIGHT;
                        y2 = (y2 + 10) % CANVAS_HEIGHT;
                        transition = (y1 > y2) ? true : false;
                        repaint();
                        break;
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle the CLOSE button
        setTitle("Move a Line");
        pack();           // pack all the components in the JFrame
        setVisible(true); // show it
        requestFocus();   // set the focus to JFrame to receive KeyEvent
    }

    /**
     * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
     */
    class DrawCanvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(CANVAS_BACKGROUND);
            g.setColor(LINE_COLOR);
            System.out.println("y1: " + y1 + ", y2: " + y2);
            if (!transition) {
                g.drawLine(x1, y1, x2, y2); // Draw the line
            } else {
                g.drawLine(x1, y1_tmp, x2, y2); // Draw the line
                g.drawLine(x1, y1, x2, y2_tmp); // Draw the line
            }
        }
    }

    class DrawAnt extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(CANVAS_BACKGROUND);
            g.setColor(LINE_COLOR);
            System.out.println("y1: " + y1 + ", y2: " + y2);
            if (!transition) {
                g.drawLine(x1, y1, x2, y2); // Draw the line
            } else {
                g.drawLine(x1, y1_tmp, x2, y2); // Draw the line
                g.drawLine(x1, y1, x2, y2_tmp); // Draw the line
            }
        }
    }
}
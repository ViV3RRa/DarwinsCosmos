package com.viv3rra;

/**
 * Created by krogh on 10/14/16.
 */
public class Ant {
    private int x;
    private int y;
    private int direction;
    private double fitness = 0.0;

    public Ant() {
        this.x = 350;//(int) Math.round(Math.random() * Main.WORLD_SIZE);
        this.y = 350;//(int) Math.round(Math.random() * Main.WORLD_SIZE);
        this.direction = 0;//(int) Math.round(Math.random() * 360);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void printTest() {
        for (int i = 0; i < 4; i++) {
            int dir = 45 + (90 * i);
            int ax = (int) Math.round(Math.sin(Math.toRadians(dir)));
            int ay = -(int) Math.round(Math.cos(Math.toRadians(dir)));
            double length = Math.sqrt((ax * ax) + (ay * ay));
            System.out.println(Math.round(ax/length) + ", " + ay/length);
        }
        System.out.println("------------------------");

    }

    public void updatePos() {
        int ax = (int) Math.round(Math.sin(Math.toRadians(direction)));
        int ay = -(int) Math.round(Math.cos(Math.toRadians(direction)));
        double length = Math.sqrt((ax * ax) + (ay * ay));
        //System.out.println(Math.sqrt((ax * ax) + (ay * ay)));
        //System.out.println(ax + ", " + ay);
        if (y + (int) Math.round(ay/length) > DrawAnAnt.WORLD_SIZE) {
            y = -(int) Math.round(DrawAnAnt.ANT_SCALED_SIZE * 2);
        } else {
            y += (int) Math.round(ay/length);
        }
        //this.x += (int) Math.round(ax/length);
        //this.y += (int) Math.round(ay/length);
    }
}

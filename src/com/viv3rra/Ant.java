package com.viv3rra;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krogh on 10/14/16.
 */
public class Ant {
    private int x;
    private int y;
    private int direction;
    private int speed = 1;

    private double fitness = 0.0;

    public Ant() {
        this.x = (int) Math.round(Math.random() * DrawAnAnt.WORLD_SIZE);
        this.y = (int) Math.round(Math.random() * DrawAnAnt.WORLD_SIZE);
        this.direction = (int) Math.round(Math.random() * 360);
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

    public List<AntFood> foundFood(List<AntFood> food) {
        int mouth = x + (int) Math.round(DrawAnAnt.ANT_SCALED_SIZE);
        List<AntFood> toRemove = new ArrayList<>();

        for (AntFood f : food) {
            if (f.getX() < mouth + 20 && f.getX() > mouth - 20 && f.getY() == y) {
                fitness += 1;
                toRemove.add(f);
            }
        }
        for (AntFood f : toRemove) {
            food.remove(f);
        }
        return food;
    }

    public void updatePos() {
        // Set new position for ant based on direction
        int ax = (int) Math.round(Math.sin(Math.toRadians(direction)));
        int ay = -(int) Math.round(Math.cos(Math.toRadians(direction)));
        double length = Math.sqrt((ax * ax) + (ay * ay));

        int movementX = ((int) Math.round(ax/length)) * speed;
        int movementY = ((int) Math.round(ay/length)) * speed;

        // Wrap position to world
        if (x + movementX > DrawAnAnt.WORLD_SIZE) {
            x = -(int) Math.round(DrawAnAnt.ANT_SCALED_SIZE * 2);
        } else if (x + movementX < -Math.round(DrawAnAnt.ANT_SCALED_SIZE * 2)) {
            x = DrawAnAnt.WORLD_SIZE;
        } else {
            x += movementX;
        }

        if (y + movementY > DrawAnAnt.WORLD_SIZE) {
            y = -(int) Math.round(DrawAnAnt.ANT_SCALED_SIZE * 2);
        } else if (y + movementY < -Math.round(DrawAnAnt.ANT_SCALED_SIZE * 2)) {
            y = DrawAnAnt.WORLD_SIZE;
        } else {
            y += movementY;
        }
    }
}

package com.viv3rra;

/**
 * Created by krogh on 10/14/16.
 */
public class AntFood {
    private int x;
    private int y;

    public AntFood() {
        this.x = (int) Math.round(Math.random() * Canvas.WORLD_SIZE);
        this.y = (int) Math.round(Math.random() * Canvas.WORLD_SIZE);
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
}

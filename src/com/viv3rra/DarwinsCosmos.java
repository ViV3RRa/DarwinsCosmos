package com.viv3rra;

import java.util.ArrayList;
import java.util.List;

public class DarwinsCosmos {
    private final int ANT_NUM = 100;
    private final int FOOD_NUM = 50;
    Canvas canvas;
    List<Ant> ants = new ArrayList<>();
    List<AntFood> food = new ArrayList<>();
    private int bestScore = 0;

    public DarwinsCosmos() {
        initializeWorld();
        run();
    }

    private void initializeWorld() {
        for (int i = 0; i < ANT_NUM; i++) {
            ants.add(new Ant());
        }

        for (int i = 0; i < FOOD_NUM; i++) {
            food.add(new AntFood());
        }

        canvas = new Canvas(ants, food);
    }

    private void run() {
        while(true) {
            updateWorld();
            canvas.updateCanvas(ants, food);
            try {
                Thread.sleep(32);
            } catch (Exception e) {
            }
        }
    }

    private void updateWorld() {
        int totalFitness = 0;

        for (Ant ant : ants) {
            food = ant.foundFood(food);
            totalFitness += ant.getFitness();
            if (ant.getFitness() > bestScore) {
                bestScore = (int)ant.getFitness();
            }
            ant.updatePos();
        }
        int amountEaten = FOOD_NUM - food.size();
        for (int i = 0; i < amountEaten; i++) {
            food.add(new AntFood());
        }
        System.out.println("Food left: " + food.size() + ", TotalFitness: " + totalFitness + ", BestScore: " + bestScore);
    }

    public static void main(String[] args) {
        DarwinsCosmos cosmos = new DarwinsCosmos();
    }
}

package com.viv3rra;

import java.util.ArrayList;
import java.util.List;

public class DarwinsCosmos {
    DrawAnAnt canvas;
    List<Ant> ants = new ArrayList<>();
    List<AntFood> food = new ArrayList<>();
    private int bestScore = 0;

    public DarwinsCosmos() {
        initializeWorld();
        run();
    }

    private void initializeWorld() {
        for (int i = 0; i < 100; i++) {
            ants.add(new Ant());
        }

        for (int i = 0; i < 50; i++) {
            food.add(new AntFood());
        }

        canvas = new DrawAnAnt(ants, food);
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
        System.out.println("Food left: " + food.size() + ", TotalFitness: " + totalFitness + ", BestScore: " + bestScore);
    }

    public static void main(String[] args) {
        DarwinsCosmos cosmos = new DarwinsCosmos();
    }
}

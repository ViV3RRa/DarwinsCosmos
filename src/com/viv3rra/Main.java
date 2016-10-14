package com.viv3rra;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Ant> ants = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            ants.add(new Ant());
        }

        DrawAnAnt cosmos = new DrawAnAnt(ants);
        while(true) {
            cosmos.update();
            try {
                Thread.sleep(16);
            } catch (Exception e) {
            }
        }
    }
}

package com.viv3rra;

import java.util.List;

/**
 * Created by krogh on 10/15/16.
 */
public class Neuron {
    private int numInputs;
    private List<Double> weights;

    public Neuron(int numInputs) {
        this.numInputs = numInputs;
        createNeuron();
    }

    private void createNeuron() {
        for (int i = 0; i < numInputs+1; i++) {
            weights.add(Math.random());
        }
    }

    public List<Double> getWeights() {
        return weights;
    }
}

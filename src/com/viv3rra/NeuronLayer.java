package com.viv3rra;

import java.util.List;

/**
 * Created by krogh on 10/15/16.
 */
public class NeuronLayer {
    private int numNeurons;
    private int numInputsPerNeuron;
    private List<Neuron> neurons;

    public NeuronLayer(int numNeurons, int numInputsPerNeuron) {
        this.numNeurons = numNeurons;
        this.numInputsPerNeuron = numInputsPerNeuron;
        createLayer();
    }

    private void createLayer() {
        for (int i = 0; i < numNeurons; i++) {
            neurons.add(new Neuron(numInputsPerNeuron));
        }
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }
}

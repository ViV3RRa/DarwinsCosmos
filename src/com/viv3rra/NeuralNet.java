package com.viv3rra;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krogh on 10/15/16.
 */
public class NeuralNet {
    private int numInputs;
    private int numOutputs;
    private int numHiddenLayers;
    private int neuronsPerHiddenLayer;
    private List<NeuronLayer> neuronLayers;

    public NeuralNet(int numInputs, int numOutputs, int numHiddenLayers, int neuronsPerHiddenLayer) {
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        this.numHiddenLayers = numHiddenLayers;
        this.neuronsPerHiddenLayer = neuronsPerHiddenLayer;
        this.neuronLayers = new ArrayList<>();
        createNeuralNet();
    }

    public void createNeuralNet() {
        if (numHiddenLayers > 0) {
            neuronLayers.add(new NeuronLayer(neuronsPerHiddenLayer, numInputs));

            for (int i = 0; i < numHiddenLayers; i++) {
                neuronLayers.add(new NeuronLayer(neuronsPerHiddenLayer, neuronsPerHiddenLayer));
            }

            neuronLayers.add(new NeuronLayer(numOutputs, neuronsPerHiddenLayer));
        } else {
            neuronLayers.add(new NeuronLayer(numOutputs, numInputs));
        }
    }

    public List<Double> getWeights() {
        List<Double> weights = new ArrayList<>();

        for (int i = 0; i < neuronLayers.size(); i++) {
            NeuronLayer layer = neuronLayers.get(i);

            for (int j = 0; j < layer.getNeurons().size(); j++) {
                Neuron neuron = layer.getNeurons().get(j);

                for (int k = 0; k < neuron.getWeights().size(); k++) {
                    weights.add(neuron.getWeights().get(k));
                }
            }
        }
        return weights;
    }

    public int getNumberOfWeights() {
        int numOfWeights = 0;

        for (int i = 0; i < neuronLayers.size(); i++) {
            NeuronLayer layer = neuronLayers.get(i);

            for (int j = 0; j < layer.getNeurons().size(); j++) {
                Neuron neuron = layer.getNeurons().get(j);

                for (int k = 0; k < neuron.getWeights().size(); k++) {
                    numOfWeights += 1;
                }
            }
        }
        return numOfWeights;
    }

    public void putWeights(List<Double> weights) {
        int weightNumber = 0;

        for (int i = 0; i < neuronLayers.size(); i++) {
            NeuronLayer layer = neuronLayers.get(i);

            for (int j = 0; j < layer.getNeurons().size(); j++) {
                Neuron neuron = layer.getNeurons().get(j);

                for (int k = 0; k < neuron.getWeights().size(); k++) {
                    neuron.getWeights().set(k, weights.get(weightNumber));
                    weightNumber += 1;
                }
            }
        }
    }

    public List<Double> update(List<Double> inputs) {
        List<Double> outputs = new ArrayList<>();
        int weight = 0;

        if (inputs.size() != numInputs) {
            return outputs;
        }

        for (int i = 0; i < numHiddenLayers; i++) {
            if (i > 0) {
                inputs = outputs;
            }
            outputs.clear();
            weight = 0;

            /*for (int j = 0; j < ) {
                // TODO: complete this method from CNeuralNet.cpp
            }*/
        }

        return null;
    }

    private double sigmoid(double activation, double response) {
        return (1/( 1 + Math.exp(-activation / response)));
    }
}

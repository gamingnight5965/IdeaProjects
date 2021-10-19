package com.testing.tictactoe;


import deepnetts.net.layers.activation.ActivationType;
import deepnetts.net.loss.LossType;
import deepnetts.net.FeedForwardNetwork;

public class AI {
        FeedForwardNetwork nueralNet = FeedForwardNetwork.builder()
                .addInputLayer(19)
                .addFullyConnectedLayer(15)
                .addFullyConnectedLayer(15)
                .addFullyConnectedLayer(15)
                .addOutputLayer(1, ActivationType.SIGMOID)
                .lossFunction(LossType.CROSS_ENTROPY)
                .build();
}

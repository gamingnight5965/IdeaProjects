package com.testing.tictactoe;


import deepnetts.eval.Evaluators;
import deepnetts.net.layers.activation.ActivationType;
import deepnetts.net.loss.LossType;
import deepnetts.net.FeedForwardNetwork;
import deepnetts.data.DataSets;

import javax.visrec.ml.data.DataSet;
import javax.visrec.ml.eval.EvaluationMetrics;
import java.io.IOException;


public class AI {
        private DataSet boardDataSet;
        private FeedForwardNetwork nueralNet = FeedForwardNetwork.builder()
                .addInputLayer(19)
                .addFullyConnectedLayer(15)
                .addFullyConnectedLayer(15)
                .addFullyConnectedLayer(15)
                .addOutputLayer(9, ActivationType.SIGMOID)
                .lossFunction(LossType.CROSS_ENTROPY)
                .build();
        private DataSet[] trainAndTestSet;

        public AI() {
                getTrainData();
//                train();

        }


        private void getTrainData() {
                try {
//                        System.out.println(DataSets.detectCsvFormat("TrainData.csv"));
                        boardDataSet = DataSets.readCsv("TrainData.csv", 9, 1, true);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                trainAndTestSet = boardDataSet.split(0.8, 0.2);
        }
        private void train(){
                nueralNet.getTrainer().setMaxError(0.03f)
                        .setMaxEpochs(10000)
                        .setLearningRate(0.001f);
                nueralNet.train(trainAndTestSet[0]);
        }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadaci;

import network.NeuralNetwork;
import network.SigmoidTransfer;

/**
 *
 * @author andre
 */
public class Zadaci {

    public void zadatak1() {
        int[] layers = new int[]{2, 2, 1};

        NeuralNetwork net = new NeuralNetwork(layers, 0.6, new SigmoidTransfer());

        /* Learning */
        for (int i = 0; i < 10000; i++) {
            double[] inputs = new double[]{Math.round(Math.random()), Math.round(Math.random())};
            double[] output = new double[1];
            double error;

            if ((inputs[0] == 1.0) || (inputs[1] == 1.0)) {
                output[0] = 1.0;
            } else {
                output[0] = 0.0;
            }

            System.out.println(inputs[0] + " or " + inputs[1] + " = " + output[0]);

            error = net.backPropagate(inputs, output);
            System.out.println("Error at step " + i + " is " + error);
        }

        System.out.println("Learning completed!");

        /* Test */
        double[] inputs = new double[]{0.0, 1.0};
        double[] output = net.execute(inputs);

        System.out.println(inputs[0] + " or " + inputs[1] + " = " + Math.round(output[0]) + " (" + output[0] + ")");

    }

    public void zadatak2() {
        int[] layers = new int[]{2, 2, 1};

        NeuralNetwork net = new NeuralNetwork(layers, 0.6, new SigmoidTransfer());

        /* Learning */
        for (int i = 0; i < 10000; i++) {
            double[] inputs = new double[]{Math.round(Math.random()), Math.round(Math.random())};
            double[] output = new double[1];
            double error;

            if ((inputs[0] == inputs[1]) && (inputs[0] == 1)) {
                output[0] = 1.0;
            } else {
                output[0] = 0.0;
            }

            System.out.println(inputs[0] + " and " + inputs[1] + " = " + output[0]);

            error = net.backPropagate(inputs, output);
            System.out.println("Error at step " + i + " is " + error);
        }

        System.out.println("Learning completed!");

        /* Test */
        double[] inputs = new double[]{1.0, 0.0};
        double[] output = net.execute(inputs);

        System.out.println(inputs[0] + " and " + inputs[1] + " = " + Math.round(output[0]) + " (" + output[0] + ")");

    }
}

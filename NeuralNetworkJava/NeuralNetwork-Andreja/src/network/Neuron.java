/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

/**
 *
 * @author andre
 */
public class Neuron {
    public double value;
    public double[] weights;
    public double bias;
    public double delta;
    
    public Neuron(int prevLayerSize) {
        this.weights = new double[prevLayerSize];
        this.bias = Math.random() / 1d;
        this.delta = Math.random() / 1d;
        this.value = Math.random() / 1d;
        
        for (int i = 0; i < this.weights.length; i++) 
            this.weights[i] = Math.random() / 1d;
    }
}

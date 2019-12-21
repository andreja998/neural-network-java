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
public class Layer {
    public Neuron neurons[];
    public int length;
    
    public Layer(int layerSize, int prevLayerSize) {
        this.length = layerSize;
        this.neurons = new Neuron[this.length];
        
        for (int i = 0; i < this.length; i++)
            this.neurons[i] = new Neuron(prevLayerSize);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author andre
 */
public class NeuralNetwork implements Cloneable {
    protected double learningRate = 0.6;
    protected Layer[] layers;
    protected TransferFunction transferFunction;
    
    public NeuralNetwork(int[] layers, double learningRate, TransferFunction fun) {
        this.learningRate = learningRate;
        this.transferFunction = fun;
        this.layers = new Layer[layers.length];
        
        for (int i = 0; i < layers.length; i++) {
            if (i != 0) {
                this.layers[i] = new Layer(layers[i], layers[i - 1]);
            } else {
                this.layers[i] = new Layer(layers[i], 0);
            }
        }
    }
    
    public double[] execute(double[] input) {
        int i, j, k;
        double newValue;
        double output[] = new double[this.layers[this.layers.length - 1].length];
        
        for (i = 0; i < this.layers[0].length; i++)
            this.layers[0].neurons[i].value = input[i];
        
        for (k = 1; k < this.layers.length; k++) {
            for (i = 0; i < this.layers[k].length; i++) {
                newValue = 0.0;
                for (j = 0; j < this.layers[k - 1].length; j++) {
                    newValue += this.layers[k].neurons[i].weights[j] * this.layers[k - 1].neurons[j].value;
                }
                newValue += this.layers[k].neurons[i].bias;
                
                this.layers[k].neurons[i].value = this.transferFunction.evaluate(newValue);
            }
        }
        
        for (i = 0; i < this.layers[this.layers.length - 1].length; i++) 
            output[i] = this.layers[this.layers.length - 1].neurons[i].value;
        
        return output;
    }
    
    public double backPropagate(double[] input, double[] output) {
        double newOutput[] = execute(input);
        double error;
        int i, j, k;
        
        for (i = 0; i < this.layers[this.layers.length - 1].length; i++) {
            error = output[i] - newOutput[i];
            this.layers[this.layers.length - 1].neurons[i].delta = error * this.transferFunction.evaluateDerivate(newOutput[i]);
        }   
        
        for (k = this.layers.length - 2; k >= 0; k--) {
            for (i = 0; i < this.layers[k].length; i++) {
                error = 0.0;
                for (j = 0; j < this.layers[k + 1].length; j++) {
                    error += this.layers[k + 1].neurons[j].delta * this.layers[k + 1].neurons[j].weights[i];
                }
                
                this.layers[k].neurons[i].delta = error * this.transferFunction.evaluateDerivate(this.layers[k].neurons[i].value);
            }
            
            for (i = 0; i < this.layers[k + 1].length; i++) {
                for (j = 0; j < this.layers[k].length; j++) {
                    this.layers[k + 1].neurons[i].weights[j] += this.learningRate * this.layers[k + 1].neurons[i].delta * this.layers[k].neurons[j].value;
                }
                this.layers[k + 1].neurons[i].bias += this.learningRate * this.layers[k + 1].neurons[i].delta;
            }
        }
        
        error = 0.0;
        for (i = 0; i < output.length; i++)
            error += Math.abs(newOutput[i] - output[i]);
        
        error /= output.length;
        
        return error;
    }
    
    public boolean save(String path) {
        try {
            FileOutputStream fout = new FileOutputStream(path);
            try (ObjectOutputStream oos = new ObjectOutputStream(fout)) {
                oos.writeObject(this);
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }
    
    public static NeuralNetwork load(String path) {
        try {
            NeuralNetwork net;
            
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream oos = new ObjectInputStream(fin);
            net = (NeuralNetwork) oos.readObject();
            oos.close();

            return net;
        } catch (Exception e) {
            return null;
        }
    }
    
    public double getLearningRate() {
        return this.learningRate;
    }
    
    public void setLearningRate(double rate) {
        this.learningRate = rate;
    }
    
    public void setTransferFunction(TransferFunction fun) {
        this.transferFunction = fun;
    }
    
    public int getInputLayerSize() {
        return this.layers[0].length;
    }
    
    public int getOutputLayerSize() {
        return this.layers[this.layers.length - 1].length;
    }
}

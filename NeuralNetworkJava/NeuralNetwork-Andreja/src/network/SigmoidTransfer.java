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
public class SigmoidTransfer implements TransferFunction {
    
    @Override
    public double evaluate(double value) {
        return 1 / (1 + Math.pow(Math.E, - value));
    }
    
    @Override
    public double evaluateDerivate(double value) {
        return (value - Math.pow(value, 2));
    }
}

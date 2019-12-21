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
public interface TransferFunction {
    public double evaluate(double value);
    public double evaluateDerivate(double value);
}

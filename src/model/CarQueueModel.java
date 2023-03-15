/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import jade.core.AID;

/**
 *
 * @author ZT
 */
public class CarQueueModel {
    AID car;
    int priority;

    public CarQueueModel(AID car, int priority) {
        this.car = car;
        this.priority = priority;
    }

    public AID getCar() {
        return car;
    }

    public void setCar(AID car) {
        this.car = car;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    
}

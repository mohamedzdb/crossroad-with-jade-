/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviours.car;

import agents.Car;
import consts.Road;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;

/**
 *
 * @author ZT
 */
public class StartDriveBehaviour extends Behaviour{
    int traficLightPoint = 3;
   

    @Override
    public boolean done() {
        return traficLightPoint == 0;
    }

    @Override
    public void action() {
        traficLightPoint --;
    }
    @Override
    public void onStart() {
        System.out.println( myAgent.getLocalName()+" in the road...");
    }

    @Override
    public int onEnd() {
         Car car = (Car) myAgent; 
         System.out.println( myAgent.getLocalName()+" in light trafic point...");
         return 1;
    }
    
}

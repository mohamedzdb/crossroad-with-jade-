/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviours.car;

import jade.core.behaviours.Behaviour;

/**
 *
 * @author ZT
 */
public class GoToDestinationBehaviour extends Behaviour {
    int distinationEndPoint = 3;
   

    @Override
    public boolean done() {
        return distinationEndPoint == 0;
    }

    @Override
    public void action() {
        distinationEndPoint --;
    }

    @Override
    public int onEnd() {
        System.out.println( myAgent.getLocalName()+" finish driving...");
        return 1;
    }
    
}

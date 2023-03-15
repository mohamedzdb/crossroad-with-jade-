/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviours.car;

import agents.Car;
import consts.Messages;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author ZT
 */
public class WaitGreenLightBehaviour extends ParallelBehaviour {
         boolean isGreen;
    public WaitGreenLightBehaviour(){
        super(ParallelBehaviour.WHEN_ALL);
        addSubBehaviour(new Behaviour() {
            
                
            @Override
            public void action() {
                ACLMessage msg = myAgent.receive();
                if(msg != null){
                    isGreen = msg.getContent().equals(Messages.GREEN_LIGHT_MSG);
                }else{
                    block();
                }
            }

            @Override
            public boolean done() {
                return isGreen;
            }
        });
        addSubBehaviour(new Behaviour() {
            @Override
            public void onStart() {
                  System.out.println( myAgent.getLocalName()+" wait for green light...");
                }
            @Override
            public void action() {
                Car car = (Car) myAgent;
                ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
                msg.addReceiver(new AID("taficlights",AID.ISLOCALNAME));
                msg.setContent(String.valueOf(car.getSource()));
                myAgent.send(msg);
            }

            @Override
            public boolean done() {
                return isGreen;
            }
        });
        
    }

    }
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviours.car;

import agents.Car;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import utils.Priority;

/**
 *
 * @author ZT
 */
public class CommuncationWithPoliceBehaviour extends ParallelBehaviour  {
    public boolean priority;
    public CommuncationWithPoliceBehaviour(){
        addSubBehaviour(new Behaviour() {   
            @Override
            public void action() {
                MessageTemplate mt = MessageTemplate.MatchSender(new AID("police",AID.ISLOCALNAME));
                ACLMessage msg = myAgent.receive(mt);
                AID sender = new AID("police",AID.ISLOCALNAME);
                if(msg != null){
                        if(msg.getPerformative() == ACLMessage.AGREE){
                             priority = true;
                             System.out.println( myAgent.getLocalName()+" give me the priority...");
                        }else if(msg.getPerformative() == ACLMessage.CANCEL){
                            System.out.println( myAgent.getLocalName()+" cancel...");
                        }
                    
                }else{
                    block();
                }
            }

            @Override
            public boolean done() {
                return priority;
            }

            @Override
            public int onEnd() {
                System.out.println( myAgent.getLocalName()+" in destination road...");
                return 1;
            }
            
        });
        addSubBehaviour(new Behaviour() {
            
            
            
            @Override
            public void onStart() {
                  System.out.println( myAgent.getLocalName()+" wait fo police decision...");
                }
            @Override
            public void action() { 
                Car car = (Car) myAgent;
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(new AID("police",AID.ISLOCALNAME));
                msg.setContent(String.valueOf(car.getSource())+"-"+Priority.getMyPriority(car.getSource(),car.getDestination()));
                myAgent.send(msg);
            }
           
            @Override
            public boolean done() {
                return priority;
            }
        });
        
    }
    
}

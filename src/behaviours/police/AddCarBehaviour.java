/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviours.police;

import agents.Police;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import model.CarQueueModel;

/**
 *
 * @author ZT
 */
public class AddCarBehaviour extends ParallelBehaviour {
    //to recive 2 messages parallel
   public AddCarBehaviour(){
       addSubBehaviour(new CyclicBehaviour() {
        @Override
        public void action() {
        addCar();
    }
    
   });
       addSubBehaviour(new CyclicBehaviour() {
        @Override
        public void action() {
        addCar();
    }
    
   });
       
    
    
    }
   CarQueueModel getCarModel(AID car, int priority){
        return new CarQueueModel(car, priority);
    };
   void addCar(){
       Police police = (Police) myAgent;
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage msg = myAgent.receive(mt);
        if (msg == null) {
            block();
        } else {
            int priority = Integer.parseInt(msg.getContent().split("-")[1]);
            int source = Integer.parseInt(msg.getContent().split("-")[0]);
            police.addCar(source,getCarModel(msg.getSender(),priority) );
        }
   }
}

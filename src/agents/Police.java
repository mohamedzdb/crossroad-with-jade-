/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;
import static consts.Road.*;
import behaviours.police.AddCarBehaviour;
import static consts.Messages.*;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CarQueueModel;

/**
 *
 * @author ZT
 */
public class Police extends Agent {
    private Queue<CarQueueModel> northQueue = new LinkedList();
    private Queue<CarQueueModel> eastQueue = new LinkedList();
    private Queue<CarQueueModel> southQueue = new LinkedList();
    private Queue<CarQueueModel> westQueue = new LinkedList();
    
    @Override
    protected void setup() {
        /*DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());
            ServiceDescription sd = new ServiceDescription();
            sd.setName("police");
            sd.setType("police");
            sd.addLanguages(FIPANames.ContentLanguage.FIPA_SL);
            dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException ex) {
            Logger.getLogger(Police.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        addBehaviour(new AddCarBehaviour());
    }
    public void addCar(int road,CarQueueModel carQueueModel){
        switch(road){
            case NORTH_ROAD:
                if(southQueue.isEmpty()){
                   passCar(carQueueModel.getCar());
                }else{
                    northQueue.add(carQueueModel);
                    priorityDecisionHirz();
                }
                break;
            case SOUTH_ROAD:
                if(northQueue.isEmpty()){
                   passCar(carQueueModel.getCar());
                }else{
                southQueue.add(carQueueModel);
                priorityDecisionHirz();
                }
                break;
            case EAST_ROAD:
                if(westQueue.isEmpty()){
                   passCar(carQueueModel.getCar());
                }else{
                   eastQueue.add(carQueueModel); 
                   priorityDecisionVect();
                }
                break;
            case WEST_ROAD:
                if(eastQueue.isEmpty()){
                   passCar(carQueueModel.getCar());
                }else{
                   westQueue.add(carQueueModel);
                   priorityDecisionVect();
                }
                break;
        }
    }

    private void passCar(AID car) {
        ACLMessage msg = new ACLMessage(ACLMessage.AGREE);
        msg.addReceiver(car);
        msg.setContent(HIGHER_PRIORITY_MSG);
        send(msg);
    }
    private void waitCar(AID car) {
        ACLMessage msg = new ACLMessage(ACLMessage.CANCEL);
        msg.addReceiver(car);
        msg.setContent(LOWER_PRIORITY_MSG);
        send(msg);
    }
    
    void priorityDecisionHirz(){
           CarQueueModel car1 =  northQueue.peek();
           CarQueueModel car2 =  southQueue.peek();
           if(car1.getPriority() > car2.getPriority()){
               waitCar(car2.getCar());
               passCar(car1.getCar());
           }else if(car1.getPriority() < car2.getPriority()){
               waitCar(car1.getCar());
               passCar(car2.getCar());
           }else{
              passCar(car1.getCar());
              passCar(car2.getCar());
           }
        
        
    }
    void priorityDecisionVect(){
           CarQueueModel car1 =  eastQueue.peek();
           CarQueueModel car2 =  westQueue.peek();
           if(car1.getPriority() > car2.getPriority()){
               waitCar(car2.getCar());
               passCar(car1.getCar());
           }else if(car1.getPriority() < car2.getPriority()){
               waitCar(car1.getCar());
               passCar(car2.getCar());
           }else{
              passCar(car1.getCar());
              passCar(car2.getCar());
           }
        
        
    }
    
    
    public void removeCar(int road,AID car){
        switch(road){
            case NORTH_ROAD:
                if(!southQueue.isEmpty()){
                   passCar(southQueue.peek().getCar());
                   northQueue.remove();
                }
                break;
            case SOUTH_ROAD:
                if(!northQueue.isEmpty()){
                   passCar(northQueue.peek().getCar());
                   southQueue.remove();
                }
                break;
            case EAST_ROAD:
                if(!westQueue.isEmpty()){
                   passCar(westQueue.peek().getCar());
                   eastQueue.remove();
                }
                break;
            case WEST_ROAD:
                if(!eastQueue.isEmpty()){
                   passCar(eastQueue.peek().getCar());
                   westQueue.remove();
                }
                break;
        }
    }



}

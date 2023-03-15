
package behaviours.car;


import agents.Car;
import consts.Road;
import jade.core.behaviours.OneShotBehaviour;


public class InitCarBehaviour extends OneShotBehaviour{
    int source;
    int destination;

    public InitCarBehaviour(int source, int destination) {
        this.source = source;
        this.destination = destination;
    }
    
    
    @Override
    public void action() {
       Car car = (Car) myAgent; 
       car.setDestination(destination);
       car.setSource(source);
       
    }

    @Override
    public void onStart() {
        System.out.println("Init car:"+ myAgent.getLocalName());
    }

    @Override
    public int onEnd() {
         Car car = (Car) myAgent; 
         System.out.println("i'm "+car.getAID().getLocalName()+": "+Road.getRoad(car.getSource())+ " ======> "+Road.getRoad(car.getDestination()));
         return 1;
    }
    
   
    
}

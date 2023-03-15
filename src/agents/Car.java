
package agents;

import consts.Road;
import behaviours.car.CommuncationWithPoliceBehaviour;
import behaviours.car.GoToDestinationBehaviour;
import behaviours.car.InitCarBehaviour;
import behaviours.car.StartDriveBehaviour;
import behaviours.car.WaitGreenLightBehaviour;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;
import utils.Priority;
import static utils.Priority.*;

public class Car extends Agent {
    int source ;
    int destination;
    int speed ;
    
    @Override
    protected void setup() {
        
        Object[] args = getArguments();
        SequentialBehaviour sequentialBehaviour = new SequentialBehaviour();
        
       
        sequentialBehaviour.addSubBehaviour(new InitCarBehaviour((int) args[0],(int) args[1]));
        sequentialBehaviour.addSubBehaviour(new StartDriveBehaviour());
        sequentialBehaviour.addSubBehaviour(new WaitGreenLightBehaviour());
        /*if(Priority.getMyPriority(getSource(),getDestination()) != HIGHER_PRIORITY  ){
            sequentialBehaviour.addSubBehaviour(new CommuncationWithPoliceBehaviour());
        }*/
        sequentialBehaviour.addSubBehaviour(new CommuncationWithPoliceBehaviour());
        sequentialBehaviour.addSubBehaviour(new GoToDestinationBehaviour());
        
        addBehaviour(sequentialBehaviour);
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int direction) {
        this.destination = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    
    
}

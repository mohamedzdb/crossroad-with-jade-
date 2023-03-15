/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import static consts.Road.*;
import static consts.LightTrafic.*;
import static consts.Messages.*;

/**
 *
 * @author ZT
 */
public class TraficLight extends Agent{
    private int northTraficLight = RED_LIGHT;
    private int southTraficLight = RED_LIGHT;
    private int eastTraficLight = GREEN_LIGHT;
    private int westTraficLight = GREEN_LIGHT;
    @Override
    protected void setup() {
        ParallelBehaviour paralleleBehaviour = new ParallelBehaviour();
        paralleleBehaviour.addSubBehaviour(new TickerBehaviour(this, 30000){
            @Override
            protected void onTick() {
                changeTraficLights();
            }
        });
       paralleleBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if(msg != null){

                    ACLMessage reply = msg.createReply();
                    reply.setContent(getTraficLightStatus(Integer.parseInt(msg.getContent())) == GREEN_LIGHT ? GREEN_LIGHT_MSG : RED_LIGHT_MSG );
                    myAgent.send(reply);
                }else{
                    block();
                }
            }
        });
        addBehaviour(paralleleBehaviour);
    }
    private void changeTraficLights(){
        if(this.eastTraficLight == GREEN_LIGHT  && this.westTraficLight == GREEN_LIGHT){
            setHirz(GREEN_LIGHT);
            setVect(RED_LIGHT);
        }else{
            setHirz(RED_LIGHT);
            setVect(GREEN_LIGHT);
        }
    }
    private void setHirz(int light){
        this.northTraficLight = light;
        this.southTraficLight = light;
    }
    private void setVect(int light){
        this.eastTraficLight = light;
        this.westTraficLight = light;
    }
    private int getTraficLightStatus(int road){
        int status = RED_LIGHT;
        switch(road){
            case NORTH_ROAD:
                status = this.northTraficLight;
                break;
            case SOUTH_ROAD:
                status = this.southTraficLight;
                break;
            case EAST_ROAD:
                status = this.eastTraficLight;
                break;
            case WEST_ROAD:
                status = this.westTraficLight;
                break;
        }
        return status;
    }


}

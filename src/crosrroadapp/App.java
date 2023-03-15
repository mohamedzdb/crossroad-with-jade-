/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crosrroadapp;

import agents.Car;
import agents.Police;
import agents.TraficLight;
import static consts.Road.*;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZT
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        jade.core.Runtime run = jade.core.Runtime.instance();
        Profile p = new ProfileImpl();
        p.setParameter(Profile.GUI, "true");
        ContainerController container = run.createMainContainer(p);
           try {
            AgentController car1 = container.createNewAgent("car1",Car.class.getName(),new Object[]{SOUTH_ROAD,WEST_ROAD});
            AgentController car2 = container.createNewAgent("car2",Car.class.getName(),new Object[]{NORTH_ROAD,WEST_ROAD});
            AgentController car3 = container.createNewAgent("car3",Car.class.getName(),new Object[]{EAST_ROAD,WEST_ROAD});
            AgentController taficlights = container.createNewAgent("taficlights",TraficLight.class.getName(),null);
            AgentController police = container.createNewAgent("police",Police.class.getName(),null);
            car1.start();
            car2.start();
            car3.start();
            taficlights.start();
            police.start();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import static consts.Road.*;
/**
 *
 * @author ZT
 */
public class Priority {
     public  static final int HIGHER_PRIORITY = 100;
     public  static final int LOWER_PRIORITY = 50;
    public static int getMyPriority(int source,int destination){
        int priority = LOWER_PRIORITY;
        switch(source){
            case NORTH_ROAD:
                priority = northRoad(destination);
                break;
            case SOUTH_ROAD:
                priority = southRoad(destination);
                break;
            case EAST_ROAD:
                priority = eastRoad(destination);
                break;
            case WEST_ROAD:
                priority = westRoad(destination);
                break;
        }
            
            return priority;
    }
    static int northRoad(int destination){
        int priority=  LOWER_PRIORITY;
        switch(destination){
            case EAST_ROAD:
               priority =  LOWER_PRIORITY;
               break;
            case WEST_ROAD:
               priority =  HIGHER_PRIORITY;
               break;
            case SOUTH_ROAD:
               priority =  HIGHER_PRIORITY;
               break;
        }
        return priority;
    }
    static int southRoad(int destination){
        int priority=  LOWER_PRIORITY;
        switch(destination){
            case EAST_ROAD:
               priority =  HIGHER_PRIORITY;
               break;
            case WEST_ROAD:
               priority =  LOWER_PRIORITY;
               break;
            case NORTH_ROAD:
               priority =  HIGHER_PRIORITY;
               break;
        }
        return priority;
    }
    static int eastRoad(int destination){
        int priority=  LOWER_PRIORITY;
        switch(destination){
            case WEST_ROAD:
               priority =  HIGHER_PRIORITY;
               break;
            case SOUTH_ROAD:
               priority =  LOWER_PRIORITY;
               break;
            case NORTH_ROAD:
               priority =  HIGHER_PRIORITY;
               break;
        }
        return priority;
    }
    static int westRoad(int destination){
        int priority=  LOWER_PRIORITY;
        switch(destination){
            case EAST_ROAD:
               priority =  HIGHER_PRIORITY;
               break;
            case SOUTH_ROAD:
               priority =  HIGHER_PRIORITY;
               break;
            case NORTH_ROAD:
               priority =  LOWER_PRIORITY;
               break;
        }
        return priority;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consts;

/**
 *
 * @author ZT
 */
public class Road {
    public final static int SOUTH_ROAD = 1;
    public final static int NORTH_ROAD = 2;
    public final static int EAST_ROAD = 3;
    public final static int WEST_ROAD = 4;
    public static String getRoad(int road){
        String roadRes = "south";
        switch(road){
            case SOUTH_ROAD:
                roadRes =  "south";
                break;
            case NORTH_ROAD:
                roadRes =  "north";
                break;     
            case EAST_ROAD:
                roadRes =  "est";
                break;  
            case WEST_ROAD:
                roadRes =  "west";
                break;  
        }
        return roadRes;
    }
}

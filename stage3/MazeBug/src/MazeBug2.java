import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug2 extends MazeBug {
    
    public Location chooseDirection(ArrayList<Location> locs) {
        int maxIndex = -1;
        int max = -1;
        
        for (int i = 0 ; i < locs.size() ; i++) {
            int direction = getLocation().getDirectionToward(locs.get(i));
            switch (direction) {
            case Location.EAST:
                if (directionCount.get(Location.EAST) > max) {
                    max = directionCount.get(Location.EAST);
                    maxIndex = i;
                }
                break;
            case Location.SOUTH:
                if (directionCount.get(Location.SOUTH) > max) {
                    max = directionCount.get(Location.SOUTH);
                    maxIndex = i;
                }
                break;
            case Location.WEST:
                if (directionCount.get(Location.WEST) > max) {
                    max = directionCount.get(Location.WEST);
                    maxIndex = i;
                }
                break;
            case Location.NORTH:
                if (directionCount.get(Location.NORTH) > max) {
                    max = directionCount.get(Location.NORTH);
                    maxIndex = i;
                }
                break;
            default:
                break;
            }
        }

        return locs.get(maxIndex);
    }
    
}

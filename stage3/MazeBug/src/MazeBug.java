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
public class MazeBug extends Bug {
    protected Location next;

    protected boolean isEnd = false;
    protected Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
    protected Integer stepCount = 0;
    protected boolean hasShown = false; //final message has been shown

    /* 
     * Record the time each direction selected  
     * key:   direction
     * value: times
     */
    protected Map<Integer, Integer> directionCount;
    
    /**
     * Constructs a maze bug
     */
    public MazeBug() {
        
        // initial them to 1 is easier to calculate.
        directionCount = new HashMap<Integer, Integer>();
        directionCount.put(Location.EAST, 1);
        directionCount.put(Location.SOUTH, 1);
        directionCount.put(Location.WEST, 1);
        directionCount.put(Location.NORTH, 1);
        
        setColor(Color.GREEN);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        boolean willMove = canMove();
        if (isEnd) {
        //to show step count when reach the goal        
            if (!hasShown) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } else if (willMove) {
            move();
            //increase step count when move 
            stepCount++;
        } 
    }

    /**
     * Find all positions that can be move to.
     * 
     * @param loc
     *            the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return null;
        ArrayList<Location> valid = new ArrayList<Location>();
        
        // it can only turn four direction
        int[] direction = {Location.EAST, Location.SOUTH, 
                            Location.NORTH, Location.WEST};
        
        for (int i = 0 ; i < direction.length ; i++) {
            Location tempLoc = getLocation().getAdjacentLocation(direction[i]);
            if (gr.isValid(tempLoc)) {
                Actor actor = gr.get(tempLoc);
                if ((actor == null) || (actor instanceof Flower)) {
                    valid.add(tempLoc);
                }
                // if bug can move to the red lock then you win!
                else if (actor instanceof Rock && actor.getColor().equals(Color.RED)) {
                    isEnd = true;
                }
            }
        }
        
        return valid;
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * 
     * @return true if this bug can move.
     */
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }

        ArrayList<Location> allLocation = getValid(getLocation());
        
        // the first element in allLocation list is current position.
        allLocation.add(0, getLocation());
        
        ArrayList<Location> emptyLocation = new ArrayList<Location>();
        
        
        for (int i = 1 ; i < allLocation.size() ; i++) {
            if (gr.get(allLocation.get(i)) == null) {
                emptyLocation.add(allLocation.get(i));
            }
        }
        
        /* 
         * check if there is a empty location can move
         * if yes, push current position into stack
         * if none, pop the stack to get the last position to move (go back)
         */
        
        if (!emptyLocation.isEmpty()) {
            next = chooseDirection(emptyLocation);
            crossLocation.push(allLocation);
            int direction = getLocation().getDirectionToward(next);
            directionCount.put(direction, directionCount.get(direction) + 1);
        }
        else if (!crossLocation.empty()) {
            allLocation = crossLocation.pop();
            next = allLocation.get(0);
            int direction = next.getDirectionToward(getLocation());
            directionCount.put(direction, directionCount.get(direction) - 1);
        }
        return true;
    }
    
    /*
     * choose which direction to move is the best.
     */
    public Location chooseDirection(ArrayList<Location> locs) {
        int random = (int) (Math.random() * locs.size());
        return locs.get(random);
    }
    
    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        if (gr.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
}

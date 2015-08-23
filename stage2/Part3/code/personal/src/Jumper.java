/* 
 * Jumper.java
 * 
 * @author Jiahua Wu
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * A <code>Jumper</code> is an actor that can jump and turn. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Actor
{
    /**
     * Constructs a red Jumper.
     */
    public Jumper()
    {
        setColor(Color.RED);
    }

    /**
     * Constructs a Jumper of a given color.
     * @param JumperColor the color for this Jumper
     */
    public Jumper(Color jumperColor)
    {
        setColor(jumperColor);
    }

    /**
     * jumps if it can jump, move if it can move, turns otherwise.
     */
    public void act()
    {
        if (canJump()) {
            jump();
        }
        else if (canMove()) {
        	move();
        }
        else {
            turn();
        }
    }

    /**
     * Turns the Jumper random degrees without changing its location.
     */
    public void turn()
    {
    	int degree = (int) (Math.round(Math.random() * 8) * 45);
        setDirection(getDirection() + degree);
    }

    /**
     * jumps the Jumper forward,
     */
    public void jump()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection())
        				   .getAdjacentLocation(getDirection());
        if (gr.isValid(next)) {
            moveTo(next);
        }
        else {
            removeSelfFromGrid();
        }
    }

    /**
     * moves the Jumper forward,
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next)) {
            moveTo(next);
        }
        else {
            removeSelfFromGrid();
        }
    }

    
    /**
     * Tests whether this Jumper can jump forward into a location that is empty or
     * contains a flower.
     * @return true if this Jumper can jump.
     */
    public boolean canJump()
    {
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection())
        				   .getAdjacentLocation(getDirection());
        return canPutOntoLocation(next);
        // ok to jump into empty location or onto flower
        // not ok to jump onto any other actor
    }
    
    /**
     * Tests whether this Jumper can move forward into a location that is empty or
     * contains a flower.
     * @return true if this Jumper can move.
     */
    public boolean canMove()
    {
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        return canPutOntoLocation(next);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
    
    /**
     * Query whether this location can put the Jumper
     * @param next the location which you want to query
     * @return true if the location can put the Jumper
     */
    public boolean canPutOntoLocation(Location next) {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        if (!gr.isValid(next)) {
            return false;
        }
        Actor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Flower);
    }
}

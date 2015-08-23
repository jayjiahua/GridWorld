/*
 * @author Wu Jiahua
 */

import info.gridworld.actor.Bug;

/**
 * A <code>ZBug</code> traces out a "Z" of a given size. <br />
 */
public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    
    // record the bug's running status.
    private enum StatusEnum {FIRST_PATH, SECOND_PATH, THIRD_PATH, STOP};
    
    private StatusEnum currentStatus;
    
    /**
     * Constructs a z bug that traces a "Z" of a given side length
     * @param length the side length
     */
    public ZBug(int length)
    {
        steps = 0;
        sideLength = length;
        currentStatus = StatusEnum.FIRST_PATH;
        
        turn();
        turn(); // turn to east
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
    	if (currentStatus == StatusEnum.STOP) {
    		return;
    	}
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        } 
        else if (canMove())
        {
        	if (currentStatus == StatusEnum.FIRST_PATH) {
        		// turn 135 degrees
        		for (int i = 0 ; i < 3 ; i++) {
        			turn();
        		}
        		currentStatus = StatusEnum.SECOND_PATH;
        	} else if (currentStatus == StatusEnum.SECOND_PATH) {
        		// turn 225 degrees
        		for (int i = 0 ; i < 5 ; i++) {
        			turn();
        		}
        		currentStatus = StatusEnum.THIRD_PATH;
        	} else if (currentStatus == StatusEnum.THIRD_PATH) {
        		// finish, stop it
        		currentStatus = StatusEnum.STOP;
        	}
            steps = 0;
        }
    	
    }
}
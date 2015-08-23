/*
 * @author Wu Jiahua
 */

import info.gridworld.actor.Bug;

/**
 * A <code>DancingBug</code> traces out a dance for a given array. <br />
 */
public class DancingBug extends Bug
{
    private int steps;
    private int sideLength;
    
    private int[] danceDegree;
    
    // which entry in danceDegree array is using.
    private int danceOffset;			
    
    /**
     * @param length the side length
     * 
     * @param degree represent how many times the bug turns before it moves
     *        For example, an array entry of 5 represents a turn of 225 degrees.
     */
    public DancingBug(int length, int[] degree)
    {
        steps = 0;
        danceOffset = 0;
        sideLength = length;
        if (degree == null) {
        	danceDegree = new int[0];
        } else {
        	danceDegree = degree.clone();
        }
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
        	for (int i = 0 ; i < danceDegree[danceOffset] ; i++) {
        		turn();
        	}
        	danceOffset = (danceOffset + 1) % danceDegree.length;
            steps = 0;
        }
    }
}

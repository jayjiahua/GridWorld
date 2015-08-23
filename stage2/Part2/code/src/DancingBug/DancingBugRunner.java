/*
 * @author Jiahua Wu
 */

import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/*
 * for the test of DancingBugRunner
 **/
public class DancingBugRunner
{

	public static void main(String[] args)
    {
	ActorWorld world = new ActorWorld();
		int[] arr = {3, 1, 5, 2, 7, 1, 5, 6, 2, 5};
        //int[] arr = {2, 2, 2, 2, 3, 3, 3, 3};
        DancingBug alice = new DancingBug(5, arr);
        alice.setColor(Color.ORANGE);
        world.add(new Location(5, 2), alice);
        world.show();
    }

}

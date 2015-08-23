/*
 * @author Jiahua Wu
 */

import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/*
 * for the test of ZBugRunner
 **/
public class ZBugRunner
{

	public static void main(String[] args)
    {
	ActorWorld world = new ActorWorld();
        ZBug alice = new ZBug(5);
        world.add(new Location(2, 2), alice);
        world.show();
    }

}

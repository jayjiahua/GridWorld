/*
 * @author Jiahua Wu
 */

import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/*
 * for the test of SpiralBugRunner
 **/
public class SpiralBugRunner
{

	public static void main(String[] args)
    {
		ActorWorld world = new ActorWorld();
        SpiralBug alice = new SpiralBug(3);
        alice.setColor(Color.ORANGE);
        SpiralBug bob = new SpiralBug(3);
        world.add(new Location(1, 1), alice);
        world.add(new Location(8, 8), bob);
        world.show();
    }

}

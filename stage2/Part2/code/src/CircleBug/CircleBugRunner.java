/*
 * @author Jiahua Wu
 */

import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/*
 * for the test of CircleBugRunner
 **/
public class CircleBugRunner
{

	public static void main(String[] args)
    {
		ActorWorld world = new ActorWorld();
        CircleBug alice = new CircleBug(3);
        alice.setColor(Color.ORANGE);
        CircleBug bob = new CircleBug(5);
        world.add(new Location(7, 8), alice);
        world.add(new Location(5, 5), bob);
        world.show();
    }

}

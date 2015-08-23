import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains a Jumper and a rock, added at random
 * locations. Click on empty locations to add additional actors. Click on
 * populated locations to invoke methods on their occupants. <br />
 * To build your own worlds, define your own actors and a runner class. See the
 * BoxJumperRunner (in the boxJumper folder) for an example. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class JumperRunner
{
	public static void main(String[] args)
    {
	ActorWorld world = new ActorWorld();
        world.add(new Rock());
        Jumper alice = new Jumper();
        alice.setColor(Color.ORANGE);
        world.add(new Location(2, 2), alice);
        world.show();
    }


}

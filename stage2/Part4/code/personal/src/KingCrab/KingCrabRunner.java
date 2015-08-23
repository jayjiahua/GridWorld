import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public final class KingCrabRunner {
private KingCrabRunner() {}
	 public static void main(String[] args)
	    {
		        ActorWorld world = new ActorWorld();
		        world.add(new Location(6, 5), new Rock());
		        world.add(new Location(5, 4), new Rock());
		        world.add(new Location(5, 3), new Rock());
		        world.add(new Location(5, 5), new Rock());
		        world.add(new Location(6, 3), new Rock());
		        world.add(new Location(6, 4), new KingCrab());
		        world.show();
		}
}

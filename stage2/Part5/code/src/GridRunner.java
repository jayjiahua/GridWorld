import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
/**
* This class runs a world with additional grid choices.
*/
public class GridRunner
{
  public static void main(String[] args)
  {
    ActorWorld world = new ActorWorld();
    world.addGridClass("LinklistSparseGrid");
    world.addGridClass("HashmapSparseGrid");
    world.addGridClass("UnboundedGrid2");    
    
    world.add(new Location(2, 2), new Critter());
    world.add(new Location(6, 6), new Rock());
    world.show();
  }
}

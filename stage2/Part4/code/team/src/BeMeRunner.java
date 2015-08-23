import info.gridworld.actor.*;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public final class BeMeRunner
{
    private BeMeRunner() {}

    
    public static void main(String[] args) {      
        Grid<Actor> grid = new BoundedGrid<Actor>(20, 20);
        ActorWorld world = new ActorWorld(grid);
        world.add(new Location(9, 9), new BeMeCritter());
        world.add(new Location(2, 5), new BeMeCritter(Color.green));
        world.add(new Critter());
        world.add(new Location(2, 10), new Rock());

        world.add(new Location(10, 10), new BeMeCritter());

        world.add(new Location(10, 9), new Critter());
        
        world.show();
    }
}
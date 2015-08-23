/* 
 * @author GA_237
 */
import info.gridworld.actor.*;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 *  BeMe class, that is to say: who touch you, it will become you.
 */
public class BeMeCritter extends Critter
{
    private static final int BREEDING_CYCLE = 10;
    private int existTime = 0;

    /**
     *  default constructor
     */
    public BeMeCritter() { 
        //默认颜色为gray
        setColor(Color.gray);
    }
    
    public BeMeCritter(Color setColor){ 
        //设置颜色
        setColor(setColor);
    }
    
    /* override process
     * Make Critter becomes BeMeCritter
     * Remove and Make it BeMeCritter 
     */
    @Override
    public void processActors(ArrayList<Actor> actors) { 
        for (Actor actor : actors)                           
        {
            if (actor instanceof Critter && !(actor instanceof BeMeCritter)){
                Location temploc = actor.getLocation();
                actor.removeSelfFromGrid();                 
                if(getGrid() != null) {
                    BeMeCritter newCritter = new BeMeCritter();
                    newCritter.putSelfInGrid(getGrid(), temploc);
                }
            }
                
        }
    }
    
    /**
     * To get the location where can move.      
     * It can only go like a "+".
    */
    @Override
    public ArrayList<Location> getMoveLocations()
    {                                                
        ArrayList<Location> locations = new ArrayList<Location>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_CIRCLE, Location.EAST, Location.LEFT};
        for (Location loc : getLocationsInDirections(dirs)) {
            if (getGrid().get(loc) == null) {
                locations.add(loc);
            }
        }
        
        return locations;
    }

    /*
     * move and create a "son" in the old location within a given time
     */
    @Override
    public void makeMove(Location loc)
    {
        existTime++;
        if (loc == null) {
            removeSelfFromGrid();
        }
        else {
            Location oldLoc = getLocation();
            moveTo(loc);
            if (existTime > BREEDING_CYCLE) {
                BeMeCritter newCritter = new BeMeCritter();
                newCritter.putSelfInGrid(getGrid(), oldLoc);
                existTime = 0;
            }
        }
    }

    /** 
     * Get all the location can go!  
     *
     */
    public ArrayList<Location> getLocationsInDirections(int[] directions)  
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc)) {
                locs.add(neighborLoc);
            }
        }
        return locs;
    }
    

}

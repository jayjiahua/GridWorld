import java.util.ArrayList;

import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * Create a class KingCrab that extends CrabCritter. A KingCrab gets 
 * the actors to be processed in the same way a CrabCritter does. 
 * A KingCrab causes each actor that it processes to move one 
 * location further away from the KingCrab. If the actor cannot move 
 * away, the KingCrab removes it from the grid. When the KingCrab has 
 * completed processing the actors, it moves like a CrabCritter.
 * @author Jiahua Wu
 */

public class KingCrab extends CrabCritter {
	
	/**
	 * override
	 */
	public void processActors(ArrayList<Actor> actors)
    {
		for (Actor a : actors) {
			ArrayList<Location> locs = getActorCanMoveLocationList(a);
			if (locs.size() == 0) {
				a.removeSelfFromGrid();
			} else {
		        int n = locs.size();
		        int r = (int) (Math.random() * n);
		        a.removeSelfFromGrid();
		        a.putSelfInGrid(getGrid(), locs.get(r));
			}
		}
    }
	
	/**
	 * get the location list which is further than original location from Kingcrab
	 * @param actor the actor you want to get
	 * @return the list of the locations which the actor can move and 
	 * further from Kingcrab than before
	 */
	private ArrayList<Location> getActorCanMoveLocationList(Actor actor) {
		Grid<Actor> gr = getGrid();
		ArrayList<Location> resultLocationList = new ArrayList<Location>();
		ArrayList<Location> locs = gr.getEmptyAdjacentLocations(actor.getLocation());
		for (Location loc : locs) {
			if (isFurther(loc, actor.getLocation())) {
				resultLocationList.add(loc);
			}
		}
		return resultLocationList;
	}
	
	/**
	 * judge if the new location is further than old location
	 * @param newLocation
	 * @param oldLocation
	 * @return true if further
	 */
	private boolean isFurther(Location newLocation, Location oldLocation) {
		Location crabLoc = getLocation();
		
		int newLocationDistance = 
				(crabLoc.getCol() - newLocation.getCol()) * 
				(crabLoc.getCol() - newLocation.getCol()) +
				(crabLoc.getRow() - newLocation.getRow()) *
				(crabLoc.getRow() - newLocation.getRow());
		int oldLocationDistance = 
				(crabLoc.getCol() - oldLocation.getCol()) * 
				(crabLoc.getCol() - oldLocation.getCol()) +
				(crabLoc.getRow() - oldLocation.getRow()) *
				(crabLoc.getRow() - oldLocation.getRow());
		return (newLocationDistance > oldLocationDistance);
	}
}

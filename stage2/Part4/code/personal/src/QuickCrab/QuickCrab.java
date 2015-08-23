import java.util.ArrayList;

import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class QuickCrab extends CrabCritter {
	
	/**
	 * A QuickCrab moves to one of the two locations, randomly selected, 
	 * that are two spaces to its right or left, if that location and 
	 * the intervening location are both empty. Otherwise, a QuickCrab 
	 * moves like a CrabCritter.
	 */
    public ArrayList<Location> getMoveLocations()
    {
    	Grid<Actor> gr = getGrid();
    	ArrayList<Location> locs = new ArrayList<Location>();

        Location leftOneLocation = getLocation().getAdjacentLocation(getDirection() + Location.LEFT);
        if (gr.isValid(leftOneLocation) && gr.get(leftOneLocation) == null) {
        	Location leftTwoLocation = leftOneLocation.getAdjacentLocation(getDirection() + Location.LEFT);
        	if (gr.isValid(leftTwoLocation) && gr.get(leftTwoLocation) == null) {
        		locs.add(leftTwoLocation);
        	}
        }
        
        Location rightOneLocation = getLocation().getAdjacentLocation(getDirection() + Location.RIGHT);
        if (gr.isValid(rightOneLocation) && gr.get(rightOneLocation) == null) {
        	Location rightTwoLocation = rightOneLocation.getAdjacentLocation(getDirection() + Location.RIGHT);
        	if (gr.isValid(rightTwoLocation) && gr.get(rightTwoLocation) == null) {
        		locs.add(rightTwoLocation);
        	}
        }

        if (locs.size() == 0) {
        	locs = super.getMoveLocations();
        }
        
        return locs;
    }
}

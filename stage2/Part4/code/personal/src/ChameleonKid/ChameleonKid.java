import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class ChameleonKid extends ChameleonCritter {
	
	/**
	 * Override this method, get the actors immediately in front or behind
	 */
	public ArrayList<Actor> getActors()
    { 
		Location locFront = getLocation().getAdjacentLocation(getDirection());
		Location locBehind = getLocation().getAdjacentLocation(getDirection() + Location.HALF_CIRCLE);
		ArrayList<Actor> actors = getGrid().getNeighbors(getLocation());
		ArrayList<Actor> frontAndBehindActor = new ArrayList<Actor>();
		for (Actor a : actors) {
        	Location loc = a.getLocation();
        	if (loc.equals(locFront) || loc.equals(locBehind)) {
        		frontAndBehindActor.add(a);
        	}
        }
		return frontAndBehindActor;
    }
}

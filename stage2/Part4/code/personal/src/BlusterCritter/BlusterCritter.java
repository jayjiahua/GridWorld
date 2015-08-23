import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * @author Jiahua Wu
 *   A BlusterCritter looks at all of the neighbors within two steps 
 * of its current location. (For a BlusterCritter not near an edge, 
 * this includes 24 locations). It counts the number of critters in 
 * those locations. If there are fewer than c critters, the 
 * BlusterCritter's color gets brighter (color values increase). 
 * If there are c or more critters, the BlusterCritter's color 
 * darkens (color values decrease). Here, c is a value that indicates 
 * the courage of the critter. It should be set in the constructor.
 */

public class BlusterCritter extends Critter 
{
	private int courage;
	private static final int DARKENING_FACTOR = 20;
	private static final int BRIGHTING_FACTOR = 20;
	
	// constructor
	public BlusterCritter(int c) {
		courage = c;
	}
	
	/**
	 * A BlusterCritter looks at all of the neighbors within two steps of 
	 * its current location. 
	 * (For a BlusterCritter not near an edge, this includes 24 locations).
	 */
    public ArrayList<Actor> getActors()
    {
    	Grid<Actor> gr = getGrid();
    	ArrayList<Actor> actorList = new ArrayList<Actor>();
    	Location loc = getLocation();
    	for (int i = loc.getRow() -2 ; i <= loc.getRow() + 2 ; i++) {
    		for (int j = loc.getCol() - 2 ; j <= loc.getCol() + 2 ; j++) {
    			Location tempLoc = new Location(i, j);
    			if (i != loc.getRow() && j != loc.getCol() && gr.isValid(tempLoc)) {
	    			Actor a = gr.get(tempLoc);
	    			if (a instanceof Critter) {
	    				actorList.add(a);
	    			}
    			}
    		}
    	}
        return actorList;
    }

    /**
     *  It counts the number of critters in those locations. If there 
     *  are fewer than c critters, the BlusterCritter's color gets 
     *  brighter (color values increase). If there are c or more 
     *  critters, the BlusterCritter's color darkens (color values decrease). 
     *  Here, c is a value that indicates the courage of the critter.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        if (actors.size() >= courage) {
            darken();
        } else {
        	brighten();
        }
    }
    
    /**
     * brighten the critter
     */
    private void brighten() {
	int colorMax = 255;
    	Color c = getColor();
    	int red = c.getRed() + BRIGHTING_FACTOR;
    	if (red > colorMax) {
    		red = colorMax;
    	}
        int green = c.getGreen() + BRIGHTING_FACTOR;
        if (green > colorMax) {
    		green = colorMax;
    	}
        int blue = c.getBlue() + BRIGHTING_FACTOR;
        if (blue > colorMax) {
    		blue = colorMax;
    	}
        setColor(new Color(red, green, blue));
    }
    
    /**
     * darken the critter
     */
    private void darken() {
	int colorMin = 0;
    	Color c = getColor();
    	int red = c.getRed() - DARKENING_FACTOR;
    	if (red < colorMin) {
    		red = colorMin;
    	}
        int green = c.getGreen() - DARKENING_FACTOR;
        if (green < colorMin) {
    		green = colorMin;
    	}
        int blue = c.getBlue() - DARKENING_FACTOR;
        if (blue < colorMin) {
    		blue = colorMin;
    	}
        setColor(new Color(red, green, blue));
    }
}

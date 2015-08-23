/* 
 * @author Jiahua Wu
 */

import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;


/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 * The constructor allocates a 16 x 16 array. When a call is made to the put method 
 * with a row or column index that is outside the current array bounds, double both 
 * array bounds until they are large enough, construct a new square array with 
 * those bounds, and place the existing occupants into the new array.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
	private Object[][] occupantArray; // the array storing the grid elements
    private int sideLength;
    
    /**
     * Constructs an hashmap unbounded grid.
     */
    public UnboundedGrid2()
    {
    	sideLength = 16;
    	occupantArray = new Object[sideLength][sideLength];
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && 0 <= loc.getCol() 
            && loc.getRow() <= sideLength && loc.getCol() <= sideLength;
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < sideLength; r++)
        {
            for (int c = 0; c < sideLength; c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    theLocations.add(loc);
                }
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {

    	if (loc == null) {
            throw new IllegalArgumentException("loc == null");
    	}
        if (isValid(loc) && loc.getRow() < sideLength && loc.getCol() < sideLength) {
        	return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
        }
        else {
        	return null;
        }
    }

    public E put(Location loc, E obj)
    {
    	if (loc == null) {
            throw new IllegalArgumentException("loc == null");
    	}
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }
        // Add the object to the grid.
        E oldOccupant = get(loc);
        
        while (sideLength <= loc.getCol() || sideLength <= loc.getRow()) {
        	doubleSize();
        }
        
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
    
    /**
     * double the map size
     */
    private void doubleSize() {
        Object[][] newOccupantArray = new Object[sideLength * 2][sideLength * 2];
        for (int i = 0 ; i < sideLength ; i++) {
        	for (int j = 0 ; j < sideLength ; j++) {
        		newOccupantArray[i][j] = occupantArray[i][j];
        	}
        }
        sideLength *= 2;
        occupantArray = newOccupantArray;
    }
}
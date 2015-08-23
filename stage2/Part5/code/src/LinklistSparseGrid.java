/**
 * @author Jiahua Wu
 */

import java.util.ArrayList;
import java.util.LinkedList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;


/**
 * SparseBoundedGrid, using an array list of linked lists to save objects.
 */

public class LinklistSparseGrid<E> extends AbstractGrid<E> {


	private LinkedList[] occupantArray;
	private int numRow;
	private int numCol;
	
	/**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public LinklistSparseGrid(int rows, int cols)
    {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        occupantArray = new LinkedList[rows];
        for (int i = 0 ; i < rows ; i++) {
        	occupantArray[i] = new LinkedList<OccupantInCol>();
        }
        
        numRow = rows;
        numCol = cols;
        
    }

    public int getNumRows()
    {
        return numRow;
    }

    public int getNumCols()
    {
        return numCol;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
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
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        LinkedList<OccupantInCol> nRowOccupants = occupantArray[loc.getRow()]; 
        for (OccupantInCol occ : nRowOccupants) {
        	if (occ.getCol() == loc.getCol()) {
        		return (E) occ.getOccupant();   // unavoidable warning
        	}
        }
        return null;
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }
        // Add the object to the grid.
        E oldOccupant = get(loc);
        
        // record whether the location in the Linklist has existed.
        boolean exist = false;    
        
        LinkedList<OccupantInCol> nRowOccupants = occupantArray[loc.getRow()];
        for (OccupantInCol occ : nRowOccupants) {
        	if (occ.getCol() == loc.getCol()) {
        		occ.setOccupant(obj);   // replace it
        		exist = true;
        	}
        }
        
        // add it
        if (!exist) {
        	nRowOccupants.add(new OccupantInCol(loc.getCol(), obj));
        }
        
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
        LinkedList<OccupantInCol> nRowOccupants = occupantArray[loc.getRow()];
        for (int i = 0 ; i < nRowOccupants.size() ; i++) {
        	if (nRowOccupants.get(i).getCol() == loc.getCol()) {
        		nRowOccupants.remove(i);
        	}
        }
        return r;
    }
	
}

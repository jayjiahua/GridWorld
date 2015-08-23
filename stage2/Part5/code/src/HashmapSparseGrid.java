/* 
 * @author Jiahua Wu
 */

import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.*;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class HashmapSparseGrid<E> extends AbstractGrid<E>
{
    private Map<Location, E> occupantMap;
    private int numCol;
    private int numRow;
    
    /**
     * Constructs an hashmap bounded grid.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public HashmapSparseGrid(int rows, int cols)
    {
        occupantMap = new HashMap<Location, E>();
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
        ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet()) {
            a.add(loc);
        }
        return a;
    }

    public E get(Location loc)
    {
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        return occupantMap.get(loc);
    }

    public E put(Location loc, E obj)
    {
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }
        return occupantMap.put(loc, obj);
    }

    public E remove(Location loc)
    {
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        return occupantMap.remove(loc);
    }
}

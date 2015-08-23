/**
 * This class is for linklist elements
 * 
 * @author Jiahua Wu
 *
 */
public class OccupantInCol
{
	private Object occupant;
	private int col;
	
	public OccupantInCol(int newCol, Object newOccupant) {
		col = newCol;
		occupant = newOccupant;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setCol(int newCol) {
		col = newCol;
	}
	
	public Object getOccupant() {
		return occupant;
	}
	
	public void setOccupant(Object newOccupant) {
		occupant = newOccupant;
	}
}
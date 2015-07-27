
package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public class Cube extends Piece {
	
	private final int type = 1;

	public Cube() {
		
	}
	
	public final int getType() {
		return type;
	}

	
	public final ArrayList<Location> possibleMoves() {
		ArrayList<Location> pMoves = new ArrayList<Location>();
		ArrayList<Location> temp = new ArrayList<Location>();
		temp.add(new Location(this.getPieceLocation().getY(), 
				this.getPieceLocation().getX() + 1)); // left
		temp.add(new Location(this.getPieceLocation().getY(), 
				this.getPieceLocation().getX() - 1)); // right
		temp.add(new Location(this.getPieceLocation().getY() - 1, 
				this.getPieceLocation().getX())); // down
		temp.add(new Location(this.getPieceLocation().getY() + 1, 
				this.getPieceLocation().getX())); // up
		
		for (int i = 0; i < temp.size(); i++) {
			if (isAvailable(temp.get(i))) {
				pMoves.add(temp.get(i));
			}
		}	
		return pMoves;
	}
	
}

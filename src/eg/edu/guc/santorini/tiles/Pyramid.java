package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public class Pyramid extends Piece {

	private final int type = 2;

	public Pyramid() {
	}

	public final int getType() {
		return type;
	}

	public final ArrayList<Location> possibleMoves() {
		ArrayList<Location> pMoves = new ArrayList<Location>();
		ArrayList<Location> temp = new ArrayList<Location>();

		temp.add(new Location(this.getPieceLocation().getY() + 1, 
				this.getPieceLocation().getX() + 1));
		temp.add(new Location(this.getPieceLocation().getY() + 1, 
				this.getPieceLocation().getX() - 1));
		temp.add(new Location(this.getPieceLocation().getY() - 1, 
				this.getPieceLocation().getX() + 1));
		temp.add(new Location(this.getPieceLocation().getY() - 1, 
				this.getPieceLocation().getX() - 1));

		for (int i = 0; i < temp.size(); i++) {
			if (isAvailable(temp.get(i))) {
				pMoves.add(temp.get(i));
			}
		}
		return pMoves;
	}
}



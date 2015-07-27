package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;



import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.utilities.Location;

public abstract class Piece {

	private Location pieceLocation;

	private int playerN;

	public Piece() {

	}	

	public final Location getPieceLocation() {
		return pieceLocation;
	}

	public final void setPieceLocation(final Location l) {
		this.pieceLocation = l;
	}

	public abstract int getType();


	public final int getPlayerN() {
		return playerN;
	}

	public final void setPlayerN(final int player) {
		this.playerN = player;
	}

	public final boolean isAvailable(final Location l) {
		int x = l.getX();
		int y = l.getY();
		if (x < 0 || x > Board.getSide() - 1 
				|| y < 0 || y > Board.getSide() - 1) {
			return false;
		}
		return true;
	}

	public abstract ArrayList<Location> possibleMoves();

	public final ArrayList<Location> possiblePlacements() {
		ArrayList<Location>  temp = new ArrayList<Location>();
		ArrayList<Location> pPlacements = new ArrayList<Location>();
		temp.add(new Location(this.pieceLocation.getY() - 1, 
				this.pieceLocation.getX() - 1));
		temp.add(new Location(this.pieceLocation.getY() - 1, 
				this.pieceLocation.getX()));
		temp.add(new Location(this.pieceLocation.getY() - 1, 
				this.pieceLocation.getX() + 1));
		temp.add(new Location(this.pieceLocation.getY(), 
				this.pieceLocation.getX() - 1));
		temp.add(new Location(this.pieceLocation.getY(), 
				this.pieceLocation.getX() + 1));
		temp.add(new Location(this.pieceLocation.getY() + 1, 
				this.pieceLocation.getX() - 1));
		temp.add(new Location(this.pieceLocation.getY() + 1, 
				this.pieceLocation.getX()));
		temp.add(new Location(this.pieceLocation.getY() + 1, 
				this.pieceLocation.getX() + 1));

		for (int i = 0; i < temp.size(); i++) {
			if (isAvailable(temp.get(i))) {
				pPlacements.add(temp.get(i));
			}
		}
		return pPlacements;
	}
}

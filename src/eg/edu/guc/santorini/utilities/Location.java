package eg.edu.guc.santorini.utilities;

import eg.edu.guc.santorini.tiles.Piece;

public class Location {
	private int x;
	private int y;
	private int level;
	private boolean hasTiles;
	private boolean dead;
	private boolean containsPiece;
	private Piece p;
	
	public Location() {

	}
	
	
	public Location(final int y, final int x) {
		this.x = x;
		this.y = y;
		level = 0;
	}
	
	public final String toString() {
		return y + " " + x;
	}
	
	public final boolean equals(final Object l) {
		if (l instanceof Location) {
		return ((Location) l).getX() == this.getX() 
		&& ((Location) l).getY() == this.getY();
		}
		return false;
	}
	
	public final void addPiece(final Piece p) {
		this.p = p;
		containsPiece = true;
	}
	
	
	public final void setContainsPiece(final Boolean f) {
		containsPiece = f;
	}
	
	public final boolean containsPiece() {
		return containsPiece;
	}
	
	public final boolean hasTiles() {
		return hasTiles;
	}
	
	public final void setHasTiles(final boolean f) {
		this.hasTiles = f;
	}
	
	public final void setX(final int x) {
		this.x = x;
	}
	
	public final int getX() {
		return x;
	}
	
	public final int getY() {
		return y;
	}
	
	public final void setY(final int y) {
		this.y = y;
	}
	
	public final boolean getDead() {
		return dead;
	}
	
	public final void setDead(final boolean dead) {
		this.dead = dead;
	}

	public final int getLevel() {
		return level;
	}

	public final void setLevel(final int level) {
		this.level = level;
	}


	public final Piece getP() {
		return p;
	}


	public final void setP(final Piece p) {
		this.p = p;
	}
	

}

package eg.edu.guc.santorini.players;

import eg.edu.guc.santorini.tiles.Cube;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.tiles.Pyramid;

public class Player {
	private String name;
	private Piece t1; //player's first piece
	private Piece t2; //player's second piece
	private boolean turn;
	private boolean moved;
	
	public Player(final String name, final int p) {
		this.name = name;
		switch(p) {
		case 1:
			t1 = new Cube();
			t2 = new Cube(); break;
		case 2:
			t1 = new Pyramid();
			t2 = new Pyramid(); break;
		default:
		}
	}

	public final boolean hasMoved() {
		return moved;
	}
	
	public final void setMoved(final boolean moved) {
		this.moved = moved;
	}

	public final String getName() {
		return name;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	public final Piece getT1() {
		return t1;
	}

	public final void setT1(final Piece t1) {
		this.t1 = t1;
	}

	public final Piece getT2() {
		return t2;
	}

	public final void setT2(final Piece t2) {
		this.t2 = t2;
	}

	public final boolean isTurn() {
		return turn;
	}

	public final void setTurn(final boolean turn) {
		this.turn = turn;
	}
	
}

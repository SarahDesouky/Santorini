package eg.edu.guc.santorini;

import java.util.ArrayList;

import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.utilities.Location;

public class Board implements BoardInterface {

	private Player one;
	private Player two;
	private Location [][] b = new Location[SIDE][SIDE];

	public Board() {

	}

	public Board(final Player x, final Player y) {
		one = x;
		two = y;
		one.getT1().setPlayerN(1);
		one.getT2().setPlayerN(1);
		two.getT1().setPlayerN(2);
		two.getT2().setPlayerN(2);
		one.setTurn(true);

		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				b[i][j] = new Location(i, j);
			}
		}
		b[0][0].addPiece(one.getT1());
		b[4][1].addPiece(one.getT2());
		b[0][3].addPiece(two.getT1());
		b[4][4].addPiece(two.getT2());
		one.getT1().setPieceLocation(b[0][0]);
		one.getT2().setPieceLocation(b[4][1]);
		two.getT1().setPieceLocation(b[0][3]);
		two.getT2().setPieceLocation(b[4][4]);
	}



	public final Player getOne() {
		return one;
	}

	public final void setOne(final Player one) {
		this.one = one;
	}

	public final Player getTwo() {
		return two;
	}

	public final void setTwo(final Player two) {
		this.two = two;
	}

	public final Location[][] getBoard() {
		return b;
	}

	public final void setBoard(final Location[][] b) {
		this.b = b;
	}


	public final void move(final Piece piece, final Location newLocation)
			throws InvalidMoveException {
		int n = piece.getPlayerN();
		Player player;
		if (n == 1) {
			player = one;
		} else {
			player = two;
		}

		if (isWinner(one) || isWinner(two)) {
			throw new InvalidMoveException();
		}
		if (!canMove(piece, newLocation) || !(player.isTurn()) 
				|| player.hasMoved()) {
			throw new InvalidMoveException();
		} else {
			this.removePieceFromLocation(piece.getPieceLocation(), piece);
			piece.setPieceLocation(b[newLocation.getY()][newLocation.getX()]);
			this.addPieceToLocation(b[newLocation.getY()]
					[newLocation.getX()], piece);
			player.setMoved(true);
		}
	}



	public static int getSide() {
		return SIDE;
	}

	public final void addPieceToLocation(final Location l, final Piece p) {
		int x = l.getX();
		int y = l.getY();
		b[y][x].addPiece(p);
	}

	public final void removePieceFromLocation(final Location l, final Piece p) {
		int x = l.getX();
		int y = l.getY();
		b[y][x].setP(null);
		b[y][x].setContainsPiece(false);
	}


	public final void place(final Piece piece, final Location newLocation)
			throws InvalidPlacementException {
		int n = piece.getPlayerN();
		Player player;
		Player other;
		if (n == 1) {
			player = one;
			other = two;
		} else {
			player = two;
			other = one;
		}
		if (isWinner(one) || isWinner(two)) {
			throw new InvalidPlacementException();
		}
		if (!(player.hasMoved()) || !(canPlace(piece, newLocation) 
				|| !(player.isTurn()))) {
			throw new InvalidPlacementException();
		} else {
			this.addTilesToLocation(newLocation);
			player.setTurn(false);
			other.setTurn(true);
			player.setMoved(false);
		}
	}

	public final void addTilesToLocation(final Location l) {

		int x = l.getX();
		int y = l.getY();

		b[y][x].setHasTiles(true);
		b[y][x].setLevel(b[y][x].getLevel() + 1);
		if (b[y][x].getLevel() > 3) {
			b[y][x].setDead(true);
		}

	}


	public final boolean isGameOver() {
		if ((isWinner(one) && one.isTurn()) || (isWinner(two) && two.isTurn()) 
				|| (hasNoMoves(one) && one.isTurn()) || (
						hasNoMoves(two) && two.isTurn())) {
			return true;
		}
		return false;
	}


	public final boolean isWinner(final Player player) {
		if ((player == one 
				&& ((getLocationLevel(one.getT1().getPieceLocation()) 
						== 3 
						|| getLocationLevel(one.getT2()
								.getPieceLocation()) == 3) 
								|| (hasNoMoves(two) && two.isTurn())) 
								|| (player == two 
								&& ((getLocationLevel(two.getT1()
										.getPieceLocation()) == 3 
										|| getLocationLevel(two.getT2()
												.getPieceLocation()) == 3) 
												|| (one.isTurn() 
														&& hasNoMoves(
																one)))))) {
			return true;
		}

		return false;

	}


	public final boolean hasNoMoves(final Player player) {
		ArrayList<Location> temp1 = player.getT1().possibleMoves();
		ArrayList<Location> temp2 = player.getT2().possibleMoves();

		for (int i = 0; i < temp1.size(); i++) { 
			if (canMove(player.getT1(), temp1.get(i))) {
				return false;
			}
		}
		for (int i = 0; i < temp2.size(); i++) {
			if (canMove(player.getT2(), temp2.get(i))) {
				return false;
			}
		}
		return true;
	}


	public final Player getWinner() {

		if (isWinner(one)) {
			return one;
		}
		return two;
	}

	public final int getLocationLevel(final Location l) {
		return b[l.getY()][l.getX()].getLevel();
	}

	public final boolean possibleMovesHasLocation(final Piece p, 
			final Location l) {
		int x = l.getX();
		int y = l.getY();
		ArrayList<Location> pMoves = p.possibleMoves();
		for (int i = 0; i < pMoves.size(); i++) {
			if (pMoves.get(i).getX() == x && pMoves.get(i).getY() == y) {
				return true;
			}
		}
		return false;
	}

	public final boolean getLocationDead(final Location l) {
		return b[l.getY()][l.getX()].getDead();
	}

	public final boolean canMove(final Piece piece, final Location location) {
		if (possibleMovesHasLocation(piece, location) 
				&& (piece.getPieceLocation().getLevel() 
						== getLocationLevel(location) 
						|| piece.getPieceLocation().getLevel() 
						== getLocationLevel(location) - 1 
						|| piece.getPieceLocation().getLevel() - 1 
						== getLocationLevel(location) 
						|| piece.getPieceLocation().getLevel() - 2 
						== getLocationLevel(location)) 
						&& !(getLocationDead(location)) 
						&& !(locationHasPiece(location))) {
			return true;
		}
		return false;
	}

	public final boolean locationHasPiece(final Location l) {
		return b[l.getY()][l.getX()].containsPiece();
	}

	public final boolean possiblePlacementsHasLocation(final Piece p, 
			final Location l) {
		int x = l.getX();
		int y = l.getY();
		ArrayList<Location> pPlacements = p.possiblePlacements();
		for (int i = 0; i < pPlacements.size(); i++) {
			if (pPlacements.get(i).getX() == x 
					&& pPlacements.get(i).getY() == y) {
				return true;
			}
		}
		return false;
	}

	public final boolean canPlace(final Piece piece, final Location location) {
		if (possiblePlacementsHasLocation(piece, location) 
				&& !(getLocationDead(location)) 
				&& !(locationHasPiece(location))) {
			return true;
		}
		return false;
	}

	public final Player getTurn() {
		if (one.isTurn()) {
			return one;
		}
		return two;
	}

	public final String[][] display() {
		String [] [] res = new String[SIDE][SIDE];
		int x = -1;
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				res[i][j] = "";
				res[i][j] = res[i][j] + b[i][j].getLevel();
				if (b[i][j].containsPiece()) {
					x = b[i][j].getP().getType();
					if (x == 1) {
						res[i][j] = res[i][j] + "C"; 
					} else {
						res[i][j] = res[i][j] + "P";
					}
					res[i][j] = res[i][j] + b[i][j].getP().getPlayerN();
				}
			}
		}
		return res;
	}




}

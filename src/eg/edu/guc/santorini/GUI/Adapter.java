package eg.edu.guc.santorini.GUI;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.utilities.Location;
import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Piece;


final class Adapter {

	private Adapter() {

	}


	public static int [] pMoves(final Cell c, final Board board) {

		//new PieceGUI(c.getIcon(), c.getCellLocation());
		Piece p = getPiece(c.getCellLocation(), board); 
		ArrayList<Location> pMoves = p.possibleMoves();
		int [] res = new int [pMoves.size()];
		for (int i = 0; i < pMoves.size(); i++) {
			//if(board.canMove(p, pMoves.get(i)))
			//{
			res[i] = boardToGUILocation(pMoves.get(i));
			//}

		}
		return res;

	}

	public static int [] pPlacements(final Cell c, final Board board) {

		//new PieceGUI(c.getIcon(), c.getCellLocation());
		Piece p = getPiece(c.getCellLocation(), board);
		ArrayList<Location> pPlacements = p.possiblePlacements();
		int [] res = new int [pPlacements.size()];
		for (int i = 0; i < pPlacements.size(); i++) {
			//if(board.canPlace(p, pPlacements.get(i))) {
			res[i] = boardToGUILocation(pPlacements.get(i));	
			//}
		}
		return res;

	}

	public static Location getLocation(final int loc, final Board b) {
		int y = loc / Board.getSide();
		int x = loc % Board.getSide();
		return b.getBoard()[y][x];
	}
	public static boolean checkPiece(final int location, final Board board) {
		Location temp = getLocation(location, board);
		return board.getBoard()[temp.getY()][temp.getX()].containsPiece();
	}


	public static Piece getPiece(final int location, final Board board) {
		Location temp = getLocation(location, board);
		return board.getBoard()[temp.getY()][temp.getX()].getP();
	}


	public static boolean addTile(final int pieceLocation, 
			final int location, final Board board) {
		Piece p = getPiece(pieceLocation, board);
		Location l = getLocation(location, board);
		try {
			board.place(p, l);
			return true;
		} catch (InvalidPlacementException e) {
			JOptionPane.showMessageDialog(null, "You cannot place a tile here");
		}
		return false;
	}

	public static boolean isGUITurn(final Cell c, final Board board) {

		if (!checkPiece(c.getCellLocation(), board)) {
			JOptionPane.showMessageDialog(null, "This is not a piece");
		} else {
			Piece p = getPiece(c.getCellLocation(), board);
			Player player = whichPlayer(p, board);
			return player.isTurn();
		}

		return false;
	}
	//	
	//	public static void setGUITurn(Cell c, Board board, boolean turn) {
	//		Piece p = getPiece(c.getCellLocation(), board);
	//		Player player = whichPlayer(p, board);
	//		player.setTurn(turn);
	//	}


	public static Player whichPlayer(final Piece piece, final Board board) {
		int temp = piece.getPlayerN();
		if (temp == 1) {
			return board.getOne();
		} else {
			return board.getTwo();
		}
	}


	public static int boardToGUILocation(final Location l) {
		return (l.getY() * Board.getSide()) + l.getX();
	}

	public static boolean moveAd(final int oldLocation, 
			final int newLocation, final Board board) {
		Piece p = getPiece(oldLocation, board);
		Location l = getLocation(newLocation, board);
		try {
			board.move(p, l);
			return true;
		} catch (InvalidMoveException e) {
			JOptionPane.showMessageDialog(null, "You cannot move here");
		}
		return false;

	}

	public static String getWinnerAd(final Board b) {
		String res = "Winner is player: ";
		Player player = b.getWinner();
		//System.out.println(player.getName());
		res += player.getName();
		return res;
	}
}

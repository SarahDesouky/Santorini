package eg.edu.guc.santorini.GUI;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import eg.edu.guc.santorini.Board;


@SuppressWarnings("serial")
public class Cell extends JLabel implements MouseListener {
	private int level;
	private Icon icon;
	private Board board;
	private int location;
	private static int counter = 0;
	private static int previousLocation = 0;
	private static int newLocation = 0;
	private Cell [] temp = GameWindow.getCells();

	public Cell(final Icon icon, final Board b, final int location) {

		super(icon);
		level = 0;
		board = b;
		counter = 0;
		previousLocation = 0;
		newLocation = 0;
		this.location = location;
		addMouseListener(this);

	}
	public Cell(final Board board, final int location) {
		level = 0;
		setBorder(BorderFactory.createEtchedBorder());
		this.location = location;
		counter = 0;
		previousLocation = 0;
		newLocation = 0;
		addMouseListener(this);
		this.board = board;

	}

	public final int getCellLocation() {
		return location;
	}

	public final void setCellLocation(final int location) {
		this.location = location;
	}
	public final void setLevel(final int level) {
		this.level = level;
	}

	public final void setIcon(final Icon icon) {
		this.icon = icon;
	}

	public final Icon getIcon() {
		return icon; 
	}

	public final int getLevel() {
		return level;

	}

	public final void mouseClicked(final MouseEvent e) {

		Cell c = (Cell) e.getComponent();

		switch(counter) {
		case 0: 
			if (Adapter.checkPiece(c.getCellLocation(), board)) {
				if (Adapter.isGUITurn(c, board)) {
					counter++;
					//System.out.println(counter);
					previousLocation = c.getCellLocation();
				}

			} 
			break;

		case 1: 
			int x = Adapter.getPiece(previousLocation, board).getType();
			int player = Adapter.getPiece(previousLocation, board).getPlayerN();

			if (Adapter.moveAd(previousLocation, c.getCellLocation(), board)) {
				counter++;
				//System.out.println("working");
				//Cell [] temp = GameWindow.getCells();
				//Icon image = temp[previousLocation].getIcon();
				int level = temp[previousLocation].getLevel();
				if (level == 0) {
					temp[previousLocation].setIcon(null);	
				} else {
					temp[previousLocation].setIcon(new ImageIcon(getClass()
							.getResource("Level" 
					+ level + ".jpg")));
				}
				temp[previousLocation].
				setBorder(BorderFactory.createEtchedBorder());
				temp[previousLocation].validate();
				temp[previousLocation].repaint();
				int level1 = c.getLevel();
				String type = "";
				String pType = "";
				if (x == 1) {
					type = "Cube";
				} else {
					type = "Pyramid";
				}
				if (player == 2) {
					pType = "Green";
				}
				if (level1 == 0) {
					temp[c.getCellLocation()].setIcon(new ImageIcon(getClass()
							.getResource(type 
									+ pType + ".jpg")));	
				} else {
					temp[c.getCellLocation()].setIcon(new ImageIcon(getClass()
							.getResource("Level" 
					+ level1 + type + pType + ".jpg")));
				}
				temp[c.getCellLocation()].setBorder(
						BorderFactory.createEtchedBorder());
				this.validate();
				this.repaint();
				newLocation = c.getCellLocation();

			} else {
				counter--;
			}
			if (board.isGameOver()) {
				String s = Adapter.getWinnerAd(board);	
				//System.out.println(s);
				JOptionPane.showMessageDialog(null, s, "Congratulations", 1);
				System.exit(0);
			}
			break;

		case 2: 
			if (Adapter.addTile(newLocation, c.getCellLocation(), board)) {

				c.setLevel(c.getLevel() + 1);
				if (c.getLevel() > 3) {
					c.setIcon(new ImageIcon(getClass().
							getResource("dead.jpg")));
					c.setBorder(BorderFactory.createEtchedBorder());
					temp[c.getCellLocation()].validate();
					temp[c.getCellLocation()].repaint();
				} else {
					c.setIcon(new ImageIcon(
							getClass().getResource(
									"Level" + c.getLevel() + ".jpg")));
					c.setBorder(BorderFactory.createEtchedBorder());
					temp[c.getCellLocation()].validate();
					temp[c.getCellLocation()].repaint();
				}
			} else {
				return;
			}
			counter = 0;
			break;
		default:
			break;
		}
	}

	public final void mouseEntered(final MouseEvent e) {

		Cell c = (Cell) e.getComponent();
		int location = c.getCellLocation();
		if (counter == 0 || counter == 1) {


			if (Adapter.checkPiece(location, board)) {
				int [] pMoves = Adapter.pMoves(c, board);
				for (int i = 0; i < pMoves.length; i++) {
					GameWindow.getCells()[pMoves[i]].setBorder(
							BorderFactory.createLineBorder(Color.RED));
					GameWindow.getCells()[pMoves[i]].validate();
					GameWindow.getCells()[pMoves[i]].repaint();


				}
			}
		} else {
			if (counter == 2) {
				if (Adapter.checkPiece(location, board)) {
					int [] pPlacements = Adapter.pPlacements(c, board);
					for (int i = 0; i < pPlacements.length; i++) {
						GameWindow.getCells()[pPlacements[i]].setBorder(
								BorderFactory.
								createLineBorder(Color.DARK_GRAY));
						GameWindow.getCells()[pPlacements[i]].validate();
						GameWindow.getCells()[pPlacements[i]].repaint();

					}
				}
			} else {
				return;
			}
		}


	}


	public final void mouseExited(final MouseEvent e) {

		Cell c = (Cell) e.getComponent();
		int location = c.getCellLocation();
		if (counter == 0 || counter == 1) {
			//System.out.println(c.getCellLocation());
			if (Adapter.checkPiece(location, board)) {
				int [] pMoves = Adapter.pMoves(c, board);
				for (int i = 0; i < pMoves.length; i++) {
					GameWindow.getCells()[pMoves[i]].removeAll();
					GameWindow.getCells()[pMoves[i]].setBorder(
							BorderFactory.createEmptyBorder());
					GameWindow.getCells()[pMoves[i]].setBorder(
							BorderFactory.createEtchedBorder());
					GameWindow.getCells()[pMoves[i]].validate();
					GameWindow.getCells()[pMoves[i]].repaint();

				}
			}
		} else {
			if (counter == 2) {
				if (Adapter.checkPiece(location, board)) {
					int [] pPlacements = Adapter.pPlacements(c, board);
					for (int i = 0; i < pPlacements.length; i++) {
						GameWindow.getCells()[pPlacements[i]].removeAll();
						GameWindow.getCells()[pPlacements[i]].setBorder(
								BorderFactory.createEmptyBorder());
						GameWindow.getCells()[pPlacements[i]].setBorder(
								BorderFactory.createEtchedBorder());
						GameWindow.getCells()[pPlacements[i]].validate();
						GameWindow.getCells()[pPlacements[i]].repaint();
					}
				}
			} else {
				return;
			}
		}
	}

	public void mousePressed(final MouseEvent e) {

	}

	public final void mouseReleased(final MouseEvent arg0) {
		setBorder(BorderFactory.createEtchedBorder());

	}





}

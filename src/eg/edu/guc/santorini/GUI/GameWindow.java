package eg.edu.guc.santorini.GUI;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.players.Player;

@SuppressWarnings("serial")
public class GameWindow extends JFrame 
implements ActionListener, MouseListener {

	private static Cell[] cells;
	private JPanel boardPanel;
	private JPanel buttonsPanel;
	private Icon p1, p2;
	private Player one, two;
	private Board board;
	private JFrame game;
	private JButton restart;
	
	public GameWindow(final int rows, final int columns, 
			final Icon p1, final Icon p2, final Player one, final Player two) {
		game = new JFrame();
		Toolkit meh = Toolkit.getDefaultToolkit();
		final int xSize = (int) meh.getScreenSize().getWidth() / 2;
		final int ySize = (int) meh.getScreenSize().getHeight() / 2;
		game.setSize(xSize, ySize);

		this.p1 = p1;
		this.p2 = p2;
		
		this.one = one;
		this.two = two;
		
		board = new Board(one, two);		

		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		//pane.setBackground(Color.blue);

		boardPanel = new JPanel();
		buttonsPanel = new JPanel();

		boardPanel.setLayout(new GridLayout(5, 5));
		buttonsPanel.setLayout(new GridLayout(3, 1));

		pane.add(boardPanel, BorderLayout.CENTER);
		pane.add(buttonsPanel, BorderLayout.WEST);

		cells = new Cell[rows * columns];

		initializeBoard(rows, columns);
		initializeButtons();
		
		

		WindowDestroyer wd = new WindowDestroyer();
		addWindowListener(wd);

		setVisible(true);
		pack();
	}

	public final void initializeBoard(final int rows, final int columns) {
		// Icon icon1 = new ImageIcon(getClass().getResource("piece1"));
		// Icon icon2 = new ImageIcon(getClass().getResource("piece2"));

		for (int i = 0; i < cells.length; i++) {
			cells[i] = new Cell(board, i);
			if (i == 0 || i == 21) {
				cells[i] = new Cell(p1, board, i);
			}
			if (i == 3 || i == 24) {
				cells[i] = new Cell(p2, board, i);
			}
			boardPanel.add(cells[i]);
		}
	}
	
	public static Cell[] getCells() {
		return cells;
	}

	public final void initializeButtons() {
		restart = new JButton(new ImageIcon(getClass().getResource(
				"reset.jpg")));
		restart.addActionListener(this);
		buttonsPanel.add(restart);
		JLabel p1 = new JLabel(one.getName(), JLabel.CENTER);
		JLabel p2 = new JLabel(two.getName(), JLabel.CENTER);
		buttonsPanel.add(p1);
		buttonsPanel.add(p2);

	}

	
	public void mouseClicked(final MouseEvent arg0) {

	}

	
	public void mouseEntered(final MouseEvent arg0) {
		

	}

	
	public void mouseExited(final MouseEvent arg0) {
		

	}

	
	public void mousePressed(final MouseEvent arg0) {
		

	}

	
	public void mouseReleased(final MouseEvent arg0) {
		

	}

	
	public final void actionPerformed(final ActionEvent e) {
		if (e.getSource() == restart) {
			this.setVisible(false);
			this.dispose();
			new NewGame();
		} else {
			return;
		}

	}

}

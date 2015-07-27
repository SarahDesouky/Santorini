package eg.edu.guc.santorini.GUI;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.players.Player;


@SuppressWarnings("serial")
public class NewGame extends JFrame implements ActionListener {

	private Icon p1, p2;
	private JButton cube;
	private JButton pyramid;
	private static int i = 0;
	private JFrame startGame;
	private Player one, two;

	public NewGame() {
		Toolkit meh = Toolkit.getDefaultToolkit();
		final int xSize = (int) meh.getScreenSize().getWidth() / 2;
		final int ySize = (int) meh.getScreenSize().getHeight() / 2;

		p1 = new ImageIcon();
		p2 = new ImageIcon();

		startGame = new JFrame();
		startGame.setVisible(true);
		startGame.setSize(xSize, ySize);

		Container g = startGame.getContentPane();
		g.setLayout(new GridLayout(3, 1));
		
		
		cube = new JButton("Cube");
		pyramid = new JButton("Pyramid");

		cube.addActionListener(this);
		pyramid.addActionListener(this);


		cube.setToolTipText("Cubes can move both horizontally and vertically");
		pyramid.setToolTipText("Pyramids can move diagonally");


		JLabel type = new JLabel("Choose your piece type", JLabel.CENTER);

		g.add(type);

		g.add(cube);
		g.add(pyramid);

		startGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();

	}

	public final Icon getP1() {
		return p1;
	}

	public final Icon getP2() {
		return p2;
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {
		Icon iconC = new ImageIcon(getClass().getResource("Cube.jpg"));
		Icon iconP = new ImageIcon(getClass().getResource("Pyramid.jpg"));
		Icon iconC2 = new ImageIcon(getClass().getResource("CubeGreen.jpg"));
		Icon iconP2 = new ImageIcon(getClass().getResource("PyramidGreen.jpg"));
		
		switch(i) {

		case 0 : 
			if (e.getSource() == cube) {
				p1 = iconC;
				i++;
				String oneName = JOptionPane.showInputDialog(
						"Player one enter your name: ");
				one = new Player(oneName, 1);
			} else {
				p1 = iconP;
				i++;
				String oneName = JOptionPane.showInputDialog(
						"Player one enter your name: ");
				one = new Player(oneName, 2);
			}
			break;

		case 1:
			if (e.getSource() == pyramid) {
				i++;
				p2 = iconP2;
				String twoName = JOptionPane.showInputDialog(
						"Player two enter your name: ");
				two = new Player(twoName, 2);
			} else {
				p2 = iconC2; 
				i++;
				String twoName = JOptionPane.showInputDialog(
						"Player two enter your name: ");
				two = new Player(twoName, 1);
			} 
			break;
		default:
			break;

		}

		if (i == 2) {
			i = 0;
			startGame.setVisible(false);
			startGame.dispose();

			new GameWindow(Board.getSide(), Board.getSide(), p1, p2, one, two);
		}
	}


}





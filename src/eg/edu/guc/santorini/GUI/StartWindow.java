package eg.edu.guc.santorini.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class StartWindow extends JFrame {

	private JButton newGame, exit;

	public StartWindow() {
		super("Santorini");
		Toolkit meh = Toolkit.getDefaultToolkit(); 
		meh.beep();
		final int xSize = (int) meh.getScreenSize().getWidth() / 2;
		final int ySize = (int) meh.getScreenSize().getHeight() / 2;
		Container pane = getContentPane();
		pane.setLayout(new GridLayout(2, 1));

		setSize(xSize, ySize);
		setVisible(true);


		newGame = new JButton("New Game");
		newGame.setBackground(new Color(22, 116, 152));

		exit = new JButton("Exit");
		exit.setBackground(new Color(130, 39, 139));
		pane.add(newGame);

		pane.add(exit);

		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				setVisible(false); 
				dispose();	
				new NewGame();						
			}
		}
				);


		exit.addActionListener(new ActionListener() {


			public void actionPerformed(final ActionEvent e) {
				setVisible(false);
				dispose();
				new Exit();
			}
		}

				);

	}
}




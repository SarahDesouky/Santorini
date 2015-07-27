package eg.edu.guc.santorini.GUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class PieceGUI extends JLabel implements MouseListener {
	
	private int boardLocation;

	public PieceGUI(final Icon icon, final int boardLocation) {
		super(icon);
		this.boardLocation = boardLocation;
	}
	
	public final void setBoardLocation(final int x) {
		boardLocation = x;
	}
	
	public final int getBoardLocation() {
		return boardLocation;
	}

	
	public void mouseClicked(final MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(final MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(final MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(final MouseEvent e) {		
		
	}

	@Override
	public void mouseReleased(final MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}

package eg.edu.guc.santorini.GUI;
import javax.swing.JFrame;

final class Call {

	private Call() {

	}

	public static void main(final String [] args) {
		StartWindow t = new StartWindow();
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.setVisible(true);
		
	}


}

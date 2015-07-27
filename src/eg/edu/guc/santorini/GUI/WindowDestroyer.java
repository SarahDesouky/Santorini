package eg.edu.guc.santorini.GUI;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class WindowDestroyer extends WindowAdapter {
	
	public final void windowClosing(final WindowEvent e) {
		System.exit(0); 
	}
}

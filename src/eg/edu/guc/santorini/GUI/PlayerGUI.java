package eg.edu.guc.santorini.GUI;

public class PlayerGUI {

	private PieceGUI t1, t2;
	
	public PlayerGUI(final PieceGUI t1, final PieceGUI t2) {
		this.t1 = t1;
		this.t2 = t2;
	}
	
	public final PieceGUI getT1() {
		return t1;
	}
	
	public final void setT1(final PieceGUI p) {
		t1 = p;
	}
	
	public final PieceGUI getT2() {
		return t2;
	}
	
	public final void setT2(final PieceGUI p) {
		t2 = p;
		
	}
	

}

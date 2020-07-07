import java.awt.Color;

public class Main {
	private static Frame f = null;
	private static boolean finishCheck ;
	public static void main(String[] args) {
		f =new Frame();
		f.setVisible(true);
	}
	
	public static void setFinishCheck(boolean check ) {
		finishCheck = check;
		f.setVisible(false);
		f.setVisible(true);
	}
}


public class Main {
	private static homepage home;
	public static void main(String[] args) {
		home = new homepage();
		home.setVisible(true);
	}
	
	public static homepage getHomepage() {
		return home;
	}
}

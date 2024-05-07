package scrabble.Console;

public class Console {
	
	private Console() {
	}

	public static void message(String text) {
		System.out.println(text);
	}

	public static final String ligneVide = "                                        ";

	public static void title(String text) {
		separateur();
		separateur();
		message(text);
		separateur();
		separateur();
	}
	
	public static void separateur() {
		message(ligneVide);
	}
}


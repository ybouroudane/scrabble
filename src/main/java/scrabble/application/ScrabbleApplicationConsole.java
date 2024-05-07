package scrabble.application;

public class ScrabbleApplicationConsole {
	
	private static final String LIGNE = "-------------------------------------------------------";

	public void afficherMessageBienvenue() {
		
        System.out.println(LIGNE);
        System.out.println("-- Bienvenue dans notre  jeu de scrabble ! --");
        System.out.println("-- YOUSSEF --");
        System.out.println(LIGNE);
    }
    
    public static void main(String[] args) {
        ScrabbleApplicationConsole jeuScrabble = new ScrabbleApplicationConsole();
        jeuScrabble.afficherMessageBienvenue();
        
    }
}

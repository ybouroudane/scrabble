package scrabble.application;

public class ScrabbleApplicationConsole {
	
	private static final String LIGNE = "-------------------------------------------------------";

	public void afficherMessageBienvenue() {
		
        System.out.println(LIGNE);
        System.out.println("-- Bienvenue dans notre magnifique jeu de scrabble ! --");
        System.out.println(LIGNE);
    }
    
    // application Scrabble
    
    public static void main(String[] args) {
        ScrabbleApplicationConsole jeuScrabble = new ScrabbleApplicationConsole();
        jeuScrabble.afficherMessageBienvenue();
        
        // Appeler d'autres méthodes ou démarrer le jeu ici
    }
}

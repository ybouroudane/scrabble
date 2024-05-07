package application;

public class ScrabbleApplicationConsole {
    
    private static final String LIGNE = "-------------------------------------------------------";

    public void afficherMessageBienvenue() {
        System.out.println(LIGNE);
        System.out.println("-- Bienvenue dans notre magnifique jeu de Scrabble par youssef ! --");
        System.out.println(LIGNE);
    }
    
    
    public void afficherPlateauEtChevalet() {
        AfficherPlateau.afficherPlateau();
        Chevalet chevalet = new Chevalet();
        System.out.println("Votre chevalet : " + chevalet.getJetons());
    }

    public static void main(String[] args) {
        ScrabbleApplicationConsole jeuScrabble = new ScrabbleApplicationConsole();
        jeuScrabble.afficherMessageBienvenue();
        jeuScrabble.afficherPlateauEtChevalet();
    }
}




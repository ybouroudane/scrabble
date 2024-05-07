package scrabble.application;

public class AfficherPlateau {

    private static final int TAILLE_PLATEAU = 15;

    public static void afficherPlateau() {
        System.out.println("Plateau de jeu xxxx du Scrabble :");
        System.out.println("-----------------------------");

        
        for (int i = 0; i < TAILLE_PLATEAU; i++) {
            
            for (int j = 0; j < TAILLE_PLATEAU; j++) {
                if (i == TAILLE_PLATEAU / 2 && j == TAILLE_PLATEAU / 2) {
                   
                    System.out.print("* ");
                } else {
                    
                    System.out.print(". ");
                }
            }
            System.out.println(); 
        }
        System.out.println("-----------------------------");
    }

    public static void main(String[] args) {
        afficherPlateau();
    }
}


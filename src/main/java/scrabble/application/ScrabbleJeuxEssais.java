package scrabble.application;

import scrabble.model.*;

public class ScrabbleJeuxEssais {
    public static void main(String[] args) {
        // Test 1 : Créer et gérer une pioche avec toutes les lettres
        testCreationPioche();

        // Test 2 : Distribution initiale des lettres vers le chevalet
        testDistributionLettresChevalet();

        // Test 3 : Tester les fonctionnalités de l'échange de lettres avec la pioche
        testEchangeLettresPioche();

        // Test 4 : Vérifier le plateau du jeu
        testAffichagePlateau();

    }

    // Test 1 : Créer et gérer une pioche avec toutes les lettres
    private static void testCreationPioche() {
        SacDeJetons pioche = new SacDeJetons();
        System.out.println("Test de création de la pioche avec toutes les lettres :");
        System.out.println("La pioche contient " + pioche.nombreDeJetonsRestants() + " jetons.");
        // Affiche la pioche pour vérifier qu'elle contient toutes les lettres
    }

    // Test 2 : Distribution initiale des lettres vers le chevalet
    private static void testDistributionLettresChevalet() {
        Joueur joueur = new Joueur("Testeur");
        SacDeJetons pioche = new SacDeJetons();
        joueur.piocher(pioche);
        System.out.println("Test de distribution des lettres vers le chevalet :");
        System.out.println(joueur.getNom() + " a obtenu les lettres suivantes : " + joueur.ObtenirChevalet().afficher());
    }

    // Test 3 : Tester les fonctionnalités de l'échange de lettres avec la pioche
    private static void testEchangeLettresPioche() {
        // Simuler l'échange de lettres avec la pioche et afficher le nouveau chevalet
    }

    // Test 4 : Vérifier le plateau du jeu
    private static void testAffichagePlateau() {
        PlateauJeu plateau = new PlateauJeu();
        System.out.println("Test d'affichage du plateau du jeu :");
        System.out.println(plateau.afficherPlateauJeu());
    }

}

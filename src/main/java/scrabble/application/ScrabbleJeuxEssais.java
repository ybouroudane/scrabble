package scrabble.application;

import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Joueur;
import scrabble.model.Lettres;
import scrabble.model.PlateauJeu;
import scrabble.model.SacDeJetons;

import java.util.ArrayList;
import java.util.List;

public class ScrabbleJeuxEssais {
    public static void main(String[] args) {
        // Test 1 : Créer et gérer une pioche avec toutes les lettres
        testCreationPioche();

        // Test 2 : Distribution initiale des lettres vers le chevalet
        testDistributionLettresChevalet();

        // Test 3 : Vérifier le plateau du jeu
        testAffichagePlateau();

        // Test 4 : Création d'un joueur
        testCreationJoueur();

        // Test 5 : Échange de lettres dans le chevalet
        testEchangeLettresChevalet();
    }

    private static void testCreationPioche() {
        SacDeJetons pioche = new SacDeJetons();
        System.out.println("\033[1m Test de création de la pioche avec toutes les lettres : \033[0m");
        System.out.println("La pioche contient " + pioche.nombreDeJetonsRestants() + " jetons.");
    }

    private static void testDistributionLettresChevalet() {
        Joueur joueur = new Joueur("Testeur");
        SacDeJetons pioche = new SacDeJetons();
        joueur.piocher(pioche);
        System.out.println("\033[1m Test de distribution des lettres vers le chevalet : \033[0m");
        System.out.println(joueur.getNom() + " a obtenu les lettres suivantes : " + joueur.ObtenirChevalet().afficher());
    }

    private static void testAffichagePlateau() {
        PlateauJeu plateau = new PlateauJeu();
        System.out.println("\033[1m Test d'affichage du plateau du jeu : \033[0m");
        System.out.println(plateau.afficherPlateauJeu());
    }

    private static void testCreationJoueur() {
        String nomJoueur = "Testeur";
        Joueur joueur = new Joueur(nomJoueur);
        System.out.println("\033[1m Test de création d'un joueur : \033[0m");
        System.out.println("Le joueur a été créé avec succès. Nom du joueur : " + joueur.getNom());
    }

    private static void testEchangeLettresChevalet() {
        Joueur joueur = new Joueur("Testeur");
        joueur.ObtenirChevalet().ajouterJeton(new Jeton(Lettres.A));
        joueur.ObtenirChevalet().ajouterJeton(new Jeton(Lettres.B));
        joueur.ObtenirChevalet().ajouterJeton(new Jeton(Lettres.C));
        joueur.ObtenirChevalet().ajouterJeton(new Jeton(Lettres.D));

        SacDeJetons sacDeJetons = new SacDeJetons();
        
        List<Integer> indices = new ArrayList<>();
        indices.add(0);
        indices.add(2);

        joueur.echangerJetons(sacDeJetons, indices);

        Chevalet chevaletApresEchange = joueur.ObtenirChevalet();
        System.out.println("\033[1m Test d'échange de lettres dans le chevalet : \033[0m");
        System.out.println("Le nombre de jetons dans le chevalet doit rester le même : " + (chevaletApresEchange.obtenirJetons().size() == 4));
        System.out.println("Le jeton à l'indice 0 doit être différent de la lettre A : " + (chevaletApresEchange.obtenirJetons().get(0).ObtenirLettre() != Lettres.A));
        System.out.println("Le jeton à l'indice 2 doit être différent de la lettre C : " + (chevaletApresEchange.obtenirJetons().get(2).ObtenirLettre() != Lettres.C));
    }

}

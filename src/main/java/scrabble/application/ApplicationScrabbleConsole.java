package scrabble.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import scrabble.Console.Console;
import scrabble.model.*;

public class ApplicationScrabbleConsole {

    private static final String VALEUR_ARRET = "OK";

    public static void main(String[] args) {
        PlateauJeu plateauJeu = new PlateauJeu();
        SacDeJetons sacDeJetons = new SacDeJetons();
        Scanner scanner = new Scanner(System.in);
        Console.message(" ");
        Console.message("Bienvenue Dans notre jeu SCRABBLE .");
        Console.message(" ");
        Console.message("Ce jeu est implementé par YOUSSEF , BENJAMIN , KYLLIAN .");
        Console.message(" ");
        Console.message("Voici le Tableau du Jeu : ");
        Console.message(" ");
        Console.message(" ");
        
        Console.message(plateauJeu.afficherPlateauJeu());
        sacDeJetons.melanger();
        afficherJetonsRestantsDansSac(sacDeJetons);

        Console.message("Entrez votre prénom:");
        String nomJoueur = scanner.nextLine();
        Joueur joueur1 = new Joueur(nomJoueur);
        joueur1.piocher(sacDeJetons);
        afficherJetonsRestantsV2(sacDeJetons);
        Console.message("Chevalet de " + nomJoueur + ": " + joueur1.ObtenirChevalet().afficher());
        modifierChevalet(joueur1, sacDeJetons, scanner);

        while (true) {
            placerUneLettreJoueur(joueur1, plateauJeu, scanner);
            joueur1.piocher(sacDeJetons);
            Console.message("Chevalet de " + nomJoueur + ": " + joueur1.ObtenirChevalet().afficher());
        }
    }

    private static void placerUneLettreJoueur(Joueur joueur, PlateauJeu plateau, Scanner scanner) {
        if (plateau.estVide()) {
            int ligneCentrale = PlateauJeu._TAILLE_GRILLE_ / 2;
            int colonneCentrale = PlateauJeu._TAILLE_GRILLE_ / 2;
            placerLettre(joueur, plateau, ligneCentrale, colonneCentrale, scanner);
            Console.message(plateau.afficherPlateauJeu());
        } else {
            Console.message("Entrez la ligne:");
            int ligne = scanner.nextInt() - 1;
            Console.message("Entrez la colonne:");
            int colonne = scanner.nextInt() - 1;

            if (ligne >= 0 && ligne < PlateauJeu._TAILLE_GRILLE_ && colonne >= 0 && colonne < PlateauJeu._TAILLE_GRILLE_
                    && plateau.obtenirCaseA(ligne, colonne).estVide()) {
                placerLettre(joueur, plateau, ligne, colonne, scanner);
                Console.message(plateau.afficherPlateauJeu());
            } else {
                Console.message("Position invalide.");
            }
        }
    }

    private static void placerLettre(Joueur joueur, PlateauJeu plateau, int ligne, int colonne, Scanner scanner) {
        Console.message("Chevalet du joueur: " + joueur.ObtenirChevalet().afficher());
        Console.message("Entrez l'indice de la lettre à placer:");
        int indiceLettre = scanner.nextInt() - 1;

        if (indiceLettre >= 0 && indiceLettre <= Chevalet.MAX_JETONS) {
            Jeton lettre = joueur.ObtenirChevalet().retirerJeton(indiceLettre);
            if (lettre.ObtenirLettre() == Lettres.JOKER) {
                Console.message("Vous avez choisi un joker. Entrez la lettre que vous voulez remplacer :");
                String nouvelleLettre = scanner.next().toUpperCase();
                Lettres nouvelleLettreEnum = Lettres.fromChar(nouvelleLettre.charAt(0));
                lettre = new Jeton(nouvelleLettreEnum);
            }
            plateau.obtenirCaseA(ligne, colonne).setJeton(lettre);
            Console.message("La lettre " + lettre.ObtenirLettre() + " a été placée en (" + (ligne + 1) + ", " + (colonne + 1) + ").");
            Console.message(plateau.afficherPlateauJeu());
            joueur.piocher(new SacDeJetons());
            Console.message("Nouveau chevalet de " + joueur.getNom() + ": " + joueur.ObtenirChevalet().afficher());
            modifierChevalet(joueur, new SacDeJetons(), scanner);
        } else {
            Console.message("Indice de lettre invalide.");
        }
    }

    private static void modifierChevalet(Joueur joueur, SacDeJetons sacDeJetons, Scanner scanner) {
        Console.message("Voulez-vous échanger des jetons du chevalet? (oui/non)");
        String input = scanner.next();

        if (input.equalsIgnoreCase("oui")) {
            List<Integer> indices = choisirJetonsAChanger(scanner);

            if (!indices.isEmpty()) {
                Console.message("Chevalet actuel du joueur: " + joueur.ObtenirChevalet().afficher());
                joueur.echangerJetons(sacDeJetons, indices);
                Console.message("Nouveau chevalet du joueur: " + joueur.ObtenirChevalet().afficher());
            }
        } else if (!input.equalsIgnoreCase("non")) {
            Console.message("Veuillez répondre par 'oui' ou 'non'.");
        }
    }

    private static List<Integer> choisirJetonsAChanger(Scanner scanner) {
        Console.message("Pour remplacer des jetons, entrez les indices un à un puis écrivez OK pour arrêter");
        List<Integer> indices = new ArrayList<>();
        while (true) {
            Console.message("Entrez la position du jeton que vous voulez retirer:");
            String input = scanner.next();

            if (input.equalsIgnoreCase(VALEUR_ARRET)) {
                break;
            } else if (Integer.parseInt(input) < 1 || Integer.parseInt(input) >= Chevalet.MAX_JETONS) {
                Console.message("ERREUR, veuillez rentrer un nombre entre 1 et " + Chevalet.MAX_JETONS + " ou OK pour arrêter:");
            } else if (indices.contains(Integer.parseInt(input) - 1)) {
                Console.message("ERREUR, veuillez rentrer un nombre différent de ceux déjà entrés:");
            } else {
                indices.add(Integer.parseInt(input) - 1);
            }
        }
        return indices;
    }

    private static void afficherJetonsRestantsDansSac(SacDeJetons sacDeJetons) {
        Console.message("Le sac contient " + sacDeJetons.nombreDeJetonsRestants() + " jetons");
    }

    private static void afficherJetonsRestantsV2(SacDeJetons sacDeJetons) {
        Console.message("Maintenant le sac contient " + sacDeJetons.nombreDeJetonsRestants() + " jetons.");
    }
}
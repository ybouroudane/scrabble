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
        Console.message("VOTRE SCORE EST : " + joueur1.getScore());	
        joueur1.piocher(sacDeJetons);
        afficherJetonsRestantsV2(sacDeJetons);

        while (true) {
            Console.message("Chevalet de " + nomJoueur + ": " + joueur1.ObtenirChevalet().afficher());
            joueur1.piocher(sacDeJetons); // Piocher avant de modifier le chevalet

            modifierChevalet(joueur1, sacDeJetons, scanner); // Appel de la méthode modifierChevalet

            while (true) {
                if (insererMot(joueur1, plateauJeu, scanner, sacDeJetons)) {
                    break;
                }
            }

            Console.message("Nouveau chevalet de " + nomJoueur + ": " + joueur1.ObtenirChevalet().afficher());
        }
    }

    private static void modifierChevalet(Joueur joueur, SacDeJetons sacDeJetons, Scanner scanner) {
        while (true) {
            Console.message("Voulez-vous échanger des jetons du chevalet? (oui/non)");
            String input = scanner.next().trim().toLowerCase();

            if (input.equals("oui")) {
                List<Integer> indices = choisirJetonsAChanger(scanner);

                if (!indices.isEmpty()) {
                    Console.message("Ancien chevalet : " + joueur.ObtenirChevalet().afficher());
                    joueur.echangerJetons(sacDeJetons, indices);
                    Console.message("Voici votre Nouveau chevalet: " + joueur.ObtenirChevalet().afficher());
                }
                break;
            } else if (input.equals("non")) {
                break;
            } else {
                Console.message("Veuillez répondre par 'oui' ou 'non'.");
            }
        }
    }
    private static boolean insererMot(Joueur joueur, PlateauJeu plateau, Scanner scanner, SacDeJetons sacDeJetons) {
        List<Integer> indicesLettres = new ArrayList<>();
        boolean hasEnteredValidIndex = false;
        while (!hasEnteredValidIndex) {
            Console.message("Entrez les indices des lettres de votre mot (tapez 'ok' pour terminer) :");
            while (true) {
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("ok")) {
                    break;
                } else {
                    try {
                        int indiceLettre = Integer.parseInt(input);
                        if (indiceLettre >= 1 && indiceLettre <= Chevalet.MAX_JETONS) {
                            if (!indicesLettres.contains(indiceLettre - 1)) {
                                indicesLettres.add(indiceLettre - 1);
                                hasEnteredValidIndex = true;
                            } else {
                                Console.message("Vous avez déjà entré cet indice. Veuillez en choisir un autre.");
                            }
                        } else {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException e) {
                        if (!input.isEmpty()) {
                            Console.message("Indice de lettre invalide. Veuillez réessayer.");
                        }
                    }
                }
            }

            if (indicesLettres.isEmpty()) {
                Console.message("Aucune lettre saisie.");
            }
        }

        List<Jeton> jetonsMotBuilder = new ArrayList<>();
        boolean contientJoker = false;
        int indiceJoker = -1;

        for (int indice : indicesLettres) {
            Jeton jeton = joueur.ObtenirChevalet().obtenirJetons().get(indice);
            jetonsMotBuilder.add(jeton);
            if (jeton.ObtenirLettre() == Lettres.JOKER) {
                contientJoker = true;
                indiceJoker = jetonsMotBuilder.size() - 1;
            }
        }

        if (contientJoker) {
            Console.message("Votre mot contient un joker. Entrez la lettre que vous voulez remplacer :");
            String nouvelleLettre = scanner.next().toUpperCase();
            Lettres nouvelleLettreEnum = Lettres.fromChar(nouvelleLettre.charAt(0));
            Jeton nouveauJeton = new Jeton(nouvelleLettreEnum);
            jetonsMotBuilder.set(indiceJoker, nouveauJeton);
        }

        StringBuilder motBuilder = new StringBuilder();
        for (Jeton jeton : jetonsMotBuilder) {
            motBuilder.append(jeton.ObtenirLettre());
        }
        String mot = motBuilder.toString();

        Console.message("Votre mot est le suivant : " + mot);

        if (plateau.estVide()) {
            int ligneCentrale = PlateauJeu._TAILLE_GRILLE_ / 2;
            int colonneCentrale = PlateauJeu._TAILLE_GRILLE_ / 2;
            Console.message("Choisissez la direction (h pour horizontale, v pour verticale) :");
            String direction = scanner.next().trim().toLowerCase();

            if (direction.equals("h") || direction.equals("v")) {
                if (plateau.placerMot(joueur, mot, ligneCentrale, colonneCentrale, direction.equals("h"))) {
                    // Retirer les lettres utilisées du chevalet
                    for (int indice : indicesLettres) {
                        joueur.ObtenirChevalet().obtenirJetons().set(indice, null);
                    }

                    // Piocher de nouvelles lettres pour compléter le chevalet
                    joueur.piocher(sacDeJetons);

                    // Remplir les emplacements vides du chevalet avec les nouvelles lettres
                    List<Jeton> jetons = joueur.ObtenirChevalet().obtenirJetons();
                    for (int i = 0; i < jetons.size(); i++) {
                        if (jetons.get(i) == null) {
                            jetons.set(i, sacDeJetons.piocherJeton());
                        }
                    }

                    Console.message(plateau.afficherPlateauJeu()); // Afficher le plateau
                    return true;
                } else {
                    Console.message("Placement du mot invalide.");
                    return false;
                }
            } else {
                Console.message("Entrée invalide. Veuillez entrer 'h' pour horizontale ou 'v' pour verticale.");
                return false; // Sortir de la méthode sans placer le mot
            }
        } else {
            Console.message("Entrez les coordonnées de la lettre sur laquelle le mot doit s'appuyer (ligne colonne) :");
            int ligneBase = scanner.nextInt() - 1;
            int colonneBase = scanner.nextInt() - 1;

            Console.message("Choisissez la direction (h pour horizontale, v pour verticale) :");
            String direction = scanner.next().trim().toLowerCase();

            if (direction.equals("h") || direction.equals("v")) {
                if (plateau.placerMot(joueur, mot, ligneBase, colonneBase, direction.equals("h"))) {
                    Console.message("Mot placé avec succès !");
                    Console.message(plateau.afficherPlateauJeu());
                    int scoreMotActuel = 0;
                    for (Jeton jeton : jetonsMotBuilder) {
                        scoreMotActuel += jeton.ObtenirLettre().ObtenirUneValeur();
                    }
                    // Ajouter le score du mot au score du joueur
                    joueur.ajouterScore(scoreMotActuel);

                    Console.message("VOTRE SCORE EST : " + joueur.getScore());

                    // Retirer les lettres utilisées du chevalet
                    for (int indice : indicesLettres) {
                        joueur.ObtenirChevalet().obtenirJetons().set(indice, null);
                    }

                    // Piocher de nouvelles lettres pour compléter le chevalet
                    joueur.piocher(sacDeJetons);

                    // Remplir les emplacements vides du chevalet avec les nouvelles lettres
                    List<Jeton> jetons = joueur.ObtenirChevalet().obtenirJetons();
                    for (int i = 0; i < jetons.size(); i++) {
                        if (jetons.get(i) == null) {
                            jetons.set(i, sacDeJetons.piocherJeton());
                        }
                    }
                    return true;
                } else {
                    Console.message("Placement du mot invalide.");
                    return false;
                }
            } else {
                Console.message("Entrée invalide. Veuillez entrer 'h' pour horizontale ou 'v' pour verticale.");
                return false; // Sortir de la méthode sans placer le mot
            }
        }
    }                
    private static List<Integer> choisirJetonsAChanger(Scanner scanner) {
        Console.message("Pour remplacer des jetons, entrez les indices un à un puis écrivez OK pour arrêter (AUSSI SI VOUS VOULEZ GARDER VOTRE CHEVALET) :");
        List<Integer> indices = new ArrayList<>();
        while (true) {
            String input = scanner.next().trim().toLowerCase();
            if (input.equals(VALEUR_ARRET.toLowerCase())) {
                break;
            } else {
                try {
                    int index = Integer.parseInt(input);
                    if (index >= 1 && index <= Chevalet.MAX_JETONS && !indices.contains(index - 1)) {
                        indices.add(index - 1);
                    } else {
                        Console.message("Indice invalide ou déjà entré, veuillez réessayer.");
                    }
                } catch (NumberFormatException e) {
                    Console.message("Entrée invalide, veuillez entrer un nombre.");
                }
            }
        }
        return indices;
    }

    private static void afficherJetonsRestantsDansSac(SacDeJetons sacDeJetons) {
        Console.message("Nombre de jetons restants dans le sac: " + sacDeJetons.nombreDeJetonsRestants());
    }

    private static void afficherJetonsRestantsV2(SacDeJetons sacDeJetons) {
        Console.message("Maintenant le sac contient " + sacDeJetons.nombreDeJetonsRestants() + " jetons.");
    }
}

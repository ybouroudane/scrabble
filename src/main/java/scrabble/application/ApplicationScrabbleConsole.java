package scrabble.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scrabble.gui.Console;
import scrabble.model.*;

public class ApplicationScrabbleConsole {
    
    public static final String VALEUR_ARRET = "OK"; 
    
    public static void main(String[] args) {
        
        System.out.printf("%s%n", "------Bienvenue dans notre jeu de Scrabble---- ");
        System.out.println();
        System.out.printf("%s%n", "Développé par YOUSSEF, Kyllian et Benjamin");
        System.out.println();
        System.out.printf("%s%n", "Le Scrabble est un jeu où les joueurs doivent former des mots en plaçant des Jetons sur un plateau.");
        System.out.printf("%s%n", "Les joueurs peuvent échanger des Jetons de leur Chevalet avec celles du sac.");
        System.out.println();
        
        PlateauJeu plateauJeu = new PlateauJeu();
        SacDeJetons sacDeJetons = new SacDeJetons();
        Joueur joueur1 = new Joueur("YOUSSEF");
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\033[1m Voici le Plateau du Scrabble : \033[0m");
        System.out.println();
        
        Console.message(plateauJeu.afficherPlateauJeu());
        
        System.out.println("\033[1m Initialisation du sac de jetons en cours.. \033[0m");
        System.out.println();
    
        afficherJetonsRestantsDansSac(sacDeJetons);
        Console.message(sacDeJetons.afficher());
        
        System.out.println();
        
        Console.message("On mélange le sac :");
        sacDeJetons.melanger();
        Console.message(sacDeJetons.afficher());
        
        
        Console.message("Le joueur pioche");
        joueur1.piocher(sacDeJetons);
        
        afficherJetonsRestantsDansSac(sacDeJetons);
        
        
        Console.message("Chevalet du joueur 1 :");
        Console.message(joueur1.ObtenirChevalet().afficher());
        
        modifierChevalet(joueur1, sacDeJetons, scanner);
        
        Console.message("Mercii , Au revoir.");
        
    }

    private static void modifierChevalet(Joueur joueur, SacDeJetons sacDeJetons, Scanner scanner) {
        List<Integer> indices;
        String input;

        do {
            Console.title("Voulez-vous échanger des jetons ? (oui/non)");
            input = scanner.next();
            
            if (input.equalsIgnoreCase("oui")) {
                indices = choisirJetonsAChanger(scanner);
                
                if (!indices.isEmpty()) {
                    Console.message("Chevalet actuel du joueur :");
                    Console.message(joueur.ObtenirChevalet().afficher());
                    
                    joueur.echangerJetons(sacDeJetons, indices);
                    
                    Console.message("Nouveau chevalet du joueur :");
                    Console.message(joueur.ObtenirChevalet().afficher());
                }
            } else if (input.equalsIgnoreCase("non")) {
                break;
            } else {
                Console.message("Veuillez répondre par 'oui' ou 'non'.");
            }
        } while (true);
    }

    private static List<Integer> choisirJetonsAChanger(Scanner scanner) {
        Console.title("  Pour remplacer des jetons, entrez les indices un à un puis écrivez OK pour arrêter"); // Changement du message
        String input; 
        List<Integer> indices = new ArrayList<>();
        do {
            Console.message("Entrez la position du jeton que vous voulez retirer : ");
            input = scanner.next(); // Changement de la méthode nextInt() à next()

            if ((!input.equalsIgnoreCase(VALEUR_ARRET) && (Integer.parseInt(input) < 1 || Integer.parseInt(input) >= Chevalet.MAX_JETONS))) { // Changement de condition
                Console.message("ERREUR, veuillez rentrer un nombre entre 1 et "+ Chevalet.MAX_JETONS +" ou OK pour arrêter : "); // Changement du message
            }
            else if (!input.equalsIgnoreCase(VALEUR_ARRET) && indices.contains(Integer.parseInt(input) - 1)) { // Changement de condition
                Console.message("ERREUR, veuillez rentrer un nombre différent de ceux déjà entrés  : ");
            }
            else if (!input.equalsIgnoreCase(VALEUR_ARRET)) { // Changement de condition
                indices.add(Integer.parseInt(input) - 1); // Changement de parsing de l'entrée
            }
        } while (!input.equalsIgnoreCase(VALEUR_ARRET)); // Changement de condition
        return indices;
    }

    private static void afficherJetonsRestantsDansSac(SacDeJetons sacDeJetons) {
        Console.message("Il y'a "+sacDeJetons.nombreDeJetonsRestants()+" jetons dans le sac :");
    }

}

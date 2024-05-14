package scrabble.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scrabble.Console.Console;
import scrabble.model.*;

public class ApplicationScrabbleConsole {
    
    public static final String VALEUR_ARRET = "OK"; 
    
    public static void main(String[] args) {
        
        Console.message("------Bienvenue dans notre jeu de Scrabble---- ");
        Console.message("");
        Console.message("Développé par YOUSSEF, Kyllian et Benjamin");
        Console.message("");
        Console.message("Le Scrabble est un jeu où les joueurs doivent former des mots en plaçant des Jetons sur un plateau.");
        Console.message("Les joueurs peuvent échanger des Jetons de leur Chevalet avec celles du sac.");
        Console.message("");
        
        PlateauJeu plateauJeu = new PlateauJeu();
        SacDeJetons sacDeJetons = new SacDeJetons();  
        Scanner scanner = new Scanner(System.in);
        
        Console.message("\033[1m Voici le Plateau du Scrabble : \033[0m");
        Console.message("");
        Console.message(plateauJeu.afficherPlateauJeu());
        
        Console.message("\033[1m Initialisation du sac de jetons en cours.. \033[0m");
        Console.message("");
    
        afficherJetonsRestantsDansSac(sacDeJetons);
        
        Console.message("");
        
        Console.message("On mélange le sac ");

        sacDeJetons.melanger();
        
        Console.message("");
        
        Console.message("Entrez votre prénom : ");
        String nomJoueur = scanner.nextLine();
        Joueur joueur1 = new Joueur(nomJoueur);
        
        Console.message("Le joueur pioche");
        Console.message("");
        joueur1.piocher(sacDeJetons);
        
        afficherJetonsRestantsV2(sacDeJetons);
       
        Console.message("");
        
        Console.message("Chevalet de " + nomJoueur + " :");
        Console.message(joueur1.ObtenirChevalet().afficher());
        
        modifierChevalet(joueur1, sacDeJetons, scanner);
        
        Console.message("Merci , Au revoir.");
        
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
        Console.title("  Pour remplacer des jetons, entrez les indices un à un puis écrivez OK pour arrêter"); 
        String input; 
        List<Integer> indices = new ArrayList<>();
        do {
            Console.message("Entrez la position du jeton que vous voulez retirer : ");
            input = scanner.next();

            if ((!input.equalsIgnoreCase(VALEUR_ARRET) && (Integer.parseInt(input) < 1 || Integer.parseInt(input) >= Chevalet.MAX_JETONS))) { 
                Console.message("ERREUR, veuillez rentrer un nombre entre 1 et "+ Chevalet.MAX_JETONS +" ou OK pour arrêter : "); 
            }
            else if (!input.equalsIgnoreCase(VALEUR_ARRET) && indices.contains(Integer.parseInt(input) - 1)) { 
                Console.message("ERREUR, veuillez rentrer un nombre différent de ceux déjà entrés  : ");
            }
            else if (!input.equalsIgnoreCase(VALEUR_ARRET)) { 
                indices.add(Integer.parseInt(input) - 1); 
            }
        } while (!input.equalsIgnoreCase(VALEUR_ARRET)); 
        return indices;
    }

    private static void afficherJetonsRestantsDansSac(SacDeJetons sacDeJetons) {
        Console.message("le sac contient  "+sacDeJetons.nombreDeJetonsRestants()+" jetons");
    }
    private static void afficherJetonsRestantsV2(SacDeJetons sacDeJetons) {
        Console.message("Maintenant le sac contient   "+sacDeJetons.nombreDeJetonsRestants()+" jetons.");
    }

}
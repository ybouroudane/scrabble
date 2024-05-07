package scrabble.model;

public class PlateauJeu {
    public static final int TAILLE_GRILLE = 15;

    private Case[][] cases;

    public PlateauJeu() {
        this.cases = new Case[TAILLE_GRILLE][TAILLE_GRILLE];
        initialiserPlateauVide();
    }

    private void initialiserPlateauVide() {
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                cases[i][j] = new Case();
            }
        }
        cases[TAILLE_GRILLE / 2][TAILLE_GRILLE / 2] = new Case(true);
    }

    public String afficherPlateauJeu() {
        StringBuilder temp = new StringBuilder();

        // Espacement pour aligner les numéros de colonnes avec les cases
        temp.append("  ");
        for (int i = 1; i <= TAILLE_GRILLE; i++) {
            temp.append(String.format("%4d", i)); // Espacement de 4 caractères
        }
        temp.append("\n");

        for (int i = 0; i < TAILLE_GRILLE; i++) {
            temp.append("  ").append("_____________________________________________________________\n");
            temp.append(String.format("%2d", i + 1)); // Numéro de ligne

            for (int j = 0; j < TAILLE_GRILLE; j++) {
                if (i == TAILLE_GRILLE / 2 && j == TAILLE_GRILLE / 2) {
                    temp.append("| * "); // Écrire une étoile dans la case du milieu
                } else {
                    temp.append("|   "); // Sinon, écrire une case vide
                }
            }
            temp.append("|\n");
        }
        temp.append("  ").append("_____________________________________________________________\n");
        return temp.toString();
    }




    public String afficherGrille() {
        StringBuilder temp = new StringBuilder();
        temp.append("     1   2   3   4   5   6   7   8   9  10  11  12  13  14  15\n");
        for (int i = 0; i < 15; i++) {
            temp.append("   -------------------------------------------------------------\n");
            temp.append(String.format("%2d ", i + 1));
            for (int j = 0; j < 15; j++) {
                Case laCase = this.cases[i][j];
                if (laCase.estOccupee()) {
                    temp.append("| " + laCase.ObtenirUnJeton().ObtenirLettre() + " ");
                } else {
                    temp.append("|   ");
                }
            }
            temp.append("|\n");
        }
        temp.append("   -------------------------------------------------------------\n");
        return temp.toString();
    }
}

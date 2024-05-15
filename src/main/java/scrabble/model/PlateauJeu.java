package scrabble.model;

public class PlateauJeu {
    public static final int TAILLE_GRILLE = 15;

    private Case[][] plateau;

    public PlateauJeu() {
        this.plateau = new Case[TAILLE_GRILLE][TAILLE_GRILLE];
        initialiserPlateauVide();
    }

    private void initialiserPlateauVide() {
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                plateau[i][j] = new Case();
            }
        }
        plateau[TAILLE_GRILLE / 2][TAILLE_GRILLE / 2] = new Case(null, true, false, false, false, false, false);
        
        
        int[][] casesDL = {{3, 1}, {7, 1}, {11, 1}, {15, 1}, {2, 2}, {6, 2}, {10, 2}, {14, 2},
                           {1, 3}, {8, 3}, {15, 3}, {1, 4}, {8, 4}, {15, 4}, {2, 6}, {6, 6},
                           {10, 6}, {14, 6}, {3, 7}, {7, 7}, {11, 7}, {15, 7}, {3, 9}, {7, 9},
                           {11, 9}, {15, 9}, {2, 10}, {6, 10}, {10, 10}, {14, 10}, {1, 11},
                           {8, 11}, {15, 11}, {1, 12}, {8, 12}, {15, 12}, {2, 14}, {6, 14},
                           {10, 14}, {14, 14}};
        for (int[] coord : casesDL) {
            plateau[coord[0]-1][coord[1]-1] = new Case(null, false, true, false, false, false, false); // DL
        }

        
        int[][] casesTL = {{5, 1}, {9, 1}, {13, 1}, {5, 5}, {9, 5}, {13, 5},
                           {5, 9}, {9, 9}, {13, 9}, {5, 13}, {9, 13}, {13, 13}};
        for (int[] coord : casesTL) {
            plateau[coord[0]-1][coord[1]-1] = new Case(null, false, false, true, false, false, false); // TL
        }

        int[][] casesDM = {{4, 4}, {12, 4}, {4, 12}, {12, 12}};
        for (int[] coord : casesDM) {
            plateau[coord[0]-1][coord[1]-1] = new Case(null, true, true, false, false, false, false); // DM
        }

        int[][] casesTM = {{1, 1}, {1, 8}, {8, 1}, {15, 15}, {1, 15}, {15, 1}, {15, 8}, {8, 15}};
        for (int[] coord : casesTM) {
            plateau[coord[0]-1][coord[1]-1] = new Case(null, true, false, true, false, false, false); // TM
        }
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
                // Affichage des cases avec leurs spécificités
                if (i == TAILLE_GRILLE / 2 && j == TAILLE_GRILLE / 2) {
                    temp.append("| * "); // Écrire une étoile dans la case du milieu
                } else if (plateau[i][j].contientEtoile()) {
                    if (plateau[i][j].estDoubleMot()) {
                        temp.append("|DM ");
                    } else if (plateau[i][j].estTripleMot()) {
                        temp.append("|TM ");
                    } else if (plateau[i][j].estDoubleLettre()) {
                        temp.append("|DL ");
                    } else if (plateau[i][j].estTripleLettre()) {
                        temp.append("|TL ");
                    }
                } else {
                    temp.append("|   "); // Sinon, écrire une case vide
                }
            }
            temp.append("|\n");
        }
        temp.append("  ").append("_____________________________________________________________\n");
        return temp.toString();
    }
}

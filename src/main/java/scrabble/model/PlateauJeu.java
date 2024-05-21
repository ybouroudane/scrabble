package scrabble.model;

public class PlateauJeu {

    public static final int _TAILLE_GRILLE_ = 15;
    private Case[][] plateau;

    public PlateauJeu() {
        this.plateau = new Case[_TAILLE_GRILLE_][_TAILLE_GRILLE_];
        initialiserPlateauVide();
    }

    private void initialiserPlateauVide() {
        for (int i = 0; i < _TAILLE_GRILLE_; i++) {
            for (int j = 0; j < _TAILLE_GRILLE_; j++) {
                plateau[i][j] = new Case();
            }
        }

        plateau[_TAILLE_GRILLE_ / 2][_TAILLE_GRILLE_ / 2] = new Case(null, false, CaseSpeciale.ETOILE);

        int[][] casesDL = {{3, 1}, {7, 1}, {11, 1}, {15, 1}, {2, 2}, {6, 2}, {10, 2}, {14, 2},
                {1, 3}, {8, 3}, {15, 3}, {1, 4}, {8, 4}, {15, 4}, {2, 6}, {6, 6},
                {10, 6}, {14, 6}, {3, 7}, {7, 7}, {11, 7}, {15, 7}, {3, 9}, {7, 9},
                {11, 9}, {15, 9}, {2, 10}, {6, 10}, {10, 10}, {14, 10}, {1, 11},
                {8, 11}, {15, 11}, {1, 12}, {8, 12}, {15, 12}, {2, 14}, {6, 14},
                {10, 14}, {14, 14}};

        for (int[] coord : casesDL) {
            plateau[coord[0] - 1][coord[1] - 1] = new Case(null, false, CaseSpeciale.DOUBLE_LETTRE);
        }

        int[][] casesTL = {{5, 1}, {9, 1}, {13, 1}, {5, 5}, {9, 5}, {13, 5},
                {5, 9}, {9, 9}, {13, 9}, {5, 13}, {9, 13}, {13, 13}};

        for (int[] coord : casesTL) {
            plateau[coord[0] - 1][coord[1] - 1] = new Case(null, false, CaseSpeciale.TRIPLE_LETTRE);
        }

        int[][] casesDM = {{4, 4}, {12, 4}, {4, 12}, {12, 12}};

        for (int[] coord : casesDM) {
            plateau[coord[0] - 1][coord[1] - 1] = new Case(null, true, CaseSpeciale.DOUBLE_MOT);
        }

        int[][] casesTM = {{1, 1}, {1, 8}, {8, 1}, {15, 15}, {1, 15}, {15, 1}, {15, 8}, {8, 15}};

        for (int[] coord : casesTM) {
            plateau[coord[0] - 1][coord[1] - 1] = new Case(null, true, CaseSpeciale.TRIPLE_MOT);
        }
    }

    public String afficherPlateauJeu() {
        StringBuilder temp = new StringBuilder();

        // Espacement pour aligner les numéros de colonnes avec les cases
        temp.append(" ");
        for (int i = 1; i <= _TAILLE_GRILLE_; i++) {
            temp.append(String.format("%4d", i)); // Espacement de 4 caractères
        }
        temp.append("\n");

        for (int i = 0; i < _TAILLE_GRILLE_; i++) {
            temp.append(" ____________________________________________________________________\n");
            temp.append(String.format("%2d", i + 1)); // Numéro de ligne

            for (int j = 0; j < _TAILLE_GRILLE_; j++) {
                // Affichage des cases avec leurs spécificités et les jetons placés
                Case caseActuelle = plateau[i][j];
                if (caseActuelle.getJeton() != null) {
                    temp.append("| ").append(caseActuelle.getJeton().ObtenirLettre()).append(" ");
                } else if (caseActuelle.ObtenirCaseSpeciale() == CaseSpeciale.ETOILE) {
                    temp.append("|").append(CaseColor.BOLD).append(" * ").append(CaseColor.RESET);
                } else if (caseActuelle.estDoubleMot()) {
                    temp.append("|").append(CaseColor.BLUE).append("DM ").append(CaseColor.RESET);
                } else if (caseActuelle.estTripleMot()) {
                    temp.append("|").append(CaseColor.RED).append("TM ").append(CaseColor.RESET);
                } else if (caseActuelle.estDoubleLettre()) {
                    temp.append("|").append(CaseColor.GREEN).append("DL ").append(CaseColor.RESET);
                } else if (caseActuelle.estTripleLettre()) {
                    temp.append("|").append(CaseColor.YELLOW).append("TL ").append(CaseColor.RESET);
                } else {
                    temp.append("|   "); // Sinon, écrire une case vide
                }
            }
            temp.append("|\n");
        }
        temp.append(" ____________________________________________________________________\n");

        return temp.toString();
    }

    public boolean estVide() {
        for (int i = 0; i < _TAILLE_GRILLE_; i++) {
            for (int j = 0; j < _TAILLE_GRILLE_; j++) {
                if (!plateau[i][j].estVide()) {
                    return false; // Il y a au moins une case non vide
                }
            }
        }
        return true; // Toutes les cases sont vides
    }

    // Nouvelle méthode pour obtenir une case spécifique du plateau
    public Case obtenirCaseA(int ligne, int colonne) {
        return plateau[ligne][colonne];
    }

    public boolean placerMot(Joueur joueur, String mot, int ligneBase, int colonneBase, boolean estHorizontal) {
        int longueurMot = mot.length();

        // Vérifier si c'est le premier mot
        boolean estPremierMot = estVide();

        if (!estPremierMot && !verifierPlacementValide(mot, ligneBase, colonneBase, estHorizontal)) {
            return false;
        }

        if (estHorizontal) {
            if (colonneBase + longueurMot > _TAILLE_GRILLE_) {
                return false; // Le mot dépasse les limites du plateau
            }
            for (int i = 0; i < longueurMot; i++) {
                Case caseActuelle = obtenirCaseA(ligneBase, colonneBase + i);
                Lettres lettre = Lettres.fromChar(mot.charAt(i));
                Jeton jeton = new Jeton(lettre);
                caseActuelle.setJeton(jeton);
                joueur.ObtenirChevalet().retirerJeton(joueur.ObtenirChevalet().obtenirJetons().indexOf(jeton));
            }
        } else {
            if (ligneBase + longueurMot > _TAILLE_GRILLE_) {
                return false; // Le mot dépasse les limites du plateau
            }
            for (int i = 0; i < longueurMot; i++) {
                Case caseActuelle = obtenirCaseA(ligneBase + i, colonneBase);
                Lettres lettre = Lettres.fromChar(mot.charAt(i));
                Jeton jeton = new Jeton(lettre);
                caseActuelle.setJeton(jeton);
                joueur.ObtenirChevalet().retirerJeton(joueur.ObtenirChevalet().obtenirJetons().indexOf(jeton));
            }
        }
        return true;
    }

    public boolean verifierPlacementValide(String mot, int ligneBase, int colonneBase, boolean estHorizontal) {
        int longueurMot = mot.length();
        Case caseBase = obtenirCaseA(ligneBase, colonneBase);

        // Vérifier que la case de base n'est pas vide
        if (caseBase.estVide()) {
            return false;
        }

        if (estHorizontal) {
            // Vérifier que les cases suivantes sont vides
            for (int i = 1; i < longueurMot; i++) {
                Case caseActuelle = obtenirCaseA(ligneBase, colonneBase + i);
                if (!caseActuelle.estVide() && caseActuelle.getJeton().ObtenirLettre() != Lettres.fromChar(mot.charAt(i))) {
                    return false;
                }
            }
        } else {
            // Vérifier que les cases suivantes sont vides
            for (int i = 1; i < longueurMot; i++) {
                Case caseActuelle = obtenirCaseA(ligneBase + i, colonneBase);
                if (!caseActuelle.estVide() && caseActuelle.getJeton().ObtenirLettre() != Lettres.fromChar(mot.charAt(i))) {
                    return false;
                }
            }
        }

        return true;
    }
}
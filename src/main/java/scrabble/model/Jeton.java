package scrabble.model;

public class Jeton {
    private Lettres lettre;

    public Jeton(Lettres lettre) {
        this.lettre = lettre;
    }

    public Lettres ObtenirLettre() {
        return lettre;
    }

    public void DefinirLettre(Lettres lettre) {
        this.lettre = lettre;
    }

    public String afficher() {
        // Tableau de chiffres en indice
        char[] chiffresExposants = new char[11];
        for (int i = 0; i < 10; i++) {
            chiffresExposants[i] = (char) (0x2080 + i);
        }
        chiffresExposants[10] = '\u2081';

        // Obtenir la valeur de la lettre
        int valeur = this.lettre.ObtenirUneValeur();
        String exposant = String.valueOf(chiffresExposants[valeur % 10]);
        if (valeur >= 10) {
            exposant = String.valueOf(chiffresExposants[valeur / 10]) + exposant;
        }

        return this.lettre.name() + exposant;
    }
}

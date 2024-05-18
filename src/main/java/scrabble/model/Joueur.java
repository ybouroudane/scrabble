package scrabble.model;

import java.util.Collections;
import java.util.List;

public class Joueur {
    private final String nom;
    private int score;
    private Chevalet chevalet;

    public Joueur(String nom) {
        this.nom = nom;
        this.score = 0;
        this.chevalet = new Chevalet();
    }

    public int AfficherScore() {
        return score;
    }

    public void DefinirScore(int score) {
        this.score = score;
    }

    public String getNom() {
        return nom;
    }

    public Chevalet ObtenirChevalet() {
        return this.chevalet;
    }

    public void piocher(SacDeJetons sacDeJetons) {
        while (this.chevalet.obtenirJetons().size() < Chevalet.MAX_JETONS) {
            this.chevalet.ajouterJeton(sacDeJetons.piocherJeton());
        }
    }

    public void echangerJetons(SacDeJetons sacDeJetons, List<Integer> indices) {
        Collections.sort(indices, Collections.reverseOrder());
        for (int index : indices) {
            if (index >= 0 && index < Chevalet.MAX_JETONS) {
                sacDeJetons.ajouterJeton(this.chevalet.retirerJeton(index));
            }
        }
        this.piocher(sacDeJetons);
        sacDeJetons.melanger();
    }
}

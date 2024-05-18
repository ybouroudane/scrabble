package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SacDeJetons {
    private List<Jeton> jetons;

    public SacDeJetons() {
        this.jetons = new ArrayList<>();
        initialiserJetons();
        melanger(); 
    }

    private void initialiserJetons() {
        ajouterLettres(Lettres.A, 9);
        ajouterLettres(Lettres.B, 2);
        ajouterLettres(Lettres.C, 2);
        ajouterLettres(Lettres.D, 3);
        ajouterLettres(Lettres.E, 15);
        ajouterLettres(Lettres.F, 2);
        ajouterLettres(Lettres.G, 2);
        ajouterLettres(Lettres.H, 2);
        ajouterLettres(Lettres.I, 8);
        ajouterLettres(Lettres.J, 1);
        ajouterLettres(Lettres.K, 1);
        ajouterLettres(Lettres.L, 5);
        ajouterLettres(Lettres.M, 3);
        ajouterLettres(Lettres.N, 6);
        ajouterLettres(Lettres.O, 6);
        ajouterLettres(Lettres.P, 2);
        ajouterLettres(Lettres.Q, 1);
        ajouterLettres(Lettres.R, 6);
        ajouterLettres(Lettres.S, 6);
        ajouterLettres(Lettres.T, 6);
        ajouterLettres(Lettres.U, 6);
        ajouterLettres(Lettres.V, 2);
        ajouterLettres(Lettres.W, 1);
        ajouterLettres(Lettres.X, 1);
        ajouterLettres(Lettres.Y, 1);
        ajouterLettres(Lettres.Z, 1);
        ajouterLettres(Lettres.JOKER, 2);
    }

    private void ajouterLettres(Lettres lettre, int quantite) {
        for (int i = 0; i < quantite; i++) {
            this.jetons.add(new Jeton(lettre));
        }
    }

    public List<Jeton> obtenirJetons() {
        return this.jetons;
    }

    public Jeton piocherJeton() {
        if (!jetons.isEmpty()) {
            return jetons.remove(0);
        }
        return null;
    }

    public void ajouterJeton(Jeton jeton) {
        this.jetons.add(jeton);
    }

    public void ajouterTous(List<Jeton> jetons) {
        this.jetons.addAll(jetons);
    }

    public boolean estVide() {
        return jetons.isEmpty();
    }

    public int nombreDeJetonsRestants() {
        return jetons.size();
    }

    public void melanger() {
        Collections.shuffle(jetons);
    }

    public String afficher() {
        StringBuilder temp = new StringBuilder();
        for (Jeton jeton : jetons) {
            temp.append(jeton.ObtenirLettre()).append(" ");
        }
        return temp.toString();
    }
}

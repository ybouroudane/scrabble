package scrabble.model;

import java.util.ArrayList;
import java.util.List;

public class Chevalet {
    private List<Jeton> jetons;
    public static final int MAX_JETONS = 7;

    public Chevalet() {
        jetons = new ArrayList<>(MAX_JETONS);
    }

    public List<Jeton> obtenirJetons() {
        return jetons;
    }

    public void ajouterJeton(Jeton jeton) {
        if (jetons.size() < MAX_JETONS) {
            jetons.add(jeton);
        }
    }

    public Jeton retirerJeton(int index) {
        Jeton jetonRetire = null;
        if (index >= 0 && index < jetons.size()) {
            jetonRetire = jetons.remove(index);
        }
        return jetonRetire;
    }

    public void echangerJetons(int index1, int index2) {
        if (index1 >= 0 && index1 < jetons.size() && index2 >= 0 && index2 < jetons.size()) {
            Jeton temp = jetons.get(index1);
            jetons.set(index1, jetons.get(index2));
            jetons.set(index2, temp);
        }
    }

    public String afficher() {
        StringBuilder affichage = new StringBuilder();
        for (Jeton jeton : jetons) {
            affichage.append(jeton.afficher())
                     .append(" ");
        }
        return affichage.toString();
    }
}
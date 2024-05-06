package scrabble.application;

import java.util.ArrayList;
import java.util.List;

public class Chevalet {

    private List<Character> jetons;

    public Chevalet() {
        jetons = new ArrayList<>();
        distribuerJetons();
    }

    private void distribuerJetons() {
        SacDeJetons sac = new SacDeJetons();
        
        for (int i = 0; i < 7; i++) {
            jetons.add(sac.piocherJeton());
        }
    }

    public void echangerLettres(SacDeJetons sac, List<Integer> indexes) {
        for (int index : indexes) {
            char nouvelleLettre = sac.piocherJeton();
            jetons.set(index, nouvelleLettre);
        }
    }

    public List<Character> getJetons() {
        return jetons;
    }

    public static void main(String[] args) {
        Chevalet chevalet = new Chevalet();
        System.out.println("Jetons du chevalet : " + chevalet.getJetons());
    }
}


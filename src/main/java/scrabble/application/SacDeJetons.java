package scrabble.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SacDeJetons {

    private List<Character> jetons;

    public SacDeJetons() {
        jetons = new ArrayList<>();
        initialiserSac();
        melangerJetons();
    }

    private void initialiserSac() {
        
        for (char lettre = 'A'; lettre <= 'Z'; lettre++) {
            int nombreJetons = getNombreJetonsLettre(lettre);
            for (int i = 0; i < nombreJetons; i++) {
                jetons.add(lettre);
            }
        }
    }

    private int getNombreJetonsLettre(char lettre) {
        
        switch (lettre) {
            case 'E':
            case 'A':
            case 'I':
            case 'N':
            case 'O':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
                return 9;
            case 'D':
            case 'L':
            case 'M':
            case 'G':
                return 3;
            case 'B':
            case 'C':
            case 'P':
                return 2;
            case 'F':
            case 'H':
            case 'V':
            case 'W':
            case 'Y':
                return 1;
            default:
                return 1; 
        }
    }

    private void melangerJetons() {
        
        Collections.shuffle(jetons);
    }

    public char piocherJeton() {
        
        if (!jetons.isEmpty()) {
            return jetons.remove(0);
        } else {
            return '\0'; 
        }
    }

    public static void main(String[] args) {
        SacDeJetons sac = new SacDeJetons();
        System.out.println("Jetons du sac : " + sac.jetons);
    }
}


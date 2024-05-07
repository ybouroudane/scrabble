package scrabble.model;

public class Case {
    private Jeton jeton;
    private boolean estOccupee;
    private boolean contientEtoile; 

    public Case() {
        this.jeton = null;
        this.estOccupee = false;
        this.contientEtoile = false; // Par défaut, la case ne contient pas d'étoile
    }

    public Case(boolean contientEtoile) {
        this();
        this.contientEtoile = contientEtoile;     }

    public Jeton ObtenirUnJeton() {
        return jeton;
    }

    public boolean estOccupee() {
        return estOccupee;
    }

    public boolean contientEtoile() {
        return contientEtoile;
    }
}

package scrabble.model;

public class Case {
    private Jeton jeton;
    private boolean estOccupee;
    private boolean contientEtoile;
    private boolean doublePoint;
    private boolean triplePoint;

    // Constructeur primaire
    public Case() {
        this.jeton = null;
        this.estOccupee = false;
        this.contientEtoile = false;
        this.doublePoint = false;
        this.triplePoint = false;
    }

    public Case(boolean contientEtoile, boolean doublePoint, boolean triplePoint) {
        this(); // Appelle le constructeur primaire pour initialiser les variables communes
        this.contientEtoile = contientEtoile;
        this.doublePoint = doublePoint;
        this.triplePoint = triplePoint;
    }

    public Jeton obtenirUnJeton() {
        return this.jeton;
    }

    public boolean estOccupee() {
        return this.estOccupee;
    }

    public boolean contientEtoile() {
        return this.contientEtoile;
    }

    public boolean estDoublePoint() {
        return this.doublePoint;
    }

    public boolean estTriplePoint() {
        return this.triplePoint;
    }
}
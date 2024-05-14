package scrabble.model;

public class Case {
    private Jeton jeton;
    private boolean estOccupee;
    private boolean contientEtoile;
    private boolean estDoubleLettre;
    private boolean estTripleLettre;
    private boolean estDoubleMot;
    private boolean estTripleMot;

    // Constructeur primaire
    public Case() {
        this.jeton = null;
        this.estOccupee = false;
        this.contientEtoile = false;
        this.estDoubleLettre = false;
        this.estTripleLettre = false;
        this.estDoubleMot = false;
        this.estTripleMot = false;
    }

    public Case(boolean contientEtoile, boolean estDoubleLettre, boolean estTripleLettre,
                boolean estDoubleMot, boolean estTripleMot) {
        this(); // Appelle le constructeur primaire pour initialiser les variables communes
        this.contientEtoile = contientEtoile;
        this.estDoubleLettre = estDoubleLettre;
        this.estTripleLettre = estTripleLettre;
        this.estDoubleMot = estDoubleMot;
        this.estTripleMot = estTripleMot;
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

    public boolean estDoubleLettre() {
        return this.estDoubleLettre;
    }

    public boolean estTripleLettre() {
        return this.estTripleLettre;
    }

    public boolean estDoubleMot() {
        return this.estDoubleMot;
    }

    public boolean estTripleMot() {
        return this.estTripleMot;
    }
}

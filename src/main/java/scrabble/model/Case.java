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
    public Case(Jeton jeton, boolean estOccupee, boolean contientEtoile, boolean estDoubleLettre,
                boolean estTripleLettre, boolean estDoubleMot, boolean estTripleMot) {
        this.jeton = jeton;
        this.estOccupee = estOccupee;
        this.contientEtoile = contientEtoile;
        this.estDoubleLettre = estDoubleLettre;
        this.estTripleLettre = estTripleLettre;
        this.estDoubleMot = estDoubleMot;
        this.estTripleMot = estTripleMot;
    }

    // Constructeur secondaire
    public Case() {
        this(null, false, false, false, false, false, false);
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
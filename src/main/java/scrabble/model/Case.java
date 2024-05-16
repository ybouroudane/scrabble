package scrabble.model;

public class Case {
    private Jeton jeton;
    private boolean estOccupee;
    private CaseSpeciale typeCase;

    // Constructeur primaire
    public Case(Jeton jeton, boolean estOccupee, CaseSpeciale typeCase) {
        this.jeton = jeton;
        this.estOccupee = estOccupee;
        this.typeCase = typeCase;
    }

    // Constructeur secondaire
    public Case() {
        this(null, false, CaseSpeciale.NORMALE);
    }
    public Jeton obtenirUnJeton() {
        return this.jeton;
    }

    public boolean estOccupee() {
        return this.estOccupee;
    }

    public boolean estCaseNormale() {
        return typeCase.equals(CaseSpeciale.NORMALE);
    }

    public boolean estCaseEtoile() {
        return typeCase.equals(CaseSpeciale.ETOILE);
    }

    public boolean estDoubleLettre() {
        return typeCase.equals(CaseSpeciale.DOUBLE_LETTRE);
    }

    public boolean estTripleLettre() {
        return typeCase.equals(CaseSpeciale.TRIPLE_LETTRE);
    }

    public boolean estDoubleMot() {
        return typeCase.equals(CaseSpeciale.DOUBLE_MOT);
    }

    public boolean estTripleMot() {
        return typeCase.equals(CaseSpeciale.TRIPLE_MOT);
    }
}
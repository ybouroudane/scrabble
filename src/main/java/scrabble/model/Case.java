package scrabble.model;

public class Case {
    private Jeton jeton;
    private boolean doubleMot;
    private CaseSpeciale caseSpeciale;

    public Case() {
        this.jeton = null;
        this.doubleMot = false;
        this.caseSpeciale = CaseSpeciale.NORMALE;
    }

    public Case(Jeton jeton, boolean doubleMot, CaseSpeciale caseSpeciale) {
        this.jeton = jeton;
        this.doubleMot = doubleMot;
        this.caseSpeciale = caseSpeciale;
    }

    public Jeton getJeton() {
        return jeton;
    }

    public void setJeton(Jeton jeton) {
        this.jeton = jeton;
    }

    public boolean estDoubleMot() {
        return doubleMot;
    }

    public boolean estDoubleLettre() {
        return caseSpeciale == CaseSpeciale.DOUBLE_LETTRE;
    }

    public boolean estTripleLettre() {
        return caseSpeciale == CaseSpeciale.TRIPLE_LETTRE;
    }

    public boolean estTripleMot() {
        return caseSpeciale == CaseSpeciale.TRIPLE_MOT;
    }

    public boolean estVide() {
        return jeton == null;
    }

    public CaseSpeciale ObtenirCaseSpeciale() {
        return caseSpeciale;
    }
}
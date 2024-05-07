package scrabble.Console;

public class Console {

    private Console() {
    }

    public static void message(String texte) {
        System.out.println(texte);
    }

    public static final String LIGNE_SEPARATOR = "---------------------------------------------------------";

    public static void titre(String texte) {
        afficherSeparateur();
        message(texte);
        afficherSeparateur();
    }

    public static void afficherSeparateur() {
        message(LIGNE_SEPARATOR);
    }
}

package scrabble.model;

import java.util.Collections;
import java.util.List;

public class Joueur {
	private final String nom;
	private int score;
	private Chevalet chevalet;
	
	public Joueur(String nom) {
		this.nom = nom;
		this.score = 0;
		this.chevalet = new Chevalet();
	}
	
	public int ObtenirScore() {
		return score;
	}
	public void VoirScore(int score) {
		this.score = score;
	}
	public String getNom() {
		return nom;
	}

	public Chevalet ObtenirChevalet() {
		return this.chevalet;
	}
	
	public void piocher(SacDeJetons SacDeJetons) {
		while(this.chevalet.obtenirJetons().size()<Chevalet.MAX_JETONS) {
			this.chevalet.ajouterJeton(SacDeJetons.piocherJeton());
		}
	}
	
	public void echangerJetons(SacDeJetons SacDeJetons, List<Integer> indices) {
		Collections.sort(indices, Collections.reverseOrder());
		for (int index : indices) {
            if (index >= 0 && index < Chevalet.MAX_JETONS) {
            	SacDeJetons.ajouterJeton(this.chevalet.retirerJeton(index));
            }
        }
		this.piocher(SacDeJetons);
		SacDeJetons.melanger();
	}
}

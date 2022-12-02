package fr.isika.cda22.projet1groupe1.projet1Groupe1;

public class Annuaire {
	
	// Attributs
	public Noeud racine;
	
	// Constructeur
	public Annuaire() {
		super();
		this.racine = null;
	}
	
	public Annuaire (Noeud racine) {
		super();
		this.racine = racine;
	}

	//Getters & Setters
	public Noeud getRacine() {
		return racine;
	}

	public void setRacine(Noeud racine) {
		this.racine = racine;
	}
	
	public boolean isEmpty() {
		return true;
	}
	
}
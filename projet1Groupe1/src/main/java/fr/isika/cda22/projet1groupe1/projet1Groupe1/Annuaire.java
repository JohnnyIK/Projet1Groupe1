package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.RandomAccessFile;
import java.io.IOException;

public class Annuaire {

	// Attributs
	private Noeud racine;

	// Constructeur
	public Annuaire(Noeud racine) {
		super();
		this.racine = racine;
	}

	// Constructeur vide
	public Annuaire() {
		super();
		this.racine = null;
	}

	// Getters & Setters
	public Noeud getRacine() {
		return racine;
	}

	public void setRacine(Noeud racine) {
		this.racine = racine;
	}

	// Méthode boolean si Annuaire vide
	public boolean isEmpty() {
		return (this.racine == null);
	}

	// Méthode toString avec si arbre vide
	public String toString() {
		if (this.isEmpty()) {
			return "Annuaire vide";
		} else {
			return "Annuaire [racine=" + racine + "]";
		}
	}

	/*
	// Méthode ajouter Stagiaire dans arbre
	public void ajouter(Stagiaire clef) {
		if (this.isEmpty()) {
			this.racine = new Noeud(clef, null, null);
		} else {
			this.racine.ajouterStagiaire(clef);
		}
	}
	
	// Methode pour sauvegarder l'annuaire dans un fichier binaire
	public void sauvegarderFichierBinaire() {
		if (this.isEmpty()) {
			System.out.println("error annuaire vide");
		} else {
			this.racine.sauvegarderFichierBinaireNoeud();
		}
	}
	
	public int nombreNoeud() {
		if (this.isEmpty()) {
			return -1;
		} else {
			return this.racine.nombreNoeuds();
		}
	}
	*/
	


	
}
package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.RandomAccessFile;

public class Noeud {

	// Attributs
	private Stagiaire cle;
	private Noeud filsGauche;
	private Noeud filsDroit;

	// Constructeur
	public Noeud(Stagiaire cle, Noeud filsGauche, Noeud filsDroit) {
		super();
		this.cle = cle;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}

	// getters & setters
	public Stagiaire getCle() {
		return cle;
	}

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}

	public Noeud getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}

	public Noeud getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	}

	// Méthode to String avec GND
	public String toString() {
		String res = "";
		// gauche
		if (this.filsGauche != null) {
			res += this.filsGauche.toString();
		}
		// Noeud
		res += this.cle.toString();
		// Droite
		if (this.filsDroit != null) {
			res += this.filsDroit.toString();
		}
		return res;
	}

	// Méthode pour ajouter stagiaire
	public void ajouterStagiaire(Stagiaire cleAjout) {
		if (this.cle.compareTo(cleAjout) > 0) {
			if (this.filsDroit == null) {
				this.filsDroit = new Noeud(cleAjout, null, null);
			} else {
				this.filsDroit.ajouterStagiaire(cleAjout);
			}
		} else {
			if (this.filsGauche == null) {
				this.filsGauche = new Noeud(cleAjout, null, null);
			} else {
				this.filsGauche.ajouterStagiaire(cleAjout);
			}

		}

	}

	// Methode récursive de parcours de l'annuaire pour sauvegarder (= ecrire) dans le fichier binaire 
	public void sauvegarderFichierBinaireNoeud() {
		// Si le noeudGauche != null, alors on passe la méthode récursive au fils gauche
		if (this.getFilsGauche() != null) {
			this.getFilsGauche().sauvegarderFichierBinaireNoeud();
		}
		// Le filsGauche == null, alors on sauvegarde (écrit) dans le fichier binaire les attributs du stagiaire
		this.getCle().sauvegarderFichierBinaireStagiaire();
		// Si le noeudDroit != null, alors on passe la méthode récursive au fils droit
		if (this.getFilsDroit() != null) {
			this.getFilsDroit().sauvegarderFichierBinaireNoeud();
		}
	}
	
	public int nombreNoeuds() {
		int nombreNoeuds = 0;
		if (this.getFilsGauche() != null) {
			nombreNoeuds += this.getFilsGauche().nombreNoeuds();
		}
		nombreNoeuds += 1;
		if (this.getFilsDroit() != null) {
			nombreNoeuds += this.getFilsDroit().nombreNoeuds();
		}
		return nombreNoeuds;
	}

}

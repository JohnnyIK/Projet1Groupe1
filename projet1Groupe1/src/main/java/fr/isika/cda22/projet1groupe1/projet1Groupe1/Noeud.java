package fr.isika.cda22.projet1groupe1.projet1Groupe1;

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

}

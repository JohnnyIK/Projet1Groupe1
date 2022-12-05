package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Noeud implements ParametreGestionnaire {

	// Attributs
	private Stagiaire cle;
	private int indexFilsGauche = -1;
	private int indexFilsDroit = -1;
	private int indexDoublon = -1;

	// Constructeur
	public Noeud(Stagiaire cle, int indexFilsGauche, int indexFilsDroit, int indexDoublon) {
		super();
		this.cle = cle;
		this.indexFilsGauche = indexFilsGauche;
		this.indexFilsDroit = indexFilsDroit;
		this.indexDoublon = indexDoublon;
	}

	// getters & setters
	public Stagiaire getCle() {
		return cle;
	}

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}

	public int getIndexFilsGauche() {
		return indexFilsGauche;
	}

	public void setIndexFilsGauche(int indexFilsGauche) {
		this.indexFilsGauche = indexFilsGauche;
	}

	public int getIndexFilsDroit() {
		return indexFilsDroit;
	}

	public void setIndexFilsDroit(int indexFilsDroit) {
		this.indexFilsDroit = indexFilsDroit;
	}

	public int getIndexDoublon() {
		return indexDoublon;
	}

	public void setIndexDoublon(int indexDoublon) {
		this.indexDoublon = indexDoublon;
	}

	// Méthode pour ajouter stagiaire
	public void ajouterStagiaireBin(Noeud noeudAjout, RandomAccessFile raf) {
		try {

			// puis on ecrit dans le fichier binaire les attributs du stagiaire (avec si
			// besoin un formatage de la longueur de l'attribut)

			noeudAjout = new Noeud(noeudAjout.getCle(), this.indexFilsGauche, this.indexFilsDroit, this.indexDoublon);

			recuperationAttributsStagiaireBin(noeudAjout.getCle(), raf);
			raf.writeInt(noeudAjout.getIndexFilsGauche());
			raf.writeInt(noeudAjout.getIndexFilsDroit());
			raf.writeInt(noeudAjout.getIndexDoublon());

			ecritureBinRecursive(noeudAjout, raf);

			long index = raf.length() / TAILLE_NOEUD_OCTET;

			if (index == 0) {
				System.out.println("ERR");
			} else {
				// String nomPrecedent = recupereNomPrecedent(raf);
				// noeudAjout.getCle().getNom().compareTo(nomPrecedent);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ecritureBinRecursive(Noeud noeudAjout, RandomAccessFile raf){
		try {
			long index = raf.length()/TAILLE_NOEUD_OCTET;
			int i = 0;
			if (noeudAjout.getCle().getNom().compareTo(recupereNomIndex(i)) < 0){

				if (noeudAjout.getIndexFilsGauche() != -1){
					ecritureBinRecursive(noeudAjout,raf);
					i++;
				} else {
					ecrireFilsGauche();
					
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	public void ecrireFilsGauche(int index) {
		System.out.println();
	}

	public String recupereNomIndex(int index) {
		String nom = "";
		return nom;
	}

	public void recuperationAttributsStagiaireBin(Stagiaire stagiaire, RandomAccessFile raf) {
		try {

			String nomLong = stagiaire.getNomFormate();
			String prenomLong = stagiaire.getPrenomFormate();
			raf.writeChars(nomLong);
			raf.writeChars(prenomLong);
			raf.writeChars(stagiaire.getDepartement());
			raf.writeChars(stagiaire.getFormationFormate());
			raf.writeChars(stagiaire.getAnneeFormation());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * // Methode récursive de parcours de l'annuaire pour sauvegarder (= ecrire)
	 * dans // le fichier binaire public void sauvegarderFichierBinaireNoeud() { //
	 * Si le noeudGauche != null, alors on passe la méthode récursive au fils gauche
	 * if (this.getFilsGauche() != null) {
	 * this.getFilsGauche().sauvegarderFichierBinaireNoeud(); } // Le filsGauche ==
	 * null, alors on sauvegarde (écrit) dans le fichier binaire // les attributs du
	 * stagiaire this.getCle().sauvegarderFichierBinaireStagiaire(); // Si le
	 * noeudDroit != null, alors on passe la méthode récursive au fils droit if
	 * (this.getFilsDroit() != null) {
	 * this.getFilsDroit().sauvegarderFichierBinaireNoeud(); } }
	 * 
	 * public int nombreNoeuds() { int nombreNoeuds = 0; if (this.getFilsGauche() !=
	 * null) { nombreNoeuds += this.getFilsGauche().nombreNoeuds(); } nombreNoeuds
	 * += 1; if (this.getFilsDroit() != null) { nombreNoeuds +=
	 * this.getFilsDroit().nombreNoeuds(); } return nombreNoeuds; }
	 * 
	 * public void ecrireNoeudBin() {
	 * 
	 * }
	 * 
	 * public Noeud rechercherNoeudBin() { return null; }
	 * 
	 */

}

/*
 * public class Noeud {
 * 
 * // Attributs private Stagiaire cle; private Noeud filsGauche; private Noeud
 * filsDroit;
 * 
 * // Constructeur public Noeud(Stagiaire cle, Noeud filsGauche, Noeud
 * filsDroit) { super(); this.cle = cle; this.filsGauche = filsGauche;
 * this.filsDroit = filsDroit; }
 * 
 * // getters & setters public Stagiaire getCle() { return cle; }
 * 
 * public void setCle(Stagiaire cle) { this.cle = cle; }
 * 
 * public Noeud getFilsGauche() { return filsGauche; }
 * 
 * public void setFilsGauche(Noeud filsGauche) { this.filsGauche = filsGauche; }
 * 
 * public Noeud getFilsDroit() { return filsDroit; }
 * 
 * public void setFilsDroit(Noeud filsDroit) { this.filsDroit = filsDroit; }
 * 
 * // Méthode to String avec GND public String toString() { String res = ""; //
 * gauche if (this.filsGauche != null) { res += this.filsGauche.toString(); } //
 * Noeud res += this.cle.toString(); // Droite if (this.filsDroit != null) { res
 * += this.filsDroit.toString(); } return res; }
 * 
 * // Méthode pour ajouter stagiaire public void ajouterStagiaire(Stagiaire
 * cleAjout) { if (this.cle.compareTo(cleAjout) > 0) { if (this.filsDroit ==
 * null) { this.filsDroit = new Noeud(cleAjout, null, null); } else {
 * this.filsDroit.ajouterStagiaire(cleAjout); } } else { if (this.filsGauche ==
 * null) { this.filsGauche = new Noeud(cleAjout, null, null); } else {
 * this.filsGauche.ajouterStagiaire(cleAjout); }
 * 
 * }
 * 
 * }
 * 
 * 
 * // Methode récursive de parcours de l'annuaire pour sauvegarder (= ecrire)
 * dans le fichier binaire public void sauvegarderFichierBinaireNoeud() { // Si
 * le noeudGauche != null, alors on passe la méthode récursive au fils gauche if
 * (this.getFilsGauche() != null) {
 * this.getFilsGauche().sauvegarderFichierBinaireNoeud(); } // Le filsGauche ==
 * null, alors on sauvegarde (écrit) dans le fichier binaire les attributs du
 * stagiaire this.getCle().sauvegarderFichierBinaireStagiaire(); // Si le
 * noeudDroit != null, alors on passe la méthode récursive au fils droit if
 * (this.getFilsDroit() != null) {
 * this.getFilsDroit().sauvegarderFichierBinaireNoeud(); } }
 * 
 * public int nombreNoeuds() { int nombreNoeuds = 0; if (this.getFilsGauche() !=
 * null) { nombreNoeuds += this.getFilsGauche().nombreNoeuds(); } nombreNoeuds
 * += 1; if (this.getFilsDroit() != null) { nombreNoeuds +=
 * this.getFilsDroit().nombreNoeuds(); } return nombreNoeuds; }
 * 
 * public void ecrireNoeudBin() {
 * 
 * }
 * 
 * public Noeud rechercherNoeudBin() { return null; }
 * 
 * }
 */

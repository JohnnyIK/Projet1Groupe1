package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Classe stagiaire pour creer des Objets stagiaires
 *
 */
public class Stagiaire implements ParametreGestionnaire {

	// Attributs ------------------------------------------------------------------------------------------------------
	private String nom;
	private String prenom;
	private String departement;
	private String formation;
	private String anneeFormation;
	
	// Constructeurs  ------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur stagiaire prennat en arguments : nom / prenom / departement / formation / anneeFormation
	 * @param nom
	 * @param prenom
	 * @param departement
	 * @param formation
	 * @param anneeFormation
	 */
	public Stagiaire(String nom, String prenom, String departement, String formation, String anneeFormation) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.formation = formation;
		this.anneeFormation = anneeFormation;
	}
	
	// Constructeur vide
	public Stagiaire() {
		// TODO Auto-generated constructor stub
	}

	// Getters and Setters  ------------------------------------------------------------------------------------------------------
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public String getAnneeFormation() {
		return anneeFormation;
	}

	public void setAnneeFormation(String anneeFormation) {
		this.anneeFormation = anneeFormation;
	}

	//Methodes  ------------------------------------------------------------------------------------------------------
	// ToString
	public String toString() {
		return " Stagiaire : " + nom + " " + prenom + ".";
	}

	/**
	 * Méthode compareTo pour Stagiaire
	 * @param myStagiaire
	 * @return nombre negatif si le nom du stagiaire entré en argument est plus petit que celui comparé, positif si plus grand, 0 si egaux
	 */
	public int compareTo(Stagiaire myStagiaire) {
		
		if (myStagiaire.getNom().compareTo(this.nom) == 0) {
			return myStagiaire.getPrenom().compareTo(this.prenom);
		} else {
			return myStagiaire.getNom().compareTo(this.nom);
		}
	}
	
	/**
	 * Creation d'un "nomLong" de TAILLE_MAX_NOM pour l'écriture dans le fichier binaire
	 * @return un nom formate pour l'ecriture dans le fichier binaire
	 */
	public String getNomFormate() {
		// Dans tous les cas, on ne va pas tronquer le nom / prenom car sinon on perd
		// l'information.
		// Donc on rajoute " " après chaque nom/prenom pour atteindre une string nomLong
		// de taille TAILLE_MAX_NOMPRENOM
		String nomLong = this.nom;
		for (int i = this.nom.length(); i < TAILLE_MAX_NOM; i++) {
			nomLong += " ";
		}
		return nomLong;
	}

	/**
	 * Creation d'un "prenomLong" de TAILLE_MAX_PRENOM pour l'écriture dans le fichier binaire
	 * @return un prenom formate pour l'ecriture dans le fichier binaire
	 */
	public String getPrenomFormate() {
		// Dans tous les cas, on ne va pas tronquer le nom / prenom car sinon on perd
		// l'information.
		// Donc on rajoute " " après chaque nom/prenom pour atteindre une string nomLong
		// de taille TAILLE_MAX_NOMPRENOM
		String prenomLong = this.prenom;
		for (int i = this.prenom.length(); i < TAILLE_MAX_PRENOM; i++) {
			prenomLong += " ";
		}
		return prenomLong;
	}

	/**
	 * Creation d'un "formationLong" de TAILLE_MAX_PRENOM pour l'écriture dans le fichier binaire
	 * @return une formation formate pour l'ecriture dans le fichier binaire
	 */
	public String getFormationFormate() {
		// Dans tous les cas, on ne va pas tronquer le nom de la formation car sinon on
		// perd
		// l'information.
		// Donc on rajoute " " après chaque nom de formation pour atteindre une string
		// formationLong
		// de taille TAILLE_MAX_FORMATION
		String formationLong = this.getFormation();
		for (int i = this.getFormation().length(); i < TAILLE_MAX_FORMATION; i++) {
			formationLong += " ";
		}
		return formationLong;
	}

	/**
	 * Creation d'un "departementLong" de TAILLE_MAX_DEPARTEMENT pour l'écriture dans le fichier binaire
	 * @return un departement formate pour l'ecriture dans le fichier binaire
	 */
	public String getDepartementFormate() {
		// Dans tous les cas, on ne va pas tronquer le departement car sinon on perd
		// l'information.
		// Donc on rajoute " " après chaque departement pour atteindre une string
		// departementLong
		// de taille TAILLE_MAX_DEPARTEMENT
		String departementLong = this.getDepartement();
		for (int i = this.getDepartement().length(); i < TAILLE_MAX_DEPARTEMENT; i++) {
			departementLong += " ";
		}
		return departementLong;
	}

	/**
	 * Creation d'un "anneeLong" de TAILLE_MAX_ANNEEFORMATION pour l'écriture dans le fichier binaire
	 * @return une annee formate pour l'ecriture dans le fichier binaire
	 */
	public String getAnneeFormationFormate() {
		// Dans tous les cas, on ne va pas tronquer l'annee car sinon on perd
		// l'information.
		// Donc on rajoute " " après chaque annee pour atteindre une string
		// anneeLong
		// de taille TAILLE_MAX_ANNEEFORMATION
		String anneeLong = this.getAnneeFormation();
		for (int i = this.getAnneeFormation().length(); i < TAILLE_MAX_ANNEEFORMATION; i++) {
			anneeLong += " ";
		}
		return anneeLong;
	}

}

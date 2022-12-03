package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Stagiaire implements ParametreGestionnaire {

	// Attributs
	private String nom;
	private String prenom;
	private String departement;
	private String formation;
	private String anneeFormation;

//	public final static int TAILLE_MAX_NOM = 20;
//	public final static int TAILLE_MAX_PRENOM = 20;
//	public final static int TAILLE_MAX_DEPARTEMENT = 2;
//	public final static int TAILLE_MAX_FORMATION = 15;
//	public final static int TAILLE_MAX_ANNEEFORMATION = 4;
//	public final static int TAILLE_STAGIAIRE_OCTET = (2 * TAILLE_MAX_NOM) + (2 * TAILLE_MAX_PRENOM)
//			+ (2 * TAILLE_MAX_DEPARTEMENT) + (2 * TAILLE_MAX_FORMATION) + (2 * TAILLE_MAX_ANNEEFORMATION);

	private static int cptNumStagiaire = 0;

	// Constructeur
	public Stagiaire(String nom, String prenom, String departement, String formation, String anneeFormation) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.formation = formation;
		this.anneeFormation = anneeFormation;
	}

	// Getters and Setters
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

	// ToString
	public String toString() {
		return " Stagiaire : " + nom + " " + prenom + ".";
	}

	// Méthode compareTo pour Stagiaire
	public int compareTo(Stagiaire myStagiaire) {
		if (myStagiaire.getNom().compareTo(this.nom) == 0) {
			return myStagiaire.getPrenom().compareTo(this.prenom);
		} else {
			return myStagiaire.getNom().compareTo(this.nom);
		}
	}

	// Creation d'un "nomLong" de TAILLE_MAX_NOM pour l'écriture dans le fichier
	// binaire
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

	// Creation d'un "prenomLong" de TAILLE_MAX_PRENOM pour l'écriture dans le
	// fichier binaire
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

	// Creation d'un "prenomLong" de TAILLE_MAX_PRENOM pour l'écriture dans le
	// fichier binaire
	public String getFormationFormate() {
		// Dans tous les cas, on ne va pas tronquer le nom / prenom car sinon on perd
		// l'information.
		// Donc on rajoute " " après chaque nom/prenom pour atteindre une string nomLong
		// de taille TAILLE_MAX_NOMPRENOM
		String formationLong = this.getFormation();
		for (int i = this.getFormation().length(); i < TAILLE_MAX_FORMATION; i++) {
			formationLong += " ";
		}
		return formationLong;
	}


	// Sauvegarde d'un stagiaire = ecriture de ses attributs dans le fichier binaire
	// + on incrémente le compteur stagiaires cptNumStagiaire
	public void sauvegarderFichierBinaireStagiaire() {

		try {
			// on accède au fichier binaire et on ouvre le flux "raf"
			RandomAccessFile raf = new RandomAccessFile("src/main/java/Fichier/sauvegardeAnnuaire.bin", "rw");

			// le lecteur de fichier binaire se place à la position "cptNumStagiaire *
			// TAILLE_STAGIAIRE_OCTET"
			// car .seek() se base sur l'octet et non sur le caractère (1 letter = 2 octets
			// / 1 int = 4 octets)
			raf.seek(cptNumStagiaire * TAILLE_STAGIAIRE_OCTET);

			// puis on ecrit dans le fichier binaire les attributs du stagiaire (avec si
			// besoin un formatage de la longueur de l'attribut)
			String nomLong = this.getNomFormate();
			String prenomLong = this.getPrenomFormate();
			raf.writeChars(nomLong);
			raf.writeChars(prenomLong);
			raf.writeChars(this.getDepartement());
			raf.writeChars(this.getFormationFormate());
			raf.writeChars(this.getAnneeFormation());

			// puis on incrémente le compteur stagiaires cptNumStagiaire
			cptNumStagiaire++;

			// On ferme le flux "raf"
			raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.IOException;
import java.io.RandomAccessFile;


/*
public class CodeRepository implements ParametreGestionnaire {
	
	// Sauvegarde d'un stagiaire = ecriture de ses attributs dans le fichier binaire
	// + on incrémente le compteur stagiaires cptNumStagiaire
	public void sauvegarderFichierBinaireStagiaire() {

		try {
			// on accède au fichier binaire et on ouvre le flux "raf"
			RandomAccessFile raf = new RandomAccessFile(CHEMIN_BIN, "rw");

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
			raf.writeChars(this.getDepartementFormate());
			raf.writeChars(this.getFormationFormate());
			raf.writeChars(this.getAnneeFormationFormate());

			// puis on incrémente le compteur stagiaires cptNumStagiaire
			cptNumStagiaire++;

			// On ferme le flux "raf"
			raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void sauvegarderFichierBinaireNoeud() {
		// Si le noeudGauche != null, alors on passe la méthode récursive au fils gauche
		if (this.getFilsGauche() != null) {
			this.getFilsGauche().sauvegarderFichierBinaireNoeud();
		} // Le filsGauche == null, alors on sauvegarde (écrit) dans le fichier binaire
		// les attributs du stagiaire
		this.getCle().sauvegarderFichierBinaireStagiaire(); // Si lenoeudDroit != null, alors on passe la méthode récursive au fils droit
		if (this.getFilsDroit() != null) {
			this.getFilsDroit().sauvegarderFichierBinaireNoeud();
		}
	}
	
	// Méthode pour importer un fichier de sauvegarde bin et générer un annuaire
	public Annuaire importSauvegardeBin() {

		Annuaire annuaire = new Annuaire();

		try {
			// on ouvre le flux "raf" associé au fichier binaire
			RandomAccessFile raf = new RandomAccessFile(CHEMIN_BIN, "rw");

			long tailleBinOctet = raf.length();
			System.out.println("TAILLE_STAGIAIRE_OCTET =" + TAILLE_STAGIAIRE_OCTET);
			System.out.println("longueur =" + tailleBinOctet);
			long nbrStagiaire = tailleBinOctet / TAILLE_STAGIAIRE_OCTET;
			System.out.println("nbrStagiaire =" + nbrStagiaire);

			long i = 0;

			while (i < nbrStagiaire) {
				// on positionne la tête de lecture en position "i * TAILLE_STAGIAIRE_OCTET"
				// car .seek() se base sur l'octet et non sur le caractère (1 letter = 2 octets
				// / 1 int = 4 octets)
				// contrairement à .readChar() qui lui se déplace de caractère en caractère
				raf.seek(i * TAILLE_STAGIAIRE_OCTET);

				// on crée les variables qui vont stocker les valeurs des attributs du stagiaire
				String nom = "";
				String prenom = "";
				String departement = "";
				String formation = "";
				String anneeFormation = "";

				// DEBUT DE LA LECTURE DU "raf"
				// On commence à parcourir/lire le fichier binaire selon ce principe :
				// 1--> de 0 à TAILLE_MAX_NOM, on sait qu'il s'agit des lettres du "nom" du
				// stagiaire
				for (int k = 0; k < TAILLE_MAX_NOM; k++) {
					nom += raf.readChar();
				}
				// 1--> de 0 à TAILLE_MAX_PRENOM, on sait qu'il s'agit des lettres du "prenom"
				// du stagiaire
				for (int k = 0; k < TAILLE_MAX_PRENOM; k++) {
					prenom += raf.readChar();
				}
				// 1--> de 0 à TAILLE_MAX_DEPARTEMENT, on sait qu'il s'agit des numeros du
				// "département" du stagiaire
				for (int k = 0; k < TAILLE_MAX_DEPARTEMENT; k++) {
					departement += raf.readChar();
				}
				// 1--> de 0 à TAILLE_MAX_FORMATION, on sait qu'il s'agit des lettres de la
				// "formation" du stagiaire
				for (int k = 0; k < TAILLE_MAX_FORMATION; k++) {
					formation += raf.readChar();
				}
				// 1--> de 0 à TAILLE_MAX_ANNEEFORMATION, on sait qu'il s'agit des lettres de l'
				// "annee de formation" du stagiaire
				for (int k = 0; k < TAILLE_MAX_ANNEEFORMATION; k++) {
					anneeFormation += raf.readChar();
				}
				// FIN DE LA LECTURE

				// On ajoute le stagiaire à l'annuaire
				// annuaire.ajouter(new Stagiaire(nom.trim(), prenom.trim(), departement,
				// formation.trim(), anneeFormation));
				i++;

			}

			// on ferme le flux "raf"
			raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// on retourne l'annuaire
		return annuaire;
	}
	
	
}
	
		/*
	 * METHODE TEXTE > ARBRE public Annuaire importAnnuaireTexte() {
	 * 
	 * Annuaire annuaire = new Annuaire();
	 * 
	 * try {
	 * 
	 * FileReader fichier = new FileReader("src/main/java/Fichier/STAGIAIRES.DON");
	 * BufferedReader br = new BufferedReader(fichier);
	 * 
	 * 
	 * // if (annuaire != null) { // si l'annuaire est vide
	 * 
	 * while (br.ready()) {
	 * 
	 * String nom = br.readLine(); String prenom = br.readLine(); String departement
	 * = br.readLine(); String formation = br.readLine(); String annee =
	 * br.readLine(); br.readLine();
	 * 
	 * Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, formation,
	 * annee);
	 * 
	 * annuaire.ajouter(stagiaire);
	 * 
	 * } // } accolade du if System.out.println(annuaire);
	 * 
	 * fichier.close();
	 * 
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } return annuaire;
	 * 
	 * }
	 */
	
	



















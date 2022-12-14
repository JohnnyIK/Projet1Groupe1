package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * /**
 * Classe pour instancier des Objets Noeud ( = indexNoeud / Stagiaire / indexFilsGauche / indexFilsDroit / indexDoublon
 *
 */
public class Noeud implements ParametreGestionnaire {

	// Attributs ----------------------------------------------------------------------------------------------------------
	private int indexNoeud = 0;
	private Stagiaire cle;
	private int indexFilsGauche = -1;
	private int indexFilsDroit = -1;
	private int indexDoublon = -1;

	// Constructeur --------------------------------------------------------------------------------------------------------
	public Noeud(int indexNoeud, Stagiaire cle, int indexFilsGauche, int indexFilsDroit, int indexDoublon) {
		super();
		this.indexNoeud = indexNoeud;
		this.cle = cle;
		this.indexFilsGauche = indexFilsGauche;
		this.indexFilsDroit = indexFilsDroit;
		this.indexDoublon = indexDoublon;
	}

	// Constructeur vide
	public Noeud() {

	}

	// Constructeur avec un stagiaire en attributs (indexNoeud = -1 /
	// indexFilsGauche = -1 / indexFilsDroit = -1 / indexDoublon = -1
	public Noeud(Stagiaire cle) {
		super();
		this.indexNoeud = -1;
		this.cle = cle;
		this.indexFilsGauche = -1;
		this.indexFilsDroit = -1;
		this.indexDoublon = -1;
	}

	// getters & setters ---------------------------------------------------------------------------------------------------
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

	public int getIndexNoeud() {
		return indexNoeud;
	}

	public void setIndexNoeud(int indexNoeud) {
		this.indexNoeud = indexNoeud;
	}
 
	// Methodes -------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Noeud [indexNoeud=" + indexNoeud + ", cle=" + cle + ", indexFilsGauche=" + indexFilsGauche
				+ ", indexFilsDroit=" + indexFilsDroit + ", indexDoublon=" + indexDoublon + "]";
	}

	/**
	 * Recupere l'attribut "nom" (en version non-formate) du noeud dans le fichier
	 * Bin
	 *
	 * @param index Index du noeud
	 * @param raf   Le RandomAccessFile du fichier bin
	 * @return String nom
	 */
	public String recupereNomNoeud(int index, RandomAccessFile raf) {
		String nomFormate = recupereNomIndex(index, raf);
		return nomFormate.trim();
	}

	/**
	 * Recupere l'attribut "prenom" (en version formate) du noeud dans le fichier
	 * Bin
	 *
	 * @param index Index du noeud
	 * @param raf   Le RandomAccessFile du fichier bin
	 * @return String prenomFormate
	 */
	public String recuperePrenomFormateNoeud(int index, RandomAccessFile raf) {
		String prenomFormate = "";
		try {
			raf.seek((index * TAILLE_NOEUD_OCTET) + INDEX_PRENOM_OCTET);
			for (int k = 0; k < TAILLE_MAX_PRENOM; k++) {
				prenomFormate += raf.readChar();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prenomFormate;
	}

	/**
	 * Recupere l'attribut "prenom" (en version non-formate) du noeud dans le
	 * fichier Bin
	 *
	 * @param index Index du noeud
	 * @param raf   Le RandomAccessFile du fichier bin
	 * @return String prenom
	 */
	public String recuperePrenomNoeud(int index, RandomAccessFile raf) {
		String prenomFormate = recuperePrenomFormateNoeud(index, raf);
		return prenomFormate.trim();
	}

	/**
	 * Recupere l'attribut "departement" (en version formate) du noeud dans le
	 * fichier Bin
	 *
	 * @param index Index du noeud
	 * @param raf   Le RandomAccessFile du fichier bin
	 * @return String departementFormate
	 */
	public String recupereDepartementFormateNoeud(int index, RandomAccessFile raf) {
		String departementFormate = "";
		try {
			raf.seek((index * TAILLE_NOEUD_OCTET) + INDEX_DEPARTEMENT_OCTET);
			for (int k = 0; k < TAILLE_MAX_DEPARTEMENT; k++) {
				departementFormate += raf.readChar();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return departementFormate;
	}

	/**
	 * Recupere l'attribut "departement" (en version non-formate) du noeud dans le
	 * fichier Bin
	 *
	 * @param index Index du noeud
	 * @param raf   Le RandomAccessFile du fichier bin
	 * @return String departement
	 */
	public String recupereDepartementNoeud(int index, RandomAccessFile raf) {
		String departementFormate = recupereDepartementFormateNoeud(index, raf);
		return departementFormate.trim();
	}

	/**
	 * Recupere l'attribut "formation" (en version formate) du noeud dans le fichier
	 * Bin
	 *
	 * @param index Index du noeud
	 * @param raf   Le RandomAccessFile du fichier bin
	 * @return String formationFormate
	 */
	public String recupereFormationFormateNoeud(int index, RandomAccessFile raf) {
		String formationFormate = "";
		try {
			raf.seek((index * TAILLE_NOEUD_OCTET) + INDEX_FORMATION_OCTET);
			for (int k = 0; k < TAILLE_MAX_FORMATION; k++) {
				formationFormate += raf.readChar();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return formationFormate;
	}

	/**
	 * Recupere l'attribut "formation" (en version non-formate) du noeud dans le
	 * fichier Bin
	 *
	 * @param index Index du noeud
	 * @param raf   Le RandomAccessFile du fichier bin
	 * @return String formation
	 */
	public String recupereFormationNoeud(int index, RandomAccessFile raf) {
		String formationFormate = recupereFormationFormateNoeud(index, raf);
		return formationFormate.trim();
	}

	/**
	 * Recupere l'attribut "anneeFormation" (en version formate) du noeud dans le
	 * fichier Bin
	 *
	 * @param index Index du noeud
	 * @param raf   Le RandomAccessFile du fichier bin
	 * @return String anneeFormationFormate
	 */
	public String recupereAnneeFormationFormateNoeud(int index, RandomAccessFile raf) {
		String anneeFormationFormate = "";
		try {
			raf.seek((index * TAILLE_NOEUD_OCTET) + INDEX_ANNEEFORMATION_OCTET);
			for (int k = 0; k < TAILLE_MAX_ANNEEFORMATION; k++) {
				anneeFormationFormate += raf.readChar();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return anneeFormationFormate;
	}

	/**
	 * Recupere l'attribut "anneeFormation" (en version non-formate) du noeud dans
	 * le fichier Bin
	 *
	 * @param index Index du noeud
	 * @param raf   Le RandomAccessFile du fichier bin
	 * @return String anneeFormation
	 */
	public String recupereAnneeFormationNoeud(int index, RandomAccessFile raf) {
		String anneeFormationFormate = recupereAnneeFormationFormateNoeud(index, raf);
		return anneeFormationFormate.trim();
	}

	// Méthode qui crée la racine si le fichier est vide, et sinon ajoute le
	// stagiaire sur fichier bin
	public void ajouterStagiaireBinIndexInitial(Noeud noeudAjout, RandomAccessFile raf, int indexInitial) {
		try {
			int indexNoeudRacine = 0;
			if (raf.length() == 0) { // si le fichier binaire est vide, on écrit le stagiaire
				ecrireNoeudBin(noeudAjout, raf, indexInitial);
			} else {
				// raf.seek(TAILLE_NOEUD_OCTET);
				ecritureBinRecursiveIndexInitial(noeudAjout, raf, indexNoeudRacine, indexInitial);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Methode recursive d'ajout de stagiaire dans le fichier bin
	public void ecritureBinRecursiveIndexInitial(Noeud noeudAjout, RandomAccessFile raf, int index, int indexInitial) {
		try {
			int indexNoeud = (int) (raf.length() / TAILLE_NOEUD_OCTET);
			int indexParent = index;
			if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomIndex(indexParent, raf)) < 0) {
				// on part a gauche
				if (recupererIndexFilsGauche(indexParent, raf) != -1) {
					// il y a un fils gauche
					indexParent = recupererIndexFilsGauche(indexParent, raf);
					// on passe la methode au fils gauche
					ecritureBinRecursiveIndexInitial(noeudAjout, raf, indexParent, indexInitial);
				} else {
					// il n'y a pas de fils gauche
					ecrireFilsGauche(indexParent, raf, indexNoeud);
					ecrireNoeudBin(noeudAjout, raf, indexInitial);
				}
			} else if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomIndex(indexParent, raf)) > 0) {
				// on part a Droite
				if (recupererIndexFilsDroit(indexParent, raf) != -1) {
					// il y a un fils Droit
					indexParent = recupererIndexFilsDroit(indexParent, raf);
					// on passe la methode au fils Droit
					ecritureBinRecursiveIndexInitial(noeudAjout, raf, indexParent, indexInitial);
				} else {
					// il n'y a pas de fils Droit
					ecrireFilsDroit(indexParent, raf, indexNoeud);
					ecrireNoeudBin(noeudAjout, raf, indexInitial);
				}
			} else if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomIndex(indexParent, raf)) == 0) {
				if (recupererIndexDoublon(indexParent, raf) != -1) {
					indexParent = recupererIndexDoublon(indexParent, raf);
					ecritureBinRecursiveIndexInitial(noeudAjout, raf, indexParent, indexInitial);
				} else {
					ecrireDoublon(indexParent, raf, indexNoeud);
					ecrireNoeudBin(noeudAjout, raf, indexInitial);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Méthode qui crée la racine si le fichier est vide, et sinon ajoute le
	// stagiaire sur fichier bin
	public void ajouterStagiaireBin(Noeud noeudAjout, RandomAccessFile raf) {
		try {
			int indexNoeudRacine = 0;
			if (raf.length() == 0) { // si le fichier binaire est vide, on écrit le stagiaire
				ecrireNoeudBin(noeudAjout, raf, indexNoeudRacine);
			} else {
				raf.seek(TAILLE_NOEUD_OCTET);
				ecritureBinRecursive(noeudAjout, raf, indexNoeudRacine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Methode recursive d'ajout de stagiaire dans le fichier bin
	public void ecritureBinRecursive(Noeud noeudAjout, RandomAccessFile raf, int index) {
		try {
			int indexNoeud = (int) (raf.length() / TAILLE_NOEUD_OCTET);
			int indexParent = index;
			if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomIndex(indexParent, raf)) < 0) { // on part a
																											// gauche

				if (recupererIndexFilsGauche(indexParent, raf) != -1) { // il y a un fils gauche
					indexParent = recupererIndexFilsGauche(indexParent, raf);
					ecritureBinRecursive(noeudAjout, raf, indexParent); // on passe la methode au fils gauche
				} else { // il n'y a pas de fils gauche
					ecrireFilsGauche(indexParent, raf, indexNoeud);
					ecrireNoeudBin(noeudAjout, raf, indexNoeud);
				}
			} else if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomIndex(indexParent, raf)) > 0) { // on
																												// part
																												// à
																												// droite

				if (recupererIndexFilsDroit(indexParent, raf) != -1) { // il y a un fils Droit
					indexParent = recupererIndexFilsDroit(indexParent, raf);
					ecritureBinRecursive(noeudAjout, raf, indexParent); // on passe la methode au fils Droit
				} else { // il n'y a pas de fils Droit
					ecrireFilsDroit(indexParent, raf, indexNoeud);
					ecrireNoeudBin(noeudAjout, raf, indexNoeud);
				}
			} else if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomIndex(indexParent, raf)) == 0) {
				if (recupererIndexDoublon(indexParent, raf) != -1) {
					indexParent = recupererIndexDoublon(indexParent, raf);
					ecritureBinRecursive(noeudAjout, raf, indexParent);
				} else {
					ecrireDoublon(indexParent, raf, indexNoeud);
					ecrireNoeudBin(noeudAjout, raf, indexNoeud);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Methode pour écrire le noeud stagiaire dans le fichier binaire à la fin du
	// fichier binaire
	public void ecrireNoeudBin(Noeud noeudAjout, RandomAccessFile raf, int indexNoeud) {
		try {

			raf.seek(raf.length());
			raf.writeInt(indexNoeud);
			recuperationAttributsStagiaireBin(noeudAjout.getCle(), raf);
			raf.writeInt(noeudAjout.getIndexFilsGauche());
			raf.writeInt(noeudAjout.getIndexFilsDroit());
			raf.writeInt(noeudAjout.getIndexDoublon());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ecrireIndexNoeud(int indexParent, RandomAccessFile raf, int indexNoeud) {
		try {
			raf.seek((indexParent * TAILLE_NOEUD_OCTET));
			raf.writeInt(indexNoeud);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Methode qui modifie l'indexFilsGauche du noeud parent en lui attribuant la
	// valeur indexNoeud
	public void ecrireFilsGauche(int indexParent, RandomAccessFile raf, int indexNoeud) {
		try {
			raf.seek((indexParent * TAILLE_NOEUD_OCTET) + INDEX_FILS_GAUCHE_OCTET);
			raf.writeInt(indexNoeud);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Methode qui modifie l'indexFilsDroit du noeud parent en lui attribuant la
	// valeur indexNoeud
	public void ecrireFilsDroit(int indexParent, RandomAccessFile raf, int indexNoeud) {
		try {
			raf.seek((indexParent * TAILLE_NOEUD_OCTET) + INDEX_FILS_DROIT_OCTET);
			raf.writeInt(indexNoeud);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Methode qui modifie l'indexDoublon du noeud parent en lui attribuant la
	// valeur indexNoeud
	public void ecrireDoublon(int indexParent, RandomAccessFile raf, int indexNoeud) {
		try {
			raf.seek((indexParent * TAILLE_NOEUD_OCTET) + INDEX_DOUBLON_OCTET);
			raf.writeInt(indexNoeud);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Methode qui récupère l'index du noeud
	public int recupererIndexNoeud(int indexNoeud, RandomAccessFile raf) {
		int indexParent = 0;
		try {
			raf.seek((indexNoeud * TAILLE_NOEUD_OCTET));
			indexParent += raf.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return indexParent;
	}

	// Methode qui récupère l'indexFilsGauche du noeud parent
	public int recupererIndexFilsGauche(int indexNoeud, RandomAccessFile raf) {
		int indexParent = 0;
		try {
			raf.seek((indexNoeud * TAILLE_NOEUD_OCTET) + INDEX_FILS_GAUCHE_OCTET);
			indexParent += raf.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return indexParent;
	}

	// Methode qui récupère l'indexFilsDroit du noeud parent
	public int recupererIndexFilsDroit(int indexNoeud, RandomAccessFile raf) {
		int indexParent = 0;
		try {
			raf.seek((indexNoeud * TAILLE_NOEUD_OCTET) + INDEX_FILS_DROIT_OCTET);
			indexParent += raf.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return indexParent;
	}

	// Methode qui récupère l'indexDoublon du noeud parent
	public int recupererIndexDoublon(int indexNoeud, RandomAccessFile raf) {
		int indexParent = 0;
		try {
			raf.seek((indexNoeud * TAILLE_NOEUD_OCTET) + INDEX_DOUBLON_OCTET);
			indexParent += raf.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return indexParent;
	}

	// Methode qui récupère l'attribut "nom" du noeud parent
	public String recupereNomIndex(int indexParent, RandomAccessFile raf) {
		String nomRecup = "";
		try {
			raf.seek(indexParent * TAILLE_NOEUD_OCTET + INDEX_NOM_OCTET);
			for (int k = 0; k < TAILLE_MAX_NOM; k++) {
				nomRecup += raf.readChar();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nomRecup;
	}

	// methode qui ecrit les attributs du stagiaire dans le fichier binaire
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

	// Méthode pour supprimer un stagiaire
	public void supprimerStagiaireNoeud(int indexStagiaireSupp) {
		try {

			int indexRacine = 0;
			int indexSansFils = -1;

			RandomAccessFile raf = new RandomAccessFile(CHEMIN_BIN, "rw");

			Noeud noeudSupp = new Noeud();
			noeudSupp = noeudSupp.recupererNoeudSupp(indexStagiaireSupp, raf);
			System.out.println(noeudSupp);

			// Si c'est la racine
			if (noeudSupp.getIndexNoeud() == indexRacine) {
				supprimerStagiaireRacine(noeudSupp, raf);
			} else {
				// Si le noeud à un doublon
				if (noeudSupp.getIndexDoublon() != indexSansFils) {
					modifierNoeudAvecDoublon(noeudSupp, raf);
				} else {

					// Si le noeud à supprimer n'a pas de fils
					if (noeudSupp.getIndexFilsGauche() == indexSansFils
							&& noeudSupp.getIndexFilsDroit() == indexSansFils) {
						modifierNoeudParentSansFils(noeudSupp, raf);
						// Si le noeud à supprimer à 2 fils mm méthode que pour la racine
					} else if (noeudSupp.getIndexFilsGauche() != indexSansFils
							&& noeudSupp.getIndexFilsDroit() != indexSansFils) {
						supprimerStagiaireRacine(noeudSupp, raf);
						// Si le noeud à supprimer a un fils droit
					} else if (noeudSupp.getIndexFilsGauche() == indexSansFils) {
						modifierNoeudAvecFilsDroit(noeudSupp, raf);
						// Si le noeud à supprimer a un fils gauche
					} else if (noeudSupp.getIndexFilsDroit() == indexSansFils) {
						modifierNoeudAvecFilsGauche(noeudSupp, raf);
					}
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void supprimerStagiaireRacine(Noeud noeudSupp, RandomAccessFile raf) {
		int indexSansFils = -1;
		int indexNoeudRemplacant = 0;

		int indexNoeudSupp = noeudSupp.getIndexNoeud();
		int indexNoeudSuppRecursif = noeudSupp.getIndexNoeud();

		int indexFDNoeudSupp = noeudSupp.getIndexFilsDroit();

		Noeud noeudRecup = new Noeud();
		noeudRecup = noeudRecup.recupererNoeud(noeudRecup, indexFDNoeudSupp, raf);

		// chercher l'index du Noeud remplacant le noeud supp
		if (noeudRecup.getIndexFilsGauche() != indexSansFils) {
			indexNoeudSuppRecursif = chercherFilsPlusPetit(noeudRecup, raf, indexNoeudSuppRecursif);
		}
		indexNoeudRemplacant = getIndexNoeud();

		// Récupérer le noeud remplacant en noeud
		Noeud noeudRemplacant = new Noeud();
		noeudRemplacant = noeudRemplacant.recupererNoeud(noeudRemplacant, indexNoeudRemplacant, raf);

		// Récupérer le stagiaire du noeud supp
		Stagiaire stgRecup = new Stagiaire();
		stgRecup = lireNoeudStagiaire(indexNoeudRemplacant, raf);

		// Ecriture dans le fichier .Bin du stagiaire
		ecrireNoeudStagiaire(stgRecup, raf, indexNoeudSupp);

		// Supprission du stagiaire remplacant dans son ancienne place
		supprimerStagiaireNoeud(indexNoeudRemplacant);

	}

	// Méthode pour écrire stagiaire dans fichier .Bin
	private void ecrireNoeudStagiaire(Stagiaire stgRecup, RandomAccessFile raf, int indexNoeudSupp) {

		try {
			raf.seek((indexNoeudSupp * TAILLE_NOEUD_OCTET) + 4);

			raf.writeChars(stgRecup.getNomFormate());
			raf.writeChars(stgRecup.getPrenomFormate());
			raf.writeChars(stgRecup.getDepartementFormate());
			raf.writeChars(stgRecup.getFormationFormate());
			raf.writeChars(stgRecup.getAnneeFormationFormate());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Méthode pour lire le stagiaire (noeud Remplacant) qu'on va mettre à la place
	// du noeud supp
	private Stagiaire lireNoeudStagiaire(int indexNoeudRemplacant, RandomAccessFile raf) {

		Stagiaire stgRecup = new Stagiaire();

		try {
			raf.seek((indexNoeudRemplacant * TAILLE_NOEUD_OCTET));

			int indexNoeudSupp = 0;
			String nomRecup = "";
			String prenomRecup = "";
			String departementRecup = "";
			String formationRecup = "";
			String anneeFormationRecup = "";

			indexNoeudSupp = raf.readInt();

			for (int k = 0; k < TAILLE_MAX_NOM; k++) {
				nomRecup += raf.readChar();
			}

			for (int k = 0; k < TAILLE_MAX_PRENOM; k++) {
				prenomRecup += raf.readChar();
			}

			for (int k = 0; k < TAILLE_MAX_DEPARTEMENT; k++) {
				departementRecup += raf.readChar();
			}

			for (int k = 0; k < TAILLE_MAX_FORMATION; k++) {
				formationRecup += raf.readChar();
			}

			for (int k = 0; k < TAILLE_MAX_ANNEEFORMATION; k++) {
				anneeFormationRecup += raf.readChar();
			}

			// Création Stagiaire qu'on récupère
			stgRecup = new Stagiaire(nomRecup, prenomRecup, departementRecup, formationRecup, anneeFormationRecup);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stgRecup;
	}

	// Méthode pour supprimer un noeud qui a un doublon
	private void modifierNoeudAvecDoublon(Noeud noeudSupp, RandomAccessFile raf) {
		int indexNoeudSansFils = -1;

		int indexNoeudSupp = noeudSupp.getIndexNoeud();// 23
		int indexNoeudParent = 0;

		int indexFGnoeudSupp = noeudSupp.getIndexFilsGauche();// 58
		int indexFDnoeudSupp = noeudSupp.getIndexFilsDroit();// -1
		int indexDbNoeudSupp = noeudSupp.getIndexDoublon();// 28
		int indexNoeudRemplancant = indexDbNoeudSupp;

		// Chercher le parents
		indexNoeudParent = recupererIndexParent(indexNoeudSupp, raf, indexNoeudParent);// 21

		Noeud noeudParent = new Noeud();
		noeudParent = noeudParent.recupererNoeud(noeudParent, indexNoeudParent, raf);

		System.out.println("\nnoeud recup : " + noeudParent);

		indexNoeudParent = getIndexNoeud();

		System.out.println("\n" + indexNoeudSupp);
		System.out.println("\n" + indexNoeudParent);

		// Modifier le parent du noeud Supp
		if (noeudParent.getIndexFilsDroit() == indexNoeudSupp) {
			ecrireFilsDroit(indexNoeudParent, raf, indexDbNoeudSupp);
		} else if (noeudParent.getIndexFilsGauche() == indexNoeudSupp) {
			ecrireFilsGauche(indexNoeudParent, raf, indexDbNoeudSupp);
		}

		// Modifier le noeud remplacant le noeud Supp
		ecrireFilsGauche(indexNoeudRemplancant, raf, indexFGnoeudSupp);
		ecrireFilsDroit(indexNoeudRemplancant, raf, indexFDnoeudSupp);

		// Modifier noeud supp
		ecrireFilsGauche(indexNoeudSupp, raf, indexNoeudSansFils);
		ecrireFilsDroit(indexNoeudSupp, raf, indexNoeudSansFils);
		ecrireDoublon(indexNoeudSupp, raf, indexNoeudSansFils);

	}

	// Méthode pour Noeud supprimé qui a un fils gauche
	private void modifierNoeudAvecFilsGauche(Noeud noeudSupp, RandomAccessFile raf) {
		int indexNoeudSansFils = -1;

		int indexNoeudParentRemplacant = 0;
		int indexNoeudParent = 0;
		int indexNoeudRemplacant = 0;
		int indexFilsNoeudRemplacant = 0;

		int indexNoeudSupp = noeudSupp.getIndexNoeud();
		int indexNoeudSuppRecursif = noeudSupp.getIndexNoeud();

		// Récupérer l'index du noeud parent du noeud Supp
		indexNoeudParent = recupererIndexParent(indexNoeudSuppRecursif, raf, indexNoeudParent);

		int indexFGNoeudSupp = noeudSupp.getIndexFilsGauche();
		int indexFDNoeudSupp = noeudSupp.getIndexFilsDroit();

		Noeud noeudRecup = new Noeud();
		noeudRecup = noeudRecup.recupererNoeud(noeudRecup, indexFGNoeudSupp, raf);

		// chercher le fils du noeud supp
		if (noeudRecup.getIndexFilsDroit() != indexNoeudSansFils) {
			indexNoeudSuppRecursif = chercherFilsPlusGrand(noeudRecup, raf, indexNoeudSuppRecursif);
		}
		indexNoeudRemplacant = getIndexNoeud();

		// Récupérer le noeud remplacant
		Noeud noeudRemplacant = new Noeud();
		noeudRemplacant = noeudRemplacant.recupererNoeud(noeudRemplacant, indexNoeudRemplacant, raf);

		// Récupérer l'index du noeud parent du noeud Remplacant
		indexNoeudParentRemplacant = recupererIndexParent(indexNoeudRemplacant, raf, indexNoeudParentRemplacant);

		if (noeudRemplacant.getIndexFilsGauche() != indexNoeudSansFils) {
			indexFilsNoeudRemplacant = noeudRemplacant.getIndexFilsGauche();
		} else if (noeudRemplacant.getIndexFilsDroit() != indexNoeudSansFils) {
			indexFilsNoeudRemplacant = noeudRemplacant.getIndexFilsDroit();
		} else {
			indexFilsNoeudRemplacant = indexNoeudSansFils;
		}

		// Modifier le parent du noeud Supp
		ecrireFilsGauche(indexNoeudParent, raf, indexNoeudRemplacant);

		// Modifier le noeud remplacant le noeud Supp
		ecrireFilsGauche(indexNoeudRemplacant, raf, indexFGNoeudSupp);
		ecrireFilsDroit(indexNoeudRemplacant, raf, indexFDNoeudSupp);

		// Modifier le parent du noeud remplacant pour pas perdre le fils du noeud
		// remplacant
		ecrireFilsDroit(indexNoeudParentRemplacant, raf, indexFilsNoeudRemplacant);

		// Modifier noeud supp
		ecrireFilsGauche(indexNoeudSupp, raf, indexNoeudSansFils);
		ecrireFilsDroit(indexNoeudSupp, raf, indexNoeudSansFils);
		ecrireDoublon(indexNoeudSupp, raf, indexNoeudSansFils);

	}

	// Méthode pour chercher le plus grand dans mon arbre gauche
	private int chercherFilsPlusGrand(Noeud noeudFils, RandomAccessFile raf, int indexNoeudNouveauFils) {
		int indexNoeudSansFils = -1;

		int indexFDNoeudSupp = noeudFils.getIndexFilsDroit();

		Noeud noeudRecup = new Noeud();
		noeudRecup = noeudRecup.recupererNoeud(noeudRecup, indexFDNoeudSupp, raf);
		System.out.println(noeudRecup);

		// Si pas de fils gauche
		if (noeudRecup.getIndexFilsDroit() != indexNoeudSansFils) {
			chercherFilsPlusGrand(noeudRecup, raf, indexNoeudNouveauFils);
		}
		indexNoeudNouveauFils = noeudRecup.getIndexNoeud();

		return indexNoeudNouveauFils;

	}

	// Méthode pour Noeud supprimé qui a un fils droit
	private void modifierNoeudAvecFilsDroit(Noeud noeudSupp, RandomAccessFile raf) {
		int indexNoeudSansFils = -1;

		int indexNoeudParentRemplacant = 0;
		int indexNoeudParent = 0;
		int indexNoeudRemplacant = 0;
		int indexFilsNoeudRemplacant = 0;

		int indexNoeudSupp = noeudSupp.getIndexNoeud();
		int indexNoeudSuppRecursif = noeudSupp.getIndexNoeud();
		// Récupérer l'index du noeud parent du noeud Supp
		indexNoeudParent = recupererIndexParent(indexNoeudSuppRecursif, raf, indexNoeudParent);

		int indexFGNoeudSupp = noeudSupp.getIndexFilsGauche();
		int indexFDNoeudSupp = noeudSupp.getIndexFilsDroit();

		Noeud noeudRecup = new Noeud();
		noeudRecup = noeudRecup.recupererNoeud(noeudRecup, indexFDNoeudSupp, raf);

		// chercher le fils du noeud supp
		if (noeudRecup.getIndexFilsGauche() != indexNoeudSansFils) {
			indexNoeudSuppRecursif = chercherFilsPlusPetit(noeudRecup, raf, indexNoeudSuppRecursif);
		}
		indexNoeudRemplacant = indexNoeudSuppRecursif;

		// Récupérer le noeud remplacant
		Noeud noeudRemplacant = new Noeud();
		noeudRemplacant = noeudRemplacant.recupererNoeud(noeudRemplacant, indexNoeudRemplacant, raf);

		// Récupérer l'index du noeud parent du noeud Remplacant
		indexNoeudParentRemplacant = recupererIndexParent(indexNoeudRemplacant, raf, indexNoeudParentRemplacant);

		if (noeudRemplacant.getIndexFilsGauche() != indexNoeudSansFils) {
			indexFilsNoeudRemplacant = noeudRemplacant.getIndexFilsGauche();
		} else if (noeudRemplacant.getIndexFilsDroit() != indexNoeudSansFils) {
			indexFilsNoeudRemplacant = noeudRemplacant.getIndexFilsDroit();
		} else {
			indexFilsNoeudRemplacant = indexNoeudSansFils;
		}

		// Modifier le parent du noeud Supp
		ecrireFilsDroit(indexNoeudParent, raf, indexNoeudRemplacant);

		// Modifier le noeud remplacant le noeud Supp
		ecrireFilsGauche(indexNoeudRemplacant, raf, indexFGNoeudSupp);
		ecrireFilsDroit(indexNoeudRemplacant, raf, indexFDNoeudSupp);

		// Modifier le parent du noeud remplacant pour pas perdre le fils du noeud
		// remplacant
		ecrireFilsGauche(indexNoeudParentRemplacant, raf, indexFilsNoeudRemplacant);

		// Modifier noeud supp
		ecrireFilsGauche(indexNoeudSupp, raf, indexNoeudSansFils);
		ecrireFilsDroit(indexNoeudSupp, raf, indexNoeudSansFils);
		ecrireDoublon(indexNoeudSupp, raf, indexNoeudSansFils);

	}

	// Méthode pour récupérer l'index du noeud Parent du noeud supp
	private int recupererIndexParent(int indexNoeudFils, RandomAccessFile raf, int indexNoeudParent) {
		indexNoeud = 0;

		while ((indexNoeudFils != recupererIndexFilsGauche(indexNoeud, raf)
				&& (indexNoeudFils != recupererIndexFilsDroit(indexNoeud, raf))
				&& (indexNoeudFils != recupererIndexDoublon(indexNoeud, raf)))) {
			indexNoeud++;
		}
		return indexNoeudParent = indexNoeud;

	}

	// Méthode pour chercher le plus grand dans mon arbre gauche
	private int chercherFilsPlusPetit(Noeud noeudFils, RandomAccessFile raf, int indexNoeudNouveauFils) {
		int indexNoeudSansFils = -1;
		indexNoeud = 0;

		int indexFilsNoeud = noeudFils.getIndexFilsGauche();

		while (indexFilsNoeud != recupererIndexNoeud(indexNoeud, raf)) {
			indexNoeud++;
		}

		Noeud noeudRecup = new Noeud();
		noeudRecup = noeudRecup.recupererNoeud(noeudRecup, indexNoeud, raf);
		System.out.println(noeudRecup);

		// Si pas de fils gauche
		if (noeudRecup.getIndexFilsGauche() != indexNoeudSansFils) {
			chercherFilsPlusPetit(noeudRecup, raf, indexNoeudNouveauFils);
		}
		indexNoeudNouveauFils = noeudRecup.getIndexNoeud();

		return indexNoeudNouveauFils;

	}

	// Méthode pour récupérer un noeud
	private Noeud recupererNoeud(Noeud noeudRecup, int indexNoeud, RandomAccessFile raf) {

		try {
			raf.seek(indexNoeud * TAILLE_NOEUD_OCTET);

			int indexNoeudSupp = 0;
			String nomRecup = "";
			String prenomRecup = "";
			String departementRecup = "";
			String formationRecup = "";
			String anneeFormationRecup = "";
			int indexFilsGauche = 0;
			int indexFilsDroit = 0;
			int indexDoublon = 0;

			indexNoeudSupp = raf.readInt();

			for (int k = 0; k < TAILLE_MAX_NOM; k++) {
				nomRecup += raf.readChar();
			}

			for (int k = 0; k < TAILLE_MAX_PRENOM; k++) {
				prenomRecup += raf.readChar();
			}

			for (int k = 0; k < TAILLE_MAX_DEPARTEMENT; k++) {
				departementRecup += raf.readChar();
			}

			for (int k = 0; k < TAILLE_MAX_FORMATION; k++) {
				formationRecup += raf.readChar();
			}

			for (int k = 0; k < TAILLE_MAX_ANNEEFORMATION; k++) {
				anneeFormationRecup += raf.readChar();
			}

			indexFilsGauche += raf.readInt();
			indexFilsDroit += raf.readInt();
			indexDoublon += raf.readInt();

			// Création noeud
			noeudRecup = new Noeud(indexNoeudSupp, null, indexFilsGauche, indexFilsDroit, indexDoublon);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return noeudRecup;
	}

	// Méthode pour supprimer un noeud qui n'a pas de fils
	private void modifierNoeudParentSansFils(Noeud noeudSupp, RandomAccessFile raf) {
		int indexNoeudSansFils = -1;

		// Chercher le noeud parent du noeud à supprimer
		int indexNoeudSupp = noeudSupp.getIndexNoeud();
		int indexFGNoeudSupp = noeudSupp.getIndexFilsGauche();
		int indexFDNoeudSupp = noeudSupp.getIndexFilsDroit();
		int indexDbNoeudSupp = noeudSupp.getIndexDoublon();

		int indexParent = 0;

		while ((indexNoeudSupp != recupererIndexFilsGauche(indexParent, raf)
				&& (indexNoeudSupp != recupererIndexFilsDroit(indexParent, raf))
				&& (indexNoeudSupp != recupererIndexDoublon(indexParent, raf)))) {
			indexParent++;
		}

		// Modif des index fils du parent
		if (indexNoeudSupp == recupererIndexFilsGauche(indexParent, raf)) {
			ecrireFilsGauche(indexParent, raf, indexNoeudSansFils);
		} else if (indexNoeudSupp == recupererIndexFilsDroit(indexParent, raf)) {
			ecrireFilsDroit(indexParent, raf, indexNoeudSansFils);
		} else {
			ecrireDoublon(indexParent, raf, indexNoeudSansFils);
		}

		// Modif des index fils du noeud Supp
		ecrireFilsGauche(indexNoeudSupp, raf, indexNoeudSansFils);
		ecrireFilsDroit(indexNoeudSupp, raf, indexNoeudSansFils);
		ecrireDoublon(indexNoeudSupp, raf, indexNoeudSansFils);

	}

	// Méthode pour chercher le noeud qui à le nom à supprimer et recupérer ce noeud
	private Noeud recupererNoeudSupp(int indexStagiaireSupp, RandomAccessFile raf) {

		Noeud noeudSupp = new Noeud();

		// Création noeud avec lecture .Bin
		try {
			raf.seek(indexStagiaireSupp * TAILLE_NOEUD_OCTET);

			int indexNoeudSupp = 0;
			String nomRecupSupp = "";
			String prenomRecupSupp = "";
			String departementRecupSupp = "";
			String formationRecupSupp = "";
			String anneeFormationRecupSupp = "";
			int indexFilsGaucheSupp = 0;
			int indexFilsDroitSupp = 0;
			int indexDoublonSupp = 0;

			indexNoeudSupp = raf.readInt();

			for (int k = 0; k < TAILLE_MAX_NOM; k++) {
				nomRecupSupp += raf.readChar();
			}

			for (int k = 0; k < TAILLE_MAX_PRENOM; k++) {
				prenomRecupSupp += raf.readChar();
			}

			for (int k = 0; k < TAILLE_MAX_DEPARTEMENT; k++) {
				departementRecupSupp += raf.readChar();
			}

			for (int k = 0; k < TAILLE_MAX_FORMATION; k++) {
				formationRecupSupp += raf.readChar();
			}

			for (int k = 0; k < TAILLE_MAX_ANNEEFORMATION; k++) {
				anneeFormationRecupSupp += raf.readChar();
			}

			indexFilsGaucheSupp += raf.readInt();
			indexFilsDroitSupp += raf.readInt();
			indexDoublonSupp += raf.readInt();

			// Création noeud
			noeudSupp = new Noeud(indexNoeudSupp, null, indexFilsGaucheSupp, indexFilsDroitSupp, indexDoublonSupp);

			// POUR VERIF DE RECUPERATION DU BON NOEUD

//			System.out.println("IndexRecupSupp = " + indexNoeudSupp);
//			System.out.println("nomRecupSupp = " + nomRecupSupp.trim());
//			System.out.println("prenomRecupSupp = " + prenomRecupSupp.trim());
//			System.out.println("departementRecupSupp = " + departementRecupSupp.trim());
//			System.out.println("formationRecupSupp = " + formationRecupSupp.trim());
//			System.out.println("anneeFormationRecupSupp = " + anneeFormationRecupSupp.trim());
//			System.out.println("indexFilsGauchSupp = " + indexFilsGaucheSupp);
//			System.out.println("indexFilsDroitSupp = " + indexFilsDroitSupp);
//			System.out.println("doublonSupp = " + indexDoublonSupp + "\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return noeudSupp;
	}

}
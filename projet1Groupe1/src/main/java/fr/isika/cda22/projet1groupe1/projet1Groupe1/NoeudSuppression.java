package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class NoeudSuppression implements ParametreGestionnaire {

	// Attributs
	private int indexNoeud = 0;
	private Stagiaire cle;
	private int indexFilsGauche = -1;
	private int indexFilsDroit = -1;
	private int indexDoublon = -1;

	// Constructeur
	public NoeudSuppression(int indexNoeud, Stagiaire cle, int indexFilsGauche, int indexFilsDroit, int indexDoublon) {
		super();
		this.indexNoeud = indexNoeud;
		this.cle = cle;
		this.indexFilsGauche = indexFilsGauche;
		this.indexFilsDroit = indexFilsDroit;
		this.indexDoublon = indexDoublon;
	}

	// Constructeur vide
	public NoeudSuppression() {

	}

	// Constructeur
	public NoeudSuppression(Stagiaire cle) {
		super();
		this.indexNoeud = -1;
		this.cle = cle;
		this.indexFilsGauche = -1;
		this.indexFilsDroit = -1;
		this.indexDoublon = -1;
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

	public int getIndexNoeud() {
		return indexNoeud;
	}

	public void setIndexNoeud(int indexNoeud) {
		this.indexNoeud = indexNoeud;
	}

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
	public void supprimerStagiaireNoeud(String stagiaireSupp) {
		try {

			RandomAccessFile raf = new RandomAccessFile(CHEMIN_BIN, "rw");
			indexNoeud = 0;
			
			Noeud noeudSupp = new Noeud();
			noeudSupp = this.recupererNoeudSupp(noeudSupp, stagiaireSupp, indexNoeud, raf);
			System.out.println(noeudSupp);
			
			
			
			//Si c'est la racine
			if(noeudSupp.getIndexNoeud()==indexNoeud) {
				supprimerRacine(noeudSupp, raf);
			}else {
				//Si le noeud à un doublon
				if(noeudSupp.getIndexDoublon()!=-1) {
					modifierNoeudAvecDoublon(noeudSupp, raf);
				}else {
					
			//Si le noeud à supprimer n'a pas de fils 
			if(noeudSupp.getIndexFilsGauche()==-1 && noeudSupp.getIndexFilsDroit()==-1) {
				modifierNoeudParentSansFils(noeudSupp, raf);
			//Si le noeud à supprimer à 2 fils	
			}else if (noeudSupp.getIndexFilsGauche()!=-1 && noeudSupp.getIndexFilsDroit()!=-1) {
				modifierNoeudParentAvecFilsDroit(noeudSupp, raf);
			//Si le noeud à supprimer a un fils droit
			}else if (noeudSupp.getIndexFilsGauche()==-1) {
				modifierNoeudParentAvecFilsDroit(noeudSupp, raf);
			//Si le noeud à supprimer a un fils gauche
			}else if (noeudSupp.getIndexFilsDroit()==-1) {
				modifierNoeudParentAvecFilsGauche(noeudSupp, raf);
			
			}
				}	
			}
		

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private void modifierNoeudAvecDoublon(Noeud noeudFils, RandomAccessFile raf) {
		int indexNoeudSansFils = -1;
		int indexNoeudFils = noeudFils.getIndexNoeud();
		int indexNoeudParent = 0;

		int indexDbNoeudSupp = noeudFils.getIndexDoublon();
		int indexFDnoeudSupp = noeudFils.getIndexFilsDroit();
		int indexFGnoeudSupp = noeudFils.getIndexFilsGauche();

		// Chercher le parents
		indexNoeudParent = recupererIndexParent(indexNoeudFils, raf, indexNoeudParent);

		indexNoeud = 0;

		int indexFilsNoeud = noeudFils.getIndexDoublon();

		while (indexFilsNoeud != recupererIndexNoeud(indexNoeud, raf)) {
			indexNoeud++;
		}

		Noeud noeudRecup = new Noeud();
		noeudRecup = this.recupererNoeud(noeudRecup, indexNoeud, raf);

		System.out.println(noeudRecup);

		indexNoeudFils = getIndexNoeud();

		System.out.println(indexNoeudFils);
		System.out.println(indexNoeudParent);
		// Modifier le parent
		ecrireFilsGauche(indexNoeudParent, raf, indexNoeudFils);// écrire Doublon au parent du noeud supprimé
		ecrireFilsDroit(indexNoeudFils, raf, indexFDnoeudSupp);// écrire FD du noeud supprimé au noeud qui prend sa place, ie au doublon											
		ecrireFilsGauche(indexNoeudFils, raf, indexFGnoeudSupp);// écrire FG du noeud supprimé au noeud qui prend sa place, ie au doublon

	}

	private void supprimerRacine(Noeud noeudFils, RandomAccessFile raf) {
		int indexNoeudSansFils = -1;
		int indexNoeudFils = noeudFils.getIndexNoeud();

		int indexFDnoeudSupp = noeudFils.getIndexFilsDroit();
		int indexFGnoeudSupp = noeudFils.getIndexFilsGauche();

		indexNoeud = 0;

		int indexFilsNoeud = noeudFils.getIndexFilsDroit();

		while (indexFilsNoeud != recupererIndexNoeud(indexNoeud, raf)) {
			indexNoeud++;
		}

		Noeud noeudRecup = new Noeud();
		noeudRecup = this.recupererNoeud(noeudRecup, indexNoeud, raf);

		System.out.println(noeudRecup);

		// chercher le fils du noeud supp
		if (noeudRecup.getIndexFilsGauche() != indexNoeudSansFils) {
			indexNoeudFils = chercherFilsPlusPetit(noeudRecup, raf, indexNoeudFils);
		}
		indexNoeudFils = getIndexNoeud();

		System.out.println(indexNoeudFils);
		// Modifier le parent
		ecrireFilsDroit(indexNoeudFils, raf, indexFDnoeudSupp);// écrire FD du noeud supprimé au noeud qui prend sa
																// place
		ecrireFilsGauche(indexNoeudFils, raf, indexFGnoeudSupp);// écrire FG du noeud supprimé au noeud qui prend sa
																// place

	}

	private void modifierNoeudParentAvecFilsGauche(Noeud noeudFils, RandomAccessFile raf) {
		int indexNoeudSansFils = -1;
		int indexNoeudFils = noeudFils.getIndexNoeud();
		int indexNoeudParent = 0;

		int indexFDnoeudSupp = noeudFils.getIndexFilsDroit();
		int indexFGnoeudSupp = noeudFils.getIndexFilsGauche();

		// Chercher le parents
		indexNoeudParent = recupererIndexParent(indexNoeudFils, raf, indexNoeudParent);

		indexNoeud = 0;

		int indexFilsNoeud = noeudFils.getIndexFilsGauche();

		while (indexFilsNoeud != recupererIndexNoeud(indexNoeud, raf)) {
			indexNoeud++;
		}

		Noeud noeudRecup = new Noeud();
		noeudRecup = this.recupererNoeud(noeudRecup, indexNoeud, raf);

		System.out.println(noeudRecup);

		// chercher le fils du noeud supp
		if (noeudRecup.getIndexFilsDroit() != indexNoeudSansFils) {
			indexNoeudFils = chercherFilsPlusGrand(noeudRecup, raf, indexNoeudFils);
		}
		indexNoeudFils = getIndexNoeud();

		System.out.println(indexNoeudFils);
		System.out.println(indexNoeudParent);
		// Modifier le parent
		ecrireFilsGauche(indexNoeudParent, raf, indexNoeudFils);// écrire FG au parent du noeud supprimé
		ecrireFilsDroit(indexNoeudFils, raf, indexFDnoeudSupp);// écrire FD du noeud supprimé au noeud qui prend sa
																// place
		ecrireFilsGauche(indexNoeudFils, raf, indexFGnoeudSupp);// écrire FG du noeud supprimé au noeud qui prend sa
																// place

	}

	private int chercherFilsPlusGrand(Noeud noeudFils, RandomAccessFile raf, int indexNoeudNouveauFils) {
		int indexNoeudSansFils = -1;
		indexNoeud = 0;

		int indexFilsNoeud = noeudFils.getIndexFilsDroit();

		while (indexFilsNoeud != recupererIndexNoeud(indexNoeud, raf)) {
			indexNoeud++;
		}

		Noeud noeudRecup = new Noeud();
		noeudRecup = this.recupererNoeud(noeudRecup, indexNoeud, raf);
		System.out.println(noeudRecup);

		// Si pas de fils gauche
		if (noeudRecup.getIndexFilsDroit() != indexNoeudSansFils) {
			chercherFilsPlusGrand(noeudRecup, raf, indexNoeudNouveauFils);
		}
		indexNoeudNouveauFils = noeudRecup.getIndexNoeud();

		return indexNoeudNouveauFils;

	}

	// Méthode pour donner au Noeud Parents l'index du fils le plus petit à la place
	// de l'index du noeud supp
	private void modifierNoeudParentAvecFilsDroit(Noeud noeudFils, RandomAccessFile raf) {
		int indexNoeudSansFils = -1;
		int indexNoeudFils = noeudFils.getIndexNoeud();
		int indexNoeudParent = 0;

		int indexFDnoeudSupp = noeudFils.getIndexFilsDroit();
		int indexFGnoeudSupp = noeudFils.getIndexFilsGauche();

		// Chercher le parents
		indexNoeudParent = recupererIndexParent(indexNoeudFils, raf, indexNoeudParent);

		indexNoeud = 0;

		int indexFilsNoeud = noeudFils.getIndexFilsDroit();

		while (indexFilsNoeud != recupererIndexNoeud(indexNoeud, raf)) {
			indexNoeud++;
		}

		Noeud noeudRecup = new Noeud();
		noeudRecup = this.recupererNoeud(noeudRecup, indexNoeud, raf);

		System.out.println(noeudRecup);

		// chercher le fils du noeud supp
		if (noeudRecup.getIndexFilsGauche() != indexNoeudSansFils) {
			indexNoeudFils = chercherFilsPlusPetit(noeudRecup, raf, indexNoeudFils);
		}
		indexNoeudFils = getIndexNoeud();

		System.out.println(indexNoeudFils);
		System.out.println(indexNoeudParent);
		// Modifier le parent
		ecrireFilsDroit(indexNoeudParent, raf, indexNoeudFils);// écrire FD au parent du noeud supprimé
		ecrireFilsDroit(indexNoeudFils, raf, indexFDnoeudSupp);// écrire FD du noeud supprimé au noeud qui prend sa
																// place
		ecrireFilsGauche(indexNoeudFils, raf, indexFGnoeudSupp);// écrire FG du noeud supprimé au noeud qui prend sa
																// place

	}

	private int recupererIndexParent(int indexNoeudFils, RandomAccessFile raf, int indexNoeudParent) {
		indexNoeud = 0;
		int indexNoeudSansFils = -1;

		while ((indexNoeudFils != recupererIndexFilsGauche(indexNoeud, raf)
				&& (indexNoeudFils != recupererIndexFilsDroit(indexNoeud, raf))
				&& (indexNoeudFils != recupererIndexDoublon(indexNoeud, raf)))) {
			indexNoeud++;
		}

		return indexNoeudParent = indexNoeud;

	}

	private int chercherFilsPlusPetit(Noeud noeudFils, RandomAccessFile raf, int indexNoeudNouveauFils) {
		int indexNoeudSansFils = -1;
		indexNoeud = 0;

		int indexFilsNoeud = noeudFils.getIndexFilsGauche();

		while (indexFilsNoeud != recupererIndexNoeud(indexNoeud, raf)) {
			indexNoeud++;
		}

		Noeud noeudRecup = new Noeud();
		noeudRecup = this.recupererNoeud(noeudRecup, indexNoeud, raf);
		System.out.println(noeudRecup);

		// Si pas de fils gauche
		if (noeudRecup.getIndexFilsGauche() != indexNoeudSansFils) {
			chercherFilsPlusPetit(noeudRecup, raf, indexNoeudNouveauFils);
		}
		indexNoeudNouveauFils = noeudRecup.getIndexNoeud();

		return indexNoeudNouveauFils;

	}

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

	private void modifierNoeudParentSansFils(Noeud noeudFils, RandomAccessFile raf) {

		int indexNoeudSansFils = -1;

		// Chercher le noeud parent du noeud à supprimer
		int indexNoeudFils = noeudFils.getIndexNoeud();

		indexNoeud = 0;

		while ((indexNoeudFils != recupererIndexFilsGauche(indexNoeud, raf)
				&& (indexNoeudFils != recupererIndexFilsDroit(indexNoeud, raf))
				&& (indexNoeudFils != recupererIndexDoublon(indexNoeud, raf)))) {
			indexNoeud++;
		}
		if (indexNoeudFils == recupererIndexFilsGauche(indexNoeud, raf)) {
			ecrireFilsGauche(indexNoeud, raf, indexNoeudSansFils);
		} else if (indexNoeudFils == recupererIndexFilsDroit(indexNoeud, raf)) {
			ecrireFilsDroit(indexNoeud, raf, indexNoeudSansFils);
		} else {
			ecrireDoublon(indexNoeud, raf, indexNoeudSansFils);
		}

		System.out.println(indexNoeud);

	}

	// Méthode pour chercher le noeud qui à le nom à supprimer et recupérer ce noeud
	private Noeud recupererNoeudSupp(Noeud noeudSupp, String stagiaireSupp, int indexNoeud, RandomAccessFile raf) {

		// rehcerhce du noeud à supprimer
		while (stagiaireSupp.compareTo(recupereNomIndex(indexNoeud, raf).trim()) != 0) {
			indexNoeud++;
		}

		// Création noeud avec lecture .Bin
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
			noeudSupp = new Noeud(indexNoeudSupp, null, indexFilsGauche, indexFilsDroit, indexDoublon);

			// POUR VERIF DE RECUPERATION DU BON NOEUD

			System.out.println("IndexRecup = " + indexNoeudSupp);
			System.out.println("nomRecup = " + nomRecup.trim());
			System.out.println("prenomRecup = " + prenomRecup.trim());
			System.out.println("departementRecup = " + departementRecup.trim());
			System.out.println("formationRecup = " + formationRecup.trim());
			System.out.println("anneeFormationRecup = " + anneeFormationRecup.trim());
			System.out.println("indexFilsGauche = " + indexFilsGauche);
			System.out.println("indexFilsDroit = " + indexFilsDroit);
			System.out.println("doublon = " + indexDoublon + "\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return noeudSupp;
	}

}

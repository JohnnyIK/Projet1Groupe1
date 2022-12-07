package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class Noeud implements ParametreGestionnaire {

	// Attributs
	private int indexNoeud = 0;
	private Stagiaire cle;
	private int indexFilsGauche = -1;
	private int indexFilsDroit = -1;
	private int indexDoublon = -1;

	// Constructeur
	public Noeud(int indexNoeud, Stagiaire cle, int indexFilsGauche, int indexFilsDroit, int indexDoublon) {
		super();
		this.indexNoeud = indexNoeud;
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
	
	public int getIndexNoeud() {
		return indexNoeud;
	}

	public void setIndexNoeud(int indexNoeud) {
		this.indexNoeud = indexNoeud;
	}

	// Méthode qui crée la racine si le fichier est vide, et sinon ajoute le stagiaire sur fichier bin
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
			if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomIndex(indexParent, raf)) < 0) { // on part a gauche

				if (recupererIndexFilsGauche(indexParent, raf) != -1) { // il y a un fils gauche
					indexParent = recupererIndexFilsGauche(indexParent, raf);
					ecritureBinRecursive(noeudAjout, raf, indexParent); // on passe la methode au fils gauche
				} else { // il n'y a pas de fils gauche
					ecrireFilsGauche(indexParent, raf, indexNoeud);
					ecrireNoeudBin(noeudAjout, raf, indexNoeud);
				}
			} else if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomIndex(indexParent, raf)) > 0) { // on part a Droite

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
	
	
	
	// Methode pour écrire le noeud stagiaire dans le fichier binaire à la fin du fichier binaire
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
	

	// Methode qui modifie l'indexFilsGauche du noeud parent en lui attribuant la valeur indexNoeud
	public void ecrireFilsGauche(int indexParent, RandomAccessFile raf, int indexNoeud) {
		try {
			raf.seek((indexParent*TAILLE_NOEUD_OCTET) + INDEX_FILS_GAUCHE_OCTET);
			raf.writeInt(indexNoeud);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Methode qui modifie l'indexFilsDroit du noeud parent en lui attribuant la valeur indexNoeud
	public void ecrireFilsDroit(int indexParent, RandomAccessFile raf, int indexNoeud) {
		try {
			raf.seek((indexParent*TAILLE_NOEUD_OCTET) + INDEX_FILS_DROIT_OCTET);
			raf.writeInt(indexNoeud);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Methode qui modifie l'indexDoublon du noeud parent en lui attribuant la valeur indexNoeud
	public void ecrireDoublon(int indexParent, RandomAccessFile raf, int indexNoeud) {
		try {
			raf.seek((indexParent*TAILLE_NOEUD_OCTET) + INDEX_DOUBLON_OCTET);
			raf.writeInt(indexNoeud);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Methode qui récupère l'indexFilsGauche du noeud parent
	public int recupererIndexFilsGauche(int indexNoeud, RandomAccessFile raf) {
		int indexParent = 0;
		try {
			raf.seek((indexNoeud* TAILLE_NOEUD_OCTET)+INDEX_FILS_GAUCHE_OCTET);
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
			raf.seek((indexNoeud* TAILLE_NOEUD_OCTET)+INDEX_FILS_DROIT_OCTET);
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
			raf.seek((indexNoeud* TAILLE_NOEUD_OCTET)+INDEX_DOUBLON_OCTET);
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
			raf.seek(indexParent*TAILLE_NOEUD_OCTET + INDEX_NOM_OCTET);
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
	
	
	
	// ******** AJOUT 20221207 *******************************************************************************
	/**
	 * Recupere l'attribut "nom" (en version non-formate) du noeud dans le fichier Bin
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
	 * Recupere l'attribut "prenom" (en version formate) du noeud dans le fichier Bin
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
	 * Recupere l'attribut "formation" (en version formate) du noeud dans le
	 * fichier Bin
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
	
	// Constructeur
		public Noeud(Stagiaire cle) {
			super();
			this.indexNoeud = -1;
			this.cle = cle;
			this.indexFilsGauche = -1;
			this.indexFilsDroit = -1;
			this.indexDoublon = -1;
		}
		
		
	
	
	

}

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
			raf.seek(indexParent*TAILLE_NOEUD_OCTET);
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

}

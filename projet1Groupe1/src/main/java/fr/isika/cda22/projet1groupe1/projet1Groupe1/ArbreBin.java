package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArbreBin implements ParametreGestionnaire{
	
	// Attributs
	public String adresseFichierBin = "";
	public RandomAccessFile raf;

	
	// Constructeur
	public ArbreBin(String adresseFichierBin) {
		super();
		this.adresseFichierBin = adresseFichierBin;
	}
	
	
	// Creation d'un "nomLong" de TAILLE_MAX_NOM pour l'écriture dans le fichier binaire
	public String getNomFormate(String nom) {
		// Dans tous les cas, on ne va pas tronquer le nom / prenom car sinon on perd
		// l'information.
		// Donc on rajoute " " après chaque nom/prenom pour atteindre une string nomLong
		// de taille TAILLE_MAX_NOMPRENOM
		String nomFormate = nom;
		for (int i = nom.length(); i < TAILLE_MAX_NOM; i++) {
			nomFormate += " ";
		}
		return nomFormate;
	}
	
	// Creation d'un "prenomLong" de TAILLE_MAX_PRENOM pour l'écriture dans le
	// fichier binaire
	public String getPrenomFormate(String prenom) {
		// Dans tous les cas, on ne va pas tronquer le nom / prenom car sinon on perd
		// l'information.
		// Donc on rajoute " " après chaque nom/prenom pour atteindre une string nomLong
		// de taille TAILLE_MAX_NOMPRENOM
		String prenomLong = prenom;
		for (int i = prenom.length(); i < TAILLE_MAX_PRENOM; i++) {
			prenomLong += " ";
		}
		return prenomLong;
	}

	// Creation d'un "formationLong" de TAILLE_MAX_PRENOM pour l'écriture dans le
	// fichier binaire
	public String getFormationFormate(String formation) {
		// Dans tous les cas, on ne va pas tronquer le nom de la formation car sinon on
		// perd
		// l'information.
		// Donc on rajoute " " après chaque nom de formation pour atteindre une string
		// formationLong
		// de taille TAILLE_MAX_FORMATION
		String formationLong = formation;
		for (int i = formation.length(); i < TAILLE_MAX_FORMATION; i++) {
			formationLong += " ";
		}
		return formationLong;
	}

	// Creation d'un "departementLong" de TAILLE_MAX_DEPARTEMENT pour l'écriture
	// dans le fichier binaire
	public String getDepartementFormate(String departement) {
		// Dans tous les cas, on ne va pas tronquer le departement car sinon on perd
		// l'information.
		// Donc on rajoute " " après chaque departement pour atteindre une string
		// departementLong
		// de taille TAILLE_MAX_DEPARTEMENT
		String departementLong = departement;
		for (int i = departement.length(); i < TAILLE_MAX_DEPARTEMENT; i++) {
			departementLong += " ";
		}
		return departementLong;
	}

	// Creation d'un "anneeLong" de TAILLE_MAX_ANNEEFORMATION pour l'écriture dans
	// le fichier binaire
	public String getAnneeFormationFormate(String anneeFormation) {
		// Dans tous les cas, on ne va pas tronquer l'annee car sinon on perd
		// l'information.
		// Donc on rajoute " " après chaque annee pour atteindre une string
		// anneeLong
		// de taille TAILLE_MAX_ANNEEFORMATION
		String anneeLong = anneeFormation;
		for (int i = anneeFormation.length(); i < TAILLE_MAX_ANNEEFORMATION; i++) {
			anneeLong += " ";
		}
		return anneeLong;
	}
	
	public int indexToOctet (int index) {
		int indexToOctet = index * TAILLE_NOEUD_OCTET;
		return indexToOctet;
	}
	
	public void ecrireNomBin(int index, String nom) {
		String nomFormate = this.getNomFormate(nom);
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			raf.seek(this.indexToOctet(index));
			raf.writeChars(nomFormate);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ecrirePrenomBin(int index, String prenom) {
		String prenomFormate = this.getPrenomFormate(prenom);
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			raf.seek(this.indexToOctet(index)+INDEX_NOM_OCTET);
			raf.writeChars(prenomFormate);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ecrireDepartementBin(int index, String departement) {
		String departementFormate = this.getDepartementFormate(departement);
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			raf.seek(this.indexToOctet(index)+INDEX_PRENOM_OCTET);
			raf.writeChars(departementFormate);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ecrireFormationBin(int index, String formation) {
		String formationFormate = this.getFormationFormate(formation);
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			raf.seek(this.indexToOctet(index)+INDEX_DEPARTEMENT_OCTET);
			raf.writeChars(formationFormate);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
// A VERIFIER ??????? ---------------------------------
	public void ecrireAnneeFormationBin(int index, String anneeFormation) {
		String anneeFormationFormate = this.getAnneeFormationFormate(anneeFormation);
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			raf.seek(this.indexToOctet(index)+INDEX_FORMATION_OCTET);
			raf.writeChars(anneeFormationFormate);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Methode pour importer le fichier texte sous la forme d'un arbre binaire dans
	// le fichier binaire de sauvegarde
	public void importAnnuaireTexte() {

		try {

			FileReader fichier = new FileReader(CHEMIN_TXT);
			BufferedReader br = new BufferedReader(fichier);

			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			this.raf.setLength(0);

			int cpt = 0;

			while (br.ready()) {

				String nom = br.readLine();
				nom = this.nomCheck(nom);
				String prenom = br.readLine();
				prenom = this.prenomCheck(prenom);
				String departement = br.readLine();
				departement = this.departementCheck(departement);
				String formation = br.readLine();
				formation = this.formationCheck(formation);
				String anneeFormation = br.readLine();
				anneeFormation = this.anneeFormationCheck(anneeFormation);
				br.readLine();

				Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, formation, anneeFormation);

				Noeud noeud = new Noeud(-1, stagiaire, -1, -1, -1);

				System.out.println("cpt " + cpt);
				cpt++;

				this.ajouterStagiaireBin(noeud);

			}

			fichier.close();
			this.raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int longueur() {
		int longueur = 0;
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			longueur = (int) this.raf.length()/TAILLE_NOEUD_OCTET;
			this.raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return longueur;
	}
	
	public Boolean isAlphaNumeric(String s) {
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (!(c >= 'à' && c <= 'û') && !(c == ' ') && !(c == '-') && !(c >= 'A' && c <= 'Z') &&
                    !(c >= 'a' && c <= 'z') &&
                    !(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }
	
	public Boolean isNumeric(String s) {
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }
	
	public Boolean textIsCorrect(String s) {
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (!(c >= 'à' && c <= 'û') && !(c == ' ') && !(c == '-') && !(c >= 'A' && c <= 'Z') &&
                    !(c >= 'a' && c <= 'z')) {
                return false;
            }
        }
        return true;
    }
	
	public Boolean departementIsCorrect(String s) {
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (!(c >= 'A' && c <= 'Z') &&
                    !(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }
	
	
	public String nomCheck(String nom) {
		if (nom.isBlank() || nom.isEmpty() || !textIsCorrect(nom)) {
			nom = "XXXXXXXXX";
		}
		if (nom.length()>TAILLE_MAX_NOM) {
			nom = nom.substring(0, TAILLE_MAX_NOM);
		}
		nom = nom.toUpperCase();
		return nom.trim() ;
	}
	
	public String randDomString() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    //System.out.println(generatedString);
	    return generatedString;
	}
	
	
	public String prenomCheck(String prenom) {
		if (prenom.isBlank() || prenom.isEmpty() || !textIsCorrect(prenom)) {
			prenom = "XXXXXX";
		}
		if (prenom.length()>TAILLE_MAX_PRENOM) {
			prenom = prenom.substring(0, TAILLE_MAX_PRENOM);
		}
		String prenomFormate ="";
		prenomFormate += prenom.substring(0,1).toUpperCase();
		prenomFormate += prenom.substring(1).toLowerCase();
		return prenomFormate.strip();
	}
	
	public String departementCheck(String departement) {
		String departementFormate = "";
		if (departement.isBlank() || departement.isEmpty() || departement.contains(" ")  ){
			for (int i = 0; i < TAILLE_MAX_DEPARTEMENT; i++) {
				departementFormate = "0" + departementFormate;
			}
		} else if (departement.length() < TAILLE_MAX_DEPARTEMENT ) {
			for (int i = departement.length(); i < TAILLE_MAX_DEPARTEMENT; i++) {
				departementFormate = "0" + departementFormate;
			}
			departementFormate += departement;
		} else if (departement.length()>TAILLE_MAX_DEPARTEMENT){
			departementFormate = departement.substring(0, TAILLE_MAX_DEPARTEMENT);
		} else {
			departementFormate = departement;
		}
		return departementFormate;
	}
	
	public String formationCheck(String formation) {
		if (formation.length()>TAILLE_MAX_FORMATION) {
			formation = formation.substring(0, TAILLE_MAX_FORMATION );
		}
		return formation.strip();
	}
	
	public String anneeFormationCheck(String anneeFormation) {
		if (anneeFormation.length() != TAILLE_MAX_ANNEEFORMATION) {
			return "0000";
		}
		if (!isNumeric(anneeFormation)) {
			return "0000";
		}
		return anneeFormation.strip();
	}
		
	// Methode pour importer le fichier texte sous la forme d'un arbre binaire dans
	// le fichier binaire de sauvegarde
	public void importStagiaire(Stagiaire stagiaire) {

		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			Noeud noeud = new Noeud(stagiaire);
			this.ajouterStagiaireBin(noeud);
			this.raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Méthode qui crée la racine si le fichier est vide, et sinon ajoute le
	// stagiaire sur fichier bin
	public void ajouterStagiaireBin(Noeud noeudAjout) {
		try {
			int indexNoeudRacine = 0;
			if (this.raf.length() == 0) { // si le fichier binaire est vide, on écrit le stagiaire
				this.ecrireNoeudBin(noeudAjout, indexNoeudRacine);
			} else {
				// this.raf.seek(TAILLE_NOEUD_OCTET);
				this.ecritureNoeudBinRecursive(noeudAjout, indexNoeudRacine);
			}

		} catch (IOException e) {
			System.out.println("EXCEPTION");
			e.printStackTrace();
		}
	}
		
		
		// Methode pour écrire le noeud stagiaire dans le fichier binaire à la fin du fichier binaire
		public void ecrireNoeudBin(Noeud noeudAjout, int indexNoeud) {
			try {
				
			this.raf.seek(this.raf.length());
			this.raf.writeInt(indexNoeud);
			recuperationAttributsStagiaireBin(noeudAjout.getCle());
			this.raf.writeInt(noeudAjout.getIndexFilsGauche());
			this.raf.writeInt(noeudAjout.getIndexFilsDroit());
			this.raf.writeInt(noeudAjout.getIndexDoublon());
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		// methode qui ecrit les attributs du stagiaire dans le fichier binaire
		public void recuperationAttributsStagiaireBin(Stagiaire stagiaire) {
			try {

				String nomLong = stagiaire.getNomFormate();
				String prenomLong = stagiaire.getPrenomFormate();
				raf.writeChars(nomLong);
				raf.writeChars(prenomLong);
				raf.writeChars(stagiaire.getDepartementFormate());
				raf.writeChars(stagiaire.getFormationFormate());
				raf.writeChars(stagiaire.getAnneeFormationFormate());

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		/* TEST NOT OK
		// Methode recursive d'ajout de stagiaire dans le fichier bin
		public void ecritureNoeudBinRecursive(Noeud noeudAjout, int index) {
			try {
				int indexNoeud = (int) (this.raf.length() / TAILLE_NOEUD_OCTET);

				if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomFormateNoeudBin(index)) < 0) { // on part a gauche

					if (recupererIndexFilsGauche(index) != -1) { // il y a un fils gauche
						index = recupererIndexFilsGauche(index);
						ecritureNoeudBinRecursive(noeudAjout, index); // on passe la methode au fils gauche
					} else { // il n'y a pas de fils gauche
						ecrireFilsGauche(index, indexNoeud);
						ecrireNoeudBin(noeudAjout, indexNoeud);
					}
				} else if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomFormateNoeudBin(index)) > 0) { // on part a Droite

					if (recupererIndexFilsDroit(index) != -1) { // il y a un fils Droit
						index = recupererIndexFilsDroit(index);
						ecritureNoeudBinRecursive(noeudAjout, index); // on passe la methode au fils Droit
					} else { // il n'y a pas de fils Droit
						ecrireFilsDroit(index, indexNoeud);
						ecrireNoeudBin(noeudAjout, indexNoeud);
					}
				} else if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomFormateNoeudBin(index)) == 0) {
					if (recupererIndexDoublon(index) == -1) {
						ecrireDoublon(index, indexNoeud);
						ecrireNoeudBin(noeudAjout, indexNoeud);
					} else {
					    insererDoublonRecursive(noeudAjout, index, indexNoeud, index);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		
		public void insererDoublonRecursive(Noeud noeudAjout, int index, int indexNoeud, int indexParent) {
			if (recupererIndexDoublon(index) != -1) {
				if (noeudAjout.getCle().getPrenom().compareTo(recuperePrenomNoeudBin(index)) > 0) {
					indexParent = index;
					index = recupererIndexDoublon(index);
					insererDoublonRecursive(noeudAjout, index, indexNoeud, indexParent);
				} else if (noeudAjout.getCle().getPrenom().compareTo(recuperePrenomNoeudBin(index)) <= 0) {
					ecrireNoeudBin(noeudAjout, indexNoeud);
					ecrireDoublon(indexParent, indexNoeud);
					ecrireDoublon(indexNoeud, index);	
				}
			} else {
				
				ecrireDoublon(index, indexNoeud);
				ecrireNoeudBin(noeudAjout, indexNoeud);
			}
		}
		*/
		

		// Methode recursive d'ajout de stagiaire dans le fichier bin
				public void ecritureNoeudBinRecursive(Noeud noeudAjout, int index) {
					try {
						int indexNoeud = (int) (this.raf.length() / TAILLE_NOEUD_OCTET);
						int indexParent = index;
						if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomFormateNoeudBin(indexParent)) < 0) { // on part a gauche

							if (recupererIndexFilsGauche(indexParent) != -1) { // il y a un fils gauche
								indexParent = recupererIndexFilsGauche(indexParent);
								ecritureNoeudBinRecursive(noeudAjout, indexParent); // on passe la methode au fils gauche
							} else { // il n'y a pas de fils gauche
								ecrireFilsGauche(indexParent, indexNoeud);
								ecrireNoeudBin(noeudAjout, indexNoeud);
							}
						} else if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomFormateNoeudBin(indexParent)) > 0) { // on part a Droite

							if (recupererIndexFilsDroit(indexParent) != -1) { // il y a un fils Droit
								indexParent = recupererIndexFilsDroit(indexParent);
								ecritureNoeudBinRecursive(noeudAjout, indexParent); // on passe la methode au fils Droit
							} else { // il n'y a pas de fils Droit
								ecrireFilsDroit(indexParent, indexNoeud);
								ecrireNoeudBin(noeudAjout, indexNoeud);
							}
						} else if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomFormateNoeudBin(indexParent)) == 0) {
							if (recupererIndexDoublon(indexParent) != -1) {
								indexParent = recupererIndexDoublon(indexParent);
								ecritureNoeudBinRecursive(noeudAjout, indexParent);
							} else {
								ecrireDoublon(indexParent, indexNoeud);
								ecrireNoeudBin(noeudAjout, indexNoeud);
							}

						}

					} catch (IOException e) {
						e.printStackTrace();
					}

		}

		/**
		 * Recupere l'attribut "indexNoeud" du noeud dans le fichier Bin
		 * @param index Index du noeud
		 * @return int indexNoeud
		 */
		public int recupereIndexNoeudBin(int index) {
			int indexNoeud = -1;
			try {
				this.raf.seek(index * TAILLE_NOEUD_OCTET);
				indexNoeud = raf.readInt();
				// raf.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

			return indexNoeud;
		}

		/**
		 * Recupere l'attribut "nom" (en version formate) du noeud dans le fichier Bin
		 * @param index Index du noeud
		 * @return String nomFormate
		 */
		public String recupereNomFormateNoeudBin(int index) {
			String nomFormate = "";
			try {
				this.raf.seek(index*TAILLE_NOEUD_OCTET + INDEX_NOM_OCTET);
				for (int k = 0; k < TAILLE_MAX_NOM; k++) {
					nomFormate += raf.readChar();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			return nomFormate;
		}
		
		/**
		 * Recupere l'attribut "nom" (en version non-formate) du noeud dans le fichier Bin
		 * 
		 * @param index Index du noeud
		 * @param raf   Le RandomAccessFile du fichier bin
		 * @return String nom
		 */
		public String recupereNomNoeudBin(int index) {
			String nomFormate = this.recupereNomFormateNoeudBin(index);
			return nomFormate.trim();
		}
		
		/**
		 * Recupere l'attribut "prenom" (en version formate) du noeud dans le fichier Bin
		 * @param index Index du noeud
		 * @return prenomFormate
		 */
		public String recuperePrenomFormateNoeudBin(int index) {
			String prenomFormate = "";
			try {
				this.raf.seek(index * TAILLE_NOEUD_OCTET + INDEX_PRENOM_OCTET);
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
		public String recuperePrenomNoeudBin(int index) {
			String prenomFormate = recuperePrenomFormateNoeudBin(index);
			return prenomFormate.trim();
		}

		/**
		 * Recupere l'attribut "departement" (en version formate) du noeud dans le
		 * fichier Bin
		 * @param index Index du noeud
		 * @return String departementFormate
		 */
		public String recupereDepartementFormateNoeudBin(int index) {
			String departementFormate = "";
			try {
				this.raf.seek(index * TAILLE_NOEUD_OCTET + INDEX_DEPARTEMENT_OCTET);
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
		 * @return String departement
		 */
		public String recupereDepartementNoeudBin(int index) {
			String departementFormate = recupereDepartementFormateNoeudBin(index);
			return departementFormate.trim();
		}

		/**
		 * Recupere l'attribut "formation" (en version formate) du noeud dans le
		 * fichier Bin
		 * 
		 * @param index Index du noeud
		 * @return String formationFormate
		 */
		public String recupereFormationFormateNoeudBin(int index) {
			String formationFormate = "";
			try {
				this.raf.seek(index * TAILLE_NOEUD_OCTET + INDEX_FORMATION_OCTET);
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
		 * @return String formation
		 */
		public String recupereFormationNoeudBin(int index) {
			String formationFormate = recupereFormationFormateNoeudBin(index);
			return formationFormate.trim();
		}

		/**
		 * Recupere l'attribut "anneeFormation" (en version formate) du noeud dans le
		 * fichier Bin
		 * 
		 * @param index Index du noeud
		 * @return String anneeFormationFormate
		 */
		public String recupereAnneeFormationFormateNoeudBin(int index) {
			String anneeFormationFormate = "";
			try {
				this.raf.seek(index * TAILLE_NOEUD_OCTET + INDEX_ANNEEFORMATION_OCTET);
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
		 * @return String anneeFormation
		 */
		public String recupereAnneeFormationNoeudBin(int index) {
			String anneeFormationFormate = recupereAnneeFormationFormateNoeudBin(index);
			return anneeFormationFormate.trim();
		}
		
		
		/**
		 * Recupere l'attribut "indexFilsGauche" du noeud dans le fichier Bin
		 * @param index Index du noeud
		 * @return int indexFilsGauche
		 */
		public int recupererIndexFilsGauche(int indexNoeud) {
			int indexFilsGauche = 0;
			try {
				this.raf.seek((indexNoeud* TAILLE_NOEUD_OCTET)+INDEX_FILS_GAUCHE_OCTET);
				indexFilsGauche += raf.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return indexFilsGauche;
		}
		
		/**
		 * Recupere l'attribut "indexFilsDroit" du noeud dans le fichier Bin
		 * @param index Index du noeud
		 * @return int indexFilsDroit
		 */
		public int recupererIndexFilsDroit(int indexNoeud) {
			int indexFilsDroit = 0;
			try {
				this.raf.seek((indexNoeud* TAILLE_NOEUD_OCTET)+INDEX_FILS_DROIT_OCTET);
				indexFilsDroit += raf.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return indexFilsDroit;
		}
		
		/**
		 * Recupere l'attribut "indexDoublon" du noeud dans le fichier Bin
		 * @param index Index du noeud
		 * @return int indexDoublon
		 */
		public int recupererIndexDoublon(int indexNoeud) {
			int indexDoublon = 0;
			try {
				this.raf.seek((indexNoeud* TAILLE_NOEUD_OCTET)+INDEX_DOUBLON_OCTET);
				indexDoublon += raf.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return indexDoublon;
		}
		
		// Methode qui modifie l'indexFilsGauche du noeud parent en lui attribuant la valeur indexNoeud
		public void ecrireFilsGauche(int indexParent, int indexNoeud) {
			try {
				this.raf.seek((indexParent*TAILLE_NOEUD_OCTET) + INDEX_FILS_GAUCHE_OCTET);
				this.raf.writeInt(indexNoeud);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Methode qui modifie l'indexFilsDroit du noeud parent en lui attribuant la valeur indexNoeud
		public void ecrireFilsDroit(int indexParent, int indexNoeud) {
			try {
				this.raf.seek((indexParent*TAILLE_NOEUD_OCTET) + INDEX_FILS_DROIT_OCTET);
				this.raf.writeInt(indexNoeud);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Methode qui modifie l'indexDoublon du noeud parent en lui attribuant la valeur indexNoeud
		public void ecrireDoublon(int indexParent, int indexNoeud) {
			try {
				this.raf.seek((indexParent*TAILLE_NOEUD_OCTET) + INDEX_DOUBLON_OCTET);
				this.raf.writeInt(indexNoeud);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Affiche dans la console le contenu du fichier binaire
		 * 
		 * @return Affiche dans la console la liste des noeuds contenus dans le fichier
		 *         binaire avec leurs attributs: [ index / nom / prenom / departement /
		 *         formation / anneeFormation / indexFilsGauche / indexFilsDroit /
		 *         indexDoublon ]
		 */
		public void afficherFichierBin() {
			try {
				System.out.println("\n******* FICHIER BIN: " + this.adresseFichierBin);
				this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
				int nbrStagiaires = (int) (this.raf.length() / (TAILLE_NOEUD_OCTET));
				// on ouvre le flux "raf" associé au fichier binaire

				// int nbrStagiaires = 100; // A DEFINIR PAR UNE FONCTION
				// annuaire.getNombreStagiaires() qui calcule le nombre de noeuds dans
				// l'annuaire;
				for (int i = 0; i < nbrStagiaires; i++) {
					// on positionne la tête de lecture en position "i * TAILLE_STAGIAIRE_OCTET"
					// car .seek() se base sur l'octet et non sur le caractère (1 letter = 2 octets
					// / 1 int = 4 octets)
					// contrairement à .readChar() qui lui se déplace de caractère en caractère
					this.raf.seek(i * (TAILLE_NOEUD_OCTET));

					// on crée les variables qui vont stocker les valeurs des attributs
					int indexNoeud = 0;
					String nomRecup = "";
					String prenomRecup = "";
					String departementRecup = "";
					String formationRecup = "";
					String anneeFormationRecup = "";
					int indexFilsGauche = 0;
					int indexFilsDroit = 0;
					int indexDoublon = 0;

					// DEBUT DE LA LECTURE DU "raf"
					// On commence à parcourir/lire le fichier binaire selon ce principe :
					indexNoeud = this.raf.readInt();
					// 1--> de 0 à TAILLE_MAX_NOM, on sait qu'il s'agit des lettres du "nom" du
					// stagiaire
					for (int k = 0; k < TAILLE_MAX_NOM; k++) {
						nomRecup += this.raf.readChar();
					}
					// 1--> de 0 à TAILLE_MAX_PRENOM, on sait qu'il s'agit des lettres du "prenom"
					// du stagiaire
					for (int k = 0; k < TAILLE_MAX_PRENOM; k++) {
						prenomRecup += this.raf.readChar();
					}
					// 1--> de 0 à TAILLE_MAX_DEPARTEMENT, on sait qu'il s'agit des numeros du
					// "département" du stagiaire
					for (int k = 0; k < TAILLE_MAX_DEPARTEMENT; k++) {
						departementRecup += this.raf.readChar();
					}
					// 1--> de 0 à TAILLE_MAX_FORMATION, on sait qu'il s'agit des lettres de la
					// "formation" du stagiaire
					for (int k = 0; k < TAILLE_MAX_FORMATION; k++) {
						formationRecup += this.raf.readChar();
					}
					// 1--> de 0 à TAILLE_MAX_ANNEEFORMATION, on sait qu'il s'agit des lettres de l'
					// "annee de formation" du stagiaire
					for (int k = 0; k < TAILLE_MAX_ANNEEFORMATION; k++) {
						anneeFormationRecup += this.raf.readChar();
					}
					indexFilsGauche += this.raf.readInt();
					indexFilsDroit += this.raf.readInt();
					indexDoublon += this.raf.readInt();

					// FIN DE LA LECTURE

					// On affiche les résultats

					System.out.println("[IndexNoeud: " + indexNoeud + "]");
					System.out.println("nom: " + nomRecup.trim() + " / " + "prenom: " + prenomRecup.trim() + " / "
							+ "dept: " + departementRecup.trim() + " / " + "formation: " + formationRecup.trim() + " / "
							+ "Annee: " + anneeFormationRecup.trim());
					System.out.println("[" + "FG: " + indexFilsGauche + "][" + "FD: " + indexFilsDroit + "][" + "DB: "
							+ indexDoublon + "]\n");
				}

				// on ferme le flux "raf"
				this.raf.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		/**
		 * RechercherMulticritere : Methode principale. 
		 * Cree une ArrayList contenant les index de noeuds recherches (dont les criteres sont dans l'objet 
		 * @param recherche Objet de classe RechercheMulticritere contenant les filtres de recherche
		 * @return une ArrayList des index des noeuds recherches
		 */
		public ArrayList<Integer> rechercheMulticriteres(RechercheMulticritere recherche) {
			ArrayList<Integer> resultatRecherche = new ArrayList<Integer>();
			try {
				this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
				
				if (this.raf.length() == 0) {
					System.out.println("ERROR >> Recherche impossible dans un fichier vide");
				} else {
					resultatRecherche =  this.rechercheMulticriteresRecursive(recherche);
				}
				
				this.raf.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
			return resultatRecherche;

		}
		
		/**
		 * Methode secondaire pour la RechercherMulticritere : 
		 * Methode recursive de recherche des index des noeuds et de leur écriture dans une ArrayList
		 * @param recherche Objet de classe RechercheMulticritere contenant les filtres de recherche
		 * @return une ArrayList des index des noeuds recherches
		 */
		public ArrayList<Integer> rechercheMulticriteresRecursive(RechercheMulticritere recherche) {
			ArrayList<Integer> resultatRecherche = new ArrayList<Integer>();
			try {
				int nombreStagiairesBin = (int) (this.raf.length() / TAILLE_NOEUD_OCTET);
				for (int index = 0; index < nombreStagiairesBin; index++) {
					this.raf.seek(index);
					Noeud noeud = this.recupereNoeudIndex(index);
					if (recherche.rechercheMulticritereNoeud(noeud) ==  true) {
						resultatRecherche.add(index);
					}
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return resultatRecherche;

		}
		
		public Noeud recupereNoeudIndex (int index) {
			Noeud noeud = new Noeud();
			noeud.setIndexNoeud(index);
			String nom = this.recupereNomFormateNoeudBin(index);
			String prenom = this.recuperePrenomFormateNoeudBin(index);
			String departement = this.recupereDepartementFormateNoeudBin(index);
			String formation = this.recupereFormationFormateNoeudBin(index);
			String anneeFormation = this.recupereAnneeFormationNoeudBin(index);
			noeud.setCle(new Stagiaire(nom, prenom, departement, formation, anneeFormation));
			noeud.setIndexFilsGauche(this.recupererIndexFilsGauche(index));
			noeud.setIndexFilsDroit(this.recupererIndexFilsDroit(index));
			noeud.setIndexDoublon(this.recupererIndexDoublon(index));
			return noeud;
		}
		
		public Noeud recupereNoeudIndex (int index, RandomAccessFile raf) {
			Noeud noeud = new Noeud(-1, null, -1,-1,-1);
			String nom = noeud.recupereNomIndex(index, raf);
			String prenom = noeud.recuperePrenomNoeud(index, raf);
			String departement = noeud.recupereDepartementNoeud(index, raf);
			String formation = noeud.recupereFormationNoeud(index, raf);
			String anneeFormation = noeud.recupereAnneeFormationNoeud(index, raf);
			noeud.setCle(new Stagiaire(nom, prenom, departement, formation, anneeFormation));
			return noeud;
		}
		
		/**
		 * Methode complementaire de l'algorithme de RechercheMulticritere :
		 * Transforme une Array list d'index de noeuds en Arbre binaire des noeud correspondants aux index
		 * @param listeIndexNoeud L'array list recuperee en sortie de la RechercheMulticriteres
		 * @param arbreBin L'arbre danns lequel transcrire l'ArrayList
		 */
		public void importArrayToArbreBin(ArrayList<Integer> listeIndexNoeud, ArbreBin arbreBin){
			try {

				this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
				RandomAccessFile rafParent = new RandomAccessFile(arbreBin.adresseFichierBin, "rw");
				this.raf.setLength(0);

					for (Integer indexNoeud : listeIndexNoeud) {
						Noeud noeud = this.recupereNoeudIndex(indexNoeud, rafParent);
						this.ajouterStagiaireBin(noeud);
					}
					
					this.raf.close();
					rafParent.close();

				} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/**
		  * Methode principale de la transformation de l'ArbreBin en ArrayList
		  * Exporte l'ArbreBin dans l'ArrayList passee en argument
		  * @param listeNoeud L'ArrayList à remplir avec les données de l'ArbreBin
		  */
		public void exportToArrayListOptionRecherche(ArrayList<Noeud> listeNoeud, boolean rechMultActivee, RechercheMulticritere recherche){
			try {
				
				this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			    if (this.raf.length() == 0) {
			    	listeNoeud = null;
			    } else {
			    	if (rechMultActivee == true) {
			    		this.exportToArrayListOptionRechercheRecursive(listeNoeud,0, 0, recherche);
			    	} else {
			    		this.exportToArrayListRecursive(listeNoeud,0, 0);
			    	}
			}
			
			this.raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Methode secondaire recursive pour la transformation de l'ArbreBin en ArrayList :
		 * @param listeNoeud L'ArrayList à remplir avec les données de l'ArbreBin
		 * @param indexEnCours L'index du noeud sur lequel on se situe
		 * @param indexParent L'index du noeud parent au noeud dans lequel on se trouve
		 */
		public void exportToArrayListOptionRechercheRecursive(ArrayList<Noeud> listeNoeud, int indexEnCours, int indexParent, RechercheMulticritere recherche){
			int indexEnCoursLocal = indexEnCours;
		
			if (this.recupererIndexFilsGauche(indexEnCours) != -1) {
				indexParent = indexEnCours;
				indexEnCours = this.recupererIndexFilsGauche(indexEnCours);
				this.exportToArrayListOptionRechercheRecursive(listeNoeud,indexEnCours, indexParent, recherche);
			}
			
			Noeud noeud = this.recupereNoeudIndex(indexEnCoursLocal);
			if (recherche.rechercheMulticritereNoeud(noeud) ==  true) {
				listeNoeud.add(noeud);
			}
			indexEnCours = indexEnCoursLocal;
			
			if (this.recupererIndexDoublon(indexEnCoursLocal) != -1) {
				this.exportToArrayListDoublonOptionRecherche(listeNoeud, this.recupererIndexDoublon(indexEnCoursLocal), recherche);
			}
			
			if (this.recupererIndexFilsDroit(indexEnCours) != -1) {
				indexParent = indexEnCours;
				indexEnCours = this.recupererIndexFilsDroit(indexEnCours);
				this.exportToArrayListOptionRechercheRecursive(listeNoeud,indexEnCours, indexParent, recherche);
			}
			
		}

		/** Troisieme méthode pour la transformation de l'ArbreBin en ArrayList : traitement des doublons
		 * 
		 * @param listeNoeud L'ArrayList à compléter par les doublons
		 * @param indexPremierDoublon Index du premier doublon (= DEUXIEME de la chaine Doublons en comptant le noeud de départ)
		 */
		public void exportToArrayListDoublonOptionRecherche(ArrayList<Noeud> listeNoeud, int indexPremierDoublon, RechercheMulticritere recherche){
			int indexParcours = indexPremierDoublon; 
	    	while (this.recupererIndexDoublon(indexParcours) != -1) {
	    		Noeud noeud = this.recupereNoeudIndex(indexParcours);
				if (recherche.rechercheMulticritereNoeud(noeud) ==  true) {
					listeNoeud.add(noeud);
				}
	    		indexParcours = this.recupererIndexDoublon(indexParcours);
	    	}
	    	Noeud noeud = this.recupereNoeudIndex(indexParcours);
	    	if (recherche.rechercheMulticritereNoeud(noeud) ==  true) {
	    		listeNoeud.add(noeud);
	    	}
		}
		
		
		 /**
		  * Methode principale de la transformation de l'ArbreBin en ArrayList
		  * Exporte l'ArbreBin dans l'ArrayList passee en argument
		  * @param listeNoeud L'ArrayList à remplir avec les données de l'ArbreBin
		  */
		public void exportToArrayList(ArrayList<Noeud> listeNoeud){
			try {
				
				this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			    if (this.raf.length() == 0) {
			    	listeNoeud = null;
			    } else {
			    	this.exportToArrayListRecursive(listeNoeud,0, 0);
			}
			
			this.raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Methode secondaire recursive pour la transformation de l'ArbreBin en ArrayList :
		 * @param listeNoeud L'ArrayList à remplir avec les données de l'ArbreBin
		 * @param indexEnCours L'index du noeud sur lequel on se situe
		 * @param indexParent L'index du noeud parent au noeud dans lequel on se trouve
		 */
		public void exportToArrayListRecursive(ArrayList<Noeud> listeNoeud, int indexEnCours, int indexParent){
			int indexEnCoursLocal = indexEnCours;
		
			if (this.recupererIndexFilsGauche(indexEnCours) != -1) {
				indexParent = indexEnCours;
				indexEnCours = this.recupererIndexFilsGauche(indexEnCours);

				this.exportToArrayListRecursive(listeNoeud,indexEnCours, indexParent);
			}
			
			listeNoeud.add(this.recupereNoeudIndex(indexEnCoursLocal));
			indexEnCours = indexEnCoursLocal;
			
			if (this.recupererIndexDoublon(indexEnCoursLocal) != -1) {
				this.exportToArrayListDoublon(listeNoeud, this.recupererIndexDoublon(indexEnCoursLocal));
			}
			
			if (this.recupererIndexFilsDroit(indexEnCours) != -1) {
				indexParent = indexEnCours;
				indexEnCours = this.recupererIndexFilsDroit(indexEnCours);
				this.exportToArrayListRecursive(listeNoeud,indexEnCours, indexParent);
			}
			
		}

		/** Troisieme méthode pour la transformation de l'ArbreBin en ArrayList : traitement des doublons
		 * 
		 * @param listeNoeud L'ArrayList à compléter par les doublons
		 * @param indexPremierDoublon Index du premier doublon (= DEUXIEME de la chaine Doublons en comptant le noeud de départ)
		 */
		public void exportToArrayListDoublon(ArrayList<Noeud> listeNoeud, int indexPremierDoublon){
			int indexParcours = indexPremierDoublon; 
	    	while (this.recupererIndexDoublon(indexParcours) != -1) {
	    		listeNoeud.add(this.recupereNoeudIndex(indexParcours));
	    		indexParcours = this.recupererIndexDoublon(indexParcours);
	    	}
	    	listeNoeud.add(this.recupereNoeudIndex(indexParcours));
		}
		
		//public void modifierAttribut()
		
		
		// test pour exporter une array list dans l'ordre initial de ses elements WIP
				public void exportToArrayListDoublonV02(ArrayList<Noeud> listeNoeud, int indexPremierDoublon){
					int cptDoublon = 2; // car si on entre dans cette methode, c'est qu'il y a déjà un doublon
					
					try {
						
						this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
					    if (this.raf.length() == 0) {
					    	listeNoeud = null;
					    } else {
					    	// on compte le nombre de doublons à partir de l'indexPremierDoublon
					    	int indexB = indexPremierDoublon; 
					    	while (this.recupererIndexDoublon(indexB) != -1) {
					    		indexB = this.recupererIndexDoublon(indexB);
					    		cptDoublon ++;
					    	}
//					    	int indexBuffer = indexPremierDoublon;
//					    	int indexEncours = indexPremierDoublon;
					    	int indexMin = indexPremierDoublon;
					    	int indexATester = this.recupererIndexDoublon(indexMin);
					    	for (int i = 0; i < cptDoublon; i ++) {
					    		for (int k = i+1; k < cptDoublon; k++) {
					    			if (this.recupereNoeudIndex(indexATester).getCle().getPrenomFormate().compareTo(recuperePrenomFormateNoeudBin(indexMin)) < 0) {
					    				indexMin = indexATester;
					    				indexATester = this.recupererIndexDoublon(indexATester);
							    	}
					    			listeNoeud.add(this.recupereNoeudIndex(indexMin));
					    			indexATester = this.recupererIndexDoublon(indexATester);
					    		}
					    	}
					    	
					    	System.out.println("cptDoublon "+cptDoublon);

					}
					
					this.raf.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				// ABIGAEL ----------------------------------- 20201209 --------------------------------
				// Méthode pour supprimer un stagiaire
				public void supprimerStagiaireNoeud(String stagiaireSupp) {
					try {

						this.raf = new RandomAccessFile(CHEMIN_BIN, "rw");
						int indexNoeud = 0;
						
						Noeud noeudSupp = new Noeud();
						noeudSupp = this.recupererNoeudSupp(noeudSupp, stagiaireSupp, indexNoeud);
						System.out.println(noeudSupp);
						
						
						
						//Si c'est la racine
						if(noeudSupp.getIndexNoeud()==indexNoeud) {
							supprimerRacine(noeudSupp);
						}else {
							//Si le noeud à un doublon
							if(noeudSupp.getIndexDoublon()!=-1) {
								modifierNoeudAvecDoublon(noeudSupp);
							}else {
								
						//Si le noeud à supprimer n'a pas de fils 
						if(noeudSupp.getIndexFilsGauche()==-1 && noeudSupp.getIndexFilsDroit()==-1) {
							modifierNoeudParentSansFils(noeudSupp);
						//Si le noeud à supprimer à 2 fils	
						}else if (noeudSupp.getIndexFilsGauche()!=-1 && noeudSupp.getIndexFilsDroit()!=-1) {
							modifierNoeudParentAvecFilsDroit(noeudSupp);
						//Si le noeud à supprimer a un fils droit
						}else if (noeudSupp.getIndexFilsGauche()==-1) {
							modifierNoeudParentAvecFilsDroit(noeudSupp);
						//Si le noeud à supprimer a un fils gauche
						}else if (noeudSupp.getIndexFilsDroit()==-1) {
							modifierNoeudParentAvecFilsGauche(noeudSupp);
						
						}
							}	
						}
					

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				private void modifierNoeudAvecDoublon(Noeud noeudFils) {
					int indexNoeudSansFils = -1;
					int indexNoeudFils = noeudFils.getIndexNoeud();
					int indexNoeudParent = 0;

					int indexDbNoeudSupp = noeudFils.getIndexDoublon();
					int indexFDnoeudSupp = noeudFils.getIndexFilsDroit();
					int indexFGnoeudSupp = noeudFils.getIndexFilsGauche();

					// Chercher le parents
					indexNoeudParent = recupererIndexParent(indexNoeudFils, indexNoeudParent);

					int indexNoeud = 0;

					int indexFilsNoeud = noeudFils.getIndexDoublon();

					while (indexFilsNoeud != recupereIndexNoeudBin(indexNoeud)) {
						indexNoeud++;
					}

					Noeud noeudRecup = new Noeud();
					noeudRecup = this.recupererNoeud(noeudRecup, indexNoeud);

					System.out.println(noeudRecup);

					indexNoeudFils = noeudFils.getIndexNoeud();

					System.out.println(indexNoeudFils);
					System.out.println(indexNoeudParent);
					// Modifier le parent
					ecrireFilsGauche(indexNoeudParent, indexNoeudFils);// écrire Doublon au parent du noeud supprimé
					ecrireFilsDroit(indexNoeudFils, indexFDnoeudSupp);// écrire FD du noeud supprimé au noeud qui prend sa place, ie au doublon											
					ecrireFilsGauche(indexNoeudFils, indexFGnoeudSupp);// écrire FG du noeud supprimé au noeud qui prend sa place, ie au doublon

				}

				private void supprimerRacine(Noeud noeudFils) {
					int indexNoeudSansFils = -1;
					int indexNoeudFils = noeudFils.getIndexNoeud();

					int indexFDnoeudSupp = noeudFils.getIndexFilsDroit();
					int indexFGnoeudSupp = noeudFils.getIndexFilsGauche();

					int indexNoeud = 0;

					int indexFilsNoeud = noeudFils.getIndexFilsDroit();

					while (indexFilsNoeud != recupereIndexNoeudBin(indexNoeud)) {
						indexNoeud++;
					}

					Noeud noeudRecup = new Noeud();
					noeudRecup = this.recupererNoeud(noeudRecup, indexNoeud);

					System.out.println(noeudRecup);

					// chercher le fils du noeud supp
					if (noeudRecup.getIndexFilsGauche() != indexNoeudSansFils) {
						indexNoeudFils = chercherFilsPlusPetit(noeudRecup, indexNoeudFils);
					}
					indexNoeudFils = noeudFils.getIndexNoeud();

					System.out.println(indexNoeudFils);
					// Modifier le parent
					ecrireFilsDroit(indexNoeudFils, indexFDnoeudSupp);// écrire FD du noeud supprimé au noeud qui prend sa
																			// place
					ecrireFilsGauche(indexNoeudFils, indexFGnoeudSupp);// écrire FG du noeud supprimé au noeud qui prend sa
																			// place

				}

				private void modifierNoeudParentAvecFilsGauche(Noeud noeudFils) {
					int indexNoeudSansFils = -1;
					int indexNoeudFils = noeudFils.getIndexNoeud();
					int indexNoeudParent = 0;

					int indexFDnoeudSupp = noeudFils.getIndexFilsDroit();
					int indexFGnoeudSupp = noeudFils.getIndexFilsGauche();

					// Chercher le parents
					indexNoeudParent = recupererIndexParent(indexNoeudFils, indexNoeudParent);

					int indexNoeud = 0;

					int indexFilsNoeud = noeudFils.getIndexFilsGauche();

					while (indexFilsNoeud != recupereIndexNoeudBin(indexNoeud)) {
						indexNoeud++;
					}

					Noeud noeudRecup = new Noeud();
					noeudRecup = this.recupererNoeud(noeudRecup, indexNoeud);

					System.out.println(noeudRecup);

					// chercher le fils du noeud supp
					if (noeudRecup.getIndexFilsDroit() != indexNoeudSansFils) {
						indexNoeudFils = chercherFilsPlusGrand(noeudRecup, indexNoeudFils);
					}
					indexNoeudFils = noeudFils.getIndexNoeud();

					System.out.println(indexNoeudFils);
					System.out.println(indexNoeudParent);
					// Modifier le parent
					ecrireFilsDroit(indexNoeudParent, indexNoeudFils);// écrire FG au parent du noeud supprimé
					ecrireFilsDroit(indexNoeudFils, indexFDnoeudSupp);// écrire FD du noeud supprimé au noeud qui prend sa
																			// place
					ecrireFilsGauche(indexNoeudFils, indexFGnoeudSupp);// écrire FG du noeud supprimé au noeud qui prend sa
																			// place

				}

				private int chercherFilsPlusGrand(Noeud noeudFils, int indexNoeudNouveauFils) {
					int indexNoeudSansFils = -1;
					int indexNoeud = 0;

					int indexFilsNoeud = noeudFils.getIndexFilsDroit();

					while (indexFilsNoeud != recupereIndexNoeudBin(indexNoeud)) {
						indexNoeud++;
					}

					Noeud noeudRecup = new Noeud();
					noeudRecup = this.recupererNoeud(noeudRecup, indexNoeud);
					System.out.println(noeudRecup);

					// Si pas de fils gauche
					if (noeudRecup.getIndexFilsDroit() != indexNoeudSansFils) {
						chercherFilsPlusGrand(noeudRecup, indexNoeudNouveauFils);
					}
					indexNoeudNouveauFils = noeudRecup.getIndexNoeud();

					return indexNoeudNouveauFils;

				}

				// Méthode pour donner au Noeud Parents l'index du fils le plus petit à la place
				// de l'index du noeud supp
				private void modifierNoeudParentAvecFilsDroit(Noeud noeudFils) {
					int indexNoeudSansFils = -1;
					int indexNoeudFils = noeudFils.getIndexNoeud();
					int indexNoeudParent = 0;

					int indexFDnoeudSupp = noeudFils.getIndexFilsDroit();
					int indexFGnoeudSupp = noeudFils.getIndexFilsGauche();

					// Chercher le parents
					indexNoeudParent = recupererIndexParent(indexNoeudFils, indexNoeudParent);

					int indexNoeud = 0;

					int indexFilsNoeud = noeudFils.getIndexFilsDroit();

					while (indexFilsNoeud != recupereIndexNoeudBin(indexNoeud)) {
						indexNoeud++;
					}

					Noeud noeudRecup = new Noeud();
					noeudRecup = this.recupererNoeud(noeudRecup, indexNoeud);

					System.out.println(noeudRecup);

					// chercher le fils du noeud supp
					if (noeudRecup.getIndexFilsGauche() != indexNoeudSansFils) {
						indexNoeudFils = chercherFilsPlusPetit(noeudRecup, indexNoeudFils);
					}
					indexNoeudFils = noeudFils.getIndexNoeud();

					System.out.println(indexNoeudFils);
					System.out.println(indexNoeudParent);
					// Modifier le parent
					ecrireFilsDroit(indexNoeudParent, indexNoeudFils);// écrire FG au parent du noeud supprimé
					ecrireFilsDroit(indexNoeudFils, indexFDnoeudSupp);// écrire FD du noeud supprimé au noeud qui prend sa
																			// place
					ecrireFilsGauche(indexNoeudFils, indexFGnoeudSupp);// écrire FG du noeud supprimé au noeud qui prend sa
																			// place

				}

				private int recupererIndexParent(int indexNoeudFils, int indexNoeudParent) {
					int indexNoeud = 0;
					int indexNoeudSansFils = -1;

					while ((indexNoeudFils != recupererIndexFilsGauche(indexNoeud)
							&& (indexNoeudFils != recupererIndexFilsDroit(indexNoeud))
							&& (indexNoeudFils != recupererIndexDoublon(indexNoeud)))) {
						indexNoeud++;
					}

					return indexNoeudParent = indexNoeud;

				}

				private int chercherFilsPlusPetit(Noeud noeudFils, int indexNoeudNouveauFils) {
					int indexNoeudSansFils = -1;
					int indexNoeud = 0;

					int indexFilsNoeud = noeudFils.getIndexFilsGauche();

					while (indexFilsNoeud != recupereIndexNoeudBin(indexNoeud)) {
						indexNoeud++;
					}

					Noeud noeudRecup = new Noeud();
					noeudRecup = this.recupererNoeud(noeudRecup, indexNoeud);
					System.out.println(noeudRecup);

					// Si pas de fils gauche
					if (noeudRecup.getIndexFilsGauche() != indexNoeudSansFils) {
						chercherFilsPlusPetit(noeudRecup, indexNoeudNouveauFils);
					}
					indexNoeudNouveauFils = noeudRecup.getIndexNoeud();

					return indexNoeudNouveauFils;

				}

				private Noeud recupererNoeud(Noeud noeudRecup, int indexNoeud) {

					try {
						this.raf.seek(indexNoeud * TAILLE_NOEUD_OCTET);

						int indexNoeudSupp = 0;
						String nomRecup = "";
						String prenomRecup = "";
						String departementRecup = "";
						String formationRecup = "";
						String anneeFormationRecup = "";
						int indexFilsGauche = 0;
						int indexFilsDroit = 0;
						int indexDoublon = 0;

						indexNoeudSupp = this.raf.readInt();

						for (int k = 0; k < TAILLE_MAX_NOM; k++) {
							nomRecup += this.raf.readChar();
						}

						for (int k = 0; k < TAILLE_MAX_PRENOM; k++) {
							prenomRecup += this.raf.readChar();
						}

						for (int k = 0; k < TAILLE_MAX_DEPARTEMENT; k++) {
							departementRecup += this.raf.readChar();
						}

						for (int k = 0; k < TAILLE_MAX_FORMATION; k++) {
							formationRecup += this.raf.readChar();
						}

						for (int k = 0; k < TAILLE_MAX_ANNEEFORMATION; k++) {
							anneeFormationRecup += this.raf.readChar();
						}

						indexFilsGauche += this.raf.readInt();
						indexFilsDroit += this.raf.readInt();
						indexDoublon += this.raf.readInt();

						// Création noeud
						noeudRecup = new Noeud(indexNoeudSupp, null, indexFilsGauche, indexFilsDroit, indexDoublon);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return noeudRecup;
				}

				private void modifierNoeudParentSansFils(Noeud noeudFils) {

					int indexNoeudSansFils = -1;

					// Chercher le noeud parent du noeud à supprimer
					int indexNoeudFils = noeudFils.getIndexNoeud();

					int indexNoeud = 0;

					while ((indexNoeudFils != recupererIndexFilsGauche(indexNoeud)
							&& (indexNoeudFils != recupererIndexFilsDroit(indexNoeud))
							&& (indexNoeudFils != recupererIndexDoublon(indexNoeud)))) {
						indexNoeud++;
					}
					if (indexNoeudFils == recupererIndexFilsGauche(indexNoeud)) {
						ecrireFilsGauche(indexNoeud, indexNoeudSansFils);
					} else if (indexNoeudFils == recupererIndexFilsDroit(indexNoeud)) {
						ecrireFilsDroit(indexNoeud, indexNoeudSansFils);
					} else {
						ecrireDoublon(indexNoeud, indexNoeudSansFils);
					}

					System.out.println(indexNoeud);

				}

				// Méthode pour chercher le noeud qui à le nom à supprimer et recupérer ce noeud
				private Noeud recupererNoeudSupp(Noeud noeudSupp, String stagiaireSupp, int indexNoeud) {

					// rehcerhce du noeud à supprimer
					while (stagiaireSupp.compareTo(recupereNomNoeudBin(indexNoeud)) != 0) {
						indexNoeud++;
					}

					// Création noeud avec lecture .Bin
					try {
						this.raf.seek(indexNoeud * TAILLE_NOEUD_OCTET);

						int indexNoeudSupp = 0;
						String nomRecup = "";
						String prenomRecup = "";
						String departementRecup = "";
						String formationRecup = "";
						String anneeFormationRecup = "";
						int indexFilsGauche = 0;
						int indexFilsDroit = 0;
						int indexDoublon = 0;

						indexNoeudSupp = this.raf.readInt();

						for (int k = 0; k < TAILLE_MAX_NOM; k++) {
							nomRecup += this.raf.readChar();
						}

						for (int k = 0; k < TAILLE_MAX_PRENOM; k++) {
							prenomRecup += this.raf.readChar();
						}

						for (int k = 0; k < TAILLE_MAX_DEPARTEMENT; k++) {
							departementRecup += this.raf.readChar();
						}

						for (int k = 0; k < TAILLE_MAX_FORMATION; k++) {
							formationRecup += this.raf.readChar();
						}

						for (int k = 0; k < TAILLE_MAX_ANNEEFORMATION; k++) {
							anneeFormationRecup += this.raf.readChar();
						}

						indexFilsGauche += this.raf.readInt();
						indexFilsDroit += this.raf.readInt();
						indexDoublon += this.raf.readInt();

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
				
		
	
		
		
		



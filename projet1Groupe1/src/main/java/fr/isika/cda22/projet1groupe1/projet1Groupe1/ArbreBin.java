package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.BufferedReader;
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
	
	public void ecrireAnneeFormationBin(int index, String anneeFormation) {
		String anneeFormationFormate = this.getAnneeFormationFormate(anneeFormation);
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			raf.seek(this.indexToOctet(index)+INDEX_DEPARTEMENT_OCTET);
			raf.writeChars(anneeFormationFormate);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Methode pour importer le fichier texte sous la forme d'un arbre binaire dans le fichier binaire de sauvegarde
		public void importAnnuaireTexte() {

			try {

				FileReader fichier = new FileReader(CHEMIN_TXT);
				BufferedReader br = new BufferedReader(fichier);

				this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
				this.raf.setLength(0);

					int cpt = 0;
					
					while (br.ready()) {
						
						
						String nom = br.readLine();
						String prenom = br.readLine();
						String departement = br.readLine();
						String formation = br.readLine();
						String annee = br.readLine();
						br.readLine();

						Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, formation, annee);

						Noeud noeud = new Noeud(-1, stagiaire, -1, -1, -1);
						
						System.out.println("cpt "+cpt);
						cpt++;
						
						this.ajouterStagiaireBin(noeud);

					}

					fichier.close();
					this.raf.close();

				} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Methode pour importer le fichier texte sous la forme d'un arbre binaire dans le fichier binaire de sauvegarde
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
		
		// Méthode qui crée la racine si le fichier est vide, et sinon ajoute le stagiaire sur fichier bin
		public void ajouterStagiaireBin(Noeud noeudAjout) {
			try {
				int indexNoeudRacine = 0;
				if (this.raf.length() == 0) { // si le fichier binaire est vide, on écrit le stagiaire
					this.ecrireNoeudBin(noeudAjout, indexNoeudRacine);
				} else {
					//this.raf.seek(TAILLE_NOEUD_OCTET);
					this.ecritureNoeudBinRecursive(noeudAjout, indexNoeudRacine);
				}

			} catch (IOException e) {
				System.out.println("EXCEPTION");
				e.printStackTrace();
			}
		}
		
//		// Méthode qui crée la racine si le fichier est vide, et sinon ajoute le stagiaire sur fichier bin
//				public void ajouterStagiaireBinV02(Stagiaire stagiaire) {
//					try {
//						Noeud noeudAjout = new Noeud(-1, stagiaire, -1,-1,-1);
//						int indexNoeudRacine = 0;
//						if (this.raf.length() == 0) { // si le fichier binaire est vide, on écrit le stagiaire
//							ecrireNoeudBin(noeudAjout, indexNoeudRacine);
//						} else {
//							//this.raf.seek(TAILLE_NOEUD_OCTET);
//							//ecritureNoeudBinRecursive(noeudAjout, indexNoeudRacine);
//						}
//
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
		
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
				raf.writeChars(stagiaire.getDepartement());
				raf.writeChars(stagiaire.getFormationFormate());
				raf.writeChars(stagiaire.getAnneeFormation());

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		// Methode recursive d'ajout de stagiaire dans le fichier bin
		public void ecritureNoeudBinRecursive(Noeud noeudAjout, int index) {
			try {
				int indexNoeud = (int) (this.raf.length() / TAILLE_NOEUD_OCTET);
				int indexParent = index;
				if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomIndex(indexParent)) < 0) { // on part a gauche

					if (recupererIndexFilsGauche(indexParent) != -1) { // il y a un fils gauche
						indexParent = recupererIndexFilsGauche(indexParent);
						ecritureNoeudBinRecursive(noeudAjout, indexParent); // on passe la methode au fils gauche
					} else { // il n'y a pas de fils gauche
						ecrireFilsGauche(indexParent, indexNoeud);
						ecrireNoeudBin(noeudAjout, indexNoeud);
					}
				} else if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomIndex(indexParent)) > 0) { // on part a Droite

					if (recupererIndexFilsDroit(indexParent) != -1) { // il y a un fils Droit
						indexParent = recupererIndexFilsDroit(indexParent);
						ecritureNoeudBinRecursive(noeudAjout, indexParent); // on passe la methode au fils Droit
					} else { // il n'y a pas de fils Droit
						ecrireFilsDroit(indexParent, indexNoeud);
						ecrireNoeudBin(noeudAjout, indexNoeud);
					}
				} else if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomIndex(indexParent)) == 0) {
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
		
		// Methode qui récupère l'attribut "nom" du noeud parent
		public String recupereNomIndex(int indexParent) {
			String nomRecup = "";
			try {
				this.raf.seek(indexParent*TAILLE_NOEUD_OCTET + INDEX_NOM_OCTET);
				for (int k = 0; k < TAILLE_MAX_NOM; k++) {
					nomRecup += raf.readChar();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			return nomRecup;
		}
		
		// Methode qui récupère l'attribut "nom" du noeud parent
				public String recuperePrenomBin(int indexParent) {
					String prenomRecup = "";
					try {
						this.raf.seek(indexParent*TAILLE_NOEUD_OCTET + INDEX_PRENOM_OCTET);
						for (int k = 0; k < TAILLE_MAX_PRENOM; k++) {
							prenomRecup += raf.readChar();
						}

					} catch (IOException e) {
						e.printStackTrace();
					}

					return prenomRecup;
				}
				
				// Methode qui récupère l'attribut "nom" du noeud parent
				public String recupereDepartementBin(int indexParent) {
					String departementRecup = "";
					try {
						this.raf.seek(indexParent*TAILLE_NOEUD_OCTET + INDEX_DEPARTEMENT_OCTET);
						for (int k = 0; k < TAILLE_MAX_DEPARTEMENT; k++) {
							departementRecup += raf.readChar();
						}

					} catch (IOException e) {
						e.printStackTrace();
					}

					return departementRecup;
				}
				
				// Methode qui récupère l'attribut "nom" du noeud parent
				public String recupereFormationBin(int indexParent) {
					String formationRecup = "";
					try {
						this.raf.seek(indexParent*TAILLE_NOEUD_OCTET + INDEX_FORMATION_OCTET);
						for (int k = 0; k < TAILLE_MAX_FORMATION; k++) {
							formationRecup += raf.readChar();
						}

					} catch (IOException e) {
						e.printStackTrace();
					}

					return formationRecup;
				}
				
				// Methode qui récupère l'attribut "nom" du noeud parent
				public String recupereAnneeFormationBin(int indexParent) {
					String anneeFormationRecup = "";
					try {
						this.raf.seek(indexParent*TAILLE_NOEUD_OCTET + INDEX_ANNEEFORMATION_OCTET);
						for (int k = 0; k < TAILLE_MAX_ANNEEFORMATION; k++) {
							anneeFormationRecup += raf.readChar();
						}

					} catch (IOException e) {
						e.printStackTrace();
					}

					return anneeFormationRecup;
				}
		
		
		// Methode qui récupère l'indexFilsGauche du noeud parent
		public int recupererIndexFilsGauche(int indexNoeud) {
			int indexParent = 0;
			try {
				this.raf.seek((indexNoeud* TAILLE_NOEUD_OCTET)+INDEX_FILS_GAUCHE_OCTET);
				indexParent += raf.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return indexParent;
		}
		
		// Methode qui récupère l'indexFilsDroit du noeud parent
		public int recupererIndexFilsDroit(int indexNoeud) {
			int indexParent = 0;
			try {
				this.raf.seek((indexNoeud* TAILLE_NOEUD_OCTET)+INDEX_FILS_DROIT_OCTET);
				indexParent += raf.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return indexParent;
		}
		
		// Methode qui récupère l'indexDoublon du noeud parent
		public int recupererIndexDoublon(int indexNoeud) {
			int indexParent = 0;
			try {
				this.raf.seek((indexNoeud* TAILLE_NOEUD_OCTET)+INDEX_DOUBLON_OCTET);
				indexParent += raf.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return indexParent;
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
		 * @return Affiche dans la console la liste des noeuds contenus dans le fichier binaire avec leurs attributs: [ index / nom / prenom / departement / formation / anneeFormation / indexFilsGauche / indexFilsDroit / indexDoublon ]
		 */
		public void afficherFichierBin() {
			try {
				System.out.println("\n******* FICHIER BIN: "+this.adresseFichierBin);
				this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
				int nbrStagiaires = (int) (this.raf.length() / (TAILLE_NOEUD_OCTET));
				// on ouvre le flux "raf" associé au fichier binaire
				
				//int nbrStagiaires = 100; // A DEFINIR PAR UNE FONCTION annuaire.getNombreStagiaires() qui calcule le nombre de noeuds dans l'annuaire;
				for (int i = 0; i < nbrStagiaires; i++) {
					// on positionne la tête de lecture en position "i * TAILLE_STAGIAIRE_OCTET"
					// car .seek() se base sur l'octet et non sur le caractère (1 letter = 2 octets / 1 int = 4 octets)
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
					
					//DEBUT DE LA LECTURE DU "raf"
					// On commence à parcourir/lire le fichier binaire selon ce principe :
					indexNoeud = this.raf.readInt();
					// 1--> de 0 à TAILLE_MAX_NOM, on sait qu'il s'agit des lettres du "nom" du stagiaire
					for (int k = 0; k < TAILLE_MAX_NOM; k++) {
						nomRecup += this.raf.readChar();
					}
					// 1--> de 0 à TAILLE_MAX_PRENOM, on sait qu'il s'agit des lettres du "prenom" du stagiaire
					for (int k = 0; k < TAILLE_MAX_PRENOM; k++) {
						prenomRecup += this.raf.readChar();
					}
					// 1--> de 0 à TAILLE_MAX_DEPARTEMENT, on sait qu'il s'agit des numeros du "département" du stagiaire
					for (int k = 0; k < TAILLE_MAX_DEPARTEMENT; k++) {
						departementRecup += this.raf.readChar();
					}
					// 1--> de 0 à TAILLE_MAX_FORMATION, on sait qu'il s'agit des lettres de la "formation" du stagiaire
					for (int k = 0; k < TAILLE_MAX_FORMATION; k++) {
						formationRecup += this.raf.readChar();
					}
					// 1--> de 0 à TAILLE_MAX_ANNEEFORMATION, on sait qu'il s'agit des lettres de l' "annee de formation" du stagiaire
					for (int k = 0; k < TAILLE_MAX_ANNEEFORMATION; k++) {
						anneeFormationRecup += this.raf.readChar();
					}
					indexFilsGauche += this.raf.readInt();
					indexFilsDroit += this.raf.readInt();
					indexDoublon += this.raf.readInt();
					
					//FIN DE LA LECTURE
					
					// On affiche les résultats
					
					System.out.println("[IndexNoeud: "+indexNoeud+"]");
					System.out.println("nom: " + nomRecup.trim()+" / " +"prenom: " + prenomRecup.trim()+" / " +"dept: " + departementRecup.trim()+" / "+"formation: " + formationRecup.trim()+" / "+"Annee: "+anneeFormationRecup.trim());
					System.out.println("["+"FG: " + indexFilsGauche+"]["+"FD: " + indexFilsDroit+"]["+"DB: " + indexDoublon+"]\n");
				}
				
				// on ferme le flux "raf"
				this.raf.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		// Methode de recherche
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
		
		// Methode de recherche
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
			Noeud noeud = new Noeud(-1, null, -1,-1,-1);
			String nom = this.recupereNomIndex(index);
			String prenom = this.recuperePrenomBin(index);
			String departement = this.recupereDepartementBin(index);
			String formation = this.recupereFormationBin(index);
			String anneeFormation = this.recupereAnneeFormationBin(index);
//			String prenom = this.recupere(index, this.raf);
//			String departement = this.recupereDepartementNoeud(index, this.raf);
//			String formation = this.recupereFormationNoeud(index, this.raf);
//			String anneeFormation = this.recupereAnneeFormationNoeud(index, this.raf);
			noeud.setCle(new Stagiaire(nom, prenom, departement, formation, anneeFormation));
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
		
		public void exportToArrayList(ArrayList<Noeud> listeNoeud){
			try {
				
				this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			    if (this.raf.length() == 0) {
			    	listeNoeud = null;
			    } else {
			    	this.exportToArrayListRecursive(listeNoeud,0);
			}
			
			this.raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void exportToArrayListRecursive(ArrayList<Noeud> listeNoeud, int index){
//			System.out.println("ID FG "+this.recupererIndexFilsGauche(index));
//			System.out.println("ID FD "+this.recupererIndexFilsDroit(index));
//			System.out.println("index "+index);
			if (this.recupererIndexFilsGauche(index) != -1) {
				index = this.recupererIndexFilsGauche(index);
				System.out.println("new index G"+index);
				this.exportToArrayListRecursive(listeNoeud,index);
			}
			listeNoeud.add(this.recupereNoeudIndex(index));
			
			if (this.recupererIndexFilsDroit(index) != -1) {
				index = this.recupererIndexFilsDroit(index);
				System.out.println("new index D"+index);
				this.exportToArrayListRecursive(listeNoeud,index);
			}
		}
		
		/*
		public ArrayList<Noeud> exportToArrayListRecursive(ArrayList<Noeud> listeNoeud, int index){
			System.out.println("ID FG "+this.recupererIndexFilsGauche(index));
			System.out.println("ID FD "+this.recupererIndexFilsDroit(index));
			System.out.println("index "+index);
			if (this.recupererIndexFilsGauche(index) != -1) {
				index = this.recupererIndexFilsGauche(index);
				listeNoeud = this.exportToArrayListRecursive(listeNoeud, index);
			} else 
			listeNoeud.add(this.recupereNoeudIndex(index));
			if (this.recupererIndexFilsDroit(index) != -1) {
				index = this.recupererIndexFilsDroit(index);
				listeNoeud = this.exportToArrayListRecursive(listeNoeud, index);
			}
			return listeNoeud;
		}
		*/
		
		
		

}

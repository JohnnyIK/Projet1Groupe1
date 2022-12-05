package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Sauvegarde extends Annuaire implements ParametreGestionnaire {

	public void recuperationFichierBinaire() {

	}

	public void sauvegardeFichierBinaire(Annuaire annuaire) {

	}

	/* TEST -------------------------------------------------------------------
	public void sauvegardeStagiaire(Stagiaire stagiaire) {

		try {
			RandomAccessFile raf = new RandomAccessFile("src/main/java/Fichier/sauvegardeStagiaire.bin", "rw");

//			for(Tarte tarte : lesTartes) {
//				String fruitLong = tarte.getFruitLong();
//				raf.writeChars(fruitLong);
//				raf.writeInt(tarte.getTaille());
//			}

			String nomLong = stagiaire.getNomLong();
			String prenomLong = stagiaire.getPrenomLong();
			raf.writeChars(nomLong);
			raf.writeChars(prenomLong);
//			raf.writeInt(tarte.getTaille());

			// je veux lire la 3e tarte
			raf.seek(0);
			String nomRecup = "";
			String prenomRecup = "";
			// int taille3 = 0;
			for (int i = 0; i < stagiaire.TAILLE_MAX_NOM; i++) {
				nomRecup += raf.readChar();
			}
			for (int i = 0; i < stagiaire.TAILLE_MAX_PRENOM; i++) {
				prenomRecup += raf.readChar();
			}

			System.out.println("nomRecup =" + nomRecup);
			System.out.println("prenomRecup =" + prenomRecup);

			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	*/
	
	public void importAnnuaireTexte() {

		//Annuaire annuaire = new Annuaire();
		
		try {

			FileReader fichier = new FileReader("src/main/java/Fichier/STAGIAIRES_10.DON");
			BufferedReader br = new BufferedReader(fichier);
			
			RandomAccessFile raf = new RandomAccessFile(CHEMIN_BIN, "rw");
			
			if (raf.length() == 0) {
				System.out.println("ERROR Call Patrick N-O-W !");
			}
			else {

			
			// if (annuaire != null) {
			// si l'annuaire est vide

			while (br.ready()) {

				String nom = br.readLine();
				String prenom = br.readLine();
				String departement = br.readLine();
				String formation = br.readLine();
				String annee = br.readLine();
				br.readLine();

				Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, formation, annee);
				
				Noeud noeud = new Noeud(stagiaire,-1,-1,-1);
				noeud.ajouterStagiaireBin(noeud,raf);

				//stagiaire.ajouterStagiaireBin(stagiaire);
				
				//annuaire.ajouter(stagiaire);

			}
			// } accolade du if
			//System.out.println(annuaire);

			fichier.close();
			raf.close();
			
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return annuaire;
		
	}
	

	/* METHODE TEXTE > ARBRE
	public Annuaire importAnnuaireTexte() {

		Annuaire annuaire = new Annuaire();
		
		try {

			FileReader fichier = new FileReader("src/main/java/Fichier/STAGIAIRES.DON");
			BufferedReader br = new BufferedReader(fichier);

			
			// if (annuaire != null) {
			// si l'annuaire est vide

			while (br.ready()) {

				String nom = br.readLine();
				String prenom = br.readLine();
				String departement = br.readLine();
				String formation = br.readLine();
				String annee = br.readLine();
				br.readLine();

				Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, formation, annee);

				annuaire.ajouter(stagiaire);

			}
			// } accolade du if
			System.out.println(annuaire);

			fichier.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return annuaire;
		
	}
	*/
	
	
	// Methode pour sauvegarder un annuaire dans un fichier binaire
	public void sauvegarderFichierBinaire() {
		//annuaire.sauvegarderFichierBinaire();
	}
	
	
	/* FONCTION OPERATIONNELE DU 03/12/2022
	// Méthode pour afficher dans la console le contenu du fichier binaire
	public void lireSauvegardeStagiaire(int nbrStagiaires) {
		try {
			// on ouvre le flux "raf" associé au fichier binaire
			RandomAccessFile raf = new RandomAccessFile("src/main/java/Fichier/sauvegardeAnnuaire.bin", "rw");

			//int nbrStagiaires = 100; // A DEFINIR PAR UNE FONCTION annuaire.getNombreStagiaires() qui calcule le nombre de noeuds dans l'annuaire;
			for (int i = 0; i < nbrStagiaires; i++) {
				// on positionne la tête de lecture en position "i * TAILLE_STAGIAIRE_OCTET"
				// car .seek() se base sur l'octet et non sur le caractère (1 letter = 2 octets / 1 int = 4 octets)
				// contrairement à .readChar() qui lui se déplace de caractère en caractère
				raf.seek(i * TAILLE_STAGIAIRE_OCTET);
				
				// on crée les variables qui vont stocker les valeurs des attributs
				String nomRecup = "";
				String prenomRecup = "";
				String departementRecup = "";
				String formationRecup = "";
				String anneeFormationRecup = "";
				
				//DEBUT DE LA LECTURE DU "raf"
				// On commence à parcourir/lire le fichier binaire selon ce principe :
				// 1--> de 0 à TAILLE_MAX_NOM, on sait qu'il s'agit des lettres du "nom" du stagiaire
				for (int k = 0; k < TAILLE_MAX_NOM; k++) {
					nomRecup += raf.readChar();
				}
				// 1--> de 0 à TAILLE_MAX_PRENOM, on sait qu'il s'agit des lettres du "prenom" du stagiaire
				for (int k = 0; k < TAILLE_MAX_PRENOM; k++) {
					prenomRecup += raf.readChar();
				}
				// 1--> de 0 à TAILLE_MAX_DEPARTEMENT, on sait qu'il s'agit des numeros du "département" du stagiaire
				for (int k = 0; k < TAILLE_MAX_DEPARTEMENT; k++) {
					departementRecup += raf.readChar();
				}
				// 1--> de 0 à TAILLE_MAX_FORMATION, on sait qu'il s'agit des lettres de la "formation" du stagiaire
				for (int k = 0; k < TAILLE_MAX_FORMATION; k++) {
					formationRecup += raf.readChar();
				}
				// 1--> de 0 à TAILLE_MAX_ANNEEFORMATION, on sait qu'il s'agit des lettres de l' "annee de formation" du stagiaire
				for (int k = 0; k < TAILLE_MAX_ANNEEFORMATION; k++) {
					anneeFormationRecup += raf.readChar();
				}
				//FIN DE LA LECTURE
				
				// On affiche les résultats
				System.out.println("nomRecup = " + nomRecup.trim());
				System.out.println("prenomRecup = " + prenomRecup.trim());
				System.out.println("departementRecup = " + departementRecup.trim());
				System.out.println("formationRecup = " + formationRecup.trim());
				System.out.println("anneeFormationRecup = " + anneeFormationRecup.trim());

			}
			
			// on ferme le flux "raf"
			raf.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	
	// Méthode pour importer un fichier de sauvegarde bin et générer un annuaire
		public Annuaire importSauvegardeBin() {
			
			Annuaire annuaire = new Annuaire();
			
			try {
				// on ouvre le flux "raf" associé au fichier binaire
				RandomAccessFile raf = new RandomAccessFile(CHEMIN_BIN, "rw");

				long tailleBinOctet = raf.length();
				System.out.println("TAILLE_STAGIAIRE_OCTET =" +TAILLE_STAGIAIRE_OCTET);
				System.out.println("longueur ="+tailleBinOctet);
				long nbrStagiaire = tailleBinOctet/TAILLE_STAGIAIRE_OCTET;
				System.out.println("nbrStagiaire ="+nbrStagiaire);
				
				long i = 0;
				
				while(i < nbrStagiaire) {
					// on positionne la tête de lecture en position "i * TAILLE_STAGIAIRE_OCTET"
					// car .seek() se base sur l'octet et non sur le caractère (1 letter = 2 octets / 1 int = 4 octets)
					// contrairement à .readChar() qui lui se déplace de caractère en caractère
					raf.seek(i * TAILLE_STAGIAIRE_OCTET);
					
					// on crée les variables qui vont stocker les valeurs des attributs du stagiaire
					String nom = "";
					String prenom = "";
					String departement = "";
					String formation = "";
					String anneeFormation = "";
					
					//DEBUT DE LA LECTURE DU "raf"
					// On commence à parcourir/lire le fichier binaire selon ce principe :
					// 1--> de 0 à TAILLE_MAX_NOM, on sait qu'il s'agit des lettres du "nom" du stagiaire
					for (int k = 0; k < TAILLE_MAX_NOM; k++) {
						nom += raf.readChar();
					}
					// 1--> de 0 à TAILLE_MAX_PRENOM, on sait qu'il s'agit des lettres du "prenom" du stagiaire
					for (int k = 0; k < TAILLE_MAX_PRENOM; k++) {
						prenom += raf.readChar();
					}
					// 1--> de 0 à TAILLE_MAX_DEPARTEMENT, on sait qu'il s'agit des numeros du "département" du stagiaire
					for (int k = 0; k < TAILLE_MAX_DEPARTEMENT; k++) {
						departement += raf.readChar();
					}
					// 1--> de 0 à TAILLE_MAX_FORMATION, on sait qu'il s'agit des lettres de la "formation" du stagiaire
					for (int k = 0; k < TAILLE_MAX_FORMATION; k++) {
						formation += raf.readChar();
					}
					// 1--> de 0 à TAILLE_MAX_ANNEEFORMATION, on sait qu'il s'agit des lettres de l' "annee de formation" du stagiaire
					for (int k = 0; k < TAILLE_MAX_ANNEEFORMATION; k++) {
						anneeFormation += raf.readChar();
					}
					//FIN DE LA LECTURE
					
					// On ajoute le stagiaire à l'annuaire
					//annuaire.ajouter(new Stagiaire(nom.trim(), prenom.trim(), departement, formation.trim(), anneeFormation));
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

//	public void ImportAnnuaire(FileReader fichier) {
//		try {
//			fichier = new FileReader("src/Fichier/Stagiaires.don");
//			BufferedReader br = new BufferedReader(fichier);
//			
//			//if (annuaire != null) {
//			//si l'annuaire est vide 
//				
//				while (br.ready()) {
//					
//					//while(!br.readLine().equals("*"){ 
//					//TANT QUE br est différent de *
//					
//					String nom = br.readLine();
//					String prenom = br.readLine();
//					int departement = Integer.parseInt(br.readLine());
//					String formation = br.readLine();
//					int annee = Integer.parseInt(br.readLine());
//					
//					Stagiaire stagiaire = new Stagiaire( nom, prenom, departement, formation, annee);
//					//ajouterStagiaire(stagiaire);
//					
//					}	
//				//} accolade du while != $
//			//} accolade du if
//			
//				
//				
//				fichier.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}







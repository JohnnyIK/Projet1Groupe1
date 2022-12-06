package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Sauvegarde extends Annuaire implements ParametreGestionnaire {


	// Methode pour importer le fichier texte sous la forme d'un arbre binaire dans le fichier binaire
	public void importAnnuaireTexte() {

		try {

			FileReader fichier = new FileReader(CHEMIN_TXT);
			BufferedReader br = new BufferedReader(fichier);

			RandomAccessFile raf = new RandomAccessFile(CHEMIN_BIN, "rw");

				int cpt = 0;
				
				while (br.ready()) {
					
					
					String nom = br.readLine();
					String prenom = br.readLine();
					String departement = br.readLine();
					String formation = br.readLine();
					String annee = br.readLine();
					br.readLine();

					Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, formation, annee);

					Noeud noeud = new Noeud(stagiaire, -1, -1, -1);
					
					System.out.println("cpt "+cpt);
					cpt++;
					
					noeud.ajouterStagiaireBin(noeud, raf);

				}

				fichier.close();
				raf.close();

			} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// Méthode pour afficher dans la console le contenu du fichier binaire
		public void lireSauvegardeStagiaire() {
			try {
				RandomAccessFile raf = new RandomAccessFile(CHEMIN_BIN, "rw");
				int nbrStagiaires = (int) (raf.length() / TAILLE_NOEUD_OCTET);
				// on ouvre le flux "raf" associé au fichier binaire
				
				//int nbrStagiaires = 100; // A DEFINIR PAR UNE FONCTION annuaire.getNombreStagiaires() qui calcule le nombre de noeuds dans l'annuaire;
				for (int i = 0; i < nbrStagiaires; i++) {
					// on positionne la tête de lecture en position "i * TAILLE_STAGIAIRE_OCTET"
					// car .seek() se base sur l'octet et non sur le caractère (1 letter = 2 octets / 1 int = 4 octets)
					// contrairement à .readChar() qui lui se déplace de caractère en caractère
					raf.seek(i * TAILLE_NOEUD_OCTET);
					
					// on crée les variables qui vont stocker les valeurs des attributs
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
					indexFilsGauche += raf.readInt();
					indexFilsDroit += raf.readInt();
					indexDoublon += raf.readInt();
					
					//FIN DE LA LECTURE
					
					// On affiche les résultats
					System.out.println("nomRecup = " + nomRecup.trim());
					System.out.println("prenomRecup = " + prenomRecup.trim());
					System.out.println("departementRecup = " + departementRecup.trim());
					System.out.println("formationRecup = " + formationRecup.trim());
					System.out.println("anneeFormationRecup = " + anneeFormationRecup.trim());
					System.out.println("indexFilsGauche = " + indexFilsGauche);
					System.out.println("indexFilsDroit = " + indexFilsDroit);
					System.out.println("doublon = " + indexDoublon);
				}
				
				// on ferme le flux "raf"
				raf.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}

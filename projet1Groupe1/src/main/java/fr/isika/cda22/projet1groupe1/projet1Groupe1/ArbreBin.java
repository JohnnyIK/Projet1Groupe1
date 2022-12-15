package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.util.*;

/**
 * Classe pour instancier des Objets Arbres Binaires retranscrits dans un fichier Bin
 *
 */
public class ArbreBin implements ParametreGestionnaire{
	
	// Attributs ----------------------------------------------------------------------------------------------------------
	private String adresseFichierBin = "";
	private RandomAccessFile raf;
	private ArrayList<String> anneeListe;

	//Constructeur --------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur permettant d'initialiser un arbre binaire avec une adresse pointant vers un fichier Bin
	 * @adresseFichierBin
	 */
	public ArbreBin(String adresseFichierBin) {
		super();
		this.adresseFichierBin = adresseFichierBin;
		this.anneeListe = new ArrayList<String>();
	}
	
	// GETTERS AND SETTERS ------------------------------------------------------------------------------------------------
	public String getAdresseFichierBin() {
		return adresseFichierBin;
	}


	public void setAdresseFichierBin(String adresseFichierBin) {
		this.adresseFichierBin = adresseFichierBin;
	}


	public RandomAccessFile getRaf() {
		return raf;
	}


	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}


	public ArrayList<String> getAnneeListe() {
		return anneeListe;
	}


	public void setAnneeListe(ArrayList<String> anneeListe) {
		this.anneeListe = anneeListe;
	}

	public ArrayList<String> getAnneeArrayListe(){
		return this.anneeListe;
	}
	
	// METHODES ----------------------------------------------------------------------------------------------------------------------------
	/**
	 * Creation d'un "nomFormate" de TAILLE_MAX_NOM pour l'écriture dans le fichier binaire
	 * @param nom Le nom a formater
	 * @return Le nom formate
	 */
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
	
	/**
	 * Creation d'un "prenomFormate" de TAILLE_MAX_NOM pour l'écriture dans le fichier binaire
	 * @param prenom Le prenom a formater
	 * @return Le prenom formate
	 */
	public String getPrenomFormate(String prenom) {
		// Dans tous les cas, on ne va pas tronquer le nom / prenom car sinon on perd
		// l'information.
		// Donc on rajoute " " après chaque nom/prenom pour atteindre une string nomLong
		// de taille TAILLE_MAX_NOMPRENOM
		String prenomFormate = prenom;
		for (int i = prenom.length(); i < TAILLE_MAX_PRENOM; i++) {
			prenomFormate += " ";
		}
		return prenomFormate;
	}

	/**
	 * Creation d'un "departementFormate" de TAILLE_MAX_NOM pour l'écriture dans le fichier binaire
	 * @param departement Le departement a formater
	 * @return Le departement formate
	 */
	public String getDepartementFormate(String departement) {
		// Dans tous les cas, on ne va pas tronquer le departement car sinon on perd
		// l'information.
		// Donc on rajoute " " après chaque departement pour atteindre une string
		// departementLong
		// de taille TAILLE_MAX_DEPARTEMENT
		String departementFormate = departement;
		for (int i = departement.length(); i < TAILLE_MAX_DEPARTEMENT; i++) {
			departementFormate += " ";
		}
		return departementFormate;
	}
	
	/**
	 * Creation d'un "formationFormate" de TAILLE_MAX_NOM pour l'écriture dans le fichier binaire
	 * @param formation La formation a formater
	 * @return La formation formate
	 */
	public String getFormationFormate(String formation) {
		// Dans tous les cas, on ne va pas tronquer le nom de la formation car sinon on
		// perd
		// l'information.
		// Donc on rajoute " " après chaque nom de formation pour atteindre une string
		// formationLong
		// de taille TAILLE_MAX_FORMATION
		String formationFormate = formation;
		for (int i = formation.length(); i < TAILLE_MAX_FORMATION; i++) {
			formationFormate += " ";
		}
		return formationFormate;
	}

	/**
	 * Creation d'un "anneeFormate" de TAILLE_MAX_NOM pour l'écriture dans le fichier binaire
	 * @param anneeFormation l'annee a formater
	 * @return L'annee formate
	 */
	public String getAnneeFormationFormate(String anneeFormation) {
		// Dans tous les cas, on ne va pas tronquer l'annee car sinon on perd
		// l'information.
		// Donc on rajoute " " après chaque annee pour atteindre une string
		// anneeLong
		// de taille TAILLE_MAX_ANNEEFORMATION
		String anneeFormate = anneeFormation;
		for (int i = anneeFormation.length(); i < TAILLE_MAX_ANNEEFORMATION; i++) {
			anneeFormate += " ";
		}
		return anneeFormate;
	}
	
	/**
	 * Renvoie le nombre d'octets correspondants a l'index en argument
	 * @param index l'index a convertir en octet
	 * @return le nombre d'octets de l'index
	 */
	private int indexToOctet (int index) {
		int indexToOctet = index * TAILLE_NOEUD_OCTET;
		return indexToOctet;
	}
	
	/**
	 * Ecrit en binaire l'index du noeud dans le fichier binaire
	 * @param indexNoeud l'index du noeud a affecter la valeur
	 * @param index l'index a ecrire
	 */
	private void ecrireIndexNoeudBin(int indexNoeud, int index) {
		try {
			raf.seek(this.indexToOctet(indexNoeud));
			raf.writeInt(index);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit en binaire le nom du noeud dans le fichier binaire
	 * @param indexNoeud l'index du noeud a affecter la valeur
	 * @param nom le nom a ecrire
	 */
	private void ecrireNomBin(int indexNoeud, String nom) {
		String nomFormate = this.getNomFormate(nom);
		try {
			raf.seek(this.indexToOctet(indexNoeud)+INDEX_NOM_OCTET);
			raf.writeChars(nomFormate);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit en binaire le nom du noeud dans le fichier binaire (open/close raf)
	 * @param indexNoeud l'index du noeud a affecter la valeur
	 * @param nom le nom a ecrire
	 */
	public void ecrireNomBinaire(int indexNoeud, String nom) {
		nom = this.nomCheckAndUpdate(nom);
		String nomFormate = this.getNomFormate(nom);
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			raf.seek(this.indexToOctet(indexNoeud)+INDEX_NOM_OCTET);
			raf.writeChars(nomFormate);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit en binaire le prenom du noeud dans le fichier binaire
	 * @param indexNoeud l'index du noeud a affecter la valeur
	 * @param prenom le prenom a ecrire
	 */
	private void ecrirePrenomBin(int indexNoeud, String prenom) {
		String prenomFormate = this.getPrenomFormate(prenom);
		try {
			raf.seek(this.indexToOctet(indexNoeud)+INDEX_PRENOM_OCTET);
			raf.writeChars(prenomFormate);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit en binaire le prenom du noeud dans le fichier binaire (open/close raf)
	 * @param indexNoeud l'index du noeud a affecter la valeur
	 * @param prenom le prenom a ecrire
	 */
	public void ecrirePrenomBinaire(int indexNoeud, String prenom) {
		String prenomFormate = this.getPrenomFormate(prenom);
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			raf.seek(this.indexToOctet(indexNoeud)+INDEX_PRENOM_OCTET);
			raf.writeChars(prenomFormate);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit en binaire le departement du noeud dans le fichier binaire
	 * @param indexNoeud l'index du noeud a affecter la valeur
	 * @param departement le departement a ecrire
	 */
	private void ecrireDepartementBin(int indexNoeud, String departement) {
		String departementFormate = this.getDepartementFormate(departement);
		try {
			raf.seek(this.indexToOctet(indexNoeud)+INDEX_DEPARTEMENT_OCTET);
			raf.writeChars(departementFormate);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit en binaire le departement du noeud dans le fichier binaire (open/close raf)
	 * @param indexNoeud l'index du noeud a affecter la valeur
	 * @param departement le departement a ecrire
	 */
	public void ecrireDepartementBinaire(int indexNoeud, String departement) {
		String departementFormate = this.getDepartementFormate(departement);
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			raf.seek(this.indexToOctet(indexNoeud)+INDEX_DEPARTEMENT_OCTET);
			raf.writeChars(departementFormate);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit en binaire la formation du noeud dans le fichier binaire
	 * @param indexNoeud l'index du noeud a affecter la valeur
	 * @param formation la formation a ecrire
	 */
	private void ecrireFormationBin(int indexNoeud, String formation) {
		String formationFormate = this.getFormationFormate(formation);
		try {
			raf.seek(this.indexToOctet(indexNoeud)+INDEX_FORMATION_OCTET);
			raf.writeChars(formationFormate);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 /**
	  * Ecrit en binaire la formation du noeud dans le fichier binaire (open/close raf)
	  * @param indexNoeud l'index du noeud a affecter la valeur
	  * @param formation la formation a ecrire
	  */
	public void ecrireFormationBinaire(int indexNoeud, String formation) {
		String formationFormate = this.getFormationFormate(formation);
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			raf.seek(this.indexToOctet(indexNoeud)+INDEX_FORMATION_OCTET);
			raf.writeChars(formationFormate);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit en binaire l'annee de formation du noeud dans le fichier binaire
	 * @param indexNoeud l'index du noeud a affecter la valeur
	 * @param anneeFormation l'annee de formation a ecrire
	 */
	private void ecrireAnneeFormationBin(int indexNoeud, String anneeFormation) {
		String anneeFormationFormate = this.getAnneeFormationFormate(anneeFormation);
		try {
			raf.seek(this.indexToOctet(indexNoeud)+INDEX_ANNEEFORMATION_OCTET);
			raf.writeChars(anneeFormationFormate);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit en binaire l'annee de formation du noeud dans le fichier binaire (open/close raf)
	 * @param indexNoeud l'index du noeud a affecter la valeur
	 * @param anneeFormation l'annee de formation a ecrire
	 */
	public void ecrireAnneeFormationBinaire(int indexNoeud, String anneeFormation) {
		anneeFormation = this.anneeFormationCheckAndUpdate(anneeFormation);
		String anneeFormationFormate = this.getAnneeFormationFormate(anneeFormation);
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			raf.seek(this.indexToOctet(indexNoeud)+INDEX_ANNEEFORMATION_OCTET);
			raf.writeChars(anneeFormationFormate);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit en binaire l'indexFilsGauche du noeud dans le fichier binaire
	 * @param indexNoeud l'index du noeud a affecter
	 * @param indexFilsGauche l'indexFilsGauche a ecrire
	 */
	private void ecrireIndexFilsGaucheBin(int indexNoeud, int indexFilsGauche) {
		try {
			raf.seek(this.indexToOctet(indexNoeud)+INDEX_FILS_GAUCHE_OCTET);
			raf.writeInt(indexFilsGauche);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit en binaire l'indexFilsDroit du noeud dans le fichier binaire
	 * @param indexNoeud l'index du noeud a affecter
	 * @param indexFilsGauche l'indexFilsDroit a ecrire
	 */
	private void ecrireIndexFilsDroitBin(int indexNoeud, int indexFilsDroit) {
		try {
			raf.seek(this.indexToOctet(indexNoeud)+INDEX_FILS_DROIT_OCTET);
			raf.writeInt(indexFilsDroit);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit en binaire l'indexDoublon du noeud dans le fichier binaire
	 * @param indexNoeud l'index du noeud a affecter
	 * @param indexFilsGauche l'indexDoublon a ecrire
	 */
	private void ecrireIndexDoublonBin(int indexNoeud, int indexDoublon) {
		try {
			raf.seek(this.indexToOctet(indexNoeud)+INDEX_DOUBLON_OCTET);
			raf.writeInt(indexDoublon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retourne le nombre de noeud (et donc de stagiaires) du fichier binaire 
	 * @return le nombre de stagiaires dans l'arbre binaire
	 */
	public int nbrStagiaires() {
		int nbrNoeuds = 0;
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			nbrNoeuds = (int) this.raf.length()/TAILLE_NOEUD_OCTET;
			this.raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return nbrNoeuds;
	}
	
	/**
	 * Teste si le string est alphanumerique
	 * @param s
	 * @return true si le string est alphanumerique
	 */
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
	
	/**
	 * Teste si le string est seulement numerique
	 * @param s
	 * @return true si le string est seulement numerique
	 */
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
	
	/**
	 * Teste si le texte est seulement alphabetique
	 * @param s
	 * @return true true si le texte est seulement alphabetique
	 */
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
	
	/**
	 * Teste si le departement ne contient que des lettres et des chiffres
	 * @param s
	 * @return true si le departement ne contient que des lettres et des chiffres
	 */
	public Boolean departementIsCorrect(String s) {
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if ((c != 'A' && c != 'B') &&
                    !(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }
	 
	/**
	 * Teste si le departement est un departement francais
	 * @param departement le departement a tester
	 * @return true si le departement est un departement francais
	 */
	public Boolean departementCheck(String departement) {
		if (!this.departementIsCorrect(departement)) {
			return false;
		} else if (departement.isBlank() || departement.isEmpty() || departement.contains(" ")){
			return false;
		} else if (departement.length() > TAILLE_MAX_DEPARTEMENT) {
			return false;
		} else if (departement.equals("2A") || departement.equals("2B")){
			return true;
		} else if (Integer.parseInt(departement) < 0) {
			return false;
		} else if (Integer.parseInt(departement) == 971 || Integer.parseInt(departement) == 972 || Integer.parseInt(departement) == 973 || Integer.parseInt(departement) == 974 || Integer.parseInt(departement) == 976) {
			return true;
		} else if (Integer.parseInt(departement) > 95) {
			return false;	
		} else {
			return true;
		}
	}
	
	/**
	 * Teste si la formation ne contient que des lettres et des chiffres
	 * 
	 * @param s
	 * @return true si le departement ne contient que des lettres et des chiffres
	 */
	public Boolean formationCheck(String s) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!(c >= 'A' && c <= 'Z') && !(c == ' ') && !(c == '-') && !(c >= '0' && c <= '9')
					&& !(c >= 'a' && c <= 'z')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Teste si l'année est une annee entre cette annee-100 et cette annee
	 * @param anneeFormation l'annee a tester
	 * @return true si l'année est une annee entre cette annee-100 et cette annee
	 */
	public Boolean anneeFormationCheck(String anneeFormation) {
		Date date = new Date();
		int year = 2022;
		System.out.println("YEAR : " + year);
		if (!isNumeric(anneeFormation)) {
			return false;
		} else if (anneeFormation.length() != TAILLE_MAX_ANNEEFORMATION) {
			return false;
		} else if (Integer.parseInt(anneeFormation) < year - 100 || Integer.parseInt(anneeFormation) > year) {
			return false;
		} else {
			return true;
		}
	}
	
	
	/**
	 * Genere une string aleatoire
	 * @return une string aleatoire de 10 caracteres
	 */
	public String randDomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}
	
	/**
	 * Verifie le nom et le formate pour l'affichage
	 * @param nom le nom a verifier
	 * @return le nom formate pour l'affichage
	 */
	public String nomCheckAndUpdate(String nom) {
		if (nom.isBlank() || nom.isEmpty() || !textIsCorrect(nom)) {
			nom = "XXXXXXXXX";
		}
		if (nom.length()>TAILLE_MAX_NOM) {
			nom = nom.substring(0, TAILLE_MAX_NOM);
		}
		nom = nom.toUpperCase();
		return nom.trim() ;
	}
	
	/**
	 * Verifie le prenom et le formate pour l'affichage
	 * @param prenom le prenom a verifier
	 * @return le prenom formate pour l'affichage
	 */
	public String prenomCheckAndUpdate(String prenom) {
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
	
	/**
	 * Verifie le departement et le formate pour l'affichage
	 * @param departement le departement a verifier
	 * @return le departement formate pour l'affichage
	 */
	public String departementCheckAndUpdate(String departement) {
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
	
	/**
	 * Verifie la formation et la formate pour l'affichage
	 * @param formation la formation a verifier
	 * @return la formation formate pour l'affichage
	 */
	public String formationCheckAndUpdate(String formation) {
		if (!formationCheck(formation)) {
			return "XXX";
		}
		if (formation.length()>TAILLE_MAX_FORMATION) {
			formation = formation.substring(0, TAILLE_MAX_FORMATION );
		}
		formation = formation.toUpperCase();
		return formation.strip();
	}
	
	/**
	 * Verifie l'annee de formation et la formate pour l'affichage
	 * @param anneeFormation l'annee de formation a verifier
	 * @return l'annee de formation formate pour l'affichage
	 */
	public String anneeFormationCheckAndUpdate(String anneeFormation) {
		if (anneeFormation.length() != TAILLE_MAX_ANNEEFORMATION) {
			return "0000";
		}
		if (!isNumeric(anneeFormation)) {
			return "0000";
		}
		return anneeFormation.strip();
	}

	/**
	 * Ajoute une annee si elle n'est pas deja presente dans l'arbre
	 * @param annee l'annee a ajouter
	 */
	private void anneeAddListe(String annee){
		Boolean anneePresente = false;
		if (this.anneeListe.size() != 0) {
			for (String anneeEnregistrees : this.anneeListe) {
				if (anneeEnregistrees.equals(annee)) {
					anneePresente = true;
				}
			}
			if (anneePresente == false) {
				this.anneeListe.add(annee);
				Collections.sort(anneeListe);
			}
		} else {
			this.anneeListe.add(annee);
		}
	}
	
	/**
	 * Affiche la liste des annees presentes dans l'arbre dans la console
	 */
	public void afficheAnneeListeConsole(){
		if (this.anneeListe != null) {
			System.out.println("LISTE");
			for (String anneeEnregistrees : this.anneeListe) {
				System.out.println("Annee liste : "+anneeEnregistrees);
			}
		} else {
			System.out.println("Annee liste vide");
		}
	}
	
	/**
	 * Importe un fichier texte dans le fichier Bin sous la forme d'un arbre binaire
	 */
	public void importAnnuaireTexte(String cheminTXT) {

		try {

			FileReader fichier = new FileReader(cheminTXT);
			BufferedReader br = new BufferedReader(fichier);

			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			this.raf.setLength(0);

			int cpt = 0;

			while (br.ready()) {

				String nom = br.readLine();
				nom = this.nomCheckAndUpdate(nom);
				String prenom = br.readLine();
				prenom = this.prenomCheckAndUpdate(prenom);
				String departement = br.readLine();
				departement = this.departementCheckAndUpdate(departement);
				String formation = br.readLine();
				formation = this.formationCheckAndUpdate(formation);
				String anneeFormation = br.readLine();
				anneeFormation = this.anneeFormationCheckAndUpdate(anneeFormation);
				br.readLine();

				Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, formation, anneeFormation);

				Noeud noeud = new Noeud(-1, stagiaire, -1, -1, -1);

				cpt++;

				this.ajouterStagiaireBin(noeud);
				

			}

			fichier.close();
			this.raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Importe un fichier texte dans le fichier Bin sous la forme d'un arbre binaire
	 */
	public void importAnnuaireTexte() {

		try {

			FileReader fichier = new FileReader(CHEMIN_TXT);
			BufferedReader br = new BufferedReader(fichier);

			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			this.raf.setLength(0);

			int cpt = 0;

			while (br.ready()) {

				String nom = br.readLine();
				nom = this.nomCheckAndUpdate(nom);
				String prenom = br.readLine();
				prenom = this.prenomCheckAndUpdate(prenom);
				String departement = br.readLine();
				departement = this.departementCheckAndUpdate(departement);
				String formation = br.readLine();
				formation = this.formationCheckAndUpdate(formation);
				String anneeFormation = br.readLine();
				anneeFormation = this.anneeFormationCheckAndUpdate(anneeFormation);
				br.readLine();

				Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, formation, anneeFormation);

				Noeud noeud = new Noeud(-1, stagiaire, -1, -1, -1);

				cpt++;

				this.ajouterStagiaireBin(noeud);
				

			}

			fichier.close();
			this.raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode pour modifier le nom situe au noeud a l'index indexNoeud
	 * 
	 * @param index
	 * @param nom
	 */
	public void modifierNom(int indexNoeud, String nom) {
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");

			Noeud noeud = new Noeud();
			noeud = this.recupereNoeudBin(indexNoeud);
			noeud.getCle().setNom(nom);
			noeud.supprimerStagiaireNoeud(indexNoeud);
			this.ajouterStagiaireBin(noeud);

			this.raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// WIP Tests
	/*
	public void telechargerFichier(String FILE_URL, String FILE_NAME) {
		//URL url1 = new URL("https://www.javatpoint.com/");
		//System.out.println("export");
		//Media media = new Media("file:///D:/ISIKA/CDA22/Projet1Groupe1/projet1Groupe1/src/main/java/Fichier/AnnuaireImpression.txt");
		
		
		try (
		
				BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
		} catch (IOException e) {
			// handle exception
		}
		
	}
		//telechargerFichier("D:/ISIKA/CDA22/Projet1Groupe1/projet1Groupe1/src/main/java/Fichier", "AnnuaireImpression.txt");
			//telechargerFichier("D:/ISIKA/CDA22/Projet1Groupe1/projet1Groupe1/src/main/java/Fichier", "AnnuaireImpression.txt");
	
	
	public void downloadTest() {
        String url = "D:/ISIKA/CDA22/Projet1Groupe1/projet1Groupe1/src/main/java/Fichier/AnnuaireImpression.txt";
        
        try {
            downloadUsingNIO(url, "/src/main/java/Fichier/AnnuaireImpression02.txt");
            
            downloadUsingStream(url, "/src/main/java/Fichier/AnnuaireImpression03.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
   

    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }

    private static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }
     */
	
	/**
	 * Ecrit la liste des stagiaires dans un fichier texte
	 * @param listeStagiaire la liste des stagiaires de l'arbre sous la forme d'une liste de noeuds
	 */
	public void ecrireFichierTxt(ArrayList<Noeud> listeStagiaire, String cheminFichier) {
		
		int nbStagiaire = listeStagiaire.size();
		System.out.println("lancement creation fichier texte");
		
		try {
			/*
			String newChemin = "";
			for (int i = 0; i < cheminFichier.length(); i++){
				//newChemin = cheminFichier.replace("\","/");"
				newChemin = cheminFichier.
			}
			*/
			
			//FileWriter fw = new FileWriter(CHEMIN_TXT_IMPRESSION,false);
			FileWriter fw = new FileWriter(cheminFichier+"/AnnuaireImpression.txt",false);
			
			fw.write("Patrick School: ");
			fw.write("LISTE DES STAGIAIRES\n\n");
			
			fw.write("Nombre de stagiaire : " + nbStagiaire + "\n\n");
			
			int cpt = 1;
			
			fw.write("\t\t\t\t" + this.getNomFormate("Nom"));
			fw.write(this.getPrenomFormate("Prénom"));
			fw.write(this.getDepartementFormate("Département")+ "\t\t");
			fw.write(this.getFormationFormate("Formation"));
			fw.write(this.getAnneeFormationFormate("Année de la formation") + "\n");
			
			for (Noeud noeud : listeStagiaire) {				
				
				fw.write("Stagiaire " + cpt + "\n");
				
				fw.write("\t\t\t\t" + noeud.getCle().getNomFormate());
				fw.write(noeud.getCle().getPrenomFormate());
				fw.write(noeud.getCle().getDepartementFormate() + "\t\t\t\t");
				fw.write(noeud.getCle().getFormationFormate());
				fw.write(noeud.getCle().getAnneeFormationFormate() + "\n");
				
				
				cpt++;
				
				}
			
			fw.close();
			/*
			File file = new File(CHEMIN_TXT_IMPRESSION);
			FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(file);
            fileChooser.showSaveDialog(null)
			//file.getAbsolutePath();
			
			 */
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * Ajouter un stagiaire dans l'arbre binaire
	 * @param stagiaire le stagiaire à ajouter
	 */
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

	/**
	 * Methode interne pour ajouter un noeud dans l'arbre binaire (le raf est deja ouvert dans la methode parente)
	 * >> cree la racine si le fichier est vide, et sinon ajoute le stagiaire sur fichier bin
	 * @param noeudAjout le noeud a ajouter dans l'arbre binaire
	 */
	private void ajouterStagiaireBin(Noeud noeudAjout) {
		try {
			int indexNoeudRacine = 0;
			if (this.raf.length() == 0) { // si le fichier binaire est vide, on écrit le stagiaire
				this.ecrireNoeudBin(noeudAjout, indexNoeudRacine);
			} else {
				this.ecritureNoeudBinRecursive(noeudAjout, indexNoeudRacine);
			}
			this.anneeAddListe(noeudAjout.getCle().getAnneeFormation());
			

		} catch (IOException e) {
			System.out.println("EXCEPTION");
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode externe pour ajouter un noeud dans l'arbre binaire (ouvre et ferme un RAF)
	 * >> cree la racine si le fichier est vide, et sinon ajoute le stagiaire sur fichier bin
	 * @param noeudAjout le noeud a ajouter dans l'arbre binaire
	 */
	public void ajouterStagiaireBin(Stagiaire stagiaire) {
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			int indexNoeudRacine = 0;
			Noeud noeudAjout = new Noeud(stagiaire);
			if (this.raf.length() == 0) { // si le fichier binaire est vide, on écrit le stagiaire
				this.ecrireNoeudBin(noeudAjout, indexNoeudRacine);
			} else {
				this.ecritureNoeudBinRecursive(noeudAjout, indexNoeudRacine);
			}
			this.anneeAddListe(noeudAjout.getCle().getAnneeFormation());
			raf.close();

		} catch (IOException e) {
			System.out.println("EXCEPTION");
			e.printStackTrace();
		}
	}
		
	/**
	 * Methode pour ecrire le noeud stagiaire dans le fichier binaire à un index donne
	 * @param noeudAjout le noeud à ajouter
	 * @param indexNoeud l'index ou ajouter le noeud
	 */
	private void ecrireNoeudBin(Noeud noeudAjout, int indexNoeud) {
		
		this.ecrireIndexNoeudBin(indexNoeud,indexNoeud);
		this.ecrireNomBin(indexNoeud,noeudAjout.getCle().getNom());
		this.ecrirePrenomBin(indexNoeud,noeudAjout.getCle().getPrenom());
		this.ecrireDepartementBin(indexNoeud,noeudAjout.getCle().getDepartement());
		this.ecrireFormationBin(indexNoeud,noeudAjout.getCle().getFormation());
		this.ecrireAnneeFormationBin(indexNoeud,noeudAjout.getCle().getAnneeFormation());
		this.ecrireIndexFilsGaucheBin(indexNoeud, noeudAjout.getIndexFilsGauche());
		this.ecrireIndexFilsDroitBin(indexNoeud, noeudAjout.getIndexFilsDroit());
		this.ecrireIndexDoublonBin(indexNoeud, noeudAjout.getIndexDoublon());

	}
	
	/**
	 * Methode pour ecrire le stagiaire dans le fichier binaire à un index donne
	 * @param noeudAjout le noeud à ajouter
	 * @param indexNoeud l'index ou ajouter le noeud
	 */
	private void ecrireStagiaireBin(Noeud noeudAjout, int indexNoeud) {
		
		this.ecrireIndexNoeudBin(indexNoeud,indexNoeud);
		this.ecrireNomBin(indexNoeud,noeudAjout.getCle().getNom());
		this.ecrirePrenomBin(indexNoeud,noeudAjout.getCle().getPrenom());
		this.ecrireDepartementBin(indexNoeud,noeudAjout.getCle().getDepartement());
		this.ecrireFormationBin(indexNoeud,noeudAjout.getCle().getFormation());
		this.ecrireAnneeFormationBin(indexNoeud,noeudAjout.getCle().getAnneeFormation());
	}
		
	/**
	 * (Methode optionnelle)
	 * Ecrit les attributs du stagiaire dans le fichier binaire
	 * @param stagiaire
	 */
	private void ecrireAttributsStagiaireBin(Stagiaire stagiaire) {
		try {

			String nomLong = stagiaire.getNomFormate();
			String prenomLong = stagiaire.getPrenomFormate();
			this.raf.writeChars(nomLong);
			this.raf.writeChars(prenomLong);
			this.raf.writeChars(stagiaire.getDepartementFormate());
			this.raf.writeChars(stagiaire.getFormationFormate());
			this.raf.writeChars(stagiaire.getAnneeFormationFormate());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Methode recursive interne pour ajouter un noeud dans l'arbre binaire
	 * @param noeudAjout le noeud à ajouter
	 * @param indexNoeud l'index ou ajouter le noeud
	 */
	public void ecritureNoeudBinRecursive(Noeud noeudAjout, int index) {
		try {
			int indexNoeudFinFichierBin = (int) (this.raf.length() / TAILLE_NOEUD_OCTET);
			int indexParent = index;
			if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomFormateNoeudBin(indexParent)) < 0) {
				// on part a gauche
				if (recupererIndexFilsGaucheBin(indexParent) != -1) {
					// il y a un fils gauche
					indexParent = recupererIndexFilsGaucheBin(indexParent);
					// on passe la methode au fils gauche
					ecritureNoeudBinRecursive(noeudAjout, indexParent);
				} else { // il n'y a pas de fils gauche
					ecrireFilsGaucheBin(indexParent, indexNoeudFinFichierBin);
					ecrireNoeudBin(noeudAjout, indexNoeudFinFichierBin);
				}
			} else if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomFormateNoeudBin(indexParent)) > 0) {
				// on part a Droite

				if (recupererIndexFilsDroitBin(indexParent) != -1) {
					// il y a un fils Droit
					indexParent = recupererIndexFilsDroitBin(indexParent);
					// on passe la methode au fils Droit
					ecritureNoeudBinRecursive(noeudAjout, indexParent);
				} else {
					// il n'y a pas de fils Droit
					ecrireFilsDroitBin(indexParent, indexNoeudFinFichierBin);
					ecrireNoeudBin(noeudAjout, indexNoeudFinFichierBin);
				}
			} else if (noeudAjout.getCle().getNomFormate().compareTo(recupereNomFormateNoeudBin(indexParent)) == 0) {
				if (recupererIndexDoublonBin(indexParent) != -1) {
					indexParent = recupererIndexDoublonBin(indexParent);
					ecritureNoeudBinRecursive(noeudAjout, indexParent);
				} else {
					ecrireDoublonBin(indexParent, indexNoeudFinFichierBin);
					ecrireNoeudBin(noeudAjout, indexNoeudFinFichierBin);
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
			this.raf.seek(index * TAILLE_NOEUD_OCTET + INDEX_NOM_OCTET);
			for (int k = 0; k < TAILLE_MAX_NOM; k++) {
				nomFormate += raf.readChar();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return nomFormate;
	}

	/**
	 * Recupere l'attribut "nom" (en version non-formate) du noeud dans le fichier
	 * Bin
	 * @param index Index du noeud
	 * @param raf   Le RandomAccessFile du fichier bin
	 * @return String nom
	 */
	public String recupereNomNoeudBin(int index) {
		String nomFormate = this.recupereNomFormateNoeudBin(index);
		return nomFormate.trim();
	}

	/**
	 * Recupere l'attribut "prenom" (en version formate) du noeud dans le fichier
	 * Bin
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
	 * @param index Index du noeud
	 * @return String departement
	 */
	public String recupereDepartementNoeudBin(int index) {
		String departementFormate = recupereDepartementFormateNoeudBin(index);
		return departementFormate.trim();
	}

	/**
	 * Recupere l'attribut "formation" (en version formate) du noeud dans le fichier
	 * Bin
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
	public int recupererIndexFilsGaucheBin(int indexNoeud) {
		int indexFilsGauche = 0;
		try {
			this.raf.seek((indexNoeud * TAILLE_NOEUD_OCTET) + INDEX_FILS_GAUCHE_OCTET);
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
	public int recupererIndexFilsDroitBin(int indexNoeud) {
		int indexFilsDroit = 0;
		try {
			this.raf.seek((indexNoeud * TAILLE_NOEUD_OCTET) + INDEX_FILS_DROIT_OCTET);
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
	public int recupererIndexDoublonBin(int indexNoeud) {
		int indexDoublon = 0;
		try {
			this.raf.seek((indexNoeud * TAILLE_NOEUD_OCTET) + INDEX_DOUBLON_OCTET);
			indexDoublon += raf.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return indexDoublon;
	}
	
	/**
	 * Retourne un noeud de l'index "indexNoeud" du fichier Bin
	 * @param indexNoeud Index du noeud
	 * @return Le noeud avec les valeurs (index, stagiaire, indexFilsGauche, indexFilsDroit, indexDoublon)
	 */
	private Noeud recupereNoeudBin (int indexNoeud) {
		Noeud noeud = new Noeud();
		noeud.setIndexNoeud(indexNoeud);
		String nom = this.recupereNomNoeudBin(indexNoeud);
		String prenom = this.recuperePrenomNoeudBin(indexNoeud);
		String departement = this.recupereDepartementNoeudBin(indexNoeud);
		String formation = this.recupereFormationNoeudBin(indexNoeud);
		String anneeFormation = this.recupereAnneeFormationNoeudBin(indexNoeud);
		noeud.setCle(new Stagiaire(nom, prenom, departement, formation, anneeFormation));
		noeud.setIndexFilsGauche(this.recupererIndexFilsGaucheBin(indexNoeud));
		noeud.setIndexFilsDroit(this.recupererIndexFilsDroitBin(indexNoeud));
		noeud.setIndexDoublon(this.recupererIndexDoublonBin(indexNoeud));
		return noeud;
	}
	
	/**
	 * (Obsolete) Retourne un noeud de l'index "indexNoeud" du fichier Bin du RandomAccessFile
	 * @param indexNoeud Index du noeud
	 * @raf le flux RandomAccessFile raf
	 * @return Le noeud avec les valeurs (index, stagiaire, indexFilsGauche, indexFilsDroit, indexDoublon)
	 */
	private Noeud recupereNoeudIndex (int indexNoeud, RandomAccessFile raf) {
		Noeud noeud = new Noeud(-1, null, -1,-1,-1);
		String nom = noeud.recupereNomIndex(indexNoeud, raf);
		String prenom = noeud.recuperePrenomNoeud(indexNoeud, raf);
		String departement = noeud.recupereDepartementNoeud(indexNoeud, raf);
		String formation = noeud.recupereFormationNoeud(indexNoeud, raf);
		String anneeFormation = noeud.recupereAnneeFormationNoeud(indexNoeud, raf);
		noeud.setCle(new Stagiaire(nom, prenom, departement, formation, anneeFormation));
		return noeud;
	}
		
	/**
	 * Methode qui modifie l'indexFilsGauche du noeud parent en lui attribuant la valeur indexNoeud
	 * @param indexParent
	 * @param indexNoeud
	 */
	private void ecrireFilsGaucheBin(int indexParent, int indexNoeud) {
		try {
			this.raf.seek((indexParent * TAILLE_NOEUD_OCTET) + INDEX_FILS_GAUCHE_OCTET);
			this.raf.writeInt(indexNoeud);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode qui modifie l'indexFilsDroit du noeud parent en lui attribuant la valeur indexNoeud
	 * @param indexParent
	 * @param indexNoeud
	 */
	private void ecrireFilsDroitBin(int indexParent, int indexNoeud) {
		try {
			this.raf.seek((indexParent * TAILLE_NOEUD_OCTET) + INDEX_FILS_DROIT_OCTET);
			this.raf.writeInt(indexNoeud);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode qui modifie l'indexDoublon du noeud parent en lui attribuant la valeur indexNoeud
	 * @param indexParent
	 * @param indexNoeud
	 */
	private void ecrireDoublonBin(int indexParent, int indexNoeud) {
		try {
			this.raf.seek((indexParent * TAILLE_NOEUD_OCTET) + INDEX_DOUBLON_OCTET);
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
	 * RechercherMulticritere : Methode principale. Cree une ArrayList contenant les
	 * index de noeuds recherches (dont les criteres sont dans l'objet
	 * 
	 * @param recherche Objet de classe RechercheMulticritere contenant les filtres
	 *                  de recherche
	 * @return une ArrayList des index des noeuds recherches
	 */
	public ArrayList<Integer> rechercheMulticriteres(RechercheMulticritere recherche) {
		ArrayList<Integer> resultatRecherche = new ArrayList<Integer>();
		try {
			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");

			if (this.raf.length() == 0) {
				System.out.println("ERROR >> Recherche impossible dans un fichier vide");
			} else {
				resultatRecherche = this.rechercheMulticriteresRecursive(recherche);
			}

			this.raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultatRecherche;

	}

	/**
	 * Methode secondaire pour la RechercherMulticritere : Methode recursive de
	 * recherche des index des noeuds et de leur écriture dans une ArrayList
	 * 
	 * @param recherche Objet de classe RechercheMulticritere contenant les filtres
	 *                  de recherche
	 * @return une ArrayList des index des noeuds recherches
	 */
	private ArrayList<Integer> rechercheMulticriteresRecursive(RechercheMulticritere recherche) {
		ArrayList<Integer> resultatRecherche = new ArrayList<Integer>();
		try {
			int nombreStagiairesBin = (int) (this.raf.length() / TAILLE_NOEUD_OCTET);
			for (int index = 0; index < nombreStagiairesBin; index++) {
				this.raf.seek(index);
				Noeud noeud = this.recupereNoeudBin(index);
				if (recherche.rechercheMulticritereNoeud(noeud) == true) {
					resultatRecherche.add(index);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultatRecherche;

	}

	/**
	 * Methode complementaire de l'algorithme de RechercheMulticritere : Transforme
	 * une Array list d'index de noeuds en Arbre binaire des noeud correspondants
	 * aux index
	 * 
	 * @param listeIndexNoeud L'array list recuperee en sortie de la
	 *                        RechercheMulticriteres
	 * @param arbreBin        L'arbre danns lequel transcrire l'ArrayList
	 */
	private void importArrayToArbreBin(ArrayList<Integer> listeIndexNoeud, ArbreBin arbreBin) {
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
	 * Methode principale de la transformation de l'ArbreBin en ArrayList  : Exporte
	 * l'ArbreBin dans l'ArrayList passee en argument
	 * 
	 * @param listeNoeud L'ArrayList à remplir avec les données de l'ArbreBin
	 */
	public void exportToArrayListOptionRecherche(ArrayList<Noeud> listeNoeud, boolean rechMultActivee,
			RechercheMulticritere recherche) {
		try {

			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			if (this.raf.length() == 0) {
				listeNoeud = null;
			} else {
				if (rechMultActivee == true) {
					this.exportToArrayListOptionRechercheRecursive(listeNoeud, 0, 0, recherche);
				} else {
					this.exportToArrayListRecursive(listeNoeud, 0, 0);
				}
			}

			this.raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode secondaire recursive pour la transformation de l'ArbreBin en
	 * ArrayList 
	 * 
	 * @param listeNoeud   L'ArrayList à remplir avec les données de l'ArbreBin
	 * @param indexEnCours L'index du noeud sur lequel on se situe
	 * @param indexParent  L'index du noeud parent au noeud dans lequel on se trouve
	 */
	private void exportToArrayListOptionRechercheRecursive(ArrayList<Noeud> listeNoeud, int indexEnCours,
			int indexParent, RechercheMulticritere recherche) {
		int indexEnCoursLocal = indexEnCours;

		if (this.recupererIndexFilsGaucheBin(indexEnCours) != -1) {
			indexParent = indexEnCours;
			indexEnCours = this.recupererIndexFilsGaucheBin(indexEnCours);
			this.exportToArrayListOptionRechercheRecursive(listeNoeud, indexEnCours, indexParent, recherche);
		}

		Noeud noeud = this.recupereNoeudBin(indexEnCoursLocal);
		if (recherche.rechercheMulticritereNoeud(noeud) == true) {
			listeNoeud.add(noeud);
		}
		indexEnCours = indexEnCoursLocal;

		if (this.recupererIndexDoublonBin(indexEnCoursLocal) != -1) {
			this.exportToArrayListDoublonOptionRecherche(listeNoeud, this.recupererIndexDoublonBin(indexEnCoursLocal),
					recherche);
		}

		if (this.recupererIndexFilsDroitBin(indexEnCours) != -1) {
			indexParent = indexEnCours;
			indexEnCours = this.recupererIndexFilsDroitBin(indexEnCours);
			this.exportToArrayListOptionRechercheRecursive(listeNoeud, indexEnCours, indexParent, recherche);
		}

	}

	/**
	 * Troisieme méthode pour la transformation de l'ArbreBin en ArrayList :
	 * traitement des doublons
	 * 
	 * @param listeNoeud          L'ArrayList à compléter par les doublons
	 * @param indexPremierDoublon Index du premier doublon (= DEUXIEME de la chaine
	 *                            Doublons en comptant le noeud de départ)
	 */
	private void exportToArrayListDoublonOptionRecherche(ArrayList<Noeud> listeNoeud, int indexPremierDoublon,
			RechercheMulticritere recherche) {
		int indexParcours = indexPremierDoublon;
		while (this.recupererIndexDoublonBin(indexParcours) != -1) {
			Noeud noeud = this.recupereNoeudBin(indexParcours);
			if (recherche.rechercheMulticritereNoeud(noeud) == true) {
				listeNoeud.add(noeud);
			}
			indexParcours = this.recupererIndexDoublonBin(indexParcours);
		}
		Noeud noeud = this.recupereNoeudBin(indexParcours);
		if (recherche.rechercheMulticritereNoeud(noeud) == true) {
			listeNoeud.add(noeud);
		}
	}

	/**
	 * Methode principale de la transformation de l'ArbreBin en ArrayList Exporte
	 * l'ArbreBin dans l'ArrayList passee en argument
	 * 
	 * @param listeNoeud L'ArrayList à remplir avec les données de l'ArbreBin
	 */
	public void exportToArrayList(ArrayList<Noeud> listeNoeud) {
		try {

			this.raf = new RandomAccessFile(this.adresseFichierBin, "rw");
			if (this.raf.length() == 0) {
				listeNoeud = null;
			} else {
				this.exportToArrayListRecursive(listeNoeud, 0, 0);
			}

			this.raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode secondaire recursive pour la transformation de l'ArbreBin en
	 * ArrayList :
	 * 
	 * @param listeNoeud   L'ArrayList à remplir avec les données de l'ArbreBin
	 * @param indexEnCours L'index du noeud sur lequel on se situe
	 * @param indexParent  L'index du noeud parent au noeud dans lequel on se trouve
	 */
	private void exportToArrayListRecursive(ArrayList<Noeud> listeNoeud, int indexEnCours, int indexParent) {
		int indexEnCoursLocal = indexEnCours;

		if (this.recupererIndexFilsGaucheBin(indexEnCours) != -1) {
			indexParent = indexEnCours;
			indexEnCours = this.recupererIndexFilsGaucheBin(indexEnCours);

			this.exportToArrayListRecursive(listeNoeud, indexEnCours, indexParent);
		}

		listeNoeud.add(this.recupereNoeudBin(indexEnCoursLocal));
		indexEnCours = indexEnCoursLocal;

		if (this.recupererIndexDoublonBin(indexEnCoursLocal) != -1) {
			this.exportToArrayListDoublon(listeNoeud, this.recupererIndexDoublonBin(indexEnCoursLocal));
		}

		if (this.recupererIndexFilsDroitBin(indexEnCours) != -1) {
			indexParent = indexEnCours;
			indexEnCours = this.recupererIndexFilsDroitBin(indexEnCours);
			this.exportToArrayListRecursive(listeNoeud, indexEnCours, indexParent);
		}

	}

	/**
	 * Troisieme méthode pour la transformation de l'ArbreBin en ArrayList :
	 * traitement des doublons
	 * 
	 * @param listeNoeud          L'ArrayList à compléter par les doublons
	 * @param indexPremierDoublon Index du premier doublon (= DEUXIEME de la chaine
	 *                            Doublons en comptant le noeud de départ)
	 */
	private void exportToArrayListDoublon(ArrayList<Noeud> listeNoeud, int indexPremierDoublon) {
		int indexParcours = indexPremierDoublon;
		while (this.recupererIndexDoublonBin(indexParcours) != -1) {
			listeNoeud.add(this.recupereNoeudBin(indexParcours));
			indexParcours = this.recupererIndexDoublonBin(indexParcours);
		}
		listeNoeud.add(this.recupereNoeudBin(indexParcours));
	}
				
}
				
		
	
		
		
		



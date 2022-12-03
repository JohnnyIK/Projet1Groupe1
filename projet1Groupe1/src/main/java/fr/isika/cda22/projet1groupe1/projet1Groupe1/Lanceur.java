package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Lanceur {

	public static void main(String[] args) {

//Test pour voir si déjà tout fonctionne

		Annuaire annuaire = new Annuaire();

//		Stagiaire s1 = new Stagiaire("Alpha", "Mathieu Matt", "75", "CDA 22", "2002");
//		Stagiaire s2 = new Stagiaire("Bravo", "Marc", "10", "FGR 456", "2012");
//		Stagiaire s3 = new Stagiaire("Echo", "Luc", "52", "HJUHJU" , "2020");
//		Stagiaire s4 = new Stagiaire("Charli", "Jean", "63", "EFT 45 IK 256", "1980");
//		Stagiaire s5 = new Stagiaire("Delta", "Jacques", "14", "EGT", "1976");
//
//		annuaire.ajouter(s1);
//		annuaire.ajouter(s2);
//		annuaire.ajouter(s3);
//		annuaire.ajouter(s4);
//		annuaire.ajouter(s5);

		// Annuaire annuaireVide = new Annuaire();

		//System.out.println(annuaire);
//		System.out.println(annuaireVide);

		Sauvegarde sauvegarde = new Sauvegarde();
		annuaire = sauvegarde.importAnnuaireTexte();
		//sauvegarde.sauvegarderFichierBinaire(annuaire);
		//sauvegarde.sauvegardeStagiaire(s2);

		sauvegarde.sauvegarderFichierBinaire(annuaire);
		System.out.println(annuaire);
		Annuaire importAnnuaire = sauvegarde.importSauvegardeBin();
		
		//int nbrStagiaires = annuaire.nombreNoeud();
		//sauvegarde.lireSauvegardeStagiaire(nbrStagiaires);
		System.out.println("import" +importAnnuaire);

	}
}

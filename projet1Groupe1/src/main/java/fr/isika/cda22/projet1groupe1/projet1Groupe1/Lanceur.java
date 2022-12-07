package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Lanceur implements ParametreGestionnaire{

	public static void main(String[] args) {

//Test pour voir si déjà tout fonctionne

//		Annuaire annuaire = new Annuaire();

		Stagiaire s1 = new Stagiaire("Alpha", "Mathieu Matt", "75", "CDA 22", "2002");
//		Stagiaire s2 = new Stagiaire("Bravo", "Marc", "10", "FGR 456", "2012");
//		Stagiaire s3 = new Stagiaire("Echo", "Luc", "52", "HJUHJU" , "2020");
//		Stagiaire s4 = new Stagiaire("Charli", "Jean", "63", "EFT 45 IK 256", "1980");
//		Stagiaire s5 = new Stagiaire("Delta", "Jacques", "14", "EGT", "1976");
		//Stagiaire s0 = new Stagiaire("AAAAAAA", "Mathieu Matt", "75", "CDA 22", "2002");
//
//		annuaire.ajouter(s1);
//		annuaire.ajouter(s2);
//		annuaire.ajouter(s3);
//		annuaire.ajouter(s4);
//		annuaire.ajouter(s5);

		// Annuaire annuaireVide = new Annuaire();

		//System.out.println(annuaire);
//		System.out.println(annuaireVide);

		
		//Annuaire annuaire = new Annuaire();
		//sauvegarde.importAnnuaireTexte();
//		System.out.println(annuaire);
//		annuaire.ajouter(s0);
//		//sauvegarde.sauvegarderFichierBinaire(annuaire);
//		//sauvegarde.sauvegardeStagiaire(s2);
//
//		sauvegarde.sauvegarderFichierBinaire(annuaire);
//		//System.out.println(annuaire);
//		Annuaire importAnnuaire = sauvegarde.importSauvegardeBin();
//		
//		//int nbrStagiaires = annuaire.nombreNoeud();
//		//sauvegarde.lireSauvegardeStagiaire(nbrStagiaires);
//		System.out.println("import" +importAnnuaire);
//		System.out.println(s0.toString());
//		System.out.println(TAILLE_NOEUD_OCTET);
//		System.out.println(TAILLE_STAGIAIRE_OCTET);
		
//		Sauvegarde sauvegarde = new Sauvegarde();
//		sauvegarde.importAnnuaireTexte();
//		sauvegarde.lireSauvegardeStagiaire(CHEMIN_BIN);
//		
//		List<String> departementRech = new ArrayList<String>();
//		departementRech.add("91");
//		departementRech.add("75");
//		String[] anneeFormationRech = {"2000", "2007"};
//		
//		RechercheMulticritere recherche = new RechercheMulticritere(false, "OU", false, "ma", true, departementRech, false, "CD", false, anneeFormationRech);
//		recherche.rechercheMulticriteres();
//		sauvegarde.lireSauvegardeStagiaire(CHEMIN_RESULTATS_RECHERCHE_BIN);
		
		
		
		ArbreBin sauvegardeBin = new ArbreBin(CHEMIN_BIN_SAUVEGARDE_V02);
		sauvegardeBin.importAnnuaireTexte();
		sauvegardeBin.afficherFichierBin();
		
		List<String> departementRech = new ArrayList<String>();
		departementRech.add("91");
		departementRech.add("75");
		String[] anneeFormationRech = {"2000", "2010"};
		
		RechercheMulticritere recherche = new RechercheMulticritere(false, "OU", false, "ma", false, departementRech, false, "CD", true, anneeFormationRech);
		ArrayList<Integer> resultatRecherche = sauvegardeBin.rechercheMulticriteres(recherche);
		 for (Integer i: resultatRecherche) {
			 System.out.println("arraylist "+i.toString());
		 }
	 
		 ArbreBin resultatRechercheBin = new ArbreBin(CHEMIN_RESULTATS_RECHERCHE_BIN);
		 resultatRechercheBin.importArrayToArbreBin(resultatRecherche, sauvegardeBin);
		 resultatRechercheBin.afficherFichierBin();
//		 sauvegardeBin.importStagiaire(s1);
//		 sauvegardeBin.afficherFichierBin();
		 ArrayList<Noeud> listeNoeud = new ArrayList<Noeud>();
		 System.out.println(resultatRechercheBin.adresseFichierBin);
		 resultatRechercheBin.exportToArrayList(listeNoeud);
		 for (Noeud noeud : listeNoeud) {
			 System.out.println(noeud.getCle().getNom());
		 }
	}
}

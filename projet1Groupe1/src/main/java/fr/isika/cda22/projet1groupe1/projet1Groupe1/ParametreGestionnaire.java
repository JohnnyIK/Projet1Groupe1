package fr.isika.cda22.projet1groupe1.projet1Groupe1;

public interface ParametreGestionnaire {
	
	public final static int TAILLE_MAX_NOM = 20;
	public final static int TAILLE_MAX_PRENOM = 20;
	public final static int TAILLE_MAX_DEPARTEMENT = 2;
	public final static int TAILLE_MAX_FORMATION = 15;
	public final static int TAILLE_MAX_ANNEEFORMATION = 4;
	public final static int TAILLE_STAGIAIRE_OCTET = (2 * TAILLE_MAX_NOM) + (2 * TAILLE_MAX_PRENOM)
			+ (2 * TAILLE_MAX_DEPARTEMENT) + (2 * TAILLE_MAX_FORMATION) + (2 * TAILLE_MAX_ANNEEFORMATION);
	public final static int TAILLE_NOEUD_OCTET = 4 + TAILLE_STAGIAIRE_OCTET + 4 + 4 + 4; // 136
	
	public final static int INDEX_FILS_GAUCHE_OCTET = 4 + TAILLE_STAGIAIRE_OCTET ;
	public final static int INDEX_FILS_DROIT_OCTET = 4 + TAILLE_STAGIAIRE_OCTET + 4;
	public final static int INDEX_DOUBLON_OCTET = 4 + TAILLE_STAGIAIRE_OCTET + 4 + 4; 
	
	public final static String CHEMIN_BIN = "src/main/java/Fichier/sauvegardeAnnuaire.bin";
	public final static String CHEMIN_TXT = "src/main/java/Fichier/STAGIAIRES10.DON";
	
	
	
	// ******** AJOUT 20221207 *******************************************************************************
	public final static String CHEMIN_RESULTATS_RECHERCHE_BIN = "src/main/java/Fichier/resultatsRecherche.bin";
	public final static int INDEX_NOM_OCTET = 4 ;
	public final static int INDEX_PRENOM_OCTET = 4 + (2 * TAILLE_MAX_NOM) ;
	public final static int INDEX_DEPARTEMENT_OCTET = INDEX_PRENOM_OCTET + 2 * TAILLE_MAX_PRENOM ;
	public final static int INDEX_FORMATION_OCTET = INDEX_DEPARTEMENT_OCTET + 2 * TAILLE_MAX_DEPARTEMENT ;
	public final static int INDEX_ANNEEFORMATION_OCTET = INDEX_FORMATION_OCTET + 2 * TAILLE_MAX_FORMATION ;
	
	public final static String CHEMIN_BIN_SAUVEGARDE_V02 = "src/main/java/Fichier/sauvegardeAnnuaireV02.bin";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

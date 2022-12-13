package fr.isika.cda22.projet1groupe1.projet1Groupe1;

/**
 * 
 * Ensemble des parametres de l'application "Stagiaires"
 * 
 * @TAILLE_MAX_NOM La taille du nom formate en nombre de caracteres
 * @TAILLE_MAX_PRENOM La taille du prenom formate en nombre de caracteres
 * @TAILLE_MAX_DEPARTEMENT La taille du departement formate en nombre de caracteres
 * @TAILLE_MAX_FORMATION La taille d'attibut formation formate en nombre de caracteres
 * @TAILLE_MAX_ANNEEFORMATION La taille de l'annee de formation formate en nombre de caracteres
 * 
 * @TAILLE_STAGIAIRE_OCTET La taille du stagiaire en octets (stagiaire = nom / prenom/ departement / formation / anneeFormation)
 * @TAILLE_NOEUD_OCTET La taille du noeud en octets (noeud = (int)index + stagiaire en octets + (int)indexFilsGauche + (int)indexFilsDroit + (int)indexDoublon)
 * 
 * @INDEX_NOM_OCTET Emplacement relatif en octets du nom
 * @INDEX_PRENOM_OCTET Emplacement relatif en octets du prenom
 * @INDEX_DEPARTEMENT_OCTET Emplacement relatif en octets du departement
 * @INDEX_FORMATION_OCTET Emplacement relatif en octets de la formation
 * @INDEX_ANNEEFORMATION_OCTET Emplacement relatif en octets de l'annee de formation
 * @INDEX_FILS_GAUCHE_OCTET Emplacement relatif en octets de l'indexFilsGauche
 * @INDEX_FILS_DROIT_OCTET Emplacement relatif en octets de l'indexFilsDroit
 * @INDEX_DOUBLON_OCTET Emplacement relatif en octets de l'indexDoublon
 * 
 * @CHEMIN_TXT Chemin relatif du fichier texte a charger dans le fichier Bin
 * @CHEMIN_BIN Chemin relatif du fichier Bin dans lequel est stocké la liste des stagiaires en binaire
 * @CHEMIN_RESULTATS_RECHERCHE_BIN (optionnel) Chemin relatif du fichier Bin contenant la liste des stagiaires recherches
 * @CHEMIN_BIN_SAUVEGARDE_V02 (utilise pour tester) Chemin relatif du fichier Bin contenant la liste des stagiaires recherches
 * 
 * @modeUtilisateur Indicateur prenant les valeurs "ADMIN" ou "USER" pour generer et tracker les modes Admin ou User
 */


public interface ParametreGestionnaire {
	
	public final static int TAILLE_MAX_NOM = 30;
	public final static int TAILLE_MAX_PRENOM = 30;
	public final static int TAILLE_MAX_DEPARTEMENT = 3;
	public final static int TAILLE_MAX_FORMATION = 15;
	public final static int TAILLE_MAX_ANNEEFORMATION = 4;
	
	public final static int TAILLE_STAGIAIRE_OCTET = (2 * TAILLE_MAX_NOM) + (2 * TAILLE_MAX_PRENOM)
			+ (2 * TAILLE_MAX_DEPARTEMENT) + (2 * TAILLE_MAX_FORMATION) + (2 * TAILLE_MAX_ANNEEFORMATION);
	public final static int TAILLE_NOEUD_OCTET = 4 + TAILLE_STAGIAIRE_OCTET + 4 + 4 + 4; // 136
	
	public final static int INDEX_NOM_OCTET = 4 ;
	public final static int INDEX_PRENOM_OCTET = 4 + (2 * TAILLE_MAX_NOM) ;
	public final static int INDEX_DEPARTEMENT_OCTET = INDEX_PRENOM_OCTET + 2 * TAILLE_MAX_PRENOM ;
	public final static int INDEX_FORMATION_OCTET = INDEX_DEPARTEMENT_OCTET + 2 * TAILLE_MAX_DEPARTEMENT ;
	public final static int INDEX_ANNEEFORMATION_OCTET = INDEX_FORMATION_OCTET + 2 * TAILLE_MAX_FORMATION ;
	public final static int INDEX_FILS_GAUCHE_OCTET = 4 + TAILLE_STAGIAIRE_OCTET ;
	public final static int INDEX_FILS_DROIT_OCTET = 4 + TAILLE_STAGIAIRE_OCTET + 4;
	public final static int INDEX_DOUBLON_OCTET = 4 + TAILLE_STAGIAIRE_OCTET + 4 + 4;

	public final static String CHEMIN_TXT = "src/main/java/Fichier/STAGIAIRES10.DON";
	
	public final static String CHEMIN_BIN = "src/main/java/Fichier/sauvegardeAnnuaire.bin";
	public final static String CHEMIN_RESULTATS_RECHERCHE_BIN = "src/main/java/Fichier/resultatsRecherche.bin";
	public final static String CHEMIN_BIN_SAUVEGARDE_V02 = "src/main/java/Fichier/sauvegardeAnnuaireV02.bin";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

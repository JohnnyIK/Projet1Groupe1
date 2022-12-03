package fr.isika.cda22.projet1groupe1.projet1Groupe1;

public interface ParametreGestionnaire {
	
	public final static int TAILLE_MAX_NOM = 20;
	public final static int TAILLE_MAX_PRENOM = 20;
	public final static int TAILLE_MAX_DEPARTEMENT = 2;
	public final static int TAILLE_MAX_FORMATION = 15;
	public final static int TAILLE_MAX_ANNEEFORMATION = 4;
	public final static int TAILLE_STAGIAIRE_OCTET = (2 * TAILLE_MAX_NOM) + (2 * TAILLE_MAX_PRENOM)
			+ (2 * TAILLE_MAX_DEPARTEMENT) + (2 * TAILLE_MAX_FORMATION) + (2 * TAILLE_MAX_ANNEEFORMATION);

}

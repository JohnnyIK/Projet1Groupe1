package fr.isika.cda22.projet1groupe1.projet1Groupe1;

public class Utilisateur {

	// Attributs
	private String loginUtilisateur;
	private String mdpUtilisateur;
	
	// Constructeur
	public Utilisateur(String loginUtilisateur, String mdpUtilisateur) {
		super();
		this.loginUtilisateur = loginUtilisateur;
		this.mdpUtilisateur = mdpUtilisateur;
	}

	//Setters & Getters
	public String getLoginUtilisateur() {
		return loginUtilisateur;
	}

	public void setLoginUtilisateur(String loginUtilisateur) {
		this.loginUtilisateur = loginUtilisateur;
	}

	public String getMdpUtilisateur() {
		return mdpUtilisateur;
	}

	public void setMdpUtilisateur(String mdpUtilisateur) {
		this.mdpUtilisateur = mdpUtilisateur;
	}


}

package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.util.ArrayList;

/**
 * Classe pour instancier un User (nom / prenom / login / mdp)
 *
 */
public abstract class User {
	//Attributs -----------------------------------------------------------------------------
	private String nom = "";
	private String prenom = "";
	private String login = "";
	private String mdp = "";
	
	
	//Constructeur --------------------------------------------------------------------------------
	public User() {
		
	}
	 /**
	  * Constructeur pour creer un User
	  * @param nom le nom du user
	  * @param prenom le prenom du user
	  * @param login le login du user
	  * @param mdp le mot de passe du user
	  */
	public User(String nom, String prenom, String login, String mdp) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
	}
	 // getters and setters -------------------------------------------------------------------
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return login;
	}
	
	
	
	
}

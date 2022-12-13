package fr.isika.cda22.projet1groupe1.projet1Groupe1;
import java.util.ArrayList;
public abstract class User {
	//Attributs
	private String nom = "";
	private String prenom = "";
	private String login = "";
	private String mdp = "";
	
	
	//Constructeur vide
	public User() {
		
	}
	
	public String getLogin() {
		return login;
	}
	public User(String nom, String prenom, String login, String mdp) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
	}
	
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
	
	
	
	
}

package fr.isika.cda22.projet1groupe1.projet1Groupe1;

public class Stagiaire {
	
	
	// Attributs
	private String nom;
	private String prenom;
	private int departement;
	private String formation;
	private int anneeFormation;

	//Constructeur
	public Stagiaire(String nom, String prenom, int departement, String formation, int anneeFormation) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.formation = formation;
		this.anneeFormation = anneeFormation;
	}

	// Getters and Setters
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


	public int getDepartement() {
		return departement;
	}


	public void setDepartement(int departement) {
		this.departement = departement;
	}


	public String getFormation() {
		return formation;
	}


	public void setFormation(String formation) {
		this.formation = formation;
	}


	public int getAnneeFormation() {
		return anneeFormation;
	}


	public void setAnneeFormation(int anneeFormation) {
		this.anneeFormation = anneeFormation;
	}
	
	
	

}

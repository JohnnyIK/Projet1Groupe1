package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 * Classe pour instancier des StagiairesTableView pour les utiliser dans la
 * tableView (>> Ajout de l'index en plus des attributs classiques du Stagiaire
 * (nom / prenom / departement / formation / annneeFormation)
 *
 */
public class StagiaireTableView {

	// ATTRIBUTS  ------------------------------------------------------------------------------------------------------
	public int index;
	public String prenom;
	public String nom;
	public String email;
	public String departement;
	public String formation;
	public String anneeFormation;

	// CONSTRUCTEUR  ------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur : indexNoeud / nom / prenom / departement / formation /
	 * annneeFormation
	 * 
	 * @param index          l'index du noeud
	 * @param nom            le nom du stagiaire
	 * @param prenom         le prenom du stagiaire
	 * @param departement    le departement du stagiaire
	 * @param formation      la formation du stagiaire
	 * @param anneeFormation l'annee de formation du stagiiare
	 */
	public StagiaireTableView(int index, String nom, String prenom, String departement, String formation,
			String anneeFormation) {
		super();
		this.index = index;
		this.prenom = prenom;
		this.nom = nom;
		this.departement = departement;
		this.formation = formation;
		this.anneeFormation = anneeFormation;
	}

	// GETTERS AND SETTERS  ------------------------------------------------------------------------------------------------------
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public String getAnneeFormation() {
		return anneeFormation;
	}

	public void setAnneeFormation(String anneeFormation) {
		this.anneeFormation = anneeFormation;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String mail) {
		this.email = mail;
	}

}

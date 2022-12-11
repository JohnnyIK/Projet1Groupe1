package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class StagiaireTableView {

	public int index;
	public String prenom;
	public String nom;
	public String email;
	public String departement;
	public String formation;
	public String anneeFormation;
	public CheckBox modifierCheckBox ;
	public Button btnModifier;
	public Button btnSupprimer;
    
	
	public StagiaireTableView(int index, String nom, String prenom, String departement, String formation, String anneeFormation) {
		super();
		this.index = index;
		this.prenom = prenom;
		this.nom = nom;
		this.departement = departement;
		this.formation = formation;
		this.anneeFormation = anneeFormation;
		this.modifierCheckBox = new CheckBox();
		this.btnModifier = new Button();
		this.btnModifier.setText("Modifier");
		this.btnSupprimer = new Button();
		this.btnSupprimer.setText("Supprimer");
	}
	
	
	
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



	public Button getBtnModifier() {
		return btnModifier;
	}

	public void setBtnModifier(Button btnModifier) {
		this.btnModifier = btnModifier;
	}

	public Button getBtnSupprimer() {
		return btnSupprimer;
	}

	public void setBtnSupprimer(Button btnSupprimer) {
		this.btnSupprimer = btnSupprimer;
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

	public CheckBox getModifierCheckBox() {
		return modifierCheckBox;
	}

	public void setModifierCheckBox(CheckBox modifierCheckBox) {
		this.modifierCheckBox = modifierCheckBox;
	}
	
}

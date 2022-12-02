package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Administrateur implements FonctionnaliteCommune {

		//Attributs 
		private String loginAdmin; 
		private String mdpAdmin;
		
		
		//Constructeur
		public Administrateur(String loginAdmin, String mdpAdmin) {
			super();
			this.loginAdmin = loginAdmin;
			this.mdpAdmin = mdpAdmin;
		}

		//Getters & Setters
		public String getLoginAdmin() {
			return loginAdmin;
		}

		public void setLoginAdmin(String loginAdmin) {
			this.loginAdmin = loginAdmin;
		}

		public String getMdpAdmin() {
			return mdpAdmin;
		}

		public void setMdpAdmin(String mdpAdmin) {
			this.mdpAdmin = mdpAdmin;
		}
		
		//Méthode spécifique à l'administrateur
		public void ModifStagiaire() {
			
		}
		
		public void supprimerStagiaire() {
			
		}
		
		public void ImportAnnuaire(Annuaire annuaire,FileReader fichier) {
			// Annuaire annuaire = new Annuaire();
			fichier = new FileReader("src/Fichier/Stagiaires.don");
			BufferedReader br = new BufferedReader(fichier);
			
			if (annuaire.isEmpty()){
				
				while (br.ready()) {
					
					while(!br.readLine().equals("*"){
						
						Stagiaire stagiaire = new Stagiaire(br.readLine(),br.readLine(),br.readLine(),br.readLine(),br.readLine());
						ajouterStagiaire(stagiaire);
						
					}
					
				
				}
					
			} else {
				
			}
			
		
		}
		
		
		
		
	

}

package fr.isika.cda22.projet1groupe1.projet1Groupe1;

public abstract class User {
	//Attributs
		private String login;
		private String mdp;
		
		//Constructeur
		public User(String login, String mdp) {
			super();
			this.login = login;
			this.mdp = mdp;
		}
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public String getMdp() {
			return mdp;
		}
		public void setMdp(String mdp) {
			this.mdp = mdp;
		}
		
		public void ajouterStagiaire() {
			return;
		}
		
		public void afficherStagiaire() {
			
		}
		
		public void rechercheStagiaire() {
			
		}
		
		public void imprimerAnnuaire() {
			
		}
	}

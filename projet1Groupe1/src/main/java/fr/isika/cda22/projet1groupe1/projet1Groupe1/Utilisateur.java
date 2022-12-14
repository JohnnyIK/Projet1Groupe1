package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe pour instancier un utilisateur (user + liste utilisateur)
 *
 */
public class Utilisateur extends User implements ParametreGestionnaire {
	// Attributs --------------------------------------------------------------------------------
	ArrayList<Utilisateur> listeUtilisateur;

	// Constructeur de la class mère -----------------------------------------------------------
	/**
	 * Cree un Utilisateur
	 * @param nom
	 * @param prenom
	 * @param login
	 * @param mdp
	 */
	public Utilisateur(String nom, String prenom, String login, String mdp) {
		super(nom, prenom, login, mdp);
		// TODO Auto-generated constructor stub
	}

	// Constructeur vide
	public Utilisateur() {

	}

	// getters and setters --------------------------------------------------------------------------------------
	public ArrayList<Utilisateur> getListeUtilisateur() {
		return listeUtilisateur;
	}

	public void setListeUtilisateur(ArrayList<Utilisateur> listeUtilisateur) {
		this.listeUtilisateur = listeUtilisateur;
	}
	
	// methode ----------------------------------------------------------------------------
	/**
	 * Recupere le login utilisateur
	 */
	public void recupereLoginUtilisateur() {

		listeUtilisateur = new ArrayList<>();

		try {
			FileReader fr = new FileReader(CHEMIN_TXT_LOGIN_UTILISATEUR);
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				String nomUtilisateur = br.readLine();
				String prenomUtilisateur = br.readLine();
				String loginUtilisateur = br.readLine();
				String mdpUtilisateur = br.readLine();
				br.readLine();
				Utilisateur utilisateur = new Utilisateur(nomUtilisateur, prenomUtilisateur, loginUtilisateur,
						mdpUtilisateur);
				listeUtilisateur.add(utilisateur);
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
package fr.isika.cda22.projet1groupe1.projet1Groupe1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Classe pour instancier des Administrateurs
 *
 */
public class Administrateur extends User implements ParametreGestionnaire {
	ArrayList<Administrateur> listeAdmin;
	
	// Constructeur de la class mère
	public Administrateur(String nom, String prenom, String login, String mdp) {
		super(nom, prenom, login, mdp);
		// TODO Auto-generated constructor stub
	}
	
	//Constructeur vide
	public Administrateur() {
		
	}
	public void recupereLoginAdmin() {
		
		listeAdmin = new ArrayList<>();
		
		try {
			FileReader fr = new FileReader(CHEMIN_TXT_LOGIN_ADMIN);
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				String nomAdmin = br.readLine();
				String prenomAdmin = br.readLine();
				String loginAdmin = br.readLine();
				String mdpAdmin = br.readLine();
				br.readLine();
				Administrateur admin = new Administrateur(nomAdmin, prenomAdmin, loginAdmin, mdpAdmin);
				listeAdmin.add(admin);
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	public ArrayList<Administrateur> getListeAdmin() {
		return listeAdmin;
	}
	public void setListeAdmin(ArrayList<Administrateur> listeAdmin) {
		this.listeAdmin = listeAdmin;
	}
	
}
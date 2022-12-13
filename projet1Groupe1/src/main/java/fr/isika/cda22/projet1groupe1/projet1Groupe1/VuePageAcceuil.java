package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import javafx.scene.control.Button;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Currency;

import javafx.application.Application;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class VuePageAcceuil extends Scene {

	

	//attributs
	Button btnAdmin;
	Button btnUser;
	//private final GridPane rootPane;
	

	
	public VuePageAcceuil() {
		
		//définition gripane et espaces
		super(new GridPane(), 600, 300);
		GridPane rootAcceuil = (GridPane)this.getRoot();
		rootAcceuil.setPadding(new Insets (50));
		rootAcceuil.setHgap(15);
		rootAcceuil.setVgap(15);
		
		
		
		//modif police mac sinon illisible
		this.setRoot(rootAcceuil);
		this.getRoot().setStyle("-fx-font-family: 'serif'");
		
		
		//titre de bienvenu
		Label labelTitre = new Label("Bienvenue sur Patrick School, votre logiciel de gestion de liste de stagiaires !");
		rootAcceuil.add(labelTitre, 1,1,4,2);
		
		Label labelChoix = new Label("Êtes-vous Administrateur ou Utilisateur ?");


		
		
		//pb sur police mac
		rootAcceuil.setStyle("-fx-font-family: 'serif'; -fx-background-image: url('file:src/main/java/icon/backgroundAcceuil.jpeg')");
		
		
		
		
		//création bouton admin et user 
		btnAdmin = new Button("Administrateur");
		
		btnUser = new Button("Utilisateur");
		
		
		//positionnement
	    rootAcceuil.add(labelChoix, 2, 5);

	    rootAcceuil.add(btnAdmin, 1, 7);

	    rootAcceuil.add(btnUser, 3, 7);
	    
	    
	    
	    //récupérer stage pour avoir acceuil créer avant btnAdmin
	    
	    
//	    getStage
	   // btnAdmin.getScene().setRoot(rootAcceuil.getRootPane);
	    
	    
	    
	}
	//public Pane getRootPane() {
		//return rootPane;
	
	
	


	public Button getBtnAdmin() {
		return btnAdmin;
	}
	public void setBtnAdmin(Button btnAdmin) {
		this.btnAdmin = btnAdmin;
	}




	public Button getBtnUser() {
		return btnUser;
	}


	public void setBtnUser(Button btnUser) {
		this.btnUser = btnUser;
	}
	
}

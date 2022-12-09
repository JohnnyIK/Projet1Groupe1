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

import java.util.Currency;

import javafx.application.Application;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



public class VueLoginAdmin extends Scene{
	
	//attributs 
	Button btnLogin;
	
	
	public VueLoginAdmin () {
		super(new GridPane(), 600, 300);
		GridPane rootLoginAdmin = (GridPane)this.getRoot();
		rootLoginAdmin.setPadding(new Insets (50));
		rootLoginAdmin.setHgap(15);
		rootLoginAdmin.setVgap(15);
		
		this.setRoot(rootLoginAdmin);
		this.getRoot().setStyle("-fx-font-family: 'serif'");
		
		rootLoginAdmin.setStyle("-fx-font-family: 'serif'; -fx-background-image: url('file:src/main/java/icon/backgroundAcceuil.jpeg')");
		
		Label labelTitreAdm = new Label("Bienvenu cher Administrateur, veuillez entrer vos identifiants :");
		rootLoginAdmin.add(labelTitreAdm, 2,2,3,1);
		
		Label labelId = new Label("Identifiant");
		TextField fieldId = new TextField("entrez identifiants");
		
		Label labelmdp = new Label("Mot de passe");
		TextField fieldMdp = new TextField("entrez votre mot de passe");
		
		btnLogin = new Button ("Valider");
		
		rootLoginAdmin.add(labelId, 2,3);
		rootLoginAdmin.add(fieldId, 3, 3);
		
		rootLoginAdmin.add(labelmdp, 2, 4);
		rootLoginAdmin.add(fieldMdp, 3, 4);
		
		rootLoginAdmin.add(btnLogin, 3, 5);
		
	}


	public Button getBtnLogin() {
		return btnLogin;
	}


	public void setBtnLogin(Button btnLogin) {
		this.btnLogin = btnLogin;
	}
	
	

}

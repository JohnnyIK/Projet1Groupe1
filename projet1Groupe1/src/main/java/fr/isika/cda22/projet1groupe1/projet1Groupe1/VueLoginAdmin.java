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
import javafx.scene.paint.Color;

import java.util.Currency;

import javafx.application.Application;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



public class VueLoginAdmin extends Scene implements ParametreGestionnaire{
	
	//attributs 
	Button btnLogin;
	TextField fieldId;
	TextField fieldMdp;
	
	
	public TextField getFieldId() {
		return fieldId;
	}


	public void setFieldId(TextField fieldId) {
		this.fieldId = fieldId;
	}


	public TextField getFieldMdp() {
		return fieldMdp;
	}


	public void setFieldMdp(TextField fieldMdp) {
		this.fieldMdp = fieldMdp;
	}


	public VueLoginAdmin () {
		super(new GridPane(), 800, 320);
		GridPane rootLoginAdmin = (GridPane)this.getRoot();
		rootLoginAdmin.setPadding(new Insets (50));
		rootLoginAdmin.setHgap(15);
		rootLoginAdmin.setVgap(15);
		
		this.setRoot(rootLoginAdmin);
		
		
		Label labelTitreAdm = new Label("Bienvenue cher Administrateur,\nveuillez entrer vos identifiants :");
		rootLoginAdmin.add(labelTitreAdm, 1,0,3,3);
		
		Label labelId = new Label("Identifiant");
		fieldId = new TextField("entrez identifiants");
		
		Label labelmdp = new Label("Mot de passe");
		fieldMdp = new TextField("entrez votre mot de passe");
		
		btnLogin = new Button ("Valider");
		
		rootLoginAdmin.add(labelId, 1,3);
		rootLoginAdmin.add(fieldId, 2, 3);
		
		rootLoginAdmin.add(labelmdp, 1, 4);
		rootLoginAdmin.add(fieldMdp, 2, 4);
		
		rootLoginAdmin.add(btnLogin, 2, 5);
		
		
		
		if (os.equals("PC")) {
			labelTitreAdm.setFont(FONTTITRE);
			labelId.setFont(FONTTEXTE);
			labelmdp.setFont(FONTTEXTE);
			btnLogin.setFont(FONTBUTTON);
			fieldId.setFont(FONTTEXTE);
			fieldMdp.setFont(FONTTEXTE);
	    	
	    }
	    
		labelTitreAdm.setTextFill(Color.rgb(60, 60, 60));
		labelId.setTextFill(Color.rgb(60, 60, 60));
		labelmdp.setTextFill(Color.rgb(60, 60, 60));
	    
		btnLogin.setPrefWidth(120);
		btnLogin.setTextFill(Color.rgb(61, 110, 139));
		btnLogin.setStyle(BUTTONCOLOR);
		btnLogin.setOnMouseEntered((event) -> {
			btnLogin.setStyle(BUTTONCOLOROVER);
	    	btnLogin.setTextFill(Color.rgb(240, 240, 240));
		});
		btnLogin.setOnMouseExited((event) -> {
			btnLogin.setStyle(BUTTONCOLOR);
			btnLogin.setTextFill(Color.rgb(61, 110, 139));
		});
	   
	    
	    this.getRoot().setStyle("-fx-font-family: 'serif'");
		
		rootLoginAdmin.setStyle("-fx-font-family: 'serif'; -fx-background-image: url('file:src/main/java/icon/backgroundAcceuil.jpeg')");
		
	}


	public Button getBtnLogin() {
		return btnLogin;
	}


	public void setBtnLogin(Button btnLogin) {
		this.btnLogin = btnLogin;
	}
	
	

}

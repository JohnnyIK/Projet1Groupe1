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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.Currency;

import javafx.application.Application;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Classe pour instancier une vue page accueil
 *
 */
public class VuePageAcceuil extends Scene implements ParametreGestionnaire{
	
	//attributs --------------------------------------------------------------------------------------------------------------
	private Button btnAdmin;
	private Button btnUser;
	
	// Constructeur ------------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur pour creer une vue page accueil
	 */
	public VuePageAcceuil() {
		
		//définition gripane et espaces
		super(new GridPane(), 800, 320);
		GridPane rootAcceuil = (GridPane)this.getRoot();
		rootAcceuil.setPadding(new Insets (50));
		rootAcceuil.setHgap(15);
		rootAcceuil.setVgap(15);
		
		//modif police mac sinon illisible
		this.setRoot(rootAcceuil);
		this.getRoot().setStyle("-fx-font-family: 'serif'");
		
		//titre de bienvenue
		Label labelTitre = new Label("Bienvenue sur Patrick's School,\nvotre logiciel de gestion de liste de stagiaires !");
		rootAcceuil.add(labelTitre, 1,1,4,2);
		
		Label labelChoix = new Label("Êtes-vous Administrateur ou Utilisateur ?");
		
		// GRID PANE BOUTONS
		GridPane grilleButtons = new GridPane();
		grilleButtons.setHgap(15);
		//création bouton admin et user 
		btnAdmin = new Button("Administrateur");
		btnUser = new Button("Utilisateur");
		grilleButtons.add(btnAdmin, 0, 0);
		grilleButtons.add(btnUser, 1, 0);
		
		//positionnement
	    rootAcceuil.add(labelChoix, 1, 5);
	    rootAcceuil.add(grilleButtons, 1, 7, 2, 1);

	    // FALSE CSS
	    if (os.equals("PC")) {
	    	labelTitre.setFont(FONTTITRE);
	    	labelChoix.setFont(FONTTEXTE);
	    	btnAdmin.setFont(FONTBUTTON);
		    btnUser.setFont(FONTBUTTON);
	    	
	    }
	    
	    labelTitre.setTextFill(Color.rgb(60, 60, 60));
	    labelChoix.setTextFill(Color.rgb(60, 60, 60));
	    
	    btnAdmin.setPrefWidth(120);
	    btnAdmin.setTextFill(Color.rgb(61, 110, 139));
	    btnAdmin.setStyle(BUTTONCOLOR);
	    btnAdmin.setOnMouseEntered((event) -> {
	    	btnAdmin.setStyle(BUTTONCOLOROVER);
	    	btnAdmin.setTextFill(Color.rgb(240, 240, 240));
		});
	    btnAdmin.setOnMouseExited((event) -> {
	    	btnAdmin.setStyle(BUTTONCOLOR);
	    	btnAdmin.setTextFill(Color.rgb(61, 110, 139));
		});
	    btnUser.setPrefWidth(120);
	    btnUser.setTextFill(Color.rgb(61, 110, 139));
	    btnUser.setStyle(BUTTONCOLOR);
	    btnUser.setOnMouseEntered((event) -> {
	    	btnUser.setStyle(BUTTONCOLOROVER);
	    	btnUser.setTextFill(Color.rgb(240, 240, 240));
	    	
		});
	    btnUser.setOnMouseExited((event) -> {
	    	btnUser.setStyle(BUTTONCOLOR);
	    	btnUser.setTextFill(Color.rgb(61, 110, 139));
		});
	    
	  //pb sur police mac
	  	rootAcceuil.setStyle("-fx-font-family: 'serif'; -fx-background-image: url('file:src/main/java/icon/BG_Login.png')");
	    
	}

	// Getters and setters --------------------------------------------------------------------------------------------------
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

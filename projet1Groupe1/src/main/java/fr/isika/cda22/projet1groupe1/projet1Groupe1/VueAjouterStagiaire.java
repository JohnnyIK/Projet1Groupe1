package fr.isika.cda22.projet1groupe1.projet1Groupe1;
import javafx.scene.control.Button;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;
import java.util.Currency;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;


public class VueAjouterStagiaire extends Scene {
	
	//attributs
	private Button btnValiderAjoutStagiaire;
	private Button btnRetour ;
	
	private Stagiaire stagiaire = new Stagiaire("Alpha", "jghj Matt", "75", "CDA 22", "2002");
	
	private TextField fieldNom ;
	private TextField fieldPrenom ;
	private TextField fieldDep ;
	private TextField fieldFormation ;
	private TextField fieldAnneeAj ;
	
	private ArbreBin sauvegardeHelper = new ArbreBin("");
	
	public VueAjouterStagiaire () {
		super(new GridPane(), 600, 370);
		GridPane rootAddIntern = (GridPane)this.getRoot();
		rootAddIntern.setPadding(new Insets (50));
		rootAddIntern.setHgap(16);
		rootAddIntern.setVgap(16);
		
		this.setRoot(rootAddIntern);
		this.getRoot().setStyle("-fx-font-family: 'serif'");
		
		rootAddIntern.setStyle("-fx-font-family: 'serif'; -fx-background-image: url('file:src/main/java/icon/backgroundAcceuil.jpeg')");
		
		Label labelTitreAj = new Label("Enregistrez vos nouveaux stagiaires :");
		rootAddIntern.add(labelTitreAj, 1,0,3,1);
		
		Label labelnomAj = new Label("Nom  ");
		fieldNom = new TextField("Entrez le nom");
		
		Label labelPrenomAj = new Label("Prenom  ");
		fieldPrenom = new TextField("Entrez le prénom");
	
		Label labelDepAj = new Label("Département");
		fieldDep = new TextField("Entrez le département");
		
		Label labelFormationAj = new Label("Formation ");
		fieldFormation = new TextField("Entrez la formation");
		
		Label labelAnneeAj = new Label("Année");
		fieldAnneeAj = new TextField("Entrez l'année");
		
		Text asterisque = new Text("*");
		Text obligatoire = new Text("(*) Champ requis");
		
		fieldNom.setOnMouseClicked((e) -> {
			fieldNom.setText("");
		});
		
//		fieldNom.setOnMouseExited((e) -> {
//			if (fieldNom.getText().equals("")) {
//			    fieldNom.setText("Entrez le nom");
//			}
//		});
		
		rootAddIntern.add(labelnomAj, 1, 1);
		rootAddIntern.add(fieldNom, 2, 1);
		
		rootAddIntern.add(labelPrenomAj, 1, 2);
		rootAddIntern.add(fieldPrenom, 2, 2);
		
		rootAddIntern.add(labelDepAj, 1,3);
		rootAddIntern.add(fieldDep, 2, 3);
		
		rootAddIntern.add(labelFormationAj, 1, 4);
		rootAddIntern.add(fieldFormation, 2, 4);
		
		rootAddIntern.add(labelAnneeAj, 1, 5);
		rootAddIntern.add(fieldAnneeAj, 2, 5);
		
		rootAddIntern.add(new Text("*"), 3, 1);
		rootAddIntern.add(new Text("*"), 3, 2);
		rootAddIntern.add(new Text("*"), 3, 3);
		rootAddIntern.add(new Text("*"), 3, 4);
		rootAddIntern.add(new Text("*"), 3, 5);
		rootAddIntern.add(obligatoire, 2, 6);
		
		btnValiderAjoutStagiaire = new Button ("Ajouter");
		btnRetour = new Button ("Annuler");
		
		GridPane grilleButtons = new GridPane();
		grilleButtons.add(btnRetour, 0, 0);
		grilleButtons.add(btnValiderAjoutStagiaire, 1, 0);
		grilleButtons.setHgap(10);
		
		rootAddIntern.add(grilleButtons, 2, 8, 2, 1);
		
		/*
		btnValiderAjoutStagiaire.setOnAction(event -> {
			String nom = fieldNom.getText();
	    	System.out.println("test bouton");
	    	System.out.println(this.sauvegardeHelper.textIsCorrect(nom));
			if (nom.equals("")) {
				fieldNom.setText("Champ requis");
			} else if (!this.sauvegardeHelper.textIsCorrect(nom) || nom.equals("Entrez le nom")){
				fieldNom.setText("Nom invalide");
			} else {
				stagiaire.setNom(nom);
				//VueAppAdmin vueAppAdmin = new VueAppAdmin(Stagiaire);
				//stage.setScene(vueAjouterStagiaire);
	            //stage.setTitle(" Patrick School - Ajouter Stagiaire ");
			}
		});
		*/
		
		
		
	}
	
	public Button getBtnvaliderAj() {
		return btnValiderAjoutStagiaire;
	}
	public void setBtnvaliderAj(Button btnvaliderAj) {
		btnValiderAjoutStagiaire = btnvaliderAj;
	}
	public Button getBtnRetour() {
		return btnRetour;
	}
	public void setBtnRetour(Button btnRetour) {
		btnRetour = btnRetour;
	}
	
	public TextField getFieldNom() {
		return this.fieldNom;
	}
	
	public Stagiaire getStagiaire() {
		return stagiaire;
	}
	
}
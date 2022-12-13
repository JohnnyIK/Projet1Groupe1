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

import java.util.Currency;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VueAppAdmin extends Scene {
		
		//attributs 
		VBox leftVbox;
		Button btnAjoutSta;
		Button btnRechAv;
		Button btnImpress;
		Button btnDoc;
		Button btnFile;
		
		HBox topHbox;
		Button btnNom;
		Button btnPrenom;
		Button btnDepartement;
		Button btnFormation;
		Button btnAnnee;
		
		public String modeUtilisateur = "";
		
		public TableViewStagiaire tableViewStagiaire;
		
		
		
		public VueAppAdmin (String modeUtilisateur) {
			
			
			super(new BorderPane(), 1000, 1000);
			this.modeUtilisateur = modeUtilisateur;
			this.tableViewStagiaire = new TableViewStagiaire();
			BorderPane rootVueAppAdmin = (BorderPane)this.getRoot();
			rootVueAppAdmin.setPadding(new Insets(10, 10, 20, 20));//(hauteur, droite, bas, largeur
			this.setRoot(rootVueAppAdmin);
			this.getRoot().setStyle("-fx-font-family: 'serif'");
			
			//Vbox dans LE LEFT
			VBox leftVbox = new VBox(30);
			ObservableList<Node>leftVboxControls = leftVbox.getChildren();
			Insets border = new Insets(20, 20, 20, 20);
			leftVbox.setPadding(border);
			leftVbox.setSpacing(30);
			BackgroundFill bgrFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
			leftVbox.setBackground(new Background(bgrFill));
			
			//boutonn dans vbox 
			btnAjoutSta = new Button("Ajouter Stagiaire");
			btnRechAv = new Button("Recherche Avancée");
			btnImpress = new Button("Impression Liste");
			btnDoc = new Button("Guide utilisation");
			btnFile = new Button("Selectionner Fichier");
			leftVboxControls.add(btnAjoutSta);
			leftVboxControls.add(btnRechAv);
			leftVboxControls.add(btnImpress);
			leftVboxControls.add(btnDoc);
			if (modeUtilisateur == "admin") {
				
			leftVboxControls.add(btnFile); //if modeutiliseur == admin , 
			}
			
			
			
			
			
			
			//Vbox mise dans le left
			rootVueAppAdmin.setLeft(leftVbox);
			
			//Hbox dans LE TOP
			HBox topHbox = new HBox(30);
			ObservableList<Node>topHboxControls = topHbox.getChildren();
			Insets border2 = new Insets(40, 40, 40, 40);
			topHbox.setPadding(border2);
			topHbox.setSpacing(30);
			BackgroundFill bgrFill2 = new BackgroundFill(Color.LIGHTBLUE, null, null);
			topHbox.setBackground(new Background(bgrFill2));
			
			//boutons dans le TOP 
			btnNom = new Button("Nom");
			topHboxControls.add(btnNom);
			TextField tapernom = new TextField("écrire nom");
			topHboxControls.add(tapernom);
		
			btnPrenom = new Button("Prénom");
			topHboxControls.add(btnPrenom);
			TextField taperPrenom = new TextField("écrire prénom");
			topHboxControls.add(taperPrenom);
			
			
			btnDepartement = new Button("Département");
			topHboxControls.add(btnDepartement);
			TextField taperDepartement = new TextField("écrire département");
			topHboxControls.add(taperDepartement);
			
			
			
			btnFormation = new Button("Formation");
			topHboxControls.add(btnFormation);
			TextField taperFormation = new TextField("écrire nom de formation");
			topHboxControls.add(taperFormation);
			
			
			btnAnnee = new Button("Année");
			topHboxControls.add(btnAnnee);
			TextField taperAnnee = new TextField("écrire l'année");
			topHboxControls.add(taperAnnee);
		
			//hide and clear TOP
			HBox test = new HBox();
			test.setId("TEST");
			rootVueAppAdmin.setTop(test);
			
			//stackpane dans center 
			StackPane stackpane = new StackPane();
			//ObservableList<Node>stackpaneControls = stackpane.getChildren();
			//Insets borderstack = new Insets(20, 20, 20, 20);
			//stackpane.setPadding(borderstack);
			//BackgroundFill bgrStack = new BackgroundFill(Color.GREEN, null, null);
			//stackpane.setBackground(new Background(bgrStack));
			
			
			//stackpane.getChildren().add(tableViewStagiaire);
			HBox testTable = new HBox();
			Label teste = new Label("TESTE");
			testTable.getChildren().add(teste);
			
			TableViewStagiaire tableViewStagiaire2 = new TableViewStagiaire();
			rootVueAppAdmin.setCenter(stackpane);
			stackpane.getChildren().add(tableViewStagiaire2);
			//rootVueAppAdmin.setCenter(testTable);
			//rootVueAppAdmin.setCenter(tableViewStagiaire2);
			
			
			
			
			String topaffichee = "";
			topHbox.setId("boxRecherche");
			btnRechAv.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
					if (!(rootVueAppAdmin.getTop().getId().equals("boxRecherche"))) {
						rootVueAppAdmin.setTop(topHbox);
						
						
					}else { 
						rootVueAppAdmin.setTop(test);
						
					}
				}
				
			});
			
			
//			btnAjoutSta.setOnAction(new EventHandler<ActionEvent>() {
//				public void handle(ActionEvent arg0) {
//					//On instancie notre premier panneau:
//					AjouterStagiaire stackpane = new AjouterStagiaire();
//					//On instancie notre scène en lui donnant notre panneau root:
//					Scene scene = new Scene(root);
//					//On instancie notre scène en lui donnant notre panneau root:
//					stage.setScene(scene);
//					//On donna un titre à notre stage:
//					stage.setTitle("Première Transition");
//					//On demande à notre stage de s'adapter à la taille de la scène:
//					stage.sizeToScene();
//					//On affiche notre stage
//					stage.show();
//				}
			}
			
}

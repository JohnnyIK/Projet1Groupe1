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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.Currency;


import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VueAppAdmin extends Scene implements ParametreGestionnaire{
		
		//attributs -----------------------------------------------------------------------------------------------
		private VBox leftVbox;
		private Button btnAjoutSta;
		private Button btnRechAv;
		private Button btnImpress;
		private Button btnDoc;
		private Button btnFile;
		
		private VBox topVbox;
		private Button btnNom;
		private Button btnPrenom;
		private Button btnDepartement;
		private Button btnFormation;
		private Button btnAnnee;
		
		public String modeUtilisateur = "";
		public TableViewStagiaire tableViewStagiaire;
		private ArbreBin sauvegardeHelper = new ArbreBin("");
		private StackPane stackpane;
		
		//Constructeur ---------------------------------------------------------------------------------------------
		public VueAppAdmin (String modeUtilisateur, String nomUser, String prenomUser) {
			
			super(new BorderPane(), 1400, 800);
			this.modeUtilisateur = modeUtilisateur;
			this.tableViewStagiaire = new TableViewStagiaire(CHEMIN_TXT, modeUtilisateur);
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
			BackgroundFill bgrFill = new BackgroundFill(Color.rgb(182,213,231), null, null);
			leftVbox.setBackground(new Background(bgrFill));
			
			//boutonn dans vbox 
			btnAjoutSta = new Button("Ajouter Stagiaire");
			btnImpress = new Button("Impression Liste");
			btnDoc = new Button("Guide utilisation");
			btnFile = new Button("Selectionner Fichier");
			leftVboxControls.add(btnAjoutSta);
			leftVboxControls.add(btnImpress);
			leftVboxControls.add(btnDoc);
			//if modeutiliseur == admin , 
			if (modeUtilisateur == "admin") {
				leftVboxControls.add(btnFile); 
			}
			
			//Vbox mise dans le left
			rootVueAppAdmin.setLeft(leftVbox);
			
			//Hbox dans LE TOP
			VBox topVbox = new VBox(30);
			Insets border2 = new Insets(40, 40, 40, 40);
			topVbox.setPadding(border2);
			topVbox.setSpacing(30);
			topVbox.setAlignment(Pos.CENTER);
			topVbox.setStyle("-fx-font-family: 'serif'; -fx-background-image: url('file:src/main/java/icon/BG_Banner.png')");
			InputStream input = getClass().getResourceAsStream("/icon/Admin1.png");
			Image image = new Image(input); //("/icon/Admin.png");
			ImageView imageView = new ImageView(image);
			
			//Composition du TOP
			GridPane infoUser = new GridPane();
			infoUser.add(imageView, 0, 0, 1, 2);
			infoUser.setHgap(20);
			Label espaceNom;
			if (this.modeUtilisateur.equals("admin")) {
				espaceNom = new Label ("Espace Administrateur");
			} else {
				espaceNom = new Label ("Espace Utilisateur");
			}
			
			Label welcome = new Label ("Bienvenue, "+prenomUser+" !");
			infoUser.add(espaceNom, 2, 0);
			infoUser.add(welcome, 2, 1);
			topVbox.getChildren().add(infoUser);
		
			rootVueAppAdmin.setTop(topVbox);
			
			//stackpane dans center 
			stackpane = new StackPane();
			rootVueAppAdmin.setCenter(stackpane);
			stackpane.getChildren().add(tableViewStagiaire);
			stackpane.setPadding(new Insets(16,0,0,0));

			String topaffichee = "";
			topVbox.setId("boxRecherche");
		
			//SET UP ACTIONS BOUTONS
			// action sur bouton ajouter (ouverture boite de dialogue avec une vue ajouter
			// stagiaire)
			btnAjoutSta.setOnAction(event -> {
				Stage dialog = new Stage();
				VueAjouterStagiaire vueAjouterStagiaire = new VueAjouterStagiaire();
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.setResizable(false);
				dialog.setTitle("Ajouter un stagiaire");

				// action sur bouton valider l'ajout
				vueAjouterStagiaire.getBtnvaliderAj().setOnAction(e -> {
					Boolean nomOK = false;
					Boolean prenomOK = false;
					Boolean departementOK = false;
					Boolean formationOK = false;
					Boolean anneeFormationOK = false;
					String nom = vueAjouterStagiaire.getFieldNom().getText();
					String prenom = vueAjouterStagiaire.getFieldPrenom().getText();
					String departement = vueAjouterStagiaire.getFieldDep().getText();
					String formation = vueAjouterStagiaire.getFieldFormation().getText();
					String anneeFormation = vueAjouterStagiaire.getFieldAnneeAj().getText();
					System.out.println("test bouton");
					System.out.println(this.sauvegardeHelper.textIsCorrect(nom));
					if (nom.equals("")) {
						vueAjouterStagiaire.getFieldNom().setText("Champ requis");
					} else if (!this.sauvegardeHelper.textIsCorrect(nom) || nom.equals("Entrez le nom")
							|| nom.equals("Nom invalide")) {
						vueAjouterStagiaire.getFieldNom().setText("Nom invalide");
					} else {
						nom = this.sauvegardeHelper.nomCheckAndUpdate(nom);
						vueAjouterStagiaire.getStagiaire().setNom(nom);
						nomOK = true;
					}
					if (prenom.equals("")) {
						vueAjouterStagiaire.getFieldPrenom().setText("Champ requis");
					} else if (!this.sauvegardeHelper.textIsCorrect(prenom) || prenom.equals("Entrez le prénom")
							|| prenom.equals("Prénom invalide")) {
						vueAjouterStagiaire.getFieldPrenom().setText("Prenom invalide");
					} else {
						prenom = this.sauvegardeHelper.prenomCheckAndUpdate(prenom);
						vueAjouterStagiaire.getStagiaire().setPrenom(prenom);
						prenomOK = true;
					}
					if (departement.equals("")) {
						vueAjouterStagiaire.getFieldDep().setText("Champ requis");
					} else if (!this.sauvegardeHelper.departementCheck(departement)
							|| departement.equals("Entrez le département")
							|| departement.equals("Département invalide")) {
						vueAjouterStagiaire.getFieldDep().setText("Département invalide");
					} else {
						departement = this.sauvegardeHelper.departementCheckAndUpdate(departement);
						vueAjouterStagiaire.getStagiaire().setDepartement(departement);
						departementOK = true;
					}
					if (formation.equals("")) {
						vueAjouterStagiaire.getFieldFormation().setText("Champ requis");
					} else if (!this.sauvegardeHelper.formationCheck(formation)
							|| formation.equals("Entrez la formation") || formation.equals("Formation invalide")) {
						vueAjouterStagiaire.getFieldFormation().setText("Formation invalide");
					} else {
						formation = this.sauvegardeHelper.formationCheckAndUpdate(formation);
						vueAjouterStagiaire.getStagiaire().setFormation(formation);
						formationOK = true;
					}
					if (anneeFormation.equals("")) {
						vueAjouterStagiaire.getFieldAnneeAj().setText("Champ requis");
					} else if (!this.sauvegardeHelper.anneeFormationCheck(anneeFormation)
							|| anneeFormation.equals("Entrez l'année") || anneeFormation.equals("Année invalide")) {
						vueAjouterStagiaire.getFieldAnneeAj().setText("Année invalide");
					} else {
						anneeFormation = this.sauvegardeHelper.anneeFormationCheckAndUpdate(anneeFormation);
						vueAjouterStagiaire.getStagiaire().setAnneeFormation(anneeFormation);
						anneeFormationOK = true;
					}
					if (nomOK == true && prenomOK == true && departementOK == true && formationOK == true
							&& anneeFormationOK == true) {
						tableViewStagiaire.getSauvegardeBin().ajouterStagiaireBin(vueAjouterStagiaire.getStagiaire());
						tableViewStagiaire.initRecherche();
						tableViewStagiaire.updateTableView(false, null);
						dialog.close();
					}
				});
				// action sur bouton annuler
				vueAjouterStagiaire.getBtnRetour().setOnAction(e -> {
					dialog.close();
				});

				dialog.setScene(vueAjouterStagiaire);
				dialog.showAndWait();

			});

			// bouton imprimer liste
			btnImpress.setOnAction(event -> {
				System.out.println("clic impression");
				ArrayList<Noeud> listeNoeud = new ArrayList<Noeud>();
				tableViewStagiaire.getSauvegardeBin().exportToArrayListOptionRecherche(listeNoeud,
				tableViewStagiaire.getRechercheActivee(), tableViewStagiaire.getRechercheCritere());
				Stage thisStage = (Stage) rootVueAppAdmin.getScene().getWindow();
				DirectoryChooser directoryChooser = new DirectoryChooser();
		        File selectedDirectory = directoryChooser.showDialog(thisStage);
		        String cheminFichier = selectedDirectory.getAbsolutePath();
		        
				
		        System.out.println(selectedDirectory.getAbsolutePath());
		        System.out.println(selectedDirectory.getPath());
		        System.out.println(selectedDirectory.getAbsoluteFile());
		        
				tableViewStagiaire.getSauvegardeBin().ecrireFichierTxt(listeNoeud, cheminFichier);
				

			});
			

			// bouton importer fichier
			btnFile.setOnAction(event -> {
				Stage dialog = new Stage();
				VueAjouterFichier vueAjouterFichier = new VueAjouterFichier();
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.setResizable(false);
				dialog.setTitle("Importer un fichier texte");

				// action sur bouton importer fichier (on charge le fichier et on cree une
				// tableview à partir de son contenu)
				vueAjouterFichier.getBtnValider1().setOnAction(e -> {
					String fichierTXT = vueAjouterFichier.getFichierTXT();
					System.out.println("fichier TXT " + fichierTXT);
					this.tableViewStagiaire = new TableViewStagiaire(fichierTXT, modeUtilisateur);
					this.stackpane = new StackPane();
					this.stackpane.getChildren().add(tableViewStagiaire);
					this.stackpane.setPadding(new Insets(16, 0, 0, 0));
					rootVueAppAdmin.setCenter(stackpane);

					dialog.close();
				});

				// action sur le bouton annuler
				vueAjouterFichier.getBtnRetour1().setOnAction(e -> {
					dialog.close();
				});

				dialog.setScene(vueAjouterFichier);
				dialog.showAndWait();

			});
			
			/*
			btnDoc.setOnAction(event -> {
				
				File file = new File("C:/Users/YourUsername/Desktop/Test.pdf");
				try {
	                Desktop.getDesktop().open(file);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
				
				File file = new File("C:/filePath/Test.pdf");
				HostServices hostServices = getHostServices();
				hostServices.showDocument(file.getAbsolutePath());
				//HostServices hostServices = getHostServices();
				//hostServices.showDocument(getClass()
					    //.getResource("computer_graphics_tutorial.pdf").toString());
				//File file = new File("C:/Users/YourUsername/Desktop/Test.pdf");
				//HostServices hostServices = getHostServices();
				//hostServices.showDocument(file.getAbsolutePath());
				 * 
				 
			});
			*/
			
			
			
			// FALSE CSS
			if (os.equals("PC")) {
				welcome.setFont(FONTTITRE);
				espaceNom.setFont(FONTTEXTE);
				btnAjoutSta.setFont(FONTBUTTON);
				btnImpress.setFont(FONTBUTTON);
				btnFile.setFont(FONTBUTTON);
				btnDoc.setFont(FONTBUTTON);
			}
		    
			btnAjoutSta.setTextFill(Color.rgb(61, 110, 139));
			btnAjoutSta.setStyle(BUTTONCOLOR);
			btnAjoutSta.setOnMouseEntered((event) -> {
				btnAjoutSta.setStyle(BUTTONCOLOROVER);
				btnAjoutSta.setTextFill(Color.rgb(240, 240, 240));
			});
			btnAjoutSta.setOnMouseExited((event) -> {
				btnAjoutSta.setStyle(BUTTONCOLOR);
				btnAjoutSta.setTextFill(Color.rgb(61, 110, 139));
			});
			
			btnImpress.setTextFill(Color.rgb(61, 110, 139));
			btnImpress.setStyle(BUTTONCOLOR);
			btnImpress.setOnMouseEntered((event) -> {
				btnImpress.setStyle(BUTTONCOLOROVER);
				btnImpress.setTextFill(Color.rgb(240, 240, 240));
			});
			btnImpress.setOnMouseExited((event) -> {
				btnImpress.setStyle(BUTTONCOLOR);
				btnImpress.setTextFill(Color.rgb(61, 110, 139));
			});
			
			btnFile.setTextFill(Color.rgb(61, 110, 139));
			btnFile.setStyle(BUTTONCOLOR);
			btnFile.setOnMouseEntered((event) -> {
				btnFile.setStyle(BUTTONCOLOROVER);
				btnFile.setTextFill(Color.rgb(240, 240, 240));
			});
			btnFile.setOnMouseExited((event) -> {
				btnFile.setStyle(BUTTONCOLOR);
				btnFile.setTextFill(Color.rgb(61, 110, 139));
			});
			
			btnDoc.setTextFill(Color.rgb(61, 110, 139));
			btnDoc.setStyle(BUTTONCOLOR);
			btnDoc.setOnMouseEntered((event) -> {
				btnDoc.setStyle(BUTTONCOLOROVER);
				btnDoc.setTextFill(Color.rgb(240, 240, 240));
			});
			btnDoc.setOnMouseExited((event) -> {
				btnDoc.setStyle(BUTTONCOLOR);
				btnDoc.setTextFill(Color.rgb(61, 110, 139));
			});
			
			btnAjoutSta.setMaxWidth(Double.MAX_VALUE);
			btnImpress.setMaxWidth(Double.MAX_VALUE);
			btnDoc.setMaxWidth(Double.MAX_VALUE);
			btnFile.setMaxWidth(Double.MAX_VALUE);
				
		}
		
		
		//Getters and setters --------------------------------------------------------------------------------------
		public Button getBtnFile() {
			return btnFile;
		}
		public void setBtnFile(Button btnFile) {
			this.btnFile = btnFile;
		}
		public Button getBtnAjoutSta() {
			return btnAjoutSta;
		}
		public void setBtnAjoutSta(Button btnAjoutSta) {
			this.btnAjoutSta = btnAjoutSta;
		}


		public Button getBtnDoc() {
			return btnDoc;
		}


		public void setBtnDoc(Button btnDoc) {
			this.btnDoc = btnDoc;
		}


		public Button getBtnImpress() {
			return btnImpress;
		}


		public void setBtnImpress(Button btnImpress) {
			this.btnImpress = btnImpress;
		}
		
		
		
			
}

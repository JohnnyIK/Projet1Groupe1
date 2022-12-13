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

import java.util.Currency;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		
		//attributs 
		VBox leftVbox;
		Button btnAjoutSta;
		Button btnRechAv;
		Button btnImpress;
		Button btnDoc;
		Button btnFile;
		
		VBox topVbox;
		Button btnNom;
		Button btnPrenom;
		Button btnDepartement;
		Button btnFormation;
		Button btnAnnee;
		
		public String modeUtilisateur = "";
		
		public TableViewStagiaire tableViewStagiaire;
		private ArbreBin sauvegardeHelper = new ArbreBin("");
		
		private StackPane stackpane;
		
		
		
		public VueAppAdmin (String modeUtilisateur, String nomUser, String prenomUser) {
			
			
			super(new BorderPane(), 1400, 800);
			//super(new BorderPane(), 1300, 800);
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
			BackgroundFill bgrFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
			leftVbox.setBackground(new Background(bgrFill));
			
			//boutonn dans vbox 
			btnAjoutSta = new Button("Ajouter Stagiaire");
			//btnRechAv = new Button("Recherche Avancée");
			btnImpress = new Button("Impression Liste");
			btnDoc = new Button("Guide utilisation");
			btnFile = new Button("Selectionner Fichier");
			leftVboxControls.add(btnAjoutSta);
			//leftVboxControls.add(btnRechAv);
			leftVboxControls.add(btnImpress);
			leftVboxControls.add(btnDoc);
			if (modeUtilisateur == "admin") {
				
			leftVboxControls.add(btnFile); //if modeutiliseur == admin , 
			}
			
			
			/*
			btnAjoutSta.setOnAction(event -> {
				
				
				Stage dialog = new Stage();
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.setResizable(false);
				
				
				
				
				GridPane rootAddIntern = new GridPane();
				rootAddIntern.setPrefWidth(600);
				rootAddIntern.setPrefHeight(600);
				
				rootAddIntern.setPadding(new Insets (50));
				rootAddIntern.setHgap(16);
				rootAddIntern.setVgap(16);
				
				this.setRoot(rootAddIntern);
				this.getRoot().setStyle("-fx-font-family: 'serif'");
				
				rootAddIntern.setStyle("-fx-font-family: 'serif'; -fx-background-image: url('file:src/main/java/icon/backgroundAcceuil.jpeg')");
				
				Label labelTitreAj = new Label("Enregistrez vos nouveaux stagiaires :");
				rootAddIntern.add(labelTitreAj, 1,0,3,1);
				
				Label labelnomAj = new Label("Nom  ");
				TextField fieldNom = new TextField("Entrez le nom");
				
				Label labelPrenomAj = new Label("Prenom  ");
				TextField fieldPrenom = new TextField("Entrez le prénom");
			
				Label labelDepAj = new Label("Département");
				TextField fieldDep = new TextField("Entrez le département");
				
				Label labelFormationAj = new Label("Formation ");
				TextField fieldFormation = new TextField("Entrez la formation");
				
				Label labelAnneeAj = new Label("Année");
				TextField fieldAnneeAj = new TextField("Entrez l'année");
				
				Text asterisque = new Text("*");
				Text obligatoire = new Text("(*) Champ requis");
				
				fieldNom.setOnMouseClicked((e) -> {
					fieldNom.setText("");
				});
				
//				fieldNom.setOnMouseExited((e) -> {
//					if (fieldNom.getText().equals("")) {
//					    fieldNom.setText("Entrez le nom");
//					}
//				});
				
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
				
				Button btnValiderAjoutStagiaire = new Button ("Ajouter");
				Button btnRetour = new Button ("Annuler");
				
				GridPane grilleButtons = new GridPane();
				grilleButtons.add(btnRetour, 0, 0);
				grilleButtons.add(btnValiderAjoutStagiaire, 1, 0);
				grilleButtons.setHgap(10);
				
				rootAddIntern.add(grilleButtons, 2, 8, 2, 1);
				
				btnValiderAjoutStagiaire.setOnAction(e -> {
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
				
				
				dialog.setTitle("Suppression");
				
				
				
				annuler.setOnAction((e) -> {
				    dialog.close();
				});
				
				supprimer.setOnAction((e) -> {
					
					System.out.println(stagiaire.getNom() + " " + stagiaire.getPrenom()
					+ " " + stagiaire.getDepartement() + " " + stagiaire.getFormation() + " "
					+ stagiaire.getAnneeFormation() + " [" + stagiaire.getIndex() + "] ");
					Noeud noeudVide = new Noeud();
					noeudVide.supprimerStagiaireNoeud(stagiaire.getIndex());
					//this.updateTableView(false, null);
					//TableViewStagiaire test = new TableViewStagiaire();
					//test.sauvegardeBin = new ArbreBin(CHEMIN_BIN);
					tableReference.testAppel();
					tableReference.rechercheRealTime();
					//tableReference.updateTableView(false, null);
					
				    dialog.close();
				    
				});
				

				Scene dialogScene = new Scene(rootAddIntern);
				dialog.setScene(dialogScene);
				dialog.showAndWait();

	         	
	         });
	         */
	         
			btnAjoutSta.setOnAction(event -> {
				Stage dialog = new Stage();
				VueAjouterStagiaire vueAjouterStagiaire = new VueAjouterStagiaire();
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.setResizable(false);
				dialog.setTitle("Ajouter un stagiaire");
				
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
					} else if (!this.sauvegardeHelper.textIsCorrect(nom) || nom.equals("Entrez le nom") || nom.equals("Nom invalide")) {
						vueAjouterStagiaire.getFieldNom().setText("Nom invalide");
					} else {
						this.sauvegardeHelper.nomCheckAndUpdate(nom);
						vueAjouterStagiaire.getStagiaire().setNom(nom);
						nomOK = true;
					}
					if (prenom.equals("")) {
						vueAjouterStagiaire.getFieldPrenom().setText("Champ requis");
					} else if (!this.sauvegardeHelper.textIsCorrect(prenom) || prenom.equals("Entrez le prénom") || prenom.equals("Prénom invalide")) {
						vueAjouterStagiaire.getFieldPrenom().setText("Prenom invalide");
					} else {
						this.sauvegardeHelper.prenomCheckAndUpdate(prenom);
						vueAjouterStagiaire.getStagiaire().setPrenom(prenom);
						prenomOK = true;
					}
					if (departement.equals("")) {
						vueAjouterStagiaire.getFieldDep().setText("Champ requis");
					} else if (!this.sauvegardeHelper.departementCheck(departement) || departement.equals("Entrez le département") || departement.equals("Département invalide")) {
						vueAjouterStagiaire.getFieldDep().setText("Département invalide");
					} else {
						departement = this.sauvegardeHelper.departementCheckAndUpdate(departement);
						vueAjouterStagiaire.getStagiaire().setDepartement(departement);
						departementOK = true;
					}
					if (formation.equals("")) {
						vueAjouterStagiaire.getFieldFormation().setText("Champ requis");
					} else if (!this.sauvegardeHelper.formationCheck(formation) || formation.equals("Entrez la formation") || formation.equals("Formation invalide")) {
						vueAjouterStagiaire.getFieldFormation().setText("Formation invalide");
					} else {
						this.sauvegardeHelper.formationCheckAndUpdate(formation);
						vueAjouterStagiaire.getStagiaire().setFormation(formation);
						formationOK = true;
					}
					if (anneeFormation.equals("")) {
						vueAjouterStagiaire.getFieldAnneeAj().setText("Champ requis");
					} else if (!this.sauvegardeHelper.anneeFormationCheck(anneeFormation) || anneeFormation.equals("Entrez l'année") || anneeFormation.equals("Année invalide")) {
						vueAjouterStagiaire.getFieldAnneeAj().setText("Année invalide");
					} else {
						this.sauvegardeHelper.anneeFormationCheckAndUpdate(anneeFormation);
						vueAjouterStagiaire.getStagiaire().setAnneeFormation(anneeFormation);
						anneeFormationOK = true;
					}
					if (nomOK == true && prenomOK == true && departementOK == true && formationOK == true && anneeFormationOK == true) {
						// VueAppAdmin vueAppAdmin = new VueAppAdmin(Stagiaire);
						// stage.setScene(vueAjouterStagiaire);
						// stage.setTitle(" Patrick School - Ajouter Stagiaire ");
						tableViewStagiaire.sauvegardeBin.ajouterStagiaireBin(vueAjouterStagiaire.getStagiaire());
						tableViewStagiaire.initRecherche();
						tableViewStagiaire.updateTableView(false, null);
						dialog.close();
					}
						
						
					
				});
				
				vueAjouterStagiaire.getBtnRetour().setOnAction(e -> {
						dialog.close();
				});
				
				dialog.setScene(vueAjouterStagiaire);
				dialog.showAndWait();
				
			 });
			
			btnFile.setOnAction(event -> {
				Stage dialog = new Stage();
				VueAjouterFichier vueAjouterFichier = new VueAjouterFichier();
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.setResizable(false);
				dialog.setTitle("Importer un fichier texte");
				
				/*
				vueAjouterStagiaire.getBtnvaliderAj().setOnAction(e -> {
					 Stage thisStage = (Stage) vboxfichier.getScene().getWindow();
						FileChooser fileOpen = new FileChooser();
						File file = fileOpen.showOpenDialog(thisStage);
						String filename = file.getName();
						txtSelected.setText(filename);
				});
				*/
				
				vueAjouterFichier.getBtnValider1().setOnAction(e -> {
					String fichierTXT = vueAjouterFichier.getFichierTXT();
					System.out.println("fichier TXT " + fichierTXT);
					
					
					this.tableViewStagiaire = new TableViewStagiaire(fichierTXT, modeUtilisateur);
					this.stackpane = new StackPane();
					this.stackpane.getChildren().add(tableViewStagiaire);
					this.stackpane.setPadding(new Insets(16,0,0,0));
					rootVueAppAdmin.setCenter(stackpane);
					/*
					TableViewStagiaire tableViewStagiaire2 = new TableViewStagiaire(fichierTXT);
					//this.tableViewStagiaire = tableViewStagiaire2;
					//this.tableViewStagiaire = null;
					StackPane stackpane2 = new StackPane();
					//this.stackpane = stackpane2;
					stackpane2.getChildren().add(tableViewStagiaire2);
					rootVueAppAdmin.setCenter(stackpane2);
					*/
					dialog.close();
					
			    });
				
				vueAjouterFichier.getBtnRetour1().setOnAction(e -> {
						dialog.close();
				});
				
				dialog.setScene(vueAjouterFichier);
				dialog.showAndWait();
				
			 });
			
			
			
			
			
			
			//Vbox mise dans le left
			rootVueAppAdmin.setLeft(leftVbox);
			
			//Hbox dans LE TOP
			VBox topVbox = new VBox(30);
			//ObservableList<Node>topVboxControls = topVbox.getChildren();
			Insets border2 = new Insets(40, 40, 40, 40);
			topVbox.setPadding(border2);
			topVbox.setSpacing(30);
			topVbox.setAlignment(Pos.CENTER);
			topVbox.setStyle("-fx-font-family: 'serif'; -fx-background-image: url('file:src/main/java/icon/backgroundAcceuil.jpeg')");
			//BackgroundFill bgrFill2 = new BackgroundFill(Color.LIGHTBLUE, null, null);
			//topVbox.setBackground(new Background(bgrFill2));
			GridPane infoUser = new GridPane();
			Label espaceNom;
			if (this.modeUtilisateur.equals("admin")) {
				espaceNom = new Label ("Espace Administrateur");
			} else {
				espaceNom = new Label ("Espace Utilisateur");
			}
			
			Label welcome = new Label ("Bienvenue, "+prenomUser+" "+nomUser+" !");
			infoUser.add(espaceNom, 0, 0);
			infoUser.add(welcome, 0, 1);
			topVbox.getChildren().add(infoUser);
			
			
			
			
			/*
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
			*/
		
			rootVueAppAdmin.setTop(topVbox);
			
			//stackpane dans center 
			stackpane = new StackPane();
			//ObservableList<Node>stackpaneControls = stackpane.getChildren();
			//Insets borderstack = new Insets(20, 20, 20, 20);
			//stackpane.setPadding(borderstack);
			//BackgroundFill bgrStack = new BackgroundFill(Color.GREEN, null, null);
			//stackpane.setBackground(new Background(bgrStack));
			
			
			//stackpane.getChildren().add(tableViewStagiaire);
			//HBox testTable = new HBox();
			//Label teste = new Label("TESTE");
			//testTable.getChildren().add(teste);
			
			//TableViewStagiaire tableViewStagiaire2 = new TableViewStagiaire();
			rootVueAppAdmin.setCenter(stackpane);
			//stackpane.setPrefWidth(200);
			stackpane.getChildren().add(tableViewStagiaire);
			stackpane.setPadding(new Insets(16,0,0,0));
			//rootVueAppAdmin.setCenter(testTable);
			//rootVueAppAdmin.setCenter(tableViewStagiaire2);
			//stackpane.setMinHeight(800);
			
			
			
			String topaffichee = "";
			topVbox.setId("boxRecherche");
			/*
			btnRechAv.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
					if (!(rootVueAppAdmin.getTop().getId().equals("boxRecherche"))) {
						rootVueAppAdmin.setTop(topHbox);
						
						
					}else { 
				
						
					}
				}
				
			});
			*/
			
			
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
			
			if (os.equals("PC")) {
				welcome.setFont(FONTTITRE);
				espaceNom.setFont(FONTTEXTE);
				//labelmdp.setFont(FONTTEXTE);
				btnAjoutSta.setFont(FONTBUTTON);
				btnImpress.setFont(FONTBUTTON);
				btnFile.setFont(FONTBUTTON);
				btnDoc.setFont(FONTBUTTON);
				//fieldId.setFont(FONTTEXTE);
				//fieldMdp.setFont(FONTTEXTE);
		    	
		    }
		    
			//labelTitreAdm.setTextFill(Color.rgb(60, 60, 60));
			//labelId.setTextFill(Color.rgb(60, 60, 60));
			//labelmdp.setTextFill(Color.rgb(60, 60, 60));
		    
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
			
}

package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.geometry.HPos;

import java.time.LocalDateTime;
//import org.controlsfx.control.RangeSlider;
import javafx.scene.text.Text;

import javafx.scene.control.Alert.AlertType;

public class TableViewStagiaire extends HBox implements ParametreGestionnaire {

	public TableView<StagiaireTableView> table = new TableView<StagiaireTableView>();
	public ArrayList<Integer> listeImpression = new ArrayList<>();
	public List<String> departementRech = new ArrayList<String>();
	public String[] anneeFormationRech = { "", "" };

	public RechercheMulticritere rechercheCritere = new RechercheMulticritere(false, "", false, "", true, departementRech,
			false, "", false, anneeFormationRech);
	
	Text nbrResultatText = new Text();
	
	public HBox tableViewStagiaireCont;
	public TextField nomRechTextField;
	public TextField prenomRechTextField;
	public TextField departementRechTextField;
	public TextField formationRechTextField;
	public TextField anneeMinRechTextField;
	public TextField anneeMaxRechTextField;
	
	public ArbreBin sauvegardeBin;
	
	ComboBox anneeMincomboBox;
	ComboBox anneeMaxcomboBox;
	

	public TableViewStagiaire(String cheminTXT) {

		super(new HBox());
		tableViewStagiaireCont = new HBox();
		this.importerTxtToTableView(cheminTXT);
//		this.sauvegardeBin = new ArbreBin(CHEMIN_BIN);
//		this.sauvegardeBin.importAnnuaireTexte();
//		this.sauvegardeBin.afficherFichierBin();
		Stagiaire s15 = new Stagiaire("ZZZZZZZZZZ", "fhfjhf ", "75", "CDA 22", "2022");
		//this.sauvegardeBin.ajouterStagiaireBin(s15);
		this.sauvegardeBin.afficheAnneeListeConsole();
		GridPane grille = new GridPane();
		
		//this.setMinWidth(2000);
		this.getChildren().add(grille);
		this.setAlignment(Pos.CENTER);
		//grille.setMinWidth(1000);
		//grille.setMaxWidth(1500);
		
		

		//departementRech.add("91");
		//departementRech.add("75");
		//anneeFormationRech[0] = "2000";
		//anneeFormationRech[1] = "2007";

		// Création de la table

		// TableView<StagiaireTableView> table = new TableView<StagiaireTableView>();

		table.setEditable(true);
		
		//table.minHeight(2000);
		table.setMinWidth(500);
		//table.setMaxWidth(1000);
		table.setPrefWidth(2000);
		
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


		Label label = new Label("");
		Label labelRecherche = new Label("Recherche par critères :");
		label.setFont(new Font("Arial", 20));

		// Création des trois colonnes

		TableColumn<StagiaireTableView, String> indexCol = new TableColumn<StagiaireTableView, String>("Index");
		indexCol.setMinWidth(10);
		indexCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("index"));

		TableColumn<StagiaireTableView, String> nomCol = new TableColumn<StagiaireTableView, String>("Nom");
		nomCol.setMinWidth(100);
		//nomCol.setMaxWidth(150);
		nomCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("nom"));
		//nomCol.setStyle("-fx-alignment: CENTER-LEFT;");

		

		TableColumn<StagiaireTableView, String> prenomCol = new TableColumn<StagiaireTableView, String>("Prénom");
		prenomCol.setMinWidth(100);
		prenomCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("prenom"));

		TableColumn<StagiaireTableView, String> departementCol = new TableColumn<StagiaireTableView, String>("Département");
		departementCol.setMinWidth(10);
		departementCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("departement"));

		TableColumn<StagiaireTableView, String> formationCol = new TableColumn<StagiaireTableView, String>("Formation");
		formationCol.setMinWidth(20);
		formationCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("formation"));

		TableColumn<StagiaireTableView, String> anneeFormationCol = new TableColumn<StagiaireTableView, String>(
				"Année de Formation");
		anneeFormationCol.setMinWidth(50);
		anneeFormationCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("anneeFormation"));

		TableColumn<StagiaireTableView, String> modCol = new TableColumn<StagiaireTableView, String>("Modifier");
		modCol.setMinWidth(100);
		modCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("modifierCheckBox"));

		TableColumn<StagiaireTableView, String> btnModCol = new TableColumn<StagiaireTableView, String>("Modifier");
		btnModCol.setMinWidth(100);
		btnModCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("btnModifier"));

		TableColumn<StagiaireTableView, String> btnSuppCol = new TableColumn<StagiaireTableView, String>("Supprimer");
		btnSuppCol.setMinWidth(100);
		btnSuppCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("btnSupprimer"));

		// On ajoute les trois colonnes à la table
		// table.getColumns().addAll(nomCol, prenomCol, deptCol, formationCol,
		// anneeFormationCol , modCol, btnModCol, btnSuppCol);
		table.getColumns().addAll(nomCol, prenomCol, departementCol, formationCol, anneeFormationCol);
		//table.
		//table.getColumns().get(1).
	

		// On rempli la table avec la liste observable
		this.updateTableView(false, null);

		//addButtonToTable("MOD", "modifier");
		addButtonToTable("", "Supprimer");
		//addCheckBoxToTable("CHECK", "ccc");

		/*
		// METHODE LORSQU'ON SELECTIONNE UNE LIGNE DU TABLEAU
		// table.getSelectionModel().selectedIndexProperty().addListener(observable ->
		// System.out.printf("Indice sélectionné: %d",
		// table.getSelectionModel().getSelectedIndex()).println());
		table.getSelectionModel().selectedIndexProperty().addListener(observable -> System.out
				.printf("Index sélectionné: %d", table.getSelectionModel().getSelectedItem().getIndex()).println());
		*/
		
		// MODIFICATION NOM OK
		nomCol.setCellFactory(TextFieldTableCell.<StagiaireTableView>forTableColumn());
		nomCol.setOnEditCommit((CellEditEvent<StagiaireTableView, String> event) -> {
			TablePosition<StagiaireTableView, String> pos = event.getTablePosition();

			int row = pos.getRow();
			StagiaireTableView stagiaire = event.getTableView().getItems().get(row);
			
			String nom = event.getNewValue();

			if (!this.sauvegardeBin.textIsCorrect(nom)) {
				Alert a = new Alert(AlertType.NONE);
		        a.setAlertType(AlertType.WARNING);
		        a.setTitle("Nom invalide");
		        a.setHeaderText(null);
		        a.setContentText("Veuillez entrer un prénom valide");
		        a.show();
		        this.updateTableView(false, rechercheCritere);
			} else {
				this.sauvegardeBin.ecrireNomBinaire(stagiaire.getIndex(), this.sauvegardeBin.prenomCheckAndUpdate(nom));
				this.updateTableView(false, null);
			}
			
			// person.setNom(newFullName);

		});
		
		// MODIFICATION PRENOM OK
		prenomCol.setCellFactory(TextFieldTableCell.<StagiaireTableView>forTableColumn());
		prenomCol.setOnEditCommit((CellEditEvent<StagiaireTableView, String> event) -> {
			TablePosition<StagiaireTableView, String> pos = event.getTablePosition();

			int row = pos.getRow();
			StagiaireTableView stagiaire = event.getTableView().getItems().get(row);

			String prenom = event.getNewValue();
			System.out.println("TEST");

			if (!this.sauvegardeBin.textIsCorrect(prenom)) {
				Alert a = new Alert(AlertType.NONE);
				a.setAlertType(AlertType.WARNING);
				a.setTitle("Prénom invalide");
				a.setHeaderText(null);
				a.setContentText("Veuillez entrer un prénom valide");
				a.show();
				this.updateTableView(false, rechercheCritere);
			} else {
				this.sauvegardeBin.ecrirePrenomBinaire(stagiaire.getIndex(),
				this.sauvegardeBin.prenomCheckAndUpdate(prenom));
				this.updateTableView(false, rechercheCritere);
			}
		});

		// MODIFICATION DEPARTEMENT OK
		departementCol.setCellFactory(TextFieldTableCell.<StagiaireTableView>forTableColumn());
		departementCol.setOnEditCommit((CellEditEvent<StagiaireTableView, String> event) -> {
			TablePosition<StagiaireTableView, String> pos = event.getTablePosition();

			int row = pos.getRow();
			StagiaireTableView stagiaire = event.getTableView().getItems().get(row);

			String departement = event.getNewValue();

			if (!this.sauvegardeBin.departementCheck(departement)) {
				Alert a = new Alert(AlertType.NONE);
				a.setAlertType(AlertType.WARNING);
				a.setTitle("Département invalide");
				a.setHeaderText(null);
				a.setContentText("Veuillez entrer un département français valide");
				a.show();
				this.updateTableView(false, rechercheCritere);
			} else {
				this.sauvegardeBin.ecrireDepartementBinaire(stagiaire.getIndex(),
				this.sauvegardeBin.departementCheckAndUpdate(departement));
				this.updateTableView(false, rechercheCritere);
				// person.setNom(newFullName);
			}

		});

		// MODIFICATION FORMATION OK
		formationCol.setCellFactory(TextFieldTableCell.<StagiaireTableView>forTableColumn());
		formationCol.setOnEditCommit((CellEditEvent<StagiaireTableView, String> event) -> {
			TablePosition<StagiaireTableView, String> pos = event.getTablePosition();

			int row = pos.getRow();
			StagiaireTableView stagiaire = event.getTableView().getItems().get(row);

			String formation = event.getNewValue();

			if (!this.sauvegardeBin.formationCheck(formation)) {
				Alert a = new Alert(AlertType.NONE);
				a.setAlertType(AlertType.WARNING);
				a.setTitle("Formation invalide");
				a.setHeaderText(null);
				a.setContentText("Veuillez entrer une formation valide");
				a.show();
				this.updateTableView(false, rechercheCritere);
			} else {
				this.sauvegardeBin.ecrireFormationBinaire(stagiaire.getIndex(),
						this.sauvegardeBin.formationCheckAndUpdate(formation));
				this.updateTableView(false, rechercheCritere);
			}
			// person.setNom(newFullName);

		});

		// MODIFICATION ANNEEFORMATION OK
		anneeFormationCol.setCellFactory(TextFieldTableCell.<StagiaireTableView>forTableColumn());
		anneeFormationCol.setOnEditCommit((CellEditEvent<StagiaireTableView, String> event) -> {
			TablePosition<StagiaireTableView, String> pos = event.getTablePosition();

			int row = pos.getRow();
			StagiaireTableView stagiaire = event.getTableView().getItems().get(row);
			String newAnneeFormation = event.getNewValue();
			System.out.println("TEST");

			if (!this.sauvegardeBin.anneeFormationCheck(newAnneeFormation)) {
				// create a alert
				Alert a = new Alert(AlertType.NONE);
				a.setAlertType(AlertType.WARNING);
				a.setTitle("Année invalide");
				a.setHeaderText(null);
				a.setContentText("Veuillez entrer une année valide");
				a.show();
				this.updateTableView(false, rechercheCritere);
			} else {
				System.out.println("TTTT");
				this.sauvegardeBin.ecrireAnneeFormationBinaire(stagiaire.getIndex(),
						this.sauvegardeBin.anneeFormationCheckAndUpdate(newAnneeFormation));
				this.updateTableView(false, rechercheCritere);
			}
			System.out.println("TTTT");
		});
		
	


		
		/*
		TextField nomRechTextField = new TextField();
		TextField prenomRechTextField = new TextField();
		TextField departementRechTextField = new TextField();
		TextField formationRechTextField = new TextField();
//         Slider anneeRechSlider = new Slider();
//         anneeRechSlider.setMin(1980);
//         Date date = new Date();
//         anneeRechSlider.setMax(date.getYear());
//		//Instantiating the RangeSlider class
//	      RangeSlider slider = new RangeSlider(0, 100, 10, 90);
//	      //Setting the slider properties
//	      slider.setShowTickLabels(true);
//	      slider.setShowTickMarks(true);
//	      slider.setMajorTickUnit(25);
//	      slider.setBlockIncrement(10);
		TextField anneeMinRechTextField = new TextField();
		TextField anneeMaxRechTextField = new TextField();
		*/
		
		this.nomRechTextField = createTextFieldRechercheInput();
		this.prenomRechTextField = createTextFieldRechercheInput();
		this.departementRechTextField = createTextFieldRechercheInput();
		this.formationRechTextField = createTextFieldRechercheInput();
		this.anneeMinRechTextField = createTextFieldRechercheInput();
		this.anneeMaxRechTextField = createTextFieldRechercheInput();

		Label nomRechLabel = new Label("Nom");
		Label prenomRechLabel = new Label("Prénom");
		Label departementRechLabel = new Label("Département");
		Label formationRechLabel = new Label("Formation");
		Label anneeFormationRechLabel = new Label("Année de formation");
		Label anneeFormationRechLabelDe = new Label("De");
		Label anneeFormationRechLabelA = new Label("à");
		
		Label nbrResultatLabel = new Label("Résultats : ");
		
		CheckBox nomSelect = new CheckBox("Nom");
		CheckBox prenomSelect = new CheckBox("Prénom");
		CheckBox departementSelect = new CheckBox("Département");
		CheckBox formationSelect = new CheckBox("Formation");
		CheckBox anneeFormationSelect = new CheckBox("Année");
		
		
		
		ArrayList<String> anneeListeMin = this.sauvegardeBin.getAnneeArrayListe();
		ArrayList<String> anneeListeMax = new ArrayList<String>();
		anneeMincomboBox = new ComboBox(FXCollections.observableArrayList(anneeListeMin));
		anneeMaxcomboBox = new ComboBox();
		anneeMaxcomboBox.setDisable(true);
		anneeMincomboBox.setPrefWidth(70);
		anneeMaxcomboBox.setPrefWidth(70);

		
		
		
		
		
		//anneeMincomboBox.setOn
		
		//Button btnRecherche = new Button();
		//btnRecherche.setText("Rechercher");
		Button btnEffacerRecherche = new Button();
		btnEffacerRecherche.setText("Réinitialiser");
		
		btnEffacerRecherche.setOnAction(e -> {
			initRecherche();
			/*
			nomRechTextField.setText("");
			prenomRechTextField.setText("");
			formationRechTextField.setText("");
			//anneeMinRechTextField.setText("");
			//anneeMaxRechTextField.setText("");
			anneeMincomboBox.setValue(null);
			anneeMaxcomboBox.setValue(null);
			anneeMaxcomboBox.setDisable(true);
			this.updateTableView(false, rechercheCritere);
			*/
		});
		
		
		anneeMincomboBox.setOnAction((event) -> {
			int selectedIndex = anneeMincomboBox.getSelectionModel().getSelectedIndex();
		    String selectedItem = (String) anneeMincomboBox.getSelectionModel().getSelectedItem();
		    anneeMaxcomboBox.setDisable(false);
		    if (anneeMincomboBox.getValue() != null) {
		    	anneeListeMax.clear();
		      for (String annee : anneeListeMin) {
		    	if (annee.compareTo(selectedItem) > 0) {
		    		anneeListeMax.add(annee);
		    	}
		     }
		    }
		    /*
		    if (anneeMaxcomboBox.isDisable() == true) {
			    anneeMaxcomboBox.setDisable(false);
			    if (anneeMincomboBox.getValue() != null) {
			      for (String annee : anneeListeMin) {
			    	if (annee.compareTo(selectedItem) > 0) {
			    		anneeListeMax.add(annee);
			    	}
			     }
			    }
		    } else if (anneeMaxcomboBox.isDisable() == false) {
		    	System.out.println("TEEEEESt");
		    	anneeMaxcomboBox.setValue(null);
		    	if (anneeMincomboBox.getValue() != null) {
				      for (String annee : anneeListeMin) {
				    	if (annee.compareTo(selectedItem) > 0) {
				    		anneeListeMax.add(annee);
				    	}
				     }
				    }
		    }
		    */
		    anneeMaxcomboBox.setItems(FXCollections.observableArrayList(anneeListeMax));
		    this.rechercheRealTime();

		    System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
		    System.out.println("   ComboBox.getValue(): " + anneeMincomboBox.getValue());
		});
		
		
		
		anneeMaxcomboBox.setOnAction((event) -> {
			this.rechercheRealTime();
		});
		
		
//		nomRechTextField.setOnKeyPressed((KeyEvent event) -> {
//					String newNom = nomRechTextField.getText();
//					System.out.println(newNom);
//		        	rechercheCritere.setNomRechSelect(nomSelect.isSelected());
//					rechercheCritere.setNomRech(newNom);
//					rechercheCritere.setPrenomRechSelect(prenomSelect.isSelected());
//					rechercheCritere.setPrenomRech(prenomRechTextField.getText());
//					rechercheCritere.setDepartementRechSelect(departementSelect.isSelected());
//					departementRech.add(departementRechTextField.getText());
//					rechercheCritere.setDepartementRech(departementRech);
//					rechercheCritere.setFormationRechSelect(formationSelect.isSelected());
//					rechercheCritere.setFormationRech(formationRechTextField.getText());
//					rechercheCritere.setAnneeFormationRechSelect(anneeFormationSelect.isSelected());
//					anneeFormationRech[0] = anneeMinRechTextField.getText();
//					anneeFormationRech[1] = anneeMaxRechTextField.getText();
//					rechercheCritere.setAnneeFormationRech(anneeFormationRech);
//					this.updateTableView(CHEMIN_BIN, true, rechercheCritere);
//		});
		
		/*
		nomRechTextField.setOnKeyReleased((KeyEvent event) -> {
			Boolean rechercheActivee;
			String nom = nomRechTextField.getText();
			String prenom = prenomRechTextField.getText();
			String formation = formationRechTextField.getText();
			//rechercheCritere.setNomRechSelect(nomSelect.isSelected());
			if (!nom.equals("")) {
				rechercheCritere.setNomRechSelect(true);
				//rechercheActivee = true;
			} else {
				rechercheCritere.setNomRechSelect(false);
				//rechercheActivee = false;
			}
			if (!prenom.equals("")) {
				rechercheCritere.setPrenomRechSelect(true);
				//rechercheActivee = true;
			} else {
				rechercheCritere.setPrenomRechSelect(false);
				//rechercheActivee = false;
			}
			if (!formation.equals("")) {
				rechercheCritere.setFormationRechSelect(true);
				//rechercheActivee = true;
			} else {
				rechercheCritere.setFormationRechSelect(false);
				//rechercheActivee = false;
			}
			if (rechercheCritere.isNomRechSelect() || rechercheCritere.isPrenomRechSelect() || rechercheCritere.isDepartementRechSelect() || rechercheCritere.isFormationRechSelect() || rechercheCritere.isAnneeFormationRechSelect()) {
				rechercheActivee = true;
			} else {
				rechercheActivee = false;
			}
			rechercheCritere.setNomRech(nom);
			//rechercheCritere.setPrenomRechSelect(prenomSelect.isSelected());
			rechercheCritere.setPrenomRech(prenom);
			rechercheCritere.setDepartementRechSelect(departementSelect.isSelected());
			departementRech.add(departementRechTextField.getText());
			rechercheCritere.setDepartementRech(departementRech);
			rechercheCritere.setFormationRechSelect(formationSelect.isSelected());
			//rechercheCritere.setFormationRech(formation);
			rechercheCritere.setAnneeFormationRechSelect(anneeFormationSelect.isSelected());
			anneeFormationRech[0] = anneeMinRechTextField.getText();
			anneeFormationRech[1] = anneeMaxRechTextField.getText();
			rechercheCritere.setAnneeFormationRech(anneeFormationRech);
			this.updateTableView(CHEMIN_BIN, rechercheActivee, rechercheCritere);
		});
		
		
		prenomRechTextField.setOnKeyReleased((KeyEvent event) -> {
			Boolean rechercheActivee;
			String nom = nomRechTextField.getText();
			String prenom = prenomRechTextField.getText();
			String formation = formationRechTextField.getText();
			//rechercheCritere.setNomRechSelect(nomSelect.isSelected());
			if (!nom.equals("")) {
				rechercheCritere.setNomRechSelect(true);
				//rechercheActivee = true;
			} else {
				rechercheCritere.setNomRechSelect(false);
				//rechercheActivee = false;
			}
			if (!prenom.equals("")) {
				rechercheCritere.setPrenomRechSelect(true);
				//rechercheActivee = true;
			} else {
				rechercheCritere.setPrenomRechSelect(false);
				//rechercheActivee = false;
			}
			if (!formation.equals("")) {
				rechercheCritere.setFormationRechSelect(true);
				//rechercheActivee = true;
			} else {
				rechercheCritere.setFormationRechSelect(false);
				//rechercheActivee = false;
			}
			if (rechercheCritere.isNomRechSelect() || rechercheCritere.isPrenomRechSelect() || rechercheCritere.isDepartementRechSelect() || rechercheCritere.isFormationRechSelect() || rechercheCritere.isAnneeFormationRechSelect()) {
				rechercheActivee = true;
			} else {
				rechercheActivee = false;
			}
			rechercheCritere.setNomRech(nom);
			//rechercheCritere.setPrenomRechSelect(prenomSelect.isSelected());
			rechercheCritere.setPrenomRech(prenom);
			rechercheCritere.setDepartementRechSelect(departementSelect.isSelected());
			departementRech.add(departementRechTextField.getText());
			rechercheCritere.setDepartementRech(departementRech);
			rechercheCritere.setFormationRechSelect(formationSelect.isSelected());
			//rechercheCritere.setFormationRech(formation);
			rechercheCritere.setAnneeFormationRechSelect(anneeFormationSelect.isSelected());
			anneeFormationRech[0] = anneeMinRechTextField.getText();
			anneeFormationRech[1] = anneeMaxRechTextField.getText();
			rechercheCritere.setAnneeFormationRech(anneeFormationRech);
			this.updateTableView(CHEMIN_BIN, rechercheActivee, rechercheCritere);
		});
		*/


		// handleSupprimer() ;

		//BorderPane.setMargin(btn, new Insets(5, 5, 5, 5));
		// Center

		// grille.setAlignment(Pos.CENTER);
		
		//grille.setPadding(new Insets(10, 10, 10, 10));

//		grille.add(label, 0, 1);
//		grille.add(table, 2, 3);
//		grille.add(btn, 0, 2);
//		grille.add(btn1, 0, 3);
//		grille.add(nomRechLabel, 0, 4);

		GridPane grilleRecherche = new GridPane();
		
		grilleRecherche.setPrefWidth(1030);
		
		grilleRecherche.add(label, 0, 0, 16,1);
		grilleRecherche.add(labelRecherche, 0, 1, 16,1);
		grilleRecherche.add(nomRechLabel, 0, 3, 1,1);
		//grille.add(nomSelect, 0, 3, 1,1);
		grilleRecherche.add(nomRechTextField, 0, 4, 1,1);
		grilleRecherche.add(prenomRechLabel, 2, 3, 1,1);
		grilleRecherche.add(prenomRechTextField, 2, 4, 1,1);
		grilleRecherche.add(departementRechLabel, 4, 3, 1,1);
		grilleRecherche.add(departementRechTextField, 4, 4, 1,1);
		grilleRecherche.add(formationRechLabel, 6, 3, 1,1);
		grilleRecherche.add(formationRechTextField, 6, 4, 1,1);
		grilleRecherche.add(anneeFormationRechLabel, 8, 3, 5,1);
		grilleRecherche.add(anneeFormationRechLabelDe, 8, 4, 1,1);
		//grille.add(anneeMinRechTextField, 10, 3, 1, 1);
		grilleRecherche.add(anneeMincomboBox, 9, 4, 1, 1);
		grilleRecherche.add(anneeFormationRechLabelA, 10, 4, 1, 1);
		//grille.add(anneeMaxRechTextField, 12, 3, 1,1);
		grilleRecherche.add(anneeMaxcomboBox, 11, 4, 1,1);
		//grille.add(btnRecherche, 15, 2, 1,1);
		grilleRecherche.add(btnEffacerRecherche, 14, 4, 1,1);
		//Line line = new Line(0, 0, grilleRecherche.getPrefWidth(), 0);
		//grilleRecherche.add(line, 0, 6, 15,1);
		
		grilleRecherche.setHgap(15);
		grilleRecherche.setVgap(5);
		
		
		
		grilleRecherche.setPadding(new Insets(10));
		
		grilleRecherche.setBackground(new Background(new BackgroundFill(Color.rgb(237, 237, 237), null, null)));
		grilleRecherche.setStyle("-fx-border: 2; -fx-border-color:rgb(212, 212, 212);");
		
		grille.setHgap(15);
		grille.setVgap(5);
		
		//grille.setHalignment(label, HPos.RIGHT);
		
		/*
		grille.add(label, 0, 0, 16,1);
		grille.add(labelRecherche, 1, 1, 16,1);
		grille.add(nomRechLabel, 1, 2, 1,1);
		//grille.add(nomSelect, 0, 3, 1,1);
		grille.add(nomRechTextField, 1, 3, 1,1);
		grille.add(prenomRechLabel, 3, 2, 1,1);
		grille.add(prenomRechTextField, 3, 3, 1,1);
		grille.add(departementRechLabel, 5, 2, 1,1);
		grille.add(departementRechTextField, 5, 3, 1,1);
		grille.add(formationRechLabel, 7, 2, 1,1);
		grille.add(formationRechTextField, 7, 3, 1,1);
		grille.add(anneeFormationRechLabel, 9, 2, 5,1);
		grille.add(anneeFormationRechLabelDe, 9, 3, 1,1);
		//grille.add(anneeMinRechTextField, 10, 3, 1, 1);
		grille.add(anneeMincomboBox, 10, 3, 1, 1);
		grille.add(anneeFormationRechLabelA, 11, 3, 1, 1);
		//grille.add(anneeMaxRechTextField, 12, 3, 1,1);
		grille.add(anneeMaxcomboBox, 12, 3, 1,1);
		//grille.add(btnRecherche, 15, 2, 1,1);
		grille.add(btnEffacerRecherche, 15, 3, 1,1);
		grille.add(line, 1, 4, 15,1);
		*/
		
		 
		
		grille.add(grilleRecherche, 1, 0, 15,1);
		grille.add(table, 1, 6, 15,1);
		
		
		
		ColumnConstraints colFirst = new ColumnConstraints();
		ColumnConstraints colVide = new ColumnConstraints();
		ColumnConstraints colTextField = new ColumnConstraints();
		ColumnConstraints colButton = new ColumnConstraints();
		ColumnConstraints colSmall = new ColumnConstraints();
		//ColumnConstraints colDoubleTextField = new ColumnConstraints();
		colFirst.setPercentWidth(0);
		colSmall.setPercentWidth(2);
		colVide.setPercentWidth(5);
		colTextField.setPercentWidth(15);
		colButton.setMaxWidth(10);
		//grille.getColumnConstraints().addAll(colFirst, colTextField, colVide, colTextField, colVide, colTextField, colVide, colTextField, colVide, colSmall, colTextField, colSmall, colTextField, colVide, colButton, colVide);
		//grilleRecherche.getColumnConstraints().addAll(colFirst, colTextField, colVide, colTextField, colVide, colTextField, colVide, colTextField, colVide, colSmall, colTextField, colSmall, colTextField, colVide, colButton, colVide);
		
	}

//    private void handleSupprimer() {
// 		btnSupprimer.setOnAction(new EventHandler<ActionEvent>() {
//
// 			@Override
// 			public void handle(ActionEvent event) {
// 				Stagiaire cleSupprimer = tableStagiaires.getSelectionModel().getSelectedItem();
// 				mesStagiaires.supprimerNoeud(cleSupprimer);
// 				setMesStagiaires(mesStagiaires);
// 			}
// 		});
// 	}
	/**
	 * TEST
	 */
	public void testAppel() {
		this.sauvegardeBin.afficheAnneeListeConsole();
		Stagiaire s15 = new Stagiaire("ZZZZZZZZZZ", "fhfjhf ", "75", "CDA 22", "2022");
		this.sauvegardeBin.ajouterStagiaireBin(s15);
	}
	

	/**
	 * Update la liste des stagiaires dans le TableView
	 * 
	 */
	public void updateTableView(Boolean rechActive, RechercheMulticritere rechercheCritere) {
		if (rechActive == false) {
			//this.initRecherche();
		}
		ObservableList<StagiaireTableView> getStagiaireTableViewList = getStagiaireTableViewList(rechActive, rechercheCritere);
		this.nbrResultatText.setText(String.valueOf(getStagiaireTableViewList.size())+" stagiaires trouvés");
		this.table.setItems(getStagiaireTableViewList(rechActive, rechercheCritere));
	}
	

	/**
	 * Transfere le fichier bin entre en argument en une ObservableList (utilisable
	 * par le TableView)
	 * 
	 * @return l'ObservableList faite de StagiaireTableView (index / nom / prenom /
	 *         departement / formation / anneeFormation)
	 */
	private ObservableList<StagiaireTableView> getStagiaireTableViewList(Boolean rechActive, RechercheMulticritere rechercheCritere) {

		//ArbreBin sauvegardeBin = new ArbreBin(cheminFichierBin);
		//sauvegardeBin.importAnnuaireTexte();
		//sauvegardeBin.afficheAnneeListeConsole(cheminFichierBin);
		//sauvegardeBin.afficherFichierBin();

		ArrayList<Noeud> listeNoeud = new ArrayList<Noeud>();
		this.sauvegardeBin.exportToArrayListOptionRecherche(listeNoeud, rechActive, rechercheCritere);
		ArrayList<StagiaireTableView> listeStagiaire = new ArrayList<StagiaireTableView>();
		for (Noeud noeud : listeNoeud) {
			StagiaireTableView contact = new StagiaireTableView(noeud.getIndexNoeud(), noeud.getCle().getNom(),
					noeud.getCle().getPrenom(), noeud.getCle().getDepartement(), noeud.getCle().getFormation(),
					noeud.getCle().getAnneeFormation());
			listeStagiaire.add(contact);
		}

		ObservableList<StagiaireTableView> list = FXCollections.observableArrayList(listeStagiaire);

		// SYSO pour test
		/*
		for (Noeud noeud : listeNoeud) {
			System.out.println("ArrayList :" + noeud.getCle().getNomFormate() + " " + noeud.getCle().getPrenomFormate()
					+ " " + noeud.getCle().getDepartementFormate() + " " + noeud.getCle().getFormationFormate() + " "
					+ noeud.getCle().getAnneeFormationFormate() + " [" + noeud.getIndexNoeud() + "] " + " "
					+ noeud.getIndexFilsGauche() + " " + noeud.getIndexFilsDroit() + " " + noeud.getIndexDoublon());
		}
		System.out.println("Nbr Stagiaires fichier BIN >> " + sauvegardeBin.nbrStagiaires());
		System.out.println("Nbr Stagiaires ArrayList >> " + listeNoeud.size());
		*/

		return list;
	}

	/**
	 * Ajout d'une colonne de boutons a la TableView
	 * 
	 * @param nomColonne  l'intitule de la colonne a ajouter
	 * @param labelBouton le label des boutons
	 */
	private void addButtonToTable(String nomColonne, String labelBouton) {
		TableColumn<StagiaireTableView, Void> colBtn = new TableColumn(nomColonne);
		TableViewStagiaire tableReference = this;
		RechercheMulticritere rechercheReference = this.rechercheCritere;

		Callback<TableColumn<StagiaireTableView, Void>, TableCell<StagiaireTableView, Void>> cellFactory = new Callback<TableColumn<StagiaireTableView, Void>, TableCell<StagiaireTableView, Void>>() {
			@Override
			public TableCell<StagiaireTableView, Void> call(final TableColumn<StagiaireTableView, Void> param) {
				final TableCell<StagiaireTableView, Void> cell = new TableCell<StagiaireTableView, Void>() {

					private final Button btn = new Button(labelBouton);

					{
						btn.setOnAction((ActionEvent event) -> {
							StagiaireTableView stagiaire = getTableView().getItems().get(getIndex());
							System.out.println(stagiaire.getNom() + " " + stagiaire.getPrenom()
									+ " " + stagiaire.getDepartement() + " " + stagiaire.getFormation() + " "
									+ stagiaire.getAnneeFormation() + " [" + stagiaire.getIndex() + "] ");
							
							Stage dialog = new Stage();
							dialog.initModality(Modality.APPLICATION_MODAL);
							dialog.setResizable(false);
							
							GridPane grilleDialog = new GridPane();
							grilleDialog.setHgap(10);
							grilleDialog.setVgap(10);
							grilleDialog.setPadding(new Insets(20, 50, 30, 50));
							
							// Setting the title
							dialog.setTitle("Suppression");
							Label texteLabel = new Label("Voulez-vous supprimer ce stagiaire de la liste ?");
							Label nomLabel = new Label("Nom");
							Label nom = new Label(stagiaire.getNom());
							Label prenomLabel = new Label("Prénom");
							Label prenom = new Label(stagiaire.getPrenom());
							Label departementLabel = new Label("Département");
							Label departement = new Label(stagiaire.getDepartement());
							Label formationLabel = new Label("Formation");
							Label formation = new Label(stagiaire.getFormation());
							Label anneeFormationLabel = new Label("Année de formation");
							Label anneeFormation = new Label(stagiaire.getAnneeFormation());
							
							Button annuler = new Button("Annuler");;
							Button supprimer = new Button("Supprimer");
							
							grilleDialog.add(texteLabel, 1, 1, 3, 1);
							grilleDialog.add(nomLabel, 1, 3, 1, 1);
							grilleDialog.add(nom, 1, 4, 1, 1);
							grilleDialog.add(prenomLabel, 2, 3, 1, 1);
							grilleDialog.add(prenom, 2, 4, 1, 1);
							grilleDialog.add(departementLabel, 3, 6, 1, 1);
							grilleDialog.add(departement, 3, 7, 1, 1);
							grilleDialog.add(formationLabel, 1, 6, 1, 1);
							grilleDialog.add(formation, 1, 7, 1, 1);
							grilleDialog.add(anneeFormationLabel, 2, 6, 1, 1);
							grilleDialog.add(anneeFormation, 2, 7, 1, 1);
							grilleDialog.add(annuler, 2, 9, 1, 1);
							grilleDialog.add(supprimer, 3, 9, 1, 1);
							
							grilleDialog.setHalignment(annuler,HPos.RIGHT);

							Scene dialogScene = new Scene(grilleDialog);

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
								//tableReference.testAppel();
								tableReference.rechercheRealTime();
								//tableReference.updateTableView(false, null);
								
							    dialog.close();
							});

							dialog.setScene(dialogScene);
							dialog.showAndWait();
							
							
							
							/*
							Dialog<String> dialog = new Dialog<String>();
							GridPane grilleDialog = new GridPane();
							grilleDialog.setHgap(10);
							grilleDialog.setVgap(10);
							grilleDialog.setPadding(new Insets(20, 150, 10, 10));
							
							// Setting the title
							dialog.setTitle("Suppression");
							Label texteLabel = new Label("Voulez-vous supprimer ce stagiaire de la liste ?");
							Label nomLabel = new Label("Nom");
							Label nom = new Label(stagiaire.getNom());
							Label prenomLabel = new Label("Prénom");
							Label prenom = new Label(stagiaire.getPrenom());
							Label departementLabel = new Label("Département");
							Label departement = new Label(stagiaire.getDepartement());
							Label formationLabel = new Label("Formation");
							Label formation = new Label(stagiaire.getFormation());
							Label anneeFormationLabel = new Label("Année de formation");
							Label anneeFormation = new Label(stagiaire.getAnneeFormation());
							
							ButtonType annulerBTN = new ButtonType("Annuler", ButtonData.OK_DONE);
							ButtonType supprimerBTN = new ButtonType("Supprimer", ButtonData.YES);
							
							Button annuler = new Button("Annuler");
							Button supprimer = new Button("Supprimer");
							
						
							
							

							
							grilleDialog.add(texteLabel, 1, 1, 3, 1);
							grilleDialog.add(nomLabel, 1, 3, 1, 1);
							grilleDialog.add(nom, 1, 4, 1, 1);
							grilleDialog.add(prenomLabel, 2, 3, 1, 1);
							grilleDialog.add(prenom, 2, 4, 1, 1);
							grilleDialog.add(departementLabel, 3, 6, 1, 1);
							grilleDialog.add(departement, 3, 7, 1, 1);
							grilleDialog.add(formationLabel, 1, 6, 1, 1);
							grilleDialog.add(formation, 1, 7, 1, 1);
							grilleDialog.add(anneeFormationLabel, 2, 6, 1, 1);
							grilleDialog.add(anneeFormation, 2, 7, 1, 1);
							grilleDialog.add(annuler, 2, 9, 1, 1);
							//grilleDialog.add(supprimer, 3, 9, 1, 1);
							
							
							//dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
							// Adding buttons to the dialog pane
							dialog.getDialogPane().setContent(grilleDialog);
							dialog.getDialogPane().getButtonTypes().add(annulerBTN);
							dialog.getDialogPane().getButtonTypes().add(supprimerBTN);
							// Setting the label
							Text txt = new Text("Click the button to show the dialog");
							Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
							txt.setFont(font);
							dialog.showAndWait();
							
							

							
							
					
							
//							if (annulerBTN.getButtonData() == ButtonData.OK_DONE){
//								System.out.println("TEST "+annulerBTN.getButtonData());
//								}
//							
//							if (supprimerBTN.getButtonData() == ButtonData.YES){
//							System.out.println("TEST "+supprimerBTN.getButtonData());
//							}
							
							


							
							
						
						
						
						*/
							
							
							
							
							
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};

		colBtn.setCellFactory(cellFactory);

		table.getColumns().add(colBtn);

	}

	/**
	 * Ajout d'une colonne de CheckBox a la TableView
	 * 
	 * @param nomColonne    l'intitule de la colonne a ajouter
	 * @param labelCheckBox le label des CheckBox
	 */
	private void addCheckBoxToTable(String nomColonne, String labelCheckBox) {
		TableColumn<StagiaireTableView, Void> colCheckBox = new TableColumn(nomColonne);

		Callback<TableColumn<StagiaireTableView, Void>, TableCell<StagiaireTableView, Void>> cellFactory = new Callback<TableColumn<StagiaireTableView, Void>, TableCell<StagiaireTableView, Void>>() {
			@Override
			public TableCell<StagiaireTableView, Void> call(final TableColumn<StagiaireTableView, Void> param) {
				final TableCell<StagiaireTableView, Void> cell = new TableCell<StagiaireTableView, Void>() {

					private final CheckBox checkbox = new CheckBox(labelCheckBox);

					{
						checkbox.setOnAction((ActionEvent event) -> {
							StagiaireTableView stagiaire = getTableView().getItems().get(getIndex());
							System.out.println("selectedData: " + stagiaire.getNom());
							listeImpression.add(stagiaire.getIndex());
							// SYSO pour test
							for (Integer i : listeImpression) {
								System.out.println("ArrayList index: " + i);
							}
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(checkbox);
						}
					}
				};
				return cell;
			}
		};

		colCheckBox.setCellFactory(cellFactory);

		table.getColumns().add(colCheckBox);

	}
	
	private TextField createTextFieldRechercheInput() {
		TextField textFieldRechercheInput = new TextField();
		
		textFieldRechercheInput.setOnKeyReleased((KeyEvent event) -> {
			this.rechercheRealTime();
		});
		return textFieldRechercheInput;
	}
	
	private void rechercheRealTime() {
		Boolean rechercheActivee;
		String nom = this.nomRechTextField.getText();
		String prenom = this.prenomRechTextField.getText();
		String formation = this.formationRechTextField.getText();
		String departement = this.departementRechTextField.getText();
		// String anneeMin = this.anneeMinRechTextField.getText();
		// String anneeMax = this.anneeMaxRechTextField.getText();
		String anneeMin = (String) anneeMincomboBox.getSelectionModel().getSelectedItem();
		String anneeMax = (String) anneeMaxcomboBox.getSelectionModel().getSelectedItem();
		System.out.println(anneeMin);
		System.out.println(anneeMax);
		// rechercheCritere.setNomRechSelect(nomSelect.isSelected());
		if (!nom.equals("")) {
			this.rechercheCritere.setNomRechSelect(true);
			// rechercheActivee = true;
		} else {
			this.rechercheCritere.setNomRechSelect(false);
			// rechercheActivee = false;
		}
		if (!prenom.equals("")) {
			this.rechercheCritere.setPrenomRechSelect(true);
			// rechercheActivee = true;
		} else {
			this.rechercheCritere.setPrenomRechSelect(false);
			// rechercheActivee = false;
		}
		if (!departement.equals("")) {
			this.rechercheCritere.setFormationRechSelect(true);
			// rechercheActivee = true;
		} else {
			this.rechercheCritere.setFormationRechSelect(false);
			// rechercheActivee = false;
		}
		if (!formation.equals("")) {
			this.rechercheCritere.setFormationRechSelect(true);
			// rechercheActivee = true;
		} else {
			this.rechercheCritere.setFormationRechSelect(false);
			// rechercheActivee = false;
		}
		if (anneeMin != null) {
			this.rechercheCritere.setAnneeFormationRechSelect(true);
			// rechercheActivee = true;
		} else {
			this.rechercheCritere.setAnneeFormationRechSelect(false);
			// rechercheActivee = false;
		}
		/*
		 * if (!((anneeMin.equals("")) || (anneeMax.equals("")))){
		 * this.rechercheCritere.setAnneeFormationRechSelect(true); //rechercheActivee =
		 * true; } else { this.rechercheCritere.setAnneeFormationRechSelect(false);
		 * //rechercheActivee = false; }
		 */
		this.rechercheCritere.setAnneeFormationRechSelect(true);
		if (this.rechercheCritere.isNomRechSelect() || this.rechercheCritere.isPrenomRechSelect()
				|| this.rechercheCritere.isDepartementRechSelect() || this.rechercheCritere.isFormationRechSelect()
				|| this.rechercheCritere.isAnneeFormationRechSelect()) {
			rechercheActivee = true;
		} else {
			rechercheActivee = false;
		}
		this.rechercheCritere.setNomRech(nom);
		// rechercheCritere.setPrenomRechSelect(prenomSelect.isSelected());
		this.rechercheCritere.setPrenomRech(prenom);
		//this.rechercheCritere.setDepartementRechSelect(false);
		departementRech.clear();
		this.departementRech.add(departementRechTextField.getText());
		this.rechercheCritere.setDepartementRech(departementRech);
		// this.rechercheCritere.setFormationRechSelect(formationSelect.isSelected());
		this.rechercheCritere.setFormationRech(formation);
		// this.rechercheCritere.setAnneeFormationRechSelect(false);
		if (anneeMin == null) {
			this.anneeFormationRech[0] = "1000";
			this.anneeFormationRech[1] = "3000";
		} else if (anneeMin != null && anneeMax == null) {
			this.anneeFormationRech[0] = anneeMin;
			this.anneeFormationRech[1] = anneeMin;
		} else if (anneeMin != null && anneeMax != null) {
			this.anneeFormationRech[0] = anneeMin;
			this.anneeFormationRech[1] = anneeMax;
		} else {
			this.anneeFormationRech[0] = "1000";
			this.anneeFormationRech[1] = "3000";
		}
		/*
		 * if (anneeMin.equals("") && !anneeMax.equals("")) { this.anneeFormationRech[0]
		 * = anneeMaxRechTextField.getText(); this.anneeFormationRech[1] =
		 * anneeMaxRechTextField.getText(); } else if (!anneeMin.equals("") &&
		 * anneeMax.equals("")) { this.anneeFormationRech[0] =
		 * anneeMinRechTextField.getText(); this.anneeFormationRech[1] =
		 * anneeMinRechTextField.getText(); } else if (!anneeMin.equals("") &&
		 * !anneeMax.equals("")) { this.anneeFormationRech[0] =
		 * anneeMinRechTextField.getText(); this.anneeFormationRech[1] =
		 * anneeMaxRechTextField.getText(); } else if (anneeMin.equals("") &&
		 * anneeMax.equals("")) { this.anneeFormationRech[0] = "1000";
		 * this.anneeFormationRech[1] = "3000"; }
		 */
		this.rechercheCritere.setAnneeFormationRech(anneeFormationRech);
		this.updateTableView(rechercheActivee, rechercheCritere);
	}
	
	/**
	 * Reinitialise les champs de recherche
	 * 
	 */
	public void initRecherche() {
		nomRechTextField.setText("");
		prenomRechTextField.setText("");
		formationRechTextField.setText("");
		//anneeMinRechTextField.setText("");
		//anneeMaxRechTextField.setText("");
		anneeMincomboBox.setValue(null);
		anneeMaxcomboBox.setValue(null);
		anneeMaxcomboBox.setDisable(true);
		this.updateTableView(false, rechercheCritere);
	}
	
	public void importerTxtToTableView(String cheminTXT) {
		this.sauvegardeBin = new ArbreBin(CHEMIN_BIN);
		this.sauvegardeBin.importAnnuaireTexte(cheminTXT);
		this.sauvegardeBin.afficherFichierBin();
	}

}

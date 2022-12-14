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

/**
 * Classe pour instancier des objets contennat une tableView et son module de recherche
 *
 */
public class TableViewStagiaire extends HBox implements ParametreGestionnaire {
	
	// ATTRIBUTS
	private TableView<StagiaireTableView> table = new TableView<StagiaireTableView>();
	private ArrayList<Integer> listeImpression = new ArrayList<>();
	private List<String> departementRech = new ArrayList<String>();
	private String[] anneeFormationRech = { "", "" };
	private RechercheMulticritere rechercheCritere = new RechercheMulticritere(false, "", false, "", true, departementRech, false, "", false, anneeFormationRech);
	private Boolean rechercheActivee = false;
	private Text nbrResultatText = new Text();
	private ArbreBin sauvegardeBin;
	
	public HBox tableViewStagiaireCont;
	private TextField nomRechTextField;
	private TextField prenomRechTextField;
	private TextField departementRechTextField;
	private TextField formationRechTextField;
	private TextField anneeMinRechTextField;
	private TextField anneeMaxRechTextField;
	private ComboBox anneeMincomboBox;
	private ComboBox anneeMaxcomboBox;
	
	private String userMode;
	

	/**
	 * Constructeur pour creer une tableView (admin ou user) a partir d'un fichier TXT
	 * 
	 * @param cheminTXT le chemin du fichier TXT a charger
	 * @param userMode  le mode dans lequel on se trouve : user / admin (= fonction
	 *                  supprimer et modifier en plus)
	 */
	public TableViewStagiaire(String cheminTXT, String userMode) {

		super(new HBox());
		tableViewStagiaireCont = new HBox();
		this.userMode = userMode;
		
		// On importe l'arbre
		this.importerTxtToTableView(cheminTXT);
		this.sauvegardeBin.afficheAnneeListeConsole();
		this.updateTableView(false, null);
		
		// on cree la grille qui va contenir la tableView et le module de recherche
		GridPane grille = new GridPane();
		grille.setHgap(15);
		grille.setVgap(5);
		this.getChildren().add(grille);
		this.setAlignment(Pos.CENTER);

		// CREATION TABLEVIEW -----------------------------------------------------------------------------------------------------------
		// Si le mode est Admin, alors la tableView est editable pour modifier les entrees
		if (userMode.equals("admin")) {
			table.setEditable(true);
		} else {
			table.setEditable(false);
		}
		
		// Setup de la tableView
		table.setMinWidth(500);
		table.setPrefWidth(2000);
		table.setPrefHeight(600);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// Création des colonnes de la tableView

		TableColumn<StagiaireTableView, String> indexCol = new TableColumn<StagiaireTableView, String>("Index");
		indexCol.setMinWidth(10);
		indexCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("index"));

		TableColumn<StagiaireTableView, String> nomCol = new TableColumn<StagiaireTableView, String>("Nom");
		nomCol.setMinWidth(100);
		nomCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("nom"));

		TableColumn<StagiaireTableView, String> prenomCol = new TableColumn<StagiaireTableView, String>("Prénom");
		prenomCol.setMinWidth(100);
		prenomCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("prenom"));

		TableColumn<StagiaireTableView, String> departementCol = new TableColumn<StagiaireTableView, String>("Département");
		departementCol.setMinWidth(10);
		departementCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("departement"));

		TableColumn<StagiaireTableView, String> formationCol = new TableColumn<StagiaireTableView, String>("Formation");
		formationCol.setMinWidth(20);
		formationCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("formation"));

		TableColumn<StagiaireTableView, String> anneeFormationCol = new TableColumn<StagiaireTableView, String>("Année de Formation");
		anneeFormationCol.setMinWidth(50);
		anneeFormationCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("anneeFormation"));
		
		TableColumn<StagiaireTableView, String> btnSuppCol = new TableColumn<StagiaireTableView, String>("Supprimer");
		btnSuppCol.setMinWidth(100);
		btnSuppCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("btnSupprimer"));
		
		// Not used : colonne CheckBOX
		TableColumn<StagiaireTableView, String> modCol = new TableColumn<StagiaireTableView, String>("Modifier");
		modCol.setMinWidth(100);
		modCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("modifierCheckBox"));

		// Not used : colonne modifier
		TableColumn<StagiaireTableView, String> btnModCol = new TableColumn<StagiaireTableView, String>("Modifier");
		btnModCol.setMinWidth(100);
		btnModCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("btnModifier"));

		table.getColumns().addAll(nomCol, prenomCol, departementCol, formationCol, anneeFormationCol);

		//addButtonToTable("MOD", "modifier");
		if (userMode.equals("admin")) {
			addButtonToTable("", "Supprimer");
		} else {
			
		}

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
				this.sauvegardeBin.modifierNom(stagiaire.getIndex(), this.sauvegardeBin.nomCheckAndUpdate(nom));
				this.rechercheRealTime();
				//this.updateTableView(false, rechercheCritere);
			}
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
				//this.updateTableView(false, rechercheCritere);
				this.rechercheRealTime();
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
				//this.updateTableView(false, rechercheCritere);
				this.rechercheRealTime();
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
				//this.updateTableView(false, rechercheCritere);
				this.rechercheRealTime();
			}
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
				//this.updateTableView(false, rechercheCritere);
				this.rechercheRealTime();
			}
			System.out.println("TTTT");
		});
		// FIN CREATION TABLEVIEW -----------------------------------------------------------------
		
		// CREATION MODULE RECHERCHE --------------------------------------------------------------
		Label label = new Label("");
		Label labelRecherche = new Label("Recherche par critères :");
		label.setFont(new Font("Arial", 20));
	
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
		
		// Not used
//		CheckBox nomSelect = new CheckBox("Nom");
//		CheckBox prenomSelect = new CheckBox("Prénom");
//		CheckBox departementSelect = new CheckBox("Département");
//		CheckBox formationSelect = new CheckBox("Formation");
//		CheckBox anneeFormationSelect = new CheckBox("Année");
		
		ArrayList<String> anneeListeMin = this.sauvegardeBin.getAnneeArrayListe();
		ArrayList<String> anneeListeMax = new ArrayList<String>();
		anneeMincomboBox = new ComboBox(FXCollections.observableArrayList(anneeListeMin));
		anneeMaxcomboBox = new ComboBox();
		anneeMaxcomboBox.setDisable(true);
		anneeMincomboBox.setPrefWidth(70);
		anneeMaxcomboBox.setPrefWidth(70);

		// Creation du bouton pour reinitialiser les criteres de recherche
		Button btnEffacerRecherche = new Button();
		btnEffacerRecherche.setText("Réinitialiser");
		btnEffacerRecherche.setOnAction(e -> {
			initRecherche();
		});
		
		// Creation de la liste des annees selectionnables en fonction de celles presentes dans l'arbre
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
		    anneeMaxcomboBox.setItems(FXCollections.observableArrayList(anneeListeMax));
		    this.rechercheRealTime();

		    System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
		    System.out.println("   ComboBox.getValue(): " + anneeMincomboBox.getValue());
		});
		
		anneeMaxcomboBox.setOnAction((event) -> {
			this.rechercheRealTime();
		});

		// Creation de la gridPane contenant les modules de recherche
		GridPane grilleRecherche = new GridPane();
		
		grilleRecherche.setPrefWidth(1030);
		grilleRecherche.setHgap(15);
		grilleRecherche.setVgap(5);
		grilleRecherche.setPadding(new Insets(10));
		grilleRecherche.setBackground(new Background(new BackgroundFill(Color.rgb(237, 237, 237), null, null)));
		grilleRecherche.setStyle("-fx-border: 2; -fx-border-color:rgb(212, 212, 212);");
		
		grilleRecherche.add(label, 0, 0, 16,1);
		grilleRecherche.add(labelRecherche, 0, 1, 16,1);
		grilleRecherche.add(nomRechLabel, 0, 3, 1,1);
		grilleRecherche.add(nomRechTextField, 0, 4, 1,1);
		grilleRecherche.add(prenomRechLabel, 2, 3, 1,1);
		grilleRecherche.add(prenomRechTextField, 2, 4, 1,1);
		grilleRecherche.add(departementRechLabel, 4, 3, 1,1);
		grilleRecherche.add(departementRechTextField, 4, 4, 1,1);
		grilleRecherche.add(formationRechLabel, 6, 3, 1,1);
		grilleRecherche.add(formationRechTextField, 6, 4, 1,1);
		grilleRecherche.add(anneeFormationRechLabel, 8, 3, 5,1);
		grilleRecherche.add(anneeFormationRechLabelDe, 8, 4, 1,1);
		grilleRecherche.add(anneeMincomboBox, 9, 4, 1, 1);
		grilleRecherche.add(anneeFormationRechLabelA, 10, 4, 1, 1);
		grilleRecherche.add(anneeMaxcomboBox, 11, 4, 1,1);
		grilleRecherche.add(btnEffacerRecherche, 14, 4, 1,1);

		// FIN CREATION MODULE RECHERCHE --------------------------------------------------------------
		
		// On ajoute le module de recherche
		// on ajoute la tableView
		grille.add(grilleRecherche, 1, 0, 15,1);
		grille.add(table, 1, 6, 15,1);
		
		// Not used : permet de definir les largeurs des colonnes
//		ColumnConstraints colFirst = new ColumnConstraints();
//		ColumnConstraints colVide = new ColumnConstraints();
//		ColumnConstraints colTextField = new ColumnConstraints();
//		ColumnConstraints colButton = new ColumnConstraints();
//		ColumnConstraints colSmall = new ColumnConstraints();
//		ColumnConstraints colDoubleTextField = new ColumnConstraints();
//		colFirst.setPercentWidth(0);
//		colSmall.setPercentWidth(2);
//		colVide.setPercentWidth(5);
//		colTextField.setPercentWidth(15);
//		colButton.setMaxWidth(10);
//		grille.getColumnConstraints().addAll(colFirst, colTextField, colVide, colTextField, colVide, colTextField, colVide, colTextField, colVide, colSmall, colTextField, colSmall, colTextField, colVide, colButton, colVide);
//		grilleRecherche.getColumnConstraints().addAll(colFirst, colTextField, colVide, colTextField, colVide, colTextField, colVide, colTextField, colVide, colSmall, colTextField, colSmall, colTextField, colVide, colButton, colVide);
		
		// FALSE CSS
		if (os.equals("PC")) {
			labelRecherche.setFont(FONTTEXTERECH);
			nomRechLabel.setFont(FONTTEXTERECH);
			prenomRechLabel.setFont(FONTTEXTERECH);
			departementRechLabel.setFont(FONTTEXTERECH);
			formationRechLabel.setFont(FONTTEXTERECH);
			anneeFormationRechLabel.setFont(FONTTEXTERECH);
			anneeFormationRechLabelA.setFont(FONTTEXTERECH);
			anneeFormationRechLabelDe.setFont(FONTTEXTERECH);
			nomRechTextField.setFont(FONTTEXTERECH);
			prenomRechTextField.setFont(FONTTEXTERECH);
			departementRechTextField.setFont(FONTTEXTERECH);
			formationRechTextField.setFont(FONTTEXTERECH);
			anneeMinRechTextField.setFont(FONTTEXTERECH);
			anneeMaxRechTextField.setFont(FONTTEXTERECH);
			btnEffacerRecherche.setFont(FONTBUTTON);
			
			btnEffacerRecherche.setTextFill(Color.rgb(61, 110, 139));
			btnEffacerRecherche.setStyle(BUTTONCOLOR);
			btnEffacerRecherche.setOnMouseEntered((event) -> {
				btnEffacerRecherche.setStyle(BUTTONCOLOROVER);
				btnEffacerRecherche.setTextFill(Color.rgb(240, 240, 240));
		    	
			});
			btnEffacerRecherche.setOnMouseExited((event) -> {
				btnEffacerRecherche.setStyle(BUTTONCOLOR);
				btnEffacerRecherche.setTextFill(Color.rgb(61, 110, 139));
			});
	    	
	    }
		
		
		
		
	}
	
	//GETTERS AND SETTERS----------------------------------------------------------------------------------------------------------------
	public RechercheMulticritere getRechercheCritere() {
		return rechercheCritere;
	}

	public void setRechercheCritere(RechercheMulticritere rechercheCritere) {
		this.rechercheCritere = rechercheCritere;
	}

	public Boolean getRechercheActivee() {
		return rechercheActivee;
	}

	public void setRechercheActivee(Boolean rechercheActivee) {
		this.rechercheActivee = rechercheActivee;
	}
	
	public ArbreBin getSauvegardeBin() {
		return sauvegardeBin;
	}

	public void setSauvegardeBin(ArbreBin sauvegardeBin) {
		this.sauvegardeBin = sauvegardeBin;
	}
	
	
	
	// METHODES --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Update la liste des stagiaires dans le TableView
	 * 
	 */
	public void updateTableView(Boolean rechActive, RechercheMulticritere rechercheCritere) {
//		if (rechActive == false) {
//			this.initRecherche();
//		}
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
							
							// creation du stage
							Stage dialog = new Stage();
							dialog.initModality(Modality.APPLICATION_MODAL);
							dialog.setResizable(false);
							dialog.setTitle("Retirer un stagiaire de la liste");

							//  creation grille mere
							GridPane grilleDialog = new GridPane();
							grilleDialog.setHgap(10);
							grilleDialog.setVgap(10);
							grilleDialog.setPadding(new Insets(30, 30, 30, 30));
							BackgroundFill bgrFill = new BackgroundFill(Color.rgb(182,213,231), null, null);
							grilleDialog.setBackground(new Background(bgrFill));
							
							Label texteLabel = new Label("Voulez-vous retirer ce stagiaire de la liste ?");
							Button annuler = new Button("Annuler");
							Button supprimer = new Button("Retirer de la liste");
							annuler.setPrefWidth(160);
							supprimer.setPrefWidth(160);
							grilleDialog.add(texteLabel, 1, 1, 3, 1);
							grilleDialog.add(annuler, 1, 9, 1, 1);
							grilleDialog.add(supprimer, 2, 9, 1, 1);
							
							 // creation grille fille où sont recapitules les infos su stagiaire
							GridPane grilleRecap = new GridPane();
							grilleRecap.setHgap(25);
							grilleRecap.setVgap(5);
							grilleRecap.setPadding(new Insets(25));
							grilleRecap.setBackground(new Background(new BackgroundFill(Color.rgb(237, 237, 237), null, null)));
							grilleRecap.setStyle("-fx-border: 2; -fx-border-color:rgb(212, 212, 212);");
							
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

							grilleRecap.add(nomLabel, 0, 0, 1, 1);
							grilleRecap.add(nom, 0, 1, 1, 1);
							grilleRecap.add(prenomLabel, 1, 0, 1, 1);
							grilleRecap.add(prenom, 1, 1, 1, 1);
							grilleRecap.add(departementLabel, 2, 3, 1, 1);
							grilleRecap.add(departement, 2, 4, 1, 1);
							grilleRecap.add(formationLabel, 0, 3, 1, 1);
							grilleRecap.add(formation, 0, 4, 1, 1);
							grilleRecap.add(anneeFormationLabel, 1, 3, 1, 1);
							grilleRecap.add(anneeFormation, 1, 4, 1, 1);
							
							// on ajoute la grille fille à la grille mere
							grilleDialog.add(grilleRecap, 1, 2, 3, 5);
					
							// on configure les boutons
							annuler.setOnAction((e) -> {
							    dialog.close();
							});

							supprimer.setOnAction((e) -> {
								System.out.println(stagiaire.getNom() + " " + stagiaire.getPrenom()
								+ " " + stagiaire.getDepartement() + " " + stagiaire.getFormation() + " "
								+ stagiaire.getAnneeFormation() + " [" + stagiaire.getIndex() + "] ");
								Noeud noeudVide = new Noeud();
								noeudVide.supprimerStagiaireNoeud(stagiaire.getIndex());
								tableReference.rechercheRealTime();
							    dialog.close();
							});
							
							// FALSE CSS
							if (os.equals("PC")) {
								
								texteLabel.setFont(FONTTEXTE);
								nomLabel.setFont(FONTTEXTE);
								prenomLabel.setFont(FONTTEXTE);
								departementLabel.setFont(FONTTEXTE);
								formationLabel.setFont(FONTTEXTE);
								anneeFormationLabel.setFont(FONTTEXTE);
							    nom.setFont(FONTTEXTEBOLD);
							    prenom.setFont(FONTTEXTEBOLD);
							    departement.setFont(FONTTEXTEBOLD);
							    formation.setFont(FONTTEXTEBOLD);
							    anneeFormation.setFont(FONTTEXTEBOLD);
							    
								annuler.setFont(FONTBUTTON);
								supprimer.setFont(FONTBUTTON);

								annuler.setTextFill(Color.rgb(183, 65, 14));
								annuler.setStyle(BUTTONCOLOR);
								annuler.setOnMouseEntered(e -> {
									annuler.setStyle(BUTTONALERTCOLOROVER);
									annuler.setTextFill(Color.rgb(240, 240, 240));
							    	
								});
								annuler.setOnMouseExited(e -> {
									annuler.setStyle(BUTTONCOLOR);
									annuler.setTextFill(Color.rgb(183, 65, 14));
								});
								
								supprimer.setTextFill(Color.rgb(61, 110, 139));
								supprimer.setStyle(BUTTONCOLOR);
								supprimer.setOnMouseEntered(e -> {
									supprimer.setStyle(BUTTONCOLOROVER);
									supprimer.setTextFill(Color.rgb(240, 240, 240));
							    	
								});
								supprimer.setOnMouseExited(e -> {
									supprimer.setStyle(BUTTONCOLOR);
									supprimer.setTextFill(Color.rgb(61, 110, 139));
								});
						    	
						    }

							Scene dialogScene = new Scene(grilleDialog);
							dialog.setScene(dialogScene);
							dialog.showAndWait();
		
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
		// on ajoute les boutons dans la colonne
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
	
	/**
	 * methode interne pour creer un TextField dont chaque input au clavier declenchera une recherche
	 * @return un TextField de recherche
	 */
	private TextField createTextFieldRechercheInput() {
		TextField textFieldRechercheInput = new TextField();
		textFieldRechercheInput.setOnKeyReleased((KeyEvent event) -> {
			this.rechercheRealTime();
		});
		return textFieldRechercheInput;
	}
	
	/**
	 * methode interne de recherche en fonction des inputs des TextFiels de recherche
	 */
	private void rechercheRealTime() {
		String nom = this.nomRechTextField.getText();
		String prenom = this.prenomRechTextField.getText();
		String formation = this.formationRechTextField.getText();
		String departement = this.departementRechTextField.getText();
		String anneeMin = (String) anneeMincomboBox.getSelectionModel().getSelectedItem();
		String anneeMax = (String) anneeMaxcomboBox.getSelectionModel().getSelectedItem();
		System.out.println(anneeMin);
		System.out.println(anneeMax);

		// teste s'il y a eu des inputs de recherche dans les TextField (cad leur contenu != "")
		// et affecte les booleen de selection (des champs de recherche correspondants) à l'objet rechercheCritere
		if (!nom.equals("")) {
			this.rechercheCritere.setNomRechSelect(true);
		} else {
			this.rechercheCritere.setNomRechSelect(false);
		}
		if (!prenom.equals("")) {
			this.rechercheCritere.setPrenomRechSelect(true);
		} else {
			this.rechercheCritere.setPrenomRechSelect(false);
		}
		if (!departement.equals("")) {
			this.rechercheCritere.setFormationRechSelect(true);
		} else {
			this.rechercheCritere.setFormationRechSelect(false);
		}
		if (!formation.equals("")) {
			this.rechercheCritere.setFormationRechSelect(true);
		} else {
			this.rechercheCritere.setFormationRechSelect(false);
		}
		if (anneeMin != null) {
			this.rechercheCritere.setAnneeFormationRechSelect(true);
		} else {
			this.rechercheCritere.setAnneeFormationRechSelect(false);
		}
	
		// si un des champs de recherche est activé (input != ""), alors on considère que la recherche est active (rechercheActivee = true)
		this.rechercheCritere.setAnneeFormationRechSelect(true);
		if (this.rechercheCritere.isNomRechSelect() || this.rechercheCritere.isPrenomRechSelect()
				|| this.rechercheCritere.isDepartementRechSelect() || this.rechercheCritere.isFormationRechSelect()
				|| this.rechercheCritere.isAnneeFormationRechSelect()) {
			this.rechercheActivee = true;
		} else {
			this.rechercheActivee = false;
		}
		
		// on recupère les inputs et on les transmet à l'objet rechercheCritere
		// nom
		if (!nom.equals("")) {
			this.rechercheCritere.setNomRech(nom.toUpperCase());
		}
		// prenom
		if (!prenom.equals("")) {
			String prenomFormate ="";
			prenomFormate += prenom.substring(0,1).toUpperCase();
			prenomFormate += prenom.substring(1).toLowerCase();
			this.rechercheCritere.setPrenomRech(prenomFormate);
		}
		// departement
		departementRech.clear();
		this.departementRech.add(departementRechTextField.getText());
		this.rechercheCritere.setDepartementRech(departementRech);
		// formation
		if (!formation.equals("")) {
			this.rechercheCritere.setFormationRech(formation.toUpperCase());
		}
		// annee formation
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
		this.rechercheCritere.setAnneeFormationRech(anneeFormationRech);
		
		// on lance l'update de la tableview avec les critères contenus dans rechercheCritere
		this.updateTableView(this.rechercheActivee, rechercheCritere);
	}
	
	/**
	 * Reinitialise les champs de recherche
	 * 
	 */
	public void initRecherche() {
		nomRechTextField.setText("");
		prenomRechTextField.setText("");
		formationRechTextField.setText("");
		departementRechTextField.setText("");
		anneeMincomboBox.setValue(null);
		anneeMaxcomboBox.setValue(null);
		anneeMaxcomboBox.setDisable(true);
		this.updateTableView(false, rechercheCritere);
	}
	
	/**
	 * Importe un fichier texte (dans l'optique de l'utiliser pour creer unn arbre bianire à mettre dans une tableview)
	 * @param cheminTXT le fichier texte à importer
	 */
	public void importerTxtToTableView(String cheminTXT) {
		this.sauvegardeBin = new ArbreBin(CHEMIN_BIN);
		this.sauvegardeBin.importAnnuaireTexte(cheminTXT);
		this.sauvegardeBin.afficherFichierBin();
	}

}

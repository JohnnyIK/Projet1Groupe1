package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Callback;

import java.time.LocalDateTime;
//import org.controlsfx.control.RangeSlider;
import javafx.scene.text.Text;

public class VueTableau extends Scene implements ParametreGestionnaire {

	TableView<StagiaireTableView> table = new TableView<StagiaireTableView>();
	ArrayList<Integer> listeImpression = new ArrayList<>();
	List<String> departementRech = new ArrayList<String>();
	String[] anneeFormationRech = { "", "" };

	RechercheMulticritere rechercheCritere = new RechercheMulticritere(false, "", false, "", true, departementRech,
			false, "", false, anneeFormationRech);
	
	Text nbrResultatText = new Text();

	public VueTableau() {

		super(new GridPane());

		GridPane grille = (GridPane) this.getRoot();

		//departementRech.add("91");
		//departementRech.add("75");
		//anneeFormationRech[0] = "2000";
		//anneeFormationRech[1] = "2007";

		// Création de la table

		// TableView<StagiaireTableView> table = new TableView<StagiaireTableView>();

		table.setEditable(true);
		
		table.minHeight(1000);

		Label label = new Label("Liste des contacts");
		label.setFont(new Font("Arial", 20));

		// Création des trois colonnes

		TableColumn<StagiaireTableView, String> indexCol = new TableColumn<StagiaireTableView, String>("Index");
		indexCol.setMinWidth(10);
		indexCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("index"));

		TableColumn<StagiaireTableView, String> nomCol = new TableColumn<StagiaireTableView, String>("Nom");
		nomCol.setMinWidth(150);
		nomCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("nom"));

		TableColumn<StagiaireTableView, String> prenomCol = new TableColumn<StagiaireTableView, String>("Prénom");
		prenomCol.setMinWidth(100);
		prenomCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("prenom"));

		TableColumn<StagiaireTableView, String> deptCol = new TableColumn<StagiaireTableView, String>("Département");
		deptCol.setMinWidth(25);
		deptCol.setCellValueFactory(new PropertyValueFactory<StagiaireTableView, String>("departement"));

		TableColumn<StagiaireTableView, String> formationCol = new TableColumn<StagiaireTableView, String>("Formation");
		formationCol.setMinWidth(100);
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
		table.getColumns().addAll(nomCol, prenomCol, deptCol, formationCol, anneeFormationCol);

		// On rempli la table avec la liste observable
		this.updateTableView(CHEMIN_BIN, false, null);

		addButtonToTable("MOD", "modifier");
		addButtonToTable("SUPP", "supprimer");
		//addCheckBoxToTable("CHECK", "ccc");

		// METHODE LORSQU'ON SELECTIONNE UNE LIGNE DU TABLEAU
		// table.getSelectionModel().selectedIndexProperty().addListener(observable ->
		// System.out.printf("Indice sélectionné: %d",
		// table.getSelectionModel().getSelectedIndex()).println());
		table.getSelectionModel().selectedIndexProperty().addListener(observable -> System.out
				.printf("Index sélectionné: %d", table.getSelectionModel().getSelectedItem().getIndex()).println());

		// MODIFICATION ANNEE FORMATION OK
		anneeFormationCol.setCellFactory(TextFieldTableCell.<StagiaireTableView>forTableColumn());
		anneeFormationCol.setOnEditCommit((CellEditEvent<StagiaireTableView, String> event) -> {
			TablePosition<StagiaireTableView, String> pos = event.getTablePosition();

			String newFullName = event.getNewValue();

			int row = pos.getRow();
			StagiaireTableView stagiaire = event.getTableView().getItems().get(row);
			int index = event.getTableView().getItems().get(row).getIndex();
			System.out.println("INDEX MODIFIE " + index);
			ArbreBin sauvegardeBin = new ArbreBin(CHEMIN_BIN);
			sauvegardeBin.ecrireAnneeFormationBinaire(stagiaire.getIndex(), newFullName);
			this.updateTableView(CHEMIN_BIN, false, null);
			// person.setNom(newFullName);

		});

		// MODIFICATION NOM OK
		nomCol.setCellFactory(TextFieldTableCell.<StagiaireTableView>forTableColumn());
		nomCol.setOnEditCommit((CellEditEvent<StagiaireTableView, String> event) -> {
			TablePosition<StagiaireTableView, String> pos = event.getTablePosition();

			String newFullName = event.getNewValue();

			int row = pos.getRow();
			StagiaireTableView stagiaire = event.getTableView().getItems().get(row);
			ArbreBin sauvegardeBin = new ArbreBin(CHEMIN_BIN);
			sauvegardeBin.ecrireNomBinaire(stagiaire.getIndex(), newFullName);
			this.updateTableView(CHEMIN_BIN, false, null);
			// person.setNom(newFullName);

		});

		// On place le label et la table dans une VBox
		BorderPane bp = new BorderPane();

		// Left
		Button btn = new Button();
		Button btn1 = new Button();
		Button btn2 = new Button();
		btn.setText("Ajouter");
		btn1.setText("Liste complete");
		btn2.setText("Rechercher");

		btn.setOnAction((ActionEvent event) -> {
			table.getColumns().get(7).setVisible(false);
		});

		btn1.setOnAction((ActionEvent event) -> {
			this.updateTableView(CHEMIN_BIN, false, null);
		});
		
		

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

		Label nomRechLabel = new Label("Nom");
		Label prenomRechLabel = new Label("Prénom");
		Label departementRechLabel = new Label("Département");
		Label formationRechLabel = new Label("Formation");
		Label anneeFormationRechLabel = new Label("anneeFormation");
		
		Label nbrResultatLabel = new Label("Résultats : ");
		
		CheckBox nomSelect = new CheckBox("Nom");
		CheckBox prenomSelect = new CheckBox("Prénom");
		CheckBox departementSelect = new CheckBox("Département");
		CheckBox formationSelect = new CheckBox("Formation");
		CheckBox anneeFormationSelect = new CheckBox("Année");
		
		btn2.setOnAction((ActionEvent event) -> {
			rechercheCritere.setNomRechSelect(nomSelect.isSelected());
			rechercheCritere.setNomRech(nomRechTextField.getText());
			rechercheCritere.setPrenomRechSelect(prenomSelect.isSelected());
			rechercheCritere.setPrenomRech(prenomRechTextField.getText());
			rechercheCritere.setDepartementRechSelect(departementSelect.isSelected());
			departementRech.add(departementRechTextField.getText());
			rechercheCritere.setDepartementRech(departementRech);
			rechercheCritere.setFormationRechSelect(formationSelect.isSelected());
			rechercheCritere.setFormationRech(formationRechTextField.getText());
			rechercheCritere.setAnneeFormationRechSelect(anneeFormationSelect.isSelected());
			anneeFormationRech[0] = anneeMinRechTextField.getText();
			anneeFormationRech[1] = anneeMaxRechTextField.getText();
			rechercheCritere.setAnneeFormationRech(anneeFormationRech);
			this.updateTableView(CHEMIN_BIN, true, rechercheCritere);
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
		
		
		nomRechTextField.setOnKeyReleased((KeyEvent event) -> {
			String newNom = nomRechTextField.getText();
			System.out.println(newNom);
        	rechercheCritere.setNomRechSelect(nomSelect.isSelected());
			rechercheCritere.setNomRech(newNom);
			rechercheCritere.setPrenomRechSelect(prenomSelect.isSelected());
			rechercheCritere.setPrenomRech(prenomRechTextField.getText());
			rechercheCritere.setDepartementRechSelect(departementSelect.isSelected());
			departementRech.add(departementRechTextField.getText());
			rechercheCritere.setDepartementRech(departementRech);
			rechercheCritere.setFormationRechSelect(formationSelect.isSelected());
			rechercheCritere.setFormationRech(formationRechTextField.getText());
			rechercheCritere.setAnneeFormationRechSelect(anneeFormationSelect.isSelected());
			anneeFormationRech[0] = anneeMinRechTextField.getText();
			anneeFormationRech[1] = anneeMaxRechTextField.getText();
			rechercheCritere.setAnneeFormationRech(anneeFormationRech);
			this.updateTableView(CHEMIN_BIN, true, rechercheCritere);
		});


		// handleSupprimer() ;

		//BorderPane.setMargin(btn, new Insets(5, 5, 5, 5));
		// Center

		// grille.setAlignment(Pos.CENTER);
		grille.setHgap(50);
		grille.setVgap(50);
		grille.setPadding(new Insets(10, 10, 10, 10));

//		grille.add(label, 0, 1);
//		grille.add(table, 2, 3);
//		grille.add(btn, 0, 2);
//		grille.add(btn1, 0, 3);
//		grille.add(nomRechLabel, 0, 4);

		HBox hbox = new HBox();
		VBox vbox = new VBox();
		VBox vbox1 = new VBox();
		
		table.minWidth(100);

		vbox.setSpacing(5);
		// grille.getChildren().addAll(btn,table,label);
		hbox.setSpacing(10);
		// vbox.setPadding(new Insets(10, 10, 10, 100));
		vbox.getChildren().addAll(label, btn, btn1, btn2,
				nomSelect, nomRechTextField,
				prenomSelect, prenomRechTextField,
				departementSelect, departementRechTextField,
				formationSelect, formationRechTextField,
				anneeFormationSelect, anneeMinRechTextField, anneeMaxRechTextField,
				nbrResultatLabel, nbrResultatText);
		vbox1.getChildren().addAll(table);
		hbox.getChildren().addAll(vbox, vbox1);
		vbox1.setMinSize(1000, 800);
		//vbox1.minHeight(1000);
		//hbox.setMinSize(1500, 500);
		grille.getChildren().addAll(hbox);

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
	 * Update la liste des stagiaires dans le TableView à partir du fichier bin
	 * entre en argument
	 * 
	 * @cheminFichierBin Le chemin relatif du fichier binaire à utiliser pour
	 *                   completer la TableView
	 */
	public void updateTableView(String cheminFichierBin, Boolean rechActive, RechercheMulticritere rechercheCritere) {
		ObservableList<StagiaireTableView> getStagiaireTableViewList = getStagiaireTableViewList(cheminFichierBin, rechActive, rechercheCritere);
		this.nbrResultatText.setText(String.valueOf(getStagiaireTableViewList.size())+" stagiaires trouvés");
		this.table.setItems(getStagiaireTableViewList(cheminFichierBin, rechActive, rechercheCritere));
	}
	


	/**
	 * Transfere le fichier bin entre en argument en une ObservableList (utilisable
	 * par le TableView)
	 * 
	 * @param cheminFichierBin Le chemin relatif du fichier binaire à transferer
	 *                         dans une ObservableList
	 * @return l'ObservableList faite de StagiaireTableView (index / nom / prenom /
	 *         departement / formation / anneeFormation)
	 */
	private ObservableList<StagiaireTableView> getStagiaireTableViewList(String cheminFichierBin, Boolean rechActive,
			RechercheMulticritere rechercheCritere) {

		ArbreBin sauvegardeBin = new ArbreBin(cheminFichierBin);
		sauvegardeBin.importAnnuaireTexte();
		sauvegardeBin.afficherFichierBin();

		ArrayList<Noeud> listeNoeud = new ArrayList<Noeud>();
		sauvegardeBin.exportToArrayListOptionRecherche(listeNoeud, rechActive, rechercheCritere);
		ArrayList<StagiaireTableView> listeStagiaire = new ArrayList<StagiaireTableView>();
		for (Noeud noeud : listeNoeud) {
			StagiaireTableView contact = new StagiaireTableView(noeud.getIndexNoeud(), noeud.getCle().getNom(),
					noeud.getCle().getPrenom(), noeud.getCle().getDepartement(), noeud.getCle().getFormation(),
					noeud.getCle().getAnneeFormation());
			listeStagiaire.add(contact);
		}

		ObservableList<StagiaireTableView> list = FXCollections.observableArrayList(listeStagiaire);

		// SYSO pour test
		for (Noeud noeud : listeNoeud) {
			System.out.println("ArrayList :" + noeud.getCle().getNomFormate() + " " + noeud.getCle().getPrenomFormate()
					+ " " + noeud.getCle().getDepartementFormate() + " " + noeud.getCle().getFormationFormate() + " "
					+ noeud.getCle().getAnneeFormationFormate() + " [" + noeud.getIndexNoeud() + "] " + " "
					+ noeud.getIndexFilsGauche() + " " + noeud.getIndexFilsDroit() + " " + noeud.getIndexDoublon());
		}
		System.out.println("Nbr Stagiaires fichier BIN >> " + sauvegardeBin.nbrStagiaires());
		System.out.println("Nbr Stagiaires ArrayList >> " + listeNoeud.size());

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

}

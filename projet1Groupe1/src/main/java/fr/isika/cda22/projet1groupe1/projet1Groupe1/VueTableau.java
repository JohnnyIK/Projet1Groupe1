package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Callback;


public class VueTableau extends Scene implements ParametreGestionnaire{

	
	TableView<StagiaireTableView> table = new TableView<StagiaireTableView>();
	
	ArrayList<Integer> listeImpression = new ArrayList<>();
	
    public  VueTableau() {
    
    	
    	
        super(new GridPane());

        GridPane grille = (GridPane)this.getRoot();
        
		//Création de la table
        
		//TableView<StagiaireTableView> table = new TableView<StagiaireTableView>();
		
		table.setEditable(true);
		
		
        Label label = new Label("Liste des contacts");
        label.setFont(new Font("Arial", 20));
 
        //Création des trois colonnes
        
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
        
        TableColumn<StagiaireTableView, String> anneeFormationCol = new TableColumn<StagiaireTableView, String>("Année de Formation");
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
                
        //On ajoute les trois colonnes à la table
        //table.getColumns().addAll(nomCol, prenomCol, deptCol, formationCol, anneeFormationCol , modCol, btnModCol, btnSuppCol);
        table.getColumns().addAll(nomCol, prenomCol, deptCol, formationCol, anneeFormationCol);
        
        //On rempli la table avec la liste observable
        table.setItems(getStagiaireTableViewList());
        
        addButtonToTable("MOD", "modifier");
        addButtonToTable("SUPP","supprimer");
        addCheckBoxToTable("CHECK", "ccc");
        
        
        //METHODE LORSQU'ON SELECTIONNE UNE LIGNE DU TABLEAU 
        //table.getSelectionModel().selectedIndexProperty().addListener(observable -> System.out.printf("Indice sélectionné: %d", table.getSelectionModel().getSelectedIndex()).println());
        table.getSelectionModel().selectedIndexProperty().addListener(observable -> System.out.printf("Index sélectionné: %d", table.getSelectionModel().getSelectedItem().getIndex()).println());
        
        // MODIFICATION ANNEE FORMATION OK
        anneeFormationCol.setCellFactory(TextFieldTableCell.<StagiaireTableView> forTableColumn());
        anneeFormationCol.setOnEditCommit((CellEditEvent<StagiaireTableView, String> event) -> {
            TablePosition<StagiaireTableView, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            StagiaireTableView stagiaire = event.getTableView().getItems().get(row);
            int index = event.getTableView().getItems().get(row).getIndex();
            System.out.println("INDEX MODIFIE "+index);
            ArbreBin sauvegardeBin = new ArbreBin(CHEMIN_BIN);
            sauvegardeBin.ecrireAnneeFormationBinaire(stagiaire.getIndex(),newFullName);
            table.setItems(getStagiaireTableViewList());
            //person.setNom(newFullName);
            
        });
        
     // MODIFICATION NOM OK
        nomCol.setCellFactory(TextFieldTableCell.<StagiaireTableView> forTableColumn());
        nomCol.setOnEditCommit((CellEditEvent<StagiaireTableView, String> event) -> {
            TablePosition<StagiaireTableView, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            StagiaireTableView stagiaire = event.getTableView().getItems().get(row);
            ArbreBin sauvegardeBin = new ArbreBin(CHEMIN_BIN);
            sauvegardeBin.ecrireNomBinaire(stagiaire.getIndex(),newFullName);
            table.setItems(getStagiaireTableViewList());
            //person.setNom(newFullName);
            
           
            
        });
        
        
        //On place le label et la table dans une VBox
         BorderPane bp = new BorderPane(); 
        
        // Left
         Button btn = new Button();
         Button btn1 = new Button();
         Button btn2 = new Button();
         btn.setText("Ajouter");
         btn1.setText("Supprimer");
         
         btn.setOnAction((ActionEvent event) -> {
        	 table.getColumns().get(7).setVisible(false);
         });
         
         
         
         //handleSupprimer() ;
         
        BorderPane.setMargin(btn, new Insets(5,5,5,5));
        // Center
        
        
        
        
        

        
        //grille.setAlignment(Pos.CENTER);
        grille.setHgap(10);
        grille.setVgap(10);
        grille.setPadding(new Insets(10, 10, 10, 10));
        
        
        grille.add(label,0,1 );
        grille.add(table, 2,3);
        grille.add(btn, 0, 2);
        grille.add(btn1, 1, 4);
        
        
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        VBox vbox1 = new VBox();
        
        hbox.setSpacing(5);
        //grille.getChildren().addAll(btn,table,label);
        hbox.setSpacing(10);
    //    vbox.setPadding(new Insets(10, 10, 10, 100));
        vbox.getChildren().addAll(label,btn ,btn1);
        vbox1.getChildren().addAll(table);
        hbox.getChildren().addAll(vbox,vbox1);
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
    
     

	public ObservableList<StagiaireTableView> getStagiaireTableViewList() {
		
		ArbreBin sauvegardeBin = new ArbreBin(CHEMIN_BIN);
		//sauvegardeBin.importAnnuaireTexte();
		//sauvegardeBin.afficherFichierBin();
        
		 ArrayList<Noeud> listeNoeud = new ArrayList<Noeud>();
		 sauvegardeBin.exportToArrayListOptionRecherche(listeNoeud, false, null);
		 ArrayList<StagiaireTableView> listeStagiaire = new ArrayList<StagiaireTableView>();
		 for (Noeud noeud: listeNoeud) {
			 StagiaireTableView contact = new StagiaireTableView(noeud.getIndexNoeud(),
					                       noeud.getCle().getNom(),
					                       noeud.getCle().getPrenom(),
					                       noeud.getCle().getDepartement(),
					                       noeud.getCle().getFormation(),
					                       noeud.getCle().getAnneeFormation());
			 listeStagiaire.add(contact);
		 }
		 
		 ObservableList<StagiaireTableView> list = FXCollections.observableArrayList(listeStagiaire);
		 
		 //SYSO pour test
		 for (Noeud noeud : listeNoeud) {
			 System.out.println("ArrayList :"+noeud.getCle().getNomFormate()+" "+noeud.getCle().getPrenomFormate()+" "+noeud.getCle().getDepartementFormate()+" "+noeud.getCle().getFormationFormate()+" "+noeud.getCle().getAnneeFormationFormate()+" ["+noeud.getIndexNoeud()+"] "+" "+noeud.getIndexFilsGauche()+" "+noeud.getIndexFilsDroit()+" "+noeud.getIndexDoublon());
		 }
		 System.out.println("Nbr Stagiaires fichier BIN >> " + sauvegardeBin.nbrStagiaires());
		 System.out.println("Nbr Stagiaires ArrayList >> " +listeNoeud.size());
		
		

	
		
	    return list;
	  }
	
	
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
                            System.out.println("selectedData: " + stagiaire.getNom());
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
	
	private void addCheckBoxToTable(String nomColonne, String labelBouton) {
        TableColumn<StagiaireTableView, Void> colCheckBox = new TableColumn(nomColonne);

        Callback<TableColumn<StagiaireTableView, Void>, TableCell<StagiaireTableView, Void>> cellFactory = new Callback<TableColumn<StagiaireTableView, Void>, TableCell<StagiaireTableView, Void>>() {
            @Override
            public TableCell<StagiaireTableView, Void> call(final TableColumn<StagiaireTableView, Void> param) {
                final TableCell<StagiaireTableView, Void> cell = new TableCell<StagiaireTableView, Void>() {

                    private final CheckBox checkbox = new CheckBox(labelBouton);

                    {
                    	checkbox.setOnAction((ActionEvent event) -> {
                        	StagiaireTableView stagiaire = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + stagiaire.getNom());
                            listeImpression.add(stagiaire.getIndex());
                          //SYSO pour test
                   		 for (Integer i : listeImpression) {
                   			 System.out.println("ArrayList index: "+i);
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


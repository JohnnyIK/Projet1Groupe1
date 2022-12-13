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
public class VueAjouterFichier extends Scene {
	
	
	private TextField txtSelected;
	private Button BtnRetour1;
	private Button BtnValider1;
	private String fichierTXT; 
	
	public VueAjouterFichier() {
		super(new VBox(15), 1000, 200 );
			
		
		VBox vboxfichier = (VBox)this.getRoot();
		this.setRoot(vboxfichier);
		this.getRoot().setStyle("-fx-font-family: 'serif'");
		BackgroundFill bgrFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
		vboxfichier.setBackground(new Background(bgrFill));
		
		
		
		ObservableList<Node> hRows = vboxfichier.getChildren();
		Insets border11 = new Insets(8,8,8,8);
		vboxfichier.setPadding(border11);
		HBox hbox1 = new HBox(15);
		ObservableList<Node> hCols1 = hbox1.getChildren();
		hbox1.setPadding(border11);
		
			//Bouttons dans fenetre selectionner fichier
		Button btnfileChooserSingle = new Button("Selectionner 1 fichier");
		//Button btnfileChooserMultiple = new Button("Selectionner plusieurs fichiers (5 max)");
		
		hCols1.addAll(btnfileChooserSingle);//,btnfileChooserMultiple);
		
			//TextField pour afficher le nom du/des fichiers
		HBox hBox2 = new HBox(15);
		ObservableList<Node> hCols2 = hBox2.getChildren();
		hBox2.setPadding(border11);
			//ajout textfield
		txtSelected = new TextField("les fichiers selectionnés seront affiché ici");
		txtSelected.setMinWidth(700);
		hBox2.getChildren().add(txtSelected);
		BtnValider1 = new Button("Valider");
		BtnRetour1 = new Button("Annuler");
		hCols2.addAll(BtnValider1, BtnRetour1);
		
			//On assemble les bouttons de control dans la Vbox vboxfichiers
		hRows.add(hbox1);
		hRows.add(hBox2);
		
		
		
		//action sur boutton btnfileChooserSingle
		btnfileChooserSingle.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
//				Node node = (Node) ae.getSource();
			    Stage thisStage = (Stage) vboxfichier.getScene().getWindow();
				FileChooser fileOpen = new FileChooser();
				File file = fileOpen.showOpenDialog(thisStage);
				//String filename = file.getName();
				String filename = file.getAbsolutePath();
				
				txtSelected.setText(filename);
				fichierTXT = filename;
			}
		});
		
		
		}
	public TextField getTxtSelected() {
		return txtSelected;
	}
	public void setTxtSelected(TextField txtSelected) {
		this.txtSelected = txtSelected;
	}
	public Button getBtnRetour1() {
		return BtnRetour1;
	}
	public void setBtnRetour1(Button btnRetour1) {
		BtnRetour1 = btnRetour1;
	}
	public Button getBtnValider1() {
		return BtnValider1;
	}
	public void setBtnValider1(Button btnValider1) {
		BtnValider1 = btnValider1;
	}
	
	public String getFichierTXT() {
		return fichierTXT;
	}
	
	/*
	public Button getBtnfileChooserSingle() {
		return btnfileChooserSingle;
	}
	*/
	
		
	
	}
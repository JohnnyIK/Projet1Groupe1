package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * JavaFX App
 */
public class App extends Application {

	//attributs 
		public VuePageAcceuil vuePageAccueil;
		public VueLoginAdmin vueLoginAdmin;
		public VueAppAdmin vueAppAdmin;
	

    public void start(Stage stage) {
    	
    	stage.setTitle(" Patrick School - Acceuil ");
    	
    	vuePageAccueil = new VuePageAcceuil();
        
        vueLoginAdmin = new VueLoginAdmin();
    	
    	vuePageAccueil.getBtnAdmin().setOnAction(event -> {
        	stage.setScene(vueLoginAdmin);
        	stage.setTitle(" Patrick School - Login ");
        	
        });	
        vueLoginAdmin.getBtnLogin().setOnAction(event -> {
        	vueAppAdmin = new VueAppAdmin("admin");
            stage.setScene(vueAppAdmin);
            stage.setTitle(" Patrick School - ApplicationAdmin ");
            
        });
        
        vuePageAccueil.getBtnUser().setOnAction(event -> {
        	vueAppAdmin = new VueAppAdmin("user");
            stage.setScene(vueAppAdmin);
            stage.setTitle(" Patrick School - ApplicationUtilisateur ");
        	
        });	

        
        //stage.setScene(vuePageAccueil);
        vueAppAdmin = new VueAppAdmin("admin");
        stage.setScene(vueAppAdmin);
        
        stage.show();
    	
    	
    }

 
    
    public static void main(String[] args) {
        launch();
    }
    

}
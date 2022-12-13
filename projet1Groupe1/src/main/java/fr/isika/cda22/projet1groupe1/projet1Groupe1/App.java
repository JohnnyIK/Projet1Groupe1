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
public class App extends Application implements ParametreGestionnaire {
	//attributs
		public VuePageAcceuil vuePageAccueil;
		public VueLoginAdmin vueLoginAdmin;
		public VueAppAdmin vueAppAdmin;
		public VueAppAdmin vueAppAdminUser;
		public VueAjouterFichier vueAjouterFichier;
		public VueAjouterStagiaire vueAjouterStagiaire;
		
    public void start(Stage stage) {
    	Administrateur admin = new Administrateur();
    	admin.recupereLoginAdmin();
    	Utilisateur utilisateur = new Utilisateur();
    	utilisateur.recupereLoginUtilisateur();
    	
    	stage.setTitle(" Patrick School - Acceuil ");
    	
    	
    	vuePageAccueil = new VuePageAcceuil();
        vueLoginAdmin = new VueLoginAdmin();
 
        //vueAppAdmin = new VueAppAdmin("admin");
        //vueAppAdminUser = new VueAppAdmin("user");

    	vuePageAccueil.getBtnAdmin().setOnAction(event -> {
        	stage.setScene(vueLoginAdmin);
        	stage.setTitle(" Patrick School - Login ");
        	
        });
    	
		vueLoginAdmin.getBtnLogin().setOnAction(event -> {

			for (int i = 0; i < admin.getListeAdmin().size(); i++) {
				if (vueLoginAdmin.getFieldId().getText().equals(admin.getListeAdmin().get(i).getLogin())
						&& vueLoginAdmin.getFieldMdp().getText().equals(admin.getListeAdmin().get(i).getMdp())) {
					vueAppAdmin = new VueAppAdmin("admin", admin.getListeAdmin().get(i).getNom(), admin.getListeAdmin().get(i).getPrenom());
					stage.setScene(vueAppAdmin);
					stage.setTitle(" Patrick School - Administrateur : " + admin.getListeAdmin().get(i).getPrenom()
							+ " " + admin.getListeAdmin().get(i).getNom());

				}
				for (int i1 = 0; i1 < utilisateur.getListeUtilisateur().size(); i1++) {
					if (vueLoginAdmin.getFieldId().getText()
							.equals(utilisateur.getListeUtilisateur().get(i1).getLogin())
							&& vueLoginAdmin.getFieldMdp().getText()
									.equals(utilisateur.getListeUtilisateur().get(i1).getMdp())) {
						vueAppAdminUser = new VueAppAdmin("user", utilisateur.getListeUtilisateur().get(i1).getNom(), utilisateur.getListeUtilisateur().get(i1).getPrenom());
						stage.setScene(vueAppAdminUser);
						stage.setTitle(" Patrick School - Utilisateur : "
								+ utilisateur.getListeUtilisateur().get(i1).getPrenom() + " "
								+ utilisateur.getListeUtilisateur().get(i1).getNom());
					}

				}

			}

		});
		
        vuePageAccueil.getBtnUser().setOnAction(event -> {
        	vueAppAdminUser = new VueAppAdmin("user", "", "");
            stage.setScene(vueAppAdminUser);
            stage.setTitle(" Patrick School - ApplicationUtilisateur ");
        	
        });	
    
        stage.setScene(vuePageAccueil); //quand remplacer vuePageAcceuil par vueAppAdmin = ok on a le boutton file
        stage.show();
    	
    	
    }
    public static void main(String[] args) {
        launch();
    }
}
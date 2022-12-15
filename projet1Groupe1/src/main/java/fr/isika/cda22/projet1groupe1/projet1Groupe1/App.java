package fr.isika.cda22.projet1groupe1.projet1Groupe1;


import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
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
	
	// attributs ---------------------------------------------------------------------------------------------------------
	public VuePageAcceuil vuePageAccueil;
	public VueLoginAdmin vueLoginAdmin;
	public VueLoginUser vueLoginUser;
	public VueAppAdmin vueAppAdmin;
	public VueAppAdmin vueAppAdminUser;
	public VueAjouterFichier vueAjouterFichier;
	public VueAjouterStagiaire vueAjouterStagiaire;

	public void start(Stage stage) {
		Administrateur admin = new Administrateur();
		admin.recupereLoginAdmin();
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.recupereLoginUtilisateur();

		stage.setTitle(" Patrick's School - Acceuil ");

		// creation des vues
		vuePageAccueil = new VuePageAcceuil();
		vueLoginAdmin = new VueLoginAdmin();
		vueLoginUser = new VueLoginUser();

		// setup des boutons des vues
		vuePageAccueil.getBtnAdmin().setOnAction(event -> {
			stage.setScene(vueLoginAdmin);
			stage.setTitle(" Patrick's School - Login ");

		});

		vuePageAccueil.getBtnUser().setOnAction(event -> {
			stage.setScene(vueLoginUser);
			stage.setTitle(" Patrick's School - Login ");

		});

		vueLoginAdmin.getBtnLogin().setOnAction(event -> {

			for (int i = 0; i < admin.getListeAdmin().size(); i++) {
				if ((vueLoginAdmin.getFieldId().getText().equals(admin.getListeAdmin().get(i).getLogin())
						&& vueLoginAdmin.getFieldMdp().getText().equals(admin.getListeAdmin().get(i).getMdp())) || (vueLoginAdmin.getFieldId().getText().equals(admin.getListeAdmin().get(i).getLogin())
								&& vueLoginAdmin.getTxtreveal().getText().equals(admin.getListeAdmin().get(i).getMdp()))) {
					
					stage.setTitle(" Patrick's School - Administrateur : " + admin.getListeAdmin().get(i).getPrenom()
							+ " " + admin.getListeAdmin().get(i).getNom());
					vueAppAdmin = new VueAppAdmin("admin", admin.getListeAdmin().get(i).getNom(),
							admin.getListeAdmin().get(i).getPrenom());
					
					
					vueAppAdmin.getBtnDoc().setOnAction(e -> {
						File file = new File("D://ISIKA/CDA22/Projet1Groupe1/projet1Groupe1/src/main/java/Fichier/Guide Utilisateur.pdf");
						HostServices hostServices = getHostServices();
						hostServices.showDocument(file.getAbsolutePath());
					});
					
				
					}
					stage.setScene(vueAppAdmin);
					//stage.setTitle(" Patrick's School - Administrateur : " + admin.getListeAdmin().get(i).getPrenom()+ " " + admin.getListeAdmin().get(i).getNom());

				}
				/*
				for (int i1 = 0; i1 < utilisateur.getListeUtilisateur().size(); i1++) {
					if (vueLoginAdmin.getFieldId().getText()
							.equals(utilisateur.getListeUtilisateur().get(i1).getLogin())
							&& vueLoginAdmin.getFieldMdp().getText()
									.equals(utilisateur.getListeUtilisateur().get(i1).getMdp())) {
						vueAppAdminUser = new VueAppAdmin("user", utilisateur.getListeUtilisateur().get(i1).getNom(),
								utilisateur.getListeUtilisateur().get(i1).getPrenom());
						stage.setScene(vueAppAdminUser);
						stage.setTitle(" Patrick's School - Utilisateur : "
								+ utilisateur.getListeUtilisateur().get(i1).getPrenom() + " "
								+ utilisateur.getListeUtilisateur().get(i1).getNom());
					}

				}
				*/

			

		});

		vueLoginUser.getBtnLogin().setOnAction(event -> {
			for (int i = 0; i < utilisateur.getListeUtilisateur().size(); i++) {
				if ((vueLoginUser.getFieldId().getText().equals(utilisateur.getListeUtilisateur().get(i).getLogin())
						&& vueLoginUser.getFieldMdp().getText().equals(utilisateur.getListeUtilisateur().get(i).getMdp())) || (vueLoginUser.getFieldId().getText().equals(utilisateur.getListeUtilisateur().get(i).getLogin())
								&& vueLoginUser.getTxtreveal().getText().equals(utilisateur.getListeUtilisateur().get(i).getMdp()))) {
					stage.setTitle(" Patrick's School - Utilisateur : " + utilisateur.getListeUtilisateur().get(i).getPrenom()
							+ " " + utilisateur.getListeUtilisateur().get(i).getNom());
					vueAppAdminUser = new VueAppAdmin("user", utilisateur.getListeUtilisateur().get(i).getNom(),
							utilisateur.getListeUtilisateur().get(i).getPrenom());
					
					vueAppAdminUser.getBtnDoc().setOnAction(e -> {
						File file = new File("D://ISIKA/CDA22/Projet1Groupe1/projet1Groupe1/src/main/java/Fichier/Guide Utilisateur.pdf");
						HostServices hostServices = getHostServices();
						hostServices.showDocument(file.getAbsolutePath());
					});
					
					stage.setScene(vueAppAdminUser);
					

				}
				/*
				for (int i1 = 0; i1 < utilisateur.getListeUtilisateur().size(); i1++) {
					if (vueLoginUser.getFieldId().getText().equals(utilisateur.getListeUtilisateur().get(i1).getLogin())
							&& vueLoginUser.getFieldMdp().getText()
									.equals(utilisateur.getListeUtilisateur().get(i1).getMdp())) {
						vueAppAdminUser = new VueAppAdmin("user", utilisateur.getListeUtilisateur().get(i1).getNom(),
								utilisateur.getListeUtilisateur().get(i1).getPrenom());
						stage.setScene(vueAppAdminUser);
						stage.setTitle(" Patrick's School - Utilisateur : "
								+ utilisateur.getListeUtilisateur().get(i1).getPrenom() + " "
								+ utilisateur.getListeUtilisateur().get(i1).getNom());
					}

				}
				*/

			}

		});
		
		/*
		vueLoginUser.getBtnLogin().setOnAction(event -> {
			for (int i = 0; i < admin.getListeAdmin().size(); i++) {
				if ((vueLoginUser.getFieldId().getText().equals(admin.getListeAdmin().get(i).getLogin())
						&& vueLoginUser.getFieldMdp().getText().equals(admin.getListeAdmin().get(i).getMdp())) || (vueLoginUser.getFieldId().getText().equals(admin.getListeAdmin().get(i).getLogin())
								&& vueLoginUser.getTxtreveal().getText().equals(admin.getListeAdmin().get(i).getMdp()))) {
					vueAppAdmin = new VueAppAdmin("admin", admin.getListeAdmin().get(i).getNom(),
							admin.getListeAdmin().get(i).getPrenom());
					stage.setScene(vueAppAdmin);
					stage.setTitle(" Patrick's School - Administrateur : " + admin.getListeAdmin().get(i).getPrenom()
							+ " " + admin.getListeAdmin().get(i).getNom());

				}
				for (int i1 = 0; i1 < utilisateur.getListeUtilisateur().size(); i1++) {
					if (vueLoginUser.getFieldId().getText().equals(utilisateur.getListeUtilisateur().get(i1).getLogin())
							&& vueLoginUser.getFieldMdp().getText()
									.equals(utilisateur.getListeUtilisateur().get(i1).getMdp())) {
						vueAppAdminUser = new VueAppAdmin("user", utilisateur.getListeUtilisateur().get(i1).getNom(),
								utilisateur.getListeUtilisateur().get(i1).getPrenom());
						stage.setScene(vueAppAdminUser);
						stage.setTitle(" Patrick's School - Utilisateur : "
								+ utilisateur.getListeUtilisateur().get(i1).getPrenom() + " "
								+ utilisateur.getListeUtilisateur().get(i1).getNom());
					}

				}

			}

		});
		*/


		stage.setScene(vuePageAccueil); // quand remplacer vuePageAcceuil par vueAppAdmin = ok on a le boutton file
		stage.show();

	}

	public static void main(String[] args) {
		launch();
	}
}

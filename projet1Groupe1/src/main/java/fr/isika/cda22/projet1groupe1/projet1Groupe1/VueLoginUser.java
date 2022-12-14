package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import javafx.scene.control.Button;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.io.InputStream;
import java.util.Currency;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Classe pour instancier des vues login User
 *
 */

public class VueLoginUser extends Scene implements ParametreGestionnaire{
	
	//attributs ------------------------------------------------------------------------------------------------
	private Button btnLogin;
	private TextField fieldId;
	private PasswordField fieldMdp;
	private Button btnreveal;
	private TextField txtreveal;
	boolean textactive = false;
	
	//constructeur --------------------------------------------------------------------------------------------
	/**
	 * Constructeur pour creer une vue login User
	 */
	public VueLoginUser () {
		super(new GridPane(), 800, 320);
		GridPane rootLoginAdmin = (GridPane)this.getRoot();
		rootLoginAdmin.setPadding(new Insets (50));
		rootLoginAdmin.setHgap(15);
		rootLoginAdmin.setVgap(15);
		this.setRoot(rootLoginAdmin);
		
		Label labelTitreAdm = new Label("Bienvenue cher Utilisateur,\nveuillez entrer vos identifiants :");
		rootLoginAdmin.add(labelTitreAdm, 1,0,3,3);
		
		Label labelId = new Label("Identifiant");
		fieldId = new TextField();
		fieldId.setPromptText("entrez identifiants");
		
		Label labelmdp = new Label("Mot de passe");
		fieldMdp = new PasswordField();
		fieldMdp.setPromptText("Mot de passe");
		txtreveal = new TextField();
		
		btnLogin = new Button ("Valider");
		
		// creation bouton reveal
		btnreveal = new Button ();
		InputStream input = getClass().getResourceAsStream("/icon/oeil.png");
		Image image = new Image(input);
		ImageView imageView = new ImageView(image);
		btnreveal.setGraphic(imageView);
		
	    btnreveal.setOnAction(new EventHandler<ActionEvent>() {
	      @Override
	      public void handle(ActionEvent event) {
	       
	        if (textactive == false) {
	       	 String password = fieldMdp.getText();
		        txtreveal.setText(password);
		        fieldMdp.setVisible(false);
		        txtreveal.setVisible(true);
		        textactive = true;
		       
	        }else {
	       	 String fieldMdp1 = txtreveal.getText();
	       	 fieldMdp.setText(fieldMdp1);
	       	 fieldMdp.setVisible(true);
		        txtreveal.setVisible(false);
	       	 textactive = false;
	        }
	      	 
	      }
	    });
		
		
		
		rootLoginAdmin.add(labelId, 1,3);
		rootLoginAdmin.add(fieldId, 2, 3);
		rootLoginAdmin.add(txtreveal, 2, 4);
		txtreveal.setVisible(false);
		
		rootLoginAdmin.add(labelmdp, 1, 4);
		rootLoginAdmin.add(fieldMdp, 2, 4);
		rootLoginAdmin.add(btnreveal, 3, 4);
		rootLoginAdmin.add(btnLogin, 2, 5);
		
		// FALSE CSS
		if (os.equals("PC")) {
			labelTitreAdm.setFont(FONTTITRE);
			labelId.setFont(FONTTEXTE);
			labelmdp.setFont(FONTTEXTE);
			btnLogin.setFont(FONTBUTTON);
			fieldId.setFont(FONTTEXTE);
			fieldMdp.setFont(FONTTEXTE);
			txtreveal.setFont(FONTTEXTE);
	   	
	    }
	  
		labelTitreAdm.setTextFill(Color.rgb(60, 60, 60));
		labelId.setTextFill(Color.rgb(60, 60, 60));
		labelmdp.setTextFill(Color.rgb(60, 60, 60));
	  
		btnreveal.setStyle(BUTTONCOLOR);
		
		btnLogin.setPrefWidth(120);
		btnLogin.setTextFill(Color.rgb(61, 110, 139));
		btnLogin.setStyle(BUTTONCOLOR);
		btnLogin.setOnMouseEntered((event) -> {
			btnLogin.setStyle(BUTTONCOLOROVER);
	  	btnLogin.setTextFill(Color.rgb(240, 240, 240));
		});
		btnLogin.setOnMouseExited((event) -> {
			btnLogin.setStyle(BUTTONCOLOR);
			btnLogin.setTextFill(Color.rgb(61, 110, 139));
		});
	 
	  
	this.getRoot().setStyle("-fx-font-family: 'serif'");	
	rootLoginAdmin.setStyle("-fx-font-family: 'serif'; -fx-background-image: url('file:src/main/java/icon/BG_Login.png')");
		
}
	
	// Getters and setters ---------------------------------------------------------------------------------------------------
	public Button getBtnLogin() {
		return btnLogin;
	}
	public void setBtnLogin(Button btnLogin) {
		this.btnLogin = btnLogin;
	}
	
	public TextField getFieldId() {
		return fieldId;
	}
	public void setFieldId(TextField fieldId) {
		this.fieldId = fieldId;
	}
	public TextField getFieldMdp() {
		return fieldMdp;
	}
	public void setFieldMdp(PasswordField fieldMdp) {
		this.fieldMdp = fieldMdp;
	}

	public boolean isTextactive() {
		return textactive;
	}

	public void setTextactive(boolean textactive) {
		this.textactive = textactive;
	}

	public TextField getTxtreveal() {
		return txtreveal;
	}

	public void setTxtreveal(TextField txtreveal) {
		this.txtreveal = txtreveal;
	}
	
	
	
	
}
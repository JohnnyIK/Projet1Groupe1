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

import java.util.Currency;

import javafx.application.Application;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



public class VueAppAdmin extends Scene {
		
		//attributs 
		Button btnLogin;
		
		
		public VueAppAdmin () {
			super(new GridPane(), 600, 300);
			GridPane rootAppAdmin = (GridPane)this.getRoot();
			rootAppAdmin.setPadding(new Insets (50));
			rootAppAdmin.setHgap(15);
			rootAppAdmin.setVgap(15);
			
			this.setRoot(rootAppAdmin);
			this.getRoot().setStyle("-fx-font-family: 'serif'");
			
			
			Label LabelAppAdmin  = new Label("Bienvenu cher Administrateur, veuillez entrer vos identifiants :");
			rootAppAdmin.add(LabelAppAdmin, 2,2,3,1);
			
			
			
			
		
}}

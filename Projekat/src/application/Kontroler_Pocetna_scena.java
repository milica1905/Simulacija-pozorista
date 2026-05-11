package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.*;

public class Kontroler_Pocetna_scena {
	 
	private Stage stage;
	private Scene scene;
	private Parent root;
	
		@FXML
	    private Label dobrodosli_labela;

	    @FXML
	    private Button posjetilac_button;

	    @FXML
	    private Button radnik_button;
	    
	    @FXML
	    private Label identifikacija_labela;
	    
	    @FXML
	    void switchtoPosjetilacScene(ActionEvent event) {
	    	try {
				root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setTitle("Dobrodošli posjetioče, ukoliko imate nalog, prijavite se!");
				stage.setScene(scene);
				stage.show();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void switchtoRadnikScene(ActionEvent event) {
	    	try {
				root = FXMLLoader.load(getClass().getResource("Radnik_prijava.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setTitle("Dobrodošli radniče, prijavite se!");
				stage.setScene(scene);
				stage.show();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
	    }

}

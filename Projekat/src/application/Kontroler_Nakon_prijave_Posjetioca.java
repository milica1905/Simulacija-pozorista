package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Kontroler_Nakon_prijave_Posjetioca implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
		@FXML
	    private Button odjava_dugme;

	    @FXML
	    private Button pregled_karata_dugme;

	    @FXML
	    private Button pozorista_dugme;

	    @FXML
	    private Button spisak_osoblja_dugme;
	    
	    @FXML
	    void biranje_pozorista(ActionEvent event) {
	    	try {
	    		Parent root = FXMLLoader.load(getClass().getResource("Pozorista_Scena.fxml"));
	    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    		Scene scene = new Scene(root);
	    		String imePrezime;
	    		if(KontrolerMain.prijava) {
	    			imePrezime = KontrolerMain.imeIPrezimePosjetioca();
	    		}
	    		else {
	    			imePrezime = Kontroler_Registracija_Posjetilac.vratiImePrezimePosjetiocaRegistracija();
	    		}
	    		stage.setTitle("Posjetioče, " + imePrezime + " izaberite pozorište u koje želite da idete!");
	    		stage.setScene(scene);
	    		stage.show();
	    	}
	    	catch(IOException e) {
	    		e.printStackTrace();
	    	}
	    }

	    @FXML
	    void odjavljivanje(ActionEvent event) {
	    	Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
		 	upozorenje.setTitle("ODJAVA");
		 	upozorenje.setContentText("Da li ste sigurni da želite da se odjavite?");
	    
		 	Optional <ButtonType>result = upozorenje.showAndWait();
		 	if(result.get()==ButtonType.OK) {
		    	try {
					root = FXMLLoader.load(getClass().getResource("Pocetna_scena.fxml"));
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setTitle("Dobrodošli!");
					stage.setScene(scene);
					stage.show();
				}
		    	catch(IOException e) {
		    		e.printStackTrace();
		    	}
		 	}
	    }

	    @FXML
	    void prikazi_karte(ActionEvent event) {
	    	try {
	    		Parent root = FXMLLoader.load(getClass().getResource("Pregled_karata_scena.fxml"));
	    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    		Scene scene = new Scene(root);
	    		String imePrezime;
	    		if(KontrolerMain.prijava) {
	    			imePrezime = KontrolerMain.imeIPrezimePosjetioca();
	    		}
	    		else {
	    			imePrezime = Kontroler_Registracija_Posjetilac.vratiImePrezimePosjetiocaRegistracija();
	    		}
	    		stage.setTitle("Posjetioče, "+imePrezime+ "ovde možete pogledati vaše karte");
	    		stage.setScene(scene);
	    		stage.show();
	    	}
	    	catch(IOException e) {
	    		e.printStackTrace();
	    	}
	    }

	    @FXML
	    void prikazi_spisak_osoblja(ActionEvent event) {
	    	try {
	    		Parent root = FXMLLoader.load(getClass().getResource("Spisak_Osoblja_Scenaa.fxml"));
	    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    		Scene scene = new Scene(root);
	    		String imePrezime;
	    		if(KontrolerMain.prijava) {
	    			imePrezime = KontrolerMain.imeIPrezimePosjetioca();
	    		}
	    		else {
	    			imePrezime = Kontroler_Registracija_Posjetilac.vratiImePrezimePosjetiocaRegistracija();
	    		}
	    		stage.setTitle("Posjetioče, "+imePrezime+ " u prilogu je spisak osoblja!");
	    		stage.setScene(scene);
	    		stage.show();
	    	}
	    	catch(IOException e) {
	    		e.printStackTrace();
	    	}
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}
}

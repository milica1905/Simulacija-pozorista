package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Kontroler_Kreiraj_Radnika implements Initializable {

    @FXML
    private Button nazad_dugme;

    @FXML
    private ChoiceBox<Pozoriste> padajuciMeniPozoristeRegRadnika;

    @FXML
    private Button registrujteRadnika;

    @FXML
    private TextField textField_Ime_Radnika_Registracija;

    @FXML
    private TextField textField_Korisnicko_Radnika_Registracija;

    @FXML
    private PasswordField passwordField_Lozinka_Radnika_Registracija;

    @FXML
    private TextField textField_Prezime_Radnika_Registracija;

    private Connection connect = null;
    private PreparedStatement statement = null;
    private PreparedStatement postoji = null;
    private ResultSet result = null;
    
    @FXML
    void registruj_Novog_Radnika(ActionEvent event) {
    	if(textField_Korisnicko_Radnika_Registracija.getText().equals("") && passwordField_Lozinka_Radnika_Registracija.getText().equals("") && textField_Ime_Radnika_Registracija.getText().equals("") && textField_Prezime_Radnika_Registracija.getText().equals("")) {
    		Alert alert1 = new Alert(Alert.AlertType.ERROR);
			alert1.setContentText("Popunite polja");
			alert1.show();
    	}
    	else if(textField_Korisnicko_Radnika_Registracija.getText().equals("") || passwordField_Lozinka_Radnika_Registracija.getText().equals("") || textField_Ime_Radnika_Registracija.getText().equals("") || textField_Prezime_Radnika_Registracija.getText().equals("")) {
    		Alert alert4 = new Alert(Alert.AlertType.ERROR);
			alert4.setContentText("Sva polja moraju biti popunjena");
			alert4.show();
    	}
    	else if(padajuciMeniPozoristeRegRadnika.getValue() == null) {
    		Alert alert5 = new Alert(Alert.AlertType.ERROR);
    		alert5.setContentText("Neophodno je da izaberete pozoriste");
    		alert5.show();
    	}
    	else if(Kontroler_Nakon_prijave_Radnika.vratiPozoristeID() != padajuciMeniPozoristeRegRadnika.getValue().getId() && Radnik_Pozorista.daLiNekoRadiUPozoristu(padajuciMeniPozoristeRegRadnika.getValue().getId())) {
			Alert alert6 = new Alert(Alert.AlertType.ERROR);
    		alert6.setContentText("Ukoliko zelite da dodate novog radnika u pozoristu u kojem NE radite neophodno je da izaberete pozoriste u kome nema radnika\n" + padajuciMeniPozoristeRegRadnika.getValue().getNaziv() + " pozoriste vec posjeduje radnika.");
    		alert6.show();			
	}
    	
    	else {
    	connect = Konekcija.getConnection();
    	String sql = "SELECT * FROM radnik_pozorista WHERE korisnicko_ime = ?";
    	try {
    			postoji = connect.prepareStatement(sql);
    			postoji.setString(1, textField_Korisnicko_Radnika_Registracija.getText());
    			
    			result = postoji.executeQuery();
    			if(result.isBeforeFirst()) {
    				Alert alert = new Alert(Alert.AlertType.ERROR);
    				alert.setContentText("Korisnicko ime " + textField_Korisnicko_Radnika_Registracija.getText() + " vec postoji. Molimo Vas unesite novo korisnicko! ");
    				alert.show();
    				textField_Korisnicko_Radnika_Registracija.setText("");
    				passwordField_Lozinka_Radnika_Registracija.setText("");
    			}
    			else {
    				statement = connect.prepareStatement("INSERT INTO radnik_pozorista (ime, prezime, korisnicko_ime, lozinka, pozoriste_id) VALUE (?,?,?,?,?)");
    				statement.setString(1, textField_Ime_Radnika_Registracija.getText());
    				statement.setString(2, textField_Prezime_Radnika_Registracija.getText());
    				statement.setString(3, textField_Korisnicko_Radnika_Registracija.getText());
    				statement.setString(4, Radnik_Pozorista.getMd5(passwordField_Lozinka_Radnika_Registracija.getText()));
    				statement.setInt(5, padajuciMeniPozoristeRegRadnika.getValue().getId());
    				statement.executeUpdate();
    				System.out.println("ALALASLSALSALSAL");
    				Konekcija.ucitajRadnika();
    				
    				Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
	        		alert4.setTitle("OBAVJESTENJE!");
	        		alert4.setContentText("Uspjesno ste dodali radnika!");
	        		Optional <ButtonType>result1 = alert4.showAndWait();
	    		 	if(result1.get()==ButtonType.OK) {
	    		 		try {
	    					Parent root = FXMLLoader.load(getClass().getResource("Nakon_prijave_Radnika.fxml"));
	    					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    					Scene scene = new Scene(root);
	    					stage.setTitle("Dobrodošli radniče ");
	    					stage.setScene(scene);
	    					stage.show();
	    				}
	    				catch(IOException e) {
	    					e.printStackTrace();
	    				}
	    		 	}
    				
    			}  			
    		}
    	catch(Exception e) {
    		e.printStackTrace();
    		}
    	if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	}
    }

    @FXML
    void switchto_Nakon_prijave_Radnika(ActionEvent event) {
    	Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
		upozorenje.setTitle("POVRATAK NAZAD");
		upozorenje.setContentText("Molimo Vas da potvrdite da želite da se vratite na prethodnu stranicu.");

		Optional<ButtonType> result = upozorenje.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("Nakon_prijave_Radnika.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setTitle("Dobrodošli radniče " + Kontroler_Radnik_prijava.imeIPrezimeRadnika());
				stage.setScene(scene);
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		padajuciMeniPozoristeRegRadnika.getItems().addAll(Pozoriste.getLista_pozorista());
		
	}
}

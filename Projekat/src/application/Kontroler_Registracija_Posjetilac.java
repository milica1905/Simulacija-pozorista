package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Kontroler_Registracija_Posjetilac implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
    @FXML
    private TextField registracija_ime_id;

    @FXML
    private TextField registracija_korisnicko_id;

    @FXML
    private PasswordField registracija_lozinka_id;

    @FXML
    private TextField registracija_prezime_id;

    @FXML
    private Button registrujte_se_button;
    
    @FXML
    private Button nazad_dugme;
    
    private Connection connect = null;
    private PreparedStatement statement = null;

    
    public static String korisnickoPosjetioicaRegistracija;
    private static String ime;
    private static String prezime;
    public static String vratiImePrezimePosjetiocaRegistracija() {
    	return ime +" , " + prezime;
    }

    @FXML
    void registrujte_se_klik(ActionEvent event) {

    	if(registracija_korisnicko_id.getText().equals("") && registracija_lozinka_id.getText().equals("") && registracija_ime_id.getText().equals("") && registracija_prezime_id.getText().equals("")) {
    		Alert alert_nisu_unesene_informacije = new Alert(Alert.AlertType.ERROR);
    		alert_nisu_unesene_informacije.setContentText("Unesite neophodne informacije!");
    		alert_nisu_unesene_informacije.show();
    	}
    	else if(registracija_korisnicko_id.getText().equals("") || registracija_lozinka_id.getText().equals("") || registracija_ime_id.getText().equals("") || registracija_prezime_id.getText().equals("")) {
    		Alert alert_nisu_popunjena_sva_polja = new Alert(Alert.AlertType.ERROR);
    		alert_nisu_popunjena_sva_polja.setContentText("Molimo popunite sva polja!");
    		alert_nisu_popunjena_sva_polja.show();
    	}
    	else {	 
    		boolean postojiKorisnicko = false;
    		for(Posjetilac_pozorista posjetioci : Posjetilac_pozorista.getLista_posjetilaca_pozorista()) {
    			if(posjetioci.getKorisnicko_ime().equals(registracija_korisnicko_id.getText())) {
    				postojiKorisnicko = true;
    				break;
    			}
    		}
    		if(postojiKorisnicko) {
    			Alert alert_postoji_korisnicko = new Alert(Alert.AlertType.WARNING);
    			alert_postoji_korisnicko.setTitle("UPOZORENJE");
    			alert_postoji_korisnicko.setContentText("Korisničko ime " + registracija_korisnicko_id.getText() + " vec postoji. Molimo Vas da unesete drugo!");
    			alert_postoji_korisnicko.show();
	    	 	registracija_korisnicko_id.setText("");
	    	 	registracija_lozinka_id.setText("");
	    	 	registracija_ime_id.setText("");
	    	 	registracija_prezime_id.setText("");
    		}
    		else {
    			connect = Konekcija.getConnection();
				boolean dodavanje_posjetioca= false;
				try {
					statement = connect.prepareStatement("INSERT INTO posjetilac_pozorista (ime, prezime, korisnicko_ime, lozinka) VALUE (?,?,?,?)");
					statement.setString(1, registracija_ime_id.getText());
					statement.setString(2, registracija_prezime_id.getText());
					statement.setString(3, registracija_korisnicko_id.getText());
					statement.setString(4, Posjetilac_pozorista.getMd5(registracija_lozinka_id.getText()));
					statement.executeUpdate();
					Konekcija.ucitajPosjetioca();
					dodavanje_posjetioca=true;
					
					korisnickoPosjetioicaRegistracija=registracija_korisnicko_id.getText();
    				
					registrujte_se_button.getScene().getWindow().hide();
    				Parent root = FXMLLoader.load(getClass().getResource("Scena_nakonprijavePosjetioca.fxml"));
    				Scene scene = new Scene(root);
    				Stage stage = new Stage();
    				ime = registracija_ime_id.getText();
    				prezime=  registracija_prezime_id.getText();
    				stage.setTitle("Dobrodošli posjetioče, " + Kontroler_Registracija_Posjetilac.vratiImePrezimePosjetiocaRegistracija());
    				stage.setScene(scene);
    				stage.show();
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
    }
    
    @FXML
    void switchtoLogIn_scenu(ActionEvent event) {
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
    void switchtoMainScene(ActionEvent event) {
    	Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
	 	upozorenje.setTitle("POVRATAK NAZAD");
	 	upozorenje.setContentText("Molimo Vas da potvrdite da želite da se vratite na prethodnu stranicu.");
    
	 	Optional <ButtonType>result = upozorenje.showAndWait();
	 	if(result.get()==ButtonType.OK) {
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
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
    
    
}
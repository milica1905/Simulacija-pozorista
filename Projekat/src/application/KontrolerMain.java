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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class KontrolerMain implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
    private Button prijava_dugme;

	@FXML
    private Button registracija_dugme;

    @FXML
    private TextField korisnicko_posjetilacID;

    @FXML
    private PasswordField posjetilac_lozinkaID;
    
    @FXML
    private Button nazad_buttonID;
    
    @FXML
    void registracijaKlik(ActionEvent event) {
    	try {
			root = FXMLLoader.load(getClass().getResource("Registracija_Posjetilac.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Dobrodošli posjetioče, ukoliko nemate nalog, registrujte se!");
			stage.setScene(scene);
			stage.show();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
    }
    
    private Connection connect = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;
    public static boolean prijava;
    public static String korisnicko_Posjetilac_prijava;
    private static int idPosjetioca=0;
    
    private static String ime;
    private static String prezime;
    public static String imeIPrezimePosjetioca() {
    	return ime +" , " + prezime;
    }
    
    @FXML
    void switchtoVratiNaPocetnu(ActionEvent event) {
    	Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
	 	upozorenje.setTitle("POVRATAK NAZAD");
	 	upozorenje.setContentText("Molimo Vas da potvrdite da želite da se vratite na početnu stranicu.");
    
	 	Optional <ButtonType>result = upozorenje.showAndWait();
	 	if(result.get()==ButtonType.OK) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Pocetna_scena.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setTitle("DOBRODOSLI!");
			stage.setScene(scene);
			stage.show();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	 	}
    }
    
    @FXML
    void prijava_klik(ActionEvent event) {
		if(korisnicko_posjetilacID.getText().equals("") && posjetilac_lozinkaID.getText().equals("")) {
    		Alert alert_prazna_polja = new Alert(Alert.AlertType.ERROR);
    		alert_prazna_polja.setContentText("Polja ne mogu biti prazna! Molimo popunite polja.");
    		alert_prazna_polja.show();
    	}
    	else if(korisnicko_posjetilacID.getText().equals("")) {
    		Alert alert_prazno_korisnicko = new Alert(Alert.AlertType.ERROR);
    		alert_prazno_korisnicko.setContentText("Vaše polje je prazno. Molimo unesite korisničko ime!");
    		alert_prazno_korisnicko.show();
    	}
		
    	else if(posjetilac_lozinkaID.getText().equals("")) {
    		Alert alert_prazna_lozinka = new Alert(Alert.AlertType.ERROR);
    		alert_prazna_lozinka.setContentText("Vaše polje je prazno. Molimo unesite lozinku!");
    		alert_prazna_lozinka.show();
    	}
    	else {
    		boolean postojiPosjetilac = false;
    		String korisnickoIme = korisnicko_posjetilacID.getText();

    		for (Posjetilac_pozorista posjetioci : Posjetilac_pozorista.getLista_posjetilaca_pozorista()) {
    		    if (posjetioci.getKorisnicko_ime().equals(korisnickoIme)) {
    		        postojiPosjetilac = true;

    		        String unesenaLozinka = posjetilac_lozinkaID.getText();
    		        String md5Lozinka = Posjetilac_pozorista.getMd5(unesenaLozinka);

    		        if (!posjetioci.getLozinka().equals(md5Lozinka)) {
    		        	posjetilac_lozinkaID.setText("");
    		            Alert alert_neispravna_lozinka = new Alert(Alert.AlertType.WARNING);
    		            alert_neispravna_lozinka.setTitle("UPOZORENJE");
    		            alert_neispravna_lozinka.setContentText("Neispravna lozinka za " + korisnickoIme + ". Pokušajte ponovo");
    		            alert_neispravna_lozinka.show();
    		        }
    		        else {
    		        	connect = Konekcija.getConnection();
        		    	String sql = "SELECT * FROM posjetilac_pozorista WHERE korisnicko_ime = ? AND lozinka = ?";
        		    	try {
        		    			statement = connect.prepareStatement(sql);
        		    			statement.setString(1, korisnicko_posjetilacID.getText());
        		    			statement.setString(2, Posjetilac_pozorista.getMd5(posjetilac_lozinkaID.getText())); //Posjetilac_pozorista.getMd5(unesenaLozinka)
        		    			result = statement.executeQuery();
        		    			if(result.next()) {
        		    				prijava = true;
        		    				korisnicko_Posjetilac_prijava=korisnicko_posjetilacID.getText();
        		    				ime=posjetioci.getIme();
        		    				prezime=posjetioci.getPrezime();
        		    				idPosjetioca= posjetioci.getId();
        		    				prijava_dugme.getScene().getWindow().hide();
        		    				Parent root = FXMLLoader.load(getClass().getResource("Nakon_prijave_Posjetioca.fxml"));
        		    				Scene scene = new Scene(root);
        		    				Stage stage = new Stage();
        		    				stage.setTitle("Dobrodošli posjetioče " + KontrolerMain.imeIPrezimePosjetioca());
        		    				stage.setScene(scene);
        		    				stage.show();
        		    				
        		    			}
        		    			else {	
        		    				
        		    				Alert alert_neispravan_unos = new Alert(Alert.AlertType.ERROR);
        		    				alert_neispravan_unos.setContentText("Neispravan unos!");
        		    				alert_neispravan_unos.show();
        		    		
        		    		
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
    		    
    		}
    		if(!postojiPosjetilac) {
				 Alert alert_ne_postoji_korisnicko = new Alert(Alert.AlertType.WARNING);
				 alert_ne_postoji_korisnicko.setTitle("UPOZORENJE");
				 alert_ne_postoji_korisnicko.setContentText("Korisničko ime: " + korisnicko_posjetilacID.getText() + " ne postoji.Pokusajte ponovo");
				 alert_ne_postoji_korisnicko.show();
		    	 korisnicko_posjetilacID.setText("");
		    	 posjetilac_lozinkaID.setText("");
			 }
    	}		
    } //zavrsetak prijave klik
    
    public void registracija_klik(ActionEvent event){
		try {
			prijava = false;
			Parent root = FXMLLoader.load(getClass().getResource("Registracija_Posjetilac.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setTitle("Dobrodošli posjetioce, registrujte se");
			stage.setScene(scene);
			stage.show();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
    
    public static int getIdPosjetioca() {
		return idPosjetioca;
	}	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

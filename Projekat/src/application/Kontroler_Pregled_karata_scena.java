package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Kontroler_Pregled_karata_scena implements Initializable{

    @FXML
    private ListView<Karta> ListView_Aktivne_rezervacije;

    @FXML
    private ListView<Karta> ListView_Istekle_rezervacije;
    
    @FXML
    private Button nazad_dugme;
    

    @FXML
    void switchtoNakon_prijave_Posjetioca(ActionEvent event) {
    	Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
	 	upozorenje.setTitle("POVRATAK NAZAD");
	 	upozorenje.setContentText("Molimo Vas da potvrdite da želite da se vratite na prethodnu stranicu.");
    
	 	Optional <ButtonType>result = upozorenje.showAndWait();
	 	if(result.get()==ButtonType.OK) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Nakon_prijave_Posjetioca.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			String imePrezime;
    		if(KontrolerMain.prijava) {
    			imePrezime = KontrolerMain.imeIPrezimePosjetioca();
    		}
    		else {
    			imePrezime = Kontroler_Registracija_Posjetilac.vratiImePrezimePosjetiocaRegistracija();
    		}
    		stage.setTitle("Dobrodošli posjetioče " + imePrezime);
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

		
		ArrayList <Karta> aktivne = new ArrayList<>();
		ArrayList <Karta> istekle = new ArrayList<>();
		
		String korisnicko;
		if(KontrolerMain.prijava) {
		 	 korisnicko = KontrolerMain.korisnicko_Posjetilac_prijava;
		 	}
		 	else {
		 		 korisnicko=Kontroler_Registracija_Posjetilac.korisnickoPosjetioicaRegistracija;
		 	}
		
		Posjetilac_pozorista pp = Posjetilac_pozorista.vratiPosjetiocaNaOsnovuStringa(korisnicko);
		for(Karta k : Karta.getLista_karata()) {
			if(k.getPosjetilac_id() == pp.getId() && k.getStatus()!=1) {
				Izvodjenje_predstave ip = Izvodjenje_predstave.vratiIzvodjenjeNaOsnovuID(k.getIzvodjenje_predstave());
				if(Izvodjenje_predstave.daLiSeIzvodiUNarednomPeriodu(ip.getDatum_i_vrijeme())) {
					aktivne.add(k);
				}
				else {
					istekle.add(k);
				}
				
			}
		}
		ListView_Aktivne_rezervacije.getItems().addAll(aktivne);
		ListView_Istekle_rezervacije.getItems().addAll(istekle);
		
	
			
	}
		
	}


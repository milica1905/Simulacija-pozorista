package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Kontroler_Spisak_osoblja_Scena implements Initializable{
	
	@FXML
    private ListView<Osoblje> ListView_Lista_autora;

    @FXML
    private ListView<Osoblje> ListView_Lista_glumaca;

    @FXML
    private ListView<Osoblje> ListView_Lista_rezisera;

    @FXML
    private Button dugme_prikazi_info_autora;

    @FXML
    private Button dugme_prikazi_info_glumaca;

    @FXML
    private Button dugme_prikazi_info_rezisera;

    @FXML
    private Button nazad_dugme;

    @FXML
    private TextArea textArea_Ispis_informacija;

    @FXML
    void prikazivanje_Informacija_O_Glumcima(ActionEvent event) {
    	Osoblje selektovaniGlumac = ListView_Lista_glumaca.getSelectionModel().getSelectedItem();
    	textArea_Ispis_informacija.clear();
    	textArea_Ispis_informacija.setText(Osoblje.ispisInf(selektovaniGlumac));
    }
    
    @FXML
    void prikazivanje_Informacija_O_Reziserima(ActionEvent event) {	
    	Osoblje selektovaniReziser = ListView_Lista_rezisera.getSelectionModel().getSelectedItem();
    	textArea_Ispis_informacija.clear();
    	textArea_Ispis_informacija.setText(Osoblje.ispisInf(selektovaniReziser));
    	
    }
    
    @FXML
    void prikazivanje_Informacija_O_Autorima(ActionEvent event) {
    	Osoblje selektovaniAutor = ListView_Lista_autora.getSelectionModel().getSelectedItem();
    	textArea_Ispis_informacija.clear();
    	textArea_Ispis_informacija.setText(Osoblje.ispisInf(selektovaniAutor));
    }
    

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
		// Lista glumaca
		ObservableList<Osoblje> glumciItems = FXCollections.observableArrayList();
    	for(Osoblje o: Osoblje.getLista_osoblja()) {
    		if(o.getTip() == 1)
    			glumciItems.add(o);
    	}	
    	ListView_Lista_glumaca.getItems().clear();
    	ListView_Lista_glumaca.setItems(glumciItems);
    	
    	// Lista rezisera
    	ObservableList<Osoblje> reziseriItems = FXCollections.observableArrayList();
    	for(Osoblje o: Osoblje.getLista_osoblja()) {
    			if(o.getTip() == 2)
    				reziseriItems.add(o);
    	}	
    	ListView_Lista_rezisera.getItems().clear();
    	ListView_Lista_rezisera.setItems(reziseriItems);
    	
		// Lista autora
    	ObservableList<Osoblje> autoriItems = FXCollections.observableArrayList();
    	for(Osoblje o: Osoblje.getLista_osoblja()) {
    			if(o.getTip() == 3)
    				autoriItems.add(o);
    	}	
    	ListView_Lista_autora.getItems().clear();
    	ListView_Lista_autora.setItems(autoriItems);
    	
	}
}

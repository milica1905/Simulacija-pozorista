package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Kontroler_Radnik_Predstave_Prethodni_period implements Initializable{
	 @FXML
	    private TableColumn<Pomocna_klasa_Spisak_predstava, SimpleStringProperty> brojslMKolona;

	    @FXML
	    private TableColumn<Pomocna_klasa_Spisak_predstava, SimpleStringProperty> cijenaKolona;

	    @FXML
	    private TableColumn<Pomocna_klasa_Spisak_predstava, SimpleStringProperty> predstavaKolona;

	    @FXML
	    private TableView<Pomocna_klasa_Spisak_predstava> tabela;

	    @FXML
	    private TableColumn<Pomocna_klasa_Spisak_predstava, SimpleStringProperty> datumKolona;

	    @FXML
	    private TableColumn<Pomocna_klasa_Spisak_predstava, SimpleStringProperty> zanrKolona;
	
	@FXML
	void switchToNakonPrijaveRadnika(ActionEvent event) {
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
		predstavaKolona.setCellValueFactory(new PropertyValueFactory<Pomocna_klasa_Spisak_predstava, SimpleStringProperty>("predstava"));
		zanrKolona.setCellValueFactory(new PropertyValueFactory<Pomocna_klasa_Spisak_predstava, SimpleStringProperty>("zanr"));
		datumKolona.setCellValueFactory(new PropertyValueFactory<Pomocna_klasa_Spisak_predstava, SimpleStringProperty>("datum"));
		cijenaKolona.setCellValueFactory(new PropertyValueFactory<Pomocna_klasa_Spisak_predstava, SimpleStringProperty>("cijena"));
		brojslMKolona.setCellValueFactory(new PropertyValueFactory<Pomocna_klasa_Spisak_predstava, SimpleStringProperty>("brojslm"));
		
		ObservableList <Pomocna_klasa_Spisak_predstava> dodajUTabelu = FXCollections.observableArrayList();
		ArrayList<Integer> kontrola = new ArrayList<>();
		
		for(Izvodjenje_predstave ip : Izvodjenje_predstave.getLista_izvodjenja_predstave()) {
			String predstava = "";
			String zanr ="";
			String datum = "";
			String cijena = "";
			String brojslm = "";
	
			if(ip.getPozoriste_id() == Kontroler_Nakon_prijave_Radnika.vratiPozoristeID()) 
					if(!Izvodjenje_predstave.daLiSeIzvodiUNarednomPeriodu(ip.getDatum_i_vrijeme())) {
						if(!kontrola.contains(ip.getPredstava_id())) {
							Predstava p = Predstava.vratiPredstavuNaOsnovuID(ip.getPredstava_id());
							kontrola.add(ip.getPredstava_id());
							predstava = p.getNaziv();
							zanr = p.getStringZanr(p.getZanr());
							datum = ip.toString();
							cijena = Double.toString(ip.getCijena());
							
							int brojsjedista = Pozoriste.vratiPozoristeNaOsnovuId(Kontroler_Nakon_prijave_Radnika.vratiPozoristeID()).getBroj_sjedista();
							
							for(Karta k: Karta.getLista_karata()) {
								if(k.getIzvodjenje_predstave() == ip.getId())
									brojsjedista = brojsjedista - 1;
							}
							brojslm = Integer.toString(brojsjedista);
							dodajUTabelu.add(new Pomocna_klasa_Spisak_predstava(predstava,zanr,datum,cijena,brojslm));
					}
						else {
							datum = ip.toString();
							int brojsjedista = Pozoriste.vratiPozoristeNaOsnovuId(Kontroler_Nakon_prijave_Radnika.vratiPozoristeID()).getBroj_sjedista();
							
							for(Karta k: Karta.getLista_karata()) {
								if(k.getIzvodjenje_predstave() == ip.getId())
									brojsjedista = brojsjedista - 1;
							}
							brojslm = Integer.toString(brojsjedista);
							dodajUTabelu.add(new Pomocna_klasa_Spisak_predstava(predstava,zanr,datum,cijena,brojslm));
						}
				}
				
			
		}
		tabela.setItems(dodajUTabelu);
	}

}

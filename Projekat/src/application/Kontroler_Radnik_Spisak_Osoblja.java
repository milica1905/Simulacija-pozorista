package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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

public class Kontroler_Radnik_Spisak_Osoblja implements Initializable{
	@FXML
    private TableColumn<PomocnaKlasa_Radnik_Osoblje, SimpleStringProperty> imeKolona;

    @FXML
    private TableColumn<PomocnaKlasa_Radnik_Osoblje, SimpleStringProperty> predstavaKolona;

    @FXML
    private TableColumn<PomocnaKlasa_Radnik_Osoblje, SimpleStringProperty> prezimeKolona;

    @FXML
    private TableView<PomocnaKlasa_Radnik_Osoblje> tabela;

    @FXML
    private TableColumn<PomocnaKlasa_Radnik_Osoblje, SimpleStringProperty> tipKolona;

    @FXML
    private TableColumn<PomocnaKlasa_Radnik_Osoblje, SimpleStringProperty> zanrKolona;

    @FXML
    private TableColumn<PomocnaKlasa_Radnik_Osoblje, SimpleStringProperty> brizv;
	@FXML
    public void switchToNakonPrijaveRadnika(ActionEvent event) {
    
    	
    	Alert alert0 = new Alert(Alert.AlertType.CONFIRMATION);
	 	alert0.setTitle("IZLAZAK");
	 	alert0.setContentText("Da li ste sigurni da zelite napustite stranicu?");
	 	
	 	Optional <ButtonType>result = alert0.showAndWait();
	 	if(result.get()==ButtonType.OK) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Nakon_prijave_Radnika.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setTitle("Dobrodošli radniče " + Kontroler_Radnik_prijava.imeIPrezimeRadnika());
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
		ArrayList <Osoblje> osoblje= Osoblje.getLista_osoblja();
		
		KomparatorOsoblje komparator = new KomparatorOsoblje();
		Collections.sort(osoblje,komparator);
		
		
		
		tipKolona.setCellValueFactory(new PropertyValueFactory<PomocnaKlasa_Radnik_Osoblje, SimpleStringProperty>("tip"));
		imeKolona.setCellValueFactory(new PropertyValueFactory<PomocnaKlasa_Radnik_Osoblje, SimpleStringProperty>("ime"));
		prezimeKolona.setCellValueFactory(new PropertyValueFactory<PomocnaKlasa_Radnik_Osoblje, SimpleStringProperty>("prezime"));
		predstavaKolona.setCellValueFactory(new PropertyValueFactory<PomocnaKlasa_Radnik_Osoblje, SimpleStringProperty>("predstava"));
		zanrKolona.setCellValueFactory(new PropertyValueFactory<PomocnaKlasa_Radnik_Osoblje, SimpleStringProperty>("zanr"));
		brizv.setCellValueFactory(new PropertyValueFactory<PomocnaKlasa_Radnik_Osoblje, SimpleStringProperty>("brizv"));
		
		ArrayList<Integer> kontrola = new ArrayList<>();
		ObservableList <PomocnaKlasa_Radnik_Osoblje> dodajUTabelu = FXCollections.observableArrayList();

		

	
		for(Osoblje o : osoblje) {
			
			String tip = " ";
			String ime = " ";
			String prezime = " " ;
			String predstava= "";
			String zanr = "";
			String brizv ="";
			
			boolean osobaKontrola = false;
			
			for(Osoblje_predstave op : Osoblje_predstave.getLista_osoblja_predstave()) {
			
					if(op.getOsoblje_id() == o.getId()) {
						osobaKontrola = true;
						if(!kontrola.contains(op.getOsoblje_id())) {
							tip = o.getStringTip(o.getTip());
							ime = o.getIme();
							prezime = o.getPrezime();
							brizv = Integer.toString(Osoblje_predstave.brojPredstavaUKojojGlumi(o));
							kontrola.add(op.getOsoblje_id());
							//kontrolaPr.add(op.getPredstava_ID());
							predstava = Predstava.vratiNazivPredstave(op.getPredstava_id());
							zanr=Predstava.dajNazivZanra(op.getPredstava_id());
							
							dodajUTabelu.add(new PomocnaKlasa_Radnik_Osoblje(tip,ime,prezime,predstava,zanr,brizv));
							
						}
						else {
							 tip = " ";
							 ime = " ";
							 prezime = " " ;
							 brizv ="";
							predstava = Predstava.vratiNazivPredstave(op.getPredstava_id());
							zanr=Predstava.dajNazivZanra(op.getPredstava_id());
							
							dodajUTabelu.add(new PomocnaKlasa_Radnik_Osoblje(tip,ime,prezime,predstava,zanr,brizv));
						}
					}
					
			}
			if(!osobaKontrola) {
				tip = o.getStringTip(o.getTip());
				ime = o.getIme();
				prezime = o.getPrezime();
				brizv = Integer.toString(Osoblje_predstave.brojPredstavaUKojojGlumi(o));
				predstava ="";
				zanr ="";
				dodajUTabelu.add(new PomocnaKlasa_Radnik_Osoblje(tip,ime,prezime,predstava,zanr,brizv));
			}
				
				
					
			
		}
	
		tabela.setItems(dodajUTabelu);
	}
}


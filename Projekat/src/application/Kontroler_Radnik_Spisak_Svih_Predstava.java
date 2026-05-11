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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Kontroler_Radnik_Spisak_Svih_Predstava implements Initializable {

	  @FXML
	  private ListView<String> listaPredstava;
	  
	  @FXML
	  private ListView<String> listaPredstavaUPoz;
	  
	  @FXML
	  private Label labela;
	  
	  @FXML
	  public void switchToNakonPrijaveRadnika(ActionEvent event) {
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
		labela.setText(Pozoriste.vratiPozoristeNaOsnovuId(Kontroler_Nakon_prijave_Radnika.vratiPozoristeID()).getNaziv());
		
		ArrayList<String> pomocnaLista = new ArrayList<>();
		
		ArrayList<String> listaPredstavaUOvomPoz = new ArrayList<>();
		
		for(Predstava p : Predstava.getLista_predstava()) {
			String s ="****************************";
			s = "Naziv predstave " + p.getNaziv() + " zanr: " + p.getStringZanr(p.getZanr()) + "\n";
			String glumci = "Glumci:\n ";
			String reziser = "Reziser:\n";
			String autor ="Autor:\n";
			for(Osoblje_predstave os : Osoblje_predstave.getLista_osoblja_predstave()) {
				if(os.getPredstava_id() == p.getId()) {
					for(Osoblje o : Osoblje.getLista_osoblja()) {
						if(o.getId() == os.getOsoblje_id()) {
							if(o.getStringTip(o.getTip()).equals("glumac")) {
								glumci += o;
								glumci += "\n";
								
							}
							else if(o.getStringTip(o.getTip()).equals("reziser")){
								reziser += o;
								reziser += "\n";
								
							}
							else {
								autor += o;
								autor +="\n";
								
							}
								
						}
						
					}
				
				}
				
			}
			s += glumci + reziser +autor;
			pomocnaLista.add(s);
			ArrayList <Predstava> kontrola = new ArrayList<>();
			for(Izvodjenje_predstave ip : Izvodjenje_predstave.getLista_izvodjenja_predstave()) {
				if(ip.getPozoriste_id() == Kontroler_Nakon_prijave_Radnika.vratiPozoristeID()) {
					if(ip.getPredstava_id() == p.getId()) {
						if(!kontrola.contains(p)) {
							listaPredstavaUOvomPoz.add(s);
						}
						kontrola.add(p);
					}
						
				}
			}
		
		}
		listaPredstava.getItems().addAll(pomocnaLista);
		
		listaPredstavaUPoz.getItems().addAll(listaPredstavaUOvomPoz);
		
	}
	
	

}

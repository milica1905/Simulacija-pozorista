package application;

import javafx.beans.property.SimpleStringProperty;

public class Pomocna_klasa_Spisak_predstava {
//sluzi za Radnik_Predstave_Naredni_period.fxml i za Radnik_Predstave_Prethodni_period.fxml
	
	private SimpleStringProperty predstava,zanr,datum,cijena,brojslm;
	
	public Pomocna_klasa_Spisak_predstava(String predstava, String zanr, String datum,String cijena, String brojslm) {
		this.predstava = new SimpleStringProperty(predstava);
		this.zanr = new SimpleStringProperty(zanr);
		this.datum = new SimpleStringProperty(datum);
		this.cijena = new SimpleStringProperty(cijena);
		this.brojslm = new SimpleStringProperty(brojslm);

	}
	
	public String getPredstava() {
		return predstava.get();
	}

	public String getZanr() {
		return zanr.get();
	}

	public String getDatum() {
		return datum.get();
	}

	public String getCijena() {
		return cijena.get();
	}

	public String getBrojslm() {
		return brojslm.get();
	}
	
}

package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Predstava {
	private int id;
	private String naziv;
	private int zanr;
	
	private static ArrayList<Predstava> lista_predstava = new ArrayList<>();

	public Predstava(int id, String naziv, int zanr) {
		this.id = id;
		this.naziv = naziv;
		this.zanr = zanr;
		lista_predstava.add(this);
	}

	public int getId() {
		return id;
	}

	public String getNaziv() {
		return naziv;
	}

	public int getZanr() {
		return zanr;
	}

	public static ArrayList<Predstava> getLista_predstava() {
		return lista_predstava;
	}
	
	public static boolean daLiPostojiPredstava(String naziv,int zanr) {
		for(Predstava p : Predstava.getLista_predstava()) 
			if(p.getNaziv().equals(naziv) && p.getZanr() == zanr) 
				return true;
			
		return false;
	}
	
	public static Predstava vratiPredstavuNaOsnovuID(int predstava_ID) {
		for(Predstava p : Predstava.getLista_predstava()) {
			if(p.getId() == predstava_ID) 
				return p;
		}
		return null;
	}
	
	public static String vratiNazivPredstave(int predstava_ID) {
		for(Predstava p : Predstava.getLista_predstava()) {
			if(p.getId() == predstava_ID) {
				return p.getNaziv();
			}
		}
		return null;
	}
	
	public static String vratiNaziveSvihPredstavaNaOsnovuID(int predstava_ID) {
		for(Predstava p: Predstava.getLista_predstava()) {
			return p.getNaziv();
		}
		return null;
	}
	
	
	
	public static int vrati_ID_Predstave(String naziv_Predstave, int zanr_Predstave) {
		for(Predstava p : Predstava.getLista_predstava()) 
			if(p.getNaziv().equals(naziv_Predstave) && p.getZanr() == zanr_Predstave)
				return p.getId();
		return 0;
		
	}
	
	public static String dajNazivZanra(int predstava_ID) {
		for(Predstava p : Predstava.getLista_predstava()) {
			if(p.getId() == predstava_ID) {
				return Tipovi.tipZanra(Tipovi.izBrojaUTip(p.getZanr()));
			}
		}
		return null;
	}
	
	public String getStringZanr(int zanr) {
		return Tipovi.tipZanra(Tipovi.izBrojaUTip(zanr));
	}
	
	public static int izStringaUInt(String zanr1) {
		switch(zanr1) {
		case "komedija" : return 1;
		case "farsa" : return 2;
		case "satira" : return 3;
		case "komedija restauracije" : return 4;
		case "tragedija" : return 5;
		case "istorija" : return 6;
		case "mjuzikl" : return 7;
		default : throw new IllegalArgumentException("Greska");
		}
	}
	
	
	public String toString() {
		return "Naziv predstave: " + this.naziv + " \nZanr predstave: " + Tipovi.tipZanra(Tipovi.izBrojaUTip(this.zanr))+"\n";
	}

	public static ArrayList<Predstava> spisakPredstavaPozorista(int id) {
	    ArrayList<Predstava> naziviPredstava = new ArrayList<>();
	    for (Izvodjenje_predstave izvodjenje : Izvodjenje_predstave.getLista_izvodjenja_predstave()) {
	        if (izvodjenje.getPozoriste_id() == id) {
	            Predstava predstava = Predstava.vratiPredstavuNaOsnovuID(izvodjenje.getPredstava_id());
	            if (Izvodjenje_predstave.daLiPostojePredstaveKojeSeIzvodeUNarednomPeriodu(predstava.getId()) && predstava!=null) {
	            	naziviPredstava.add(predstava);
	            }
	        }
	    }
	    return naziviPredstava;
	}
	
	
}

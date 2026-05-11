package application;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Izvodjenje_predstave {
	private int id;
	private int predstava_id;
	private int pozoriste_id;
	private double cijena;
	private Timestamp datum_i_vrijeme;
	
	private static ArrayList<Izvodjenje_predstave> lista_izvodjenja_predstave = new ArrayList<>();

	public Izvodjenje_predstave(int id, int predstava_id, int pozoriste_id, double cijena, Timestamp datum_i_vrijeme) {
		this.id = id;
		this.predstava_id = predstava_id;
		this.pozoriste_id = pozoriste_id;
		this.cijena = cijena;
		this.datum_i_vrijeme = datum_i_vrijeme;
		lista_izvodjenja_predstave.add(this);
	}

	public int getId() {
		return id;
	}

	public int getPredstava_id() {
		return predstava_id;
	}

	public int getPozoriste_id() {
		return pozoriste_id;
	}

	public double getCijena() {
		return cijena;
	}

	public Timestamp getDatum_i_vrijeme() {
		return datum_i_vrijeme;
	}
	
	public String datumUString(Timestamp ts) {
		return ts.toString();
	}

	public static ArrayList<Izvodjenje_predstave> getLista_izvodjenja_predstave() {
		return lista_izvodjenja_predstave;
	}
	
	public static boolean daLiJeMoguceOtkazatiRez(Timestamp datumPredstave) {
		// ako se danasnji datum nalazi 2 dana prije predstave mogice je otkazati rez;
		Timestamp datumSad = new Timestamp(System.currentTimeMillis());
		Timestamp prije2dana = new Timestamp(datumPredstave.getTime() - 48*3600*1000);//2 dana prije predstave
		
		if(prije2dana.after(datumSad) || prije2dana.equals(datumSad)) 
			return true;
		
		return false;
	}
	
	public static boolean daLiSeIzvodiUNarednomPeriodu(Timestamp datumPredstave) {
		Timestamp datumSad = new Timestamp(System.currentTimeMillis());
		if(datumPredstave.after(datumSad))
			return true;
		return false;
	}
	
	public String toStringDatum() {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		String string = df.format(this.datum_i_vrijeme);
		//vraca samo ispisan datum u stringu!
		return string;
	}

	public static Izvodjenje_predstave vratiIzvodjenjeNaOsnovuID(int izvodjenjeID) {
		for(Izvodjenje_predstave ip : lista_izvodjenja_predstave) {
			if(ip.getId() == izvodjenjeID)
				return ip;
		}
		return null;
	}
	
	public static boolean daLiPostojiIzvodjenje(int predstavaID,int pozoristeID,double cijena1,Timestamp datumivrijeme) {
		boolean uslov = false;
		for(Izvodjenje_predstave ip : Izvodjenje_predstave.getLista_izvodjenja_predstave()) {
		 if(ip.getPredstava_id() == predstavaID && ip.getPozoriste_id() == pozoristeID && ip.getCijena()==cijena1 && ip.getDatum_i_vrijeme().equals(datumivrijeme)) {
			 uslov=true;
			 break;
		 }
		}
		return uslov;
	}
	
	@Override
	public String toString() {
		return "\nDatum i vrijeme: " + this.toStringDatum();
	}

	public static boolean daLiPostojePredstaveKojeSeIzvodeUNarednomPeriodu(int pozID) {
		ArrayList<Predstava> pomocnaListaPredstava = new ArrayList<>();
		
		for(Izvodjenje_predstave ip : Izvodjenje_predstave.getLista_izvodjenja_predstave()) {
			if(ip.getPozoriste_id() == pozID) {
				
				if(Izvodjenje_predstave.daLiSeIzvodiUNarednomPeriodu(ip.getDatum_i_vrijeme())) {
					if(!pomocnaListaPredstava.contains(Predstava.vratiPredstavuNaOsnovuID(ip.getPredstava_id())))
					pomocnaListaPredstava.add(Predstava.vratiPredstavuNaOsnovuID(ip.getPredstava_id()));
				}
			}
		}
		if(pomocnaListaPredstava.isEmpty())
			return false;
		return true;
	}
	
	public static Izvodjenje_predstave izvodjenje_pozID(int pozID) {
		for(Izvodjenje_predstave ip : Izvodjenje_predstave.getLista_izvodjenja_predstave()) {
			if(ip.getPozoriste_id() == pozID) {
				return ip;
			}
	}
		return null;
	}
	
	
	
	
}

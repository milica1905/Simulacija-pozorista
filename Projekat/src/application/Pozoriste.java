package application;

import java.util.ArrayList;
import java.util.List;

public class Pozoriste {
	private int id;
	private String naziv;
	private String grad;
	private int broj_sjedista;

	private static ArrayList<Pozoriste> lista_pozorista = new ArrayList<>();

	public Pozoriste(int id, String naziv, String grad, int broj_sjedista) {
		this.id = id;
		this.naziv = naziv;
		this.grad = grad;
		this.broj_sjedista = broj_sjedista;
		lista_pozorista.add(this);
	}

	public int getId() {
		return id;
	}

	public String getNaziv() {
		return naziv;
	}

	public String getGrad() {
		return grad;
	}

	public int getBroj_sjedista() {
		return broj_sjedista;
	}

	public static ArrayList<Pozoriste> getLista_pozorista() {
		return lista_pozorista;
	}

	public static Pozoriste vratiPozoristeNaOsnovuId(int pID) {
		for (Pozoriste p : Pozoriste.getLista_pozorista()) {
			if (p.getId() == pID)
				return p;
		}
		return null;
	}

	public static Pozoriste vratiPozoristeNaOsnovuNaziva(String naziv) {
		for (Pozoriste p : Pozoriste.getLista_pozorista()) {
			if (p.getNaziv().equals(naziv))
				return p;
		}
		return null;
	}
	
	public static String pozoristeNaId(int id) {
		for(Pozoriste p : Pozoriste.getLista_pozorista()) {
			if(p.getId() == id)
				return p.toString();	
		}
		return "";
	}

	public static boolean daLiPostojiPozoriste(String naziv, String grad, int br_sjedista) {
		for (Pozoriste p : Pozoriste.getLista_pozorista()) {
			if (p.getNaziv().equals(naziv) && p.getGrad().equals(grad) && p.getBroj_sjedista() == br_sjedista)
				return true;
		}
		return false;
	}

	public String toString() {
		String string = "Ime pozorista: " + this.naziv + "\nGrad: " + this.grad + "\nBroj sjedista: "
				+ this.broj_sjedista + ".\n";
		return string;
	}

	public static ArrayList<String> vratiSveNazivePozorista() {
		ArrayList<String> rez = new ArrayList<>();
		for (Pozoriste p : Pozoriste.getLista_pozorista()) {
			rez.add(p.getNaziv());
		}
		return rez;
	}

	/*
	 * public List<String> spisakPredstavaPozorista() { ArrayList<String> rez = new
	 * ArrayList<>(); for (Izvodjenje_predstave p :
	 * Izvodjenje_predstave.lista_izvodjenja_predstave) { if (p.getPozoriste_id() ==
	 * this.getId() ) { String nazivPredstave =
	 * Predstava.vratiNazivPredstave(p.getPredstava_id()); if
	 * (!rez.contains(nazivPredstave)) { rez.add(nazivPredstave); } } } return rez;
	 * }
	 */

	public static int dajBrojSlobodnihSjedista(Pozoriste p, Izvodjenje_predstave ip) {
		int brojSjedistaUPoz = p.getBroj_sjedista();

		for (Karta k : Karta.getLista_karata()) {
			if (k.getIzvodjenje_predstave() == ip.getId()) {
				brojSjedistaUPoz = brojSjedistaUPoz - 1;
			}
		}
		return brojSjedistaUPoz;
	}
	
	

}



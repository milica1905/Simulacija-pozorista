package application;

import java.util.ArrayList;

public class Osoblje {
	private int id;
	private String ime;
	private String prezime;
	private int tip;
	
	private static ArrayList<Osoblje> lista_osoblja = new ArrayList<>();

	public Osoblje(int id, String ime, String prezime, int tip) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.tip = tip;
		lista_osoblja.add(this);
		
		boolean uslov= false;
		for(Osoblje o : lista_osoblja)
			if(o.getIme().equals(this.ime) && o.getPrezime().equals(this.prezime)) {
				uslov= true;
				break;
			}
		if(!uslov) {
			lista_osoblja.add(this);
		}
	}

	public int getId() {
		return id;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public int getTip() {
		return tip;
	}

	public static ArrayList<Osoblje> getLista_osoblja() {
		return lista_osoblja;
	}
	
	public String toString() {
		return this.prezime + ", " + this.ime;
	}
	
	public static boolean da_Li_Postoji_Osoblje(int tip1, String ime1, String prezime1) {
		for(Osoblje o : Osoblje.getLista_osoblja()) 
			if(o.getTip() == tip1 && ime1.equals(o.getIme()) && prezime1.equals(o.getPrezime()))
				return true;			
		return false;
	}
	
	public static Osoblje vratiNaOsnovuImenaPrezimena(int tip1, String ime1, String prezime1) {
		for(Osoblje o : Osoblje.getLista_osoblja()) {
			if(o.getTip()==tip1 && o.getIme().equals(ime1) && o.getPrezime().equals(prezime1)) {
				return o;
			}
		}
		return null;		
	}
	
	
	public static Osoblje vratiOsobljeNaOsnovuID(int id) {
		for(Osoblje o : Osoblje.getLista_osoblja()) {
			if(o.getId() == id)
				return o;
		}
		return null;
	}
	
	public static int vratiDuzinuListe(int tip1) {
		int n = 0;
		for(Osoblje o : Osoblje.getLista_osoblja())
			if(o.getTip() == tip1)
				n++;
		return n;
	}
	
	public String getStringTip(int tip) {
		return Tipovi.tipOsobe(Tipovi.izBrojaUTip(tip));
	}
	
	public static String ispisInf(Osoblje o) {
		String res = "";
		
		if(o.getTip() == 1)
		{
			res = "Ime: " + o.ime + "\n";
			res += "Prezime: " + o.prezime + "\n";
			res += "\nPredstave u kojima glumi: \n";
			for(Osoblje_predstave op: Osoblje_predstave.getLista_osoblja_predstave())
			{
				if(op.getOsoblje_id() == o.id) {
					res += Predstava.vratiPredstavuNaOsnovuID(op.getPredstava_id()) + "\n";
					}
			}
		}
		
		if(o.getTip() == 2)
		{
			res = "Ime: " + o.ime + "\n";
			res += "Prezime: " + o.prezime + "\n";
			res += "\nPredstave koje je rezirao: \n";
			for(Osoblje_predstave op: Osoblje_predstave.getLista_osoblja_predstave())
			{
				if(op.getOsoblje_id() == o.id) {
					res += Predstava.vratiPredstavuNaOsnovuID(op.getPredstava_id()) + "\n";
					}
			}
		}
		
		if(o.getTip() == 3)
		{
			res = "Ime: " + o.ime + "\n";
			res += "Prezime: " + o.prezime + "\n";
			res += "\nPredstave koje je napravio: \n";
			for(Osoblje_predstave op: Osoblje_predstave.getLista_osoblja_predstave())
			{
				if(op.getOsoblje_id() == o.id) {
					res += Predstava.vratiPredstavuNaOsnovuID(op.getPredstava_id()) + "\n";
					}
			}
		}
		
		return res;
	}
	
}

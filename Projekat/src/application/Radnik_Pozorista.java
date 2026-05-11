package application;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Radnik_Pozorista {
	private int id;
	private String ime;
	private String prezime;
	private String korisnicko_ime;
	private String lozinka;
	private int pozoriste_id;
	
	private static ArrayList<Radnik_Pozorista> lista_radnikaPozorista = new ArrayList<>();

	public Radnik_Pozorista(int id, String ime, String prezime, String korisnicko_ime, String lozinka,
			int pozoriste_id) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnicko_ime = korisnicko_ime;
		this.lozinka = lozinka;
		this.pozoriste_id = pozoriste_id;

		boolean uslov= false;
		for(Radnik_Pozorista p : lista_radnikaPozorista)
			if(p.korisnicko_ime.equals(this.korisnicko_ime)) {
				uslov= true;
				break;
			}
		if(!uslov) {
			lista_radnikaPozorista.add(this);
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

	public String getKorisnicko_ime() {
		return korisnicko_ime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public int getPozoriste_id() {
		return pozoriste_id;
	}

	public static ArrayList<Radnik_Pozorista> getLista_radnikaPozorista() {
		return lista_radnikaPozorista;
	}
	
	
	public static String getMd5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Radnik_Pozorista vratiRadnikaNaOsnovuImena(String korisnicko_radnik) {
		for(Radnik_Pozorista radnici : Radnik_Pozorista.lista_radnikaPozorista) {
			if(radnici.getKorisnicko_ime().equals(korisnicko_radnik))
				return radnici;
		}
		return null;
	}
	
	public static boolean daLiNekoRadiUPozoristu(int id) {
		for(Radnik_Pozorista r : Radnik_Pozorista.getLista_radnikaPozorista()) {
			if(r. getPozoriste_id() == id)
				return true;
		}
		return false;
	}
	public static boolean daLiJeDobroUnesenaSifra(String korisnicko, String lozinka1) {
		for(Radnik_Pozorista r : Radnik_Pozorista.getLista_radnikaPozorista()) {
			if(r.getKorisnicko_ime().equals(korisnicko) && r.getLozinka().equals(lozinka1))
					return true;
		}
		return false;
		
	}
	
	public static int vratiIdTabele(String korisnicko) {
		int pomocniID = 0;
		for(Radnik_Pozorista r :Radnik_Pozorista.getLista_radnikaPozorista())
			if(r.getKorisnicko_ime().equals(korisnicko)) {
				pomocniID=r.getId();
				break;
		}
		return pomocniID;
	}
	
	
	
}

package application;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Posjetilac_pozorista {
	private int id;
	private String ime;
	private String prezime;
	private String korisnicko_ime;
	private String lozinka;

	private static ArrayList<Posjetilac_pozorista> lista_posjetilaca_pozorista = new ArrayList<>();

	public Posjetilac_pozorista(int id, String ime, String prezime, String korisnicko_ime, String lozinka) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnicko_ime = korisnicko_ime;
		this.lozinka = lozinka;
		
		boolean uslov= false;
		for(Posjetilac_pozorista posjet : lista_posjetilaca_pozorista) 
			if(posjet.korisnicko_ime.equals(this.korisnicko_ime)) {
				uslov=true;
				break;
			}
		
		if(!uslov) {
			lista_posjetilaca_pozorista.add(this);
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

	public static ArrayList<Posjetilac_pozorista> getLista_posjetilaca_pozorista() {
		return lista_posjetilaca_pozorista;
	}
	
	public  String toString() {
		return "Posjetilac: " + this.ime + " "  + this.prezime +".\n";
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
	
	public static Posjetilac_pozorista vratiPosjetiocaNaOsnovuStringa(String korisnicko_posjetilac) {
		for(Posjetilac_pozorista p : Posjetilac_pozorista.lista_posjetilaca_pozorista) {
			if(p.getKorisnicko_ime().equals(korisnicko_posjetilac))
				return p;
		}
		return null;
	}
	
	public static int vratiIDPosjetiocaNaOsnovuStringa(String korisnicko_posjetilac) {
		for(Posjetilac_pozorista p : Posjetilac_pozorista.lista_posjetilaca_pozorista) {
			if(p.getKorisnicko_ime().equals(korisnicko_posjetilac))
				return p.getId();
		}
		return -1;
	}
	
	public static Posjetilac_pozorista dajPosjetiocaNaOsnovuIDa(int posjID) {
		for(Posjetilac_pozorista pp : Posjetilac_pozorista.getLista_posjetilaca_pozorista()) {
			if(pp.getId() == posjID) {
				return pp;
			}
		}
		return null;
	}
}

package application;

import java.util.ArrayList;

public class Osoblje_predstave {
	private int id;
	private int osoblje_id;
	private int predstava_id;
	
	private static ArrayList<Osoblje_predstave> lista_osoblja_predstave = new ArrayList<>();

	public Osoblje_predstave(int id, int osoblje_id, int predstava_id) {
		this.id = id;
		this.osoblje_id = osoblje_id;
		this.predstava_id = predstava_id;
		lista_osoblja_predstave.add(this);
	
		boolean uslov= false;
		for(Osoblje_predstave op : lista_osoblja_predstave)
			if(op.getOsoblje_id() == this.osoblje_id && op.getPredstava_id() == this.predstava_id) {
				uslov= true;
				break;
			}
		if(!uslov) {
			lista_osoblja_predstave.add(this);
		}
	
	}

	public int getId() {
		return id;
	}

	public int getOsoblje_id() {
		return osoblje_id;
	}

	public int getPredstava_id() {
		return predstava_id;
	}

	public static ArrayList<Osoblje_predstave> getLista_osoblja_predstave() {
		return lista_osoblja_predstave;
	}
	
	public static int brojPredstavaUKojojGlumi(Osoblje o) {
		int broj = 0;
		for(Osoblje_predstave os : Osoblje_predstave.getLista_osoblja_predstave()) {
			if(os.getOsoblje_id() == o.getId())
				broj++;
		}
		return broj;
	}
	
	/*public static ArrayList<Osoblje_predstave> spisakOsobljaPredstave(int id) {
	    ArrayList<Osoblje_predstave> imenaOsoblja = new ArrayList<>();
	    for (Osoblje_predstave os : Osoblje_predstave.getLista_osoblja_predstave()) {
	        if (os.getPredstava_id() == id) {
	            Osoblje imeOsobe = Osoblje.vratiOsobljeNaOsnovuID(os.getPredstava_id());
	            imenaOsoblja.addAll(imeOsobe);
	            
	        }
	    }
	    return imeOsobe;
	}
	*/
}

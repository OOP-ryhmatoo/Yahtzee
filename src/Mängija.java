
public class Mängija {

	private String mängijaNimi;
	private Skoor skooriTabel;
	private int[] hetkeTäringud = new int[5];
	
	public Mängija(){
		this.mängijaNimi="Nimetu";
		this.skooriTabel=new Skoor();
	}
	
	public Mängija(String nimi){
		this.mängijaNimi=nimi;
		this.skooriTabel=new Skoor();
	}

	public Skoor getSkooriTabel() {
		return skooriTabel;
	}

	public void setSkooriTabel(Skoor skooriTabel) {
		this.skooriTabel = skooriTabel;
	}
	
	@Override
	public String toString(){
		return "Nimi: "+ mängijaNimi + "\n\n" + skooriTabel.toString();
	}
	
}

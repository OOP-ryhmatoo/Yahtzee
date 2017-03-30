import java.util.List;
import java.util.Scanner;

public class Mängija {

	private String mängijaNimi;
	private Skoor skooriTabel;

	public Mängija(){
		this.mängijaNimi="Nimetu";
		this.skooriTabel=new Skoor();
	}

	public Mängija(String nimi){
		this.mängijaNimi=nimi;
		this.skooriTabel=new Skoor();
	}

	public String getMängijaNimi() {
		return mängijaNimi;
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


	// Mängijal on võimalik valida, kuhu tulemus salvestada
	public void salvestaTulemus(List<Täring> tulemus, Scanner scKasutajalt){

		System.out.println("Sinu vooru tulemus on: ");
		for (Täring t: tulemus){
			System.out.print(t.toString());
		}
		// System.out.println("Sinu hetke skoor on: ");
		System.out.println(skooriTabel);
		System.out.println("Vali tühi lahter kuhu oma skoor salvestada!(Sisesta soovitud lahtri nr, nt Maja jaoks, sisesta: 12)");

		while (true) {
			System.out.println("Sisesta vaba lahti number.");
			String kasutajaSisend = scKasutajalt.nextLine();
			try {
				int lahtriNr = Integer.parseInt(kasutajaSisend);
				// vaba lahtri kontroll
				if (skooriTabel.loeSkoor(lahtriNr) != 0) {
					System.out.println("See lahter on juba täidetud");
					continue;
				}
				if (lahtriNr < 1 || lahtriNr > 16){
					System.out.println("See lahter ei sobi");
					continue;
				}
				
				if (lahtriNr < 7){
					skooriTabel.esimenePool(tulemus, lahtriNr);
				}
				else {
					skooriTabel.teinePool(tulemus, lahtriNr);
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("Palun sisesta täisarv.");
			}

		} // while lõpp
		
	} // salvestaTulemus lõpp

}

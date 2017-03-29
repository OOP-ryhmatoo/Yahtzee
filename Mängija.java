import java.util.List;
import java.util.Scanner;

public class Mängija {

	private String mängijaNimi;
	private Skoor skooriTabel;
	//private int[] hetkeTäringud = new int[5]; Seda paistab, et polegi vaja
	
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
	
	public void salvestaTulemus(List<Täring> tulemus, Scanner scKasutajalt){
		
		System.out.println("Sinu vooru tulemus on: ");
		for (Täring t: tulemus){
			System.out.println(t.toString());
		}
		System.out.println("Sinu hetke skoor on: ");
		System.out.println(skooriTabel);
		System.out.println("Vali tühi lahter kuhu oma skoor salvestada!(Sisesta soovitud lahtri nr, nt Maja jaoks, sisesta: 12)");
		String kasutajaSisend = scKasutajalt.nextLine();
		if (Integer.parseInt(kasutajaSisend)<7){
			skooriTabel.esimenePool(tulemus,Integer.parseInt(kasutajaSisend));
		}
		else {
			skooriTabel.teinePool(tulemus,Integer.parseInt(kasutajaSisend));
		}
		System.out.println("Tulemuse peale vooru: ");
		System.out.println(skooriTabel);
		
	}
	
}

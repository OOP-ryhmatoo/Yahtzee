
public class Täring implements Comparable<Täring>{

	private int külgi;
	private int vise;
	// Täringu ekraanil
	private final String täringuSilmad1 = "\n ------- \n|       |\n|   o   |\n|       |\n ------- \t";
	private final String täringuSilmad2 = "\n ------- \n|    o  |\n|       |\n|  o    |\n ------- \t";
	private final String täringuSilmad3 = "\n ------- \n|    o  |\n|   o   |\n|  o    |\n ------- \t";
	private final String täringuSilmad4 = "\n ------- \n|  o o  |\n|       |\n|  o o  |\n ------- \t";
	private final String täringuSilmad5 = "\n ------- \n|  o o  |\n|   o   |\n|  o o  |\n ------- \t";
	private final String täringuSilmad6 = "\n ------- \n|  o o  |\n|  o o  |\n|  o o  |\n ------- \t";


	public Täring(){		//Tavalise, 6-tahulise täringu konstruktor
		this.külgi=6;
	}
	// Seda ei peaks vaja minema
/*	public Täring(int n){ 	//N-tahulise täringu konstruktor
		this.külgi=n;
	}*/

	public int getVise() {
		return vise;
	}

	public void veereta(){	//Täringu veeretamine
		vise= (int)(Math.random()*külgi +1);

		/*if (vise==1){
			System.out.print(täringuSilmad1);
		}
		if (vise==2){
			System.out.print(täringuSilmad2);
		}
		if (vise==3){
			System.out.print(täringuSilmad3);
		}
		if (vise==4){
			System.out.print(täringuSilmad4);
		}
		if (vise==5){
			System.out.print(täringuSilmad5);
		}
		if (vise==6){
			System.out.print(täringuSilmad6);
		}*/
	}

	@Override
	public int compareTo(Täring võrreldav) {
		if (vise < võrreldav.vise)
			return 1; // negatiivne arv näitab, et this on väiksem kui võrreldav, eespool väiksemad täringud
		if (vise > võrreldav.vise)
			return -1; // positiivne arv näitab, et this on suurem kui võrreldav, tagapool suuremad täringud
		return 0; // null tähendab, et mõlemad on võrdsed
	}

	@Override
	public String toString() {
		if (vise==1){
			return täringuSilmad1;
		}
		if (vise==2){
			return täringuSilmad2;
		}
		if (vise==3){
			return täringuSilmad3;
		}
		if (vise==4){
			return täringuSilmad4;
		}
		if (vise==5){
			return täringuSilmad5;
		}
		else{ 
			return täringuSilmad6;
		}
	}

}

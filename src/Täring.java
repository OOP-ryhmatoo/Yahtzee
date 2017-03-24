
public class Täring {

	private int külgi;
	
	// Täringu ekraanil
	private final String täringuSilmad1 = "\n ------- \n|       |\n|       |\n|   o   |\n|       |\n|       |\n ------- \t";
	private final String täringuSilmad2 = "\n ------- \n|       |\n|    o  |\n|       |\n|  o    |\n|       |\n ------- \t";
	private final String täringuSilmad3 = "\n ------- \n|       |\n|    o  |\n|   o   |\n|  o    |\n|       |\n ------- \t";
	private final String täringuSilmad4 = "\n ------- \n|       |\n|  o o  |\n|       |\n|  o o  |\n|       |\n ------- \t";
	private final String täringuSilmad5 = "\n ------- \n|       |\n|  o o  |\n|   o   |\n|  o o  |\n|       |\n ------- \t";
	private final String täringuSilmad6 = "\n ------- \n|       |\n|  o o  |\n|  o o  |\n|  o o  |\n|       |\n ------- \t";
    
	public Täring(){		//Tavalise, 6-tahulise täringu konstruktor
		this.külgi=6;
	}
	public Täring(int n){ 	//N-tahulise täringu konstruktor
		this.külgi=n;
	}
	
	public int veereta(){	//Täringu veeretamine
		return (int)(Math.random()*külgi +1);
	}
}

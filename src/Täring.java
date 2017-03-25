
public class Täring {

	private int külgi;
	private int vise;
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

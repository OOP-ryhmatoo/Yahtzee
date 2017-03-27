/*
 *  Yahtzee mäng
 * 
 */

import java.util.List;
import java.util.Scanner;

public class PeaKlass {

	public static void main(String[] args) {
		
		Yahtzee yatzyMäng = new Yahtzee();
		// Muutuja kasutaja sisendite jaoks
		Scanner scKasutajalt =  new Scanner(System.in);
		// 1-4 mängijat.
		int mängijateArv = 0;
		String kasutajaSisend;
		boolean mängLõppenud = false;
		Tops tops = new Tops();
		
		System.out.println("Kas soovid mängujuhendit näha? (Y/N)");
		kasutajaSisend = scKasutajalt.nextLine();
		
		// Mängujuhendi näitamise valik
		if (kasutajaSisend.toUpperCase().equals("Y")) {
			System.out.println("JUHEND");
			yatzyMäng.näitaJuhendit();			
		}

		// Mängijate arvu määramine
		while (mängijateArv < 1 || mängijateArv > 4) {
			System.out.println("Sisesta mängijate arv (1-4)");
			kasutajaSisend = scKasutajalt.nextLine();
			try {
				mängijateArv = Integer.parseInt(kasutajaSisend);
			} catch (NumberFormatException e) {
				System.out.println("Palun sisesta täisarv.");
			}			
		}
		
		// Mängijate lisamine
		System.out.println("Palun sisesta mängijate nimed.");
		for (int i = 1; i <= mängijateArv; i++) {
			System.out.println("Mängija nr. " + i);
			kasutajaSisend = scKasutajalt.nextLine();			
			yatzyMäng.lisaMängija(new Mängija(kasutajaSisend));
		}

		List<Mängija> mängijad = yatzyMäng.getMängijad();
		
		
		// Mängutsükkel, iga mängija teeb 12 käiku
		while (!mängLõppenud) {
			
			for (Mängija mängija : mängijad) {
				System.out.println(mängija);
				
				// Igal mängijal on võimalik kolm korda täringuid veeretada. 
<<<<<<< HEAD
				//for (int j = 1; j <= 3; j++) {
					System.out.println("Veeretamisvoor. " + 1);
						tops.viskering();	
		
					// Muud Täringute veeretamise valik. Mõte praegu võtta sõne, teha split ja set numbritest 1-5.
					// Kõiki muid sisendeid võiks ignoreerida.
					
					System.out.println("Vali uuesti veeretatavad täringud (Näiteks täringute 1 ja 4 uuesti veeretamiseks: 14)");
					kasutajaSisend = scKasutajalt.nextLine();
					String [] valik=kasutajaSisend.split("");
					
					if (kasutajaSisend ==""){
						break;
					}
					System.out.println("Veeretamisvoor. " + 2);
					tops.viskering(valik);
					
					System.out.println("Vali uuesti veeretatavad täringud (Näiteks täringute 1 ja 4 uuesti veeretamiseks: 14)");
					kasutajaSisend = scKasutajalt.nextLine();
					valik=kasutajaSisend.split("");
					
					if (kasutajaSisend ==""){
						break;
					}
					System.out.println("Veeretamisvoor. " + 3);
					tops.viskering(valik);
					System.out.println("Voor läbi");
				//}
			}
=======
				for (int j = 1; j < 4; j++) {
					if (j==1){
					System.out.println("Veeretamisvoor. " + j);
						tops.viskering();
					}
					else{
						
						// Muud Täringute veeretamise valik. Mõte praegu võtta sõne, teha split ja set numbritest 1-5.
						// Kõiki muid sisendeid võiks ignoreerida.
						
						System.out.println("Vali uuesti veeretatavad täringud (Näiteks täringute 1 ja 4 uuesti veeretamiseks: 14)"
								+ "\nKui uuesti veeretada ei taha, vajuta midagi sisestamata Enter.");
						kasutajaSisend = scKasutajalt.nextLine();
						String [] valik=kasutajaSisend.split("");
						
						if (kasutajaSisend ==""){
							break;
						}
						System.out.println("Veeretamisvoor. " + 2);
						tops.viskering(valik);
						
						System.out.println("Vali uuesti veeretatavad täringud (Näiteks täringute 1 ja 4 uuesti veeretamiseks: 14)"
								+ "\nKui uuesti veeretada ei taha, vajuta midagi sisestamata Enter.");
						kasutajaSisend = scKasutajalt.nextLine();
						valik=kasutajaSisend.split("");
						
						if (kasutajaSisend ==""){
							break;
						}
						System.out.println("Veeretamisvoor. " + 3);
						tops.viskering(valik);
						System.out.println("Voor läbi");
					}
				}
				
				
				//Skoori salvestamine
				mängija.salvestaTulemus(tops.getTäringud(), scKasutajalt);
				
				
>>>>>>> refs/remotes/origin/master
			mängLõppenud = true;
			
		}


		scKasutajalt.close();
		
	}

}
}

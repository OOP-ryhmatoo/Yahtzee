/*
 *  Yahtzee mäng
 * 
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
		int ring = 1;
		// Uuesti on võimalik veeretada täriguid 1,2,3,4,5. 
		Set<String> valikud = new HashSet<String>(Arrays.asList("1", "2", "3", "4", "5"));
		// Mängija valib täringud, mida uuesti veeretada
		Set<String> täringuteValik = new HashSet<>();

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
		while (ring<=12) {

			for (Mängija mängija : mängijad) {
				System.out.println(mängija + " kord.");

				// Igal mängijal on võimalik kolm korda täringuid veeretada.
				for (int j = 1; j < 4; j++) {

					System.out.println("Veeretamisvoor. " + j);

					// Esimeses voorus veeretatakse kõiki täringuid
					if (j==1) {
						tops.viskering();
					}
					else {

						// Täringute uuesti veeretamise valik.
						// Küsib kasutajalt sõne ja lubatud väärtusi otsides moodustab hulga (set) numbritest 1-5.
						// Tühik lubab mänguringi lõpetada (tsüklist väljuda) 

						System.out.println("Vali uuesti veeretatavad täringud (Näiteks täringute 1 ja 4 uuesti veeretamiseks: 14)"
								+ "\nKui uuesti veeretada ei taha, vajuta midagi sisestamata Enter.");

						kasutajaSisend = scKasutajalt.nextLine();

						// Kasutaja ei taha veeretada
						if (kasutajaSisend ==""){
							break;
						}
						// kasutaja sisendist otsitakse Stringe 1-5
						for (String s : valikud) {
							if (kasutajaSisend.contains(s))
								täringuteValik.add(s);						
						}

						// Topsis olevad täringud
						tops.viskering(täringuteValik);						

						System.out.println("Voor " + j + " sai läbi");

						// valikud puhtaks
						täringuteValik.clear();
					} // else lõpp

				} // for lõpp


				//Skoori salvestamine
				//
				mängija.salvestaTulemus(tops.getTäringud(), scKasutajalt);


				

			}
			ring+=1;

			

		}
		scKasutajalt.close();
	}
}

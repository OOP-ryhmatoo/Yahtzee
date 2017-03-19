/*
 *  Yahtzee mäng
 * 
 */

import java.util.Scanner;

public class PeaKlass {

	public static void main(String[] args) {
		
		Yahtzee yatzyMäng = new Yahtzee();
		
		Scanner sc =  new Scanner(System.in);
		
		System.out.println("Kas soovid mängujuhendit näha? (Y/N)");
		
		String kasutajaSisend = sc.nextLine();
		
		if (kasutajaSisend.toUpperCase().equals("Y")) {
			// Näitab juhendit
			System.out.println("JUHEND");
			yatzyMäng.näitaJuhendit();
			
		}


		sc.close();
	}

}

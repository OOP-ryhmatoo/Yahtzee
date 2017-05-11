package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Yahtzee {

	private final String juhendiFail = "Mängujuhend.txt";
	private List<Mängija> mängijad = new ArrayList<>();


	// Tagastab mängijate koopia, et mängijate nimekirja ei saaks tagastatud listi kaudu muuta
	public List<Mängija> getMängijad() {
		List<Mängija> mängijateKoopia = new ArrayList<>();
		for (Mängija m : this.mängijad) {
			mängijateKoopia.add(m);
		}
		return mängijateKoopia;
	}


	public void lisaMängija(Mängija mängija) {
		mängijad.add(mängija);
	}


	// Mängujuhendi näitamine
	public String näitaJuhendit() throws FileNotFoundException {
		String juhend = "";
		File fail = new File(juhendiFail);
		try(Scanner sc = new Scanner(fail)) {
			
			juhend = loeFailist(sc);

		} catch (FileNotFoundException e) {
		 throw new FileNotFoundException();
		} 		

		System.out.println(juhend);
		return juhend;

	}

	// Juhendi lugemine failist
	private String loeFailist(Scanner sc) {
		StringBuilder text = new StringBuilder();
		while (sc.hasNextLine()) {
			text.append(sc.nextLine());
			text.append("\n");
		}
		return text.toString();
	}

}

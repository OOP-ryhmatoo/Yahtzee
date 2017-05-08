package application;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Skoor {


	private int[] skoorid;
	private String[] skoorSisestatud;

	public void lisaSkoor(int lahter, int tulemus) {
		this.skoorid[lahter] = tulemus;
		this.skoorSisestatud[lahter] = "*";
	}

	public int loeSkoor(int lahter) {
		return this.skoorid[lahter];
	}

	public String loeSkooriKontroll(int lahter) {
		return this.skoorSisestatud[lahter];
	}

	public Skoor(){
		this.skoorid=new int[17];
		this.skoorSisestatud = new String[17];
		Arrays.fill(skoorSisestatud, "");
	}


	public void esimenePool(List<Täring> tulemus,int sisend){

		System.out.println("Esimene tabelipool " + sisend);
		int täringuteSumma=0;

		for (Täring täring : tulemus){
			if (täring.getVise() == sisend){
				täringuteSumma += sisend;
			} //sisemise if lõpp
		} // for lõpp
		lisaSkoor(sisend-1, täringuteSumma);

		// Praegu arvutab iga kord poolsumma ja boonuse. Tulevikus peaks midagi optimaalsemat välja mõtlema
		// Boonuse arvutamine
		int esimesePooleSumma = 0;
		for (int i = 0; i < 6; i++) {
			esimesePooleSumma += loeSkoor(i);
		}
		lisaSkoor(6, esimesePooleSumma);

		if (esimesePooleSumma >= 63){
			lisaSkoor(7, 50);
		}
		

		lisaSkoor(16, lõppTulemus());
	} // meetodi lõpp


	public void teinePool(List<Täring> tulemus,int sisend){

		// Abiks skooride kontrollimisel, Võti täringusilmad, väärtus mitu täringut selle tulemusega on
		Map<Integer, Integer> erinevadTäringud = new HashMap<>();
		for (Täring t : tulemus) {
			int silmad = t.getVise();
			if (!erinevadTäringud.containsKey(silmad)) {
				erinevadTäringud.put(silmad, 1);
				System.out.println("Lisan uue täringu " + silmad );
			}
			else {
				erinevadTäringud.put(silmad, erinevadTäringud.get(silmad) + 1);
			}
		}


		System.out.println("Teine tabelipool " + sisend);
		int täringuteSumma=0;
		tulemus.sort(null);


		// üks paar
		if (sisend == 9) {
			for (int i = 6; i > 0; i--) {
				if (erinevadTäringud.containsKey(i) && erinevadTäringud.get(i) >= 2) {
					täringuteSumma = i * 2;
					break;
				}				
			}
		}

		// Kaks paari TODO
		// Erinevate silmade arvuga saab olla 2 või 3 täringut. Paarid peavad olema eri kõrgusega.
		if (sisend == 10) {
			if (erinevadTäringud.size() == 2 || erinevadTäringud.size() == 3) {
				for (int i = 6; i > 0; i--) {
					if (erinevadTäringud.containsKey(i) && erinevadTäringud.get(i) >= 2) {
						täringuteSumma += i * 2;
					}				
				} // for lõpp
			}
		}


		// Kolmik
		if (sisend == 11) {			
			for (int i = 6; i > 0; i--) {
				if (erinevadTäringud.containsKey(i) && erinevadTäringud.get(i) >= 3) {
					täringuteSumma = i * 3;
					break;
				}				
			}
		}

		// Väike rida
		if (sisend == 12){
			if (erinevadTäringud.size() == 5 && !erinevadTäringud.containsKey(6))
				täringuteSumma = 1 + 2 + 3 + 4 + 5;
		}

		// Suur rida
		if (sisend == 13){
			if (erinevadTäringud.size() == 5 && !erinevadTäringud.containsKey(1))
				täringuteSumma = 2 + 3 + 4 + 5 + 6;
		}

		// Maja TODO ?? kontrollida
		// Erinevaid täringuväärtusi saab olla 2 - ühte peab olema 3 ja teist 2 tk
		if (sisend == 14){
			if (erinevadTäringud.size() == 2) {
				for (int i = 6; i > 0; i--) {
					if (erinevadTäringud.containsKey(i) && erinevadTäringud.get(i) == 3) {
						täringuteSumma += i * 3;
						break;
					}
					
				}
				for (int i = 6; i > 0; i--) {
					if (erinevadTäringud.containsKey(i) && erinevadTäringud.get(i) == 2) {
						täringuteSumma += (i * 2);
						break;
					}				
				}
				
				for (int key : erinevadTäringud.keySet()) {
					// Maja tingimus pole täidetud
					if (erinevadTäringud.get(key) > 3 || erinevadTäringud.get(key) < 1) {
						täringuteSumma = 0;
						break;
					}
				} // for lõpp
			}
		}

		// Chance
		if (sisend == 15){
			for (Täring täring : tulemus){
				täringuteSumma += täring.getVise();
			}
		}

		// Yahtzee
		if (sisend == 16){
			if (erinevadTäringud.size() == 1) {
				täringuteSumma = 50;
			}
		} 

		lisaSkoor(sisend-1, täringuteSumma);
		
		
		lisaSkoor(16, lõppTulemus());
	}

	private int lõppTulemus() {
		int summa = 0;
		for (int i = 0; i < skoorid.length - 1; i++) {
			summa += skoorid[i];
		}
		return summa - skoorid[6];
	}

	@Override
	public String toString(){
		return  "\n1.Ühed: \t" + skoorid[0] +
				"\n2.Kahed: \t" + skoorid[1]+
				"\n3.Kolmed: \t" + skoorid[2]+
				"\n4.Neljad: \t"+skoorid[3]+
				"\n5.Viied: \t"+skoorid[4]+
				"\n6.Kuued: \t"+skoorid[5]+
				"\nPoolsumma: \t"+skoorid[6]+
				"\nBoonus: \t"+skoorid[7]+
				"\n9.Üks Paar: \t"+skoorid[8]+
				"\n10.Kaks Paari: \t"+skoorid[9]+
				"\n11.Kolmikr: \t"+skoorid[10]+
				"\n12.Väike Rida: \t"+skoorid[11]+
				"\n13.Suur Rida: \t"+skoorid[12]+
				"\n14.Maja: \t"+skoorid[13]+
				"\n15.Chance: \t"+skoorid[14] +
				"\n16.Yahtzee: \t"+skoorid[15]+
				"\nLõpptulemus: \t"+skoorid[16];

	}


}
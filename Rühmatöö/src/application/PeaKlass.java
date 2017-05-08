/*
n *  Yahtzee mäng
 *  Autorid:
 *  Karl Hannes Veskus
 *  Mikk Õunmaa
 *  Raul Lehesalu
 * 
 */
package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PeaKlass extends Application {


	@Override
	public void start(Stage peaLava) {

		// Esimsesest rühmatööst - nii palju kui võimalik kasutada.
		Yahtzee yatzyMäng = new Yahtzee();
		Tops tops = new Tops();
		// Muutuja kasutaja sisendite jaoks
		Scanner scKasutajalt =  new Scanner(System.in);
		String kasutajaSisend;
		// Praegu toetab ühte mängijat. Rühmatöö teises pooles võiks lisada mitme mängija toe
		int mängijateArv = 1;
		int mänguVoor = 1;
		// Uuesti on võimalik veeretada täriguid 1,2,3,4,5. 
		Set<String> valikud = new HashSet<String>(Arrays.asList("1", "2", "3", "4", "5"));
		// Mängija valib täringud, mida uuesti veeretada
		Set<String> täringuteValik = new HashSet<>();



		// Graafika

		double kõrgus = 900;
		double laius = 1200;

		Group juur = new Group();
		VBox vBox = new VBox();
		vBox.setSpacing(20);
		juur.getChildren().add(vBox);
		Scene stseen = new Scene(juur, laius, kõrgus, Color.SNOW);

		// Mängujuhendi nupp
		Button abiNupp = new Button();
		abiNupp.setText("Mängujuhend");
		vBox.getChildren().add(abiNupp);

		
		// Mängujuhendi näitamise käsitleja
		abiNupp.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				peaLava.hide();
				Stage juhendiLava = new Stage();
				VBox vBox = new VBox(10);
				TextArea juhend = new TextArea();
				juhend.setEditable(false);
				vBox.getChildren().add(juhend);
				juhend.setText(yatzyMäng.näitaJuhendit());
				
				Button sulgeNupp = new Button("Sulge");
				vBox.getChildren().add(sulgeNupp);

				// Vastamise järel näitab tagasi pealavale
				sulgeNupp.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						peaLava.show();
						juhendiLava.hide();
					}
				});
				
				Scene stseen2 = new Scene(vBox);
				juhendiLava.setScene(stseen2);
				juhendiLava.show();
				
				// Kui nurgast kinni panna, siis läheb mängu juurde tagasi
				juhendiLava.setOnHiding(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						peaLava.show();
					}					
				});	
			}			
		});
		

				


		// Infoala  mängijale tagasiside andmiseks
		TextArea infoAla = new TextArea();
		infoAla.setPrefSize(laius - 1, kõrgus/3);
		infoAla.setEditable(false);		
		vBox.getChildren().add(infoAla);

		// täringud
		GridPane täringuteAla = new GridPane();
		vBox.getChildren().add(täringuteAla);


		List<Täring> täringud = tops.viskering();
		täringud.forEach(t -> System.out.println(t.getVise()));

		// Hetkel Placeholderid näitamiseks. Päriselt peaks siin peale viset õiget täringut näitama
		täringuteAla.add(new GaafilineTäring(täringud.get(0).getVise()).getTäring(), 0, 0);
		täringuteAla.add(new GaafilineTäring(täringud.get(1).getVise()).getTäring(), 1, 0);
		täringuteAla.add(new GaafilineTäring(täringud.get(2).getVise()).getTäring(), 2, 0);
		täringuteAla.add(new GaafilineTäring(täringud.get(3).getVise()).getTäring(), 3, 0);
		täringuteAla.add(new GaafilineTäring(täringud.get(4).getVise()).getTäring(), 4, 0);


		// Täringute hoidmise nupud
		// TODO käsitlejad lisamiseks/ eemaldamiseks täringuteValik.add(s); 	täringuteValik.remove(s)	
		täringuteAla.add(new HoiaNupp(1).getNupp(), 0, 1);
		täringuteAla.add(new HoiaNupp(2).getNupp(), 1, 1);
		täringuteAla.add(new HoiaNupp(3).getNupp(), 2, 1);
		täringuteAla.add(new HoiaNupp(4).getNupp(), 3, 1);
		täringuteAla.add(new HoiaNupp(5).getNupp(), 4, 1);

		
		// Veeretamise nupp
		// TODO Mousevent teha Topsis olevad täringud lähevad veeretamisele
		// tops.viskering(täringuteValik);						

		Button veeretaNupp = new Button();
		veeretaNupp.setText("Veereta");
		vBox.getChildren().add(veeretaNupp);
		
		
		// Kasutaja sisendi ala. Siia siis keyboardi evendid mis skooritabeli reale tulemus salvestada
		TextArea sisendiAla = new TextArea();
		sisendiAla.setPrefSize(laius - 1, kõrgus/7);	
		vBox.getChildren().add(sisendiAla);

		peaLava.setTitle("Yahtzee JavaFX");       
		peaLava.setScene(stseen);
		peaLava.show();
		
		/*
		// Mängijate lisamine
		System.out.println("Palun sisesta mängijate nimed.");
		for (int i = 1; i <= mängijateArv; i++) {
			System.out.println("Mängija nr. " + i);
			kasutajaSisend = scKasutajalt.nextLine();			
			yatzyMäng.lisaMängija(new Mängija(kasutajaSisend));
		}

		List<Mängija> mängijad = yatzyMäng.getMängijad();


		// Mängutsükkel, iga mängija teeb 12 käiku.
		// Mängija saab valida, millisesse lahtrisse tulemus lisada.
		while (mänguVoor <= 12) {

			System.out.println("Mänguvoor " + mänguVoor);

			for (Mängija mängija : mängijad) {
				System.out.println("Mängija " + mängija.getMängijaNimi() + " kord.");

				// Mängijal on võimalik kolm korda täringuid veeretada.
				for (int j = 1; j < 4; j++) {

					System.out.println(mängija.getMängijaNimi() +  " veeretamisvoor " + j);

					// Esimeses voorus veeretatakse kõiki täringuid
					if (j==1) {
						tops.viskering();
					}
					else {

						// Täringute uuesti veeretamise valik.
						// Küsib kasutajalt sõne ja lubatud väärtusi otsides moodustab hulga (set) numbritest 1-5.
						// Enter lubab mänguringi lõpetada (tsüklist väljuda) 

						System.out.println("Vali uuesti veeretatavad täringud (Näiteks täringute 1 ja 4 uuesti veeretamiseks: 14)"
								+ "\nKui uuesti veeretada ei taha, vajuta midagi sisestamata Enter.");

						kasutajaSisend = scKasutajalt.nextLine();

						// Kasutaja ei taha veeretada
						if (kasutajaSisend.equals("")){
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


				// Skoori salvestamine
				// System.out.println("Salvestan tulemuse");
				mängija.salvestaTulemus(tops.getTäringud(), scKasutajalt);
				System.out.println("Näitan tabelit\n" + mängija.getSkooriTabel());

			}
			mänguVoor += 1;		

		}
		scKasutajalt.close();

		for (Mängija mängija : mängijad) {
			System.out.println("Lõpptabel\n" + mängija.getSkooriTabel());
		}*/
	}
	public static void main(String[] args) {
		launch(args);
	}

}

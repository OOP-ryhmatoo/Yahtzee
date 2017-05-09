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
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Queue;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
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


	protected static List<Täring> täringud;
	protected String veeretamisVoor = "";
	protected String mänguRing = "";
	protected Mängija mängija;
	
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

		// Uuesti on võimalik veeretada täriguid 1,2,3,4,5. 
		Set<String> valikud = new HashSet<>(Arrays.asList("1", "2", "3", "4", "5"));
		// Mängija valib täringud, mida uuesti veeretada
		Set<String> täringuteValik = new HashSet<>();
		
		// veeretamisevoorude arvepidaja
		Queue<String> veeretamisVoorud = new LinkedList<>();
		// Mängija üks voor, kolm korda saab veeretada		
		veeretamisVoorud.add("1. veeretamine");
		veeretamisVoorud.add("2. veeretamine");
		veeretamisVoorud.add("3. veeretamine");
		veeretamisVoorud.add("");
		// Mänguvoorude arvepidaja
		Queue<String> mänguRingid = new LinkedList<>();
		mänguRingid.add("1. mänguring");
		mänguRingid.add("2. mänguring");
		mänguRingid.add("3. mänguring");
		mänguRingid.add("4. mänguring");
		mänguRingid.add("");

		yatzyMäng.lisaMängija(new Mängija("Testmängija"));
		
		// Graafika

		double kõrgus = 900;
		double laius = 1200;

		Group juur = new Group();
		VBox vBox = new VBox();
		vBox.setSpacing(20);
		juur.getChildren().add(vBox);
		Scene stseen = new Scene(juur, laius, kõrgus, Color.SNOW);
		
		// Miski enam-vähem talutava skaleerumise peab välja mõtlema
		stseen.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		        System.out.println("Width: " + newSceneWidth);
		    }
		});
		stseen.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		        System.out.println("Height: " + newSceneHeight);
		    }
		});

		
		
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

				// Juhendi sulgemise järel läheb tagasi pealavale
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

		//Algne täringute näitamine
		täringud = tops.viskering();
		täringud.forEach(t -> System.out.println(t.getVise()));
		Node täring1 = new GaafilineTäring(täringud.get(0).getVise()).getTäring();
		Node täring2 = new GaafilineTäring(täringud.get(1).getVise()).getTäring();
		Node täring3 = new GaafilineTäring(täringud.get(2).getVise()).getTäring();
		Node täring4 = new GaafilineTäring(täringud.get(3).getVise()).getTäring();
		Node täring5 = new GaafilineTäring(täringud.get(4).getVise()).getTäring();
		täringuteAla.add(täring1, 0, 0);
		täringuteAla.add(täring2, 1, 0);
		täringuteAla.add(täring3, 2, 0);
		täringuteAla.add(täring4, 3, 0);
		täringuteAla.add(täring5, 4, 0);


		// Täringute hoidmise nupud
		// TODO käsitlejad lisamiseks/ eemaldamiseks täringuteValik.add(s); 	täringuteValik.remove(s)
		Button hoiaNupp1 = new HoiaNupp().getNupp();
		Button hoiaNupp2 = new HoiaNupp().getNupp();
		Button hoiaNupp3 = new HoiaNupp().getNupp();
		Button hoiaNupp4 = new HoiaNupp().getNupp();
		Button hoiaNupp5 = new HoiaNupp().getNupp();

		Button[] hoiaNupud = {hoiaNupp1, hoiaNupp2, hoiaNupp3, hoiaNupp4, hoiaNupp5};

		for (int i = 0; i < hoiaNupud.length; i++) {	
			
			Button h = hoiaNupud[i];
			// Hoidmise nupu ID
			h.setId(String.valueOf(i+1));
			täringuteAla.add(h, i, 1);
			GridPane.setHalignment(h, HPos.CENTER);
			
			// Hoianupu hiirekäsitleja
			h.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					//System.out.println(event.getTarget());
					//System.out.println(event.getSource());
					if (h.getText().equals("Hoia")) {
						h.setStyle("-fx-base: green;");
						h.setText("Veereta");
						täringuteValik.add(h.getId());
						System.out.println("Lisan täringutevalikusse " + h.getId());
					} else {
						h.setStyle("-fx-base: grey;");
						h.setText("Hoia");		
						täringuteValik.remove(h.getId());
						System.out.println("eemaldan täringutevalikust " + h.getId());
					}
				}
			});			
		}
		
		
		// Veeretamise nupp		
		Button veeretaNupp = new Button();
		veeretaNupp.setText("Veereta");
		vBox.getChildren().add(veeretaNupp);
		
		veeretaNupp.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
			public void handle(MouseEvent me) {
		        System.out.println("veeretaNupu handler");
		        System.out.println("Täringutevalik" + täringuteValik);
		        
		        veeretamisVoor = veeretamisVoorud.poll();
		        infoAla.setText(mänguRing + "\n" + veeretamisVoor);
				System.out.println("mänguRing" + mänguRing);
				System.out.println("veeretamisVoor" + veeretamisVoor);
		        täringud = tops.viskering(täringuteValik);	
		        täringuteAla.add(new GaafilineTäring(täringud.get(0).getVise()).getTäring(), 0, 0);
				täringuteAla.add(new GaafilineTäring(täringud.get(1).getVise()).getTäring(), 1, 0);
				täringuteAla.add(new GaafilineTäring(täringud.get(2).getVise()).getTäring(), 2, 0);
				täringuteAla.add(new GaafilineTäring(täringud.get(3).getVise()).getTäring(), 3, 0);
				täringuteAla.add(new GaafilineTäring(täringud.get(4).getVise()).getTäring(), 4, 0);
				
				
				if (veeretamisVoorud.isEmpty()) {
					System.out.println("Uus mänguring");
					mänguRing = mänguRingid.poll();
					veeretamisVoorud.add("1. veeretamine");
					veeretamisVoorud.add("2. veeretamine");
					veeretamisVoorud.add("3. veeretamine");
					veeretamisVoorud.add("");
			        veeretamisVoor = veeretamisVoorud.poll();
					System.out.println("mänguRing" + mänguRing);
			        infoAla.setText(mänguRing + "\n" + veeretamisVoor);
			        // TODO tulemuse salvestamine skooritabelisse
				}
				
				// Mänguringid otsas
				if (mänguRingid.isEmpty()) {
					peaLava.hide();
					Stage mänguLõpp = new Stage();
					VBox vBox2 = new VBox(10);
					TextArea lõpuInfo = new TextArea();
					vBox2.getChildren().add(lõpuInfo);
					lõpuInfo.setEditable(false);
					lõpuInfo.setText("Mäng läbi\n");
					//lõpuInfo.setText("Mäng läbi\n" + mängija.getSkooriTabel());
					
					// TODO faili salvestamine
					Button salvestaNupp = new Button("Salvesta skoor");
					vBox2.getChildren().add(salvestaNupp);
					Scene stseen3 = new Scene(vBox2);
					mänguLõpp.setScene(stseen3);
					mänguLõpp.show();
				}		    
		    }
		});
		
		
		
		// Kasutaja sisendi ala. Siia siis keyboardi evendid 
		// Nime sisestamine, mis skooritabeli reale tulemus salvestada
		TextArea sisendiAla = new TextArea();
		sisendiAla.setPrefSize(laius - 1, kõrgus/7);	
		vBox.getChildren().add(sisendiAla);

		// Esimese vooru info näitamine
		mänguRing = mänguRingid.poll();
        veeretamisVoor = veeretamisVoorud.poll();
        infoAla.setText("Mängu algus\n" + mänguRing + "\n" + veeretamisVoor);

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

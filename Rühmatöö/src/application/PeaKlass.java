/*
n *  Yahtzee mäng
 *  Autorid:
 *  Karl Hannes Veskus
 *  Mikk Õunmaa
 *  Raul Lehesalu
 * 
 */
package application;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Queue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
	public void start(Stage peaLava) throws IOException{

		// Esimsesest rühmatööst - nii palju kui võimalik kasutada.
		Yahtzee yatzyMäng = new Yahtzee();
		Tops tops = new Tops();

		// Mängija valib täringud, mida uuesti veeretada
		Set<String> täringuteValik = new HashSet<>(Arrays.asList("1", "2", "3", "4", "5"));

		// veeretamisevoorude arvepidaja
		// Mängija saab ühes voorus kolm korda veeretada	
		Queue<String> veeretamisVoorud = new LinkedList<>(Arrays.asList("1. veeretamine", 
				"2. veeretamine", "3. veeretamine", ""));
		
		// Mänguvoorude arvepidaja TODO kui testimisest küllalt saab, lisa kõik voorud
		Queue<String> mänguRingid = new LinkedList<>();
		for (int i = 1; i < 14; i++)
			mänguRingid.add( String.valueOf(i) + ". mänguring");
		mänguRingid.add("");


		// Testmangija
		yatzyMäng.lisaMängija(new Mängija("Testmängija"));
		mängija = yatzyMäng.getMängijad().get(0);
		
		
		// Graafika

		double kõrgus = 900;
		double laius = 1300;

		Group juur = new Group();
		VBox vBox = new VBox();
		vBox.setSpacing(20);
		juur.getChildren().add(vBox);
		Scene stseen = new Scene(juur, laius, kõrgus, Color.SNOW);	
		vBox.prefWidthProperty().bind(stseen.widthProperty());


		// Mängujuhendi nupp
		Button abiNupp = new Button();
		abiNupp.setText("Mängujuhend");
		vBox.getChildren().add(abiNupp);
		vBox.setAlignment(Pos.TOP_RIGHT);

		// Mängujuhendi näitamise käsitleja
		abiNupp.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				
				peaLava.hide();
				Stage juhendiLava = new Stage();
				VBox vBox = new VBox(10);				
				TextArea juhend = new TextArea();
				juhend.setEditable(false);
				juhend.setWrapText(true);
				juhend.setPrefHeight(700);
				vBox.getChildren().add(juhend);
				try{
					juhend.setText(yatzyMäng.näitaJuhendit());
				} catch (FileNotFoundException e) {
					juhend.setPrefHeight(50);
					juhend.setText("Juhendit ei leitud, ei saa reegleid näidata.");
				}

				Button sulgeNupp = new Button("Sulge");
				vBox.getChildren().add(sulgeNupp);
				vBox.setAlignment(Pos.CENTER);
				sulgeNupp.prefHeightProperty().bind(vBox.heightProperty().divide(10.0));
				sulgeNupp.prefWidthProperty().bind(vBox.widthProperty().divide(5.0));
				
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
		infoAla.prefHeightProperty().bind(stseen.heightProperty().divide(3.0));
		infoAla.prefWidthProperty().bind(stseen.widthProperty());
		
		// täringud ja hoidmise nupud gridpane sees
		GridPane täringuteAla = new GridPane();
		täringuteAla.setHgap(10);
		täringuteAla.setVgap(10);
		täringuteAla.setPadding(new Insets(10, 10, 10, 10)); 
		vBox.getChildren().add(täringuteAla);

		täringuteAla.prefWidthProperty().bind(stseen.widthProperty());
		täringuteAla.prefHeightProperty().bind(stseen.heightProperty().divide(2.5));

		// Algne täringute näitamine
		täringud = tops.viskering();
		täringud.forEach(t -> System.out.println(t.getVise()));
		Pane täring1 = new GraafilineTäring(täringud.get(0).getVise()).getTäring();
		Pane täring2 = new GraafilineTäring(täringud.get(1).getVise()).getTäring();
		Pane täring3 = new GraafilineTäring(täringud.get(2).getVise()).getTäring();
		Pane täring4 = new GraafilineTäring(täringud.get(3).getVise()).getTäring();
		Pane täring5 = new GraafilineTäring(täringud.get(4).getVise()).getTäring();
		täringuteAla.add(täring1, 0, 0);
		täringuteAla.add(täring2, 1, 0);
		täringuteAla.add(täring3, 2, 0);
		täringuteAla.add(täring4, 3, 0);
		täringuteAla.add(täring5, 4, 0);


		// Täringute hoidmise nupud
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
					if (h.getText().toLowerCase().equals("hoia")) {
						h.setStyle("-fx-base: green; -fx-font: 24 arial;");
						h.setText("Veereta");
						täringuteValik.remove(h.getId());
						System.out.println("Lisan täringutevalikusse " + h.getId());
					} else if (h.getText().toLowerCase().equals("veereta")){
						h.setStyle("-fx-base: lightgrey; -fx-font: 24 arial;");
						h.setText("Hoia");
						täringuteValik.add(h.getId());	
						System.out.println("Eemaldan täringutevalikust " + h.getId());
					}
				}
			});			
		}


		// Veeretamise nupp		
		Button veeretaNupp = new Button();
		veeretaNupp.setText("Veereta");
		veeretaNupp.setStyle("-fx-base: darkgreen; -fx-font: 24 arial;");
		vBox.getChildren().add(veeretaNupp);
		vBox.setAlignment(Pos.BOTTOM_CENTER);
/*		veeretaNupp.prefHeightProperty().bind(vBox.heightProperty().divide(10));
		veeretaNupp.prefWidthProperty().bind(vBox.widthProperty().divide(7));*/
		
		veeretaNupp.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {

				System.out.println("Täringutevalik" + täringuteValik);

				veeretamisVoor = veeretamisVoorud.poll();
				infoAla.setText(mänguRing + "\n" + veeretamisVoor);
				System.out.println("mänguRing " + mänguRing);
				System.out.println("veeretamisVoor " + veeretamisVoor);
				
				täringud = tops.viskering(täringuteValik);
				Pane t1 = new GraafilineTäring(täringud.get(0).getVise()).getTäring();
				Pane t2 = new GraafilineTäring(täringud.get(1).getVise()).getTäring();
				Pane t3 = new GraafilineTäring(täringud.get(2).getVise()).getTäring();
				Pane t4 = new GraafilineTäring(täringud.get(3).getVise()).getTäring();
				Pane t5 = new GraafilineTäring(täringud.get(4).getVise()).getTäring();
				täringuteAla.add(t1, 0, 0);
				täringuteAla.add(t2, 1, 0);
				täringuteAla.add(t3, 2, 0);
				täringuteAla.add(t4, 3, 0);
				täringuteAla.add(t5, 4, 0);

				// Kolmanda veeretamise järel läheb käiku
				if (veeretamisVoorud.isEmpty()) {
					
					// vooru tulemuse salvestamine
					peaLava.hide();
					mängija.salvestaGraafilineTulemus(tops.getTäringud());
					peaLava.show();

					mänguRing = mänguRingid.poll();
					veeretamisVoorud.addAll(Arrays.asList("1. veeretamine", "2. veeretamine", "3. veeretamine", ""));
					veeretamisVoor = veeretamisVoorud.poll();
					System.out.println("Uus mänguRing nr. " + mänguRing);
					infoAla.setText(mänguRing + "\n" + veeretamisVoor);
				}

				// Mängu lõpp
				if (mänguRingid.isEmpty()) {
					peaLava.hide();
					Stage mänguLõpp = new Stage();
					VBox vBox2 = new VBox(30);
					TextArea lõpuInfo = new TextArea();
					vBox2.getChildren().add(lõpuInfo);
					lõpuInfo.setPrefHeight(500);
					lõpuInfo.setEditable(false);

					lõpuInfo.setText("Mäng läbi\n" + mängija.getSkooriTabel());

					Button salvestaNupp = new Button("Salvesta skoor");
					vBox2.getChildren().add(salvestaNupp);

					salvestaNupp.setOnMouseClicked(new EventHandler<MouseEvent>() {
						
						
						@Override
						public void handle(MouseEvent me) {
							
							try {
								//salvestab faili
								mängija.salvestaSkoorFaili("YahtzeeSkoor.txt");
								
							} catch (IOException e1) {
								// kui ei õnnestunud salvestada, siis vastav aken ja sulgub
								Stage failed = new Stage();
								VBox vBox4 =new VBox(10);	
								TextArea  ebaõnnestus= new TextArea();
								ebaõnnestus.setEditable(false);
								vBox4.getChildren().add(ebaõnnestus);
								ebaõnnestus.setText("Sinu skoori ei õnnestunud salvestada teadmata põhjusel, äkki veab teine kord paremini");
								Button failedSulge = new Button("Sulge");
								vBox4.getChildren().add(failedSulge);
								Scene stseen5 = new Scene(vBox4, 200, 100);
								failed.setScene(stseen5);
								failed.show();
								failedSulge.setOnMouseClicked(new EventHandler<MouseEvent>() {
									@Override
									public void handle(MouseEvent me) {
										failed.close();
										mänguLõpp.close();
										peaLava.close();
									}
							});
							} // catch lõpp
							//Teavitab salvestamise õnnestumisest
							mänguLõpp.hide();
							Stage salvestamine = new Stage();
							VBox vBox3 = new VBox(10);	
							TextArea  salvestatud= new TextArea();
							salvestatud.setEditable(false);
							vBox3.getChildren().add(salvestatud);
							salvestatud.setText("Skoor on salvestatud.");
							Button sulgeMäng = new Button("Sulge");
							vBox3.getChildren().add(sulgeMäng);
							Scene stseen4 = new Scene(vBox3, 200, 100);
							salvestamine.setScene(stseen4);
							salvestamine.show();
							//sulgeb programmi
							sulgeMäng.setOnMouseClicked(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent me) {
									salvestamine.close();
									mänguLõpp.close();
									peaLava.close();
								}
							});
			        }});

					Scene stseen3 = new Scene(vBox2, 400, 650);
					mänguLõpp.setScene(stseen3);
					mänguLõpp.show();
				}		    
			}
		});


		// Esimese vooru info näitamine
		mänguRing = mänguRingid.poll();
		veeretamisVoor = veeretamisVoorud.poll();
		infoAla.setText("Mängu algus\n" + mänguRing + "\n" + veeretamisVoor);

		peaLava.setTitle("Yahtzee JavaFX");       
		peaLava.setScene(stseen);
		peaLava.show();

	
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}

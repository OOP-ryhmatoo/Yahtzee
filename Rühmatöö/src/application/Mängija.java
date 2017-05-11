package application;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Mängija {

	private String mängijaNimi;
	private Skoor skooriTabel;
	protected String sisestusInfo = "";

	public Mängija(){
		this.mängijaNimi="Nimetu";
		this.skooriTabel=new Skoor();
	}

	public Mängija(String nimi){
		this.mängijaNimi=nimi;
		this.skooriTabel=new Skoor();
	}

	public String getMängijaNimi() {
		return mängijaNimi;
	}

	public Skoor getSkooriTabel() {
		return skooriTabel;
	}

	public void setSkooriTabel(Skoor skooriTabel) {
		this.skooriTabel = skooriTabel;
	}

	@Override
	public String toString(){
		return "Nimi: "+ mängijaNimi + "\n\n" + skooriTabel.toString();
	}


	// Mängijal on võimalik valida, kuhu tulemus salvestada
	public void salvestaTulemus(List<Täring> tulemus, Scanner scKasutajalt){

		System.out.println("Sinu vooru tulemus on: ");
		for (Täring t: tulemus){
			System.out.print(t.toString());
		}
		// System.out.println("Sinu hetke skoor on: ");
		System.out.println(skooriTabel);
		System.out.println("Vali tühi lahter kuhu oma skoor salvestada!(Sisesta soovitud lahtri nr, nt Maja jaoks, sisesta: 12)");

		while (true) {
			System.out.println("Sisesta vaba lahti number.");
			String kasutajaSisend = scKasutajalt.nextLine();
			try {
				int lahtriNr = Integer.parseInt(kasutajaSisend);
				// vaba lahtri kontroll
				if (skooriTabel.loeSkoor(lahtriNr-1) != 0) {
					System.out.println("See lahter on juba täidetud");
					continue;
				}

				// Sisendi kontrollid
				if (skooriTabel.loeSkooriKontroll(lahtriNr-1).equals("*")) {
					System.out.println("See lahter on juba täidetud");
					continue;
				}

				// Sisendi kontrollid
				if (lahtriNr < 1 || lahtriNr > 16){
					System.out.println("See lahter ei sobi");
					continue;
				}

				if (lahtriNr < 7){
					skooriTabel.esimenePool(tulemus, lahtriNr);
				}
				else {
					skooriTabel.teinePool(tulemus, lahtriNr);
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("Palun sisesta täisarv.");
			}

		} // while lõpp

	} // salvestaTulemus lõpp

	
	
	public void salvestaGraafilineTulemus(List<Täring> tulemus) {
		
		Stage mänguSalvestamine = new Stage();
		
		// Ei lase nurgast X-st kinni panna. Välja saab õige lahtri numbri sisestamise järel
		Platform.setImplicitExit(false);
		mänguSalvestamine.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        event.consume();
		    }
		});
		
		
		VBox vBox = new VBox(10);
		vBox.setSpacing(5);
		TextArea skooritabel = new TextArea();
		vBox.getChildren().add(skooritabel);
		skooritabel.setEditable(false);
		skooritabel.setPrefHeight(470);
		skooritabel.setText("Sinu skooritabel on:\n" + getSkooriTabel().toString());

		TextArea juhendid = new TextArea();
		vBox.getChildren().add(juhendid);
		juhendid.setEditable(false);
		
		sisestusInfo = "";
		sisestusInfo += "Sinu täringud on:\n";
		for (Täring t : tulemus) {
			sisestusInfo += String.valueOf(t.getVise()) + ", ";
		}
		sisestusInfo += "\nSisesta alumisel tekstialal vaba lahti number.\nSisestamiseks vajuta klahvi ENTER";
		
		juhendid.setText(sisestusInfo.toString());
	
		TextArea kasutajaSisend = new TextArea();
		vBox.getChildren().add(kasutajaSisend);
		
		// Kasutaja sisendi tekstiala fokuseerimine wrapitult. Lihtsalt kasutajaSisend.requestFocus(); ei toiminud
		Platform.runLater(new Runnable() {
		     @Override
		     public void run() {
		    	 kasutajaSisend.requestFocus();
		     }
		});
		
		// Kasutaja sisendi klaviatuurilt 
		// Mängijal on võimalik valida, kuhu tulemus salvestada
		kasutajaSisend.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(key.getCode()==KeyCode.ENTER) {
				juhendid.clear();
				
				System.out.println("Kasutaja sisend: " + kasutajaSisend.getText());

				try {
					int lahtriNr = Integer.parseInt(kasutajaSisend.getText().trim());

					// Sisendi kontrollid
					if (lahtriNr < 1 || lahtriNr > 16 || lahtriNr == 7 || lahtriNr == 8){
						juhendid.clear();
						juhendid.setText("See lahter ei sobi\n");
						juhendid.appendText(sisestusInfo);
						kasutajaSisend.clear();
					} else
					// vaba lahtri kontroll
					if (skooriTabel.loeSkoor(lahtriNr-1) != 0) {
						juhendid.clear();						
						juhendid.setText("See lahter on juba täidetud\n");
						juhendid.appendText(sisestusInfo);
						kasutajaSisend.clear();
					} else
					// Sisendi kontrollid
					if (skooriTabel.loeSkooriKontroll(lahtriNr-1).equals("*")) {
						juhendid.clear();
						juhendid.setText("See lahter on juba täidetud\n");
						juhendid.appendText(sisestusInfo);
						kasutajaSisend.clear();
					} else

					if (lahtriNr < 7){
						skooriTabel.esimenePool(tulemus, lahtriNr);
						mänguSalvestamine.hide();
						return;
					}
					else {
						skooriTabel.teinePool(tulemus, lahtriNr);
						mänguSalvestamine.hide();
						return;
					}

				} catch (NumberFormatException e) {
					juhendid.clear();
					juhendid.setText("Palun sisesta täisarv.\n");
					juhendid.appendText(sisestusInfo);
					kasutajaSisend.clear();
				}
			}
		});

		Scene stseen = new Scene(vBox, 400, 650);
		mänguSalvestamine.setScene(stseen);
		mänguSalvestamine.showAndWait();

	}

	public void salvestaSkoorFaili(String failinimi) throws IOException {
		this.skooriTabel.salvestaFaili(failinimi);
	}

}

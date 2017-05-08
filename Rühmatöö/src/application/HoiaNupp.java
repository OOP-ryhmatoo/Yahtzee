package application;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class HoiaNupp {
	
	private Button hoiaNupp;
	private int id;

	public HoiaNupp(int indeks) {
		super();
		this.hoiaNupp = ehitaNupp();
		this.id = indeks;
	}

	private Button ehitaNupp() {
		Button b = new Button();
		b.setText("Hoia");
		return b;
	}

	public Node getNupp() {
		return this.hoiaNupp;
	}
	
	// Selle järgi saab nupu ära tunda, millist täringut hoida
	public int getNupuId(HoiaNupp nupp) {
		return this.id;
		
	}
	
	

}

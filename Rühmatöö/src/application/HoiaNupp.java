package application;

import javafx.scene.control.Button;

public class HoiaNupp  {

	protected Button hoiaNupp;

	public HoiaNupp() {
		super();
		this.hoiaNupp = new Button();
		this.hoiaNupp.setText("Hoia");
	}

	public Button getNupp() {
		return this.hoiaNupp;
	}


}




import javafx.scene.control.Button;

public class HoiaNupp  {

	protected Button hoiaNupp;

	public HoiaNupp() {
		super();
		this.hoiaNupp = new Button();
		this.hoiaNupp.setText("Hoia");
		this.hoiaNupp.setStyle("-fx-base: lightgrey; -fx-font: 24 arial;");
	}

	public Button getNupp() {
		return this.hoiaNupp;
	}


}

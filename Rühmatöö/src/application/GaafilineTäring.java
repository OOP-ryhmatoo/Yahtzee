package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GaafilineTäring extends Täring {
		
	private Pane täring;
	
	public GaafilineTäring(int i) {
		super();
		this.täring = ehitaTäring(i);
	}

	private Pane ehitaTäring(int i) {
		
		Pane täring = new Pane();
		Rectangle täringuRuut = new Rectangle(20,20,200,200);	    
		täringuRuut.setArcHeight(15);
		täringuRuut.setArcWidth(15);
		täringuRuut.setStroke(Color.BLACK);
		täring.getChildren().add(täringuRuut);	
		if (i==0) {
			täringuRuut.setStroke(Color.BLUE);
		}		
		if (i==1) {
			täring.getChildren().add(täringuSilm(120,120,20));
		} 
		else if (i==2) {
			täring.getChildren().add(täringuSilm(55,55,20));
			täring.getChildren().add(täringuSilm(185,185,20));
		} 
		else if (i==3) {
			täring.getChildren().add(täringuSilm(55,55,20));
			täring.getChildren().add(täringuSilm(120,120,20));
			täring.getChildren().add(täringuSilm(185,185,20));
		} 
		else if (i==4) {
			täring.getChildren().add(täringuSilm(55,55,20));
			täring.getChildren().add(täringuSilm(55,185,20));
			täring.getChildren().add(täringuSilm(185,55,20));
			täring.getChildren().add(täringuSilm(185,185,20));
		}
		else if (i==5) {
			täring.getChildren().add(täringuSilm(55,55,20));
			täring.getChildren().add(täringuSilm(55,185,20));
			täring.getChildren().add(täringuSilm(120,120,20));
			täring.getChildren().add(täringuSilm(185,55,20));
			täring.getChildren().add(täringuSilm(185,185,20));
		}
		else if (i==6) {
			täring.getChildren().add(täringuSilm(55,55,20));
			täring.getChildren().add(täringuSilm(55,185,20));
			täring.getChildren().add(täringuSilm(55,120,20));
			täring.getChildren().add(täringuSilm(185,120,20));
			täring.getChildren().add(täringuSilm(185,55,20));
			täring.getChildren().add(täringuSilm(185,185,20));
		}		
		return täring;
	}
	
	private Circle täringuSilm(double r, double x, double y) {
		Circle täringuSilm = new Circle(r, x, y);
		täringuSilm.setFill(Color.WHITE);
		return täringuSilm;		
	}
	
	public Pane getTäring() {
			return täring;		
	}

}

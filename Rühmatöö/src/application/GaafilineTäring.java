package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GraafilineTäring extends Täring {
	
	int külg=200;
	private Pane täring;
	
	public GraafilineTäring(int i) {
		super();
		this.täring = ehitaTäring(i);
	}

	private Pane ehitaTäring(int i) {
		
		Pane täring = new Pane();
		Rectangle täringuRuut = new Rectangle(15,15,200,200);
		

		täringuRuut.setArcHeight(15);
		täringuRuut.setArcWidth(15);
		täringuRuut.setStroke(Color.BLACK);
		täring.getChildren().add(täringuRuut);	
		if (i==0) {
			täringuRuut.setStroke(Color.BLUE);
		}		
		if (i==1) {
			Circle s1=täringuSilm(120,120,20);
			s1.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s1.centerXProperty().bind(täring.widthProperty().divide(2));
			s1.centerYProperty().bind(s1.centerXProperty());
			täring.getChildren().add(s1);			
		} 
		else if (i==2) {
			Circle s1=täringuSilm(55,55,20);
			s1.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s1.centerXProperty().bind(täring.widthProperty().multiply(0.275));
			s1.centerYProperty().bind(s1.centerXProperty());
			täring.getChildren().add(s1);	
			
			Circle s2=täringuSilm(185,185,20);
			s2.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s2.centerXProperty().bind(täring.widthProperty().multiply(0.725));
			s2.centerYProperty().bind(s2.centerXProperty());
			täring.getChildren().add(s2);	
			
		} 
		else if (i==3) {
			Circle s1=täringuSilm(55,55,20);
			s1.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s1.centerXProperty().bind(täring.widthProperty().multiply(0.275));
			s1.centerYProperty().bind(s1.centerXProperty());
			täring.getChildren().add(s1);	
			
			Circle s2=täringuSilm(185,185,20);
			s2.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s2.centerXProperty().bind(täring.widthProperty().multiply(0.725));
			s2.centerYProperty().bind(s2.centerXProperty());
			täring.getChildren().add(s2);	
			
			Circle s3=täringuSilm(120,120,20);
			s3.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s3.centerXProperty().bind(täring.widthProperty().divide(2));
			s3.centerYProperty().bind(s3.centerXProperty());
			täring.getChildren().add(s3);	
		} 
		else if (i==4) {
			Circle s1=täringuSilm(55,55,20);
			s1.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s1.centerXProperty().bind(täring.widthProperty().multiply(0.275));
			s1.centerYProperty().bind(s1.centerXProperty());
			täring.getChildren().add(s1);	
			
			Circle s2=täringuSilm(185,185,20);
			s2.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s2.centerXProperty().bind(täring.widthProperty().multiply(0.725));
			s2.centerYProperty().bind(s2.centerXProperty());
			täring.getChildren().add(s2);	
			
			Circle s3=täringuSilm(55,185,20);
			s3.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s3.centerXProperty().bind(täring.widthProperty().multiply(0.275));
			s3.centerYProperty().bind(täring.widthProperty().multiply(0.725));
			täring.getChildren().add(s3);	
			
			Circle s4=täringuSilm(55,185,20);
			s4.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s4.centerXProperty().bind(täring.widthProperty().multiply(0.725));
			s4.centerYProperty().bind(täring.widthProperty().multiply(0.275));
			täring.getChildren().add(s4);	
			
		}
		else if (i==5) {
			Circle s1=täringuSilm(55,55,20);
			s1.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s1.centerXProperty().bind(täring.widthProperty().multiply(0.275));
			s1.centerYProperty().bind(s1.centerXProperty());
			täring.getChildren().add(s1);	
			
			Circle s2=täringuSilm(185,185,20);
			s2.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s2.centerXProperty().bind(täring.widthProperty().multiply(0.725));
			s2.centerYProperty().bind(s2.centerXProperty());
			täring.getChildren().add(s2);	
			
			Circle s3=täringuSilm(55,185,20);
			s3.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s3.centerXProperty().bind(täring.widthProperty().multiply(0.275));
			s3.centerYProperty().bind(täring.widthProperty().multiply(0.725));
			täring.getChildren().add(s3);	
			
			Circle s4=täringuSilm(185,55,20);
			s4.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s4.centerXProperty().bind(täring.widthProperty().multiply(0.725));
			s4.centerYProperty().bind(täring.widthProperty().multiply(0.275));
			täring.getChildren().add(s4);	
			
			Circle s5=täringuSilm(120,120,20);
			s5.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s5.centerXProperty().bind(täring.widthProperty().divide(2));
			s5.centerYProperty().bind(s5.centerXProperty());
			täring.getChildren().add(s5);
		}
		else if (i==6) {
			Circle s1=täringuSilm(55,55,20);
			s1.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s1.centerXProperty().bind(täring.widthProperty().multiply(0.275));
			s1.centerYProperty().bind(s1.centerXProperty());
			täring.getChildren().add(s1);	
			
			Circle s2=täringuSilm(185,185,20);
			s2.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s2.centerXProperty().bind(täring.widthProperty().multiply(0.725));
			s2.centerYProperty().bind(s2.centerXProperty());
			täring.getChildren().add(s2);	
			
			Circle s3=täringuSilm(55,185,20);
			s3.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s3.centerXProperty().bind(täring.widthProperty().multiply(0.275));
			s3.centerYProperty().bind(täring.widthProperty().multiply(0.725));
			täring.getChildren().add(s3);	
			
			Circle s4=täringuSilm(185,55,20);
			s4.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s4.centerXProperty().bind(täring.widthProperty().multiply(0.725));
			s4.centerYProperty().bind(täring.widthProperty().multiply(0.275));
			täring.getChildren().add(s4);	
			
			Circle s5=täringuSilm(185,100,20);
			s5.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s5.centerXProperty().bind(täring.widthProperty().multiply(0.725));
			s5.centerYProperty().bind(täring.widthProperty().multiply(0.5));
			täring.getChildren().add(s5);	
			
			Circle s6=täringuSilm(55,100,20);
			s6.radiusProperty().bind(täringuRuut.heightProperty().divide(10));
			s6.centerXProperty().bind(täring.widthProperty().multiply(0.275));
			s6.centerYProperty().bind(täring.widthProperty().multiply(0.5));
			täring.getChildren().add(s6);	
		}		
		return täring;
	}
	
	
	
	
	private Circle täringuSilm(double x, double y, double r) {
		Circle täringuSilm = new Circle(x, y, r);
		täringuSilm.setFill(Color.WHITE);
		return täringuSilm;		
	}
	
	public Pane getTäring() {
			return täring;		
	}

}

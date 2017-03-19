
public class Skoor {
	
	
	private int[] skoorid;
	
	public Skoor(int [] sisse){
		this.skoorid=sisse;
	}
	
	public Skoor(){
		this.skoorid=new int[13];
	}
	
	/*
	skoorid[0]= Ühed
	skoorid[1]= Kahed
	skoorid[2]= Kolmed
	skoorid[0]= Ühed
	skoorid[1]= Kahed
	skoorid[2]= Kolmed
	skoorid[3]= Neljad
	skoorid[4]= Viied
	skoorid[5]= Kuued
	skoorid[6]= 3 Sarnast
	skoorid[7]= 4 Sarnast
	skoorid[8]= Maja
	skoorid[9]= Väike Rida
	skoorid[10]= Suur Rida 
	skoorid[11]= Yahtzee
	skoorid[12]= Chance
	 */
	
	
	
	@Override
	public String toString(){
		return "Ühed: \t\t" + skoorid[0] +
				"\nKahed: \t\t" + skoorid[1]+
				"\nKolmed: \t" + skoorid[2]+
				"\nNeljad: \t"+skoorid[3]+
				"\nViied: \t\t"+skoorid[4]+
				"\nKuued: \t\t"+skoorid[5]+
				"\n3 Sarnast: \t"+skoorid[6]+
				"\n4 Sarnast: \t"+skoorid[7]+
				"\nMaja: \t\t"+skoorid[8]+
				"\nVäike Rida: \t"+skoorid[9]+
				"\nSuur Maja: \t"+skoorid[10]+
				"\nYahtzee: \t"+skoorid[11]+
				"\nChance: \t"+skoorid[12];	
	}
	
	
	
	
}

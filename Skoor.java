import java.util.List;

public class Skoor {
	
	
	private int[] skoorid;
	
	public Skoor(int [] sisse){
		this.skoorid=sisse;
	}
	
	public Skoor(){
		this.skoorid=new int[16];
	}
	
	/*
	skoorid[0]= Ühed
	skoorid[1]= Kahed
	skoorid[2]= Kolmed
	skoorid[3]= Neljad
	skoorid[4]= Viied
	skoorid[5]= Kuued
	skorrid[6]=Esimese poole summa
	skoorid[7]=Boonus
	skoorid[8]= 1 Paar
	skoorid[9]= 2 Paar
	skorrid[10]= Kolmik
	skoorid[11]= Maja
	skoorid[12]= Väike Rida
	skoorid[13]= Suur Rida 
	skoorid[14]= Chance
	skoorid[15]= Yahtzee
	
	 */
	public void esimenePool(List<Täring> tulemus,int sisend){
		int täringuteSumma=0;
		if (sisend==1){
			for (Täring täring : tulemus){
				if (täring.getVise()==1){
					täringuteSumma+=1;
				} //sisemise if lõpp
			} // for lõpp
			skoorid[0]=täringuteSumma;
		} //välimise if lõpp
		if (sisend==2){
			for (Täring täring : tulemus){
				if (täring.getVise()==2){
					täringuteSumma+=2;
				} //sisemise if lõpp
			} // for lõpp
			skoorid[1]=täringuteSumma;
		} //välimise if lõpp
		if (sisend==3){
			for (Täring täring : tulemus){
				if (täring.getVise()==3){
					täringuteSumma+=3;
				} //sisemise if lõpp
			} // for lõpp
			skoorid[2]=täringuteSumma;
		} //välimise if lõpp
		if (sisend==4){
			for (Täring täring : tulemus){
				if (täring.getVise()==4){
					täringuteSumma+=4;
				} //sisemise if lõpp
			} // for lõpp
			skoorid[3]=täringuteSumma;
		} //välimise if lõpp
		if (sisend==5){
			for (Täring täring : tulemus){
				if (täring.getVise()==5){
					täringuteSumma+=5;
				} //sisemise if lõpp
			} // for lõpp
			skoorid[4]=täringuteSumma;
		} //välimise if lõpp
		if (sisend==6){
			for (Täring täring : tulemus){
				if (täring.getVise()==6){
					täringuteSumma+=6;
				} //sisemise if lõpp
			} // for lõpp
			skoorid[5]=täringuteSumma;
		} //välimise if lõpp
		skoorid[6]=skoorid[0]+skoorid[1]+skoorid[2]+skoorid[3]+skoorid[4]+skoorid[5];
		if (skoorid[6]>=63){
			skoorid[7]=50;
		}
	} // meetodi lõpp
	
	public void teinePool(List<Täring> tulemus,int sisend){
		int täringuteSumma=0;
		tulemus.sort(null);
		if (sisend==9){
		skoorid[8]=täringuteSumma;	
		} //if lõpp
		if (sisend==10){
			skoorid[9]=täringuteSumma;	
			} //if lõpp
		if (sisend==11){
			skoorid[10]=täringuteSumma;	
			} //if lõpp
		if (sisend==12){
			skoorid[11]=täringuteSumma;	
			} //if lõpp
		if (sisend==13){
			skoorid[12]=täringuteSumma;	
			} //if lõpp
		if (sisend==14){
			skoorid[13]=täringuteSumma;	
			} //if lõpp
		if (sisend==14){
			for (Täring täring : tulemus){
				täringuteSumma+=täring.getVise();
			}
			skoorid[15]=täringuteSumma;	
			} //if lõpp
		if (sisend==15){
			if (tulemus.get(0)==tulemus.get(1) && tulemus.get(1)==tulemus.get(2) && tulemus.get(2)==tulemus.get(3) && tulemus.get(3)==tulemus.get(4) && tulemus.get(4)==tulemus.get(5) && tulemus.get(5)==tulemus.get(6)){
				skoorid[15]=50;	
			}
			} //if lõpp
	} // meetod lõpp
	@Override
	public String toString(){
		return "1.Ühed: \t" + skoorid[0] +
				"\n2.Kahed: \t" + skoorid[1]+
				"\n3.Kolmed: \t" + skoorid[2]+
				"\n4.Neljad: \t"+skoorid[3]+
				"\n5.Viied: \t"+skoorid[4]+
				"\n6.Kuued: \t"+skoorid[5]+
				"\nPoolsumma: \t"+skoorid[6]+
				"\nBoonus: \t"+skoorid[7]+
				"\n9.Üks Paar: \t"+skoorid[8]+
				"\n10.Kaks Paari: \t"+skoorid[9]+
				"\n11.Kolmikr: \t"+skoorid[10]+
				"\n12.Maja: \t"+skoorid[11]+
				"\n13.Väike Rida: \t"+skoorid[12]+
				"\n14.Suur Rida: \t"+skoorid[13]+
				"\n15.Chance: \t"+skoorid[14] +
				"\n16.Yahtzee: \t"+skoorid[15];
				
	}
	
	
	
	
}

package application;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Tops {
	private List<Täring> täringud = new ArrayList<>();

	public Tops() {
		täringud.add(new Täring());
		täringud.add(new Täring());
		täringud.add(new Täring());
		täringud.add(new Täring());
		täringud.add(new Täring());
	}

	// Veeretatakse kõiki täringuid
	public List<Täring> viskering(){
		for (Täring täring : täringud){
			täring.veereta();
			//System.out.println(täring);
		}
		return getTäringud();
	}
	
	// Veeretatakse mängija valitud täringuid
	public List<Täring> viskering(Set<String> täringuteValik){
		System.out.println("Veeretan uuesti täringuid " + täringuteValik);
		for (String valitud : täringuteValik){
			System.out.println("Veeretan uuesti täringut " + Integer.parseInt(valitud));
			täringud.get(Integer.parseInt(valitud)-1).veereta();
		}
		for (Täring täring : täringud){
			System.out.println(täring);
		}
		return täringud;
	}

	public List<Täring> getTäringud() {
		return this.täringud;
	}

}

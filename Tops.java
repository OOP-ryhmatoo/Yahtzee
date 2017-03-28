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
	public void viskering(){
		for (Täring täring : täringud){
			täring.veereta();
			System.out.println(täring);
		}
	}
	
	// Veeretatakse mängija valitud täringuid
	public void viskering(Set<String> täringuteValik){
		for (String valitud : täringuteValik){
			System.out.println("Veeretan uuesti täringut " + Integer.parseInt(valitud));
			täringud.get(Integer.parseInt(valitud)-1).veereta();
		}
		for (Täring täring : täringud){
			System.out.println(täring);
		}
	}

	public List<Täring> getTäringud() {
		return this.täringud;
	}

}

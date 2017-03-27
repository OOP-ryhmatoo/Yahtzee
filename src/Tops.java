import java.util.ArrayList;
import java.util.List;

public class Tops {
	private List<Täring> täringud = new ArrayList<>();

	public Tops() {
		täringud.add(new Täring());
		täringud.add(new Täring());
		täringud.add(new Täring());
		täringud.add(new Täring());
		täringud.add(new Täring());
		täringud.add(new Täring());
	}
	
	public void viskering(){
		for (Täring täring : täringud){
			täring.veereta();
			System.out.println(täring);
		}
	}
	public void viskering(String[] valik){
		for (String valitud : valik){
			täringud.get(Integer.parseInt(valitud)-1).veereta();
		}
		for (Täring täring : täringud){
			System.out.println(täring);
		}
		}
	
}

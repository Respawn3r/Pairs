package fruits;

import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;

public class Fruit {
	
	private static final String[] NAMES = {
			"apple", "banana", "blueberry", "cherry", "fig", "lemon", 
			"orange", "pear", "plum", "raspberry", "strawberry", "watermelon"
	};
	
	private static int creationCount = 0;

	public static Fruit create() {
		
		return new Fruit(NAMES[creationCount++]);
		
	}
	
	private final String name;
	private PhongMaterial depiction;
	
	public Fruit(String name) {
		
		this.name = name;

		Image diffuse = new Image("/textures/" + this.name + "/diffuse.png");
		
		depiction = new PhongMaterial();
		depiction.setDiffuseMap(diffuse);
		
	}
	
	public PhongMaterial getDepiction() {
		return depiction;
	}
	
}

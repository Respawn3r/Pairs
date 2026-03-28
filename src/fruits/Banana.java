package fruits;

import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;

public class Banana extends Fruit {

	private static final String DIFFUSE_PATH = "/textures/banana/diffuse.png";
	
	private PhongMaterial depiction;
	
	
	public Banana() {
		
		Image diffuse = new Image(DIFFUSE_PATH);
		
		depiction = new PhongMaterial();
		depiction.setDiffuseMap(diffuse);
		
	}
	
	@Override
	public PhongMaterial getDepiction() {
		
		return depiction;
		
	}

}

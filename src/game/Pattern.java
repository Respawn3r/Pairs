package game;

import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;

public class Pattern {
	
	private static final String[] NAMES = {
			"1", "2", "3", "4", "5", "6", 
			"7", "8", "9", "10", "11", "12"
	};
	
	private static int creationCount = 0;

	public static Pattern create() {
		
		String tmp = NAMES[creationCount++];
		if (creationCount >= NAMES.length) creationCount = 0;
		return new Pattern(tmp);
		
	}
	
	private final String name;
	private Image diffuse;
	
	public Pattern(String name) {
		
		this.name = name;

		this.diffuse = new Image("/textures/diffuse" + this.name + ".png");
		
	}

	public Image getDiffuse() {
		return this.diffuse;
	}
	
}

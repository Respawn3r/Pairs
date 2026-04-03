package game;

import javafx.scene.image.Image;

public class Pattern {
	
	private static final String[] NAMES = {
			"ass", "beer", "cheese", "clouds", "colon", "eye", 
			"fish", "ladybug", "shoelaces", "street", "teeth", "zebra"
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

		this.diffuse = new Image("/textures/card_" + this.name + ".png");
		
	}

	public Image getDiffuse() {
		return this.diffuse;
	}
	
}

package game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Background extends Group {

	private static final Color COLOR = Color.LAWNGREEN;
	
	public Background() {
		Box box = new Box(5,5,5);
		box.setMaterial(new PhongMaterial(Color.DARKGREEN));
		this.getChildren().add(box);
		box.setTranslateZ(50);
	}
	
	public Color getColor() {
		return COLOR;
	}


}

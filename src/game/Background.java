package game;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Background extends Group {

	private static final Color COLOR = Color.GREEN;
	private static final float WIDTH = 60.0f;
	private static final float HEIGHT = 40.0f;
	private static final float GAP = 7.0f;
	private static final float SHAKE_POS = 2.0f;
	private static final float DEFAULT_PITCH = -45.0f;
	private static final float SHAKE_PITCH = 10.0f;
	private static final float DEFAULT_YAW = 45.0f;
	private static final float SHAKE_YAW = 10.0f;
	
	
	public Background() {
		
		for (float x = 0.0f; x < WIDTH; x += GAP) {
			for (float y = 0.0f; y < HEIGHT; y += GAP) {
				
				Grass grass = new Grass();
				this.getChildren().add(grass);
				grass.setTranslateX(x - WIDTH/2.0f);
				grass.setTranslateY(y - HEIGHT/2.0f);
				grass.setPitch(DEFAULT_PITCH);
				grass.setYaw(DEFAULT_YAW);
				grass.shake(SHAKE_POS, SHAKE_PITCH, SHAKE_YAW);
				
			}
		}
		
	}
	
	public Color getColor() {
		return COLOR;
	}


}

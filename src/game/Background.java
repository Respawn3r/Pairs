package game;

import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.DirectionalLight;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class Background extends Group {

	private static final Color COLOR = Color.LIMEGREEN;
	private static final float WIDTH = 60.0f;
	private static final float HEIGHT = 40.0f;
	private static final float GAP = 5.0f;
	private static final float SHAKE_POS = 1.0f;
	private static final float DEFAULT_PITCH = 90.0f;
	private static final float SHAKE_PITCH = 5.0f;
	private static final float DEFAULT_YAW = 45.0f;
	private static final float SHAKE_YAW = 4.0f;
	private static final Color SUN_COLOR = Color.WHITE;
	private static final double BACKGROUND_DISTANCE = 45.0f;
	private static final double TILT = -50.0f;
	
	
	
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
		
		DirectionalLight sun = new DirectionalLight(SUN_COLOR);
		sun.setDirection(new Point3D(0.0f, 1.0f, 3.0f));
		// this.getChildren().add(sun);
		AmbientLight ambient = new AmbientLight(SUN_COLOR);
		this.getChildren().add(ambient);
		
		this.setTranslateZ(BACKGROUND_DISTANCE);
		this.setRotationAxis(Rotate.X_AXIS);
		this.setRotate(TILT);
	}
	
	public Color getColor() {
		return COLOR;
	}


}

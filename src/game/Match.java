package game;

import fruits.Fruit;
import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Match {
	
	private static final double CAM_DISTANCE = 30.0;
	private static final double CAM_ANGLE = 5.0;
	private static final double CAM_ANGLE_ADJUSTMENT = 1.5;
	private static final int DEFAULT_FIELD_WIDTH = 6;
	private static final int DEFAULT_FIELD_HEIGHT = 4;
	private static final double WAIT_BEFORE_FACE_DOWN = 1.5;
	private static final Color BACKGROUND_COLOR = Color.LAWNGREEN;
	
	
	private Player firstPlayer;
	private Player secondPlayer;
	private Player turnHolder;
	private Field field;	
	private Scene scene;
	private PerspectiveCamera cam;
	private boolean locked;
	
	
	public Match() {
		
		this.firstPlayer = new Player();
		this.secondPlayer = new Player();
		this.turnHolder = firstPlayer;
		this.field = new Field(this, DEFAULT_FIELD_WIDTH, DEFAULT_FIELD_HEIGHT);
		this.locked = false;
		
		cam = new PerspectiveCamera(true);
		cam.setTranslateZ(-CAM_DISTANCE);
		cam.setTranslateY(-CAM_ANGLE_ADJUSTMENT);
		cam.setRotationAxis(Rotate.X_AXIS);
		cam.setRotate(-CAM_ANGLE);
		
		this.scene = new Scene(new Group(field, cam), 0, 0, 
				true, SceneAntialiasing.BALANCED);
		this.scene.setFill(BACKGROUND_COLOR);
		this.scene.setCamera(cam);
				
	}
	
	public Scene getScene() {
		
		return this.scene;
		
	}

	public void reportClick(Card card) {
		
		if (locked) return;
		locked = true;
		
		boolean selectionCompleted = turnHolder.getSelection().add(card);
		
		if (!selectionCompleted) {
			card.faceUp();
			locked = false;
			return;
		}
		
		if (selectionCompleted) {
			Fruit pairedFruit = turnHolder.getSelection().evaluate();
			
			if (pairedFruit == null) {
				card.faceUp();
				PauseTransition pause = new PauseTransition(
						Duration.seconds(WAIT_BEFORE_FACE_DOWN));
				pause.setOnFinished(_ -> {
					turnHolder.getSelection().faceDown();
					turnHolder.getSelection().reset();
					turnHolder = (turnHolder == firstPlayer) ? 
							secondPlayer : firstPlayer;
					locked = false;
				});
				pause.play();
				
			} else {
				// make card face up happily
				// pause
				// assign both cards of selection to turn holder
				// check for win condition
				// reset selection
				// unlock
			}
		}
		
	}
	
	
}

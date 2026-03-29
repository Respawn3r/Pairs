package game;

import fruits.Fruit;
import javafx.animation.PauseTransition;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Match extends Foreground {
	
	private static final double FIELD_DISTANCE = 30.0;
	private static final double FIELD_TILT = 0.0; 
	// BUG!!! When u tilt, the animation (applied to child) moves all other children.
	// Copilot said it has to do with recalculated pivot point.
	// Try wrapping the animation group in another parent. Apply tilt to wrapper only.
	private static final int DEFAULT_FIELD_WIDTH = 6;
	private static final int DEFAULT_FIELD_HEIGHT = 4;
	private static final double WAIT_BEFORE_FACE_DOWN = 1.5;
	
	
	private Player firstPlayer;
	private Player secondPlayer;
	private Player turnHolder;
	private Field field;
	private boolean locked;
	
	
	public Match(Game game) {
		
		super(game);
		
		this.firstPlayer = new Player();
		this.secondPlayer = new Player();
		this.turnHolder = firstPlayer;
		this.locked = false;
		
		this.field = new Field(this, DEFAULT_FIELD_WIDTH, DEFAULT_FIELD_HEIGHT);
		this.getChildren().add(field);
		this.field.setTranslateZ(FIELD_DISTANCE);
		this.field.setRotationAxis(Rotate.X_AXIS);
		this.field.setRotate(FIELD_TILT);
		
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

	@Override
	public void play() {
		
		return;
	}
	
	
}

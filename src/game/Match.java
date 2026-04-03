package game;

import javafx.animation.Animation;
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
		
		this.firstPlayer = new Player("blue");
		this.secondPlayer = new Player("orange");
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
			Pattern pairedFruit = turnHolder.getSelection().evaluate();
			
			if (pairedFruit == null) {
				card.faceUp();
				PauseTransition pause = new PauseTransition(
						Duration.seconds(WAIT_BEFORE_FACE_DOWN));
				pause.setOnFinished(_ -> {
					turnHolder.getSelection().faceDown();
					turnHolder.getSelection().reset();
					this.swapTurnHolder();
					locked = false;
				});
				pause.play();
				
			} else {
				card.faceUpHappy();
				PauseTransition pause = new PauseTransition(
						Duration.seconds(WAIT_BEFORE_FACE_DOWN));
				pause.setOnFinished(_ -> {
					turnHolder.getSelection().assign(turnHolder);
					turnHolder.getSelection().reset();
					if (field.isAllFaceUp()) {
						if (field.isWinner(turnHolder)) {
							Animation anim = field.getWinAnimation(turnHolder);
							anim.setOnFinished(_ -> {
								game.reportEnd();
							});
							anim.play();
						} else {
							Animation anim = field.getRemisAnimation();
							anim.setOnFinished(_ -> {
								game.reportEnd();
							});
							anim.play();
						}
					}
					locked = false;
				});
				pause.play();
			}
		}
		
	}
	
	private void swapTurnHolder() {

		turnHolder = (turnHolder == firstPlayer) ? 
				secondPlayer : firstPlayer;
		this.getScene().setCursor(turnHolder.getCursor());
		
	}
	

	@Override
	public void play() {
		
		this.getScene().setCursor(turnHolder.getCursor()); // image cursor for title screen
		
		return;
	}
	
	
}

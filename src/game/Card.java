package game;

import fruits.Fruit;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import shapes.CardEdge;
import shapes.CardFace;

public class Card extends Group {
	
	public static final double SHORT_SIDE = 2.0;
	public static final double LONG_SIDE = 3.0;
	public static final double THICKNESS = .2;
	private static final double FACE_UP_DURATION = .5;
	private static final double FACE_DOWN_DURATION = 1.0;
	private static final double TURN_HEIGHT = 3.0;
	

	private Match match;	
	private Fruit fruit;
	private boolean facingUp;
	private CardFace frontFace;
	private CardFace backFace;
	private CardEdge cardEdge;
	private ParallelTransition faceUpTransition;
	private ParallelTransition faceDownTransition;
	

	
	public Card(Match match, Fruit fruit) {
		
		this.match = match;
		this.fruit = fruit;
		this.facingUp = false;
		
		frontFace = new CardFace(fruit);
		frontFace.setTranslateZ(THICKNESS * -.5);
		backFace = new CardFace(null);
		backFace.setTranslateZ(THICKNESS * .5);
		backFace.setRotationAxis(Rotate.X_AXIS);
		backFace.setRotate(180.0);
		cardEdge = new CardEdge();
		this.getChildren().addAll(frontFace, backFace, cardEdge);
		
		this.setRotationAxis(Rotate.X_AXIS);
		this.setRotate(180.0);
		
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, 
				new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				
				if (!facingUp) {
					Card.this.match.reportClick(Card.this);
				}
				
				mouseEvent.consume();
				
			}
			
		});
		
		TranslateTransition upT1 = new TranslateTransition(
				Duration.seconds(FACE_UP_DURATION / 2), this);
		upT1.setToZ(-TURN_HEIGHT);
		TranslateTransition upT2 = new TranslateTransition(
				Duration.seconds(FACE_UP_DURATION / 2), this);
		upT2.setToZ(0);
		SequentialTransition upSQ = new SequentialTransition(
				this, upT1, upT2);
		RotateTransition upRT = new RotateTransition(
				Duration.seconds(FACE_UP_DURATION), this);
		upRT.setAxis(Rotate.X_AXIS);
		upRT.setToAngle(0.0);
		this.faceUpTransition = new ParallelTransition(this, upSQ, upRT);
		
		TranslateTransition downT1 = new TranslateTransition(
				Duration.seconds(FACE_DOWN_DURATION / 2), this);
		downT1.setToZ(-TURN_HEIGHT);
		TranslateTransition downT2 = new TranslateTransition(
				Duration.seconds(FACE_DOWN_DURATION / 2), this);
		downT2.setToZ(0);
		SequentialTransition downSQ = new SequentialTransition(
				this, downT1, downT2);
		RotateTransition downRT = new RotateTransition(
				Duration.seconds(FACE_DOWN_DURATION), this);
		downRT.setAxis(Rotate.X_AXIS);
		downRT.setToAngle(180.0);
		this.faceDownTransition = new ParallelTransition(this, downSQ, downRT);
		
	}
	
	public void faceUp() {
		faceUpTransition.play();
		facingUp = true;
		return;
	}
	
	public void faceUpHappy() {
		//TODO create according transition in constructor
		faceUp();
		return;
	}

	public void faceDown() {
		faceDownTransition.play();
		facingUp = false;
		return;
	}
	
	public Fruit getFruit() {
		return this.fruit;
	}
}

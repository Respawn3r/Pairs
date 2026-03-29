package game;

import fruits.Fruit;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.PhongMaterial;
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
	private static final double HAPPY_TURN_HEIGHT = 5.0;
	private static final int HAPPY_REVS = 1;
	private static final double HAPPY_FACE_UP_DURATION = 1.5;
	

	private Match match;	
	private Fruit fruit;
	private boolean facingUp;
	private CardFace frontFace;
	private CardFace backFace;
	private CardEdge cardEdge;
	private ParallelTransition faceUpTransition;
	private ParallelTransition faceUpHappyTransition;
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
		
		TranslateTransition uT1 = new TranslateTransition(
				Duration.seconds(FACE_UP_DURATION / 2), this);
		uT1.setToZ(-TURN_HEIGHT);
		TranslateTransition uT2 = new TranslateTransition(
				Duration.seconds(FACE_UP_DURATION / 2), this);
		uT2.setToZ(0);
		SequentialTransition uT = new SequentialTransition(
				this, uT1, uT2);
		RotateTransition uR = new RotateTransition(
				Duration.seconds(FACE_UP_DURATION), this);
		uR.setAxis(Rotate.X_AXIS);
		uR.setToAngle(0.0);
		this.faceUpTransition = new ParallelTransition(this, uT, uR);
		
		TranslateTransition hT1 = new TranslateTransition(
				Duration.seconds(HAPPY_FACE_UP_DURATION / 2), this);
		hT1.setToZ(-HAPPY_TURN_HEIGHT);
		TranslateTransition hT2 = new TranslateTransition(
				Duration.seconds(HAPPY_FACE_UP_DURATION / 2), this);
		hT2.setToZ(0);
		SequentialTransition hT = new SequentialTransition(
				this, hT1, hT2);
		RotateTransition hR = new RotateTransition(
				Duration.seconds(HAPPY_FACE_UP_DURATION / 2), this);
		hR.setAxis(Rotate.X_AXIS);
		hR.setToAngle(HAPPY_REVS * -360.0);
		this.faceUpHappyTransition = new ParallelTransition(this, hT, hR);
		
		TranslateTransition dT1 = new TranslateTransition(
				Duration.seconds(FACE_DOWN_DURATION / 2), this);
		dT1.setToZ(-TURN_HEIGHT);
		TranslateTransition dT2 = new TranslateTransition(
				Duration.seconds(FACE_DOWN_DURATION / 2), this);
		dT2.setToZ(0);
		SequentialTransition dT = new SequentialTransition(
				this, dT1, dT2);
		RotateTransition dR = new RotateTransition(
				Duration.seconds(FACE_DOWN_DURATION), this);
		dR.setAxis(Rotate.X_AXIS);
		dR.setToAngle(180.0);
		this.faceDownTransition = new ParallelTransition(this, dT, dR);
		
	}
	
	
	public void faceUp() {
		faceUpTransition.play();
		facingUp = true;
		return;
	}
	
	
	public void faceUpHappy() {
		faceUpHappyTransition.play();
		facingUp = true;
		return;
	}

	
	public void faceDown() {
		faceDownTransition.play();
		facingUp = false;
		return;
	}


	public void assign(Player player) {
		// TODO: replace this with the zig-zag frame thing.
		this.frontFace.setMaterial(new PhongMaterial(player.getColor()));
		return;
	}
	
	
	public Fruit getFruit() {
		return this.fruit;
	}
}

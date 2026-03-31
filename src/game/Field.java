package game;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.util.Duration;

public class Field extends Group {
	
	private static final int TIMES_SHUFFLE = 100;
	private static final double GAP_FACTOR = .3;
	private static final double GAP = Card.SHORT_SIDE * GAP_FACTOR;
	
	private Match match;
	private int width;
	private int height;
	private int size;
	private Pattern[] fruits;
	private Card[] cards;
	private double xOffset;
	private double yOffset;
	
	public Field(Match match, int width, int height) {
		
		this.match = match;
		this.width = width;
		this.height = height;
		size = width * height;
		
		fruits = new Pattern[size / 2];
		cards = new Card[size];
		
		for (int i = 0; i < fruits.length; i++) {
			fruits[i] = Pattern.create();
			cards[2 * i] = new Card(this.match, fruits[i]);
			cards[2 * i + 1] = new Card(this.match, fruits[i]);
		}
		
		for (int i = 0; i < TIMES_SHUFFLE; i++) {
			shuffle();
		}
		
		this.getChildren().addAll(cards);
		
		this.xOffset = xOffset();
		this.yOffset = zOffset();
		
		for (int i = 0; i < cards.length; i++) {
			cards[i].setTranslateX(this.iToRealX(i));
			cards[i].setTranslateY(this.iToRealY(i));
		}
		
	}
	
	private void shuffle() {
		
		int i = StaticRandom.random.nextInt(size);
		int j = StaticRandom.random.nextInt(size);
		Card tmp = cards[i];
		cards[i] = cards[j];
		cards[j] = tmp;
		
	}
	
	private double iToRealX(int i) {
		int x = i % width;
		
		double xCoordinate = x * (Card.SHORT_SIDE + GAP);
		
		return xCoordinate + xOffset;
	}

	private double iToRealY(int i) {
		int y = i / width;
		
		double yCoordinate = y * (Card.LONG_SIDE + GAP) * -1;
		
		return yCoordinate + yOffset;
	}
	
	private double xOffset() {
		double totalRealWidth = width * Card.SHORT_SIDE + (width - 1) * GAP;
		return totalRealWidth / 2 * -1;
	}
	
	private double zOffset() {
		double totalRealHeight = height * Card.LONG_SIDE + (height - 1) * GAP;
		return totalRealHeight / 2;
	}

	public boolean isAllFaceUp() {
		for (int i = 0; i < cards.length; i++) {
			if (!cards[i].isFacingUp()) return false;
		}
		return true;
	}

	public boolean isWinner(Player turnHolder) {
		int turnHolderCount = 0;
		int otherCount = 0;
		
		for (int i = 0; i < cards.length; i++) {
			if (cards[i].getAssignment() == turnHolder) {
				turnHolderCount++;
			} else {
				otherCount++;
			}
		}
		
		return turnHolderCount > otherCount; // remis otherwise
	}

	public Animation getWinAnimation(Player turnHolder) {
		System.out.println("win animation");
		return new PauseTransition(Duration.seconds(3.0));
	}

	public Animation getRemisAnimation() {
		System.out.println("remis animation");
		return new PauseTransition(Duration.seconds(3.0));
	}

	
}

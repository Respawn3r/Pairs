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
		
		this.xOffset = offset(true);
		this.yOffset = offset(false);
		
		for (int i = 0; i < cards.length; i++) {
			cards[i].setTranslateX(this.iToReal(i, true));
			cards[i].setTranslateY(this.iToReal(i, false));
		}
		
	}
	
	private void shuffle() {
		
		int i = StaticRandom.random.nextInt(size);
		int j = StaticRandom.random.nextInt(size);
		Card tmp = cards[i];
		cards[i] = cards[j];
		cards[j] = tmp;
		
	}
	
	private double iToReal(int i, boolean x) {
		int coord = x ? i % width : i / width;
		double offset = x ? xOffset : yOffset;
		double length = x ? Card.SHORT_SIDE : Card.LONG_SIDE;
		
		return offset + coord * (length + GAP);
	}
	
	private double offset(boolean x) {
		int count = x ? width : height;
		double length = x ? Card.SHORT_SIDE : Card.LONG_SIDE;
		
		double minMaxSpan = ((double)(count - 1)) 
				* (length + GAP);
		return minMaxSpan / 2.0f * -1.0f;
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

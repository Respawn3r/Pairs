package game;

import fruits.Fruit;

public class Selection {

	private Card first;
	private Card second;
	
	public boolean add(Card card) {
		
		if (first == null) {
			first = card;
			return false;
		}
		
		if (second == null) {
			second = card;
			return true;
		}
		
		return true;
		
	}

	public Fruit evaluate() {
		return null;
	}
	
	public void faceDown() {
		first.faceDown();
		second.faceDown();
	}

	public void reset() {
		first = null;
		second = null;
	}

}

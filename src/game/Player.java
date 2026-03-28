package game;

import fruits.Fruit;

public class Player {

	private Selection selection;
	
	public Player() {
		this.selection = new Selection();
	}

	public void assignFruit(Fruit fruit) {
		//TODO
	}

	public Selection getSelection() {
		return this.selection;
	}
}

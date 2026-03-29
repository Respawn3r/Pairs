package game;

import fruits.Fruit;
import javafx.scene.paint.Color;

public class Player {

	private Selection selection;
	private Color color;
	
	public Player(Color color) {
		this.selection = new Selection();
		this.color = color;
	}

	public void assignFruit(Fruit fruit) {
		//TODO
	}

	public Selection getSelection() {
		return this.selection;
	}

	public Color getColor() {
		return this.color;
	}
}

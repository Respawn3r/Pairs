package game;

import javafx.scene.Group;

public abstract class Foreground extends Group {

	protected Game game;
	
	public Foreground(Game game) {
		this.game = game;
		
		// create scene graph
		
	}
	
	public abstract void play();
	
}

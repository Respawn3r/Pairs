package game;

import javafx.animation.PauseTransition;
import javafx.scene.shape.Box;
import javafx.util.Duration;

public class TitleScreen extends Foreground {

	public TitleScreen(Game game) {
		
		super(game);
		
		Box box = new Box();
		this.getChildren().add(box);
		box.setTranslateZ(10);
		
	}

	@Override
	public void play() {
		
		PauseTransition p = new PauseTransition(Duration.seconds(1.0));
		p.setOnFinished(_ -> {game.reportEnd();});
		p.play();
		
	}

}

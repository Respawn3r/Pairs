package game;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class TitleScreen extends Foreground {

	private static final double TITLE_DISTANCE = 10.0;
	private static final double TITLE_TIME = 2.0;

	public TitleScreen(Game game) {
		
		super(game);
		
		TitleMeshView titleMeshView = new TitleMeshView();
		this.getChildren().add(titleMeshView);
		titleMeshView.setTranslateZ(TITLE_DISTANCE);
		
	}

	@Override
	public void play() {
		
		this.getScene().setCursor(null); // image cursor for title screen
		
		PauseTransition p = new PauseTransition(Duration.seconds(TITLE_TIME));
		p.setOnFinished(_ -> {game.reportEnd();});
		p.play();
		
	}

}

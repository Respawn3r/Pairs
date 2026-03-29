package game;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Game extends Application {
	
	private static final String TITLE = "Pairs";
	private static final double LEFT_SPACING = .05;
	private static final double UPPER_SPACING = .05;
	private static final double RIGHT_SPACING = .3;
	private static final double BOTTOM_SPACING = .3;
	
	private enum Phase {
		TITLE, MATCH, WIN
	};
	
	private Stage stage;
	private Scene scene;
	private Background background;
	private Group foregroundRoot;
	private Phase phase;
	
	@Override
	public void start(Stage stage) throws Exception {
	
		this.stage = stage;
		
		this.stage.setTitle(TITLE);
		this.stage.setX(Screen.getPrimary().getVisualBounds().getWidth()
				* LEFT_SPACING);
		this.stage.setY(Screen.getPrimary().getVisualBounds().getHeight()
				* UPPER_SPACING);
		this.stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth()
				* (1 - LEFT_SPACING - RIGHT_SPACING));
		this.stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight()
				* (1 - UPPER_SPACING - BOTTOM_SPACING));

		
		this.background = new Background();
		this.foregroundRoot = new Group();
		this.scene = new Scene(new Group(this.background, this.foregroundRoot), 
				0, 0, true, SceneAntialiasing.BALANCED);
		this.scene.setFill(background.getColor());
		this.scene.setCamera(new PerspectiveCamera(true));
		this.stage.setScene(this.scene);
		this.stage.show();
		
		this.replaceForeground(new TitleScreen(this));
		this.phase = Phase.TITLE;
		
	}
	
	
	private void replaceForeground(Foreground foreground) {
		
		this.foregroundRoot.getChildren().clear();
		this.foregroundRoot.getChildren().add(foreground);
		foreground.play();
		
	}
	
	
	public void reportEnd() {
		
		switch(this.phase) {
		case TITLE:
			this.replaceForeground(new Match(this));
			this.phase = Phase.MATCH;
			break;
		case MATCH:
			this.replaceForeground(new WinScreen(this));
			this.phase = Phase.WIN;
			break;
		case WIN:
			this.replaceForeground(new Match(this));
			this.phase = Phase.MATCH;
			break;
		}
		
	}

	
	public PerspectiveCamera getCam() {
		
		return (PerspectiveCamera) this.scene.getCamera();
		
	}
	
	
	@Override
	public void stop() {
		
	}
	

}

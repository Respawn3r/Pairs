package game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FruitPairs extends Application {
	
	public static final double LEFT_SPACING = 50;
	public static final double UPPER_SPACING = 50;
	public static final double RELATIVE_WIDTH = .5;
	public static final double RELATIVE_HEIGHT = .5;
	
	private Stage stage;
	
	private Scene introScene;
	private Scene matchScene;
	
	private Match match;
	
	@Override
	public void start(Stage stage) throws Exception {
	
		this.stage = stage;
		stage.setTitle("Fruit Pairs");
		stage.setX(LEFT_SPACING);
		stage.setY(UPPER_SPACING);
		stage.setWidth((Screen.getPrimary().getVisualBounds().getWidth() - LEFT_SPACING) / 2);
		stage.setHeight((Screen.getPrimary().getVisualBounds().getHeight() - UPPER_SPACING) / 2);

		match = new Match();
		matchScene = match.getScene();
		introScene = createIntroScene();
		
		this.stage.setScene(introScene);
		
		stage.show();
		
	}
	
	
	private Scene createIntroScene() {
		
		Text gameTitle = new Text(0, 100, "FRUIT PAIRS");
		Text instruction = new Text(0, 200, "click anywhere to start");
		Group textGroup = new Group(gameTitle, instruction);
		Scene tmp = new Scene(textGroup, Color.LAWNGREEN);
		
		tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				FruitPairs.this.stage.setScene(matchScene);
				
			}});
		
		return tmp;
		
	}
	
	
	@Override
	public void stop() {
		
	}
	
	

}

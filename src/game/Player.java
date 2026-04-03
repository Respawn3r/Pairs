package game;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

public class Player {

	private Selection selection;
	private String name;
	private ImageCursor cursor;
	
	public Player(String name) {
		this.selection = new Selection();
		this.name = name;
		this.cursor = new ImageCursor(new Image("/textures/" + name + "_cursor.png"));
	}

	public Selection getSelection() {
		return this.selection;
	}

	public String getName() {
		return this.name;
	}

	public ImageCursor getCursor() {
		return this.cursor;
	}

	public Mark generateMark() {
		return new Mark(this.name);
	}
}

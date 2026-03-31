package game;



public class Player {

	private Selection selection;
	private String name;
	
	public Player(String name) {
		this.selection = new Selection();
		this.name = name;
	}

	public Selection getSelection() {
		return this.selection;
	}

	public String getName() {
		return this.name;
	}
}

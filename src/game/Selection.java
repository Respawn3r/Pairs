package game;

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

	public Pattern evaluate() {
		return first.getFruit().equals(second.getFruit()) ? 
				first.getFruit() : null;
	}
	
	public void faceDown() {
		first.faceDown();
		second.faceDown();
	}
	
	public void assign(Player player) {
		first.assign(player);
		second.assign(player);
	}

	public void reset() {
		first = null;
		second = null;
	}

}

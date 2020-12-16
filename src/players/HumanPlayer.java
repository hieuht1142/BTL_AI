package players;

import java.awt.Point;

public class HumanPlayer extends Player {
	
	private String name;
	
	public HumanPlayer(int playerNumber, String name) {
		super(playerNumber);
		this.name = name;
	}

	@Override
	public boolean isHumanPlayer() {
		return true;
	}

	@Override
	public Point play(int[][] board) {
		return null;
	}

	@Override
	public String toString() {
		return "Player " + name;
	}
}

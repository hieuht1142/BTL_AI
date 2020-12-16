package players;

import java.awt.Point;

public abstract class Player {
	
	private int playerNumber; // 1 or 2	
	
	/*public Player() {
		
	}*/
	
	public Player(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	
	public int getPlayerNumber() {
		return playerNumber;
	}

	public abstract boolean isHumanPlayer();
	
	public abstract Point play(int[][] board);
	
}

package players;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import game.GameManager;

public class RandomPlayer extends Player {

	public RandomPlayer(int playerNumber) {
		super(playerNumber);
	}

	@Override
	public boolean isHumanPlayer() {
		return false;
	}

	@Override
	public Point play(int[][] board) {
		Random random = new Random();
		int player = getPlayerNumber();
		
		List<Point> possibleMoves = GameManager.getAllPossibleMoves(board, player);
		
		if (possibleMoves.size() == 0) return null;
		return possibleMoves.get(random.nextInt(possibleMoves.size()));
	}

}

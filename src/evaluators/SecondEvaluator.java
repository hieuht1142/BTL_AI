package evaluators;

import game.Board;
import game.GameManager;

public class SecondEvaluator extends Heuristics implements Evaluator {

	@Override
	public double evaluate(int[][] board, int player) {
		if (GameManager.isGameFinished(board)) {
			return evalDiscParity(board, player);
		}
	
		switch (getGamePhase(board)) {
			case END:
				return evalDiscParity(board, player);
			default:
				return evalStaticWeights(board, player);
		}
	}
	
	private Phase getGamePhase(int[][] board) {
		int numDisc = Board.getTotalDiscNum(board);
		if (numDisc <= 20) {
			return Phase.BEGIN;
		} else if (numDisc <= 50) {
			return Phase.MIDDLE;
		} else {
			return Phase.END;
		}
  }

}

package evaluators;

import game.GameManager;

public class AdvancedEvaluator extends Heuristics implements Evaluator {

	@Override
	public double evaluate(int[][] board, int player) {
		if (GameManager.isGameFinished(board)) {
			return evalDiscParity(board, player);
		}
		
		switch (GameManager.getGamePhase(board)) {
			case END:
				return evalDiscParity(board, player);
			default:
				double value = (30 * evalCornerCaptured(board, player) + 5 * evalMobility(board, player)
								+ 25 * evalStability(board, player) + 25 * evalDiscParity(board, player)) / 85;
				return value;
		}
	}
	
	public String toString() {
		return "AdvancedEval";
	}
	
}

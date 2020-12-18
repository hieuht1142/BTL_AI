package evaluators;

import game.GameManager;

public class WeightTableEvaluator extends Heuristics implements Evaluator {

	@Override
	public double evaluate(int[][] board, int player) {
		if (GameManager.isGameFinished(board)) {
			return evalDiscParity(board, player);
		}
	
		switch (GameManager.getGamePhase(board)) {
			case END:
				return evalDiscParity(board, player);
			default:
				return evalStaticWeights(board, player);
		}
	}
	
	public String toString() {
		return "WeightEval";
	}

}

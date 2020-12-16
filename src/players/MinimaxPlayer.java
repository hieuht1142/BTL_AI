package players;

import java.awt.Point;

import evaluators.Evaluator;
import game.GameManager;

public class MinimaxPlayer extends Player {
	
	private int searchDepth;
	private Evaluator evaluator;
	
	public MinimaxPlayer(int playerNumber, int searchDepth, Evaluator evaluator) {
		super(playerNumber);
		this.searchDepth = searchDepth;
		this.evaluator = evaluator;
	}

	@Override
	public boolean isHumanPlayer() {
		return false;
	}

	@Override
	public Point play(int[][] board) {
		return minimaxWithABPruning(board, getPlayerNumber(), searchDepth, evaluator);
	}
	
	private Point minimaxWithABPruning(int[][] board, int player, int depth, Evaluator e) {
		Point bestMove = null;
		int oplayer = (player == 1) ? 2 : 1;
		
		double max = -Double.MAX_VALUE;
		for (Point move : GameManager.getAllPossibleMoves(board, player)) {
			
			int[][] newBoard = GameManager.getNewBoardAfterMove(board, move, player);
			double score = minValue(newBoard, oplayer, depth - 1, -Double.MAX_VALUE, Double.MAX_VALUE, e);
			
			if (score > max) {
				max = score;
				bestMove = move;
			}
		}
		
		return bestMove;
	}
	
	private double maxValue(int[][] board, int player, int depth, 
							double alpha, double beta, Evaluator e) {
		int opponent = (player == 1) ? 2 : 1;
		//terminal test
		if (depth == 0 || GameManager.isGameFinished(board)) {
			return e.evaluate(board, player);
		}
		
		//if has no move
		if (!GameManager.hasAnyMoves(board, player)) {
			return minValue(board, opponent, depth - 1, alpha, beta, e);
		}
		
		double max = -Double.MAX_VALUE;
		for (Point move : GameManager.getAllPossibleMoves(board, player)) {
			
			int[][] newBoard = GameManager.getNewBoardAfterMove(board, move, player);
			double score = minValue(newBoard, opponent, depth - 1, alpha, beta, e);
			
			max = (score > max) ? score : max;
			if (max >= beta) return max;
			alpha = (max > alpha) ? max : alpha;
		}
		
		return max;
	}
	
	private double minValue(int[][] board, int player, int depth, 
							double alpha, double beta, Evaluator e) {
		int opponent = (player == 1) ? 2 : 1;
		//terminal test
		if (depth == 0 || GameManager.isGameFinished(board)) {
			return e.evaluate(board, opponent);
		}
		
		//if has no move
		if (!GameManager.hasAnyMoves(board, player)) {
			return maxValue(board, opponent, depth - 1, alpha, beta, e);
		}
		
		double min = Double.MAX_VALUE;
		for (Point move : GameManager.getAllPossibleMoves(board, player)) {
			
			int[][] newBoard = GameManager.getNewBoardAfterMove(board, move, player);
			double score = maxValue(newBoard, opponent, depth - 1, alpha, beta, e);
			
			min = (score < min) ? score : min;
			if (min <= alpha) return min;
			beta = (min < beta) ? min : beta;
		}
		
		return min;
	}
	
	@Override
	public String toString() {
		return "Minimax player";
	}
		
}

package player.ai;

import java.awt.Point;

import game.BoardHelper;

public class MinimaxSearch {
	
	public static Point minimaxWithABPrunning(int[][] board, int player, int depth, Evaluator e) {
		Point bestMove = null;
		int oplayer = (player == 1) ? 2 : 1;
		
		int max = Integer.MIN_VALUE;;
		for (Point move : BoardHelper.getAllPossibleMoves(board, player)) {
			
			int[][] newBoard = BoardHelper.getNewBoardAfterMove(board, move, player);
			int score = minValue(newBoard, oplayer, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, e);
			System.out.println("Score: " + score);
			if (score > max) {
				max = score;
				bestMove = move;
			}
			System.out.println("Max: " + max);
		}
		
		return bestMove;
	}
	
	private static int maxValue(int[][] board, int player, int depth, 
							int alpha, int beta, Evaluator e) {
		
		//terminal test
		if (depth == 0 || BoardHelper.isGameFinished(board)) {
			return e.eval(board, player);
		}
		
		int oplayer = (player == 1) ? 2 : 1;
		
		//if has no move
		if (!BoardHelper.hasAnyMoves(board, player)) {
			return minValue(board, oplayer, depth - 1, alpha, beta, e);
		}
		
		int max = Integer.MIN_VALUE;
		for (Point move : BoardHelper.getAllPossibleMoves(board, player)) {
			
			int[][] newBoard = BoardHelper.getNewBoardAfterMove(board, move, player);
			int score = minValue(newBoard, oplayer, depth - 1, alpha, beta, e);
			
			max = (score > max) ? score : max;
			alpha = (max > alpha) ? max : alpha;
			if (beta <= alpha) break;
			/*if (max >= beta) return max;
			alpha = (max > alpha) ? max : alpha;*/		
		}
		
		return max;
	}
	
	private static int minValue(int[][] board, int player, int depth,
							int alpha, int beta, Evaluator e) {
		
		//terminal test
		if (depth == 0 || BoardHelper.isGameFinished(board)) {
			return e.eval(board, player);
		}
		
		int oplayer = (player == 1) ? 2 : 1;
		
		//if has no move
		if (!BoardHelper.hasAnyMoves(board, player)) {
			return maxValue(board, oplayer, depth - 1, alpha, beta, e);
		}
		
		int min = Integer.MAX_VALUE;
		for (Point move : BoardHelper.getAllPossibleMoves(board, player)) {
			
			int[][] newBoard = BoardHelper.getNewBoardAfterMove(board, move, player);
			int score = maxValue(newBoard, oplayer, depth - 1, alpha, beta, e);
			
			min = (score < min) ? score : min;
			beta = (min < beta) ? min : beta;
			if (beta <= alpha) break;
			/*if (min <= alpha) return min;
			beta = (min < beta) ? min : beta;*/
		}
	
		return min;
	}
	
}

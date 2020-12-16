package evaluators;

import game.Board;
import game.GameManager;

public class Heuristics {

	public double evalDiscParity(int[][] board , int player) {
		int opponent = (player == 1) ? 2 : 1;
		
		int myDisc = Board.getPlayerDiscNum(board, player);
		int opDisc = Board.getPlayerDiscNum(board, opponent);
		
		return 100 * (myDisc - opDisc) / (myDisc + opDisc);
	}
	
	public double evalMobility(int[][] board , int player) {
		int opponent = (player == 1) ? 2 : 1;
		
		int myValue = GameManager.getAllPossibleMoves(board, player).size();
		int opponentValue = GameManager.getAllPossibleMoves(board, opponent).size();
		
		if (myValue + opponentValue == 0) return 0;
		return 100.0 * (myValue - opponentValue) / (myValue + opponentValue);
	}
	
	public double evalCornerCaptured(int[][] board , int player) {
		int opponent = (player == 1) ? 2 : 1;
		
		int myCorners = 0;
		int opponentCorners = 0;
		
		if(board[0][0] == player) myCorners++;
        if(board[7][0] == player) myCorners++;
        if(board[0][7] == player) myCorners++;
        if(board[7][7] == player) myCorners++;
        
        if(board[0][0] == opponent) opponentCorners++;
        if(board[7][0] == opponent) opponentCorners++;
        if(board[0][7] == opponent) opponentCorners++;
        if(board[7][7] == opponent) opponentCorners++;
        
        if (myCorners + opponentCorners == 0) return 0;
        return 100.0 * (myCorners - opponentCorners) / (myCorners + opponentCorners);
	}
	
	public double evalStability(int[][] board , int player) {
		int opponent = (player == 1) ? 2 : 1;
		
		int myStableNum = Board.getStableDiscNum(board, player);
		int opStableNum = Board.getStableDiscNum(board, opponent);
		
		if (myStableNum + opStableNum == 0) return 0;
		return 100.0 * (myStableNum - opStableNum) / (myStableNum + opStableNum);
	}
	
	public double evalStaticWeights(int[][] board , int player) {
		final int[][] w = {
						{100, -20, 10,  5,  5, 10, -20, 100},
						{-20, -50, -2, -2, -2, -2, -50, -20},
						{ 10,  -2, -1, -1, -1, -1,  -2,  10},
						{  5,  -2, -1, -1, -1, -1,  -2,   5},
						{  5,  -2, -1, -1, -1, -1,  -2,   5},
						{ 10,  -2, -1, -1, -1, -1,  -2,  10},
						{-20, -50, -2, -2, -2, -2, -50, -20},
						{100, -20, 10,  5,  5, 10, -20, 100}
		};
		
		int opponent = (player == 1) ? 2 : 1;
		
		int myWeight = 0;
		int opWeight = 0;
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == player) myWeight += w[i][j];
				if (board[i][j] == opponent) opWeight += w[i][j];
			}
		}
		
		return myWeight - opWeight;
	}
	
}

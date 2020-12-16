package game;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public final class Board {
	
	private Board() { }
	
	/**
	 * 
	 * @return initial board
	 */
	public static int[][] getNewBoard() {
		
		//array is initiated by zero with all element
        int[][] board = new int[8][8];
        
        board[3][3] = 2;
        board[3][4] = 1;  
        board[4][3] = 1;
        board[4][4] = 2;
        
        return board;
    }
	
	/**
	 * 
	 * @param board
	 * @param player
	 * @return the disc number of the player 
	 */
	public static int getPlayerDiscNum(int[][] board, int player) {
        int num = 0;
        for (int[] row : board) {
            for (int element : row) {
                if(element == player) num++;
            }
        }
        return num;
    }
	
	/**
	 * 
	 * @param board
	 * @return the total disc number on board
	 */
	public static int getTotalDiscNum(int[][] board) {
        return getPlayerDiscNum(board, 1) + getPlayerDiscNum(board, 2);
    }
	
	/**
	 * This method is used to count stable discs
	 * @return the number of stable discs (
	 */
	public static int getStableDiscNum(int[][] board, int player) {
		
		Set<Point> stableDiscs = new HashSet<>();
		stableDiscs.addAll( getStableDiscsWithCorner(board, player, 0, 0) );
		stableDiscs.addAll( getStableDiscsWithCorner(board, player, 0, 7) );
		stableDiscs.addAll( getStableDiscsWithCorner(board, player, 7, 0) );
		stableDiscs.addAll( getStableDiscsWithCorner(board, player, 7, 7) );
		
		return stableDiscs.size();
	}
	
	private static Set<Point> getStableDiscsWithCorner(int[][] board, int player, int i, int j) {
		
		Set<Point> result = new HashSet<>();
		if (board[i][j] != player) return result;
				
		int iMove = (i == 0) ? 1 : -1;
		int jMove = (j == 0) ? 1 : -1;
	
		result.add(new Point(i, j));
		
		int jFirst = j + jMove;
		while (0 < jFirst && jFirst < 7 && board[i][jFirst] == player) {
			result.add(new Point(i, jFirst));
			jFirst += jMove;
		}
		
		int iFirst = i + iMove;
		while (0 < iFirst && iFirst < 7 && board[iFirst][j] == player) {
			result.add(new Point(iFirst, j));
			iFirst += iMove;
		}
		
		int jSecond = j + jMove;
		if (board[i + iMove][j] == player) {
			while (((0 < jSecond && jSecond < jFirst - 1) || (jFirst + 1 < jSecond && jSecond < 7))
					&& board[i + iMove][jSecond] == player) {
				result.add(new Point(i + iMove, jSecond));
				jSecond += jMove;
			}
		}
		
		int iSecond = i + iMove;
		if (board[i][j + jMove] == player) {
			while (((0 < iSecond && iSecond < iFirst - 1) || (iFirst + 1 < iSecond && iSecond < 7))
					&& board[i + iMove][iSecond] == player) {
				result.add(new Point(iSecond, j + jMove));
				iSecond += iMove;
			}
		}
		
		return result;
	}
	
}

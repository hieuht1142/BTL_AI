package game;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public final class Board {
	
	private Board() { }
	
	/**
	 * Create the initial board
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
	 * Count the disc number of the given player
	 * @param board the board
	 * @param player the player to counting
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
	 * Count total disc number of the board
	 * @param board computed board
	 * @return the total disc number on board
	 */
	public static int getTotalDiscNum(int[][] board) {
        return getPlayerDiscNum(board, 1) + getPlayerDiscNum(board, 2);
    }
	
	/**
	 * Count the number of stable discs for current player.
	 * This method using {@link #getStableDiscsWithCorner(int[][], int, int, int)} for computation
	 *
	 * @param board current playing board
	 * @param player current player
	 * 
	 * @return the number of stable discs
	 */
	public static int getStableDiscNum(int[][] board, int player) {
		
		Set<Point> stableDiscs = new HashSet<>();
		stableDiscs.addAll( getStableDiscsWithCorner(board, player, 0, 0) );
		stableDiscs.addAll( getStableDiscsWithCorner(board, player, 0, 7) );
		stableDiscs.addAll( getStableDiscsWithCorner(board, player, 7, 0) );
		stableDiscs.addAll( getStableDiscsWithCorner(board, player, 7, 7) );
		
		return stableDiscs.size();
	}
	
	/**
	 * Count the number of stable discs with the given corner. <br/>
	 * There are four corners with corresponding (i, j) ( (0, 0), (0, 7), (7, 0), (7, 7) )
	 * 
	 * @param board current playing board
	 * @param player the number player
	 * @param i i index of the corner point
	 * @param j j index of the corner point
	 * 
	 * @return the number of stable discs with the given corner
	 * @see #getStableDiscNum(int[][], int)
	 */
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

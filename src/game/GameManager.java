package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class GameManager {
	
	private GameManager() { }

    public static boolean isGameFinished(int[][] board) {
       return !(hasAnyMoves(board,1) || hasAnyMoves(board,2));
    }

    /*public Point getMove(int[][] before , int[][] after) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (before[i][j] == 0 && after[i][j] != 0) {
                    return new Point(i,j);
                }
            }
        }
        return null;
    }*/

    public static int getWinner(int[][] board) {
    	// game not finished 
        if (!isGameFinished(board)) return -1;
        
        int p1s = Board.getPlayerDiscNum(board, 1);
        int p2s = Board.getPlayerDiscNum(board, 2);

        if (p1s == p2s) { 			//tie              
            return 0;
        } else if (p1s > p2s) { 	//p1 wins  
            return 1;
        } else { 					//p2 wins               
            return 2;
        }        
    }
    
    public enum Phase {
    	BEGIN, MIDDLE, END
    }
    
    public static Phase getGamePhase(int[][] board) {
    	
    	int numDisc = Board.getTotalDiscNum(board);
    	
		if (numDisc <= 20) {
			return Phase.BEGIN;
		} else if (numDisc <= 50) {
			return Phase.MIDDLE;
		} else {
			return Phase.END;
		}
    }

    public static boolean hasAnyMoves(int[][] board, int player) {
        return getAllPossibleMoves(board,player).size() > 0;
    }

    public static ArrayList<Point> getAllPossibleMoves(int[][] board, int player) {
        ArrayList<Point> result = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (canPlay(board, player, i, j)) {
                    result.add(new Point(i, j));
                }
            }
        }
        return result;
    }

    public static List<Point> getReverseDiscs(int[][] board, int player, int i, int j) {
    	
        List<Point> allReverseDiscs = new ArrayList<>();

        allReverseDiscs.addAll( getReverseDiscs(board, player, i, j, -1,  0) ); //up
        allReverseDiscs.addAll( getReverseDiscs(board, player, i, j,  1,  0) ); //down
        allReverseDiscs.addAll( getReverseDiscs(board, player, i, j,  0, -1) ); //left
        allReverseDiscs.addAll( getReverseDiscs(board, player, i, j,  0,  1) ); //right
        allReverseDiscs.addAll( getReverseDiscs(board, player, i, j, -1, -1) ); //up left
        allReverseDiscs.addAll( getReverseDiscs(board, player, i, j, -1,  1) ); //up right
        allReverseDiscs.addAll( getReverseDiscs(board, player, i, j,  1, -1) ); //down left
        allReverseDiscs.addAll( getReverseDiscs(board, player, i, j,  1,  1) ); //down right
        
        return allReverseDiscs;
    }
    
    public static boolean canPlay(int[][] board,int player,int i,int j){
    	return board[i][j] == 0 && getReverseDiscs(board, player, i, j).size() != 0;
    }
    
    private static List<Point> getReverseDiscs(int[][] board, int player, int i, int j, int iMove, int jMove) {
    	
    	int opponent = ((player == 1) ? 2 : 1);
    	List<Point> reverseDiscs = new ArrayList<>(6);
    	
    	int mi = i + iMove;
    	int mj = j + jMove;
    	
    	while ( (0 <= mi && mi <= 7) && (0 <= mj && mj <= 7) && board[mi][mj] == opponent) {
    		reverseDiscs.add(new Point(mi, mj));
    		mi += iMove;
    		mj += jMove;
    	}
    	
    	if ( (0 <= mi && mi <= 7) && (0 <= mj && mj <= 7) && board[mi][mj] == player ) {
    		return reverseDiscs;
    	}
    	return new ArrayList<>(0);
    }

    public static int[][] getNewBoardAfterMove(int[][] board, Point move , int player){
       
    	int[][] newboard = new int[8][8];
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                newboard[k][l] = board[k][l];
            }
        }

        newboard[move.x][move.y] = player;
        
        List<Point> rev = getReverseDiscs(newboard,player,move.x,move.y);
        for(Point pt : rev){
            newboard[pt.x][pt.y] = player;
        }

        return newboard;
    }
    
}

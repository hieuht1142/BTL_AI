package player.ai;

import static player.ai.Evaluator.evalCorner;
import static player.ai.Evaluator.evalDiscDiff;
import static player.ai.Evaluator.evalMobility;
import static player.ai.Evaluator.evalParity;

import game.BoardHelper;

public class DynamicEvaluator implements Evaluator {
	private static final int EARLY_GAME = 19;
	private static final int MID_GAME = 58;
	@SuppressWarnings("unused")
	private static final int LATE_GAME = 60;
	
	@Override
    public int eval(int[][] board , int player){

        //test whether terminal state
        if(BoardHelper.isGameFinished(board)){
            return 1000*evalDiscDiff(board, player);
        }
        
        int sc = BoardHelper.getTotalStoneCount(board);
        
        if (sc <= EARLY_GAME) {
        	return 1000*evalCorner(board,player) + 50*evalMobility(board,player);
        } else if (sc <= MID_GAME){
        	return 1000*evalCorner(board,player) + 20*evalMobility(board,player) +
        			10*evalDiscDiff(board, player) + 100*evalParity(board);
        			
        } else {
        	return 1000*evalCorner(board,player) + 100*evalMobility(board,player) +
        			500*evalDiscDiff(board, player) + 500*evalParity(board);
        }
    }
    
    @Override
    public String toString() {
    	return "Dynamic";
    }

}

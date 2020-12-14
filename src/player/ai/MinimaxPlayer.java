package player.ai;

import game.GamePlayer;

import java.awt.*;

public class MinimaxPlayer extends GamePlayer {

    private int searchDepth;
    private Evaluator evaluator;

    public MinimaxPlayer(int mark, int searchDepth, Evaluator evaluator) {
        super(mark);
        this.searchDepth = searchDepth;
        this.evaluator = evaluator;
    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String playerName() {
        return  evaluator.toString() + " Minimax (Depth " + searchDepth + ")";
    }

    @Override
    public Point play(int[][] board) {
        return MinimaxSearch.minimaxWithABPrunning(board, myMark, searchDepth, evaluator);
    }
    
    //new MinimaxSearch().minimaxWithABPrunning(board,myMark,searchDepth,evaluator);
}

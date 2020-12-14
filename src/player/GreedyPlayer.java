package player;

import game.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GreedyPlayer extends GamePlayer {

    public GreedyPlayer(int mark) {
        super(mark);
    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String playerName() {
        return "Greedy Player";
    }

    @Override
    public Point play(int[][] board) {

        ArrayList<Point> myPossibleMoves = BoardHelper.getAllPossibleMoves(board,myMark);

        Point bestMove = null;
        int bestValue = 0;

        for(Point move : myPossibleMoves) {
            int val = BoardHelper.getReversePoints(board, myMark, move.x,move.y).size();
            if(val > bestValue){
                bestValue = val;
                bestMove = move;
            }
        }

        return bestMove;

    }
    

}
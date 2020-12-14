package player;

import game.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends GamePlayer {

    Random rnd = new Random();

    public RandomPlayer(int mark) {
        super(mark);
    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String playerName() {
        return "Random Player";
    }

    @Override
    public Point play(int[][] board) {
        ArrayList<Point> possibleMoves = BoardHelper.getAllPossibleMoves(board,myMark);
        
        if (possibleMoves.size() == 0) return null;
        return possibleMoves.get(rnd.nextInt(possibleMoves.size()));
    }

}

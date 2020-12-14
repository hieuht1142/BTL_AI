package player.ai;

import static player.ai.Evaluator.evalCorner;
import static player.ai.Evaluator.evalDiscDiff;
import static player.ai.Evaluator.evalMobility;

public class StaticEvaluator implements Evaluator {

	@Override
    public int eval(int[][] board , int player){
        int mob = evalMobility(board,player);
        int sc = evalDiscDiff(board,player);
        return 2*mob + sc + 1000*evalCorner(board,player);
    }

	@Override
    public String toString() {
    	return "Static";
    }

}

package game;

import javax.swing.*;

import player.ai.DynamicEvaluator;
import player.ai.MinimaxPlayer;
import player.ai.StaticEvaluator;

import java.awt.*;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {

    public GameWindow(){
    	GamePlayer player1 = new MinimaxPlayer(1, 4, new DynamicEvaluator());
    	GamePlayer player2 = new MinimaxPlayer(2, 7, new DynamicEvaluator());
        GamePanel gp = new GamePanel(player1, player2);
        
        this.add(gp);
        this.setTitle("Reversi v0.1");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        //this.setSize(500,500);

    }

    public static void main(String[] args) {
        new GameWindow();
    }

}

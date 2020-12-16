package game;

import javax.swing.*;


import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow(GamePlayer player1, GamePlayer player2){
        GamePanel1 gp = new GamePanel1(player1, player2);
        this.add(gp);
        this.setTitle("Reversi v0.1");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        //this.setSize(500,500);

    }

}

package ui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import players.Player;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {

    public GameWindow(Player player1, Player player2){
        GamePanel gp = new GamePanel(player1, player2, this);
        this.add(gp);
        this.setTitle("Othello");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}

package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.GameManager;
import players.HumanPlayer;
import players.Player;

public class GamePanel extends JPanel implements GameEngine {

	int[][] board;
	
	int turn = 1;
	
	BoardCell[][] cells;
	
	Player player1;
	Player player2;
	
	JPanel reversiBoard;
	JPanel leftSide;
	JPanel rightSide;
	
	Integer totalScore1;
	Integer totalScore2;
	
	JLabel score1;
	JLabel score2;
	
	Timer player1HandlerTimer;
    Timer player2HandlerTimer;
	
	@Override
	public int getBoardValue(int i, int j) {
		return board[i][j];
	}

	@Override
	public void setBoardValue(int i, int j, int value) {
		board[i][j] = value;
	}

	@Override
	public void handleClick(int i, int j) {
		if(awaitForClick && GameManager.canPlay(board, turn, i, j)) {
			System.out.println("User Played in : "+ i + " , " + j);
			
			//update board
            board = GameManager.getNewBoardAfterMove(board,new Point(i,j),turn);

            //advance turn
            turn = (turn == 1) ? 2 : 1;

            repaint();

            awaitForClick = false;

            //callback
            manageTurn();
		}
		
	}
	
	public void handleAI(Player ai){
        Point aiPlayPoint = ai.play(board);
        int i = aiPlayPoint.x;
        int j = aiPlayPoint.y;
        if(!GameManager.canPlay(board,ai.getPlayerNumber(),i,j)) System.err.println("FATAL : AI Invalid Move !");
        System.out.println(ai.toString() + " Played in : "+ i + " , " + j);

        //update board
        board = GameManager.getNewBoardAfterMove(board,aiPlayPoint,turn);

        //advance turn
        turn = (turn == 1) ? 2 : 1;

        repaint();
    }
		
	public GamePanel(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	
    	this.setBackground(new Color(189, 189, 189));
    	this.setLayout(new BorderLayout());
    	
    	reversiBoard = new JPanel();
    	reversiBoard.setLayout(new GridLayout(8, 8));
    	reversiBoard.setPreferredSize(new Dimension(800, 800));
    	reversiBoard.setBackground(new Color(41, 100, 59));
    	
    	resetBoard();
    	
    	cells = new BoardCell[8][8];
    	
    	for(int i = 0; i < 8; i++) {
    		for(int j = 0; j < 8; j++) {
    			cells[i][j] = new BoardCell(this,reversiBoard,i,j);
                reversiBoard.add(cells[i][j]);
    		}
    	}
    	leftSide = new JPanel(); leftSide.setPreferredSize(new Dimension(150, 0));
    	leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
    	
    	rightSide = new JPanel(); rightSide.setPreferredSize(new Dimension(150, 0));
    	rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
    	
    	score1 = new JLabel("Score1: "); score1.setFont(new java.awt.Font("Arial", 1, 100));
    	score2 = new JLabel("Score2: "); score2.setFont(new java.awt.Font("Arial", 1, 100));
    	
    	
    	JPanel player1Bar = new Oval(1);	
    	JPanel player2Bar = new Oval(2); 
    	
    	leftSide.add(new JLabel(player1.toString())); leftSide.add(player1Bar); leftSide.add(score1);
    	rightSide.add(new JLabel(player2.toString())); rightSide.add(player2Bar); rightSide.add(score2);
    	
    	
    	updateBoardInfo();   	
    	
    	this.add(leftSide, BorderLayout.WEST);
    	this.add(reversiBoard, BorderLayout.CENTER);
    	this.add(rightSide, BorderLayout.EAST);
    	
    	player1HandlerTimer = new Timer(1000,(ActionEvent e) -> {
            handleAI(player1);
            player1HandlerTimer.stop();
            manageTurn();
        });

        player2HandlerTimer = new Timer(1000,(ActionEvent e) -> {
            handleAI(player2);
            player2HandlerTimer.stop();
            manageTurn();
        });

        manageTurn();
    }
	
	public boolean awaitForClick = false;
	public void manageTurn() {
		if(GameManager.hasAnyMoves(board, 1) || GameManager.hasAnyMoves(board, 2)) {
			updateBoardInfo();
			if(turn == 1) {
				if(GameManager.hasAnyMoves(board, 1)) {
					if(player1.isHumanPlayer()) {
						awaitForClick = true;
					}
					else {
						player1HandlerTimer.start();
					}
				}else {
					System.out.println("player 1 has no legal moves");
					turn = 2;
					manageTurn();
				}
			}else {
				if (GameManager.hasAnyMoves(board, 2)) {
					if(player2.isHumanPlayer()) {
						awaitForClick = true;
					} else {
						player2HandlerTimer.restart();
					}
				} else {
					System.out.println("Player 2 has no legal moves");
					turn = 1;
					manageTurn();
				}
			}
		}
		
		else {
			System.out.println("Game finished !");
			int winner = GameManager.getWinner(board);
			if(winner == 1) System.out.println("Player 1 won");
			else System.out.println("Player 2 won");
			
			String message = "";
			if(winner == 1) message = player1.toString() + " win";
			else message = player2.toString() + " win";
			
			JOptionPane.showMessageDialog(this, message);
		}
	}
	
	public void resetBoard() {
		board = new int[8][8];
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = 0;
			}
		}
		
		setBoardValue(3,3,2);
        setBoardValue(3,4,1);
        setBoardValue(4,3,1);
        setBoardValue(4,4,2);
	}

	public void updateBoardInfo() {
		totalScore1 = 0;
		totalScore2 = 0;
		
		for(int i = 0 ; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if (board[i][j] == 1) totalScore1++;
				if (board[i][j] == 2) totalScore2++;
				
				if (GameManager.canPlay(board,turn,i,j)) {
                    cells[i][j].highlight = 1;
                } else {
                    cells[i][j].highlight = 0;
                }
			}
		}
		
		score1.setText(totalScore1.toString());
		score2.setText(totalScore2.toString());
	}
	
}

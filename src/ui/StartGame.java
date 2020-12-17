package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import evaluators.MobilityEvaluator;
import evaluators.WeightTableEvaluator;
import evaluators.AdvancedEvaluator;
import players.HumanPlayer;
import players.MinimaxPlayer;
import players.Player;
import players.RandomPlayer;

@SuppressWarnings("serial")
public class StartGame extends JFrame {

    private Player player1;
    private Player player2;
    
    public StartGame() {
        initComponents();
    }

                
    private void initComponents() {

    	jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        nameText = new JTextField();
        LevelCombo = new JComboBox<>();
        PlayButton = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel2.setFont(new Font("Times New Roman", 1, 30)); // NOI18N
        jLabel2.setText("Name");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(150, 300, 140, 51);

        nameText.setFont(new Font("Times New Roman", 0, 26)); // NOI18N
        nameText.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        getContentPane().add(nameText);
        nameText.setBounds(320, 300, 169, 51);

        jLabel1.setFont(new Font("Times New Roman", 1, 30)); // NOI18N
        jLabel1.setText("Level");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(180, 390, 117, 58);

        LevelCombo.setBackground(new java.awt.Color(240, 240, 240));
        LevelCombo.setFont(new Font("Times New Roman", 0, 26)); // NOI18N
        LevelCombo.setModel(new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        getContentPane().add(LevelCombo);
        LevelCombo.setBounds(320, 390, 100, 58);

        PlayButton.setFont(new Font("Times New Roman", 1, 30)); // NOI18N
        PlayButton.setText("Play");
        PlayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                PlayButtonActionPerformed(evt);
            }
        });
        getContentPane().add(PlayButton);
        PlayButton.setBounds(500, 450, 150, 50);

        jLabel3.setIcon(new ImageIcon(getClass().getResource("/Image/Othello1.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 10, 1200, 630);

        pack();
    }// </editor-fold>                        

    private void PlayButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        String name = nameText.getText();
        String level = LevelCombo.getSelectedItem().toString();
        
        player1 = new MinimaxPlayer(1, 5, new AdvancedEvaluator());
        switch(level) {
        	default :
        		player2 = new MinimaxPlayer(2, 4, new MobilityEvaluator());
        		break;
        	
        	case "2" :
        		player2 = new MinimaxPlayer(2, 5, new MobilityEvaluator());
        		break;
        		
        	case "3" :
        		player2 = new MinimaxPlayer(2, 5, new WeightTableEvaluator());
        		break;
        	
        	case "4" :
        		player2 = new MinimaxPlayer(2, 6, new WeightTableEvaluator());
        		break;
        		
        }
        
        new GameWindow(player1, player2);
        dispose();
    }                                          

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StartGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(StartGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(StartGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(StartGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartGame().setVisible(true);
            }
        });
    }

                         
    private javax.swing.JComboBox<String> LevelCombo;
    private javax.swing.JButton PlayButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField nameText;                 
}

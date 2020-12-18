package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import evaluators.AdvancedEvaluator;
import evaluators.MobilityEvaluator;
import evaluators.WeightTableEvaluator;
import players.HumanPlayer;
import players.MinimaxPlayer;
import players.Player;

@SuppressWarnings("serial")
public class StartGame extends javax.swing.JFrame {
	
	private Player player1;
	private Player player2;
	
	private JComboBox<String> LevelCombo;
    private JButton PlayButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JTextField nameText;
    
    public StartGame() {
        initComponents();
    }
    
    private void initComponents() {

        jLabel2 = new JLabel();
        nameText = new JTextField();
        jLabel1 = new JLabel();
        LevelCombo = new JComboBox<>();
        PlayButton = new JButton();
        jLabel3 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new Font("Times New Roman", 1, 30)); // NOI18N
        jLabel2.setText("Name");

        nameText.setFont(new Font("Times New Roman", 0, 26)); // NOI18N
        nameText.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new Font("Times New Roman", 1, 30)); // NOI18N
        jLabel1.setText("Level");

        LevelCombo.setBackground(new Color(240, 240, 240));
        LevelCombo.setFont(new Font("Times New Roman", 0, 26)); // NOI18N
        LevelCombo.setModel(new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5"}));

        PlayButton.setFont(new Font("Times New Roman", 1, 30)); // NOI18N
        PlayButton.setText("Play");
        PlayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                PlayButtonActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new ImageIcon(getClass().getResource("/Image/Othello.png"))); // NOI18N

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(150, 150, 150)
						.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addGap(30, 30, 30)
						.addComponent(nameText, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createSequentialGroup().addGap(180, 180, 180)
						.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(23, 23, 23)
						.addComponent(LevelCombo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createSequentialGroup().addGap(500, 500, 500).addComponent(PlayButton,
						GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addComponent(jLabel3));
        
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(300, 300, 300)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(nameText, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
						.addGap(39, 39, 39)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(LevelCombo, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addGap(2, 2, 2)
						.addComponent(PlayButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel3,
						GroupLayout.PREFERRED_SIZE, 630, GroupLayout.PREFERRED_SIZE)));

        pack();
    }// </editor-fold>                        

    private void PlayButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	String name = nameText.getText();
        String level = LevelCombo.getSelectedItem().toString();
        
        player1 = new HumanPlayer(1, name);
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
        	case "5" :
        		player2 = new MinimaxPlayer(2, 6, new AdvancedEvaluator());
        		
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
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartGame().setVisible(true);
            }
        });
    }
                      
}

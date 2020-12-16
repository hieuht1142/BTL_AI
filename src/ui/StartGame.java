package ui;

import evaluators.SecondEvaluator;
import evaluators.FirstEvaluator;
import players.HumanPlayer;
import players.MinimaxPlayer;
import players.Player;

public class StartGame extends javax.swing.JFrame {

    private Player player1;
    private Player player2;
    
    public StartGame() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")            
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        LevelCombo = new javax.swing.JComboBox<>();
        PlayButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel2.setText("Name");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(150, 300, 140, 51);

        nameText.setFont(new java.awt.Font("Times New Roman", 0, 26)); // NOI18N
        nameText.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(nameText);
        nameText.setBounds(320, 300, 169, 51);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel1.setText("Level");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(180, 390, 117, 58);

        LevelCombo.setBackground(new java.awt.Color(240, 240, 240));
        LevelCombo.setFont(new java.awt.Font("Times New Roman", 0, 26)); // NOI18N
        LevelCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        getContentPane().add(LevelCombo);
        LevelCombo.setBounds(320, 390, 100, 58);

        PlayButton.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        PlayButton.setText("Play");
        PlayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayButtonActionPerformed(evt);
            }
        });
        getContentPane().add(PlayButton);
        PlayButton.setBounds(500, 450, 150, 50);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Othello1.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 10, 1200, 630);

        pack();
    }// </editor-fold>                        

    private void PlayButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        String name = nameText.getText();
        String level = LevelCombo.getSelectedItem().toString();
        
        player1 = new HumanPlayer(1, name);
        switch(level) {
        	default :
        		player2 = new MinimaxPlayer(2, 4, new FirstEvaluator());
        		break;
        	
        	case "2" :
        		player2 = new MinimaxPlayer(2, 5, new FirstEvaluator());
        		break;
        		
        	case "3" :
        		player2 = new MinimaxPlayer(2, 5, new SecondEvaluator());
        		break;
        	
        	case "4" :
        		player2 = new MinimaxPlayer(2, 6, new SecondEvaluator());
        		break;
        		
        }
        
        new GameWindow(player1, player2);
        dispose();
    }                                          

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

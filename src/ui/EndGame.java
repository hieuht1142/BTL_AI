
package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import players.Player;

public class EndGame extends javax.swing.JPanel {
	private Player player1;
	private Player player2;
	private JFrame parentFrame;
	
    public EndGame(Player player1, Player player2, String thongBao, JFrame parent) {
        initComponents();
        thongBaoLb.setText(thongBao);
        this.player1 = player1;
        this.player2 = player2;
        parentFrame = parent;
    }

    
    @SuppressWarnings("unchecked")                        
    private void initComponents() {

        thongBaoLb = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        yesBtn = new javax.swing.JButton();
        noBtn = new javax.swing.JButton();

        thongBaoLb.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel2.setText("PLAY AGAIN?");

        yesBtn.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        yesBtn.setText("YES");
        yesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesBtnActionPerformed(evt);
            }
        });

        noBtn.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        noBtn.setText("NO");
        noBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(yesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(194, 194, 194)
                                .addComponent(noBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(thongBaoLb, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(197, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(thongBaoLb, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(202, 202, 202))
        );
    }// </editor-fold>                        

    private void yesBtnActionPerformed(java.awt.event.ActionEvent evt) {                                       
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new GamePanel(player1, player2, parentFrame));
        this.validate();
        this.repaint();
    }                                      

    private void noBtnActionPerformed(java.awt.event.ActionEvent evt) {                                      
        parentFrame.dispose();
    }                                     


    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton noBtn;
    private javax.swing.JLabel thongBaoLb;
    private javax.swing.JButton yesBtn;
    // End of variables declaration                   
}

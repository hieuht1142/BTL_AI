
package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import players.Player;

@SuppressWarnings("serial")
public class EndGame extends JPanel {

	private Player player1;
	private Player player2;
    private JFrame parentFrame;
    
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JButton noBtn;
    private JLabel thongBaoLb;
    private JButton yesBtn; 
    
    public EndGame(JFrame parentFrame, Player player1, Player player2, String message) {
        initComponents();
        this.parentFrame = parentFrame;
        this.player1 = player1;
        this.player2 = player2;
        thongBaoLb.setText(message);
    };

    private void initComponents() {

        yesBtn = new JButton();
        noBtn = new JButton();
        jPanel1 = new JPanel();
        thongBaoLb = new JLabel();
        jLabel2 = new JLabel();

        yesBtn.setFont(new Font("Times New Roman", 1, 26)); // NOI18N
        yesBtn.setText("YES");
        yesBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                yesBtnActionPerformed(evt);
            }
        });

        noBtn.setFont(new Font("Times New Roman", 1, 26)); // NOI18N
        noBtn.setText("NO");
        noBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                noBtnActionPerformed(evt);
            }
        });

        thongBaoLb.setFont(new Font("Times New Roman", 1, 26)); // NOI18N

        jLabel2.setFont(new Font("Times New Roman", 1, 28)); // NOI18N
        jLabel2.setText("PLAY AGAIN?");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(thongBaoLb, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(172, 172, 172)
						.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(176, Short.MAX_VALUE)));
        
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(thongBaoLb, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
						.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(336, 336, 336)
								.addComponent(yesBtn, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
								.addGap(137, 137, 137)
								.addComponent(noBtn, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(178, 178, 178).addComponent(jPanel1,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(214, Short.MAX_VALUE))
        );
		
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(45, 45, 45)
				.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(118, 118, 118)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(noBtn, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(yesBtn, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(187, Short.MAX_VALUE)));
		
    }// </editor-fold>                        

    private void yesBtnActionPerformed(ActionEvent evt) {                                       

    	this.removeAll();
		this.setLayout(new BorderLayout());
		this.add(new GamePanel(player1, player2, parentFrame));
		this.validate();
		this.repaint();

    }                                      

    private void noBtnActionPerformed(ActionEvent evt) {                                      
    	parentFrame.dispose();
    }                                     
                 
}

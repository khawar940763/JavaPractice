import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class HomePageJFrame extends JFrame {
	HomePageJPanel homePageJPanel;
	JLabel scoreLabel;
	public HomePageJFrame(){
		this.setLayout(new BorderLayout());
		
		scoreLabel = new JLabel("Score: 0", JLabel.CENTER );
		scoreLabel.setPreferredSize(new Dimension(900 , 50));
		scoreLabel.setFont(new Font("Serif", Font.BOLD, 25));
		homePageJPanel = new HomePageJPanel(scoreLabel);
		this.add(homePageJPanel, BorderLayout.CENTER);
		this.add(scoreLabel, BorderLayout.SOUTH);
		this.setUndecorated(true); // remove the upper bar of the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // turing all the operation of the JFrame on being closed though the close window button
        this.setSize(900 , 550); // setting the size of the JFrame width: 300px and Height: 300px
        this.setName("Snake Game"); // setting the title of the JFrame
        this.setLocationRelativeTo(null); // position the JFrame to the center of the screen
        this.setVisible(true); // making he JFrame visible as it is hidding by default
        this.addKeyListener(new MyKeyAdapter());
	}
public class MyKeyAdapter extends KeyAdapter{ 
		
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if(homePageJPanel.GAME_DIRECTION != 'D' ) {
					homePageJPanel.GAME_DIRECTION = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(homePageJPanel.GAME_DIRECTION != 'U' ) {
					homePageJPanel.GAME_DIRECTION = 'D';
				}
				break;
			case KeyEvent.VK_LEFT:
				if(homePageJPanel.GAME_DIRECTION != 'R' ) {
					homePageJPanel.GAME_DIRECTION = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(homePageJPanel.GAME_DIRECTION != 'L' ) {
					homePageJPanel.GAME_DIRECTION = 'R';
				}
				break;
			case KeyEvent.VK_SPACE:
				if(homePageJPanel.MOVING == false) {
					homePageJPanel.MOVING = true;
					homePageJPanel.timer.start();
				}else {
					homePageJPanel.MOVING = false;
					homePageJPanel.timer.stop();
				}
				break;
			}
		}
	}

}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

import javax.swing.JPanel;

public class HomePageJPanel extends JPanel implements ActionListener{
	Timer timer;
	Random random;
	final int SCREEN_WIDTH = 900; 
	final int SCREEN_HEIGHT = 500;
	final int UNIT_SIZE = 25;
	final int TOTAL_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	int APPLEX = 0;
	int APPLEY = 0;
	int[] SNAKEX = new int[TOTAL_UNITS];
	int[] SNAKEY = new int[TOTAL_UNITS];
	int SNAKE_PARTS = 6;
	char GAME_DIRECTION = 'R';
	int APPLES_EATEN = 0;
	boolean MOVING = false;
	JLabel scoreLabel;
	HomePageJPanel(JLabel lblScore){
		scoreLabel = lblScore;
		this.setPreferredSize(new Dimension(SCREEN_WIDTH , SCREEN_HEIGHT));
		this.setBackground(new Color(103, 76, 196));
		timer = new Timer(100 , this);
		timer.start();
		random = new Random();
//		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		newApple();
		move();
		MOVING = true;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		draw(g2d);
	}
	
	public void draw(Graphics2D g2d) {
		
		if(MOVING) {
		g2d.setColor(Color.white);
//		for(int i  = 0; i < TOTAL_UNITS ; i++) {
//			g2d.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
//			g2d.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
//		}
		g2d.setColor(Color.black);
		g2d.fillOval(APPLEX, APPLEY, UNIT_SIZE, UNIT_SIZE);
		
			for(int i = 0; i < SNAKE_PARTS; i++) {
				if(i==0) {
					g2d.setColor(Color.black);
					g2d.fillRect(SNAKEX[i], SNAKEY[i], UNIT_SIZE, UNIT_SIZE);
				}else {
					g2d.setColor(Color.white);
					g2d.fillRect(SNAKEX[i], SNAKEY[i], UNIT_SIZE, UNIT_SIZE);					
				}
			}
		}else {
			gameOver(g2d);
		}
				
	}
	
	public void newApple() {
		APPLEX  = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		APPLEY  = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
	}
	
	public void move() {
		for(int i = SNAKE_PARTS; i >  0; i--) {
			SNAKEX[i] = SNAKEX[i-1];
			SNAKEY[i] = SNAKEY[i-1];
		}
		switch (GAME_DIRECTION){
		case 'R':
			SNAKEX[0] = SNAKEX[0] + UNIT_SIZE;
			break;
		case 'L':
			SNAKEX[0] = SNAKEX[0] - UNIT_SIZE;
			break;
		case 'U':
			SNAKEY[0] = SNAKEY[0] - UNIT_SIZE;
			break;
		case 'D':
			SNAKEY[0] = SNAKEY[0] + UNIT_SIZE;
			break;
		}
	}
	
	public void checkCollision() {
		for(int i = SNAKE_PARTS; i > 0 ; i-- ) {
			if((SNAKEX[0] == SNAKEX[i]) && (SNAKEY[0] == SNAKEY[i])) {
				MOVING = false;
				break;
			}
			
		}
		if( SNAKEX[0] > SCREEN_WIDTH ||  SNAKEX[0] < 0 || SNAKEY[0] > SCREEN_HEIGHT || SNAKEY[0] < 0) {
			MOVING = false;
		}
	}
	
	public void gameOver(Graphics2D g2d) {
		g2d.setColor(Color.green);
		g2d.setFont(new Font("Serif", Font.PLAIN , 40));
		FontMetrics metrics = getFontMetrics(g2d.getFont());
		g2d.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2 , SCREEN_HEIGHT / 2);
		g2d.setFont(new Font("Serif", Font.PLAIN , 20));
		g2d.setColor(Color.white);
//		g2d.drawString("Score you made:"+APPLES_EATEN*10, (SCREEN_WIDTH - metrics.stringWidth("Score you made:"+APPLES_EATEN*10))/2 , g2d.getFont().getSize());
		timer.stop();
	}

	public void checkApple() {
		if((SNAKEX[0] == APPLEX) && (SNAKEY[0] == APPLEY)) {
			SNAKE_PARTS++;
			APPLES_EATEN++;
			newApple();			
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(MOVING) {
			move();
			checkApple();
			checkCollision();	
			repaint();
			scoreLabel.setText("Score: "+APPLES_EATEN*10);
		}
	}
	
}

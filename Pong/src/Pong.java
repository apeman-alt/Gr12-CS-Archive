//Recreation of PONG
//created by Josh Muszka // apeman
//October 20, 2021


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pong implements MouseMotionListener {

	/** GLOBAL VARIABLES **/
	
	int panW = 800; //screen width
	int panH = 600; //screen height
	GamePanel panel = new GamePanel(); //panel
	int my = -50; //mouse's y-position
	
	//game stuff
	int playerScore = 0; int opponentScore = 0;
	
	Rectangle ball = new Rectangle((panW/2)-5,(panH/2)-5,10,10);
	Rectangle playerPaddle = new Rectangle(0,(panH/2)-40,10,80);
	Rectangle opponentPaddle = new Rectangle(panW-10,(panH/2)-40,10,80);
	
	int ballSpeedX = -3; int ballSpeedY = ballSpeedX;
	int opponentPaddleSpeed = 0;
	
	JLabel lblPlayerScore = new JLabel(""+playerScore);
	
	JLabel lblOpponentScore = new JLabel(""+opponentScore);
	
	
	public static void main (String[] args) {
		
		new Pong();
		
	}
	
	Pong() {
		
		
		panel.addMouseMotionListener(this);
		setup();
		
		while (playerScore < 5 && opponentScore < 5){
				
				panel.repaint();
				
				moveBall();
				movePlayerPaddle();
				//devAI();
				opponentAI();
				score();
				
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
			if (playerScore > opponentScore) {
				
				lblPlayerScore.setText("Player 1");
				lblOpponentScore.setText(" Wins!");
				
			}
			if (opponentScore > playerScore) {
				lblPlayerScore.setText("Computer");
				lblOpponentScore.setText(" Wins!");
			}
		
	}
	
	public void setup() {
		
		JFrame window = new JFrame ("Pong");
		window.add(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		lblPlayerScore.setForeground(Color.white);
		lblOpponentScore.setForeground(Color.white);
		
		panel.add(lblPlayerScore);
		panel.add(lblOpponentScore);
		
		
		window.pack();
		window.setVisible(true);
	}
	
	
	public void moveBall() {
		
		////////TODO  CHANGING BALL SPEEDS
		
		//bounce off ceiling and floor
		if (ball.y <= 0) {
			ballSpeedY *= -1;
			
		}
		if (ball.y + ball.height >= panH) {
			ballSpeedY *= -1;
		}
		
		//bounce off player paddle
		if (ball.x <= playerPaddle.x + playerPaddle.width) {
			
			if (ball.y + (ball.height /2) >= playerPaddle.y - 10 && ball.y + (ball.height /2) < (playerPaddle.y + (playerPaddle.height /5)*1)) {
				ballSpeedX = 5; //EDGE, 3
				
				if (ballSpeedY > 0) {
					ballSpeedY = 5;
				}
				if (ballSpeedY < 0) {
					ballSpeedY = -5;
				}
				
			}
			
			if (ball.y + (ball.height /2) >= (playerPaddle.y + (playerPaddle.height /5)*1) && ball.y + (ball.height /2) < (playerPaddle.y + (playerPaddle.height/5)*2)) {
				ballSpeedX = 4; //INNER, 2
				
				if (ballSpeedY > 0) {
					ballSpeedY = 4;
				}
				if (ballSpeedY < 0) {
					ballSpeedY = -4;
				}
			}
			
			if (ball.y + (ball.height /2) >= (playerPaddle.y + (playerPaddle.height/5)*2) && ball.y + (ball.height /2) <= (playerPaddle.y + (playerPaddle.height/5)*3))  {
				ballSpeedX = 3; //CENTRE, 1
				
				if (ballSpeedY > 0) {
					ballSpeedY = 3;
				}
				if (ballSpeedY < 0) {
					ballSpeedY = -3;
				}
			}

			if (ball.y + (ball.height /2) > (playerPaddle.y + (playerPaddle.height /5)*3) && ball.y + (ball.height /2) <= (playerPaddle.y + (playerPaddle.height/5)*4)) {
				ballSpeedX = 4; //INNER, 2
				
				if (ballSpeedY > 0) {
					ballSpeedY = 4;
				}
				if (ballSpeedY < 0) {
					ballSpeedY = -4;
				}
			}
			
			if (ball.y + (ball.height /2) > (playerPaddle.y + (playerPaddle.height /5)*4) && ball.y + (ball.height /2) <= (playerPaddle.y + (playerPaddle.height)) + 10) {
				ballSpeedX = 5; //EDGE, 3
				
				if (ballSpeedY > 0) {
					ballSpeedY = 5;
				}
				if (ballSpeedY < 0) {
					ballSpeedY = -5;
				}
			}
			
		}
		
		//bounce off opponent paddle
		if (ball.x >= opponentPaddle.x) {
			
			if (ball.y + (ball.height /2) >= opponentPaddle.y - 10 && ball.y + (ball.height /2) < (opponentPaddle.y + (opponentPaddle.height /5)*1)) {
				ballSpeedX = -5; //EDGE, 3
				
				if (ballSpeedY > 0) {
					ballSpeedY = 5;
				}
				if (ballSpeedY < 0) {
					ballSpeedY = -5;
				}
				
			}
			
			if (ball.y + (ball.height /2) >= (opponentPaddle.y + (opponentPaddle.height /5)*1) && ball.y + (ball.height /2) < (opponentPaddle.y + (opponentPaddle.height/5)*2)) {
				ballSpeedX = -4; //INNER, 2
				
				if (ballSpeedY > 0) {
					ballSpeedY = 4;
				}
				if (ballSpeedY < 0) {
					ballSpeedY = -4;
				}
			}
			
			if (ball.y + (ball.height /2) >= (opponentPaddle.y + (opponentPaddle.height/5)*2) && ball.y + (ball.height /2) <= (opponentPaddle.y + (opponentPaddle.height/5)*3))  {
				ballSpeedX = -3; //CENTRE, 1
				
				if (ballSpeedY > 0) {
					ballSpeedY = 3;
				}
				if (ballSpeedY < 0) {
					ballSpeedY = -3;
				}
			}

			if (ball.y + (ball.height /2) > (opponentPaddle.y + (opponentPaddle.height /5)*3) && ball.y + (ball.height /2) <= (opponentPaddle.y + (opponentPaddle.height/5)*4)) {
				ballSpeedX = -4; //INNER, 2
				
				if (ballSpeedY > 0) {
					ballSpeedY = 4;
				}
				if (ballSpeedY < 0) {
					ballSpeedY = -4;
				}
			}
			
			if (ball.y + (ball.height /2) > (opponentPaddle.y + (opponentPaddle.height /5)*4) && ball.y + (ball.height /2) <= (opponentPaddle.y + (opponentPaddle.height)) + 10) {
				ballSpeedX = -5; //EDGE, 3
				
				if (ballSpeedY > 0) {
					ballSpeedY = 5;
				}
				if (ballSpeedY < 0) {
					ballSpeedY = -5;
				}
			}
			
		}
		
		
		
		
		
		ball.x += ballSpeedX;
		ball.y += ballSpeedY;
	}
	
	public void movePlayerPaddle() {
		
		playerPaddle.y = my - 40;
		
	}
	
	public void opponentAI() {
		
		//go other way if it hits top
		if (opponentPaddle.y <= 0) {
			opponentPaddleSpeed = 4;
		}
		
		//go other way if it hits bottom
		if (opponentPaddle.y + opponentPaddle.width >= panH) {
			opponentPaddleSpeed = -4;
		}
			
		//go down if ball is below
		if (ball.y + (ball.height /2) + 40 > opponentPaddle.y + (opponentPaddle.height / 2)) {
			opponentPaddleSpeed = 4;
		}
		
		//go up if ball is above
		if (ball.y + (ball.height /2) - 40 < opponentPaddle.y + (opponentPaddle.height /2)) {
			opponentPaddleSpeed = -4;
		}
		
		opponentPaddle.y += opponentPaddleSpeed;
		
	}
	
	//SECOND AI FOR D PURPOSES
		public void devAI() {
			
			//go other way if it hits top
			if (playerPaddle.y <= 0) {
				opponentPaddleSpeed = 4;
			}
			
			//go other way if it hits bottom
			if (playerPaddle.y + playerPaddle.height >= panH) {
				opponentPaddleSpeed = -4;
			}
				
			//go down if ball is below
			if (ball.y + (ball.height /2) + 50 > playerPaddle.y + (playerPaddle.height / 2)) {
				opponentPaddleSpeed = 4;
			}
			
			//go up if ball is above
			if (ball.y + (ball.height /2) - 20 < playerPaddle.y + (playerPaddle.height /2)) {
				opponentPaddleSpeed = -4;
			}
			
			playerPaddle.y += opponentPaddleSpeed;
			
		}
	
	public void score() {
		
		//if opponent scores
		if (ball.x + ball.width < 0) {
			
			opponentScore++;
			lblOpponentScore.setText(""+opponentScore);
			
			
			ball.x = panW/2 - ball.width/2;
			ball.y = panH/2 - ball.height/2;
			ballSpeedY = ballSpeedX;
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ballSpeedX = -4;
			ballSpeedY = ballSpeedX;
			opponentPaddle.y = (panH/2) - (opponentPaddle.height/2);
		}
		if (ball.x > panW) {
			
			playerScore++;
			lblPlayerScore.setText(""+playerScore);
			
			ball.x = panW/2 - ball.width/2;
			ball.y = panH/2 - ball.height/2;
			ballSpeedX = 0;
			ballSpeedY = ballSpeedX;
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ballSpeedX = -4;
			ballSpeedY = ballSpeedX;
			opponentPaddle.y = (panH/2) - (opponentPaddle.height/2);
		}
	}
	
	class GamePanel extends JPanel {
		
		//constructor since the "GamePanel" is basically an object
		GamePanel() {
		this.setPreferredSize (new Dimension(panW,panH));
		this.setBackground(Color.black);
		}
		
		@Override
		//do all drawing stuff here
		public void paintComponent (Graphics g) {
			super.paintComponent(g);
			
			g.setColor(Color.white);
			
			g.fillRect(ball.x, ball.y, ball.width, ball.height);
			g.fillRect(playerPaddle.x, playerPaddle.y, playerPaddle.width, playerPaddle.height);
			g.fillRect(opponentPaddle.x, opponentPaddle.y, opponentPaddle.width, opponentPaddle.height);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		my = e.getY();
		
	}
	
	
	
	
	
	
	
	//useless
	@Override
	public void mouseDragged(MouseEvent e) {
	}
}

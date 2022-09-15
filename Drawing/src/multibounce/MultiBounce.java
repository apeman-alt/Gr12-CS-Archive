package multibounce;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MultiBounce implements ActionListener{

	public static void main (String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MultiBounce();
			}
		});
	}

	JFrame window;
	GamePanel panel;
	int panW = 600;
	int panH = 600;

	Color bgColour = Color.black;
	Color ballColours[] = {
			Color.red,
			Color.orange,
			Color.yellow,
			Color.green,
			Color.blue,
			Color.pink,
			Color.cyan,
			Color.darkGray,
			Color.magenta,
			Color.white
	};

	ArrayList<Ball> ballList = new ArrayList<>(); //balls
	Paddle playerPaddle;
	Timer timer;

	int mx; //mouse position x
	int roundCount = 1;
	int score = 0;
	int prevScore = score;

	boolean alive = true;


	MultiBounce() {

		window = new JFrame("Click to begin (or pause)!");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		panel = new GamePanel();
		window.add(panel);
		panel.addMouseListener(new StartPauseAL());
		panel.addMouseMotionListener(new PaddleMovementAL());


		setup();

		timer = new Timer(8, this);

		window.pack();
		window.setVisible(true);

	}

	void setup() {

		ballList.add(new Ball(panW/2 - 10, panH/2 - 10,20,3,-3,Color.white));
		//add first ball
		playerPaddle = new Paddle(panW/2 - 40, panH-10, 160, 10);


	}

	void moveBall() {

		int i = 0;
		//collision
		for (Ball b : ballList) {

			//right
			if (b.xx + b.diameter > panW) {
				b.speedX *= -1;
				changeBallColour();
			}

			//bottom
			//			if (b.yy + b.diameter > panH) {
			//				b.speedY *= -1;
			//			}

			//left
			if (b.xx <= 0) {
				b.speedX *= -1;
				changeBallColour();
			}

			//top
			if (b.yy <= 0) {
				b.speedY *= -1;
				changeBallColour();
			}

			//paddle
			if (b.xx + b.diameter >= playerPaddle.paddleXX && b.xx <= playerPaddle.paddleXX + playerPaddle.paddleWidth) {

				if (b.yy + b.diameter >= panH - playerPaddle.paddleHeight && b.yy + b.diameter <= panH) {
					b.speedY *= -1;
					score++;
					changeBallColour();
				}

			}
		}



		for (Ball b : ballList) {
			b.xx += b.speedX;
			b.yy += b.speedY;
		}

	}

	void movePaddle() {

		playerPaddle.paddleXX = mx - (playerPaddle.paddleWidth/2);

	}

	void roundCount() {

		if (score == prevScore + (5*roundCount)) {
			roundCount++;
			if (roundCount%3 == 0) {
				for (Ball b : ballList) {
					if (b.speedX > 0) {
						b.speedX++;
					}
					if (b.speedX < 0) {
						b.speedX--;
					}
					if (b.speedY > 0) {
						b.speedY++;
					}
					if (b.speedY < 0) {
						b.speedY--;
					}
				}
			}

			addBalls();
		}


		window.setTitle("Round " + (roundCount));

	}

	void addBalls() {

		if (roundCount > 1) ballList.add(new Ball(panW/2 - 10, panH/2 - 10,20,3,-3,Color.white));

	}

	void changeBallColour() {
		Color temp;
		
		for (Ball b : ballList) {
			temp = b.ballColour;

			while (temp.equals(b.ballColour)) {
				b.ballColour = ballColours[(int)(Math.random()*ballColours.length)];
			}
		}
		


	}



	class GamePanel extends JPanel {

		GamePanel() {
			this.setPreferredSize (new Dimension(panW,panH));
			this.setBackground(bgColour);
		}


		public void paintComponent (Graphics g) {
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON); //antialiasing

			//score
			g.setColor(Color.white);
			g.drawString("SCORE: " + score, panW/2 - 27, 10);

			//draw paddle
			g2.setColor(Color.white);
			g2.fillRect(playerPaddle.paddleXX, playerPaddle.paddleYY, playerPaddle.paddleWidth, playerPaddle.paddleHeight);

			//draw balls N number of times
			for (Ball b : ballList) {
				g2.setColor(b.ballColour);
				g2.fillOval(b.xx, b.yy, b.diameter, b.diameter);
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		moveBall();
		movePaddle();
		roundCount();

		panel.repaint();
	}


	class StartPauseAL implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (timer.isRunning() ) {
				window.setTitle("CLICK TO RESUME");
				timer.stop();
			}
			else {
				timer.start();
			}


		}

		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

	class PaddleMovementAL implements MouseMotionListener {

		@Override
		public void mouseMoved(MouseEvent e) {
			mx = e.getX();
		}



		@Override
		public void mouseDragged(MouseEvent e) {
		}

	}

}

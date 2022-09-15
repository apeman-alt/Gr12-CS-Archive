import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JFrame;

import javax.swing.JPanel;

public class BallThreads {

	public static void main (String[] args) {

		new BallThreads();

	}

	JFrame window;
	DrawingPanel panel = new DrawingPanel();
	Rectangle ball1 = new Rectangle(300,285,30,30);
	Rectangle ball2 = new Rectangle(485,500,30,30);
	int ballSpeedX1 = 3;
	int ballSpeedY1 = -3;
	int ballSpeedX2 = -3;
	int ballSpeedY2 = 3;
	
	int panW = 800;
	int panH = 600;
	
	BallThreads() {
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(panel);
		
		window.pack();
		window.setVisible(true);

		//put threads here
		
		
		
		
		
		
		
		//repaint screen
		while (true) {
			
			panel.repaint();
			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	


	class DrawingPanel extends JPanel {

		DrawingPanel() {
			this.setBackground(Color.black);
			this.setPreferredSize(new Dimension(800,600));
		}

		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON); //antialiasing

			g2.setColor(Color.red);
			g2.fillOval(ball1.x, ball1.y, ball1.width, ball1.height);
			g2.setColor(Color.blue);
			g2.fillOval(ball2.x, ball2.y, ball2.width, ball2.height);


		}

	}

	
	
}





//ball movement 1

/*
while (true) {
	
	if (ball1.x < 0) {
		ballSpeedX1 *= -1;
	}
	if (ball1.x + ball1.width >= panW) {
		ballSpeedX1 *= -1;
	}
	if (ball1.y < 0) {
		ballSpeedY1 *= -1;
	}
	if (ball1.y + ball1.height >= panH) {
		ballSpeedY1 *= -1;
	}
					
	ball1.x += ballSpeedX1;
	ball1.y += ballSpeedY1;

}
*/





//ball movement 2

/*
	while (true) {
		
		if (ball2.x < 0) {
			ballSpeedX2 *= -1;
		}
		if (ball2.x + ball2.width >= panW) {
			ballSpeedX2 *= -1;
		}
		if (ball2.y < 0) {
			ballSpeedY2 *= -1;
		}
		if (ball2.y + ball2.height >= panH) {
			ballSpeedY2 *= -1;
		}	
		
		ball2.x += ballSpeedX2;
		ball2.y += ballSpeedY2;
	
	}
*/




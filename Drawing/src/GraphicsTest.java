/**
 * 
 * Josh Muszka
 * 
 * November 18, 2021
 * 
 * Graphics Test
 * 
 * This program moves a ball horizontally, and the user can stop and start the ball, as well as change its direction
 * 
 */



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GraphicsTest implements ActionListener {

	public static void main (String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GraphicsTest();
			}
		});
	}

	DrawingPanel drawingPanel; //panel where ball is drawn
	JPanel bottomPanel; //panel where buttons are located
	Timer timer;

	int drawingPanelW = 600;
	int drawingPanelH = 300;
	int bottomPanelW = drawingPanelW;
	int bottomPanelH = 50;
	


	//ball
	Rectangle ball = new Rectangle (drawingPanelW/2 - 15, drawingPanelH/2 - 15, 30,30);
	int ballSpeed = 3;

	//buttons
	JButton pauseResumeBtn;
	JButton reverseBtn;

	GraphicsTest() {

		JFrame window = new JFrame("Graphics Test - Josh Muszka");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//panels
		drawingPanel = new DrawingPanel();
		bottomPanel = new JPanel();

		//make drawingPanel at the top and bottomPanel at the bottom of window
		window.add(drawingPanel, BorderLayout.NORTH);
		window.add(bottomPanel, BorderLayout.SOUTH);

		//drawing panel size and colour
		drawingPanel.setPreferredSize(new Dimension(drawingPanelW,drawingPanelH));
		drawingPanel.setBackground(Color.black);

		//bottom panel size and colour
		bottomPanel.setPreferredSize(new Dimension(bottomPanelW, bottomPanelH));		
		bottomPanel.setBackground(Color.decode("#A0C1EC"));


		//buttons
		pauseResumeBtn = new JButton ("Pause");
		pauseResumeBtn.addActionListener(new PauseResumeAL());

		reverseBtn = new JButton ("Reverse");
		reverseBtn.addActionListener(new ReverseAL());


		//add buttons to bottomPanel
		bottomPanel.add(pauseResumeBtn);
		bottomPanel.add(reverseBtn);

		//timer (to allow motion of the ball)
		timer = new Timer (8, this);
		timer.start(); //start timer

		window.pack();
		window.setVisible(true);
	}

	class DrawingPanel extends JPanel {

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D graphics2D = (Graphics2D) g;

			//anti-aliasing for smooth-looking ball
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON); 

			g.setColor(Color.white); //make ball white
			g.fillOval(ball.x,ball.y,ball.width,ball.height); //draw ball on screen

		}



	}

	void moveBall() {
		//make ball move
		ball.x += ballSpeed;

		//bring ball back on left side if it leaves right side
		if (ball.x > drawingPanelW && ballSpeed > 0) { //if ball position is greater than panel width AND speed is positive (moving rightwards)
			ball.x = -ball.width; //brings the ball to position -30, just outside the left border of the screen
		}

		//bring ball back on right side if it leaves left side
		if (ball.x + ball.width < 0 && ballSpeed < 0) { //if ball position is less than zero AND speed is negative (moving leftwards)
			ball.x = drawingPanelW; //bring ball position to the panel width, just outside the right border of the screen
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		moveBall(); //move ball across screen
		drawingPanel.repaint(); //update ball's position on screen
	}

	//action listener to pause and resume
	class PauseResumeAL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (timer.isRunning()) { //if timer is currently running when button is clicked
				timer.stop(); //stop the timer
				pauseResumeBtn.setText("Resume"); //change button to say "resume"
			}
			else { //if the timer is not currently running when button is clicked
				timer.start(); //start the timer
				pauseResumeBtn.setText("Pause"); //change button to say "pause"
			}
		}

	}

	//action listener to change ball's direction
	class ReverseAL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ballSpeed *= -1; //multiplying speed by *-1 changes the direction
		}

	}
}
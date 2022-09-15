//Josh Muszka
//This program displays an animation-loop that resembles a loading icon
//November 7, 2021

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LoadingIcon implements ActionListener, KeyListener {

	int N = 100; //change number of dots
	
	DrawingPanel panel;
	int panW = 600; //width of screen
	int panH = 600; //height of screen
	
	int diameter = 18; //diameter of each dot
	double rotationRadius = (diameter/2)*5.5; //affects how far apart the dots start from each other
	double angle[] = new double[N]; //array to store the angle of every dot
	double xx[] = new double[N]; //array to store the x position of every dot
	double yy[] = new double[N]; //array to store the y psoition of every dot

	int laserLength = 40; //length of the lasers
	double laserMovement = 0; //as time goes on, this value increases, thus making the lasers move

	Color colours[] = { //colours are stored in an array for random colours
			Color.decode("#ff6161"),
			Color.decode("#ffb359"),
			Color.decode("#fbff6a"),
			Color.decode("#8bff73"),
			Color.decode("#4fefff"),
			Color.decode("#5494ff"),
			Color.decode("#7b66ff"),
			Color.decode("#ff93ff"),
	};

	Color dotColour; //current colour of dots is stored here (keeping track of the current colour allows for no repeats in a row to occur)
	Timer timer; 


	public static void main (String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new LoadingIcon();
			}
		});
	}


	LoadingIcon() {

		JFrame window = new JFrame("Loading Icon");
		panel = new DrawingPanel();
		window.add(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.addKeyListener(this); //key listener to stop and start timer with a keyboard button press

		initialize();

		//timer
		timer = new Timer (12, this);
		timer.start();

		window.pack();
		//window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setVisible(true);

	}


	public void initialize() {

		//calculate angles to position dots evenly around a circle
		for (int i = 0; i < N; i++) {
			angle[i] = 360 - (360/N *i);
		}

		//calculate x and y positions of each dot
		for (int i = 0; i < N; i++) {
			xx[i] = panW/2 + rotationRadius*Math.sin(Math.toRadians(angle[i])) - (diameter/2); 
			yy[i] = panH/2 - rotationRadius*Math.cos(Math.toRadians(angle[i])) - (diameter/2);
			// subtracting half the diameter (diameter/2) centres the system of dots on the screen
		}

		dotColour = colours[(int)(Math.random()*colours.length)]; //choose a random colour for the dots

	}


	public void dotMovement() {

		//decrease angle (starting from 360 degrees)
		for (int i = 0; i < N; i++) {
			angle[i]--; //decrease angle to move counter-clockwise
		}



		//update dot positions
		for (int i = 0; i < N-1; i++) {


			//alternate function transformations so that some are stretched horizonally and some vertically
			//cosine and sine functions are used for horizonal and vertical motion respectively to make dots rotate in a circle
			if (i%2 == 0) {
				xx[i] -= 2*Math.cos(Math.toRadians(angle[i]));
				yy[i] -= Math.sin(Math.toRadians(angle[i]));
			}
			else {
				xx[i] -= Math.cos(Math.toRadians(angle[i]));
				yy[i] -= 2*Math.sin(Math.toRadians(angle[i]));
			}


		}

		//make last dot move in a normal circle (for cooler effect)
		xx[N-1] -= 2*Math.cos(Math.toRadians(angle[N-1]));
		yy[N-1] -= 2*Math.sin(Math.toRadians(angle[N-1]));		



		//reset angles after a full rotation has taken place 
		//(makes math for other things easier)

		if (angle[0] == 0) {
			for (int i = 0; i < N; i++) {
				angle[i] = 360 - (360/N *i);
			}
		}


	}


	public void shootLasers() {

		//move lasers
		laserMovement += 4;

		//reset laserMovement to 0 every time it hits 167 degrees
		//this is so they can fire again every rotation
		if (angle[0]%167 == 0) {
			laserMovement = 0;
		}
	}


	public void changeColour() {

		//change colour every rotation
		Color temp;
		if (angle[0]%360 == 0) {

			//ensure that the same colour isn't used twice in a row
			temp = dotColour;
			while (temp.equals(dotColour)) {
				dotColour = colours[(int)(Math.random()*colours.length)];
			}

		}
	}



	@SuppressWarnings("serial")
	class DrawingPanel extends JPanel {

		DrawingPanel() {
			this.setPreferredSize(new Dimension(panW,panH));
			//this.setBackground(Color.decode("#2E2E2E"));
			this.setBackground(new Color(35,35,35,85)); //85 transparancy allows for a slight trail to follow behind the animation
		}


		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON); 


			//draw lines between dots

			g2.setColor(Color.white);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {	
					//having two for loops allows for lines to be drawn between every dot
					//draws a line between dot i and dot j


					//draw line between a dot's x and y positions and the x and y positions of all other dots in the system
					g2.drawLine((int)xx[i] + (diameter/2), (int)yy[i] + (diameter/2), (int)xx[j] + (diameter/2), (int)yy[j] + (diameter/2));
				}
			}


			//draw dot outlines

			g2.setColor(Color.white);
			for (int i = 0; i < N; i++) {

				//draws a slightly larger white dot underneath the main dots
				g2.fill(new Ellipse2D.Double(xx[i]-2, yy[i]-2, diameter+4, diameter+4));


			}





			//draw lasers
			g2.setColor(dotColour);
			g2.setStroke(new BasicStroke(6));
			if (angle[0]<= 167) { //so lasers dont launch at very beginning of loop



				for (int i = 0; i < N; i++) {


					for (int j = 0; j < N; j++) {

						//having two for loops allows for lasers to be drawn between every dot
						//draws a laser between dot i and dot j




						//quadrants don't correlate to position on the screen, but to position on a geometric cartesian plane

						//ie, if the first dot is located at (350, 400) and the second dot at (200, 200), then the distance between them mathematically is (-150, -200)
						//and thus this correlates to the bottom left quadrant of the plane (down and left)
						//distance is calculated by subtracting dot1 from dot2 since lasers are travelling from dot1 to dot2

						//since the mathematical quadrant affects whether you're adding or subtracting, there must be four separate if-statements to account for each scenario
						//the calculations are the exact same for each scenario with the exception of the positive/negative signs

						//the quadrant changes since the dots are constantly rotating around each other







						//angle between two dots is calculated by taking the tan inverse of the y and x distances between them
						//(opposite/adjacent) is calculated by (y2-y1)/(x2-x1)
						//this angle is then used to determine the x and y position of the lasers so that they are always positioned directly along the path between the two dots

						//first point of laser position is calculated by multiplying the laserLength (aka hypotenuse, which is constant) by the cos or sin of the angle (whether it's for the x or y position)
						//then the position of the first dot is added/subtracted
						//after this calculation, the laser moves by adding/subtracting the laserMovement (which is calculated via the same method, but with laserMovement instead of laserLength) to make this point move toward the dot its traveling to
						//this first point of the laser is the one that's closer to the destination-dot

						//second point of dot is calculated by adding/subtracting the laserMovement calculation to/from the first dot position
						//since the second point of the laser is moving at the same rate as the first point due to the laserMovement calculation, the length of the laser remains constant




						/**bottom left quadrant**/

						if (yy[j] - yy[i] < 0 && xx[j] - xx[i] > 0) {

							//if laser position y meets the y position of the dot it's travelling to (this could have also been done with x positions alternatively)
							if (yy[i]- Math.abs((laserLength*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))+(diameter/2) - Math.abs((laserMovement*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) ))) <= yy[j] + (diameter/2)) {

								//draw laser underneath dot, essentially making it disappear

								g2.drawLine((int) (xx[j]+(diameter/2)), //first x point of laser
										(int) (yy[j] +(diameter/2)), //first y point of laser
										(int) (xx[j]+(diameter/2)), //second x point of laser
										(int) (yy[j])+(diameter/2)); //second y point of laser


							}

							//otherwise, make laser move toward destination dot
							else {
								g2.drawLine((int) (xx[i]+ Math.abs((laserLength*Math.cos((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) ))) +(diameter/2)          + Math.abs((laserMovement*Math.cos((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) ))) ), //first x point of laser
										(int) (yy[i]- Math.abs((laserLength*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))+(diameter/2) - Math.abs((laserMovement*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))), //first point of laser
										(int) (xx[i] + (diameter/2) + Math.abs((laserMovement*Math.cos((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))), //second x point of laser
										(int) ((yy[i]) + (diameter/2) - Math.abs((laserMovement*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) ))))); //second y point of laser
							}

						}






						/**top right quadrant**/

						if (yy[j] - yy[i] > 0 && xx[j] - xx[i] < 0) {

							if (yy[i] + Math.abs((laserLength*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))+(diameter/2) + Math.abs((laserMovement*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) ))) >= yy[j] + (diameter/2)) {

								g2.drawLine((int) (xx[j]+(diameter/2)), 
										(int) (yy[j] +(diameter/2)), 
										(int) (xx[j]+(diameter/2)), 
										(int) (yy[j])+(diameter/2));

							}

							else {
								g2.drawLine((int) (xx[i] - Math.abs((laserLength*Math.cos((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) ))) +(diameter/2)          - Math.abs((laserMovement*Math.cos((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) ))) ), 
										(int) (yy[i] + Math.abs((laserLength*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))+(diameter/2) + Math.abs((laserMovement*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))), 
										(int) (xx[i] + (diameter/2) - Math.abs((laserMovement*Math.cos((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))), 
										(int) ((yy[i]) + (diameter/2) + Math.abs((laserMovement*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))));
							}



						}






						/**top left quadrant**/


						if (yy[j] - yy[i] > 0 && xx[j] - xx[i] > 0) {

							if (yy[i] + Math.abs((laserLength*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))+(diameter/2) + Math.abs((laserMovement*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) ))) >= yy[j] + (diameter/2)) {

								g2.drawLine((int) (xx[j]+(diameter/2)), 
										(int) (yy[j] +(diameter/2)), 
										(int) (xx[j]+(diameter/2)), 
										(int) (yy[j])+(diameter/2));

							}

							else {
								g2.drawLine((int) (xx[i]+ Math.abs((laserLength*Math.cos((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) ))) +(diameter/2)          + Math.abs((laserMovement*Math.cos((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) ))) ), 
										(int) (yy[i]+ Math.abs((laserLength*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))+(diameter/2) + Math.abs((laserMovement*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))), 
										(int) (xx[i] + (diameter/2) + Math.abs((laserMovement*Math.cos((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))), 
										(int) ((yy[i]) + (diameter/2) + Math.abs((laserMovement*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))));

							}
						}






						/**bottom right quadrant**/


						if (yy[j] - yy[i] < 0 && xx[j] - xx[i] < 0) {

							if (yy[i]- Math.abs((laserLength*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))+(diameter/2) - Math.abs((laserMovement*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) ))) <= yy[j] + (diameter/2)) {

								g2.drawLine((int) (xx[j]+(diameter/2)), 
										(int) (yy[j] +(diameter/2)), 
										(int) (xx[j]+(diameter/2)), 
										(int) (yy[j])+(diameter/2));



							}

							else {
								g2.drawLine((int) (xx[i] - Math.abs((laserLength*Math.cos((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) ))) +(diameter/2)          - Math.abs((laserMovement*Math.cos((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) ))) ), 
										(int) (yy[i]- Math.abs((laserLength*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))+(diameter/2) - Math.abs((laserMovement*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))), 
										(int) (xx[i] + (diameter/2) - Math.abs((laserMovement*Math.cos((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))), 
										(int) ((yy[i]) + (diameter/2) - Math.abs((laserMovement*Math.sin((Math.atan((yy[j]-yy[i])/(xx[j]-xx[i]))) )))));
							}

						}

					}
				}


			}



			//draw dots
			for (int i = 0; i < N; i++) {
				g2.fill(new Ellipse2D.Double(xx[i], yy[i], diameter, diameter));
			}


			//add numbers on top of each dot
			//(for debugging purposes)
			/*
			g2.setColor(Color.black);
			for (int i = 0; i < N; i++) {
				g2.drawString(""+(i+1), (int)xx[i]+7, (int)yy[i]+13);
			}
			 */

		}

	}


	@Override
	public void actionPerformed(ActionEvent e) {

		dotMovement();
		shootLasers();
		changeColour();

		panel.repaint();

	}




	@Override
	public void keyPressed(KeyEvent e) {
		if (timer.isRunning()) {
			timer.stop();
		}
		else {
			timer.start();
		}

	}






	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}

}
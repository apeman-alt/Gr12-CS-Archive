package donkeykong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {

	GamePanel panel;
	Player player;

	int panW = 800;
	int panH = 600;

	boolean upPressed = false;
	boolean downPressed = false;
	boolean rightPressed = false;
	boolean leftPressed = false;
	boolean airborne = false;

	double gravity = 0.03;
	
	//entities
	ArrayList<Platform> platformList = new ArrayList<>();
	

	public static void main (String[] args) {
		new Game();
	}

	Game() {

		setup();

		while (true) {

			//System.out.println(airborne);
			movePlayer();
			panel.repaint();

			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {e.printStackTrace();}

		}

	}


	public void setup() {

		JFrame frame = new JFrame("Donkey Kong");	
		panel = new GamePanel();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setResizable(false);

		player = new Player();

		Platform ground = new Platform(20,500,300);
		platformList.add(ground);
		
		Platform plat = new Platform(390,490,200);
		platformList.add(plat);










		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}


	public void movePlayer() {


		//check if airborne
		
		for (Platform p : platformList) {
			
//			if (player.y + player.height < panH) {
//				airborne = true;
//			}
//
//			if (player.speedY > 0 && player.y + player.height >= 600) {
//				airborne = false;
//				player.speedY = 0;
//			}
			
			if (player.y + player.height > p.y-1 && player.y+player.height < p.y+1) {
				
				if (player.x+player.width >= p.x && player.x <= p.x+p.width) {
					airborne = false;
					player.speedY = 0;
					player.y = p.y - player.height; //make sure player is exactly aligned with platform when it lands
					break; //no need to keep iterating through all platforms if it has already found a platform it is on
				}
				else {
					airborne = true;
				}
				
			}
			else {
				airborne = true;
			}

			
		}
		




		player.speedX = 0;

		if (rightPressed) {
			player.speedX = 1;
		}
		if (leftPressed) {
			player.speedX = -1;
		}


		//cancellation of movement situations
		if (leftPressed && rightPressed) {
			player.speedX = 0;
		}



		//gravity
		if (airborne) {
			player.speedY += gravity;
		}

		//wall collision right
		if (player.x+player.width >= panW && rightPressed) {
			player.speedX = 0;
		}
		//wall collision wall collision left
		if (player.x <= 0 && leftPressed) {
			player.speedX = 0;
		}
		
		//platform collision
		for (Platform p : platformList) {
			
			if (player.y + player.height > p.y && player.y < p.y+p.height) {
				
				//right
				if (player.x + player.width >= p.x && rightPressed) {
					
					player.speedX = 0;
					
				}
				
				//left
//				if (//TODO:make this work) {
//					//player.speedX = 0;
//				}
				
				
			}
			
			
			
		}
		


		player.x += player.speedX;
		player.y += player.speedY;






	}


	class GamePanel extends JPanel {

		GamePanel() {
			this.setPreferredSize(new Dimension(panW,panH));
			this.setBackground(Color.black);
			this.addKeyListener(new KeyAL());

			this.setFocusable(true);
			this.requestFocusInWindow();
		}

		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON); //antialiasing

			//draw player
			g2.setColor(Color.white);
			g2.fill(new Rectangle2D.Double(player.x, player.y, player.width, player.height));

			//draw platforms
			for (Platform p : platformList) {
				
				g2.fill(new Rectangle2D.Double(p.x,p.y,p.width,p.height));
				
			}
			
		}

	}

	class KeyAL implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

			if (e.getKeyChar() == ' ') {	

				if (!airborne && player.speedY == 0) {
					player.y -= 2;
					player.speedY = - 2;
				}

			}
		}

		@Override
		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if (key == 87) {
				upPressed = true;
			}
			if (key == 65) {
				leftPressed = true;
			}
			if (key == 83) {
				downPressed = true;
			}
			if (key == 68) {
				rightPressed = true;
			}


		}

		@Override
		public void keyReleased(KeyEvent e) {

			if (e.getKeyCode() == 87) {
				upPressed = false;
			}

			if (e.getKeyCode() == 65) {
				leftPressed = false;
			}

			if (e.getKeyCode() == 83) {
				downPressed = false;
			}

			if (e.getKeyCode() == 68) {
				rightPressed = false;
			}


		}

	}


}

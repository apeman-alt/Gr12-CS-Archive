/**
 * 
 * Josh Muszka
 * 
 * Conway's Game of Life Simulator
 * 
 * Version 1.0, release TBD
 * 
 */

package gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOfLife {



	DrawingPanel panel;
	JPanel btnPanel;
	JButton startBtn;
	JButton resetBtn;
	JLabel genLbl;

	final static int WINSIZE = 800;
	final static int GRID = 60;
	int gridSize = WINSIZE / 40;
	boolean isRunning = false;


	int board[][] = new int[GRID][GRID];
	int generation = 0;


	//initial mouse positions when pressed
	int pX;
	int pY; 


	int dx;
	int dy;


	int camX = 0;
	int camY = 0;
	public static void main (String[] args) {
		new GameOfLife();		
	}

	GameOfLife() {

		JFrame window = new JFrame("Conway's Game of Life");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new DrawingPanel();
		btnPanel = new JPanel();


		btnPanel.setPreferredSize(new Dimension(WINSIZE, 40));
		btnPanel.setBackground(Color.decode("#1c1c1c"));
		startBtn = new JButton("Start");
		startBtn.addActionListener(new StartButtonAL());
		resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ResetButtonAL());

		genLbl = new JLabel (" GEN " + generation);
		genLbl.setFont(new Font("Arial", Font.BOLD, 24));
		genLbl.setForeground(Color.white);

		btnPanel.add(startBtn);
		btnPanel.add(resetBtn);
		btnPanel.add(genLbl);
		window.add(panel, BorderLayout.NORTH);
		window.add(btnPanel, BorderLayout.SOUTH);


		runLife();

		window.pack();
		window.setVisible(true);
		
	}


	public void runLife() {

		Thread gfxThread = new Thread() {

			public void run() {
				while(true) {
					panel.repaint();
					try {
						Thread.sleep(8);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		};


		Thread lifeThread = new Thread() {

			public void run() {

				while(true) {

					try {
						Thread.sleep(600);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (isRunning) {
						birth();
						death();

						updateBoard();
						generation++;

						genLbl.setText(" GEN " + generation);

					}
				}

			}

		};



		gfxThread.start();
		lifeThread.start();

	}


	//0 = dead
	//1 = alive
	//2 = will die next generation
	//3 = will be born next generation

	public void death() {

		for (int i = 0; i < GRID; i++) {

			for (int j = 0; j < GRID; j++) {

				int neighbours = 0;


				if (board[i][j] == 1) {
					if (i > 0) {

						if (board[i-1][j] == 1 || board[i-1][j] == 2) {
							neighbours++;
						}

					}
					if (i < GRID -1) {

						if (board[i+1][j] == 1 || board[i+1][j] == 2) {
							neighbours++;
						}

					}
					if (j > 0) {

						if (board[i][j-1] == 1 || board[i][j-1] == 2) {							
							neighbours++;
						}

					}
					if (j < GRID - 1) {

						if (board[i][j+1] == 1 || board[i][j+1] == 2) {
							neighbours++;
						}

					}
					if (i > 0 && j > 0) {

						if(board[i-1][j-1] == 1 || board[i-1][j-1] == 2) {
							neighbours++;
						}

					}
					if (i < GRID -1 && j > 0) {

						if (board[i+1][j-1] == 1 || board[i+1][j-1] == 2) {
							neighbours++;
						}

					}
					if (i > 0 && j < GRID -1) {

						if (board[i-1][j+1] == 1 || board[i-1][j+1] == 2) {
							neighbours++;
						}

					}
					if (i < GRID-1 && j < GRID - 1) {

						if (board[i+1][j+1]==1 || board[i+1][j+1]==2) {
							neighbours++;
						}

					}


					//Any live cell with fewer than two live neighbours dies, as if by underpopulation.
					if (neighbours < 2) {
						board[i][j] = 2;
					}

					//Any live cell with more than three live neighbours dies, as if by overpopulation.
					if (neighbours > 3) {
						board[i][j] = 2;
					}





				}
			}

		}

	}




	public void birth() {



		for (int i = 0; i < GRID; i++) {

			for (int j = 0; j < GRID; j++) {

				int neighbours = 0;


				if (board[i][j] == 0 ) {
					if (i > 0) {

						if (board[i-1][j] == 1 || board[i-1][j] == 2) {
							neighbours++;
						}

					}
					if (i < GRID -1) {

						if (board[i+1][j] == 1 || board[i+1][j] == 2) {
							neighbours++;
						}

					}
					if (j > 0) {

						if (board[i][j-1] == 1 || board[i][j-1] == 2) {							
							neighbours++;
						}

					}
					if (j < GRID - 1) {

						if (board[i][j+1] == 1 || board[i][j+1] == 2) {
							neighbours++;
						}

					}
					if (i > 0 && j > 0) {

						if(board[i-1][j-1] == 1 || board[i-1][j-1] == 2) {
							neighbours++;
						}

					}
					if (i < GRID -1 && j > 0) {

						if (board[i+1][j-1] == 1 || board[i+1][j-1] == 2) {
							neighbours++;
						}

					}
					if (i > 0 && j < GRID -1) {

						if (board[i-1][j+1] == 1 || board[i-1][j+1] == 2) {
							neighbours++;
						}

					}
					if (i < GRID-1 && j < GRID - 1) {

						if (board[i+1][j+1]==1 || board[i+1][j+1]==2) {
							neighbours++;
						}

					}


					//Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
					if (neighbours == 3) {
						board[i][j] = 3;
					}

				}
			}

		}

	}




	public void updateBoard() {

		for (int i = 0; i < GRID; i++) {

			for (int j = 0; j < GRID; j++) {

				if (board[i][j] == 2) {

					board[i][j] = 0;

				}

				if (board[i][j] == 3) {

					board[i][j] = 1;

				}

			}

		}

	}



	@SuppressWarnings("serial")
	class DrawingPanel extends JPanel {

		DrawingPanel() {

			this.setBackground(Color.black);
			this.setPreferredSize(new Dimension(WINSIZE,WINSIZE-200));
			this.addMouseListener(new ClickAL());
			this.addMouseMotionListener(new MouseMotionAL());
			this.addMouseWheelListener(new ScrollAL());

		}

		public void paintComponent(Graphics g) {

			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON); //antialiasing


			//draw grid lines
			g2.setColor(Color.gray);
			for (int i = 0; i < GRID; i++) {
				g2.drawLine(gridSize*i - camX, 0 - camY, gridSize*i - camX, WINSIZE-200 - camY);
				g2.drawLine(0 - camX, gridSize*i - camY, WINSIZE - camX, gridSize*i - camY);
			}

			//draw dots
			g2.setColor(Color.white);
			for (int i = 0; i < GRID; i++) {

				for (int j = 0; j < GRID; j++) {

					if (board[i][j] == 1 || board[i][j] == 2) {

						g2.fillOval(i*gridSize+1 - camX, j*gridSize+1 - camY, gridSize-1, gridSize-1);

					}

				}

			}




		}

	}

	class StartButtonAL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (isRunning) {
				isRunning = false;
				startBtn.setText("Resume");
			}
			else {
				isRunning = true;
				startBtn.setText("Pause");
			}

		}

	}

	class ResetButtonAL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			isRunning = false;

			for (int i = 0; i < GRID; i++) {

				for (int j = 0; j < GRID; j++) {

					board[i][j] = 0;

				}

			}

			generation = 0;

			startBtn.setText("Start");
			genLbl.setText(" GEN " + generation);

		}


	}


	class ClickAL implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			int x = (e.getX()+camX) / gridSize;
			int y = (e.getY()+camY) / gridSize;

			if (!isRunning) {
				if (board[x][y] == 0) {		
					board[x][y] = 1;	
				}
				else {	
					board[x][y] = 0;	
				}
			}




		}

		@Override
		public void mousePressed(MouseEvent e) {
			pX = e.getX();
			pY = e.getY();

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class MouseMotionAL implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			dx = e.getX() - pX;
			dy = e.getY() - pY;

			if (dx+pX-e.getX() == 0) {

				pX = e.getX();
			}

			if (dy+pY-e.getY() == 0) {

				pY = e.getY();

			}

			camX -= dx;
			camY -= dy;
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
	
	class ScrollAL implements MouseWheelListener{

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			
			
			//scroll up to zoom in
			//scroll down to zoom out
			
			//if scrolling up
			if (e.getWheelRotation() < 0) {
				
				//limits the zoom in, so that user can't make the grid size larger than 60 px
				if (gridSize <= 60) {
					
					//zoom in
					gridSize -= e.getWheelRotation();
				}
				
			}
			
			//if scrolling down
			if (e.getWheelRotation() > 0) {
				
				//limits the zoom out, so that user can't make grid size smaller than 10 px
				if (gridSize >= 10) {
					
					//zoom out
					gridSize -= e.getWheelRotation();
				}
				
			}
			
			
		}
		
	}

}

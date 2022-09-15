/**
 * 
 * Josh Muszka
 * 
 * November 17, 2021
 * 
 * This program generates a random map of landmasses, meant to represent continents and islands
 * Initially, LAND squares and EMPTY squares are drawn on the map
 * If the user left clicks on an empty square, it fills that square and all adjacent empty squares with water squares (dark blue OCEAN squares if the segment touches the 
 * border of the map, light blue LAKE squares if not
 * If the user right clicks on a water square, it turns to a LAND square, and if the user right clicks on
 * a LAND square, it turns to an EMPTY square
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Starting class for MapContinent program

public class MapLakeContinent
{
	public static void main(String[] args) {
		new MapLakeContinent();
	}

	//constants	
	final static int GRID = 32; //size of grid/board
	final static int SQSIZE = 23; // size of each square in pixels
	final static int NUM_LAND = (GRID * GRID /2); //number of land tiles

	//terrain
	final static int EMPTY = 0;		//constant for empty tile. This is the terrain that needs to be a specific value (since arrays are initialized to zero)
	final static int LAND = 1;		//contant for land tile
	final static int LAKE = 33;		//this is just any number used for LAKE and OCEAN
	final static int OCEAN = 89;
	//colours: you can change these
	final static Color COLOURBACK = new Color(242,242,242);	
	final static Color COLOUREMPTY = new Color(222,222,222);
	final static Color COLOURLAND = new Color(100,200,100);
	final static Color COLOURLAKE = new Color(100,100,255);
	final static Color COLOUROCEAN = new Color(10,10,130);

	//global variables
	int[][] board = new int[GRID][GRID];

	MapLakeContinent() {	//constructor
		initGame();
		createAndShowGUI();
	}

	//PROBLEM 4: When half of the squares are land, the land is scattered quite a lot into little islands.
	//           Find a way to make a random map that has the land in bigger chunks.
	void initGame() {		
		//clear board
		for (int i=0;i<GRID;i++) {
			for (int j=0;j<GRID;j++) {
				board[i][j]=EMPTY;
			}
		}

		makeRandomMap();
	}

	void makeRandomMap() {
		//boolean done = false;
		//int landTiles = 0;
		//PROBLEM 1: Make an equal number of land and water squares, but make sure that the land is randomly distributed.

		for (int x = 0; x < GRID; x++) {

			for (int y = 0; y < GRID; y++) {

				if (Math.random()*100 >= 98 && board[x][y] != LAND) { //2% chance of a square being land (intially)
					board[x][y] = LAND;
					makeContinents(x,y); //this method places additional land squares adjacent to the ones already generated, forming large continental landmasses


				}

			}
		}

	}

	void makeContinents(int x, int y) {

		//up
		if (y-1 >= 0 && board[x][y-1]==EMPTY) {

			if (Math.random()*100 >= 55) { //45% chance of forming an adjacent land square
				board[x][y-1] = LAND;
				makeContinents(x,y-1); //make more adjacent squares adjacent to the previous adjacent square
			}

		}

		//right
		if (x+1 < GRID && board[x+1][y]==EMPTY) {

			if (Math.random()*100 >= 55) { //45% chance of forming an adjacent land square
				board[x+1][y] = LAND;
				makeContinents(x+1,y); //make more adjacent squares adjacent to the previous adjacent square
			}

		}

		//down
		if (y+1 < GRID && board[x][y+1]==EMPTY) {

			if (Math.random()*100 >= 55) { //45% chance of forming an adjacent land square
				board[x][y+1] = LAND;
				makeContinents(x,y+1); //make more adjacent squares adjacent to the previous adjacent square
			}

		}

		//left
		if (x-1 >= 0 && board[x-1][y]==EMPTY) {

			if (Math.random()*100 >= 55) { //45% chance of forming an adjacent land square
				board[x-1][y] = LAND;
				makeContinents(x-1,y); //make more adjacent squares adjacent to the previous adjacent square
			}

		}





	}


	//PROBLEM 2: Fix the function "findLakes()" so that it colours all empty squares that are adjacent to this one.
	//PROBLEM 3: Once you have solved problem 2, now set things up so that if any part 
	//           of a lake touches the edge of the board it becomes an ocean.	
	void findLakes(int x, int y) {
		//call subroutine to colour in all contiguous lake squares

		if (board[x][y] == EMPTY || board[x][y] == LAKE) {

			board[x][y] = LAKE;

			//up
			if (y-1 >= 0 && board[x][y-1] == EMPTY) {
				board[x][y-1] = LAKE;
				findLakes(x, y-1);
			}

			//right
			if (x + 1 < GRID && board[x+1][y] == EMPTY) {
				board[x+1][y] = LAKE;
				findLakes (x+1,y);
			}

			//down
			if (y+1 < GRID && board[x][y+1] == EMPTY) {
				board[x][y+1] = LAKE;
				findLakes (x,y+1);
			}

			//left
			if (x-1 >= 0 && board[x-1][y] == EMPTY) {
				board[x-1][y] = LAKE;
				findLakes (x-1, y);
			}


		}



	}


	//make all border lakes dark blue
	void findOceans(int x, int y) {

		for (x = 0; x < GRID; x++) {
			for (y = 0; y < GRID; y++) {

				if (x == 0 || x == GRID -1 || y == 0 || y == GRID-1) {
					if (board[x][y] == LAKE) {
						board[x][y] = OCEAN;
					}
				}

				if (board[x][y] == OCEAN) {

					if (y-1 >= 0 && board[x][y-1] == LAKE) {
						board[x][y-1] = OCEAN;
						findOceans(x, y-1);
					}

					//right
					if (x + 1 < GRID && board[x+1][y] == LAKE) {
						board[x+1][y] = OCEAN;
						findOceans (x+1,y);
					}

					//down
					if (y+1 < GRID && board[x][y+1] == LAKE) {
						board[x][y+1] = OCEAN;
						findOceans (x,y+1);
					}

					//left
					if (x-1 >= 0 && board[x-1][y] == LAKE) {
						board[x-1][y] = OCEAN;
						findOceans (x-1, y);
					}

				}
			}
		}



	}


	void createAndShowGUI() {
		DrawingPanel panel = new DrawingPanel();

		//JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Minesweeper Problem #1-4");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		Container content = frame.getContentPane();
		// content.setLayout(new BorderLayout(2,2));	
		content.add(panel, BorderLayout.CENTER);		
		//frame.setSize(SCRSIZE, SCRSIZE); //may not be needed since my JPanel has a preferred size
		frame.setResizable(false);		
		frame.pack();
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);

		//once the panel is visible, initialize the graphics. (Is this before paintComponent is run?)
		panel.initGraphics();

	}

	class DrawingPanel extends JPanel	//inner class
	{		
		int jpanW, jpanH;
		int blockX, blockY;		

		public DrawingPanel() {
			setBackground(COLOURBACK);
			//Because the panel size variables don't get initialized until the panel is displayed,
			//we can't do a lot of graphics initialization here in the constructor.
			this.setPreferredSize(new Dimension(GRID*SQSIZE,GRID*SQSIZE));
			MyMouseListener ml = new MyMouseListener();
			addMouseListener(ml);			
		}

		//** Called by createGUI()
		void initGraphics() {
			jpanW = this.getSize().width;		
			jpanH = this.getSize().height;	
			blockX = (int)((jpanW/GRID)+0.5);
			blockY = (int)((jpanH/GRID)+0.5);
			// System.out.println("init");
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			//Draw white grid
			g.setColor(Color.WHITE);
			for (int i=0;i<GRID;i++) {
				g.drawLine(blockX*i,0,blockX*i,jpanH);
				g.drawLine(0,blockY*i,jpanW,blockY*i);
			}

			for (int i=0;i<GRID;i++) {
				for (int j=0;j<GRID;j++) {
					colourRect(i,j,g);						
				}
			}			
		}

		void colourRect(int i, int j, Graphics g) {



			for (int row = 0; row < GRID; row++) {

				for (int col = 0; col < GRID; col++) {

					int terrain = board[row][col];

					if (terrain==LAND) {
						g.setColor(COLOURLAND);
						g.fillRect(blockX*row+1, blockY*col+1, blockX-2, blockY-2);
					}
					if (terrain==EMPTY) {
						g.setColor(COLOUREMPTY);
						g.fillRect(blockX*row+1, blockY*col+1, blockX-2, blockY-2);
					}
					if (terrain==LAKE) {
						g.setColor(COLOURLAKE);
						g.fillRect(blockX*row+1, blockY*col+1, blockX-2, blockY-2);
					}
					if (terrain==OCEAN) {
						g.setColor(COLOUROCEAN);
						g.fillRect(blockX*row+1, blockY*col+1, blockX-2, blockY-2);
					}
				}

			}


		}		

		class MyMouseListener extends MouseAdapter	{	//inner class inside DrawingPanel
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				//calculate which square you clicked on
				int i = (int)  x/blockX;
				int j = (int) y/blockY;	// blockY/y

				//allow the right mouse button to toggle/cycle the terrain
				if (e.getButton() != MouseEvent.BUTTON1) {
					switch (board[i][j]) {
					case LAND:
						board[i][j] = EMPTY;
						break;
					default:
						board[i][j] = LAND;
					}
					repaint();
					return;
				}

				findLakes(i,j);		
				findOceans(i,j);
				repaint();
			}		
		} //end of MyMouseListener class

	} //end of DrawingPanel class

}
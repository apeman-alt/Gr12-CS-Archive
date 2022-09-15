//Josh Muszka
//October 30th, 2021
//This program drags a rectangle when the mouse button is held down and then draws it once the mouse is released


/* M. Harwood. Updated Feb 2018
 * This program demonstrates how to drag a rectangle and then draw it once the mouse is released
 */
/************************************************
					TODO: 
 1. add comments explaining how things are working (how the variables are used, eg. why mx1=-1)
 2. add code so that you can drag upwards and left as well. 
	Currently it only works for dragging down to the right
 **************************************************/	
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DragRectangle extends JFrame implements MouseListener, MouseMotionListener {
	public static void main (String[] args){
		new DragRectangle();
	}

	//Constants
	final static Stroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0); //makes mouse draw a dashed line rectangle
	final static BasicStroke stroke = new BasicStroke(2.0f);


	//instance variables
	int scrW=400, scrH=400;	//screen width and height
	DrawingPanel panel;
	int mx1,mx2,my1,my2;	//initial and final mouse points
	//initial mouse points are where the mouse is clicked
	//final mouse points are wherever the mouse is dragged to

	boolean dragging = false; //changes to true whenever mouse is dragging
	Color foreColour = Color.GREEN.darker();
	Color backColour = Color.WHITE;
	Color stretchColour = Color.RED;

	DragRectangle() {
		this.setTitle("Dragging a rectangle");
		this.setSize(scrW,scrH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); //open window in middle of the screen
		panel = new DrawingPanel();
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		this.add(panel);
		this.validate();
		this.setVisible(true);
	}


	private class DrawingPanel extends JPanel{

		private int prevx = 0, prevy = 0; //variables to store the initial points of a rectangle
		private int prevw = 0, prevh = 0; //variables to store the final width and height of a rectangle

		//constructor
		DrawingPanel(){
			mx1=mx2=my1=my2=-1; //set to -1 so that when the rectangle first gets drawn (before being clicked + dragged) it is off the screen and the user can't see it	
		}

		public void paintComponent(Graphics g) {
			//super.paintComponent(g); No. This erases everything

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //antialiasing

			//	g.setColor(backColour);
			int mw = mx2-mx1; //width of rectangle
			int mh = my2-my1; //height of rectangle

			g2.setStroke(dashed);
			g2.setXORMode(this.getBackground());
			g2.setColor(stretchColour);



			if (dragging) {	//if mouse is dragging		
				/* If you just try and draw a white rectangle, it erases all other rectangles too. Use XOR mode */
				//g2.setColor(this.getBackground());

				g2.setStroke(dashed);
				g2.setXORMode(this.getBackground());
				g2.setColor(stretchColour);




				//ERASE PREVIOUS RECTANGLES WHILE DRAGGING
				
				//if down and right
				if (prevw > 0 && prevh > 0) g.drawRect(mx1, my1,prevw,prevh); //draws an identical rectangle each rectangle drawn by the dragged mouse so that it erases/canels it out (due to XORmode)
				
				//if down and left
				else if (prevw < 0 && prevh > 0) g.drawRect(mx1+prevw,my1,-prevw,prevh);
				
				//if up and right
				else if (prevw > 0 && prevh < 0) g.drawRect(mx1,my1+prevh,prevw,-prevh);	
				
				//if up and left
				else if (prevw < 0 && prevh < 0) g.drawRect(mx1+prevw, my1+prevh, -prevw, -prevh);



			} else { //if mouse is not dragging
				g2.setPaintMode();
				g2.setColor(foreColour);
				g2.setStroke(stroke);

				//DRAW GREEN RECTANGLE

				if (prevw > 0 && prevh < 0) { 
					g2.drawRect(mx1,my1+prevh,prevw,-prevh); //when mouse button is released, a permanent green rectangle will be drawn in the dimensions of whatever the last rectangle was.
				} else if (prevw > 0 && prevh > 0){			
					g2.drawRect(mx1, my1,prevw,prevh);
				} else if (prevw < 0 && prevh > 0) {
					g.drawRect(mx1+prevw,my1,-prevw,prevh);
				}
				else if (prevw < 0 && prevh < 0) {
					g2.drawRect(mx1+prevw, my1+prevh, -prevw, -prevh);
				}
			}	





			//DRAW DOTTED RECTANGLE ACCORDING TO MOUSE DRAGGING

			/**
			 * drawRect() can only draw rectangles starting from the upper-left corner (it draws from top to bottom and from left to right).
			 * The dimensions *must* be entered sequentially as HORIZONTAL POSITION OF UPPER LEFT CORNER, VERTICAL POSITION OF UPPER LEFT CORNER, WIDTH, HEIGHT.
			 * When dragging down and to the right, the intiial and final mouse positions already correlate to this, so they can be entered as is.
			 * However, other directions require various tweaking to work properly.
			 * Eg. width of left-direction rectangle must be calculated as mx1-mx2, since width can only be positive and mx1 is 
			 * larger in this case since it's further to the right, and mx2 must be the upper-left corner horizontal position since that's always the leftmost point of the rectanlge.
			 * 
			 * Same logic applies for up-right and up-left rectangles.
			 */
			if (dragging) {
				prevx = mx1; prevy = my1;
				prevw = mw;  prevh = mh;

				//if down and right
				if (mw > 0 && mh > 0) g.drawRect(mx1,my1,mw,mh);

				//if down and left
				if (mw < 0 && mh > 0) g.drawRect(mx2,my1,mx1-mx2,mh);

				//if up and right
				if (mw > 0 && mh < 0) g.drawRect(mx1,my2,mw,my1-my2);

				//if up and left
				if (mw < 0 && mh < 0) g.drawRect(mx2,my2,mx1-mx2,my1-my2);



			} else {
				prevx = prevy = 0; //prevx and prevy must be reset after every rectangle is drawn since otherwise they will affect the dimensions of the following rectangle
				prevw = prevh = 0;
			}



		}		
	}

	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mouseClicked(MouseEvent e) {}

	//this is where the first point of the rectangle is drawn
	public void mousePressed(MouseEvent e) {
		mx1=e.getX();
		my1=e.getY();
		//System.out.println(mx1);
		//System.out.println(my1);
	}
	public void mouseReleased(MouseEvent e) {
		dragging=false;
		panel.repaint();
	}

	public void mouseMoved(MouseEvent e) { }
	public void mouseDragged(MouseEvent e) { 
		dragging=true;
		mx2 = e.getX();
		my2 = e.getY();		
		panel.repaint();
		//System.out.println(mx2);
		//System.out.println(my2);
	}
}
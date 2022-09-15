import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerMove implements ActionListener{

	int N = 5;
	
	JFrame window;
	DrawingPanel panel;
	int panW = 600;
	int panH = panW;

	double xx = panW/2;
	double yy = panH/2 - 55;
	
	double xx2 = panW/2 - 52.3081;
	double yy2 = panH/2 - 16.9959;
	
	double xx3 = panW/2 - 32.3282;
	double yy3 = panH/2 + 44.4959;
	
	double xx4 = panW/2 + 32.3282;
	double yy4 = panH/2 + 44.4959;
	
	double xx5 = panW/2 + 52.3081;
	double yy5 = panH/2 - 16.9959;
	
	Ellipse2D rect = new Ellipse2D.Double(xx,yy,20,20);
	Ellipse2D rect2 = new Ellipse2D.Double(xx2,yy2,20,20);
	Ellipse2D rect3 = new Ellipse2D.Double(xx3,yy3,20,20);
	Ellipse2D rect4 = new Ellipse2D.Double(xx4,yy4,20,20);
	Ellipse2D rect5 = new Ellipse2D.Double(xx5,yy5,20,20);

	public static void main (String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TimerMove();
			}
		});
	}

	TimerMove() {

		window = new JFrame("Timer Move");
		panel = new DrawingPanel();
		window.add(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);


		//timer
		Timer timer = new Timer (12, this);
		timer.start();

		window.pack();
		window.setVisible(true);

	}

	class DrawingPanel extends JPanel {

		DrawingPanel() {
			this.setPreferredSize(new Dimension(panW,panH));
			this.setBackground(Color.white);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g;
			
			 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON); 
			          
			
			g2.setColor (Color.red);

		
			g2.fill(rect);
			g2.fill(rect2);
			g2.fill(rect3);
			g2.fill(rect4);
			g2.fill(rect5);
			
			//g2.fill(new Ellipse2D.Double(xx, yy, 20, 20));
			
			
			//g2.drawOval(200, 300, 200, 200);
			
			g2.drawLine((int)xx + 10, (int)yy + 10, (int)xx2 + 10, (int)yy2 + 10);
			g2.drawLine((int)xx + 10, (int)yy + 10, (int)xx3 + 10, (int)yy3 + 10);
			g2.drawLine((int)xx + 10, (int)yy + 10, (int)xx4 + 10, (int)yy4 + 10);
			g2.drawLine((int)xx + 10, (int)yy + 10, (int)xx5 + 10, (int)yy5 + 10);
			
			g2.drawLine((int)xx2 + 10, (int)yy2 + 10, (int)xx + 10, (int)yy + 10);
			g2.drawLine((int)xx2 + 10, (int)yy2 + 10, (int)xx3 + 10, (int)yy3 + 10);
			g2.drawLine((int)xx2 + 10, (int)yy2 + 10, (int)xx4 + 10, (int)yy4 + 10);
			g2.drawLine((int)xx2 + 10, (int)yy2 + 10, (int)xx5 + 10, (int)yy5 + 10);
			
			g2.drawLine((int)xx3 + 10, (int)yy3 + 10, (int)xx2 + 10, (int)yy2 + 10);
			g2.drawLine((int)xx3 + 10, (int)yy3 + 10, (int)xx + 10, (int)yy + 10);
			g2.drawLine((int)xx3 + 10, (int)yy3 + 10, (int)xx4 + 10, (int)yy4 + 10);
			g2.drawLine((int)xx3 + 10, (int)yy3 + 10, (int)xx5 + 10, (int)yy5 + 10);
			
			g2.drawLine((int)xx4 + 10, (int)yy4 + 10, (int)xx2 + 10, (int)yy2 + 10);
			g2.drawLine((int)xx4 + 10, (int)yy4 + 10, (int)xx3 + 10, (int)yy3 + 10);
			g2.drawLine((int)xx4 + 10, (int)yy4 + 10, (int)xx + 10, (int)yy + 10);
			g2.drawLine((int)xx4 + 10, (int)yy4 + 10, (int)xx5 + 10, (int)yy5 + 10);
			
			g2.drawLine((int)xx5 + 10, (int)yy5 + 10, (int)xx2 + 10, (int)yy2 + 10);
			g2.drawLine((int)xx5 + 10, (int)yy5 + 10, (int)xx3 + 10, (int)yy3 + 10);
			g2.drawLine((int)xx5 + 10, (int)yy5 + 10, (int)xx4 + 10, (int)yy4 + 10);
			g2.drawLine((int)xx5 + 10, (int)yy5 + 10, (int)xx + 10, (int)yy + 10);
			

			g2.setColor(Color.white);
			g2.drawString("1", (int)xx + 7, (int)yy + 13);
			g2.drawString("2", (int)xx2 + 7, (int)yy2 + 13);
			g2.drawString("3", (int)xx3 + 7, (int)yy3 + 13);
			g2.drawString("4", (int)xx4 + 7, (int)yy4 + 13);
			g2.drawString("5", (int)xx5 + 7, (int)yy5 + 13);
			//g2.drawLine(0, panH/2, panW, panH/2);
			//g2.drawLine(panW/2,0,panW/2,panH);
		}

	}
 double angle1 = 360;
 double angle2 = 288;
 double angle3 = 216;
 double angle4 = 144;
 double angle5 = 72;
 
	@Override
	public void actionPerformed(ActionEvent e) {
		
		angle1--;
		angle2--;
		angle3--;
		angle4--;
		angle5--;
		
		xx -= 2*Math.cos(Math.toRadians(angle1));
		yy -= Math.sin(Math.toRadians(angle1));	
		
		xx2 -= Math.cos(Math.toRadians(angle2));
		yy2 -= 2*Math.sin(Math.toRadians(angle2));	
		
		xx3 -= 2*Math.cos(Math.toRadians(angle3));
		yy3 -= Math.sin(Math.toRadians(angle3));	
		
		xx4 -= Math.cos(Math.toRadians(angle4));
		yy4 -= 2*Math.sin(Math.toRadians(angle4));
		
		xx5 -= 2*Math.cos(Math.toRadians(angle5));
		yy5 -= 2*Math.sin(Math.toRadians(angle5));
		
		rect.setFrame(xx, yy, 20, 20);
		rect2.setFrame(xx2,yy2,20,20);
		rect3.setFrame(xx3,yy3,20,20);
		rect4.setFrame(xx4,yy4,20,20);
		rect5.setFrame(xx5,yy5,20,20);
		panel.repaint();
		
	}
}
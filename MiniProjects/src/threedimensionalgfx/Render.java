package threedimensionalgfx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Render {

	Canvas3D canvas = new Canvas3D();
	Cube cube1;
	
	
	public static void main (String[] args) {
		
		new Render();
		
	}
	
	Render() {
		
		JFrame frame = new JFrame("3D rendering engine");
		frame.add(canvas);
		
		setup();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	void setup() {
		
		cube1 = new Cube(200,200, 100, 100, 10);
		
	}
	
	@SuppressWarnings("serial")
	class Canvas3D extends JPanel {
		
		Canvas3D () {
			this.setPreferredSize(new Dimension(800,600));
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			
			
			
			g.setColor(Color.red);
			
			g.fillRect(cube1.x,cube1.y,(int)(cube1.length/cube1.z),(int)(cube1.width/cube1.z));
			
			
		}
		
		
	}
	
}

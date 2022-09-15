package blackpanther;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class BlackPanther extends JFrame{

	public static void main(String[] args) {
		new BlackPanther();
	}


	Image background;
	Image chadwickBoseman;
	
	int jPanW = 1137;
	int jPanH = 600;

	BlackPanther(){	

		this.setTitle("Wakanda Forever!");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	
		this.setSize(jPanW,jPanH);
		this.setLocationRelativeTo(null);
		JPanel panel = new GrPanel();
		panel.setBackground(new Color(20,50,100));
		
		background = loadImage("wakanda.jpg");
		chadwickBoseman = loadImage("chadwickboseman.jpg");

		this.add(panel,BorderLayout.CENTER);
		this.setVisible(true);
	}


	private class GrPanel extends JPanel {

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.WHITE);
			if (background == null) return;

			//draw and stretch to the whole panel	
			g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

			//draw and keep original size
			g.drawImage(chadwickBoseman, (jPanW/2)-(chadwickBoseman.getWidth(null)/2), (jPanH/2)-(chadwickBoseman.getHeight(null)/2), null);

		}
	}

	Image loadImage(String filename) {

		Image image = null;

		// Requires a / at the beginning of the filename
		URL imageURL = this.getClass().getResource(filename); 

		// Filename must be relative (no / at beginning) 
		InputStream inputStr = BlackPanther.class.getClassLoader().getResourceAsStream(filename);

		if (imageURL != null) {
			ImageIcon icon = new ImageIcon(imageURL);				
			image = icon.getImage();
		} else {
			JOptionPane.showMessageDialog(null, "An image failed to load: " + filename , "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return image;
	}

}
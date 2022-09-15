import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyActionListeners {

	JFrame window;
	JPanel panel;
	JButton btn[] = new JButton[5];
	int count = 0;

	JLabel greeting;
	JLabel sliderNum;
	
	int panW = 600;
	int panH = 400;
	
	JSlider slider;

	public static void main (String[] args) {

		new MyActionListeners ();

	}

	MyActionListeners () {

		/** Set up window, panel, and labels **/

		window = new JFrame("MyActionListeners");
		panel = new JPanel ();
		window.setSize(panW,panH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(panel);

		
		/** JLabel **/
		greeting = new JLabel ("Hello");
		sliderNum = new JLabel (""+0);
		panel.add(greeting);

			
		
		/** JButtons **/
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new JButton(""+(i+1));
			panel.add(btn[i]);
		}
		
		//Button 1 changes background color
		btn[0].addActionListener(new backgroundColour());

		//Button 2 changes window title to # of times the button was clicked
		btn[1].addActionListener(new clickCount());

		//Button 3 and Button 4 draw a random white line across the screen
		btn[2].addActionListener(new changeGreeting());
		btn[3].addActionListener(new changeGreeting());

		//Button 5 moves the window to a random spot and resizes it to a random size
		btn[4].addActionListener(new windowPosition());

		

		
		/** JSliders **/
		slider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);	
		slider.setPreferredSize(new Dimension(100, 20));
		panel.add(slider);

		//SliderX changes window's width
		slider.addChangeListener(new manualSize());
		slider.setBackground(Color.WHITE);
		
		panel.add(sliderNum);
		
		window.setVisible(true);

	}

	class backgroundColour implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int num = (int)(Math.random()*7);

			Color[] colour = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.PINK};

			panel.setBackground(colour[num]);

			window.setVisible(true);
		}

	}

	class clickCount implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			count++;
			window.setTitle("Number of clicks: " + count);

			window.setVisible(true);


		}

	}

	class changeGreeting implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (greeting.getText().equals("Hello")) {
				greeting.setText("Goodbye");
				window.setVisible(true);
			}
			else {
				greeting.setText("Hello");
				window.setVisible(true);
			}
			

		}


	}
	
	class windowPosition implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			
			panW = (int)(Math.random()*1000)+120;
			panH = (int)(Math.random()*800)+96;
			
			int posX = (int)(Math.random()* ((screenSize.getWidth()) -panW ));
			int posY = (int)(Math.random()* ((screenSize.getHeight()) -panH ));
			
			
			window.setSize(panW,panH);
			window.setLocation(posX,posY);
			
			
			window.setVisible(true);
			
		}
		
	}
	
	class manualSize implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			
			if (slider.getValueIsAdjusting()) {
				sliderNum.setText(""+slider.getValue());
			}
			
		}
		
	}

}

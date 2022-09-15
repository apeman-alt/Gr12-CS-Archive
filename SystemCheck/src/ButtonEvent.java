import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ButtonEvent {


	JFrame frame = new JFrame("ButtonEvent");
	JButton button = new JButton("Click me");

	public ButtonEvent() {
		button.addActionListener(new ActionListener() {

			@Override // ?????
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					button.setBackground(Color.RED);
			}});
		
		frame.add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new ButtonEvent();


			}

		});
	}
	
	private class Evt implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			button.setBackground(Color.RED);
			
		}
		
	}
}

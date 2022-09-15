
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingUtilities;


public class SystemCheckPreliminary {

	public SystemCheckPreliminary (){

		int N = 6;
		int colourStatus [] = new int [N];

		JButton btnTest = new JButton("TEST");
		//JLabel
		JLabel lbl = new JLabel("WAITING...", SwingConstants.CENTER);
		lbl.setFont(new Font("Serif", Font.BOLD, 32));
		JButton btn[] = new JButton [N];

		//window stuff
		JFrame window = new JFrame("SystemCheck");
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		//create panel
		JPanel panel1 = new JPanel(new GridLayout(3,1,240,90));
		panel1.setBorder(new EmptyBorder(30, 40, 30, 40));
		panel1.setBackground(Color.GRAY);		

		JPanel panel2 = new JPanel(new GridLayout(2,3,80,20));
		panel2.setBorder(new EmptyBorder(0,100,0,100));
		
		JPanel panel3 = new JPanel(new GridLayout(1,1));
		panel3.setBorder(new EmptyBorder(0,260,30,260));
		
		panel1.setBackground(Color.GRAY);		
		panel2.setBackground(Color.GRAY);
		panel3.setBackground(Color.GRAY);
		window.add(panel1);


		ActionListener changeColour = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof JButton){

					for (int i = 0; i < N; i++) {
						if (e.getSource() == btn[i]) {

							if (colourStatus[i] < 2) {
								colourStatus[i]++;
								if (colourStatus[i] == 1) {
									btn[i].setBackground(Color.ORANGE);
								}
								if (colourStatus[i] == 2) {
									btn[i].setBackground(Color.GREEN);
								}



							}

						}
					}
				}
			}};

			//buttons			

			panel1.add(lbl);
			for (int i = 0; i < N; i++) {
				btn[i] = new JButton();
				btn[i].setBackground(Color.RED);
				btn[i].setPreferredSize(new Dimension(48,30));
				btn[i].setText(""+(i+1));

				btn[i].addActionListener(changeColour);
				panel2.add(btn[i]);
			}



			//ACTION LISTENER FOR TEST BUTTON :D

			btnTest.addActionListener(new ActionListener() {			
				public void actionPerformed(ActionEvent e) {
				
					int sum = 0;
					for (int i = 0; i < N; i++) {
						sum+=colourStatus[i];
					}
					
					if (sum < N*2) {
						lbl.setText("DANGER ERROR!");
					}
					else {
						lbl.setText("All Systems Go");
					}
				}
			});

			panel1.add(panel2);
			panel1.add(panel3);
			panel3.add(btnTest);

			window.setVisible(true);
	}



	/////////////////////////////////////

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new SystemCheckPreliminary();


			}

		});
	}

}


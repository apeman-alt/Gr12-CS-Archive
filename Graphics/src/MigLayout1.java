
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JWindow;

import net.miginfocom.swing.MigLayout;



public class MigLayout1 {
	
	
	
	public static void main (String[] args) {
		
		menuContent();
		
		
	}
/*
	static void coffeeOrder() {
		
		JFrame window = new JFrame ("Coffee Order");
		window.setSize(500,900);
		//window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//panel with MigLayout
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout()); //initialize MigLayout
		//panel.setBackground (Color.decode("#e0e9ff"));
		
		
		menuContent(panel);
		
		
		
		
	}
*/	
	static void menuContent() {
		
		
		
		JFrame window = new JFrame ("Coffee Order");
		window.setSize(500,900);
		//window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//panel with MigLayout
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout()); //initialize MigLayout
		//panel.setBackground (Color.decode("#e0e9ff"));
		
		JLabel lblName = new JLabel ("Name: ");
		panel.add(lblName);
		JTextField tfName = new JTextField (16);
		panel.add(tfName, "wrap");
		
		JLabel lblTime = new JLabel ("Arrival Time: ");
		panel.add(lblTime);
		JTextField tfTime = new JTextField (8);
		panel.add(tfTime, "wrap");
		
		JLabel lblPhone = new JLabel ("Phone Number: ");
		panel.add(lblPhone);
		JTextField tfPhone = new JTextField(16);
		panel.add(tfPhone, "wrap");
		
		/** ---------------- **/
				
		JLabel lblSize = new JLabel ("Size:");
		panel.add(lblSize, "wrap");
		JRadioButton rbSize [] = new JRadioButton[4];
		rbSize[0] = new JRadioButton ("Small");
		rbSize[1] = new JRadioButton ("Medium");
		rbSize[2] = new JRadioButton ("Large");
		rbSize[3] = new JRadioButton ("Extra Large");
		ButtonGroup sizes = new ButtonGroup ();
		
		for (int i = 0; i < rbSize.length; i++) {
			sizes.add(rbSize[i]);
			panel.add(rbSize[i], "wrap");
			
		}
		
		JLabel lblSugar = new JLabel ("Sugar: ");
		panel.add(lblSugar, "wrap");
		JRadioButton rbSugar [] = new JRadioButton[4];
		rbSugar[0] = new JRadioButton ("None");
		rbSugar[1] = new JRadioButton ("1");
		rbSugar[2] = new JRadioButton ("2");
		rbSugar[3] = new JRadioButton ("3");
		ButtonGroup sugar = new ButtonGroup ();
		
		for (int i = 0; i < rbSugar.length; i++) {
			sugar.add(rbSugar[i]);
			panel.add(rbSugar[i], "wrap");
		}
		
		JLabel lblCream = new JLabel ("Cream:");
		panel.add(lblCream, "wrap");
		JRadioButton rbCream [] = new JRadioButton[4];
		rbCream[0] = new JRadioButton ("None");
		rbCream[1] = new JRadioButton ("1");
		rbCream[2] = new JRadioButton ("2");
		rbCream[3] = new JRadioButton ("3");
		ButtonGroup cream = new ButtonGroup ();
		
		for (int i = 0; i < rbCream.length; i++) {
			cream.add(rbCream[i]);
			panel.add(rbCream[i], "wrap");
		}
		
		/** ---------------- **/
		
		JLabel order = new JLabel ("Order");
		panel.add(order, "wrap");
		
		ButtonEvent(window, rbSize, order);
		
		window.add(panel);
		window.setVisible(true);
		
		
	}
	
	public static void ButtonEvent(JFrame window, JRadioButton rbSize[], JLabel order) {
			
			rbSize[0].addActionListener(new ActionListener() {

			@Override // ?????
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < rbSize.length; i++) {
					if (e.getSource() == rbSize[i]) {
						System.out.println(rbSize[i].getText());
						order.setText(rbSize[i].getText());
					}
				}
			}});
		
			
			rbSize[1].addActionListener(new ActionListener() {

				@Override // ?????
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for (int i = 0; i < rbSize.length; i++) {
						if (e.getSource() == rbSize[i]) {
							System.out.println(rbSize[i].getText());
							order.setText(rbSize[i].getText());
						}
					}
				}});
			
			rbSize[2].addActionListener(new ActionListener() {

				@Override // ?????
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for (int i = 0; i < rbSize.length; i++) {
						if (e.getSource() == rbSize[i]) {
							System.out.println(rbSize[i].getText());
							order.setText(rbSize[i].getText());
						}
					}
				}});
			
			rbSize[3].addActionListener(new ActionListener() {

				@Override // ?????
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for (int i = 0; i < rbSize.length; i++) {
						if (e.getSource() == rbSize[i]) {
							System.out.println(rbSize[i].getText());
							order.setText(rbSize[i].getText());
						}
					}
				}});
		//window.add(order);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.pack();
		window.setVisible(true);
	}
	
}

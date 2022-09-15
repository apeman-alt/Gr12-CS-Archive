
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;



public class CoffeeShop {

	JRadioButton rbSize [] = new JRadioButton[4];
	JRadioButton rbSugar [] = new JRadioButton[4];
	JRadioButton rbCream [] = new JRadioButton[4];
	JPanel panel = new JPanel();
	static NumberFormat d = new DecimalFormat("#0.00");
	
	public static void main (String[] args) {

		new CoffeeShop();


	}

	CoffeeShop() {

		JFrame window = new JFrame ("Virtual Cafe Simulator");
		window.setSize(900,900);
		//window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//panel with MigLayout
		
		//JPanel panelName = new JPanel();
		//JPanel panelTime = new JPanel();
		//JPanel panelPhone = new JPanel();
		panel.setLayout(new MigLayout("insets 20, wrap 3, gap 8")); //initialize MigLayout
		//panel.setBackground (Color.decode("#e0e9ff"));

		panel.add(new JLabel("Combrewter - Online Cafe"), "span 3, align center, gapbottom 15");
		
		
		JLabel lblName = new JLabel ("Name: ");
		panel.add(lblName, "align right");
		JTextField tfName = new JTextField (16);
		panel.add(tfName, "span 2, wrap");

		JLabel lblTime = new JLabel ("Arrival Time: ");
		panel.add(lblTime, "align right");
		JTextField tfTime = new JTextField (10);
		panel.add(tfTime,"span 2, wrap");

		JLabel lblPhone = new JLabel ("Phone Number: ");
		panel.add(lblPhone, "align right");
		JTextField tfPhone = new JTextField(10);
		panel.add(tfPhone, "wrap, gapbottom 5");

		JLabel lblTotal = new JLabel ("Total: $" + d.format(0.00));
		
		JButton btnSendOrder = new JButton ("Send Order");

		JLabel lblOrderNum = new JLabel();
		
				
		drinkModifiers();

		
		panel.add(lblTotal, "align center");
		panel.add(btnSendOrder);

		ButtonEvent(window, rbSize, rbSugar, rbCream, lblTotal, panel, btnSendOrder, lblOrderNum);
		
		panel.setBackground(Color.decode("#e0e7ff"));
		window.add(panel);
		window.pack();
		window.setVisible(true);



	}

	void drinkModifiers() {



		JPanel sizePanel, sugarPanel, creamPanel;

		sizePanel = new JPanel(new MigLayout());
		sizePanel.setBorder(BorderFactory.createTitledBorder(
		         BorderFactory.createEtchedBorder(), "Size:", TitledBorder.LEFT, TitledBorder.TOP));

		rbSize[0] = new JRadioButton ("Small");
		rbSize[1] = new JRadioButton ("Medium");
		rbSize[2] = new JRadioButton ("Large");
		rbSize[3] = new JRadioButton ("Extra Large");
		ButtonGroup sizes = new ButtonGroup ();


		for (int i = 0; i < rbSize.length; i++) {
			sizes.add(rbSize[i]);
			sizePanel.add(rbSize[i], "wrap");
			rbSize[i].setBackground(Color.decode("#e0e7ff"));
		}

		sugarPanel = new JPanel(new MigLayout());
		sugarPanel.setBorder(BorderFactory.createTitledBorder(
		         BorderFactory.createEtchedBorder(), "Sugar:", TitledBorder.LEFT, TitledBorder.TOP));
		rbSugar[0] = new JRadioButton ("None");
		rbSugar[1] = new JRadioButton ("1");
		rbSugar[2] = new JRadioButton ("2");
		rbSugar[3] = new JRadioButton ("3");
		ButtonGroup sugar = new ButtonGroup ();


		for (int i = 0; i < rbSugar.length; i++) {
			sugar.add(rbSugar[i]);
			sugarPanel.add(rbSugar[i], "wrap");
			rbSugar[i].setBackground(Color.decode("#e0e7ff"));
		}

		creamPanel = new JPanel (new MigLayout());
		creamPanel.setBorder(BorderFactory.createTitledBorder(
		         BorderFactory.createEtchedBorder(), "Cream:", TitledBorder.LEFT, TitledBorder.TOP));

		rbCream[0] = new JRadioButton ("None");
		rbCream[1] = new JRadioButton ("1");
		rbCream[2] = new JRadioButton ("2");
		rbCream[3] = new JRadioButton ("3");
		ButtonGroup cream = new ButtonGroup ();


		for (int i = 0; i < rbCream.length; i++) {
			cream.add(rbCream[i]);
			creamPanel.add(rbCream[i], "wrap");
			rbCream[i].setBackground(Color.decode("#e0e7ff"));
		}

		sizePanel.setBackground(Color.decode("#e0e7ff"));
		sugarPanel.setBackground(Color.decode("#e0e7ff"));
		creamPanel.setBackground(Color.decode("#e0e7ff"));
		
		panel.add(sizePanel, "sizegroup mod");
		panel.add(sugarPanel, "sizegroup mod");
		panel.add(creamPanel, "sizegroup mod, wrap, gapbottom 5");
		
	}

	public static void ButtonEvent(JFrame window, JRadioButton rbSize[], JRadioButton rbSugar[], JRadioButton rbCream[], JLabel lblTotal,
			JPanel panel, JButton btnSendOrder, JLabel lblOrderNum) {

		double price [] = {1.62, 1.85, 2.02, 2.30};

		for (int i = 0; i < rbSize.length; i++) {
			rbSize[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					for (int i = 0; i < rbSize.length; i++) {
						if (e.getSource() == rbSize[i]) {
							System.out.println(rbSize[i].getText());
							lblTotal.setText("Total: $" + d.format(price[i]));			
							System.out.println(price[i]);

							//panel.revalidate();
							panel.add(lblTotal, "align center");
							panel.add(btnSendOrder);
							window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							window.setVisible(true);
							return;

						}
					}
				}});
		}


		btnSendOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSendOrder) {

					//panel.remove(btnSendOrder);
					lblOrderNum.setText("Order #: " + (int)(Math.random()*100+1));


					btnSendOrder.setEnabled(false);

					for (int i = 0; i < rbSize.length; i++) {
						rbSize[i].setEnabled(false);
						rbSugar[i].setEnabled(false);
						rbCream[i].setEnabled(false);
					}
					
					panel.add(lblOrderNum);

					//panel.revalidate();
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.setVisible(true);
					return;
				}
			}
		});


	}

}
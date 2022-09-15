//Henry Lee ICS4U1, Oct 5 2021. MigLayout practice

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class MigSample {

	public static void main(String[] args) {
		new MigSample();
	}

	//adding panels, including those for radio & checkmark buttons
	JPanel panel = new JPanel();
	JPanel radioPanel = new JPanel(); 
	JPanel checkPanel = new JPanel(); 
	JPanel panelname = new JPanel();
	JPanel paneldate = new JPanel();
	JPanel panelschool = new JPanel();

	//adding the main window
	JFrame frame = new JFrame("Mig layout");

	//adding text and labels 
	JLabel title = new JLabel("COVID-19 Screening");
	JLabel name = new JLabel("Name:");
	JLabel date = new JLabel("Date:");
	JLabel school = new JLabel("School:");
	JLabel questionone = new JLabel("Have you been in close contact with anyone w/ the COVID Virus in the past 14 days?");
	JLabel questiontwo = new JLabel("Are you experiencing any of the following symptoms? Check all that apply:");

	//adding in text fields to go alonside some of the labels 
	JTextField nameField = new JTextField("Henry Lee", 10);
	JTextField dateField = new JTextField("October 5th, 2021", 10);
	JTextField schoolField = new JTextField("London Central Secondary School", 20);
	JTextField questiononeField = new JTextField("No", 5);

	//adding regular clickable buttons 
	JButton submitBtn = new JButton("Submit");
	JButton cancelBtn = new JButton("Cancel");

	// create checkbox buttons, which more of 1 can be selected
	JCheckBox check1 = new JCheckBox("Coughing");
	JCheckBox check2 = new JCheckBox("Sneezing");
	JCheckBox check3 = new JCheckBox("Having trouble breathing");
	JCheckBox check4 = new JCheckBox("Fever");

	//create radio buttons, which only one of them can be selected
	JRadioButton radio1 = new JRadioButton("Yes");
	JRadioButton radio2 = new JRadioButton("No");

	//initialize a group for the two radio buttons 
	ButtonGroup radio3 = new ButtonGroup();

	MigSample() {



		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	
		buildMigDashboard();
		frame.add(panel);
		frame.pack(); 
		frame.setResizable(true);
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);

	}

	private void buildMigDashboard() {
		panel.setLayout(new MigLayout("wrap 4"));


		panel.add(title, "span, center, gapbottom 15");
		panelname.add(name);
		panelname.add(nameField);

		//wrap means to go to a new row
		panel.add(panelname, "wrap");

		paneldate.add(date);
		paneldate.add(dateField);

		panel.add(paneldate, "wrap");


		panelschool.add(school);
		panelschool.add(schoolField);

		panel.add(panelschool,"wrap");

		panel.add(questionone);

		radioPanel.add(radio1);
		radioPanel.add(radio2);

		//group the two radio buttons in one to make sure both cannot be selected
		radio3.add(radio1);
		radio3.add(radio2);

		panel.add(radioPanel, "wrap");

		panel.add(questiontwo, "wrap");

		checkPanel.add(check1);
		checkPanel.add(check2);
		checkPanel.add(check3);
		checkPanel.add(check4);

		panel.add(checkPanel, "wrap"); 



		panel.add(submitBtn, "span, split 3, sizegroup bttn");
		panel.add(cancelBtn, "sizegroup bttn");




	}
}
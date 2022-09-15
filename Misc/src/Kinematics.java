import java.io.DataInputStream;
import java.io.IOException;

public class Kinematics {

	public static void main (String[] args) throws IOException {
		
		DataInputStream input = new DataInputStream (System.in);
		
		String a = "acceleration";
		String i = "initial velocity";
		String f = "final velocity";
		String d = "displacement";
		String t = "time";
		
		String userInput = "";
		System.out.println("(Assume positive up and east directions)\n\nUNITS\nAcceleration: m/s^2\nInitial & Final Velocity: m/s\nDisplacement: "
				+ "m\nTime: s\n");
		System.out.println("Acceleration - a, Initial velocity - i, Final velocity - f, Displacement - d, Time - t");
		System.out.println("Enter three variables to solve for the other two\n");
		
		System.out.print("What is your first variable? ");
		userInput = input.readLine();
		
		while (! userInput.equals("a") &&! userInput.equals("i")&&! userInput.equals("f")&&! userInput.equals("d") &&! userInput.equals("t")    ) {
			
			System.out.print("What is your first variable? ");
			userInput = input.readLine();
			
			if (! userInput.equals("a") &&! userInput.equals("i")&&! userInput.equals("f")&&! userInput.equals("d") &&! userInput.equals("t")    ) {
				System.out.println("ERROR: Choose a valid variable");
			}
			
		}
			
		
	}
	
}

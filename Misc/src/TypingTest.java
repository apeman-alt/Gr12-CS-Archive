import java.io.*;
import java.text.NumberFormat;
public class TypingTest {

	@SuppressWarnings("deprecation")
	public static void main (String[] args) throws IOException {

		DataInputStream input = new DataInputStream (System.in);
		//DataFormat d = new NumberFormat ("#0.00");

		String test = "The quick fox jumps over the lazy dog";
		double accuracy = 0.00;
		int count = 0;

		System.out.println (test);
		System.out.println (" ");
		String uSent = input.readLine();

		System.out.println (" ");

		while (count < uSent.length()) {

			for (int i = 0; i < uSent.length(); i++) {

				if (test.substring(i, i+1).equals(uSent.substring(i, i+1))) {
					accuracy++;

				}
				
				count++;

			}

		}

		accuracy = (accuracy / test.length()) * 100;

		System.out.println ("Your accuracy was " + accuracy + "%");

	}

}

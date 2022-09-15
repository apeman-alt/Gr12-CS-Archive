/**
 * 
 * Josh Muszka
 * 
 * September 21, 2021
 * 
 * This program experiments with the various uses of printf statements
 *
 */


public class MyPrintf {

	public static void main (String[] args) {

		// print 1 divided by 7 to five decimal places
		//System.out.printf("%.5f%n%n", (double)(1.0/7.0));
		
		System.out.printf("%.5f%n%na", (100000/7)/100000.0);



		String name = "Bessy";
		String color = "brown";
		int weight = 1200;

		// prints "The cow's name is Bessy, she is brown and weighs 1200 kg."		
		System.out.printf("The cow's name is %s, she is %s and weighs %d kg.%n%n", name, color, weight);



		int xx = 123;

		System.out.printf("| %8d |%n| %s |%n| %8d |%n| %08d |%n| %+8d |%n| %-8d |%n| %8.1f |", 12345678, "========", xx, xx, xx, xx, (double)xx);
	}

}

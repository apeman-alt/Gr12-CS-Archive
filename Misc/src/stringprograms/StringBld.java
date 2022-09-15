/**
 * 
 * Josh Muszka
 * 
 * December 13, 2021
 * 
 * This program experiments with converting between strings and stringbuilders, and
 * using the stringbuilder methods append(), toString(), capacity(), delete(), insert(), and replace()
 * 
 */

package stringprograms;

public class StringBld {

	public static void main (String[] args) {

		StringBuilder sb = new StringBuilder();
		String message;
		
		sb.append("Hello world");
		message = sb.toString();
		System.out.println(message); //print "Hello world"
		System.out.println(sb.capacity()); //print capacity (16)
		
		sb.delete(5,11); //delete "world"
		message = sb.toString();
		System.out.println(message); //print "Hello"
		
		sb.insert(5, " human");
		message = sb.toString();
		System.out.println(message); //print "Hello human"
		
		sb.replace(0, sb.capacity(), "Goodbye"); //replace entire stringbuilder with "Goodbye"
		message = sb.toString();
		System.out.println(message); //print "Goodbye"
	}

}
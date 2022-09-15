/**
 * 
 * Josh Muszka
 * 
 * December 15, 2021
 * 
 * This program reads in a sentence from the user, and
 * divides it into a paragraph with no more than N (default 20) characters
 * per line
 * It also adds a box of asterisks around the paragraph
 * 
 */

package stringprograms;

import java.util.Scanner;

public class BorderOfStars {

	final static int N = 20; //line length

	public static void main (String[] args) {


		Scanner scan = new Scanner(System.in);
		StringBuilder strBuild = new StringBuilder();
		System.out.print("Enter a sentence: ");

		String words [] = (scan.nextLine()).split(" ");
		scan.close();

		int characterCount = 0; //represents the length of a line



		//loop through each individual word
		for (int i = 0; i < words.length; i++) {

			//so that a single word cannot exceed 20 characters
			if(words[i].length() > N) {
				System.out.println("Words cannot exceed 20 characters");
				return;
			}


			//count the characters in the current line
			for (int j = 0; j < words[i].length(); j++) {
				characterCount++;
			}


			if (characterCount != 20) {
				characterCount++; //add an extra +1 after the loop to account for the space after the word, so long as that doesn't exceed the line length limit
			}


			if (characterCount > N) { //if the letter count exceeds the character capacity (N)
				for (int j = 0; j < N - (characterCount-(words[i].length()+2)) ; j++) { // remaining spaces: N - length of the extra word (word that will be pushed to next line)
					strBuild.append(" "); //add extra spaces to fill the line (for an even * box)
				}

				strBuild.append("*\n* "); //add * on both sides

				//reset character count for next line
				//next line already has a word pushed down to it, so make letter count equal to length of that word
				characterCount = words[i].length() + 1; //+1 to account for the space after the word
			}

			strBuild.append(words[i] + " "); //add each word and a space to the string builder

		}

		//to add spaces to the last line (which is not currently > N, and thus will not have been detected by the code above)
		for (int j = 0; j < N -(characterCount-1); j++) {
			strBuild.append(" ");
		}


		System.out.println(" ");
		for (int i = 0; i < N+4; i++) System.out.print("*");
		System.out.print("\n* " + strBuild.toString() +"*\n"); //print out paragraph
		for (int i = 0; i < N+4; i++) System.out.print("*");


	}

}

/**
 * 
 * Josh Muszka
 * 
 * December 13, 2021
 * 
 * This program reads in a sentence from the user, and then checks each word to
 * see if it has 3 of the same letter in a row
 * It prints all words that have 3 letters in a row
 * 
 */

package stringprograms;

import java.util.Scanner;

public class Triplets {

	public static void main(String[] args) {

		Scanner myScan = new Scanner (System.in);
		System.out.print("Enter a sentence: ");

		String sentence = myScan.nextLine(); //get sentence that user types in
		String words[] = sentence.split(" "); //break up the sentence into separate words (between the spaces)

		for (int i = 0; i < words.length; i++) { //check each word in the sentence

			for (int j = 0; j < words[i].length()-2; j++) { //-2, since if a letter is less than two spots away from the end of the word then it won't be possible for there to be 3 in a row

				if (words[i].substring(j,j+1).equals(words[i].substring(j+1,j+2))) { //if current letter is equal to next letter

					if (words[i].substring(j+1,j+2).equals(words[i].substring(j+2,j+3))) { //if next letter is equal to next next letter

						System.out.println(words[i]); //print out the word since there are at least 3 letters in a row
						break; //break out of inner loop to move onto next word

					}

				}

			}
		}

		myScan.close();


	}
}
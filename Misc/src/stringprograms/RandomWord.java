/**
 * 
 * Josh Muszka
 * 
 * December 28, 2021
 * 
 * This program randomly creates a new 6 letter word with either
 * 1 or 2 vowels
 * 
 */

package stringprograms;

public class RandomWord {

	final static int N = 6; //length of word
	
	public static void main (String[] args) {
	
		
		StringBuilder sb = new StringBuilder();
		
		//array of vowels to be inserted later
		char vowels[] = {'a', 'e', 'i', 'o', 'u'};
		
		
		
		
		//generate 6 letter string of random consonants
		
		for (int i = 0; i < N; i++) {
			
			//random letter between a and z, based on ASCII values
			char c = (char)((Math.random()*26)+97);
			
			//makes sure that no vowels get placed into the string builder
			while (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				
				c = (char)((Math.random()*26)+97);
					
			}
			sb.append(c);		
		}
		
		
		
		
		//add vowels
		//loop will run either 1 or 2 times, adding either 1 or 2 vowels
		
		for (int i = 0; i < (int)(Math.random()*2+1); i++) {
			
			//chooses which spot the vowel will be placed in the word
			int replacement = (int)(Math.random()*N);
			
			//so that it doesn't overwrite an already existing vowel
			while (sb.substring(replacement, replacement + 1).equals("a") 
					|| sb.substring(replacement, replacement + 1).equals("e")
					|| sb.substring(replacement, replacement + 1).equals("i")
					|| sb.substring(replacement, replacement + 1).equals("o")
					|| sb.substring(replacement, replacement + 1).equals("u")) {
				replacement = (int)(Math.random()*N);
			}
			
			//choose a random vowel from the array, and convert it to a string to be replaced into the string builder
			//replace() requires a string, not a char
			String newVowel = ""+vowels[(int)(Math.random()*vowels.length)];
			
			sb.replace(replacement, replacement+1, newVowel);
			
			
		}
		
		
		System.out.println(sb.toString());
		
		
	}
	
	
}

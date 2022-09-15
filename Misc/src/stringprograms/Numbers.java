package stringprograms;

import java.util.Scanner;

public class Numbers {


	public static void main(String[] args) {

		StringBuilder sb = new StringBuilder();
		Scanner myScan = new Scanner (System.in);
		String numbers [] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		int num;

		System.out.print("Enter a sentence: ");
		String sentence = myScan.nextLine(); //get sentence that user types in
		sb.append(sentence);


		for (int i = 0; i < sb.length(); i++) {

			for (int j = 0; j < numbers.length; j++) {

				if (sentence.substring(i,i+1).equals(numbers[j])) {

					if (numbers[j].equals("9")) {
						num = 0;
					}
					else {
						num = Integer.parseInt(numbers[j]) + 1;
					}
					sb.replace(i, i+1, ""+num);

				}

			}

		}

		myScan.close();
		sentence = sb.toString();
		System.out.println(sentence);

	}




}
/**
 * Josh Muszka 
 * 
 * September 20, 2021
 * 
 * This program generates 25 random numbers between 1 and 1000, places them in an array,
 * and then sorts and prints the array
 * 
 **/

import java.util.Arrays;

public class SortingArrays {

	static int [] array = new int [25]; //array	

	public static void main (String[] args) {

		//generates random numbers between 1 and 1000 and places them in the array
		generateNumbers();

		//sort array
		sortArray();

	}

	static void generateNumbers() {

		//generates random numbers between 1 and 1000 and places them in the array
		
		for (int i = 0; i < array.length; i++) {
			array [i] = (int) (Math.random()*1000 + 1);
		}

		System.out.println(Arrays.toString(array)); //print unsorted array
		System.out.println(" ");
	}


	static void sortArray() {

		//sort array

		int hold = 0; //variable to "hold on" to values when swapping them

		for (int i = 0; i < array.length - 1; i++) { // determines the array element which the smallest of the subsequent values is then placed into
			// eg. if i = 0, then the following loop checks all subsequent elements and places the smallest of them into the first element (array[0])
			// if i = 1. then loop checks all subsequent elements and places smallest into the second element (array[1])
			// etc...

			for (int j = 0 + i; j < array.length - 1; j++) {
				// j determines which element will be compared with array[i]

				if (array[i] > array[j+1]) {
					// checks if array[i] is greater than a given element, and if yes then the two elements swap values, if no then nothing changes

					hold = array[i];
					array[i] = array[j+1];
					array[j+1] = hold;

				}
			}

		}

		System.out.println(Arrays.toString(array)); //print sorted array
	}

}

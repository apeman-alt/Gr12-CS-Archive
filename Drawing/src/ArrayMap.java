/*
 * Josh Muszka
 * 
 * November 16, 2021
 * 
 * This program prints a square-grid (random size bewteen 1 and 20) of numbers to the 
 * console (using a 2D array), fills the border spots with
 * the number 10, fills position (8,2) with the number 99 (only if the grid is larger than 8x8),
 * and then fills the remaining spots with the number 0.
 */

import java.util.Arrays;

public class ArrayMap {
	
	private static final int N = (int) (Math.random()*20) + 1; //width and height of grid
	private static int map[][] = new int [N][N];
	
	public static void main (String[] args) {
			
		System.out.println("Grid size: " + N +"x" + N);

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (col%N == 0) { //make sure every row is exactly N spaces long, making it a square
					System.out.println(" ");
				}
				
				//set border numbers to 10
				if (col == 0 || col == N-1 || row == 0 || row == N-1) {
					map[row][col] = 10;
				} 
				//set all other numbers to 0
				else {
					//map[row][col] = 0;
					}
				
				
				//set (8, 2) to 99 (only if N is bigger than 8)
				if (N > 8) {
					if (row == 8 && col == 2) {
						map[row][col] = 99;
					}
				}
				
				
				System.out.printf("%3d", map[row][col]);
			}
		}
		
		
		
		
	}
	
}
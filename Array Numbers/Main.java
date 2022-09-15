import java.io.*;
import java.text.*;

public class Main {
	
	public static void main (String[] args) throws IOException {
		
		int[] num = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		int hold = 0;
		
		//generate numbers
		System.out.println ("NUMBERS:");
		for (int i = 0; i < 12; i++) {
			num[i] = (int) (Math.random() * 100 + 1);

			System.out.print (num[i] + " ");
		}
		
		
		//sort numbers
		for (int i = 0; i < 11; i++)
		{
			if (num[0] > num[1])
			{
				hold = num[0];
				num[0] = num[1];
				num[1] = hold;
				
			}
			
			if (num[1] > num[2])
			{
				hold = num[1];
				num[1] = num[2];
				num[2] = hold;
				
			}
			
			if (num[2] > num[3])
			{
				hold = num[2];
				num[2] = num[3];
				num[3] = hold;
				
			}
			
			if (num[3] > num[4])
			{
				hold = num[3];
				num[3] = num[4];
				num[4] = hold;
				
			}
			
			if (num[4] > num[5])
			{
				hold = num[4];
				num[4] = num[5];
				num[5] = hold;
				
			}
			
			if (num[5] > num[6])
			{
				hold = num[5];
				num[5] = num[6];
				num[6] = hold;
				
			}
			
			if (num[6] > num[7])
			{
				hold = num[6];
				num[6] = num[7];
				num[7] = hold;
				
			}
			
			if (num[7] > num[8])
			{
				hold = num[7];
				num[7] = num[8];
				num[8] = hold;
				
			}
			
			if (num[8] > num[9])
			{
				hold = num[8];
				num[8] = num[9];
				num[9] = hold;
				
			}
			
			if (num[9] > num[10])
			{
				hold = num[9];
				num[9] = num[10];
				num[10] = hold;
				
			}
			
			if (num[10] > num[11])
			{
				hold = num[10];
				num[10] = num[11];
				num[11] = hold;
				
			}
		}
		
		System.out.println (" ");
		System.out.println (" ");
		System.out.println ("LOWEST: " + num[0]);
		System.out.println ("HIGHEST: " + num[11]);
	}
}

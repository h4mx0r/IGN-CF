/*Code Foo Problem #3
 *  "You own a license plate manufacturing company.
 *Write a program that takes a population and determines the simplest pattern
 *that will produce enough unique plates. Since all the plates that match the
 *pattern will be generated, find the pattern that produces the least excess plates.
 *Use a combination of letters (A-Z) and numbers (0-9). "
 *
 *  Written by Clement Kuo - April 2012*/
 import java.util.*;

 public class LicenseGen
 {
	public static void main(String[] args)
	{
		int pop = 0, let = 0, num = 1, total=1;
		//num starts at 1 because you can't have nothing on a license plate
		//also total numbers(10) is less than total letters(26).
		//num and let both represent powers of 10 and 26 respectively
		Scanner kb = new Scanner(System.in);

		while(pop<=0)
		{
			System.out.print("Enter a population: ");
			pop = kb.nextInt();
			if(pop<=0)//Only positive populations!
			{
				System.out.println("Invalid population.");
			}
		}

		while(total<pop)
		{
			total=1;//reset the total each loop
			System.out.println("NUMBER " + num +" LETTER "+let+" TOTAL "+total);
			if(num<=0) //need a way to reset the exponents
			{
				num = let+1;
				let = 0;
			}
			else
			{
				num--;
				let++;
			}
			for(int i=1; i<=let; i++)
			{
				total*=26;
			}
			for(int i=1; i<=num; i++)
			{
				total*=10;
			}
			System.out.println("The final Total is: "+total);
		}
		//Printing the results
		System.out.println("Population:  " + pop +
		"\nLetters:  " + let +
		"\nNumbers:  " + num +
		"\nTotal Plates:  " + total +
		"\nExcess Plates:  " + (total-pop));
	}
 }
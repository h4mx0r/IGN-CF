/*written by Clement Kuo 2012
 *for Code Foo Bonus question*/
import java.util.*;
public class ConnectFour
{
	public static void printBoard(int[][] boardSize)
	{
		int row=0;
		System.out.println(" 1 2 3 4 5 6 7");
		for(int u = 0; u<6; u++)
		{

			System.out.println("===============");
			for(int i = 0; i<7; i++)
			{
				System.out.print("|");
				if(boardSize[i][row]==0)
				{
					System.out.print(" ");
				}
				else if(boardSize[i][row]==1)
				{
					System.out.print("O");
				}
				else
					System.out.print("X");
			}
			System.out.print("|\n");
			row++;
		}
		System.out.println("===============");
	}

	public static boolean checkWinner(int[][] boardSize)
	{
		int hfour = 0;
		int afour = 0;
		for(int i = 0; i<6; i++)
		{//test horizontal options
			for(int u = 0; u<7; u++)
			{
				if(boardSize[u][i]==1)
				{
					afour=0;
					hfour++;
					if(hfour==4)
						return false;
				}
				else if(boardSize[u][i]==2)
				{
					hfour=0;
					afour++;
					if(afour==4)
						return false;
				}
				else
				{
					hfour = 0;
					afour = 0;
				}
			}
			hfour = 0; //so it doesn't keep counting to the next row
			afour = 0;
		}
		for(int u = 0; u<7; u++)
		{//test for vertical wins
			for(int i = 0; i<6; i++)
			{
				if(boardSize[u][i]==1)
				{
					afour=0;
					hfour++;
					if(hfour==4)
						return false;
				}
				else if(boardSize[u][i]==2)
				{
					hfour=0;
					afour++;
					if(afour==4)
						return false;
				}
				else
				{
					hfour = 0;
					afour = 0;
				}
			}
			hfour = 0; //so it doesn't keep counting to the next column.
			afour = 0;
		}
		for(int a = 0; a<4; a++)
		{//finally, I must test for diagonals, first top left to bottom right
			for(int b = 0; b<3; b++)
			{//no matter what, such a diagonal will overlap into a 4x3 grid
				if(boardSize[a][b]==1)
				{
					hfour++;
					for(int i=0; i<4; i++)
					{
						if(boardSize[a+i][b+i]==1)
						{
							hfour++;
							if(hfour==4)
								return false;
						}
						else
							hfour=0;
					}
				}
				if(boardSize[a][b]==2)
				{
					afour++;
					for(int i = 0; i<4; i++)
					{
						if(boardSize[a+i][b+i]==2)
						{
							afour++;
							if(afour==4)
								return false;
						}
						else
							afour=0;
					}
				}
			}
		}
		for(int a = 0; a<4; a++)
		{//now we must check diagonals from bottom left to top right
			for(int b = 3; b<6; b++)
			{
				if(boardSize[a][b]==1)
				{
					hfour++;
					for(int i=0; i<4; i++)
					{
						if(boardSize[a+i][b-i]==1)
						{
							hfour++;
							if(hfour==4)
								return false;
						}
						else
							hfour=0;
					}
				}
				if(boardSize[a][b]==2)
				{
					afour++;
					for(int i = 0; i<4; i++)
					{
						if(boardSize[a+i][b-i]==2)
						{
							afour++;
							if(afour==4)
								return false;
						}
						else
							afour=0;
					}
				}
			}
		}//now we must check diagonals from bottom left to top right
		return true;
	}

	public static void main(String[]args)
	{
		final int human = 1;
		final int ai = 2;
		int valid = 0;
		int slot, cpu;
		int check = 0, row = 5;
		Scanner kb = new Scanner(System.in);
		int[][] boardSize = new int[7][6];
		System.out.println("Welcome to Connect Four!\n");
		while(checkWinner(boardSize))
		{//Game keeps going until checkWinner declares a winner
			printBoard(boardSize);
			System.out.println("Choose a slot to drop in!");
			slot = kb.nextInt();
			while(valid!=1)
			{
				while(slot < 1 || slot > 7)
				{//checks for valid column selection
					System.out.println("Not a valid slot. Try again: ");
					slot = kb.nextInt();
				}
				if(boardSize[slot-1][0]!=0)
				{//checks if column is full
					System.out.println("Column is full. Try again: ");
					slot = kb.nextInt();
				}
				if(boardSize[slot-1][0]==0 && (slot >= 1 && slot <=7))
				{
					valid = 1;
				}
			}
			valid = 0;
			slot--;
			while(check==0)
			{//placing the human player's piece
				if(boardSize[slot][row] == 0)
				{
					boardSize[slot][row] = human;
					check++;
				}
				row--;
			}
			check=0;
			row = 5;
			if(checkWinner(boardSize)==false)
			{//checking winner after human move means only a human could have won if return false;
				printBoard(boardSize);
				System.out.println("HUMAN WINS");
				break;
			}
			while(check==0)
			{//placing the AI's piece
				cpu = (int)(Math.random()*7);
				if(boardSize[cpu][0]==0)
				{//checks if column is full for AI
					while(check==0)
					{
						if(boardSize[cpu][row]==0)
						{
							boardSize[cpu][row] = ai;
							check++;
						}
						else
						{
							row--;
						}
					}
				}
			}
			check = 0;
			row = 5;
			if(checkWinner(boardSize)==false)
			{//checking winner after AI move means only an AI could have won if return false;
				printBoard(boardSize);
				System.out.println("AI WINS");
				break;
			}
		} //Has checkWinner found a winner yet?
		System.out.println("Thanks for playing!");
	}
}
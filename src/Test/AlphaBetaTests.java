package Test;


import java.util.Random;
import java.util.Scanner;
import Controller.AlphaBeta;
import Model.CheckersGameState;
import Model.Move;
import Model.ThirtyFiveRepBoard;

public class AlphaBetaTests
{
	public static void main(String[] args)
	{
		CheckersGameState cb = new ThirtyFiveRepBoard();
		AlphaBeta test= new AlphaBeta();
		Scanner input = new Scanner(System.in);

		System.out.println("Please note that error checks for bad user inputs are not implemented. Please follow the directions");
		
		int i = 0;
		int curr;
		while (i < 35)
		{
			curr = i;
			while (i < curr + 4)
			{
				System.out.print("-" + Integer.toString(i));
				i++;
			}
			
			System.out.println();
			
			curr = i;
			while (i < curr + 4)
			{
				System.out.print(Integer.toString(i) + "-");
				i++;
			}
			
			i++;
			System.out.println();
		}
		System.out.println("****************************************************************");
		cb.printState();
		Move move = test.alphabeta(cb, 5);
		System.out.println(move);
		
		}
	}

package Test;

import java.util.Scanner;
import Controller.AlphaBeta;
import Model.CheckersGameState;
import Model.Move;
import Model.ThirtyFiveRepBoard;
import Model.ThirtyFiveRepCheckerPiece;

public class AlphaBetaTests
{
	public static void main(String[] args)
	{
		AlphaBetaTests obj = new AlphaBetaTests();
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
		
		obj.apexTest();
	}
	
	public void apexTest()
	{
		System.out.println("APEX TEST");
		ThirtyFiveRepCheckerPiece[] set = new ThirtyFiveRepCheckerPiece[35];
		AlphaBeta tester = new AlphaBeta();

		set[6] = new ThirtyFiveRepCheckerPiece("Black");
		set[28] = new ThirtyFiveRepCheckerPiece("White");
		
		CheckersGameState state = new ThirtyFiveRepBoard(set);
		state.printState();
		
		Move move = tester.alphabeta(state, 5);
		System.out.println(move);
		
	}
}

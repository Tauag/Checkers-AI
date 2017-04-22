package Test;

import Controller.AlphaBeta;
import Model.ThirtyFiveRepBoard;
import Model.ThirtyFiveRepCheckerPiece;

public class AlphaBetaTests
{
	public static void main(String[] args)
	{
		AlphaBetaTests obj = new AlphaBetaTests();
//		obj.apexTest();
		obj.advTest();
	}
	
	public void apexTest()
	{
		System.out.println("APEX TEST");
		ThirtyFiveRepCheckerPiece[] set = new ThirtyFiveRepCheckerPiece[35];

		// If they exist at the apex points
//		set[6] = new ThirtyFiveRepCheckerPiece("Black");
//		set[28] = new ThirtyFiveRepCheckerPiece("White");
		
		
//		set[6] = new ThirtyFiveRepCheckerPiece("Black");
//		set[6].toggleKing();
//		set[28] = new ThirtyFiveRepCheckerPiece("White");
//		set[28].toggleKing();
		
		// Active man at 6
//		set[6] = new ThirtyFiveRepCheckerPiece("Black");
//		set[11] = new ThirtyFiveRepCheckerPiece("White");
//		set[10] = new ThirtyFiveRepCheckerPiece("White");
		
		// Active man at 28
//		set[28] = new ThirtyFiveRepCheckerPiece("White");
//		set[23] = new ThirtyFiveRepCheckerPiece("Black");
//		set[24] = new ThirtyFiveRepCheckerPiece("Black");
		
		ThirtyFiveRepBoard state = new ThirtyFiveRepBoard(set);
		state.printState();
				
//		System.out.println(state.apexheuristic("Black"));
		System.out.println(state.apexheuristic("White"));
		
	}
	
	public void advTest()
	{
		System.out.println("ADV TEST");
		ThirtyFiveRepCheckerPiece[] set = new ThirtyFiveRepCheckerPiece[35];
		
//		set[4-7] = new ThirtyFiveRepCheckerPiece("Black");
//		set[9-12] = new ThirtyFiveRepCheckerPiece("Black");
		
		set[4] = new ThirtyFiveRepCheckerPiece("White");
		
		
		ThirtyFiveRepBoard state = new ThirtyFiveRepBoard(set);
		state.printState();
		
		System.out.println(state.advheuristic("White"));
//		System.out.println(state.advheuristic("Black"));
	}
}

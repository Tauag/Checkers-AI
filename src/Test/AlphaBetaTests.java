package Test;

import Controller.AlphaBeta;
import Model.CheckersGameState;
import Model.ThirtyFiveRepBoard;
import Model.ThirtyFiveRepCheckerPiece;

public class AlphaBetaTests
{
	public static void main(String[] args)
	{
		AlphaBetaTests obj = new AlphaBetaTests();
//		obj.apexTest();
//		obj.advTest();
//		obj.kcentTest();
//		obj.mobTest();
//		obj.poleTest();
//		obj.relTest();
		obj.AlphaBetaSearchTest();
	}
	
	public void AlphaBetaSearchTest()
	{
		System.out.println("ALPHABETA TEST");
		ThirtyFiveRepCheckerPiece[] set = new ThirtyFiveRepCheckerPiece[35];
		set[6] = new ThirtyFiveRepCheckerPiece("Black");
		set[11] = new ThirtyFiveRepCheckerPiece("White");
		AlphaBeta Tester = new AlphaBeta();
		ThirtyFiveRepBoard Board = new ThirtyFiveRepBoard("Black", set);
		Board.initBoard();
		while(!Board.isTerminal())
		{
			System.out.println("**********************************");
			Board = (ThirtyFiveRepBoard) Board.result(Tester.alphabeta(Board, 3));
			Board.printState();
		}
		Board.printState();
	}
	
	public void apexTest()
	{
		System.out.println("APEX TEST");
		ThirtyFiveRepCheckerPiece[] set = new ThirtyFiveRepCheckerPiece[35];

		// If they exist at the apex points
		set[6] = new ThirtyFiveRepCheckerPiece("Black");
		set[28] = new ThirtyFiveRepCheckerPiece("White");
		
		
		set[6] = new ThirtyFiveRepCheckerPiece("Black");
		set[6].turnKing();
		set[28] = new ThirtyFiveRepCheckerPiece("White");
		set[28].turnKing();
		
		// Active man at 6
		set[6] = new ThirtyFiveRepCheckerPiece("Black");
		set[11] = new ThirtyFiveRepCheckerPiece("White");
		set[10] = new ThirtyFiveRepCheckerPiece("White");
		
		// Active man at 28
		set[28] = new ThirtyFiveRepCheckerPiece("White");
		set[23] = new ThirtyFiveRepCheckerPiece("Black");
		set[24] = new ThirtyFiveRepCheckerPiece("Black");
		
		ThirtyFiveRepBoard state = new ThirtyFiveRepBoard("Black", set);
		state.printState();
				
		System.out.println(state.apexheuristic("Black"));
		System.out.println(state.apexheuristic("White"));
		
	}
	
	/*
	 * Nathan's summary:
	 * If WHITE has passive men in positions (4-7, 9-12), white gets +1 advantage
	 * If BLACK has passive men in positions (22-25, 27-30), black gets +1 advantage
	 * If either side has passive men in positions (13-16, 18-21), that side gets -1 advantage
	 */
	public void advTest()
	{
		System.out.println("ADV TEST");
		ThirtyFiveRepCheckerPiece[] set = new ThirtyFiveRepCheckerPiece[35];
		
		// All white pieces for +1
		set[4] = new ThirtyFiveRepCheckerPiece("White");
		set[5] = new ThirtyFiveRepCheckerPiece("White");
		set[6] = new ThirtyFiveRepCheckerPiece("White");
		set[7] = new ThirtyFiveRepCheckerPiece("Black");
		set[9] = new ThirtyFiveRepCheckerPiece("White");
		set[10] = new ThirtyFiveRepCheckerPiece("White");
		set[11] = new ThirtyFiveRepCheckerPiece("White");
		set[12] = new ThirtyFiveRepCheckerPiece("White");
		
		// All black pieces for +1
		set[22] = new ThirtyFiveRepCheckerPiece("Black");
		set[23] = new ThirtyFiveRepCheckerPiece("Black");
		set[24] = new ThirtyFiveRepCheckerPiece("Black");
		set[25] = new ThirtyFiveRepCheckerPiece("Black");
		set[27] = new ThirtyFiveRepCheckerPiece("White");
		set[28] = new ThirtyFiveRepCheckerPiece("Black");
		set[29] = new ThirtyFiveRepCheckerPiece("Black");
		set[30] = new ThirtyFiveRepCheckerPiece("Black");
		
		
		set[15] = new ThirtyFiveRepCheckerPiece("Black");
		set[18] = new ThirtyFiveRepCheckerPiece("White");
		
		ThirtyFiveRepBoard state = new ThirtyFiveRepBoard("Black", set);
		state.printState();
		
		System.out.println("White score: " + state.advheuristic("White"));
		System.out.println("Black score: " + state.advheuristic("Black"));
	}
	
	/*
	 * Summary: Passive kings in the dead center of the board are checked here
	 */
	public void kcentTest()
	{
		System.out.println("KCENT TEST");
		ThirtyFiveRepCheckerPiece[] set = new ThirtyFiveRepCheckerPiece[35];
		
		
		// Whites
//		set[10] = new ThirtyFiveRepCheckerPiece("White");
//		set[10].turnKing();
//		
//		set[11] = new ThirtyFiveRepCheckerPiece("White");
//		set[11].turnKing();
//		
//		set[14] = new ThirtyFiveRepCheckerPiece("White");
//		set[14].turnKing();
//
//		set[15] = new ThirtyFiveRepCheckerPiece("White");
//		set[15].turnKing();
//
//		set[19] = new ThirtyFiveRepCheckerPiece("White");
//		set[19].turnKing();
//
//		set[20] = new ThirtyFiveRepCheckerPiece("White");
//		set[20].turnKing();
//
//		set[23] = new ThirtyFiveRepCheckerPiece("White");
//		set[23].turnKing();
//
//		set[24] = new ThirtyFiveRepCheckerPiece("White");
//		set[24].turnKing();


		// Blacks
		set[10] = new ThirtyFiveRepCheckerPiece("Black");
		set[10].turnKing();
		
		set[11] = new ThirtyFiveRepCheckerPiece("Black");
		set[11].turnKing();
		
		set[14] = new ThirtyFiveRepCheckerPiece("Black");
		set[14].turnKing();

		set[15] = new ThirtyFiveRepCheckerPiece("Black");
		set[15].turnKing();

		set[19] = new ThirtyFiveRepCheckerPiece("Black");
		set[19].turnKing();

		set[20] = new ThirtyFiveRepCheckerPiece("Black");
		set[20].turnKing();

		set[23] = new ThirtyFiveRepCheckerPiece("Black");
		set[23].turnKing();

		set[24] = new ThirtyFiveRepCheckerPiece("Black");
		set[24].turnKing();
		
		ThirtyFiveRepBoard state = new ThirtyFiveRepBoard("Black", set);
		state.printState();
		
		System.out.println("White score: " + state.kcentheuristic("White"));
		System.out.println("Black score: " + state.kcentheuristic("Black"));
	}
	
	
	public void mobTest()
	{
		System.out.println("MOB TEST");
		ThirtyFiveRepCheckerPiece[] set = new ThirtyFiveRepCheckerPiece[35];

		set[0] = new ThirtyFiveRepCheckerPiece("Black");
		set[1] = new ThirtyFiveRepCheckerPiece("Black");
		set[2] = new ThirtyFiveRepCheckerPiece("Black");
		set[3] = new ThirtyFiveRepCheckerPiece("Black");
		set[19] = new ThirtyFiveRepCheckerPiece("White");

		set[6] = new ThirtyFiveRepCheckerPiece("Black");
		set[13] = new ThirtyFiveRepCheckerPiece("Black");
		set[12] = new ThirtyFiveRepCheckerPiece("Black");
		set[14] = new ThirtyFiveRepCheckerPiece("Black");		
		
		
		ThirtyFiveRepBoard state = new ThirtyFiveRepBoard("Black", set);
		state.printState();
		
		System.out.println("White score: " + state.mobheuristic("White"));
		System.out.println("Black score: " + state.mobheuristic("Black"));
	}
	
	
	public void poleTest()
	{
		System.out.println("POLE TEST");
		ThirtyFiveRepCheckerPiece[] set = new ThirtyFiveRepCheckerPiece[35];

		set[0] = new ThirtyFiveRepCheckerPiece("Black");
		set[5] = new ThirtyFiveRepCheckerPiece("Black");
		set[5].turnKing();
		set[6] = new ThirtyFiveRepCheckerPiece("Black");
		set[9] = new ThirtyFiveRepCheckerPiece("Black");
		set[10] = new ThirtyFiveRepCheckerPiece("Black");
		
		set[28] = new ThirtyFiveRepCheckerPiece("White");
		set[29] = new ThirtyFiveRepCheckerPiece("White");
		set[23] = new ThirtyFiveRepCheckerPiece("White");
		set[31] = new ThirtyFiveRepCheckerPiece("White");
		set[30] = new ThirtyFiveRepCheckerPiece("White");


		
		ThirtyFiveRepBoard state = new ThirtyFiveRepBoard("Black", set);
		state.printState();
		
		System.out.println("White score: " + state.poleheuristic("White"));
		System.out.println("Black score: " + state.poleheuristic("Black"));
	}
	
	
	public void relTest()
	{
		System.out.println("REL TEST");
		ThirtyFiveRepCheckerPiece[] set = new ThirtyFiveRepCheckerPiece[35];

		set[0] = new ThirtyFiveRepCheckerPiece("Black");
		set[5] = new ThirtyFiveRepCheckerPiece("Black");
		set[5].turnKing();
		set[6] = new ThirtyFiveRepCheckerPiece("Black");
		set[9] = new ThirtyFiveRepCheckerPiece("Black");
//		set[10] = new ThirtyFiveRepCheckerPiece("Black");
		
		set[28] = new ThirtyFiveRepCheckerPiece("White");
		set[29] = new ThirtyFiveRepCheckerPiece("White");
//		set[23] = new ThirtyFiveRepCheckerPiece("White");
//		set[30] = new ThirtyFiveRepCheckerPiece("White");
//		set[31] = new ThirtyFiveRepCheckerPiece("White");
		
		
		ThirtyFiveRepBoard state = new ThirtyFiveRepBoard("Black", set);
		state.printState();
		
		System.out.println("White score: " + state.relativecountheuristic("White"));
		System.out.println("Black score: " + state.relativecountheuristic("Black"));

	}
	
}

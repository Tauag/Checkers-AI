package Test;

import java.util.Random;
import java.util.Scanner;

import Controller.AlphaBeta;
import Model.CheckersGameState;
import Model.Move;
import Model.ThirtyFiveRepBoard;
import Model.ThirtyFiveRepCheckerPiece;

public class HeuristicTester {
	public static void main(String[] args) {
		centerTest();
		cntTest();
	}
	
	public static void centerTest() {
		ThirtyFiveRepCheckerPiece[] set = new ThirtyFiveRepCheckerPiece[35];

		set[10] = new ThirtyFiveRepCheckerPiece("Black");
		set[11] = new ThirtyFiveRepCheckerPiece("White");
		
		set[14] = new ThirtyFiveRepCheckerPiece("White");
		set[15] = new ThirtyFiveRepCheckerPiece("Black");
		
		set[19] = new ThirtyFiveRepCheckerPiece("White");
		set[20] = new ThirtyFiveRepCheckerPiece("White");
		
		set[23] = new ThirtyFiveRepCheckerPiece("Black");
		set[24] = new ThirtyFiveRepCheckerPiece("White");
		
		ThirtyFiveRepBoard state = new ThirtyFiveRepBoard(set);
		System.out.println("Center heuristic test for passive man");
		state.printState();
		
		System.out.print("Heuristic For Black: ");
		state.centheuristic("Black"); 
		
		System.out.print("Heuristic For White: ");
		state.centheuristic("White");
	}
	
	public static void cntTest() {
		ThirtyFiveRepCheckerPiece[] set = new ThirtyFiveRepCheckerPiece[35];

		set[10] = new ThirtyFiveRepCheckerPiece("White");
		set[10].turnKing();
		set[11] = new ThirtyFiveRepCheckerPiece("Black");
		
		set[14] = new ThirtyFiveRepCheckerPiece("Black");
		set[14].turnKing();
		set[15] = new ThirtyFiveRepCheckerPiece("White");
		
	//	set[19] = new ThirtyFiveRepCheckerPiece("Black");
		set[20] = new ThirtyFiveRepCheckerPiece("White");
		set[20].turnKing();
		
	//	set[23] = new ThirtyFiveRepCheckerPiece("Black");
		set[24] = new ThirtyFiveRepCheckerPiece("Black");
		
		ThirtyFiveRepBoard state = new ThirtyFiveRepBoard(set);
		System.out.println("Center heuristic test for active man");
		state.printState();
		
		System.out.print("Heuristic For Black: ");
		state.cntrheuristic("Black");
		
		System.out.print("Heuristic For White: ");
		state.cntrheuristic("White");
	}
}

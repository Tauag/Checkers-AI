package Test;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

import Controller.AlphaBeta;
import Model.CheckersGameState;
import Model.Move;
import Model.ThirtyFiveRepBoard;
import Model.ThirtyFiveRepCheckerPiece;

public class HeuristicTester {
	public static void main(String[] args) {
		centerTest();
		cntTest();
		backTest();
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
		
		ThirtyFiveRepBoard state = new ThirtyFiveRepBoard("Black", set);
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
		
		set[19] = new ThirtyFiveRepCheckerPiece("Black");
		set[20] = new ThirtyFiveRepCheckerPiece("White");
		set[20].turnKing();
		
	//	set[23] = new ThirtyFiveRepCheckerPiece("Black");
		set[24] = new ThirtyFiveRepCheckerPiece("Black");
		
		ThirtyFiveRepBoard state = new ThirtyFiveRepBoard("Black", set);
		System.out.println("Center heuristic test for active man");
		state.printState();
		
		System.out.print("Heuristic For Black: ");
		state.cntrheuristic("Black");
		
		System.out.print("Heuristic For White: ");
		state.cntrheuristic("White");
	}
	
	public static void backTest() {
		ThirtyFiveRepCheckerPiece[] set = new ThirtyFiveRepCheckerPiece[35];
		set[0] = new ThirtyFiveRepCheckerPiece("Black");
		//set[0].turnKing();
		set[1] = new ThirtyFiveRepCheckerPiece("Black");
		set[2] = new ThirtyFiveRepCheckerPiece("Black");
		set[3] = new ThirtyFiveRepCheckerPiece("Black");
		set[5] = new ThirtyFiveRepCheckerPiece("White");
		
		set[19] = new ThirtyFiveRepCheckerPiece("White");
		//set[19].turnKing();
		set[11] = new ThirtyFiveRepCheckerPiece("Black");
		set[15] = new ThirtyFiveRepCheckerPiece("Black");
		
		set[31] = new ThirtyFiveRepCheckerPiece("White");
		set[32] = new ThirtyFiveRepCheckerPiece("White");
		set[32].turnKing();
		set[33] = new ThirtyFiveRepCheckerPiece("White");
		set[34] = new ThirtyFiveRepCheckerPiece("White");
		
		ThirtyFiveRepBoard state = new ThirtyFiveRepBoard("Black", set);
		System.out.println("Back Row Testing");
		state.printState();
		
		System.out.print("Back heuristic for Black piece: ");
		state.backheuristic("Black");
		
		System.out.print("Back heuristic for White piece: ");
		state.backheuristic("White");
	}
}

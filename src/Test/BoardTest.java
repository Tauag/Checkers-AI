package Test;
import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.Test;

import Model.CheckersGameState;
import Model.ThirtyFiveRepBoard;
import Model.ThirtyFiveRepCheckerPiece;

public class BoardTest {
	
	
	@Test
	public void double_jump_white(){
		ThirtyFiveRepCheckerPiece[] pieces = new ThirtyFiveRepCheckerPiece[35];
		//pieces[2] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[6] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[15] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[20] = new ThirtyFiveRepCheckerPiece("White");
		CheckersGameState cb = new ThirtyFiveRepBoard("White", pieces);		
		Scanner input = new Scanner(System.in);
		int choice, sz;
		System.out.println("**********************************");
			cb.printState();
			while(!cb.isTerminal()){
				System.out.println("Choose an available actions: (You only need to input the number)");
				System.out.println(cb.player() + " turn");
				if(!cb.actions().isEmpty()){
					sz = cb.actions().size();
					for(int j = 0; j < sz; j++){
						System.out.print(Integer.toString(j) + ": ");
						System.out.println(cb.actions().get(j));
					}
					choice = input.nextInt();
					cb = cb.result(cb.actions().get(choice));
					System.out.println("**********************************");
				}
				cb.printState();
			}
	}
	
	@Test
	public void double_jump_black(){
		ThirtyFiveRepCheckerPiece[] pieces = new ThirtyFiveRepCheckerPiece[35];
		//pieces[23] = new ThirtyFiveRepCheckerPiece("White");
		pieces[19] = new ThirtyFiveRepCheckerPiece("White");
		pieces[28] = new ThirtyFiveRepCheckerPiece("White");
		pieces[15] = new ThirtyFiveRepCheckerPiece("Black");
		CheckersGameState cb = new ThirtyFiveRepBoard("Black", pieces);		
		Scanner input = new Scanner(System.in);
		int choice, sz;
		System.out.println("**********************************");
			cb.printState();
			while(!cb.isTerminal()){
				System.out.println("Choose an available actions: (You only need to input the number)");
				System.out.println(cb.player() + " turn");
				if(!cb.actions().isEmpty()){
					sz = cb.actions().size();
					for(int j = 0; j < sz; j++){
						System.out.print(Integer.toString(j) + ": ");
						System.out.println(cb.actions().get(j));
					}
					choice = input.nextInt();
					cb = cb.result(cb.actions().get(choice));
					System.out.println("**********************************");
				}
				cb.printState();
			}
	}
	
	@Test
	public void cyclic_jump_white(){
		ThirtyFiveRepCheckerPiece[] pieces = new ThirtyFiveRepCheckerPiece[35];
		pieces[6] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[7] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[15] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[16] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[20] = new ThirtyFiveRepCheckerPiece("White");
		CheckersGameState cb = new ThirtyFiveRepBoard("White", pieces);		
		Scanner input = new Scanner(System.in);
		int choice, sz;
		System.out.println("**********************************");
			cb.printState();
			while(!cb.isTerminal()){
				System.out.println("Choose an available actions: (You only need to input the number)");
				System.out.println(cb.player() + " turn");
				if(!cb.actions().isEmpty()){
					sz = cb.actions().size();
					for(int j = 0; j < sz; j++){
						System.out.print(Integer.toString(j) + ": ");
						System.out.println(cb.actions().get(j));
					}
					choice = input.nextInt();
					cb = cb.result(cb.actions().get(choice));
					System.out.println("**********************************");
				}
				cb.printState();
			}
	}
	
	@Test
	public void cyclic_jump_black(){
		ThirtyFiveRepCheckerPiece[] pieces = new ThirtyFiveRepCheckerPiece[35];
		pieces[20] = new ThirtyFiveRepCheckerPiece("White");
		pieces[19] = new ThirtyFiveRepCheckerPiece("White");
		pieces[28] = new ThirtyFiveRepCheckerPiece("White");
		pieces[29] = new ThirtyFiveRepCheckerPiece("White");
		pieces[15] = new ThirtyFiveRepCheckerPiece("Black");
		CheckersGameState cb = new ThirtyFiveRepBoard("Black", pieces);		
		Scanner input = new Scanner(System.in);
		int choice, sz;
		System.out.println("**********************************");
			cb.printState();
			while(!cb.isTerminal()){
				System.out.println("Choose an available actions: (You only need to input the number)");
				System.out.println(cb.player() + " turn");
				if(!cb.actions().isEmpty()){
					sz = cb.actions().size();
					for(int j = 0; j < sz; j++){
						System.out.print(Integer.toString(j) + ": ");
						System.out.println(cb.actions().get(j));
					}
					choice = input.nextInt();
					cb = cb.result(cb.actions().get(choice));
					System.out.println("**********************************");
				}
				cb.printState();
			}
	}
	
	public void test_isActive(){
		ThirtyFiveRepCheckerPiece[] pieces = new ThirtyFiveRepCheckerPiece[35];
		pieces[31] = new ThirtyFiveRepCheckerPiece("White");
		pieces[27] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[32] = new ThirtyFiveRepCheckerPiece("White");
		pieces[20] = new ThirtyFiveRepCheckerPiece("White");
		pieces[25] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[30] = new ThirtyFiveRepCheckerPiece("White");
		ThirtyFiveRepBoard board = new ThirtyFiveRepBoard("White", pieces);
		
		assertEquals(true, board.isActive(31, pieces[31]));
		assertEquals(true, board.isActive(32, pieces[32]));
		assertEquals(false, board.isActive(20, pieces[20]));
		assertEquals(false, board.isActive(27, pieces[27]));
		assertEquals(false, board.isActive(25, pieces[25]));
		assertEquals(false, board.isActive(30, pieces[30]));
		
		pieces = new ThirtyFiveRepCheckerPiece[35];
		pieces[14] = new ThirtyFiveRepCheckerPiece("White");
		pieces[14].turnKing();
		pieces[19] = new ThirtyFiveRepCheckerPiece("Black");
		board = new ThirtyFiveRepBoard("White", pieces);
		
		assertEquals(true, board.isActive(14, pieces[14]));
	}
	
	@Test
	public void test_canSingleMove(){
		ThirtyFiveRepCheckerPiece[] pieces = new ThirtyFiveRepCheckerPiece[35];
		pieces[25] = new ThirtyFiveRepCheckerPiece("White");
		pieces[9] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[2] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[6] = new ThirtyFiveRepCheckerPiece("White");
		pieces[7] = new ThirtyFiveRepCheckerPiece("White");
		ThirtyFiveRepBoard board = new ThirtyFiveRepBoard("White", pieces);
		
		assertEquals(true, board.canSingleMove(25, pieces[25]));
		assertEquals(true, board.canSingleMove(9, pieces[9]));
		assertEquals(false, board.canSingleMove(2, pieces[2]));
		assertEquals(true, board.canSingleMove(6, pieces[6]));
		assertEquals(true, board.canSingleMove(7, pieces[7]));
		
		pieces = new ThirtyFiveRepCheckerPiece[35];
		pieces[1] = new ThirtyFiveRepCheckerPiece("White");
		pieces[31] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[1].turnKing();
		pieces[31].turnKing();
		board = new ThirtyFiveRepBoard("White", pieces);
		
		assertEquals(true, board.canSingleMove(1, pieces[1]));
		assertEquals(true, board.canSingleMove(31, pieces[31]));
	}
	
	@Test
	public void test_isPole(){
		ThirtyFiveRepCheckerPiece[] pieces = new ThirtyFiveRepCheckerPiece[35];
		pieces[25] = new ThirtyFiveRepCheckerPiece("White");
		pieces[9] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[5] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[2] = new ThirtyFiveRepCheckerPiece("Black");
		pieces[22] = new ThirtyFiveRepCheckerPiece("Black");
		ThirtyFiveRepBoard board = new ThirtyFiveRepBoard("White", pieces);
		
		assertEquals(true, board.isPole(25, pieces[25]));
		assertEquals(false, board.isPole(9, pieces[9]));
		assertEquals(false, board.isPole(5, pieces[5]));
		assertEquals(true, board.isPole(2, pieces[2]));
		assertEquals(true, board.isPole(22, pieces[22]));
		
		pieces = new ThirtyFiveRepCheckerPiece[35];
		pieces[31] = new ThirtyFiveRepCheckerPiece("White");
		pieces[22] = new ThirtyFiveRepCheckerPiece("White");
		pieces[32] = new ThirtyFiveRepCheckerPiece("White");
		board = new ThirtyFiveRepBoard("White", pieces);
		
		assertEquals(true, board.isPole(31, pieces[31]));
		
		pieces = new ThirtyFiveRepCheckerPiece[35];
		pieces[13] = new ThirtyFiveRepCheckerPiece("White");
		pieces[22] = new ThirtyFiveRepCheckerPiece("White");
		pieces[4] = new ThirtyFiveRepCheckerPiece("White");
		pieces[12] = new ThirtyFiveRepCheckerPiece("White");
		pieces[21] = new ThirtyFiveRepCheckerPiece("White");
		pieces[3] = new ThirtyFiveRepCheckerPiece("White");
		board = new ThirtyFiveRepBoard("White", pieces);
		
		assertEquals(true, board.isPole(13, pieces[13]));
	}
}

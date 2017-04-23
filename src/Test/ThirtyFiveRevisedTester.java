package Test;
import java.util.Random;
import java.util.Scanner;

import Model.CheckersGameState;
import Model.ThirtyFiveRepBoard;
import Model.ThirtyFiveRepCheckerPiece;

public class ThirtyFiveRevisedTester {
	public static void main(String[] args){
		
		//CheckersGameState cb = new ThirtyFiveRepBoardRevised();
		
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
	
}

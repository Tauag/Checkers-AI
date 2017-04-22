package Test;
import java.util.Random;
import java.util.Scanner;

import Model.CheckersGameState;
import Model.ThirtyFiveRepBoard;
import Model.ThirtyFiveRepBoardRevised;
import Model.ThirtyFiveRepCheckerPiece;

public class ThirtyFiveRevisedTester {
	public static void main(String[] args){
		
		//CheckersGameState cb = new ThirtyFiveRepBoardRevised();
		ThirtyFiveRepCheckerPiece[] pieces = new ThirtyFiveRepCheckerPiece[35];
		pieces[19] = new ThirtyFiveRepCheckerPiece("White");
		pieces[20] = new ThirtyFiveRepCheckerPiece("White");
		pieces[28] = new ThirtyFiveRepCheckerPiece("White");
		pieces[29] = new ThirtyFiveRepCheckerPiece("White");
		pieces[15] = new ThirtyFiveRepCheckerPiece("Black");
		CheckersGameState cb = new ThirtyFiveRepBoardRevised("Black", pieces);
		Scanner input = new Scanner(System.in);
		int choice, sz;
		
		System.out.println("Please note that error checks for bad user inputs are not implemented. Please follow the directions");
		
		int i = 0;
		int curr;
		while(i < 35){
			curr = i;
			while(i < curr + 4){
				System.out.print("-" + Integer.toString(i));
				i++;
			}
			System.out.println();
			curr = i;
			while(i < curr + 4){
				System.out.print(Integer.toString(i) + "-");
				i++;
			}
			i++;
			System.out.println();
		}	
		
		System.out.println("Do you want to see a random game? 0: yes 1: no, I want to play the game myself");
		choice = input.nextInt();
		if(choice == 0){
			Random rand = new Random();
			cb.printState();
			while(!cb.isTerminal()){
				System.out.println("**********************************");
				if(!cb.actions().isEmpty()){
					sz = cb.actions().size();
					choice = rand.nextInt(sz);
					System.out.println(cb.actions().get(choice));
					cb = cb.result(cb.actions().get(choice));
				}
				cb.printState();
			}
		}
		else{
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
}

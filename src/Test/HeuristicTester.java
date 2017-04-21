package Test;

import java.util.Random;
import java.util.Scanner;

import Controller.AlphaBeta;
import Model.CheckersGameState;
import Model.Move;
import Model.ThirtyFiveRepBoard;

public class HeuristicTester {
	public static void main(String[] args) {
//		CheckersGameState cb = new ThirtyFiveRepBoard();
//		Scanner input = new Scanner(System.in);
//		int choice, sz;
//		
//		System.out.println("Please note that error checks for bad user inputs are not implemented. Please follow the directions");
//		
//		int i = 0;
//		int curr;
//		while(i < 35){
//			curr = i;
//			while(i < curr + 4){
//				System.out.print("-" + Integer.toString(i));
//				i++;
//			}
//			System.out.println();
//			curr = i;
//			while(i < curr + 4){
//				System.out.print(Integer.toString(i) + "-");
//				i++;
//			}
//			i++;
//			System.out.println();
//		}	
//		Random rand = new Random();
//		cb.printState();
//		while(!cb.isTerminal()){
//			System.out.println("**********************************");
//			if(!cb.actions().isEmpty()){
//				sz = cb.actions().size();
//				choice = rand.nextInt(sz);
//				System.out.println(cb.actions().get(choice));
//				cb = cb.result(cb.actions().get(choice));
//			}
//			cb.printState();
//		}
		centerTest();
	}
	
	public static void centerTest() {
		ThirtyFiveRepBoard tb = new ThirtyFiveRepBoard();
		tb.printState();
		
	}
}

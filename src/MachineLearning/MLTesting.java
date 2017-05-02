package MachineLearning;

import java.io.IOException;

import Model.MLThirtyFiveRepBoard;
import Model.ThirtyFiveRepCheckerPiece;

public class MLTesting {
	public static void main(String[] args) throws IOException
	{

			//Initialize all the tools we need
			//ReGenerateWeights("Black");
			//ReGenerateWeights("White");
			int movestaken = 0;
			int maxmoves = 2;
			ThirtyFiveRepCheckerPiece[] set = new ThirtyFiveRepCheckerPiece[35];
			set[5] = new ThirtyFiveRepCheckerPiece("Black");
			set[7] = new ThirtyFiveRepCheckerPiece("Black");
			set[23] = new ThirtyFiveRepCheckerPiece("Black");
			set[25] = new ThirtyFiveRepCheckerPiece("Black");
			set[15] = new ThirtyFiveRepCheckerPiece("White");
			set[13] = new ThirtyFiveRepCheckerPiece("White");
			MLControllerConstants p1const = new MLControllerConstants("Player1const.txt");
			MLWeightConstants p1weights = new MLWeightConstants("Player1weight.txt");
			MLControllerConstants p2const = new MLControllerConstants("Player2const.txt");
			MLWeightConstants p2weights = new MLWeightConstants("Player2weight.txt");
			System.out.println("ALPHABETA TEST");
			MLAlphaBeta Tester = new MLAlphaBeta(p1const, p1weights, p2const, p2weights);
			MLThirtyFiveRepBoard Board = new MLThirtyFiveRepBoard("White", set);
			//Board.initBoard();
			Board.printState();
			while(!Board.isTerminal() && movestaken<maxmoves)
			{
				movestaken++;
				System.out.println("**********************************");
				double start = System.nanoTime();
				Board =  (MLThirtyFiveRepBoard) Board.result(Tester.alphabeta(Board, 2));
				double end = System.nanoTime();
				Board.printState();				
				System.out.println("Time taken: " + (end - start) / 1000000 + "ms");
				break;
			}
			System.out.println(Board.player());
			Board.printState();
			
	}
}

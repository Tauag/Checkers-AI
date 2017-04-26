package Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

import Controller.AlphaBeta;
import Controller.ConstantsLog;
import Controller.ControllerConstants;
import Model.ThirtyFiveRepBoard;
import Model.ThirtyFiveRepCheckerPiece;

public class GameTestLogging {
	
	public static void main(String[] args)
	{
		GameTestLogging obj = new GameTestLogging();
//		obj.apexTest();
//		obj.advTest();
//		obj.kcentTest();
//		obj.mobTest();
//		obj.poleTest();
//		obj.relTest();
		obj.GameLog(2,120);
	}
	
	public void GameLog(int maximumdepth, int maxmoves) 
	{
		ConstantsLog Heuristics = new ConstantsLog();
		int numberofmoves = 0;
		AlphaBeta Tester = new AlphaBeta();
		ThirtyFiveRepBoard Board = new ThirtyFiveRepBoard();
		Board.initBoard();
		while(!Board.isTerminal())
		{
			numberofmoves++;
			System.out.println("**********************************");
			Board = (ThirtyFiveRepBoard) Board.result(Tester.alphabeta(Board, maximumdepth));
			Board.printState();
			if(numberofmoves > maxmoves)
				break;
		}
		PrintWriter writer = null;
		try{

			writer = new PrintWriter("Depth" + maximumdepth + ".txt", "UTF-8");
			writer.println("Number of moves: " + numberofmoves);
			writer.println("Maximum depth was: " + maximumdepth);
			writer.println(Heuristics.ConstantsUsed());
			String test = Board.printStatetoString();
		    writer.write(test);
		    System.out.println(test);
		    writer.flush();
		    writer.close();
//		    writer.println("The second line");
		} catch (IOException e) {
		   // do something
		}
		finally
		{
			if (writer!=null)
				writer.close();
		}
		Board.printState();
	}
}

package Player;

import Controller.*;
import Model.ThirtyFiveRepBoard;
import Model.Move;

public class GeneralPlayer {
	private String _player;
	private ThirtyFiveRepBoard _board;
	private AlphaBeta _abminimax;
	
	public GeneralPlayer(){
		_board = new ThirtyFiveRepBoard();
		_abminimax = new AlphaBeta();
	}
	
	public void setPlayer(String player){
		_player = player;
	}
	
	public String getPlayer(){
		return _player;
	}
	
	/*
	 * Takes a Move object and updates the board accordingly
	 */
	public void advanceMove(Move move){
		_board = (ThirtyFiveRepBoard) _board.result(move);
	}
	
	/*
	 * Use AlphaBeta pruning on Minimax to find the best move and take it
	 */
	public Move findBestPlayerMove(){
		Move move = _abminimax.alphabeta(_board, Controller.ControllerConstants._MAXDEPTH);
		advanceMove(move);
		return move;
	}
	
	public boolean gameOver(){
		return _board.isTerminal();
	}
	
	/*
	 * Returns 1 for WIN, -1 for LOSE and 0 for DRAW
	 */
	public int gameStatus(){
		String gameStatus = _board.getWinner();
		
		if(gameStatus.equals("Draw"))
			return 0;
		else if(gameStatus.equals(_player))
			return 1;
		else
			return -1;
	}
	
	public ThirtyFiveRepBoard getBoard()
	{
		return _board;
	}
}

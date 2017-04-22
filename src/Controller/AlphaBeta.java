package Controller;
import Model.CheckersGameState;
import Model.Move;
import Model.ThirtyFiveRepBoard;

public class AlphaBeta {
	public Move alphabeta(CheckersGameState state, int maxDepth){
		Move maxAction = null;
		int maxValue = Integer.MIN_VALUE;
		int temp;
		
		for(Move a : state.actions()){
			state.printState();
			temp = minValue(state.result(a), maxDepth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, state.player());
			if(temp > maxValue){
				maxAction = a;
				maxValue = temp;
			}
		}
		
		return maxAction;
	}
	
	public int minValue(CheckersGameState state, int maxDepth, int alpha, int beta, String player){
		if(state.isTerminal())
			return state.utility(player);
		else if(maxDepth == 0)
			return state.analyze(player);
		else{
			int value = Integer.MAX_VALUE;
			int temp;
			int beta_prime = beta;
			for(Move move : state.actions()){
				state.printState();
				temp = maxValue(state.result(move), maxDepth - 1, alpha, beta_prime, player);
				if(temp < value)
					value = temp;
				if(value < beta_prime)
					beta_prime = value;
				if(beta_prime <= alpha)
					break;
			}
			return value;
		}
	}
	
	public int maxValue(CheckersGameState state, int maxDepth, int alpha, int beta, String player){
		if(state.isTerminal())
			return state.utility(player);
		else if(maxDepth == 0)
			return state.analyze(player);
		else{
			int value = Integer.MIN_VALUE;
			int temp;
			int alpha_prime = alpha;
			for(Move move : state.actions()){
				state.printState();
				temp = minValue(state.result(move), maxDepth - 1, alpha_prime, beta, player);
				if(temp > value)
					value = temp;
				if(value > alpha_prime)
					alpha_prime = value;
				if(beta <= alpha_prime)
					break;
			}
			return value;
		}
	}	
}

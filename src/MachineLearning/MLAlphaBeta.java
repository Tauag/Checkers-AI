package MachineLearning;

import Model.CheckersGameState;
import Model.Move;

public class MLAlphaBeta {
	public MLControllerConstants P1Constants;
	public MLControllerConstants P2Constants;
	public MLWeightConstants P1Weights;
	public MLWeightConstants P2Weights;
	
	MLAlphaBeta(MLControllerConstants a, MLWeightConstants b, MLControllerConstants c, MLWeightConstants d)
	{
		this.P1Constants = a;
		this.P1Weights = b;
		this.P2Constants = c;
		this.P2Weights = d;
	}
	
	public Move alphabeta(CheckersGameState state, int maxDepth){
		Move maxAction = null;
		int maxValue = Integer.MIN_VALUE;
		int temp;
		
		for(Move a : state.actions()){
//			state.printState();
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
		else if(maxDepth == 0){
//			System.out.println("**************************************");
//			state.printState();
			return state.analyzeML(player, P2Constants, P2Weights);}
		else{
			int value = Integer.MAX_VALUE;
			int temp;
			int beta_prime = beta;
			for(Move move : state.actions()){
//				state.printState();
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
		else if(maxDepth == 0){
//			System.out.println("**************************************");
//			state.printState();
			return state.analyzeML(player, P1Constants, P1Weights);}
		else{
			int value = Integer.MIN_VALUE;
			int temp;
			int alpha_prime = alpha;
			for(Move move : state.actions()){
//				state.printState();
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
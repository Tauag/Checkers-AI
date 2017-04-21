
public class MinimaxSearch {
	public Move minimaxDecision(CheckersGameState state, int maxDepth){
		// Finds best action for Max at state
		
		Move maxAction = null;
		int maxValue = Integer.MIN_VALUE;
		int temp;
		
		for(Move a : state.actions()){
			temp = minValue(state.result(a), maxDepth, 1);
			if(temp > maxValue){
				maxAction = a;
				maxValue = temp;
			}
		}
		
		return maxAction;
	}
	
	public int minValue(CheckersGameState state, int maxDepth, int depthLevel){
		/* Returns the minimum utility of the state if terminal, otherwise,
		 * propogate the value from the terminate state back up to current state
		 * and return
		 */
		
		if(state.isTerminal())
			return state.utility();
		else if(depthLevel >= maxDepth){
			return state.analyze();
		}
		else{
			int minValue = Integer.MAX_VALUE;
			int temp;
			
			for(Move a : state.actions()){
				temp = maxValue(state.result(a), maxDepth, depthLevel+1);
				if(temp < minValue)
					minValue = temp;
			}
			
			return minValue;
		}
	}
	
	public int maxValue(CheckersGameState state, int maxDepth, int depthLevel){
		/* Returns the maximum utility of the state if terminal, otherwise,
		 * propogate the value from the terminate state back up to current state
		 * and return
		 */
		
		if(state.isTerminal())
			return state.utility();
		else if(depthLevel >= maxDepth){
			return state.analyze();
		}
		else{
			int maxValue = Integer.MIN_VALUE;
			int temp;
			
			for(Move a : state.actions()){
				temp = minValue(state.result(a), maxDepth, depthLevel+1);
				if(temp > maxValue)
					maxValue = temp;
			}
			
			return maxValue;
		}
	}
}

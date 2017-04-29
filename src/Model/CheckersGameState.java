package Model;
import java.util.List;

import MachineLearning.MLControllerConstants;
import MachineLearning.MLWeightConstants;

public interface CheckersGameState {
	String player();
	List<Move> actions();
	CheckersGameState result(Move x);
	void printState();
	boolean isTerminal();
	int utility(String player);
	int analyze(String player);
	int analyzeML(String player, MLControllerConstants playercontrols, MLWeightConstants playerweights);
}

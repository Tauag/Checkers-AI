package Model;
import java.util.List;

public interface CheckersGameState {
	String player();
	List<Move> actions();
	CheckersGameState result(Move x);
	void printState();
	boolean isTerminal();
	int utility(String player);
	int analyze(String player);
}

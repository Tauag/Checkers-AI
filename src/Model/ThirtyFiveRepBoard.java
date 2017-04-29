package Model;
import java.util.LinkedList;
import java.util.List;
import Controller.WeightConstants;
import MachineLearning.MLControllerConstants;
import MachineLearning.MLWeightConstants;
import Controller.ControllerConstants;

public class ThirtyFiveRepBoard implements CheckersGameState{
	private String _playerTurn;
	private String _winner;
	private ThirtyFiveRepCheckerPiece[] _set;
	
	public ThirtyFiveRepBoard(){
		_playerTurn = "Black";
		_set = new ThirtyFiveRepCheckerPiece[35];
		initBoard();
	}
	
	public ThirtyFiveRepBoard(String turn, ThirtyFiveRepCheckerPiece[] set){
		_playerTurn = turn;
		_winner = null;
		_set = set;
	}

	public String getWinner(){
		return _winner;
	}

	@Override
	public String player() {
		return _playerTurn;
	}

	@Override
	public List<Move> actions() {
		List<Move> allJumpActions = new LinkedList<Move>();
		List<Move> allSingleActions = new LinkedList<Move>();
		List<Move> temp;
		
		for(int i = 0; i < 35; i++){
			if(_set[i] != null && _set[i].getColor().equals(_playerTurn) && isValid(i)){
				for(Move mv : hasJumpMoves(i, _set[i], _set[i].isKing(), i)){ //for all possible jump moves of each piece
					temp = fillJumpMove(i, _set[i], _set[i].isKing(), mv, i); //temp is a list of possible jump moves
					allJumpActions.addAll(temp);
				}
				if(allJumpActions.size() == 0){
					for(String mv : hasSingleMoves(i, _set[i]))
						allSingleActions.add(fillMove(i, _set[i], mv, new Move(i)));
				}
			}
		}
		if(allJumpActions.size() > 0)
			return allJumpActions;
		else
			return allSingleActions;
	}
	
	/*
	 * Compiles a list of possible jump moves for a given piece
	 */
	public List<Move> hasJumpMoves(int location, ThirtyFiveRepCheckerPiece piece, boolean kinged, int originalLocation){
		List<Move> possibleMoves = new LinkedList<Move>();
		if(piece.getColor().equals("Black")){
			//The logic works like this:
			//First check if the current piece's location and its destination location is valid
			//Second check that the space it is jumping over is filled and it is a piece of the opposing team
			//Finally check that its destination is empty and therefore allows a jump to occur OR if the destination is the same and the jump is cyclical
			if(isValid(location + 8) && isValid(location + 4) && _set[location + 4] != null && _set[location + 4].getColor().equals("White"))
					if(_set[location + 8] == null || ((location + 8) == originalLocation))
						possibleMoves.add(new Move(location, (location + 8), (location + 4)));
			if(isValid(location + 10) && isValid(location + 5) && _set[location + 5] != null && _set[location + 5].getColor().equals("White"))
					if(_set[location + 10] == null || ((location + 10) == originalLocation))
						possibleMoves.add(new Move(location, (location + 10), (location + 5)));
			if(kinged){
				if(isValid(location - 10) && isValid(location - 5) && _set[location - 5] != null && _set[location - 5].getColor().equals("White"))
						if(_set[location - 10] == null || ((location - 10) == originalLocation))
							possibleMoves.add(new Move(location, (location - 10), (location - 5)));
				if(isValid(location - 8) && isValid(location - 4) && _set[location - 4] != null && _set[location - 4].getColor().equals("White"))
						if(_set[location - 8] == null || ((location - 8) == originalLocation))
							possibleMoves.add(new Move(location, (location - 8), (location - 4)));
			}
		}
		
		if(piece.getColor().equals("White")){
			if(isValid(location - 10) && isValid(location - 5) && _set[location - 5] != null && _set[location - 5].getColor().equals("Black"))
					if(_set[location - 10] == null || ((location - 10) == originalLocation))
						possibleMoves.add(new Move(location, (location - 10), (location - 5)));
			if(isValid(location - 8) && isValid(location - 4) && _set[location - 4] != null && _set[location - 4].getColor().equals("Black"))
					if(_set[location - 8] == null || ((location - 8) == originalLocation))
						possibleMoves.add(new Move(location, (location - 8), (location - 4)));
			if(kinged){
				if(isValid(location + 8) && isValid(location + 4) && _set[location + 4] != null && _set[location + 4].getColor().equals("Black"))
						if(_set[location + 8] == null || ((location + 8) == originalLocation))
							possibleMoves.add(new Move(location, (location + 8), (location + 4)));
				if(isValid(location + 10) && isValid(location + 5) && _set[location + 5] != null && _set[location + 5].getColor().equals("Black"))
						if(_set[location + 10] == null || ((location + 10) == originalLocation))
							possibleMoves.add(new Move(location, (location + 10), (location + 5)));
			}
		}
		return possibleMoves;
	}
	
	/*
	 * Compiles a list of non-jump moves for a given piece
	 */
	public List<String> hasSingleMoves(int location, ThirtyFiveRepCheckerPiece piece){
		List<String> retlist = new LinkedList<String>();
		if(piece.getColor().equals("Black")){
			if(isValid(location) && isValid(location + 4) && _set[location + 4] == null)
				retlist.add("L");
			if(isValid(location) && isValid(location + 5) && _set[location + 5] == null)
				retlist.add("R");
			if(piece.isKing()){
				if(isValid(location) && isValid(location - 5) && _set[location - 5] == null)
					retlist.add("BL");
				if(isValid(location) && isValid(location - 4) && _set[location - 4] == null)
					retlist.add("BR");
			}
		}
		else{
			if(isValid(location) && isValid(location - 5) && _set[location - 5] == null)
				retlist.add("L");
			if(isValid(location) && isValid(location - 4) && _set[location - 4] == null)
				retlist.add("R");
			if(piece.isKing()){
				if(isValid(location) && isValid(location + 4) && _set[location + 4] == null)
					retlist.add("BL");
				if(isValid(location) && isValid(location + 5) && _set[location + 5] == null)
					retlist.add("BR");
			}
		}
		return retlist;
	}
	
	/*
	 * Fills up a Move object with the given non-jump commands
	 */
	public Move fillMove(int location, ThirtyFiveRepCheckerPiece target, String command, Move move){
		int nextLocation;
		if(target.getColor().equals("Black")){
			if(command.equals("L")){
				nextLocation = location + 4;
				move.new_coordinate = nextLocation;
			}
			else if(command.equals("R")){
				nextLocation = location + 5;
				move.new_coordinate = nextLocation;
			}
			else if(command.equals("BL")){
				nextLocation = location - 5;
				move.new_coordinate = nextLocation;
			}
			else{
				nextLocation = location - 4;
				move.new_coordinate = nextLocation;
			}
			if(nextLocation > 30)
				move.kinged = true;
		}
		else{
			if(command.equals("L")){
				nextLocation = location - 5;
				move.new_coordinate = nextLocation;
			}
			else if(command.equals("R")){
				nextLocation = location - 4;
				move.new_coordinate = nextLocation;
			}
			else if(command.equals("BL")){
				nextLocation = location + 4;
				move.new_coordinate = nextLocation;
			}
			else{
				nextLocation = location + 5;
				move.new_coordinate = nextLocation;
			}
			if(nextLocation < 4)
				move.kinged = true;
		}
		return move;
	}
	
	public boolean killedalready(Move newestmove, Move previousmove)
	{
		if(!previousmove._kills.containsAll(newestmove._kills))
		{
				return false;
		}
		return true;
	}
	
	/*
	 * Fills up a given Move object with jump commands
	 */
	public List<Move> fillJumpMove(int location, ThirtyFiveRepCheckerPiece target, boolean kinged, Move move, int originalLocation){
		List<Move> retlist = new LinkedList<Move>();
		int nextLocation = move.getnewcoordinate();
		boolean isKing = kinged;
		if(target.getColor().equals("Black")){
				if(move.getnewcoordinate() > 30)
					isKing = true;
				for(Move mv : hasJumpMoves(move.getnewcoordinate(), target, isKing,originalLocation))
					if(!killedalready(mv, move))
					{
						move._kills.add((mv.new_coordinate + mv.old_coordinate) / 2);
						retlist.addAll(fillJumpMove(nextLocation, target, isKing, new Move(move.old_coordinate, mv.getnewcoordinate(), 
								new LinkedList<Integer>(move._kills)), originalLocation));
					}
				}
		else
		{
			if(move.getnewcoordinate() < 4)
				isKing = true;
			for(Move mv : hasJumpMoves(move.getnewcoordinate(), target, isKing, originalLocation))
				if(!killedalready(mv, move))
				{
					move._kills.add((mv.new_coordinate + mv.old_coordinate) / 2);
					retlist.addAll(fillJumpMove(nextLocation, target, isKing, new Move(move.old_coordinate, mv.getnewcoordinate(), 
							new LinkedList<Integer>(move._kills)), originalLocation));
				}		
		}
		if(retlist.size() == 0){
			move.new_coordinate = nextLocation;
			move.kinged = isKing;
			retlist.add(move);
		}
		return retlist;
	}
	
	/*
	 * Positions less than 0, greater than 34 are off limits
	 * Positions 8, 17 and 26 are not valid
	 */
	public boolean isValid(int location){
		if(location < 0 || location > 34 || location == 8 || location == 17 || location == 26)
			return false;
		else
			return true;
	}

	@Override
	public CheckersGameState result(Move x){
		ThirtyFiveRepCheckerPiece[] newset = this.cloneBoard();
		
		if(x.kinged)
			newset[x.old_coordinate].turnKing();
		
		if(x.new_coordinate != x.old_coordinate){
			newset[x.new_coordinate] = newset[x.old_coordinate];
			newset[x.old_coordinate] = null;
		}
		for(int i : x._kills)
			newset[i] = null;
		
		String next;
		if(_playerTurn == "Black")
			next = "White";
		else
			next = "Black";
		
		return new ThirtyFiveRepBoard(next, newset);
	}
	
	public int utility(String player){
		if(_winner.equals("Draw"))
			return 0;
		if(player.equals(_winner))
			return Integer.MAX_VALUE;
		else
			return Integer.MIN_VALUE;
	}

	@Override
	public void printState() {
		int i = 0;
		int curr;
		while(i < 35){
			curr = i;
			while(i < curr + 4){
				System.out.print(" - " + pieceToString(_set[i]));
				i++;
			}
			System.out.println();
			System.out.print(" ");
			curr = i;
			while(i < curr + 4){
				System.out.print(pieceToString(_set[i]) + " - ");
				i++;
			}
			i++;
			System.out.println();
		}	
	}
	
	public String printStatetoString()
	{
		String output = "";
		int i = 0;
		int curr;
		while(i < 35){
			curr = i;
			while(i < curr + 4){
				output = output + " - " + pieceToString(_set[i]);
				i++;
			}
			output = output + "\n";
			output = output + " ";
			curr = i;
			while(i < curr + 4){
				output = output + pieceToString(_set[i]) + " - ";
				i++;
			}
			i++;
			output = output + "\n";
		}	
		return output;
	}
	
	public String pieceToString(ThirtyFiveRepCheckerPiece x){
		if(x == null)
			return "-";
		else if(x.getColor().equals("Black")){
			if(x.isKing())
				return "B";
			else
				return "b";
		}
		else{
			if(x.isKing())
				return "W";
			else
				return "w";
		}
	}
	
	@Override
	public boolean isTerminal() {
		if(actions().isEmpty()){
			_winner = "Draw";
			return true;
		}
		
		String oneColor;
		int i = 0;
		while(_set[i] == null && i < 34)
			i++;
		if(_set[i] == null)
			return true;
		oneColor = _set[i].getColor();
		while(i < 35){
			if(_set[i] != null)
				if(!(_set[i].getColor().equals(oneColor)))
					return false;
			i++;
		}
		_winner = oneColor;
		return true;
	}
	
	/*
	 * Initalizes the board at default starting positions
	 */
	public void initBoard(){
		for(int i = 0; i < 13; i++)
			if(i != 8)
				_set[i] = new ThirtyFiveRepCheckerPiece("Black");
		
		for(int i = 34; i > 21; i--)
			if(i != 26)
				_set[i] = new ThirtyFiveRepCheckerPiece("White");
	}
	
	/*
	 * Basically its hasJumpMoves but returns immediately when a jump is available
	 * Marks a piece as "Active" if it has jumps
	 */
	public boolean isActive(int location, ThirtyFiveRepCheckerPiece piece){
		if(piece.getColor().equals("Black")){
			if(isValid(location + 8) && isValid(location + 4) && _set[location + 4] != null && _set[location + 8] == null)
					if(_set[location + 4].getColor().equals("White"))
						return true;
			if(isValid(location + 10) && isValid(location + 5) && _set[location + 5] != null && _set[location + 10] == null)
					if(_set[location + 5].getColor().equals("White"))
						return true;
			if(piece.isKing()){
				if(isValid(location - 10) && isValid(location - 5) && _set[location - 5] != null && _set[location - 10] == null)
						if(_set[location - 5].getColor().equals("White"))
							return true;
				if(isValid(location - 8) && isValid(location - 4) && _set[location - 4] != null && _set[location - 8] == null)
						if(_set[location - 4].getColor().equals("White"))
							return true;
			}
		}
		
		if(piece.getColor().equals("White")){
			if(isValid(location - 10) && isValid(location - 5) && _set[location - 5] != null && _set[location - 10] == null)
					if(_set[location - 5].getColor().equals("Black"))
						return true;
			if(isValid(location - 8) && isValid(location - 4) && _set[location - 4] != null && _set[location - 8] == null)
					if(_set[location - 4].getColor().equals("Black"))
						return true;
			if(piece.isKing()){
				if(isValid(location + 8) && isValid(location + 4) && _set[location + 4] != null && _set[location + 8] == null)
						if(_set[location + 4].getColor().equals("Black"))
							return true;
				if(isValid(location + 10) && isValid(location + 5) && _set[location + 5] != null && _set[location + 10] == null)
						if(_set[location + 5].getColor().equals("Black"))
							return true;
			}
		}
		
		return false;
	}
	
	/*
	 * Basically hasSingleMoves but returns true on first possible move, false otherwise
	 */
	public boolean canSingleMove(int location, ThirtyFiveRepCheckerPiece piece){
		if(piece.getColor().equals("Black")){
			if(isValid(location) && isValid(location + 4) && _set[location + 4] == null)
				return true;
			if(isValid(location) && isValid(location + 5) && _set[location + 5] == null)
				return true;
			if(piece.isKing()){
				if(isValid(location) && isValid(location - 5) && _set[location - 5] == null)
					return true;
				if(isValid(location) && isValid(location - 4) && _set[location - 4] == null)
					return true;
			}
		}
		else{
			if(isValid(location) && isValid(location - 5) && _set[location - 5] == null)
				return true;
			if(isValid(location) && isValid(location - 4) && _set[location - 4] == null)
				return true;
			if(piece.isKing()){
				if(isValid(location) && isValid(location + 4) && _set[location + 4] == null)
					return true;
				if(isValid(location) && isValid(location + 5) && _set[location + 5] == null)
					return true;
			}
		}
		
		return false;
	}
	
	/*
	 * Checks if a piece is completely surrounded by empty spaces
	 */
	public boolean isPole(int location, ThirtyFiveRepCheckerPiece piece){
		if(piece.getColor().equals("Black")){
			if(isValid(location + 4))
				if(!(_set[location + 4] == null))
					return false;
			if(isValid(location + 5))
				if(!(_set[location + 5] == null))
					return false;
			if(isValid(location - 5))
				if(!(_set[location - 5] == null))
					return false;
			if(isValid(location - 4))
				if(!(_set[location - 4] == null))
					return false;
		}
		else{
			if(isValid(location - 5))
				if(!(_set[location - 5] == null))
					return false;
			if(isValid(location - 4))
				if(!(_set[location - 4] == null))
					return false;
			if(isValid(location + 4))
				if(!(_set[location + 4] == null))
					return false;
			if(isValid(location + 5))
				if(!(_set[location + 5] == null))
					return false;
		}
		
		return true;
	}
	
	/*
	 * Clones a board
	 */
	public ThirtyFiveRepCheckerPiece[] cloneBoard(){
		ThirtyFiveRepCheckerPiece[] board = new ThirtyFiveRepCheckerPiece[35];
		
		for(int i = 0; i < 35; i++){
			if(_set[i] != null)
				board[i] = new ThirtyFiveRepCheckerPiece(_set[i]);
		}
		
		return board;
	}
	
	/***************************************************************   HEURISTICS FUNCTIONS  ****************************************************************/
	
	/*
	 * (non-Javadoc)
	 * Aggragates the total heuristic score
	 * Takes the weights from the constants file
	 */
	public int analyze(String player){
		int heuristicScore = 0;
		
		if(ControllerConstants._ADV)
			heuristicScore += (advheuristic(player) * WeightConstants._ADV);
		if(ControllerConstants._APEX)
			heuristicScore += (apexheuristic(player) * WeightConstants._APEX);
		if(ControllerConstants._BACK)
			heuristicScore += (backheuristic(player) * WeightConstants._BACK);
		if(ControllerConstants._CENT)
			heuristicScore += (centheuristic(player) * WeightConstants._CENT);
		if(ControllerConstants._CNTR)
			heuristicScore += (cntrheuristic(player) * WeightConstants._CNTR);
		if(ControllerConstants._KCENT)
			heuristicScore += (kcentheuristic(player) * WeightConstants._KCENT);
		if(ControllerConstants._MOB)
			heuristicScore += (mobheuristic(player) * WeightConstants._MOB);
		if(ControllerConstants._POLE)
			heuristicScore += (poleheuristic(player) * WeightConstants._POLE);
		if(ControllerConstants._RELATIVECOUNT)
			heuristicScore += (relativecountheuristic(player) * WeightConstants._RELATIVECOUNT);
		
		return heuristicScore;
	}
	
	/*
	 * The parameter is credited with 1 for each passive man in the 5th and 6th rows
	 * (in the direction of the player) and debited 1 for each passive man in the 
	 * 3rd and 4th rows
	 */
	public int advheuristic(String player){
		int heuristicScore = 0;
		if(player.equals("White")){
			int[] advancingRow = {4, 5, 6, 7, 9, 10, 11, 12};
			int[] defendingRow = {13, 14, 15, 16, 18, 19, 20, 21};
			
			for(int pos : advancingRow){
				if(_set[pos] != null)
					if(_set[pos].getColor().equals(player) && !(isActive(pos, _set[pos])))
						heuristicScore++;
			}
			for(int pos : defendingRow){
				if(_set[pos] != null)
					if(_set[pos].getColor().equals(player) && !(isActive(pos, _set[pos])))
						heuristicScore--;
			}
		}
		else{
			int[] advancingRow = {22, 23, 24, 25, 27, 28, 29, 30};
			int[] defendingRow = {13, 14, 15, 16, 18, 19, 20, 21};
			
			for(int pos : advancingRow){
				if(_set[pos] != null)
					if(_set[pos].getColor().equals(player) && !(isActive(pos, _set[pos])))
						heuristicScore++;
			}
			for(int pos : defendingRow){
				if(_set[pos] != null)
					if(_set[pos].getColor().equals(player) && !(isActive(pos, _set[pos])))
						heuristicScore--;
			}
		}
//		System.out.println(heuristicScore);
		return heuristicScore;
	}
	
	public int AGGRO(String player)
	{
		String opponentpiececolor = "";
		String mypiececolor = "";
		if(player.equals("White"))
		{
			opponentpiececolor = "B";
			mypiececolor = "W";
		}
		else
		{
			opponentpiececolor = "W";
			mypiececolor = "B";
		}
			
		List<ThirtyFiveRepCheckerPiece> OpposingPlayersPieces = new LinkedList<ThirtyFiveRepCheckerPiece>();
		for(int i = 0; i < _set.length; i++)
		{
			if(_set[i].getColor().toUpperCase().equals(opponentpiececolor))
			{
				
			}
		}
		return 0;
	}
	
	/*
	 * -1 if there are no kings, positions 6 and 28 are not filled with active pieces and neither of them are 
	 * filled with passive pieces
	 */
	public int apexheuristic(String player){
		boolean noKings = true, eitherActive = false, neitherPassive = true;
		
		if(_set[6] != null && _set[6].getColor().equals(player)){
			eitherActive |= isActive(6, _set[6]);
			neitherPassive &= isActive(6, _set[6]);
		}
		if(_set[28] != null && _set[28].getColor().equals(player)){
			eitherActive |= isActive(28, _set[28]);
			neitherPassive &= isActive(28, _set[28]);
		}
		for(ThirtyFiveRepCheckerPiece piece : _set){
			if(piece != null)
				if(piece.isKing())
					noKings = false;
		}
		
		if(noKings && eitherActive && neitherPassive)
			return -1;
		else
			return 0;
	}
	
	/*
	 * The parameter is credited with 1 if there are no active kings on the board 
	 * and if the two bridge squares (0 and 2, or 32 and 34) in the back row 
	 * are occupied by passive pieces.
	 */
	public int backheuristic(String player){
		boolean noActiveKings = true, bridgePassiveOccupation = true;
		
		for(int i = 0; i < 35; i++){
			if(_set[i] != null)
				if(_set[i].isKing() && isActive(i, _set[i]))
					noActiveKings = false;
		}
		
		if(player.equals("Black")){
			if(_set[0] != null)
				bridgePassiveOccupation &= (_set[0].getColor().equals("Black") && !(isActive(0, _set[0])));
			else
				bridgePassiveOccupation = false;
			if(_set[2] != null)
				bridgePassiveOccupation &= (_set[2].getColor().equals("Black") && !(isActive(2, _set[2])));
			else
				bridgePassiveOccupation = false;
		}
		else{
			if(_set[32] != null)
				bridgePassiveOccupation &= (_set[32].getColor().equals("White") && !(isActive(32, _set[32])));
			else
				bridgePassiveOccupation = false;
			if(_set[34] != null)
				bridgePassiveOccupation &= (_set[34].getColor().equals("White") && !(isActive(34, _set[34])));
			else
				bridgePassiveOccupation = false;
		}
		
		if(noActiveKings && bridgePassiveOccupation)
			return 1;
		else
			return 0;
	}
	
	/*
	 * The parameter is credited with 1 for each of the following squares: 
	 * 10, 11, 14, 15, 19, 20, 23 and 24 which is occupied by a passive man.
	 */
	public int centheuristic(String player){
		int[] centerPositions = {10, 11, 14, 15, 19, 20, 23, 24};
		int heuristicScore = 0;
		
		for(int pos : centerPositions){
			if(_set[pos] != null)
				if(_set[pos].getColor().equals(player) && !(isActive(pos, _set[pos])))
					heuristicScore++;
		}
		
		return heuristicScore;
	}
	
	/*
	 * The parameter is credited with 1 for each of the following squares: 
	 * 10, 11, 14, 15, 19, 20, 23 and 24 that is either currently occupied by an active piece
	 */
	public int cntrheuristic(String player){
		int[] centerPositions = {10, 11, 14, 15, 19, 20, 23, 24};
		int heuristicScore = 0;
		
		for(int pos : centerPositions){
			if(_set[pos] != null)
				if(_set[pos].getColor().equals(player) && isActive(pos, _set[pos]))
					heuristicScore++;
		}
		
		return heuristicScore;
	}
	
	/*
	 * The parameter is credited with 1 for each of the following squares: 
	 * 10, 11, 14, 15, 19, 20, 23 and 24 which is occupied by a passive king
	 */
	public int kcentheuristic(String player){
		int[] centerPositions = {10, 11, 14, 15, 19, 20, 23, 24};
		int heuristicScore = 0;
		
		for(int pos : centerPositions){
			if(_set[pos] != null)
				if(_set[pos].getColor().equals(player) && _set[pos].isKing() && !(isActive(pos, _set[pos])))
					heuristicScore++;
		}
		
		return heuristicScore;
	}
	
	/*
	 * The parameter is credited with 1 for each square to which the active side could 
	 * move one or more pieces in the normal fashion, 
	 * disregarding the fact that jump moves may or may not be available
	 */
	public int mobheuristic(String player){
		int heuristicScore = 0;
		
		for(int i = 0; i < 35; i++){
			if(_set[i] != null)
				if(_set[i].getColor().equals(player) && canSingleMove(i, _set[i]))
					heuristicScore++;
		}
		
		return heuristicScore;
	}

	/*
	 * The parameter is credited with 1 for each passive man that is completely surrounded by empty squares.
	 */
	public int poleheuristic(String player){
		int heuristicScore = 0;
		
		for(int i = 0; i< 35; i++){
			if(_set[i] != null)
				if(_set[i].getColor().equals(player) && isPole(i, _set[i]))
					heuristicScore++;
		}
		
		return heuristicScore;
	}

	/*
	 * The parameter is credited with one for each piece it has more than the opponent. It is debited for
	 * each piece the opponent has more than the player.
	 */
	public int relativecountheuristic(String player){
		int heuristicScore = 0;
		
		for(int i = 0; i < 35; i++){
			if(_set[i] != null){
				if(_set[i].getColor().equals(player))
					heuristicScore++;
				else
					heuristicScore--;
			}
		}
		
		return heuristicScore;
	}

	@Override
	public int analyzeML(String player, MLControllerConstants playercontrols, MLWeightConstants playerweights) {
		// TODO Auto-generated method stub
		return 0;
	}
}

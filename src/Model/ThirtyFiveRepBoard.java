package Model;
import java.util.LinkedList;
import java.util.List;
import Controller.WeightConstants;
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
				for(String mv : hasJumpMoves(i, _set[i], _set[i].isKing())){
					temp = fillJumpMove(i, _set[i], _set[i].isKing(), mv, new Move(i));
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
	public List<String> hasJumpMoves(int location, ThirtyFiveRepCheckerPiece piece, boolean kinged){
		List<String> possibleMoves = new LinkedList<String>();
		if(piece.getColor().equals("Black")){
			if(isValid(location + 8) && isValid(location + 4) && _set[location + 4] != null && _set[location + 8] == null)
					if(_set[location + 4].getColor().equals("White"))
						possibleMoves.add("JL");
			if(isValid(location + 10) && isValid(location + 5) && _set[location + 5] != null && _set[location + 10] == null)
					if(_set[location + 5].getColor().equals("White"))
						possibleMoves.add("JR");
			if(kinged){
				if(isValid(location - 10) && isValid(location - 5) && _set[location - 5] != null && _set[location - 10] == null)
						if(_set[location - 5].getColor().equals("White"))
							possibleMoves.add("JBL");
				if(isValid(location - 8) && isValid(location - 4) && _set[location - 4] != null && _set[location - 8] == null)
						if(_set[location - 4].getColor().equals("White"))
							possibleMoves.add("JBR");
			}
		}
		
		if(piece.getColor().equals("White")){
			if(isValid(location - 10) && isValid(location - 5) && _set[location - 5] != null && _set[location - 10] == null)
					if(_set[location - 5].getColor().equals("Black"))
						possibleMoves.add("JL");
			if(isValid(location - 8) && isValid(location - 4) && _set[location - 4] != null && _set[location - 8] == null)
					if(_set[location - 4].getColor().equals("Black"))
						possibleMoves.add("JR");
			if(kinged){
				if(isValid(location + 8) && isValid(location + 4) && _set[location + 4] != null && _set[location + 8] == null)
						if(_set[location + 4].getColor().equals("Black"))
							possibleMoves.add("JBL");
				if(isValid(location + 10) && isValid(location + 5) && _set[location + 5] != null && _set[location + 10] == null)
						if(_set[location + 5].getColor().equals("Black"))
							possibleMoves.add("JBR");
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
	
	/*
	 * Fills up a given Move object with jump commands
	 */
	public List<Move> fillJumpMove(int location, ThirtyFiveRepCheckerPiece target, boolean kinged, String command, Move move){
		List<Move> retlist = new LinkedList<Move>();
		int nextLocation = location;
		boolean isKing = kinged;
		if(target.getColor().equals("Black")){
			if(command.equals("JL")){
				nextLocation = location + 8;
				move.addToKills(location + 4);
				if(nextLocation > 30)
					isKing = true;
				for(String mv : hasJumpMoves(nextLocation, target, isKing))
					if(!mv.equals("JBR"))
						retlist.addAll(fillJumpMove(nextLocation, target, isKing, mv, new Move(move.old_coordinate, new LinkedList<Integer>(move._kills))));
			}
			else if(command.equals("JR")){
				nextLocation = location + 10;
				move.addToKills(location + 5);
				if(nextLocation > 30)
					isKing = true;
				for(String mv : hasJumpMoves(nextLocation, target, isKing))
					if(!mv.equals("JBL"))
					retlist.addAll(fillJumpMove(nextLocation, target, isKing, mv, new Move(move.old_coordinate, new LinkedList<Integer>(move._kills))));
			}
			else if(command.equals("JBL") && kinged){
				nextLocation = location - 10;
				move.addToKills(location - 5);
				if(nextLocation > 30)
					isKing = true;
				for(String mv : hasJumpMoves(nextLocation, target, isKing))
					if(!mv.equals("JR"))
						retlist.addAll(fillJumpMove(nextLocation, target, isKing, mv, new Move(move.old_coordinate, new LinkedList<Integer>(move._kills))));
			}
			else if(command.equals("JBR") && kinged){
				nextLocation = location - 8;
				move.addToKills(location - 4);
				if(nextLocation > 30)
					isKing = true;
				for(String mv : hasJumpMoves(nextLocation, target, isKing))
					if(!mv.equals("JL"))
						retlist.addAll(fillJumpMove(nextLocation, target, isKing, mv, new Move(move.old_coordinate, new LinkedList<Integer>(move._kills))));
			}
		}
		else{
			if(command.equals("JL")){
				nextLocation = location - 10;
				move.addToKills(location - 5);
				if(nextLocation < 4)
					isKing = true;
				for(String mv : hasJumpMoves(nextLocation, target, isKing))
					if(!mv.equals("JBR"))
						retlist.addAll(fillJumpMove(nextLocation, target, isKing, mv, new Move(move.old_coordinate, new LinkedList<Integer>(move._kills))));
			}
			else if(command.equals("JR")){
				nextLocation = location - 8;
				move.addToKills(location - 4);
				if(nextLocation < 4)
					isKing = true;
				for(String mv : hasJumpMoves(nextLocation, target, isKing))
					if(!mv.equals("JBL"))
						retlist.addAll(fillJumpMove(nextLocation, target, isKing, mv, new Move(move.old_coordinate, new LinkedList<Integer>(move._kills))));
			}
			else if(command.equals("JBL") && kinged){
				nextLocation = location + 8;
				move.addToKills(location + 4);
				if(nextLocation < 4)
					isKing = true;
				for(String mv : hasJumpMoves(nextLocation, target, isKing))
					if(!mv.equals("JR"))
						retlist.addAll(fillJumpMove(nextLocation, target, isKing, mv, new Move(move.old_coordinate, new LinkedList<Integer>(move._kills))));
			}
			else if(command.equals("JBR") && kinged){
				nextLocation = location + 10;
				move.addToKills(location + 5);
				if(nextLocation < 4)
					isKing = true;
				for(String mv : hasJumpMoves(nextLocation, target, isKing))
					if(!mv.equals("JL"))
						retlist.addAll(fillJumpMove(nextLocation, target, isKing, mv, new Move(move.old_coordinate, new LinkedList<Integer>(move._kills))));
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
	public CheckersGameState result(Move x) {
		if(x.kinged)
			_set[x.old_coordinate].turnKing();
		
		ThirtyFiveRepCheckerPiece[] newset = _set.clone();
		newset[x.new_coordinate] = newset[x.old_coordinate];
		newset[x.old_coordinate] = null;
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
				System.out.print("-" + pieceToString(_set[i]));
				i++;
			}
			System.out.println();
			curr = i;
			while(i < curr + 4){
				System.out.print(pieceToString(_set[i]) + "-");
				i++;
			}
			i++;
			System.out.println();
		}	
	}
	
	public String pieceToString(ThirtyFiveRepCheckerPiece x){
		if(x == null)
			return "-";
		else if(x.getColor().equals("Black"))
			return "b";
		else
			return "w";
	}
	
	@Override
	public boolean isTerminal() {
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
	 * Takes samuel coordinates and converts it to row,column format
	 * Returns a Location object
	 */
	public Location samuelToXY(int pos){
		int augmented, row, col;
		
		if(pos < 8){
			augmented = (7 - pos) * 2;
			row = augmented / 8;
			col = augmented % 8;
			if (row % 2 == 0) ++col;
			row += 6;
			col = 7 - col;
		}
		else if(pos < 17){
			augmented = (16 - pos) * 2;
			row = augmented / 8;
			col = augmented % 8;
			if (row % 2 == 0) ++col;
			row += 4;
			col = 7 - col;
		}
		else if(pos < 26){
			augmented = (25 - pos) * 2;
			row = augmented / 8;
			col = augmented % 8;
			if (row % 2 == 0) ++col;
			row += 2;
			col = 7 - col;
		}
		else{
			augmented = (34 - pos) * 2;
			row = augmented / 8;
			col = augmented % 8;
			if (row % 2 == 0) ++col;
			col = 7 - col;
		}
		
		return new Location(row, col);
	}
	
	/*
	 * Basically its hasJumpMoves but returns immediately when a jump is availiable
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
	
	/***************************************************************   HEURISTICS FUNCTIONS  ****************************************************************/
	/*
	 * (non-Javadoc)
	 * Aggragates the total heuristic score
	 * Takes the weights from the constants file
	 */
	public int analyze(String player){
		int heuristicScore = 0;
		
		if(ControllerConstants._APEX)
			heuristicScore += (apexheuristic(player) * WeightConstants._APEX);
		if(ControllerConstants._BACK)
			heuristicScore += (backheuristic(player) * WeightConstants._BACK);
		
		return heuristicScore;
	}
	
	/*
	 * -1 if there are no kings, positions 6 and 25 are not filled with active pieces and neither of them are 
	 * filled with passive pieces
	 */
	public int apexheuristic(String player){
		boolean noKings = false, bothActive = false, neitherPassive = true;
		
		if(_set[6] != null){
			bothActive |= isActive(6, _set[6]);
			neitherPassive &= isActive(6, _set[6]);
		}
		if(_set[25] != null){
			bothActive |= isActive(25, _set[25]);
			neitherPassive &= isActive(25, _set[25]);
		}
		for(ThirtyFiveRepCheckerPiece piece : _set){
			if(piece != null)
				if(piece.isKing())
					noKings = true;
		}
		
		if(noKings && bothActive && !(neitherPassive))
			return -1;
		else
			return 0;
	}
	
	public int backheuristic(String player){
		// TODO
		return 0;
	}
}

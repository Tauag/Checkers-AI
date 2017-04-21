import java.util.LinkedList;
import java.util.List;

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
	
	public int utility(){
		if(_playerTurn.equals(_winner))
			return 1;
		else
			return -1;
	}
	
	public int analyze(){
		// TODO
		return 0;
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
	
	public void initBoard(){
		for(int i = 0; i < 13; i++)
			if(i != 8)
				_set[i] = new ThirtyFiveRepCheckerPiece("Black");
		
		for(int i = 34; i > 21; i--)
			if(i != 26)
				_set[i] = new ThirtyFiveRepCheckerPiece("White");
	}
}

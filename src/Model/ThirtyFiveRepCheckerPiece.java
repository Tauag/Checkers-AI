package Model;

public class ThirtyFiveRepCheckerPiece {
	private boolean _isKing;
	private String _color;
	
	public ThirtyFiveRepCheckerPiece(String color){
		_isKing = false;
		_color = color;
	}
	
	public ThirtyFiveRepCheckerPiece(ThirtyFiveRepCheckerPiece piece){
		_isKing = piece.isKing();
		_color = piece.getColor();
	}
	
	public void turnKing(){
		_isKing = true;
	}
	
	public String getColor(){
		return _color;
	}
	
	public boolean isKing(){
		return _isKing;
	}
	
	public String toString(){
		if(isKing())
			return _color + " " + "King";
		else
			return _color + " " + "Pawn";
 	}
}
